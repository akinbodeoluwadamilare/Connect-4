package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Controllers.PlayerController;
import restService.request.RegisterPlayerRequest;
import restService.response.RegisterPlayerResponse;

public class PlayerControllerTest {
    private RegisterPlayerResponse existingPlayerResponse;

    @Before
    public void setUp() {
        PlayerController playerController = new PlayerController();

        // Test data
        String existingUsername = "DammyLare1";
        String password = "LetsPlay5829";

        // Attempt to register a player with an existing username
        RegisterPlayerRequest playerRequest = new RegisterPlayerRequest(existingUsername, password);
        existingPlayerResponse = playerController.registerPlayer(playerRequest);
    }

    @Test
    public void testNonuniquePlayerRegistration_Scenario1_3() {
        PlayerController playerController = new PlayerController();

        // Test data
        String existingUsername = "DammyLare1";
        String password = "LetsPlay5829";

        // Execute registration for an existing username
        RegisterPlayerRequest playerRequest = new RegisterPlayerRequest(existingUsername, password);
        RegisterPlayerResponse response = playerController.registerPlayer(playerRequest);

        System.out.println("Response isSuccess: " + response.isSuccess());
        System.out.println("Response playerId: " + response.getPlayerId());
        System.out.println("Response username: " + response.getUsername());
        System.out.println("Response errorMessage: " + response.getErrorMessage());

        // Verify the response
        assertFalse(response.isSuccess());
        assertEquals(-1, response.getPlayerId());
        assertNull(response.getUsername());
        assertEquals("Username already exists", response.getErrorMessage());
    }
}