package SundayCCWeek23_BattleshipSpringApp.SundayCCWeek23_BattleshipSpringApp;

import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class BattleshipService {
    private final Random random = new Random();

    public void setupComputerShips(BattleshipGameModel game) {
        int[] sizes = {5, 4, 3, 3, 2};
        for (int size : sizes) {
            boolean placed = false;
            while (!placed) {
                int r = random.nextInt(10), c = random.nextInt(10);
                boolean horizontal = random.nextBoolean();
                if (canPlace(game.computerBoard, r, c, size, horizontal)) {
                    place(game.computerBoard, r, c, size, horizontal);
                    placed = true;
                }
            }
        }
    }

    public boolean canPlace(char[][] board, int r, int c, int size, boolean h) {
        if (h && c + size > 10) return false;
        if (!h && r + size > 10) return false;
        for (int i = 0; i < size; i++) {
            if (h && board[r][c + i] != '~') return false;
            if (!h && board[r + i][c] != '~') return false;
        }
        return true;
    }

    public void place(char[][] board, int r, int c, int size, boolean h) {
        for (int i = 0; i < size; i++) {
            if (h) board[r][c + i] = 'S';
            else board[r + i][c] = 'S';
        }
    }

    public void computerTurn(BattleshipGameModel game) {
        while (true) {
            int r = random.nextInt(10), c = random.nextInt(10);
            if (game.playerBoard[r][c] != 'H' && game.playerBoard[r][c] != 'M') {
                if (game.playerBoard[r][c] == 'S') game.playerBoard[r][c] = 'H';
                else game.playerBoard[r][c] = 'M';
                break;
            }
        }
    }

    public boolean checkWin(char[][] board) {
        for (char[] row : board) for (char cell : row) if (cell == 'S') return false;
        return true;
    }
}