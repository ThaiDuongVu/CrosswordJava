import java.util.Arrays;

public class Crossword {
    private int boardSize = 20;
    private String[] words = {"hello", "world"};
    private String blank = " ";

    private String[][] board = new String[boardSize][boardSize];

    private String[] placedWords = new String[words.length];
    private String[] unplacedWords = new String[words.length];

    public static void main(String[] args) {
        new Crossword().makeCrossword();
    }

    public void makeCrossword() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = blank;
            }
        }
        printBoard();
    }

    private void printBoard() {
        for (int y = -2; y < boardSize + 2; y++) {
            for (int x = -1; x < boardSize + 1; x++) {
                if (y == -2 || y == boardSize + 1) {
                    if (x == -1 || x == boardSize) {
                        System.out.print(" ");
                    }
                    else {
                        System.out.print(x % 10);
                    }
                }
                else if (y == -1 || y == boardSize) {
                    if (x == -1 || x == boardSize) {
                        System.out.print(" ");
                    }
                    else {
                        System.out.print("_");
                    }
                }
                else {
                    if (x == -1) {
                        System.out.print("|");
                    }
                    else if (x == boardSize) {
                        System.out.print("|" + y);
                    }
                    else {
                        System.out.print(board[x][y]);
                    }
                }
            }
            System.out.println("");
        }
    }
}