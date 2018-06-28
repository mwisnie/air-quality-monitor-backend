package mwisnie.project.airqualitymonitorbackend.service.activation;

import mwisnie.project.airqualitymonitorbackend.entity.User;

import java.util.Optional;

public interface UserActivationTokenService {

    void createEmailVerificationToken(String userId, String token);

    Optional<User> getUserByToken(String token);

}
