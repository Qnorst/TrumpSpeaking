package com.vladimir.trumpspeaking.buildMenu.tools;

import com.vladimir.trumpspeaking.models.FavoriteQuote;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class BuildKeyboard {
    public static List<List<InlineKeyboardButton>> buildMenu() {
        var rawOne = List.of(new Button("Сгенерировать цитату с именем", ButtonId.FIND_QUOTE_WITH_NAME));
        var rawTwo = List.of(new Button("Сгенерировать цитату", ButtonId.FIND_QUOTE));
        var rawThree = List.of(new Button("Мое избранное", ButtonId.MY_FAVORITE));
        var rawFour = List.of(new Button("О боте", ButtonId.ABOUT));

        return RowsButton.createKeyboard(rawOne, rawTwo, rawThree, rawFour);
    }

    public static List<List<InlineKeyboardButton>> buildAbout() {
        return RowsButton.createKeyboard(backButton());
    }

    public static List<List<InlineKeyboardButton>> buildGenerationQuoteWithName() {
        var rawOne = List.of(new Button("Сгенерировать еще", ButtonId.CHOICE_GENERATION_WITH_NAME));
        var rawTwo = List.of(new Button("Добавить в избранное", ButtonId.ADD_FAVORITE));

        return RowsButton.createKeyboard(rawOne, rawTwo, backButton());
    }

    public static List<List<InlineKeyboardButton>> buildChoiceGenerationQuote() {
        var rawOne = List.of(new Button("То же имя", ButtonId.MORE_WITH_NAME));
        var rawTwo = List.of(new Button("Другое имя", ButtonId.NEW_NAME));

        return RowsButton.createKeyboard(rawOne, rawTwo, backButton());
    }

    public static List<List<InlineKeyboardButton>> buildGenerateQuote() {
        var rawOne = List.of(new Button("Сгенерировать еще", ButtonId.FIND_QUOTE));
        var rowTwo = List.of(new Button("Добавить в избранное", ButtonId.ADD_FAVORITE_NO_NAME));

        return RowsButton.createKeyboard(rawOne, rowTwo, backButton());
    }

    public static List<List<InlineKeyboardButton>> buildMyFavorites(List<FavoriteQuote> favoriteQuotes) {
        List<List<Button>> plugButtons = new ArrayList<>();
        for (var quote : favoriteQuotes){
            plugButtons.add(List.of(new Button(quote.getTitleQuote(), quote.getId())));
        }

        return RowsButton.createKeyboardForFavoriteQuote(plugButtons);
    }

    public static List<List<InlineKeyboardButton>> buildEditMyFavoriteQuote() {
        var rowOne = List.of(new Button("Удалить", ButtonId.DELETE_MY_QUOTE));
        var rowTwo = List.of(new Button("<< Назад", ButtonId.BACK_MY_FAVORITES));

        return RowsButton.createKeyboard(rowOne, rowTwo);
    }

    public static List<List<InlineKeyboardButton>> buildAddFavoriteMessageWithName() {
        var rowOne = List.of(new Button("Сгенерировать", ButtonId.CHOICE_GENERATION_WITH_NAME));
        return RowsButton.createKeyboard(rowOne, backButton());
    }

    public static List<List<InlineKeyboardButton>> buildAddFavoriteMessage() {
        var rowOne = List.of(new Button("Сгенерировать", ButtonId.FIND_QUOTE));
        return RowsButton.createKeyboard(rowOne, backButton());
    }

    public static List<List<InlineKeyboardButton>> buildDeleteFromMyFavoriteQuote(){
        return RowsButton.createKeyboard(List.of(new Button("<< Назад", ButtonId.BACK_MY_FAVORITES)));
    }

    private static List<Button> backButton() {
        return List.of(new Button("<< Назад", ButtonId.BACK));
    }

}
