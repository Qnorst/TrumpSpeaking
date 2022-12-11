package com.vladimir.trumpspeaking.buildMenu.tools;

public enum ButtonId {

    ABOUT("ABOUT"),
    BACK("BACK"),
    FIND_QUOTE("FIND_QUOTE"),
    FIND_QUOTE_WITH_NAME("FIND_QUOTE_WITH_NAME"),
    ADD_FAVORITE("ADD_FAVORITE"),
    ADD_FAVORITE_NO_NAME("ADD_FAVORITE_NO_NAME"),
    DELETE_MY_QUOTE("DELETE_MY_QUOTE"),
    BACK_MY_FAVORITES("BACK_MY_FAVORITES"),
    MY_FAVORITE("MY_FAVORITE"),
    CHOICE_GENERATION_WITH_NAME("CHOICE_GENERATION_WITH_NAME"),
    MORE_WITH_NAME("MORE_WITH_NAME"),
    NEW_NAME("NEW_NAME");


    private String idButton;
    ButtonId(String idButton){
        this.idButton = idButton;
    }

    public String value(){
        return this.idButton;
    }
}
