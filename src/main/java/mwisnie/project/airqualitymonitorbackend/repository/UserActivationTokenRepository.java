package mwisnie.project.airqualitymonitorbackend.repository;

import mwisnie.project.airqualitymonitorbackend.entity.UserActivationToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActivationTokenRepository extends MongoRepository<UserActivationToken, String> {
}
