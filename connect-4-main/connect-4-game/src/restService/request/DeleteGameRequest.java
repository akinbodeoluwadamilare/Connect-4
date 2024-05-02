package restService.request;

public class DeleteGameRequest {
    private final int gameId;

    public DeleteGameRequest(int gameId) {
        this.gameId = gameId;
    }

    public int getGameId() {
        return this.gameId;
    }
}
