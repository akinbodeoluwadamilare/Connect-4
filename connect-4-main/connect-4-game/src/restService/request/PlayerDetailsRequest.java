package restService.request;

public class PlayerDetailsRequest {
    private final int playerId;

    public PlayerDetailsRequest(int playerId) {
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return this.playerId;
    }
}
