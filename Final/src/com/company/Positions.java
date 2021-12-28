package com.company;

import java.util.HashMap;

public class Positions {
    public static HashMap<String, Integer> all = new HashMap<>() {{
        put("left-top", 0);
        put("left-bottom", 1);
        put("right-top", 2);
        put("right-bottom", 3);
        put("center", 4);
    }};
    public static final Integer LEFT_TOP = all.get("left-top");
    public static final Integer LEFT_BOTTOM = all.get("left-bottom");
    public static final Integer RIGHT_TOP = all.get("right-top");
    public static final Integer RIGHT_BOTTOM = all.get("right-bottom");
    public static final Integer CENTER = all.get("center");
}
