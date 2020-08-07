import java.util.ArrayList;

public class GamePlayingAI {
    private Connect4 game;

    public GamePlayingAI(Connect4 game) {
        this.game = game;
    }

    public boolean ComputerMove(int player) {
        if (game.getGameOver() == true) {
            return false;
        }
        int c = getBestMove(new Board(game.findLowestRow(3), 3, game.getCp(), game.getGrid(), false), 0, 6, true);
        if (c < 0 || c >= game.getGrid()[0].length) {
            return false;
        }

        if (game.isValidMove(game.findLowestRow(c), c)) {
            game.getGrid()[game.findLowestRow(c)][c] = player;
            game.setCurrentPlayerTurn(game.toggle(game.getCurrentPlayerTurn()));
        } else {
            return false;
        }
        if (Board.checkForWinner(player, game.getGrid())) {
            game.setWinner(player);
            game.setGameOver(true);
        }
        return true;
    }

    public int getBestMove(Board boardstate, int stepdepth, int maxDepth, boolean maximizingplayer) {

        if (stepdepth == maxDepth) {
            return findHeuristics(boardstate) * (maxDepth - stepdepth + 1);
        }
        if (findHeuristics(boardstate) == 100) {
            return findHeuristics(boardstate) * (maxDepth - stepdepth + 1);
        }
        if (findHeuristics(boardstate) == -100) {
            return findHeuristics(boardstate) * (maxDepth - stepdepth + 1);
        }

        if (maximizingplayer) {
            ArrayList<Integer> allValidMoves = getAllValidMoves(boardstate);
            int BestValueSoFar = -101 * (maxDepth - (stepdepth + 1) + 1);
            int BestValueMove = 3;
            Board nextBoardState;
            for (int i = 0; i < allValidMoves.size(); i++) {
                nextBoardState = new Board(boardstate.findLowestRow(allValidMoves.get(i)),
                        allValidMoves.get(i), game.getCp(), boardstate.getBoard(), true);
                int value = getBestMove(nextBoardState, stepdepth + 1, maxDepth, false);
                if (value > BestValueSoFar) {
                    BestValueSoFar = value;
                    BestValueMove = allValidMoves.get(i);
                }
                if (value == BestValueSoFar) {
                    if (Math.abs(allValidMoves.get(i) - game.getNumOfCols() / 2) < Math.abs(BestValueMove - game.getNumOfCols() / 2) && boardstate.findLowestRow(allValidMoves.get(i)) >= game.getNumOfRows() - 4) {
                        BestValueSoFar = value;
                        BestValueMove = allValidMoves.get(i);
                    }
                }
            }

            if (stepdepth == 0) {
                if (game.isValidMove(game.findLowestRow(BestValueMove), BestValueMove)) {
                    return BestValueMove;
                } else {
                    System.out.println("Draw");
                }
            }
            return BestValueSoFar;
        } else {
            ArrayList<Integer> allValidMoves = getAllValidMoves(boardstate);
            int BestValueSoFar = 101 * (maxDepth - (stepdepth + 1) + 1);
            int BestValueMove = 3;
            Board nextBoardState;
            for (int i = 0; i < allValidMoves.size(); i++) {
                nextBoardState = new Board(boardstate.findLowestRow(allValidMoves.get(i)),
                        allValidMoves.get(i), game.getP(), boardstate.getBoard(), true);

                int value = getBestMove(nextBoardState, stepdepth + 1, maxDepth, true);
                if (value < BestValueSoFar) {
                    BestValueSoFar = value;
                    BestValueMove = allValidMoves.get(i);
                }
                if (value == BestValueSoFar) {
                    if (Math.abs(allValidMoves.get(i) - game.getNumOfCols() / 2) < Math.abs(BestValueMove - game.getNumOfCols() / 2) && boardstate.findLowestRow(allValidMoves.get(i)) >= game.getNumOfRows() - 4) {
                        BestValueSoFar = value;
                        BestValueMove = allValidMoves.get(i);
                    }
                }
            }
            if (stepdepth == 0) {
                if (game.isValidMove(game.findLowestRow(BestValueMove), BestValueMove) == true) {
                    return BestValueMove;
                } else {
                    System.out.println("Draw");
                }
            }
            return BestValueSoFar;
        }
    }

    public ArrayList<Integer> getAllValidMoves(Board boardstate) {
        ArrayList<Integer> Moves = new ArrayList<>();
        for (int c = 0; c < game.getGrid()[0].length; c++) {
            int r = boardstate.findLowestRow(c);
            if (r != -1) {
                Moves.add(c);
            }
        }
        return Moves;
    }

    public int findHeuristics(Board boardstate) {
        if (boardstate.checkForWinner(game.getP()) == true) {
            return -100;
        } else if (boardstate.checkForWinner(game.getCp()) == true) {
            return 100;
        } else if (boardstate.ThreeInARow(game.getP()) == true) {
            return -10;
        } else if (boardstate.ThreeInARow(game.getCp()) == true) {
            return 10;
        }
        return 0;
    }

}
