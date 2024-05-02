package Controllers;

import DomainObjects.PlayerDomainObject;
import Models.PlayerModel;
import restService.request.RegisterPlayerRequest;
import restService.response.RegisterPlayerResponse;


public class PlayerController {
   
    
    public RegisterPlayerResponse registerPlayer(RegisterPlayerRequest request) {

        try{
            PlayerDomainObject domainObject = new PlayerDomainObject(0, request.getUsername(), request.getPassword());
            PlayerDomainObject domainCreated = PlayerModel.RegisterPlayer(domainObject);
            return new RegisterPlayerResponse(domainCreated);

        } catch (Exception ex) {

            RegisterPlayerResponse response = new RegisterPlayerResponse(ex.getMessage());
            return response;
        }
    }
}
