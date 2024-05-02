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

    @Before
    public void setUp() {
        PlayerDataAccess iniAccess = new PlayerDataAccess();
        CreateGameRequest request = new CreateGameRequest(1, 1, 2);
        gameController = new GameController();
        gameController.CreateGame(request);
    }

    @Test
    public void testSingleMove() {
        PlayGameRequest request = new PlayGameRequest(1, 1, 3);
        PlayGameResponse response = gameController.PlayGame(request);

        assertEquals(1, response.getGameId());
        assertEquals("Playing", response.getGameStatus());
        assertEquals(-1, response.getWinner());
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
        assertFalse(response.isSuccess());
        assertEquals(-1, response.getPlayerId());
        assertNull(response.getUsername());
        assertEquals("Username already exists", response.getErrorMessage());


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
