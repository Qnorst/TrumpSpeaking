package com.vladimir.trumpspeaking.buildMenu;

import com.vladimir.trumpspeaking.buildMenu.tools.BuildKeyboard;
import com.vladimir.trumpspeaking.buildMenu.tools.BuildTextMessage;
import com.vladimir.trumpspeaking.models.FavoriteQuote;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

public class Keyboard {
    public SendMessage drawMenu(String chatId){
        return BuildTextMessage.buildSend(chatId, "Меню",
                new InlineKeyboardMarkup(BuildKeyboard.buildMenu()));
    }

    public EditMessageText drawMenu(String chatId, int messageId){
        return BuildTextMessage.buildEdit(chatId, messageId, "Меню",
                new InlineKeyboardMarkup(BuildKeyboard.buildMenu()));
    }

    public SendMessage drawGenerateQuoteWithName(String chatId, String quote){
        return BuildTextMessage.buildSend(chatId, quote, new InlineKeyboardMarkup(BuildKeyboard.buildGenerationQuoteWithName()));
    }

    public EditMessageText drawGenerateQuoteWithName(String chatId, int messageId, String quote){
        return BuildTextMessage.buildEdit(chatId, messageId, quote,
                new InlineKeyboardMarkup(BuildKeyboard.buildGenerationQuoteWithName()));
    }

    public EditMessageText drawGenerateMoreWithName(String chatId, int messageId){
        return BuildTextMessage.buildEdit(chatId, messageId, "Еще?",
                new InlineKeyboardMarkup(BuildKeyboard.buildChoiceGenerationQuote()));
    }

    public EditMessageText drawGenerateQuote(String chatId, int messageId, String quote){
        return BuildTextMessage.buildEdit(chatId, messageId, quote,
                new InlineKeyboardMarkup(BuildKeyboard.buildGenerateQuote()));
    }

    public EditMessageText drawAboutBot(String chatId, int messageId){
        String message = "Бот версии 1.0\nАльфа версия\nАвтор: Владимир Денисенко";
        return BuildTextMessage.buildEdit(chatId, messageId, message,
                new InlineKeyboardMarkup(BuildKeyboard.buildAbout()));
    }

    public EditMessageText drawAddToFavoriteWithName(String chatId, int messageId, String message){
        return BuildTextMessage.buildEdit(chatId, messageId, message,
                new InlineKeyboardMarkup(BuildKeyboard.buildAddFavoriteMessageWithName()));
    }

    public EditMessageText drawAddToFavorite(String chatId, int messageId, String message){
        return BuildTextMessage.buildEdit(chatId, messageId, message,
                new InlineKeyboardMarkup(BuildKeyboard.buildAddFavoriteMessage()));
    }

    public EditMessageText drawMyFavorite(String chatId, int messageId, String message, List<FavoriteQuote> favoriteQuotes){
        return BuildTextMessage.buildEdit(chatId, messageId, message,
                new InlineKeyboardMarkup(BuildKeyboard.buildMyFavorites(favoriteQuotes)));
    }

    public EditMessageText drawEditMyFavoriteQuote(String chatId, int messageId, String quote){
        return BuildTextMessage.buildEdit(chatId, messageId, quote,
                new InlineKeyboardMarkup(BuildKeyboard.buildEditMyFavoriteQuote()));
    }

    public EditMessageText drawDeleteMyFavoriteQuote(String chatId, int messageId, String message){
        return BuildTextMessage.buildEdit(chatId, messageId, message,
                new InlineKeyboardMarkup(BuildKeyboard.buildDeleteFromMyFavoriteQuote()));
    }



}
