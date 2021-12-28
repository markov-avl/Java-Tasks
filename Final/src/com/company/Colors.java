package com.company;

import java.awt.Color;
import java.util.HashMap;

public class Colors {
    public static HashMap<String, Color> all = new HashMap<>() {{
        put("white", Color.WHITE);
        put("black", Color.BLACK);
        put("red", Color.RED);
        put("green", Color.GREEN);
        put("blue", Color.BLUE);
    }};
    public static final Color WHITE = all.get("white");
    public static final Color BLACK = all.get("black");
    public static final Color RED = all.get("red");
    public static final Color GREEN = all.get("green");
    public static final Color BLUE = all.get("blue");
}
