package com.vladimir.trumpspeaking.telegram;

import com.vladimir.trumpspeaking.api.TrumpAPI;
import com.vladimir.trumpspeaking.api.models.Quote;
import com.vladimir.trumpspeaking.api.tools.ProcessingStr;
import com.vladimir.trumpspeaking.buildMenu.Keyboard;
import com.vladimir.trumpspeaking.buildMenu.tools.ButtonId;
import com.vladimir.trumpspeaking.config.ConfigApp;
import com.vladimir.trumpspeaking.exception.FavoriteQuoteListNotExist;
import com.vladimir.trumpspeaking.exception.FavoriteQuoteNotExist;
import com.vladimir.trumpspeaking.exception.UserTelegramNotFound;
import com.vladimir.trumpspeaking.models.FavoriteQuote;
import com.vladimir.trumpspeaking.models.UserTelegram;
import com.vladimir.trumpspeaking.service.FavoriteQuoteService;
import com.vladimir.trumpspeaking.service.UserService;
import com.vladimir.trumpspeaking.tools.Helper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.List;


@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    private ConfigApp configApp;
    @Autowired
    private UserService userService;
    @Autowired
    private FavoriteQuoteService quoteService;
    @Autowired
    private TrumpAPI trumpAPI;

    @Override
    public String getBotUsername() {
        return configApp.getNameBot();
    }

    @Override
    public String getBotToken() {
        return configApp.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().getText().startsWith("/"))
            processingTextCommand(update);

        else if (update.hasCallbackQuery())
            processingClickButton(update.getCallbackQuery());
    }

    private void processingTextCommand(Update update) {
        Keyboard keyboard = new Keyboard();
        String command = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        String chatIdStr = String.valueOf(chatId);

        if (command.equals("/start")) {
            userService.registration(chatId);
            sendMessage(keyboard.drawMenu(chatIdStr));
        } else if (command.startsWith("/n")) {
            String name = command.substring(command.indexOf(" ") + 1).trim();
            try {
                userService.setFindName(chatId, name);
                Quote findQuote = trumpAPI.getRandomQuoteWithName(name);
                sendMessage(keyboard.drawGenerateQuoteWithName(chatIdStr, findQuote.getText()));

            } catch (UserTelegramNotFound userTelegramNotFound) {
                sendText(chatIdStr, userTelegramNotFound.getMessage());
            } catch (IOException | InterruptedException e) {
                sendText(chatIdStr, "Произошла ошибка, возможно сервис не отвечает");
            }
        }
    }

    private void processingClickButton(CallbackQuery callbackQuery) {
        Long chatId = callbackQuery.getMessage().getChatId();
        String chatIdStr = String.valueOf(chatId);
        int messageId = callbackQuery.getMessage().getMessageId();

        if (Helper.isNumber(callbackQuery.getData()))
            processingFavoriteQuote(chatIdStr, messageId, Integer.parseInt(callbackQuery.getData()), chatId);

        else
            try {
                processingClickButtonId(chatIdStr, messageId, callbackQuery.getData(), chatId, callbackQuery);
            } catch (UserTelegramNotFound | IOException | InterruptedException | FavoriteQuoteListNotExist |
                     FavoriteQuoteNotExist ex) {
                sendText(chatIdStr, ex.getMessage());
            }
    }

    private void processingFavoriteQuote(String chatId, int messageId, int idQuote, Long idUser){
        Keyboard keyboard = new Keyboard();
        String quote = quoteService.findById(idQuote).getTextQuote();
        sendEditMessage(keyboard.drawEditMyFavoriteQuote(chatId, messageId, quote));
    }

    private void processingClickButtonId(String chatIdStr, int messageId, String buttonIdStr,
                                         Long idUser, CallbackQuery callbackQuery) throws UserTelegramNotFound, IOException, InterruptedException, FavoriteQuoteListNotExist, FavoriteQuoteNotExist {

        ButtonId buttonId = ButtonId.valueOf(buttonIdStr);
        Keyboard keyboard = new Keyboard();
        UserTelegram userTelegram = userService.findById(idUser);
        switch (buttonId) {
            case ABOUT: //Отображение сообщения с информацией "О боте"
                sendEditMessage(keyboard.drawAboutBot(chatIdStr, messageId));
                break;

            case BACK: //Кнопка << Назад, которая возвращает в меню
                sendEditMessage(keyboard.drawMenu(chatIdStr, messageId));
                break;
            //Отвечают за отправку команды для ввода нового имени
            case NEW_NAME:
            case FIND_QUOTE_WITH_NAME:
                sendText(chatIdStr, "Введите имя\n/n name");
                break;
            //Сообщение с выбором: Это же имя или другое?
            case CHOICE_GENERATION_WITH_NAME:
                sendEditMessage(keyboard.drawGenerateMoreWithName(chatIdStr, messageId));
                break;
            //Если было выбрано "с тем же именем", то из базы берется последнее имя, которое пользователь ввел
            case MORE_WITH_NAME:
                Quote quoteWithName = trumpAPI.getRandomQuoteWithName(userTelegram.getFindName());
                sendEditMessage(keyboard.drawGenerateQuoteWithName(chatIdStr, messageId, quoteWithName.getText()));
                break;

            case ADD_FAVORITE:
                String textQuoteWithName = callbackQuery.getMessage().getText();
                if (!quoteService.favoriteQuoteExistUser(idUser, textQuoteWithName)) {
                    FavoriteQuote favoriteQuote = new FavoriteQuote(ProcessingStr.processingTitle(textQuoteWithName),
                            textQuoteWithName, userTelegram.getId());
                    quoteService.createFavoriteQuote(favoriteQuote, idUser);

                    sendEditMessage(keyboard.drawAddToFavoriteWithName(chatIdStr, messageId, "Цитата добавлена"));
                } else
                    sendEditMessage(keyboard.drawAddToFavoriteWithName(chatIdStr, messageId,
                            "Цитата не добавлена из-за того, что она у вас уже есть!"));

                break;

            case ADD_FAVORITE_NO_NAME:
                String textQuote = callbackQuery.getMessage().getText();
                if (!quoteService.favoriteQuoteExistUser(idUser, textQuote)) {
                    FavoriteQuote favoriteQuote = new FavoriteQuote(ProcessingStr.processingTitle(textQuote), textQuote, userTelegram.getId());

                    quoteService.createFavoriteQuote(favoriteQuote, idUser);

                    sendEditMessage(keyboard.drawAddToFavorite(chatIdStr, messageId, "Цитата добавлена"));
                } else
                    sendEditMessage(keyboard.drawAddToFavorite(chatIdStr, messageId,
                            "Цитата не добавлена из-за того, что она у вас уже есть!"));
                break;

            //Поиск цитаты без имени. Если данная цитата уже отображается у пользователя, то сообщение не меняется
            case FIND_QUOTE:
                String text = callbackQuery.getMessage().getText();
                String quote = trumpAPI.getRandomQuote().getText();
                if (!text.equals(quote))
                    sendEditMessage(keyboard.drawGenerateQuote(chatIdStr, messageId, quote));
                break;

            case BACK_MY_FAVORITES:
            case MY_FAVORITE:
                List<FavoriteQuote> favoriteQuotes = quoteService.getAllFavoriteQuotes(userTelegram.getId());
                sendEditMessage(keyboard.drawMyFavorite(chatIdStr, messageId, "Ваши избранные цитаты", favoriteQuotes));
                break;

            case DELETE_MY_QUOTE:
                FavoriteQuote quoteForDelete = quoteService.getByTextAndIdUser(callbackQuery.getMessage().getText(), idUser);
                quoteService.deleteFavoriteQuoteById(quoteForDelete.getId(), idUser);
                sendEditMessage(keyboard.drawDeleteMyFavoriteQuote(chatIdStr, messageId, "Цитата удалена!"));
                break;
        }
    }

    private void sendText(String chatId, String text) {
        try {
            execute(new SendMessage(chatId, text));
        } catch (TelegramApiException telegramApiException) {
            System.out.println(telegramApiException.getMessage());
        }
    }

    private void sendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void sendEditMessage(EditMessageText editMessageText) {
        try {
            execute(editMessageText);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }
}
