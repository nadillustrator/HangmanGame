package model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private String hiddenWord;
    private String displayWord;
    private final List<String> namedLetters;
    private String picture;
    private Boolean isVictory;


    public Game() {
        this.namedLetters = new ArrayList<>();
        this.picture = Picture.getPictures().get(0);
    }

    public List<String> getNamedLetters() {
        return namedLetters;
    }

    public void setNamedLetters(String letter) {
        this.namedLetters.add(letter);
    }

    public String getDisplayWord() {
        return displayWord;
    }

    public void setDisplayWord(String displayWord) {
        this.displayWord = displayWord;
    }

    public String getHiddenWord() {
        return hiddenWord;
    }

    public void setHiddenWord(String hiddenWord) {
        this.hiddenWord = hiddenWord;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Boolean getVictory() {
        return isVictory;
    }

    public void setVictory(Boolean victory) {
        isVictory = victory;
    }
}
