package com.vladimir.trumpspeaking.buildMenu.tools;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class BuildTextMessage {
    public static SendMessage buildSend(String chatId, String text, InlineKeyboardMarkup markup){
        SendMessage sendMessage = new SendMessage(chatId, text);
        sendMessage.setReplyMarkup(markup);

        return sendMessage;
    }

    public static EditMessageText buildEdit(String chatId, int messageId, String text, InlineKeyboardMarkup markup){
        EditMessageText editMessageText = new EditMessageText(text);
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(messageId);
        editMessageText.setReplyMarkup(markup);

        return editMessageText;
    }
}
