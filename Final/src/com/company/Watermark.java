package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Watermark {
    public static final String defaultColor = "white";
    public static final String defaultPosition = "center";
    public static final int defaultSize = 10;
    public static final int defaultOpacity = 10;

    Color color = Colors.all.get(defaultColor);
    int position = Positions.all.get(defaultPosition);
    int opacity = defaultOpacity;
    int size = defaultSize;

    public void addWatermark(String text, String input, String output) {
        try {
            File sourceImageFile = new File(input);
            File destImageFile = new File(output);

            BufferedImage sourceImage = ImageIO.read(sourceImageFile);
            Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();

            // initializes necessary graphic properties
            AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) opacity / 100);
            g2d.setComposite(alphaChannel);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 64));
            FontMetrics fontMetrics = g2d.getFontMetrics();
            Rectangle2D rect = fontMetrics.getStringBounds(text, g2d);

            // calculates the coordinate where the String is painted
            int centerX = (sourceImage.getWidth() - (int) rect.getWidth()) / 2;
            int centerY = sourceImage.getHeight() / 2;

            // paints the textual watermark
            g2d.drawString(text, centerX, centerY);

            ImageIO.write(sourceImage, "png", destImageFile);
            g2d.dispose();

            System.out.println("The text watermark is added to the image.");

        } catch (IOException e) {
            e.printStackTrace();
        }
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