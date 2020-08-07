public class Board {
    private int[][] gameGrid;
    int r, c;

    public Board(int row, int col, int player, int[][] x, boolean addPiece) {
        this.gameGrid = new int[6][7];
        for (int r = 0; r < gameGrid.length; r++) {
            for (int c = 0; c < gameGrid[0].length; c++) {
                this.gameGrid[r][c] = x[r][c];
            }
        }
        if (addPiece == true) {
            if (isInGrid(row, col)) gameGrid[row][col] = player;
        }
    }

    public int[][] getBoard() {
        return gameGrid;
    }


    public boolean checkForWinner(int currentPlayer) {
        for (int row = 0; row < gameGrid.length; row++) {
            for (int col = 0; col < gameGrid[0].length; col++) {
                if (row + 3 <= gameGrid.length - 1) {
                    if (gameGrid[row][col] == currentPlayer && gameGrid[row + 1][col] == currentPlayer && gameGrid[row + 2][col] == currentPlayer
                            && gameGrid[row + 3][col] == currentPlayer) {
                        return true;
                    }
                }
                if (col + 3 <= gameGrid[0].length - 1) {
                    if (gameGrid[row][col] == currentPlayer && gameGrid[row][col + 1] == currentPlayer && gameGrid[row][col + 2] == currentPlayer
                            && gameGrid[row][col + 3] == currentPlayer) {
                        return true;
                    }
                }
                if (col - 3 >= 0 && row - 3 >= 0) {
                    if (gameGrid[row][col] == currentPlayer && gameGrid[row - 1][col - 1] == currentPlayer && gameGrid[row - 2][col - 2] == currentPlayer
                            && gameGrid[row - 3][col - 3] == currentPlayer) {
                        return true;
                    }
                }
                if (col + 3 <= gameGrid[0].length - 1 && row - 3 >= 0) {
                    if (gameGrid[row][col] == currentPlayer && gameGrid[row - 1][col + 1] == currentPlayer && gameGrid[row - 2][col + 2] == currentPlayer
                            && gameGrid[row - 3][col + 3] == currentPlayer) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean checkForWinner(int currentPlayer, int[][] g) {
        for (int row = 0; row < g.length; row++) {
            for (int col = 0; col < g[0].length; col++) {
                if (row + 3 <= g.length - 1) {
                    if (g[row][col] == currentPlayer && g[row + 1][col] == currentPlayer && g[row + 2][col] == currentPlayer
                            && g[row + 3][col] == currentPlayer) {
                        return true;
                    }
                }
                if (col + 3 <= g[0].length - 1) {
                    if (g[row][col] == currentPlayer && g[row][col + 1] == currentPlayer && g[row][col + 2] == currentPlayer
                            && g[row][col + 3] == currentPlayer) {
                        return true;
                    }
                }
                if (col - 3 >= 0 && row - 3 >= 0) {
                    if (g[row][col] == currentPlayer && g[row - 1][col - 1] == currentPlayer && g[row - 2][col - 2] == currentPlayer
                            && g[row - 3][col - 3] == currentPlayer) {
                        return true;
                    }
                }
                if (col + 3 <= g[0].length - 1 && row - 3 >= 0) {
                    if (g[row][col] == currentPlayer && g[row - 1][col + 1] == currentPlayer && g[row - 2][col + 2] == currentPlayer
                            && g[row - 3][col + 3] == currentPlayer) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int findLowestRow(int c) {
        for (int r = gameGrid.length - 1; r >= 0; r--) {
            if (gameGrid[r][c] == 0) {
                return r;
            }
        }
        return (-1);
    }


    public boolean isInGrid(int row, int col) {
        if (row < gameGrid.length && row >= 0) {
            if (col < gameGrid[0].length && col >= 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean ThreeInARow(int currentPlayer) {
        for (int row = 0; row < gameGrid.length; row++) {
            for (int col = 0; col < gameGrid[0].length; col++) {
                if (row + 3 <= gameGrid.length - 1) {
                    int numOfOccupiedByCurrentPlayer = 0;
                    int numOfEmpty = 0;
                    for (int i = 0; i < 4; i++) {
                        int x = gameGrid[row + i][col];
                        if (x == currentPlayer) numOfOccupiedByCurrentPlayer++;
                        if (x == 0) numOfEmpty++;
                        else break;
                    }
                    if (numOfEmpty == 1 && numOfOccupiedByCurrentPlayer == 3) return true;
                }
                if (col + 3 <= gameGrid[0].length - 1) {
                    int numOfOccupiedByCurrentPlayer = 0;
                    int numOfEmpty = 0;
                    for (int i = 0; i < 4; i++) {
                        int x = gameGrid[row][col + i];
                        if (x == currentPlayer) numOfOccupiedByCurrentPlayer++;
                        if (x == 0) numOfEmpty++;
                        else break;
                    }
                    if (numOfEmpty == 1 && numOfOccupiedByCurrentPlayer == 3) return true;
                }
                if (col - 3 >= 0 && row - 3 >= 0) {
                    int numOfOccupiedByCurrentPlayer = 0;
                    int numOfEmpty = 0;
                    for (int i = 0; i < 4; i++) {
                        int x = gameGrid[row - i][col - i];
                        if (x == currentPlayer) numOfOccupiedByCurrentPlayer++;
                        if (x == 0) numOfEmpty++;
                        else break;
                    }
                    if (numOfEmpty == 1 && numOfOccupiedByCurrentPlayer == 3) return true;
                }
                if (col + 3 <= gameGrid[0].length - 1 && row - 3 >= 0) {
                    int numOfOccupiedByCurrentPlayer = 0;
                    int numOfEmpty = 0;
                    for (int i = 0; i < 4; i++) {
                        int x = gameGrid[row - i][col + i];
                        if (x == currentPlayer) numOfOccupiedByCurrentPlayer++;
                        if (x == 0) numOfEmpty++;
                        else break;
                    }
                    if (numOfEmpty == 1 && numOfOccupiedByCurrentPlayer == 3) return true;
                }
            }
        }
        return false;
    }
}


