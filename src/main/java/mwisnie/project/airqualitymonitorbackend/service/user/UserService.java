package mwisnie.project.airqualitymonitorbackend.service.user;

import mwisnie.project.airqualitymonitorbackend.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(String id);

    User createUser(User user);

    User updateUser(User user);

    void deleteUserById(String id);

}
