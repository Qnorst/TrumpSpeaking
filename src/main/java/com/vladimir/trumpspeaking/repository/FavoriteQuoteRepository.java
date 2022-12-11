package com.vladimir.trumpspeaking.repository;

import com.vladimir.trumpspeaking.models.FavoriteQuote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteQuoteRepository extends JpaRepository<FavoriteQuote, Integer> {
    FavoriteQuote findFavoriteQuoteById(int id);

    boolean existsFavoriteQuoteByTextQuoteAndIdUser(String text, Long id);

    FavoriteQuote findFavoriteQuoteByTextQuoteAndIdUser(String text, Long id);

    FavoriteQuote findById(int id);
}
