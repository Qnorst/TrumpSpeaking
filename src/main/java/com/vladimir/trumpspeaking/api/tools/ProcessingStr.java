package com.vladimir.trumpspeaking.api.tools;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ProcessingStr {
    public static String processingTitle(String text){
        return String.join(" ", Arrays.copyOfRange(text.split(" "), 0, 4))+"...";
    }

    public static String replaceSpace(String str){
        return str.trim().replace(" ", "%20");
    }
}
