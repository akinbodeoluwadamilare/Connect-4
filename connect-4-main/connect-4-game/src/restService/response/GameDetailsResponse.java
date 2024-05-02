package restService.response;

import DomainObjects.GameDomainObject;

public class GameDetailsResponse {
    private final String errorMessage;
    private final int gameId;
    private final int playerOneId;
    private final int playerTwoId;
    private final boolean success;
    private final String gameStatus;
    private final String player1Username;
    private final String player2Username;
    private final int winner;
    private final int currentTurnPlayer;
    private final String board;


    public GameDetailsResponse(GameDomainObject object) {
        this.errorMessage = null;
        this.success = true;
        this.gameId =  object.GetGameId();
        this.playerOneId = object.GetPlayerOne().getPlayerId();
        this.playerTwoId = object.GetPlayerTwo().getPlayerId();
        this.player1Username = object.GetPlayerOne().getUsername();
        this.player2Username = object.GetPlayerTwo().getUsername();
        this.winner = object.GetWinner();
        this.currentTurnPlayer = object.GetCurrentTurnPlayer();
        this.gameStatus = object.GetGameStatus();
        this.board = object.GetGameBoard();
    }

    public GameDetailsResponse(String errMsg) {
        this.errorMessage = errMsg;
        this.gameId = -1;
        this.playerOneId = -1;
        this.playerTwoId = -1;
        this.player1Username = null;
        this.player2Username = null;
        this.gameStatus = null;
        this.winner = -1;
        this.currentTurnPlayer = -1;
        this.success = false;
        this.board = "";
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getPlayer1Username() {
        return this.player1Username;
    }

    public String getPlayer2Username() {
        return this.player2Username;
    }

    public int getPlayerOneId() {
        return this.playerOneId;
    }

    public int getPlayerTwoId() {
        return this.playerTwoId;
    }

    public int getWinner() {
        return this.winner;
    }

    public int getCurrentTurnPlayer() {
        return this.currentTurnPlayer;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getGameStatus() {
        return this.gameStatus;
    }

    public int getGameId() {
        return this.gameId;
    }

    public String getBoard() {
        return this.board;
    }
}
