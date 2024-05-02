package restService.request;

public class GameDetailsRequest {
    private final int gameId;

    public GameDetailsRequest(int gameId) {
        this.gameId = gameId;

    }
    public int getGameId() {
        return gameId;
    }
    
}
