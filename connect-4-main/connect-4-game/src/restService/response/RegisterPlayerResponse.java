package restService.response;

import DomainObjects.PlayerDomainObject;

public class RegisterPlayerResponse {

    private final String username;
    private final String errorMessage;
    private final int playerId;
    private final boolean success;

    public RegisterPlayerResponse(PlayerDomainObject pDomainObject) {
        this.username = pDomainObject.getUsername();
        this.playerId = pDomainObject.getPlayerId();
        this.errorMessage = null;
        this.success = true;
    }

    public RegisterPlayerResponse(String errorMessage) {
        this.username = null;
        this.playerId = -1;
        this.errorMessage = errorMessage;
        this.success = false;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getUsername() {
        return this.username;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
