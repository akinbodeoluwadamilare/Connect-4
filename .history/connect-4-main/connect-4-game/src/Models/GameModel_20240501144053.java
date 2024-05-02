package Models;

import DataAccess.GameDataAccess;
import DataObject.GameDataObject;
import DomainObjects.GameDomainObject;

public class GameModel {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private String[] gameBoard = new String[ROWS * COLS];

    public static GameDomainObject AddGame(GameDomainObject game) {
        GameDataObject gameToAdd = new GameDataObject(game);
        GameDataAccess.AddGame(gameToAdd);
        return new GameDomainObject(gameToAdd);
    }

    public static GameDomainObject GetGameByGameId(int gameId) throws Exception {
        GameDataObject gameRetrieved = GameDataAccess.GetGameByGameId(gameId);
        if (gameRetrieved == null) {
            throw new Exception("Invalid GameId");
        }
        return new GameDomainObject(gameRetrieved);
    }

    public GameDomainObject makeMove(int gameId, int playerId, int column) throws Exception {
        GameDataObject game = GameDataAccess.GetGameByGameId(gameId);
        int currentTurnPlayer = game.GetCurrentTurnPlayer();
        validate(column, playerId, currentTurnPlayer);
        stringToBoard(game.GetGameBoard());
        if (makeMove(column, String.valueOf(playerId))) {
            game.setWinner(playerId);
            game.setGameStatus("Completed");
        }
        setCurrentTurnPlayer(currentTurnPlayer, game);
        game.setGameBoard(boardToString());
        return new GameDomainObject(game);
    }

    public boolean makeMove(int column, String playerToken) throws Exception {
        for (int row = ROWS - 1; row >= 0; row--) {
            int index = row * COLS + column;
            if (gameBoard[index].equals("0")) {
                gameBoard[index] = playerToken;
                return checkWinner(row, column, playerToken);
            }
        }
        throw new Exception("Column is full");
    }

    private void validate(int column, int playerId, int currentTurnPlayer) throws Exception {
        if (column < 0 || column >= COLS) throw new Exception("Invalid column");
        if (playerId != currentTurnPlayer) throw new Exception("Cannot make move");

    }
    private boolean checkWinner(int row, int column, String playerToken) {
        String pattern = playerToken.repeat(4);  // The winning pattern, e.g., "XXXX" or "OOOO"

        // Check all directions
        String[] directions = new String[] {
            getLine(row, column, 0, 1),   // Horizontal
            getLine(row, column, 1, 0),   // Vertical
            getLine(row, column, 1, 1),   // Diagonal /
            getLine(row, column, 1, -1)   // Diagonal \
        };

        for (String line : directions) {
            if (line.contains(pattern)) {
                return true;
            }
        }
        return false;
    }

    private String getLine(int startRow, int startCol, int deltaRow, int deltaCol) {
        StringBuilder line = new StringBuilder();
        int r = startRow;
        int c = startCol;
        while (r >= 0 && r < ROWS && c >= 0 && c < COLS) {
            line.append(gameBoard[r * COLS + c]);
            r += deltaRow;
            c += deltaCol;
        }
        return line.toString();
    }

    public String boardToString() {
        return String.join("", gameBoard);
    }

    // Loads the board from a single string
    public void stringToBoard(String boardString) {
        gameBoard = boardString.split("");
    }

    public static void setCurrentTurnPlayer(int currentTurnPlayer, GameDataObject game) {
        int player1Id = game.GetPlayerOne().getPlayerId();
        int player2Id = game.GetPlayerTwo().getPlayerId();
        if (currentTurnPlayer == player1Id){
            currentTurnPlayer = player2Id;
        } else {
            currentTurnPlayer = player1Id;
        }
        game.setCurrentTurnPlayer(currentTurnPlayer);
    }
}
