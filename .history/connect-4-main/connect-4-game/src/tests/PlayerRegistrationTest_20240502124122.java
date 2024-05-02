package tests;
// Let's add the eighth test scenario for invalid password with special characters to the PlayerRegistrationTest class and then run the JUnit test to validate the registerPlayer method.

// Updated JUnit Test Scenarios

import static org.junit.Assert.*;

import org.junit.Test;

import Controllers.PlayerController;
import restService.request.RegisterPlayerRequest;
import restService.response.RegisterPlayerResponse;

public class PlayerRegistrationTest {

    @Test
    public void testInvalidInputs_Scenario1_1() {
        PlayerController playerController = new PlayerController();

        // Test data
        String username = "short";
        String password = "tenchars00";

        RegisterPlayerRequest playerRequest = new RegisterPlayerRequest(username, password);
        RegisterPlayerResponse response = playerController.registerPlayer(playerRequest);
        // Execute
        // Verify
        assertFalse(response.isSuccess());
        assertEquals("Invalid Username", response.getErrorMessage());
        assertEquals(-1, response.getPlayerId());
        assertNull(response.getUsername());
    }

    @Test
    public void testValidInputs_Scenario1_2() {
        PlayerController playerController = new PlayerController();

        // Test data
        String username = "DammyLare3";
        String password = "BestPlayer02e";

        // Execute
        RegisterPlayerRequest playerRequest = new RegisterPlayerRequest(username, password);
        RegisterPlayerResponse response = playerController.registerPlayer(playerRequest);
        // Verify
        assertTrue(response.isSuccess());
        assertNull(response.getErrorMessage());
        assertEquals(0, response.getPlayerId());
        assertEquals(username, response.getUsername());
    }

    @Test
    public void testInvalidInputs_Scenario1_4() {
        PlayerController playerController = new PlayerController();

        // Test data
        String username = "DammyLare1";
        String password = "Dammy";

        // Execute
        RegisterPlayerRequest playerRequest = new RegisterPlayerRequest(username, password);
        RegisterPlayerResponse response = playerController.registerPlayer(playerRequest);
        // Verify
        assertFalse(response.isSuccess());
        assertEquals("Invalid Password", response.getErrorMessage());
        assertEquals(-1, response.getPlayerId());
        assertNull(response.getUsername());
    }

    @Test
    public void testInvalidInputs_Scenario1_5() {
        PlayerController playerController = new PlayerController();

        // Test data
        String username = "OluwaDammyLareAkinbode1";
        String password = "LetsPlay5949";

        // Execute
        RegisterPlayerRequest playerRequest = new RegisterPlayerRequest(username, password);
        RegisterPlayerResponse response = playerController.registerPlayer(playerRequest);
        // Verify
        assertFalse(response.isSuccess());
        assertEquals("Invalid Username", response.getErrorMessage());
        assertEquals(-1, response.getPlayerId());
        assertNull(response.getUsername());
    }

    @Test
    public void testInvalidInputs_Scenario1_6() {
        PlayerController playerController = new PlayerController();

        // Test data
        String username = "DammyLare1";
        String password = "OluwaDammyLareAkinbode1";

        // Execute
        RegisterPlayerRequest playerRequest = new RegisterPlayerRequest(username, password);
        RegisterPlayerResponse response = playerController.registerPlayer(playerRequest);
        // Verify
        assertFalse(response.isSuccess());
        assertEquals("Invalid Password", response.getErrorMessage());
        assertEquals(-1, response.getPlayerId());
        assertNull(response.getUsername());
    }

    @Test
    public void testInvalidInputs_Scenario1_7() {
        PlayerController playerController = new PlayerController();

        // Test data
        String username = "DammyLare$#";
        String password = "LetsPlay6009";

        // Execute
        RegisterPlayerRequest playerRequest = new RegisterPlayerRequest(username, password);
        RegisterPlayerResponse response = playerController.registerPlayer(playerRequest);

        // Verify
        assertFalse(response.isSuccess());
        assertEquals("Invalid Username", response.getErrorMessage());
        assertEquals(-1, response.getPlayerId());
        assertNull(response.getUsername());
    }

    @Test
    public void testInvalidInputs_Scenario1_8() {
        PlayerController playerController = new PlayerController();

        // Test data
        String username = "DamyLare1";
        String password = "LetsPlay6009_&";

        // Execute
        RegisterPlayerRequest playerRequest = new RegisterPlayerRequest(username, password);
        RegisterPlayerResponse response = playerController.registerPlayer(playerRequest);
        // Verify
        assertFalse(response.isSuccess());
        assertEquals("Invalid Password", response.getErrorMessage());
        assertEquals(-1, response.getPlayerId());
        assertNull(response.getUsername());
    }
}
