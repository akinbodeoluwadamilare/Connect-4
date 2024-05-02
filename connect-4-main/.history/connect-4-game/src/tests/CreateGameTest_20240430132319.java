package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Controllers.GameController;
import DataAccess.PlayerDataAccess;
import restService.request.CreateGameRequest;
import restService.response.CreateGameResponse;
public class CreateGameTest
{
    @Before
    public void setUp() {
       
        // Mocking some games for testing
        PlayerDataAccess initAccess = new PlayerDataAccess();
    }
    
    @Test
    public void testValidGameCreation_Scenario3_1() {
        // Test data
        int gameId = 1;
        int player1id = 1;
        int player2id = 2;


        // Execute
        CreateGameRequest request = new CreateGameRequest(gameId, player1id, player2id);
        GameController controller = new GameController();
        CreateGameResponse response = controller.CreateGame(request);

        // Verify
        assertTrue(response.isSuccess());
        assertEquals(null, response.getErrorMessage());
        assertEquals(1, response.getGameId());
        assertEquals(1, response.getPlayerOneId());
        assertEquals(2, response.getPlayerTwoId());

        assertEquals("joseph", response.getPlayer1Username());
        assertEquals("dare", response.getPlayer2Username());
        assertTrue(response.getCurrentTurnPlayer() == 1 || response.getCurrentTurnPlayer() == 2);
        assertEquals("Playing", response.getGameStatus());
        assertEquals("000000000000000000000000000000000000000000", response.getBoard());
    }


    // JUnit Test for Invalid Game Creation with invalid playerId


    @Test
    public void testInvalidGameCreation_Scenario3_2() {

        // Test data
        int gameId = 2;
        int invalidPlayerId = 999999999;
        int player2id = 2;


        // Execute
        CreateGameRequest request = new CreateGameRequest(gameId, invalidPlayerId, player2id);
        GameController controller = new GameController();
        CreateGameResponse response = controller.CreateGame(request);

        // Verify
        assertFalse(response.isSuccess());
        assertEquals("Player Id 999999999 does not exist", response.getErrorMessage());
        assertEquals(-1, response.getGameId());
        assertEquals(-1, response.getPlayerOneId());
        assertEquals(-1, response.getPlayerTwoId());
        assertEquals(-1, response.getCurrentTurnPlayer());
        assertNull(response.getGameStatus());
        assertEquals("", response.getBoard());
    }


    // JUnit Test for Missing Player ID


    @Test
    public void testMissingPlayerId_Scenario3_3() {

        // Test data
        int gameId = 3;
        int player1Id = 3;
        int player2Id = 7;

        // Execute
        CreateGameRequest request = new CreateGameRequest(gameId, player1Id, player2Id);
        GameController controller = new GameController();
        CreateGameResponse response = controller.CreateGame(request);

        // Verify
        assertFalse(response.isSuccess());
        assertEquals("Player Id 3 does not exist", response.getErrorMessage());
        assertEquals(-1, response.getGameId());
        assertEquals(-1, response.getPlayerOneId());
        assertEquals(-1, response.getPlayerTwoId());
        assertEquals(-1, response.getCurrentTurnPlayer());
        assertNull(response.getGameStatus());
        assertEquals("", response.getBoard());
    }
}

