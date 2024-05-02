package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import Controllers.GameController;
import DataAccess.PlayerDataAccess;
import Models.GameModel;
import restService.request.CreateGameRequest;
import restService.request.PlayGameRequest;
import restService.response.PlayGameResponse;

public class GameControllerTest {

    private GameController gameController;

    @Before
    public void setUp() {
        // Mock the necessary dependencies
        PlayerDataAccess iniAccess = new PlayerDataAccess();
        CreateGameRequest request = new CreateGameRequest(1, 1, 2);
        gameController = new GameController();
        gameController.CreateGame(request);
    }

    @Test
    public void testInvalidMove_InvalidGameID() {
        // Arrange
        PlayGameRequest request = new PlayGameRequest(999999999, 1, 1); // Prepare the request

        // Act
        PlayGameResponse response = gameController.PlayGame(request);

        System.out.println("Response isSuccess: " + response.isSuccess());
        // System.out.println("Response playerId: " + response.getPlayerId());
        // .out.println("Response username: " + response.getUsername());
        System.out.println("Response errorMessage: " + response.getErrorMessage());

        // Assert
        assertFalse(response.isSuccess()); // Check if the response indicates failure
        assertEquals("Invalid GameId.", response.getErrorMessage()); // Check if the error message is "Invalid Game ID."
        assertEquals(0, response.getGameId()); // Check if the game ID is 0
        assertEquals(0, response.getPlayer1Id()); // Check if player1 ID is 0
        assertEquals(0, response.getPlayer2Id()); // Check if player2 ID is 0
        assertEquals(0, response.getCurrentTurnPlayer()); // Check if current turn player is 0
        assertEquals(null, response.getGameStatus()); // Check if the game status is null
        assertEquals(null, response.getGameBoard()); // Check if the game board is null
    }
}


