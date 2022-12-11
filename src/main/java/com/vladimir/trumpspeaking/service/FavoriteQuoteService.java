package com.vladimir.trumpspeaking.service;



import com.vladimir.trumpspeaking.exception.FavoriteQuoteListNotExist;
import com.vladimir.trumpspeaking.exception.FavoriteQuoteNotExist;
import com.vladimir.trumpspeaking.exception.UserTelegramNotFound;
import com.vladimir.trumpspeaking.models.FavoriteQuote;

import java.util.List;

public interface FavoriteQuoteService {
    FavoriteQuote createFavoriteQuote(FavoriteQuote favoriteQuote, Long userId) throws UserTelegramNotFound;
    void deleteFavoriteQuoteById(int id, Long idUser) throws FavoriteQuoteListNotExist, FavoriteQuoteNotExist, UserTelegramNotFound;
    List<FavoriteQuote> getAllFavoriteQuotes(Long userId) throws UserTelegramNotFound;
    boolean favoriteQuoteExistUser(Long idUser, String textQuote);

    FavoriteQuote getByTextAndIdUser(String text, Long id);

    FavoriteQuote findById(int id);
}
