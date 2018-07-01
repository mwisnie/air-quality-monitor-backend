package mwisnie.project.airqualitymonitorbackend.service.activation;

import mwisnie.project.airqualitymonitorbackend.entity.User;
import mwisnie.project.airqualitymonitorbackend.entity.UserActivationToken;

public interface UserActivationTokenService {

    void createUserActivationToken(String userId, String token);

    UserActivationToken getAccountActivationTokenByToken(String token);

    User getUserByToken(UserActivationToken token);

}
