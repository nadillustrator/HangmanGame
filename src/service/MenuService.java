package service;

import java.util.Scanner;

public class MenuService {
    public static final String PLAY = "1";
    public static final Scanner scanner = new Scanner(System.in);


    public  static  void showStartMenu() {
        System.out.println("Начать новую игру: введите " + PLAY);
        System.out.println("Выход:             введите любое другое значение");
    }

    public static void startGame() {
        boolean isNewGame = true;
        GameService gameService = new GameService();
        while (isNewGame) {
            showStartMenu();
            String choice = scanner.next();
            isNewGame = choice.equals(PLAY);
            if (isNewGame) {
                gameService.playNewGame();
            } else {
                System.out.println("ИГРА ЗАВЕРШЕНА");
                break;
            }
        }
        scanner.close();
    }


}
