package mwisnie.project.airqualitymonitorbackend.repository;

import mwisnie.project.airqualitymonitorbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
