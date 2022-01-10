package com.company;

import org.apache.commons.cli.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Options options = Main.getOptions();
        Watermark watermark = new Watermark();
        try {
            CommandLine cmd = new DefaultParser().parse(options, args);
            String text = cmd.getOptionValue("text");
            String source = cmd.getOptionValue("source");
            watermark.setColor(cmd.getOptionValue("color"));
            watermark.setPosition(cmd.getOptionValue("position"));
            watermark.setSize(cmd.getOptionValue("size"));
            watermark.setOpacity(cmd.getOptionValue("opacity"));
            watermark.addWatermark(text, source, cmd.getOptionValue("output"));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            new HelpFormatter().printHelp("watermark", options);
            System.exit(1);
        }
//        watermark.addWatermark("shit", "https://picsum.photos/200/300", "test-out.png");
    }

    public static Options getOptions() {
        Options options = new Options();
        Option text = new Option("t", "text", true,
                "watermark text");
        Option source = new Option("s", "source", true,
                "source image path or http/https image link");
        Option output = new Option("o", "output", true,
                "watermarked image path");
        Option color = new Option("c", "color", true,
                "color of watermark: " + String.join("|", Colors.all.keySet()));
        Option position = new Option("p", "position", true,
                "position of watermark: " + String.join("|", Positions.all.keySet()));
        Option size = new Option(null, "size", true,
                "size of watermark: from 1% to 100% of source image width");
        Option opacity = new Option(null, "opacity", true,
                "opacity of watermark: from 1% to 100%");

        text.setRequired(true);
        source.setRequired(true);
        output.setRequired(false);
        color.setRequired(false);
        position.setRequired(false);
        opacity.setRequired(false);
        size.setRequired(false);

        options.addOption(text);
        options.addOption(source);
        options.addOption(output);
        options.addOption(color);
        options.addOption(position);
        options.addOption(size);
        options.addOption(opacity);

        return options;
    }
}
