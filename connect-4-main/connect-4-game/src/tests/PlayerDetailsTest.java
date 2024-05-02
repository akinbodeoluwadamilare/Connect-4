package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Controllers.PlayerController;
import DataAccess.PlayerDataAccess;
import restService.request.PlayerDetailsRequest;
import restService.response.PlayerDetailsResponse;

public class PlayerDetailsTest {

  @Before
  public void setUp() {
    PlayerDataAccess initAccess = new PlayerDataAccess();
  }

    @Test
    public void testValidPlayer() {
        int playerId = 1;

        PlayerDetailsRequest request = new PlayerDetailsRequest(playerId);
        PlayerController playerController = new PlayerController();
        PlayerDetailsResponse response = playerController.getPlayerDetails(request);

        // Verify the response
       assertTrue(response.isSuccess());
       assertEquals(1, response.getPlayerId());
       assertEquals("DammyLare1", response.getUsername());
        assertNull(response.getErrorMessage());
    }

    @Test
    public void testInValidPlayer() {
        int playerId = 10;

        PlayerDetailsRequest request = new PlayerDetailsRequest(playerId);
        PlayerController playerController = new PlayerController();
        PlayerDetailsResponse response = playerController.getPlayerDetails(request);

        // Verify the response
       assertFalse(response.isSuccess());
       assertEquals(-1, response.getPlayerId());
       assertNull(response.getUsername());
       assertEquals("Player Id 10 does not exist", response.getErrorMessage());
    }
}