package service;

import model.Game;
import model.Picture;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static service.MenuService.scanner;

public class GameService {

    private static final String PATH = "dictionary\\singular.txt";
    private static List<String> words = new ArrayList<>();

    public GameService() {
        fillInWordList();
    }

    private static void fillInWordList() {
        File file = new File(PATH);
        try (Stream<String> lines = Files.lines(file.toPath(), StandardCharsets.UTF_8)) {
            words = lines
                    .map(String::trim)
                    .filter(s -> s.length() > 5)
                    .collect(Collectors.toList());
            if (words.isEmpty()) {
                throw new IllegalArgumentException("Word list is empty");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void playNewGame() {
        Game game = createNewGame();
        showNewGameMenu(game);
        while (game.getVictory() == null) {
            System.out.println(game.getPicture());
            System.out.print("Загаданное слово: ");
            System.out.println(game.getDisplayWord());
            System.out.print("Введите букву:");
            String letter = scanner.next().toUpperCase();

            if (isInputValueValid(letter, game)) {
                game.setNamedLetters(letter);

                if (containLetter(game, letter)) {
                    setLetterToDisplayWord(game, letter);
                } else {
                    changePicture(game);
                    System.out.println("Буквы \"" + letter + "\" нет в этом слове");
                }
                checkVictory(game);
            }

            System.out.println();

        }
    }

    private void changePicture(Game game) {
        String currentPicture = game.getPicture();
        int currentIndex = Picture.getPictures().indexOf(currentPicture);
        game.setPicture(Picture.getPictures().get(currentIndex + 1));
    }

    private static void checkVictory(Game game) {
        if (game.getHiddenWord().equals(game.getDisplayWord())) {
            System.out.println("Поздравляю! Вы выиграли! Загаданное слово: " + game.getDisplayWord());
            game.setVictory(true);
        }
        if (game.getPicture().equals(Picture.getPictures().get(6))) {
            System.out.println();
            System.out.println(game.getPicture());
            System.out.println("Вы проиграли. Загаданное слово: " + game.getHiddenWord());
            System.out.println();
            game.setVictory(false);
        }
    }

    private Boolean isInputValueValid(String inputValue, Game game) {
        if (isOneSymbol(inputValue)) {
            System.err.println("Нужно ввести один символ");
            return false;
        } else if (isLetterWasNamedBefore(inputValue, game)) {
            System.err.println("Вы уже называли " + inputValue);
            return false;
        } else if (!isLetterCyrillicSymbol(inputValue)) {
            System.err.println("Введите букву из кириллицыыы");
            return false;
        }
        return true;

    }

    private static boolean isOneSymbol(String inputValue) {
        return inputValue.length() > 1;
    }

    private static boolean isLetterWasNamedBefore(String inputValue, Game game) {
        return game.getNamedLetters().contains(inputValue);
    }

    public boolean isLetterCyrillicSymbol(String inputValue) {
        return Character.UnicodeBlock.of(inputValue.charAt(0)).equals(Character.UnicodeBlock.CYRILLIC);
    }

    private void setLetterToDisplayWord(Game game, String letter) {
        StringBuilder sbWord = new StringBuilder();
        sbWord.append(game.getHiddenWord());
        StringBuilder result = new StringBuilder();
        result.append(game.getDisplayWord());
        while (sbWord.indexOf(letter) != -1) {
            int index = sbWord.indexOf(letter);
            result.replace(index, index + 1, letter);
            sbWord.replace(index, index + 1, " ");
        }
        game.setDisplayWord(result.toString());
    }

    private Boolean containLetter(Game game, String letter) {

        return game.getHiddenWord().contains(letter);
    }

    private void showNewGameMenu(Game game) {
        System.out.println();
        System.out.println("НОВАЯ ИГРА");
        System.out.println("Загаданное слово состоит из " + (game.getHiddenWord().length() / 2 + 1) + " букв");
    }

    ;

    private Game createNewGame() {
        Game game = new Game();
        game.setHiddenWord(defineRandomWord());
        game.setDisplayWord(formDisplayWord(game.getHiddenWord()));
        return game;
    }

    private String defineRandomWord() {
        return words.get(ThreadLocalRandom.current().nextInt(words.size())).codePoints()
                .map(Character::toUpperCase)
                .mapToObj(cp -> new String(Character.toChars(cp)))
                .collect(Collectors.joining(" "));
    }

    private String formDisplayWord(String word) {
        return word.replaceAll("\\p{L}", "_");
    }

}
