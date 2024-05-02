package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import Controllers.GameController;
import DomainObjects.GameDomainObject;
import DomainObjects.PlayerDomainObject;
import Models.GameModel;
import restService.request.PlayGameRequest;
import restService.response.PlayGameResponse;

public class GameControllerTest {

    private GameController gameController;

    @Before
    public void setUp() {
        gameController = new GameController();
        gameController.setGameModel(new StubbedGameModel());
    }

    public class StubbedGameModel extends GameModel {
        @Override
        public PlayGameResponse makeMove(int gameId, int playerId, int column) {
            // Return a predefined response for testing purposes
            return new PlayGameResponse(new GameDomainObject(gameId, new PlayerDomainObject(1, "Player1", "password1"),
                    new PlayerDomainObject(2, "Player2", "password2")), true);
        }
    }

    @Test
    public void testValidMove() {
        // Arrange
        PlayGameRequest request = new PlayGameRequest(1, 2, 4); // Prepare the request

        // Act
        PlayGameResponse response = gameController.PlayGame(request); // Execute the method under test

        // Assert
        assertTrue(response.isSuccess()); // Check if the response indicates success
        assertEquals("", response.getErrorMessage()); // Check if the error message is empty
        assertEquals(1, response.getGameId()); // Check if the game ID matches
        assertEquals(1, response.getPlayer1Id()); // Check if player1 ID matches
        assertEquals(2, response.getPlayer2Id()); // Check if player2 ID matches
        assertEquals(1, response.getCurrentTurnPlayer()); // Check if current turn player matches
        assertEquals("Playing", response.getGameStatus()); // Check if the game status is "Playing"
        assertEquals("0000000000000000000000000", response.getGameBoard()); // Check if the game board matches
    }
}
