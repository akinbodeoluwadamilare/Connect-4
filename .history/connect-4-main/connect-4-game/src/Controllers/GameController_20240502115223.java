package Controllers;

import DataAccess.GameDataAccess;
import DomainObjects.GameDomainObject;
import DomainObjects.PlayerDomainObject;
import Models.GameModel;
import Models.PlayerModel;
import restService.request.CreateGameRequest;
import restService.request.GameDetailsRequest;
import restService.request.PlayGameRequest;
import restService.request.DeleteGameRequest;
import restService.response.CreateGameResponse;
import restService.response.GameDetailsResponse;
import restService.response.PlayGameResponse;
import restService.response.DeleteGameResponse;

public class GameController {
    public GameDetailsResponse GetGameDetails(GameDetailsRequest request) {
        try {
            GameDomainObject gameDomain = GameModel.GetGameByGameId(request.getGameId());
            if (gameDomain == null) {
                throw new Exception("Game not found.");
            }
            return new GameDetailsResponse(gameDomain);
        } catch (Exception ex) {
            return new GameDetailsResponse(ex.getMessage());
        }
    }

    public CreateGameResponse CreateGame(CreateGameRequest request) {
        try {
            PlayerDomainObject player1 = PlayerModel.GetPlayerById(request.getPlayer1Id());
            PlayerDomainObject player2 = PlayerModel.GetPlayerById(request.getPlayer2Id());
            GameDomainObject gameObject = new GameDomainObject(request.getGameId(), player1, player2);
            GameDomainObject gameToReturn = GameModel.AddGame(gameObject);
            return new CreateGameResponse(gameToReturn);
        } catch (Exception ex) {
            return new CreateGameResponse(ex.getMessage());
        }
    }

    public PlayGameResponse PlayGame(PlayGameRequest request) {
        try {
            GameModel model = new GameModel();
            GameDomainObject gameToReturn = model.makeMove(request.getGameId(), request.getPlayerId(), request.getColumn());
            if (gameToReturn == null) {
                throw new Exception("Game not found.");
            }
            return new PlayGameResponse(gameToReturn);
        } catch (Exception ex) {
            return new PlayGameResponse(ex.getMessage());
        }
    }

    public DeleteGameResponse deleteGame(DeleteGameRequest request) {
        try {
            if (GameDataAccess.GetGameByGameId(request.getGameId()) != null) {
                // Game exists, delete it
                // Add delete logic here (e.g., GameDataAccess.DeleteGame(request.getGameId()))
                return new DeleteGameResponse(true, null);
            } else {
                // Game does not exist
                return new DeleteGameResponse(false, "Invalid Game.");
            }
        } catch (Exception ex) {
            return new DeleteGameResponse(false, ex.getMessage());
        }
    }
}
