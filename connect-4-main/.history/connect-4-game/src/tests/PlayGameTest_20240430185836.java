package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Controllers.GameController;
import DataAccess.PlayerDataAccess;
import Models.GameModel;
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
        assertEquals("000000000000000000000000000000000000001000", response.getGameBoard()); // Check if the game board matches
    }

    @Test
    public void testInvalidMove_InvalidGameID() {
        // Arrange
        PlayGameRequest request = new PlayGameRequest(999999999, 1, 1); // Prepare the request
        // Mock the behavior of the GameModel
        GameModel gameModelMock = mock(GameModel.class);
        when(gameModelMock.makeMove(anyInt(), anyInt(), anyInt())).thenThrow(new Exception("Invalid Game ID.")); // Simulate exception for invalid game ID
        gameController.setGameModel(gameModelMock); // Set the mock GameModel to the GameController

        // Act
        PlayGameResponse response = gameController.playGame(request); // Execute the method under test

        // Assert
        assertFalse(response.isSuccess()); // Check if the response indicates failure
        assertEquals(1, response.getErrorMessage().size()); // Check if there's one error message
        assertEquals("Invalid Game ID.", response.getErrorMessage().get(0)); // Check if the error message is "Invalid Game ID."
        assertEquals(0, response.getGameId()); // Check if the game ID is 0
        assertEquals(0, response.getPlayer1Id()); // Check if player1 ID is 0
        assertEquals(0, response.getPlayer2Id()); // Check if player2 ID is 0
        assertEquals(0, response.getCurrentTurnPlayer()); // Check if current turn player is 0
        assertEquals(null, response.getGameStatus()); // Check if the game status is null
        assertEquals(null, response.getGameBoard()); // Check if the game board is null
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
