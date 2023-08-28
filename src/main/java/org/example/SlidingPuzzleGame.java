package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

public class SlidingPuzzleApp extends Application {
    private static final int BOARD_SIZE = 15;
    private int[][] board;
    private int emptyX;
    private int emptyY;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initializeBoard();

        Pane pane = new Pane();
        drawBoard(pane);

        Scene scene = new Scene(pane, 300, 300);
        primaryStage.setTitle("Sliding Puzzle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeBoard() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
        int value = 1;

        // Wypełnij planszę wartościami od 1 do BOARD_SIZE^2 - 1
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = value;
                value++;
            }
        }

        // Ustaw ostatnie pole na wartość pustego pola (0)
        emptyX = BOARD_SIZE - 1;
        emptyY = BOARD_SIZE - 1;
        board[emptyX][emptyY] = 0;

        // Przetasuj planszę
        shuffleBoard();
    }

    private void shuffleBoard() {
        Random random = new Random();

        // Wykonaj kilka losowych ruchów na planszy
        for (int i = 0; i < 100; i++) {
            // Wybierz losowy kierunek ruchu: 0 - w górę, 1 - w dół, 2 - w lewo, 3 - w prawo
            int direction = random.nextInt(4);

            // Sprawdź, czy ruch w wybranym kierunku jest możliwy
            if (canMove(direction)) {
                // Przesuń puste pole w wybranym kierunku
                moveEmptyTile(direction);
            }
        }
    }

    private void moveEmptyTile(int direction) {
        int newEmptyX = emptyX;
        int newEmptyY = emptyY;

        // Przesunięcie pustego pola w odpowiednim kierunku
        if (direction == 0 && emptyY > 0) { // Góra
            newEmptyY--;
        } else if (direction == 1 && emptyX < BOARD_SIZE - 1) { // Prawo
            newEmptyX++;
        } else if (direction == 2 && emptyY < BOARD_SIZE - 1) { // Dół
            newEmptyY++;
        } else if (direction == 3 && emptyX > 0) { // Lewo
            newEmptyX--;
        }

        // Zamiana pustego pola z sąsiednim polem
        if (newEmptyX != emptyX || newEmptyY != emptyY) {
            board[emptyX][emptyY] = board[newEmptyX][newEmptyY];
            board[newEmptyX][newEmptyY] = 0;
            emptyX = newEmptyX;
            emptyY = newEmptyY;
        }
    }

    private boolean canMove(int direction) {
        int targetX = emptyX;
        int targetY = emptyY;

        // Określenie potencjalnych współrzędnych docelowe na podstawie kierunku
        if (direction == 0) { // Góra
            targetY--;
        } else if (direction == 1) { // Prawo
            targetX++;
        } else if (direction == 2) { // Dół
            targetY++;
        } else if (direction == 3) { // Lewo
            targetX--;
        }

        // Sprawdzenie, czy nowe współrzędne są wewnątrz planszy
        if (targetX >= 0 && targetX < BOARD_SIZE && targetY >= 0 && targetY < BOARD_SIZE) {
            return true;
        }

        return false;
    }

    private boolean isPuzzleSolved() {
        // Implementacja sprawdzania, czy układka jest rozwiązana
        int expectedValue = 1;

        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (board[x][y] != expectedValue) {
                    return false;
                }
                expectedValue = (expectedValue + 1) % (BOARD_SIZE * BOARD_SIZE);
            }
        }

        return true;
    }

    private void moveTile(int x, int y) {
        // Implementacja przesuwania pól
    }

    private void drawBoard(Pane pane) {
        // Implementacja rysowania planszy
    }
}