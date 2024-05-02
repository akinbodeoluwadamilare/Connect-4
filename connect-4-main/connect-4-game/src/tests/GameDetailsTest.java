package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controllers.GameController;
import DomainObjects.GameDomainObject;
import DomainObjects.PlayerDomainObject;
import Models.GameModel;
import restService.request.GameDetailsRequest;
import restService.response.GameDetailsResponse;

import org.junit.*;


public class GameDetailsTest {

    @Before
    public void setUp() {
       
        // Mocking some games for testing
        PlayerDomainObject player1 = new PlayerDomainObject(1, "DammyLare1", "Yellow");
        PlayerDomainObject player2 = new PlayerDomainObject(2, "JosephA", "Red");
        PlayerDomainObject player3 = new PlayerDomainObject(3, "DapoAbiodun", "Yellow");
        PlayerDomainObject player4 = new PlayerDomainObject(4, "TeamLagos", "Red");
        PlayerDomainObject player5 = new PlayerDomainObject(5, "Otedola", "Yellow");
        PlayerDomainObject player6 = new PlayerDomainObject(6, "Dangote", "Red");
       
        GameDomainObject game1 = new GameDomainObject(1, player1, player2);
        GameDomainObject game2 = new GameDomainObject(2, player3, player4);
        GameDomainObject game3 = new GameDomainObject(3, player5, player6);
       
        GameModel.AddGame(game1);
        GameModel.AddGame(game2);
        GameModel.AddGame(game3);
    }


    @Test
    public void testGetGameDetailsValidOngoingGame() {
        GameController controller = new GameController();
        GameDetailsRequest request = new GameDetailsRequest(1);
        GameDetailsResponse response = controller.GetGameDetails(request);
       
        assertTrue(response.isSuccess());
        assertEquals(1, response.getGameId());
        assertEquals("DammyLare1", response.getPlayer1Username());
        assertEquals("JosephA", response.getPlayer2Username());
        assertEquals(1, response.getCurrentTurnPlayer());
        assertEquals("Playing", response.getGameStatus());
        // assertEquals(-1, response.getWinner()); 
        assertEquals("000000000000000000000000000000000000000000", response.getBoard());
    }


    @Test
    public void testGetGameDetailsValidCompletedGameWithWinner() {
        GameController controller = new GameController();
        GameDetailsRequest request = new GameDetailsRequest(2);
        GameDetailsResponse response = controller.GetGameDetails(request);

        assertTrue(response.isSuccess());
        assertEquals(2, response.getGameId());
        assertEquals("DapoAbiodun", response.getPlayer1Username());
        assertEquals("TeamLagos", response.getPlayer2Username());
        assertEquals(3, response.getCurrentTurnPlayer());
        assertEquals("Playing", response.getGameStatus());
        // assertEquals(-1, response.getWinner());
        assertEquals("000000000000000000000000000000000000000000", response.getBoard());
    }


    @Test
    public void testGetGameDetailsValidCompletedGameWithStalemate() {
        GameController controller = new GameController();
        GameDetailsRequest request = new GameDetailsRequest(3);
        GameDetailsResponse response = controller.GetGameDetails(request);

        assertTrue(response.isSuccess());
        assertEquals(3, response.getGameId());
        assertEquals("Otedola", response.getPlayer1Username());
        assertEquals("Dangote", response.getPlayer2Username());
        assertEquals(5, response.getCurrentTurnPlayer());
        assertEquals("Playing", response.getGameStatus());
        // assertEquals(-1, response.getWinner());
        assertEquals("000000000000000000000000000000000000000000", response.getBoard());
    }


    @Test
    public void testGetGameDetailsInvalidGameId() {
        GameController controller = new GameController();
        GameDetailsRequest request = new GameDetailsRequest(999);
        GameDetailsResponse response = controller.GetGameDetails(request);

        assertFalse(response.isSuccess());
        assertEquals("Invalid GameId", response.getErrorMessage());
        assertEquals(-1, response.getGameId());
        assertEquals(-1, response.getCurrentTurnPlayer());
        assertNull(response.getGameStatus());
        assertEquals(-1, response.getWinner());
        assertEquals("", response.getBoard());
    }
}
