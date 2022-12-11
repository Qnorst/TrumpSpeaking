package com.vladimir.trumpspeaking.tools;

public class Helper {
    public static boolean isNumber(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }
}
