package mwisnie.project.airqualitymonitorbackend.service.user;

import lombok.RequiredArgsConstructor;
import mwisnie.project.airqualitymonitorbackend.entity.User;
import mwisnie.project.airqualitymonitorbackend.exception.types.RegistrationException;
import mwisnie.project.airqualitymonitorbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User createUser(User user) {
        userRepository.findByUsername(user.getUsername()).ifPresent(u -> {
            String msg = String.format("Username %s is already taken.", u.getUsername());
            throw new RegistrationException(msg);
        });
        userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
            String msg = String.format("Email %s is already taken.", u.getEmail());
            throw new RegistrationException(msg);
        });

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

}
