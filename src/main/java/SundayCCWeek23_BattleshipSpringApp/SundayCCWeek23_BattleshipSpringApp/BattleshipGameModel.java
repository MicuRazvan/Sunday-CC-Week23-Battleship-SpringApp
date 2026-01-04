package SundayCCWeek23_BattleshipSpringApp.SundayCCWeek23_BattleshipSpringApp;

import java.util.ArrayList;
import java.util.List;

public class BattleshipGameModel {
    public char[][] playerBoard = new char[10][10];
    public char[][] computerBoard = new char[10][10];
    public boolean gameStarted = false;
    public boolean placementComplete = false;
    public String status = "Place your ships!";
    public List<String> shipsToPlace = new ArrayList<>(List.of("Carrier-5", "Battleship-4", "Destroyer-3", "Submarine-3", "PatrolBoat-2"));

    public BattleshipGameModel() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                playerBoard[i][j] = '~';
                computerBoard[i][j] = '~';
            }
        }
    }
}