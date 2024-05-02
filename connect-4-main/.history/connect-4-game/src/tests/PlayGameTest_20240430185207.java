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

        //assertEquals(1, response.getGameId());
        //assertEquals("Playing", response.getGameStatus());
        //assertEquals(-1, response.getWinner());

        // Assert
        assertTrue(response.isSuccess()); // Check if the response indicates success
        assertEquals(null, response.getErrorMessage()); // Check if the error message is empty
        assertEquals(1, response.getGameId()); // Check if the game ID matches
        assertEquals(1, response.getPlayer1Id()); // Check if player1 ID matches
        assertEquals(2, response.getPlayer2Id()); // Check if player2 ID matches
        assertEquals(2, response.getCurrentTurnPlayer()); // Check if current turn player matches
        assertEquals("Playing", response.getGameStatus()); // Check if the game status is "Playing"
        assertEquals("000000000000000000000000000000000000001000", response.getGameBoard()); // Check if the game board matches
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
