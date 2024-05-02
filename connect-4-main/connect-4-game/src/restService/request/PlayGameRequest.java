package restService.request;

public class PlayGameRequest {
    private final int gameId;
    private final int playerId;
    private final int column;

    public PlayGameRequest(int gameId, int playerId, int column) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.column = column;
    }

    public int getGameId() {
        return this.gameId;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public int getColumn() {
        return this.column;
    }
}
