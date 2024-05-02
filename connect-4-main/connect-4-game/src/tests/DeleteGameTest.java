package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

import Controllers.GameController;
import DataAccess.PlayerDataAccess;
import restService.request.CreateGameRequest;
import restService.request.DeleteGameRequest;
import restService.response.CreateGameResponse;
import restService.response.DeleteGameResponse;

public class DeleteGameTest {

    @Before
    public void setUp() {
       
        // Mocking some games for testing
        PlayerDataAccess initAccess = new PlayerDataAccess();
        int gameId = 1;
        int player1id = 1;
        int player2id = 2;

        CreateGameRequest request = new CreateGameRequest(gameId, player1id, player2id);
        GameController controller = new GameController();
        controller.CreateGame(request);
    }
    
    @Test
    public void testValidGameDeletion() {
        // Set up default data if needed
        
        // Create a GameController instance
        GameController gameController = new GameController();
        
        // Create a DeleteGameRequest with the gameId specified in the scenario
        int gameId = 1; // gameId: 1
        DeleteGameRequest request = new DeleteGameRequest(gameId);
        
        // Invoke the method responsible for deleting the game
        DeleteGameResponse response = gameController.deleteGame(request);

        System.out.println("Response isSuccess: " + response.isSuccess());
        System.out.println("Response errorMessage: " + response.getErrorMessage());
        
        // Assert that the response matches the expected response
        assertTrue(response.isSuccess());
        assertNull(response.getErrorMessage());
    }

    @Test
    public void testInvalidGameDeletion() {
        // Set up default data if needed
        
        // Create a GameController instance
        GameController gameController = new GameController();
        
        // Create a DeleteGameRequest with the gameId specified in the scenario
        int gameId = 99999; // gameId: 99999 (invalid)
        DeleteGameRequest request = new DeleteGameRequest(gameId);
        
        // Invoke the method responsible for deleting the game
        DeleteGameResponse response = gameController.deleteGame(request);

        System.out.println("Response isSuccess: " + response.isSuccess());
        System.out.println("Response errorMessage: " + response.getErrorMessage());
        
        // Assert that the response matches the expected response
        assertFalse("Success should be false", response.isSuccess());
        assertNotNull("ErrorMessage should not be null", response.getErrorMessage());
        assertEquals("ErrorMessage should contain 'Invalid GameId'", "Invalid GameId", response.getErrorMessage());
    }
}
