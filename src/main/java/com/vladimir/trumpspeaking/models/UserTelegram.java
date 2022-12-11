package com.vladimir.trumpspeaking.models;



import com.vladimir.trumpspeaking.exception.FavoriteQuoteListNotExist;
import com.vladimir.trumpspeaking.exception.FavoriteQuoteNotExist;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "public", name = "users_telegram")
public class UserTelegram {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "find_name")
    private String findName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private List<FavoriteQuote> favoriteQuotes;

    public void addFavoriteQuoteToUser(FavoriteQuote favoriteQuote){
        if (favoriteQuotes==null)
            favoriteQuotes = new ArrayList<>();

        favoriteQuotes.add(favoriteQuote);
    }

    public void removeFavoriteQuoteFromUser(FavoriteQuote favoriteQuote) throws FavoriteQuoteListNotExist, FavoriteQuoteNotExist {
        if (favoriteQuotes==null){
            favoriteQuotes = new ArrayList<>();
            throw new FavoriteQuoteListNotExist("FavoriteQuotes is empty");
        }

        favoriteQuotes.remove(favoriteQuote);
    }

    public UserTelegram() {
    }

    public UserTelegram(Long id, String findName) {
        this.id = id;
        this.findName = findName;
    }

    public Long getId() {
        return id;
    }

    public String getFindName() {
        return findName;
    }

    public List<FavoriteQuote> getFavoriteQuotes() {
        return favoriteQuotes;
    }

    public void setFindName(String findName) {
        this.findName = findName;
    }

    @Override
    public String toString() {
        return "UserTelegram{" +
                "id=" + id +
                ", findName='" + findName + '\'' +
                ", favoriteQuotes=" + favoriteQuotes.toString() +
                '}';
    }
}
