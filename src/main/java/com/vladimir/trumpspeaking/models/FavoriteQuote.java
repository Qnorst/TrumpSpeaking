package com.vladimir.trumpspeaking.models;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "favorite_quotes")
public class FavoriteQuote {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title_quote")
    private String titleQuote;

    @Column(name = "text_quote")
    private String textQuote;

    @Column(name = "id_user")
    private Long idUser;

    public FavoriteQuote() {
    }

    public FavoriteQuote(String titleQuote, String textQuote, Long idUser) {
        this.titleQuote = titleQuote;
        this.textQuote = textQuote;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public String getTitleQuote() {
        return titleQuote;
    }

    public String getTextQuote() {
        return textQuote;
    }

    @Override
    public String toString() {
        return "FavoriteQuote{" +
                "id=" + id +
                ", titleQuote='" + titleQuote + '\'' +
                ", textQuote='" + textQuote + '\'' +
                '}';
    }
}
