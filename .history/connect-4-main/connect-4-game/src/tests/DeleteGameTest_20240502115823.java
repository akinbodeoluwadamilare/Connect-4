import org.junit.Test;
import static org.junit.Assert.*;
import Controllers.GameController;
import restService.request.DeleteGameRequest;
import restService.response.DeleteGameResponse;

public class DeleteGameTest {

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
        
        // Assert that the response matches the expected response
        assertTrue("Success should be true", response.isSuccess());
        assertNull("ErrorMessage should be null", response.getErrorMessage());
    }
}
