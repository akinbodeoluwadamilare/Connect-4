package DomainObjects;

import DataObject.PlayerDataObject;

public class PlayerDomainObject {
    private int playerId;
    private String username;
    private String password;

    public PlayerDomainObject(int id, String username, String password) {
        this.playerId = id;
        this.username = username;
        this.password = password;
    }

    public PlayerDomainObject(PlayerDataObject pObject) {
        this.playerId = pObject.playerId;
        this.username = pObject.username;
        this.password = pObject.password;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }
}
