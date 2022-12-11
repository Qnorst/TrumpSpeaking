package com.vladimir.trumpspeaking.buildMenu.tools;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class RowsButton {

    private static InlineKeyboardButton createButton(Button button) {
        InlineKeyboardButton buttonKeyboard = new InlineKeyboardButton(button.getText());
        buttonKeyboard.setCallbackData(button.getIdButton().value());
        return buttonKeyboard;
    }

    private static InlineKeyboardButton createButtonForFavoriteQuotes(Button button){
        InlineKeyboardButton buttonKeyboard = new InlineKeyboardButton(button.getText());
        buttonKeyboard.setCallbackData(String.valueOf(button.getButtonId()));
        return buttonKeyboard;
    }

    public static List<List<InlineKeyboardButton>> createKeyboard(List<Button>... buttons) {
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        for (var item1 : buttons) {
            List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
            for (var item2 : item1) {
                inlineKeyboardButtons.add(createButton(item2));
            }
            rows.add(inlineKeyboardButtons);
        }

        return rows;
    }

    public static List<List<InlineKeyboardButton>> createKeyboardForFavoriteQuote(List<List<Button>> buttons){
        List<List<InlineKeyboardButton>> rowsForFavoriteQuotes = new ArrayList<>();
        for (var item: buttons){
            List<InlineKeyboardButton> tempRow = new ArrayList<>();
            for (var button:item)
                tempRow.add(createButtonForFavoriteQuotes(button));
            rowsForFavoriteQuotes.add(tempRow);
        }

        List<InlineKeyboardButton> backButtonRow = new ArrayList<>();
        backButtonRow.add(createButton(new Button("<< Назад", ButtonId.BACK)));
        rowsForFavoriteQuotes.add(backButtonRow);

        return rowsForFavoriteQuotes;
    }
}
