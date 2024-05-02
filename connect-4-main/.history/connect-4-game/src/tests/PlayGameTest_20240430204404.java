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
    public void testValidMove() {
        PlayGameRequest request = new PlayGameRequest(1, 1, 3);
        PlayGameResponse response = gameController.PlayGame(request);

        // Assert
        assertTrue(response.isSuccess()); // Check if the response indicates success
        assertEquals(null, response.getErrorMessage()); // Check if the error message is empty
        assertEquals(1, response.getGameId()); // Check if the game ID matches
        assertEquals(1, response.getPlayer1Id()); // Check if player1 ID matches
        assertEquals(2, response.getPlayer2Id()); // Check if player2 ID matches
        assertEquals(2, response.getCurrentTurnPlayer()); // Check if current turn player matches
        assertEquals("Playing", response.getGameStatus()); // Check if the game status is "Playing"
        assertEquals("000000000000000000000000000000000000000000", response.getGameBoard()); // Check if the game board matches
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

        System.out.println("Response isSuccess: " + response.isSuccess());
        System.out.println("Response playerId: " + response.getPlayer1Id());
        System.out.println("Response username: " + response.getPlayer2Id());
        System.out.println("Response errorMessage: " + response.getErrorMessage());

        // Assert
        assertFalse(response.isSuccess()); // Check if the response indicates failure
        assertEquals("Cannot make move.", response.getErrorMessage()); // Check if the error message is "Cannot make move."
        assertEquals(1, response.getGameId()); // Check if the game ID is 1
        assertEquals(1, response.getPlayer1Id()); // Check if player1 ID is 1
        assertEquals(2, response.getPlayer2Id()); // Check if player2 ID is 2
        assertEquals(1, response.getCurrentTurnPlayer()); // Check if current turn player is 1
        assertEquals("Playing", response.getGameStatus()); // Check if the game status is "Playing"
        assertEquals("121212121212121212121212121212121212121212", response.getGameBoard()); // Check if the game board is as expected

    }
}
