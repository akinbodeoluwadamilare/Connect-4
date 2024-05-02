package DomainObjects;

import DataObject.GameDataObject;

public class GameDomainObject {
    private int gameId;
    private PlayerDomainObject player1;
    private PlayerDomainObject player2;
    private int currentTurnPlayer;
    private int winner;
    private String status;
    // private BoardDomainObject gameBoard;
    private final String TOKEN = "0";
    private String gameBoard;

    public GameDomainObject(int gameId, PlayerDomainObject player1, PlayerDomainObject player2) {
        this.gameId = gameId;
        this.player1 = player1;
        this.player2 = player2;
        this.currentTurnPlayer = player1.getPlayerId(); //player1 will always start
        this.status = "Playing";
        this.gameBoard = TOKEN.repeat(42);
        this.winner = -1;
    }

    public GameDomainObject(GameDomainObject gameDomainObject) { //copy constructor
        this.gameId = gameDomainObject.gameId;
        this.player1 = gameDomainObject.player1;
        this.player2 = gameDomainObject.player2;
        this.gameBoard = gameDomainObject.gameBoard;
        this.currentTurnPlayer = gameDomainObject.currentTurnPlayer;
        this.status = gameDomainObject.status;
        this.winner = gameDomainObject.winner;
    }

    public GameDomainObject(GameDataObject gDataObject) {
        this.player1 = new PlayerDomainObject(gDataObject.player1);
        this.player2 = new PlayerDomainObject(gDataObject.player2);
        this.gameId = gDataObject.GetGameId();
        this.winner = gDataObject.GetWinner();
        this.currentTurnPlayer = gDataObject.GetCurrentTurnPlayer();
        this.status = gDataObject.GetGameStatus();
        this.gameBoard = gDataObject.GetGameBoard();
    }

    public PlayerDomainObject GetPlayerOne() {
        return this.player1;
    }

    public PlayerDomainObject GetPlayerTwo() {
        return this.player2;
    }

    public int GetCurrentTurnPlayer() {
        return this.currentTurnPlayer;
    }

    public void setCurrentTurnPlayer(int playerToSet) {
        this.currentTurnPlayer = playerToSet; 
    }

    public String GetGameBoard() {
        return this.gameBoard;
    }

    public void setGameBoard(String boardToSet) {
        this.gameBoard = boardToSet;
    }

    public int GetWinner() {
        return this.winner;
    }

    public void setWinner(int winnerId) {
        this.winner = winnerId;
    }

    public String GetGameStatus() {
        return this.status;
    }

    public void setGameStatus(String status) {
        this.status = status;
    }

    public int GetGameId() {
        return this.gameId;
    }
}
