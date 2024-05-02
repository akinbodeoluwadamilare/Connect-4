package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Controllers.GameController;
import DataAccess.PlayerDataAccess;
import restService.request.CreateGameRequest;
import restService.request.PlayGameRequest;
import restService.response.PlayGameResponse;

public class PlayGameTest {
    
    private GameController gameController;

    //private PlayGame playGame;
    //private GameController gameController;

    @Before
    public void setUp() {
        PlayerDataAccess iniAccess = new PlayerDataAccess();
        CreateGameRequest request = new CreateGameRequest(1, 1, 2);
        gameController = new GameController();
        gameController.CreateGame(request);
    }

    //playGame = new PlayGame();
    //gameController new GameController();

    @Test
    public void testSingleMove() {
        PlayGameRequest request = new PlayGameRequest(1, 1, 3);
        PlayGameResponse response = gameController.PlayGame(request);

        assertEquals(1, response.getGameId());
        assertEquals("Playing", response.getGameStatus());
        assertEquals(-1, response.getWinner());
    }

    @Test
    public void testValidMove() {
        PlayGameRequest request = new PlayGameRequest(1, 2, 4);
        PlayGameResponse response = gameController.PlayGame(request);

        //PlayGame.PlayGameResponse response = playGame.playGame(1, 2, 4);

        assertTrue(response.isSuccess());
        assertTrue(response.getErrorMessage().isEmpty());
        assertEquals(1, response.getGameId());

        PlayGameRequest request = new PlayGameRequest(1, 2, 4);
        PlayGameResponse response = gameController.PlayGame(request);



        PlayGame.Player expectedPlayer1 = new PlayGame.Player(1, "DammyLare1", "Yellow");
        PlayGame.Player expectedPlayer2 = new PlayGame.Player(2, "JosephA", "Red");
        assertEquals(expectedPlayer1.getPlayerId(), response.getPlayer1().getPlayerId());
        assertEquals(expectedPlayer1.getUsername(), response.getPlayer1().getUsername());
        assertEquals(expectedPlayer1.getColor(), response.getPlayer1().getColor());

        assertEquals(expectedPlayer2.getPlayerId(), response.getPlayer2().getPlayerId());
        assertEquals(expectedPlayer2.getUsername(), response.getPlayer2().getUsername());
        assertEquals(expectedPlayer2.getColor(), response.getPlayer2().getColor());

        assertEquals(2, response.getCurrentTurnPlayer());
        assertEquals("Playing", response.getGameStatus());
        assertEquals("0000000000000000000000100", response.getBoard());
    }

    @Test
    public void testWinningMove() {
        // Setup - playing moves that lead up to a win
        gameController.PlayGame(new PlayGameRequest(1, 1, 0));
        gameController.PlayGame(new PlayGameRequest(1, 2, 1));
        gameController.PlayGame(new PlayGameRequest(1, 1, 0));
        gameController.PlayGame(new PlayGameRequest(1, 2, 1));
        gameController.PlayGame(new PlayGameRequest(1, 1, 0));
        PlayGameResponse response = gameController.PlayGame(new PlayGameRequest(1, 1, 0));  // Winning move
        
        System.out.println("Response gameId: " + response.getGameId());

        // Verify the response
        assertEquals(1, response.getGameId());
    }

    @Test
    public void testFullColumn() {
        // Filling a column
        gameController.PlayGame(new PlayGameRequest(1, 1, 0));
        gameController.PlayGame(new PlayGameRequest(1, 2, 0));
        gameController.PlayGame(new PlayGameRequest(1, 1, 0));
        gameController.PlayGame(new PlayGameRequest(1, 2, 0));
        gameController.PlayGame(new PlayGameRequest(1, 1, 0));
        gameController.PlayGame(new PlayGameRequest(1, 2, 0));
        
        // Attempt to place another disc in the full column
        PlayGameResponse response = gameController.PlayGame(new PlayGameRequest(1, 1, 0));

    }
}
