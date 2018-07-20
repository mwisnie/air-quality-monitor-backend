package mwisnie.project.airqualitymonitorbackend.repository;

import mwisnie.project.airqualitymonitorbackend.entity.UserActivationToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserActivationTokenRepository extends MongoRepository<UserActivationToken, String> {

    Optional<UserActivationToken> getUserActivationTokenByToken(String token);

}
