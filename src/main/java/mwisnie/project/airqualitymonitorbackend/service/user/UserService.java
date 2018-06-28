package mwisnie.project.airqualitymonitorbackend.service.user;

import mwisnie.project.airqualitymonitorbackend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserById(String id);

    Optional<User> createUser(User user);

    Optional<User> updateUser(User user);

    void deleteUserById(String id);

}
