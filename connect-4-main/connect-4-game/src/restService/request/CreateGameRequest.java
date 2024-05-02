package restService.request;

public class CreateGameRequest {
    private final int gameId;
    private final int player1Id;
    private final int player2Id;

    public CreateGameRequest(int gameId, int player1Id, int player2Id) {
        this.gameId = gameId;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
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
}
