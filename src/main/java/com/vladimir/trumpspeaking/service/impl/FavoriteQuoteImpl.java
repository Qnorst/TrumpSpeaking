package com.vladimir.trumpspeaking.service.impl;

import com.vladimir.trumpspeaking.exception.FavoriteQuoteListNotExist;
import com.vladimir.trumpspeaking.exception.FavoriteQuoteNotExist;
import com.vladimir.trumpspeaking.exception.UserTelegramNotFound;
import com.vladimir.trumpspeaking.models.FavoriteQuote;
import com.vladimir.trumpspeaking.models.UserTelegram;
import com.vladimir.trumpspeaking.repository.FavoriteQuoteRepository;
import com.vladimir.trumpspeaking.repository.UserRepository;
import com.vladimir.trumpspeaking.service.FavoriteQuoteService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FavoriteQuoteImpl implements FavoriteQuoteService {

    @Autowired
    private FavoriteQuoteRepository quoteRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public FavoriteQuote createFavoriteQuote(FavoriteQuote favoriteQuote, Long idUser) throws UserTelegramNotFound {
        userExist(idUser);

        UserTelegram user = userRepository.findUserTelegramById(idUser);
        user.addFavoriteQuoteToUser(quoteRepository.save(favoriteQuote));

        return favoriteQuote;
    }

    @Override
    public void deleteFavoriteQuoteById(int id, Long idUser) throws FavoriteQuoteListNotExist, FavoriteQuoteNotExist, UserTelegramNotFound {
        favoriteQuoteExist(id);
        userExist(idUser);

        FavoriteQuote quote = quoteRepository.findFavoriteQuoteById(id);
        UserTelegram userTelegram = userRepository.findUserTelegramById(idUser);
        userTelegram.removeFavoriteQuoteFromUser(quote);
        quoteRepository.delete(quote);
    }

    @Override
    public List<FavoriteQuote> getAllFavoriteQuotes(Long userId) throws UserTelegramNotFound {
        userExist(userId);
        UserTelegram userTelegram = userRepository.findUserTelegramById(userId);
        return userTelegram.getFavoriteQuotes();
    }

    //Если цитата найдена, вернется true, иначе false
    @Override
    public boolean favoriteQuoteExistUser(Long idUser, String textQuote) {
        return quoteRepository.existsFavoriteQuoteByTextQuoteAndIdUser(textQuote, idUser);
    }

    @Override
    public FavoriteQuote getByTextAndIdUser(String text, Long id) {
        return quoteRepository.findFavoriteQuoteByTextQuoteAndIdUser(text, id);
    }

    @Override
    public FavoriteQuote findById(int id) {
        return quoteRepository.findById(id);
    }

    private void userExist(Long userId) throws UserTelegramNotFound {
        if (!userRepository.existsById(userId))
            throw new UserTelegramNotFound("User not exist");
    }

    private void favoriteQuoteExist(int id) throws FavoriteQuoteNotExist {
        if (!quoteRepository.existsById(id))
            throw new FavoriteQuoteNotExist("Favorite quote not exits");
    }

}
