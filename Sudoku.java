public class Sudoku {
    final static int SIZE = 9;

    public static void main(String[] args) {
        int[][] grid = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        if (solve(grid)) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    System.out.print(grid[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No solution exists");
        }
    }

    public static boolean isValid(int[][] board, int num, int row, int col) {
        for (int i = 0; i < SIZE; i++) {
            // Column safety
            if (board[row][i] == num) {
                return false;
            }

            // Row safety
            if (board[i][col] == num) {
                return false;
            }
        }

        // 3x3 box safety
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean solve(int[][] grid) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (grid[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(grid, num, row, col)) {
                            grid[row][col] = num;
                            if (solve(grid)) {
                                return true;
                            }
                            grid[row][col] = 0; // Backtrack
                        }
                    }
                    return false; // Trigger backtracking
                }
            }
        }
        return true; // Solved
    }
}
