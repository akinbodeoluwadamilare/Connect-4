package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import Controllers.GameController;
import Models.GameModel;
import restService.request.PlayGameRequest;
import restService.response.PlayGameResponse;

public class GameControllerTest {

    private GameController gameController;

    @Before
    public void setUp() {
        gameController = new GameController();
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
        PlayGameResponse response = gameController.PlayGame(request);

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
}
