package com.vladimir.trumpspeaking.buildMenu.tools;

public class Button {
    private String text;
    private ButtonId idButton;
    private int buttonId;

    public Button(String text, ButtonId idButton) {
        this.text = text;
        this.idButton = idButton;
    }

    public Button(String text, int buttonId) {
        this.text = text;
        this.buttonId = buttonId;
    }

    public String getText() {
        return text;
    }

    public ButtonId getIdButton() {
        return idButton;
    }

    public int getButtonId() {
        return buttonId;
    }
}
