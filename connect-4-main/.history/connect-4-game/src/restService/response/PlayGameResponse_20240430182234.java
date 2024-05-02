package restService.response;

import DomainObjects.GameDomainObject;

public class PlayGameResponse {
    private final boolean success;
    private final String errorMessage;
    private final int gameId;
    private final int player1Id;
    private final int player2Id;
    private final int currentTurnPlayer;
    private final int winner;
    private final String gameStatus;
    private final String board;


    public PlayGameResponse(GameDomainObject game) {
        this.success = true;
        this.errorMessage = null;
        this.gameId = game.GetGameId();
        this.player1Id = game.GetPlayerOne().getPlayerId();
        this.player2Id = game.GetPlayerTwo().getPlayerId();
        this.currentTurnPlayer = gam.GetCurrentTurnPlayer().getCurrentTurnPlayer();
        this.winner = game.GetWinner();
        this.gameStatus = game.GetGameStatus();
        this.board = game.GetGameBoard();

    }

    public PlayGameResponse(String errorMessage) {
        this.success = false;
        this.errorMessage = errorMessage;
        this.gameId = -1;
        this.player1Id = -1;
        this.player2Id = -1;
        this.winner = -1;
        this.gameStatus = null;
        this.board = null;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getGameBoard() {
        return this.board;
    }

    public int getGameId() {
        return this.gameId;
    }

    public int getPlayer1Id() {
        return this.player1Id;
    }

    public int getPlayer2Id() {
        return this.player2Id;
    }

    public int getWinner() {
        return this.winner;
    }

    public String getGameStatus() {
        return this.gameStatus;
    }
}
