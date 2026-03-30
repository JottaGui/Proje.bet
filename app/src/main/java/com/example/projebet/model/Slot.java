package com.example.projebet.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Slot {

    public static final int MAX = 3;
    public static final int TOTAL_SYMBOLS = 4;

    private final int[][] grid = new int[MAX][MAX];
    private final Random random = new Random();

    public RoundResult play() {
        spin();
        return checkResult();
    }

    private void spin() {
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                grid[i][j] = random.nextInt(TOTAL_SYMBOLS);
            }
        }
    }

    private RoundResult checkResult() {
        List<String> wins = new ArrayList<>();

        if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
            wins.add("Diagonal principal");
        }

        if (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) {
            wins.add("Diagonal secundária");
        }

        for (int i = 0; i < MAX; i++) {
            if (grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) {
                wins.add("Linha " + (i + 1));
            }
        }

        for (int j = 0; j < MAX; j++) {
            if (grid[0][j] == grid[1][j] && grid[1][j] == grid[2][j]) {
                wins.add("Coluna " + (j + 1));
            }
        }

        boolean won = !wins.isEmpty();

        String message;
        if (won) {
            message = "Você ganhou em: " + String.join(", ", wins);
        } else {
            message = "Você não ganhou.";
        }

        return new RoundResult(copyGrid(), won, wins, message);
    }

    private int[][] copyGrid() {
        int[][] copy = new int[MAX][MAX];
        for (int i = 0; i < MAX; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, MAX);
        }
        return copy;
    }

    public static class RoundResult {
        private final int[][] grid;
        private final boolean won;
        private final List<String> wins;
        private final String message;

        public RoundResult(int[][] grid, boolean won, List<String> wins, String message) {
            this.grid = grid;
            this.won = won;
            this.wins = wins;
            this.message = message;
        }

        public int[][] getGrid() {
            return grid;
        }

        public boolean isWon() {
            return won;
        }

        public List<String> getWins() {
            return wins;
        }

        public String getMessage() {
            return message;
        }
    }
}
