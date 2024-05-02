package DataObject;

import DomainObjects.PlayerDomainObject;

public class PlayerDataObject {
    public int playerId;
    public String username;
    public String password;

public PlayerDataObject(int playerId, String username, String password) {
    this.playerId = playerId;
    this.username = username;
    this.password = password;
}

public PlayerDataObject(PlayerDomainObject pDomainObject) {
    this.playerId = pDomainObject.getPlayerId();
    this.username = pDomainObject.getUsername();
    this.password = pDomainObject.getPassword();
}

public PlayerDataObject(PlayerDataObject pDataObject) {
    this.playerId = pDataObject.playerId;
    this.username = pDataObject.username;
    this.password = pDataObject.password;
}

public int getPlayerId() {
    return this.playerId;
}

public String getUsername() {
    return this.username;
}

public String getPassword() {
    return this.password;
}
}