package com.company;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Pattern;

public class Watermark {
    public static final String defaultColor = "white";
    public static final String defaultPosition = "center";
    public static final int defaultSize = 10;
    public static final int defaultOpacity = 10;
    public static final String fontName = "Arial";
    public static final int fontStyle = Font.BOLD;

    Color color = Colors.all.get(defaultColor);
    int position = Positions.all.get(defaultPosition);
    int opacity = defaultOpacity;
    int size = defaultSize;

    public void addWatermark(String text, String source, String output) {
        try {
            BufferedImage sourceImage = getSourceImage(source);
            String outputImageName = getOutputImageName(source, output);
            File outputImage = new File(outputImageName);

            Graphics2D graphics2D = (Graphics2D) sourceImage.getGraphics();
            graphics2D.setColor(color);
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) opacity / 100));
            graphics2D.setFont(new Font(fontName, fontStyle, getSize(text, sourceImage)));

            Rectangle2D textRectangle = graphics2D.getFontMetrics().getStringBounds(text, graphics2D);
            graphics2D.drawString(text, getX(textRectangle, sourceImage), getY(textRectangle, sourceImage));

            ImageIO.write(sourceImage, "png", outputImage);
            graphics2D.dispose();

            System.out.println("The text watermark is added to the image");
            System.out.println("The output image saved in " + outputImageName);
        } catch (IOException ignored) {
            System.out.println("ERROR: Cannot get source image!");
        }
    }

    private BufferedImage getSourceImage(String source) throws IOException, IIOException {
        File image = new File(source);
        if (image.exists() && image.isFile()) {
            return ImageIO.read(new File(source));
        }
        if (Pattern.matches("^(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]$", source)) {
            return ImageIO.read(new URL(source));
        }
        throw new FileNotFoundException();
    }

    private String getOutputImageName(String source, String output) {
        String endOfName = "-watermarked.png";
        if (output != null) {
            if (Pattern.matches("^[-_. A-Za-z0-9]+$", output)) {
                source = output;
                endOfName = ".png";
            } else {
                System.out.println("Invalid output file name, " +
                        "so the output file name will be taken from source file name");
            }
        }
        String fileName = new File(source).getName();
        return (fileName.contains(".") ? fileName.substring(0, fileName.lastIndexOf('.')) : fileName) + endOfName;
    }

    private int getSize(String text, BufferedImage image) {
        Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        int width = image.getWidth() / 100 * size;
        int s = 0;
        while (s == 0 || graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth() < width) {
            graphics2D.setFont(new Font(fontName, fontStyle, ++s));
        }
        return s;
    }

    private int getX(Rectangle2D rectangle2D, BufferedImage image) {
        if (position == Positions.LEFT_TOP || position == Positions.LEFT_BOTTOM) {
            return 0;
        } else if (position == Positions.RIGHT_TOP || position == Positions.RIGHT_BOTTOM) {
            return image.getWidth() - (int) rectangle2D.getWidth();
        }
        return (image.getWidth() - (int) rectangle2D.getWidth()) / 2;
    }

    private int getY(Rectangle2D rectangle2D, BufferedImage image) {
        if (position == Positions.LEFT_TOP || position == Positions.RIGHT_TOP) {
            return (int) rectangle2D.getHeight() / 3 * 2;
        } else if (position == Positions.LEFT_BOTTOM || position == Positions.RIGHT_BOTTOM) {
            return image.getHeight() - (int) (rectangle2D.getHeight() / 4);
        }
        return (image.getHeight() + (int) rectangle2D.getHeight()) / 2;
    }

    public void setColor(String value) {
        if (value == null) {
            return;
        }
        if (Colors.all.containsKey(value)) {
            color = Colors.all.get(value);
            return;
        }
        System.out.println("Cannot parse a correct value from color argument");
        System.out.println("Color set to default value: " + defaultColor);
        color = Colors.all.get(defaultColor);
    }

    public void setPosition(String value) {
        if (value == null) {
            return;
        }
        if (Positions.all.containsKey(value)) {
            position = Positions.all.get(value);
            return;
        }
        System.out.println("Cannot parse a correct value from position argument");
        System.out.println("Position set to default value: " + defaultPosition);
        position = Positions.all.get(defaultPosition);
    }

    public void setSize(String value) {
        if (value == null) {
            return;
        }
        try {
            int s = Integer.parseInt(value);
            if (1 <= s && s <= 100) {
                size = s;
                return;
            }
            System.out.println("Size must be a value between 1 and 100");
        } catch (NumberFormatException ignored) {
            System.out.println("Cannot parse a number from a size argument");
        }
        System.out.println("Size set to default value: " + defaultSize + "%");
        size = defaultSize;
    }

    public void setOpacity(String value) {
        if (value == null) {
            return;
        }
        try {
            int o = Integer.parseInt(value);
            if (1 <= o && o <= 100) {
                opacity = o;
                return;
            }
            System.out.println("Opacity must be a value between 1 and 100");
        } catch (NumberFormatException ignored) {
            System.out.println("Cannot parse a number from a opacity argument");
        }
        System.out.println("Opacity set to default value: " + defaultOpacity + "%");
        opacity = defaultOpacity;
    }
}