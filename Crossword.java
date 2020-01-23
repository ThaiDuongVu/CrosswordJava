import java.lang.reflect.Array;
import java.util.ArrayList;

public class Crossword {
    private int boardSize = 20;
    private String[] words = {"hello", "world", "my", "name", "is"};
    private String blank = " ";

    private String[][] board = new String[boardSize][boardSize];

    private ArrayList<String> placedWords = new ArrayList<String>();
    private ArrayList<String> unplacedWords = new ArrayList<String>();

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

    private void addFirstWord(String word) {
        int col = boardSize / 2 - word.length() / 2;
        int row = boardSize / 2;

        for (int x = col; x < word.length() + col; x++) {
            board[x][row] = Character.toString(word.charAt(x - col));
        }
        placedWords.add(word);
    }

    private boolean checkVertical(String word, int row, int col) {
        int blankNumber = 0;

        if (word.length() + row > boardSize) {
            return false;
        }

        if (row < boardSize - word.length()) {
            if (!board[col][row + word.length()].equals(" ")) {
                return false;
            }
        }
        if (row > 0) {
            if (!board[col][row - 1].equals(" ")) {
                return false;
            }
        }

        for (int i = row; i < (word.length() + row); i++) {
            if (i >= boardSize) {
                return false;
            }
            else {
                if (board[col][i].equals(" ")) {
                    if (col == 0) {
                        if (!board[col + 1][i].equals(" ")) {
                            return false;
                        }
                    }
                    if (col == boardSize - 1) {
                        if (!board[col - 1][i].equals(" ")) {
                            return false;
                        }
                    }
                    else {
                        if (!board[col + 1][i].equals(" ") || !board[col - 1][i].equals(" ")) {
                            return false;
                        }
                        else {
                            blankNumber++;
                        }
                    }
                }
                else {
                    if (!board[col][i].equals(Character.toString(word.charAt(i - row)))) {
                        return false;
                    }
                }
            }
        }
        return blankNumber < word.length();
    }

    private boolean addVertical(String word) {
        for (int col = 0; col < boardSize - 1; col++) {
            for (int row = 0; row < boardSize - word.length() + 1; row++) {
                if (checkVertical(word, row, col)) {
                    for (int i = row; i < word.length() + row; i++) {
                        board[col][i] = Character.toString(word.charAt(i - row));
                    }
                    placedWords.add(word);
                    return true;
                }
            }
        }
        unplacedWords.add(word);
        return false;
    }

    private boolean checkHorizontal(String word, int row, int col) {
        int blankNumber = 0;

        if (word.length() + col > boardSize) {
            return false;
        }
        if (col < boardSize - word.length()) {
            if (!board[col + word.length()][row].equals(" ")) {
                return false;
            }
        }
        if (col > 0) {
            if (!board[col - 1][row].equals(" ")) {
                return false;
            }
        }

        for (int i = col; i < word.length() + col; i++) {
            if (i >= boardSize) {
                return false;
            }
            else {
                if (board[i][row].equals(" ")) {
                    if (row == 0) {
                        if (!board[i][row + 1].equals(" ")) {
                            return false;
                        }
                    }
                    if (row == boardSize - 1) {
                        if (!board[i][row - 1].equals(" ")) {
                            return false;
                        }
                    }
                    else {
                        if (!board[i][row + 1].equals(" ") || !board[i][row - 1].equals(" ")) {
                            return false;
                        }
                        else {
                            blankNumber++;
                        }
                    }
                }
                else {
                    if (!board[i][row].equals(Character.toString(word.charAt(i - col)))) {
                        return false;
                    }
                }
            }
        }
        return blankNumber < word.length();
    }

    private boolean addHorizontal(String word) {
        for (int row = 0; row < boardSize - 1; row++) {
            for (int col = 0; col < boardSize - word.length() + 1; col++) {
                if (checkHorizontal(word, row, col)) {
                    for (int i = col; i < word.length() + col; i++) {
                        board[i][row] = Character.toString(word.charAt(i - col));
                    }
                    placedWords.add(word);
                    return true;
                }
            }
        }
        unplacedWords.add(word);
        return false;
    }

    private void addWords() {
        for (int i = 0; i < words.length; i++) {
            if (i == 0) {
                addFirstWord(words[i]);
            }
            if (i != 0 && i % 2 == 0) {
                addHorizontal(words[i]);
            }
            if (i % 2 != 0) {
                addVertical(words[i]);
            }
        }
    }
}