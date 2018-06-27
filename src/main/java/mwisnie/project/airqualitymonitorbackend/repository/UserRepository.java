package mwisnie.project.airqualitymonitorbackend.repository;


import mwisnie.project.airqualitymonitorbackend.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

}
