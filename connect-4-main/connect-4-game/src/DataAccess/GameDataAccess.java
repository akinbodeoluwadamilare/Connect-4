package DataAccess;

import java.util.ArrayList;

import DataObject.GameDataObject;

public class GameDataAccess {
    // private static int getNextId() {
    //     int thisId = nextId;
    //     nextId++;
    //     return thisId; 
    // }
    private static ArrayList<GameDataObject> games = new ArrayList<GameDataObject>();

    public void initialize() {}
    public static GameDataObject AddGame(GameDataObject game) {
        games.add(game);
        return game;
    }

    public static GameDataObject GetGameByGameId(int gameId) {
        for (GameDataObject game: games) {
            if (game.gameId == gameId) {
                return new GameDataObject(game);
            }
        }
        return null;
    }

    public static void DeleteGame(int gameId) {
        for (GameDataObject game : games) {
            if (game.gameId == gameId) {
                games.remove(game);
                break;
            }
        }
    }
}
