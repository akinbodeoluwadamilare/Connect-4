package Models;

import java.util.ArrayList;

import DataAccess.PlayerDataAccess;
import DataObject.PlayerDataObject;
import DomainObjects.PlayerDomainObject;
import java.util.regex.Pattern;

public class PlayerModel {
    public static ArrayList<PlayerDomainObject> GetAllPlayers() {
        ArrayList<PlayerDataObject> playerList = PlayerDataAccess.GetAllPlayers();
        ArrayList<PlayerDomainObject> playersToReturn = new ArrayList<PlayerDomainObject>();
        for (PlayerDataObject player: playerList) {
            playersToReturn.add(new PlayerDomainObject(player));
        }
        return playersToReturn;
    }

    public static PlayerDomainObject RegisterPlayer(PlayerDomainObject player) throws Exception {
        validate(player);
        PlayerDataObject playerToAdd = new PlayerDataObject(player);
        PlayerDataAccess.AddPlayer(playerToAdd);
        return new PlayerDomainObject(playerToAdd);
    }

    public static PlayerDomainObject GetPlayerById(int playerId) throws Exception {
        PlayerDataObject playerToRetrieve = PlayerDataAccess.GetPlayerById(playerId);
        String error = String.format("Player Id %d does not exist", playerId);
        if (playerToRetrieve == null) {
            throw new Exception(error);
        }
        return new PlayerDomainObject(playerToRetrieve);
    }

    private static void validate(PlayerDomainObject playerDomainObject) throws Exception {
        String pUsername =  playerDomainObject.getUsername();
        String pPassword = playerDomainObject.getPassword();

        if (pUsername.length() < 6 || pUsername.length() > 20) {
            throw new Exception("Invalid Username");
        }

        if (pPassword.length() < 6 || pPassword.length() > 20) {
            throw new Exception("Invalid Password");
        }

        if (!Pattern.matches("^[a-zA-Z0-9]+", pPassword)) {
            throw new Exception("Invalid Password");
        }

        if (!Pattern.matches("^[a-zA-Z0-9]+", pUsername)) {
            throw new Exception("Invalid Username");
        }

    }
}
