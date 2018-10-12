package mwisnie.project.airqualitymonitorbackend.controller;

import lombok.RequiredArgsConstructor;
import mwisnie.project.airqualitymonitorbackend.entity.User;
import mwisnie.project.airqualitymonitorbackend.event.OnRegistrationEvent;
import mwisnie.project.airqualitymonitorbackend.exception.types.RegistrationException;
import mwisnie.project.airqualitymonitorbackend.exception.types.UnauthorizedException;
import mwisnie.project.airqualitymonitorbackend.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = {"/api/users", "/api/users/"})
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    @Autowired
    private final UserServiceImpl userService;

    @Autowired
    private final ApplicationEventPublisher applicationEventPublisher;

    @GetMapping
    public List<User> getAllUsers(Authentication auth) {
        //todo : fix
        if (isAdmin(auth) || true) {
            return userService.getAllUsers();
        }
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") String id, Authentication auth) {
        if (!isAuthorized(id, auth)) {
            throw new UnauthorizedException(String.format("Not authorized to see data of user %s.", id));
        }
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody @Valid User user) {
        User createdUser = userService.createUser(user);
        if (createdUser == null) {
            throw new RegistrationException("Error - unable to create the user.");
        }

        applicationEventPublisher.publishEvent(new OnRegistrationEvent(createdUser));
        return createdUser;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") String id, @RequestBody User user, Authentication auth) {
        if (!isAuthorized(id, auth) || !id.equals(user.getId())) {
            throw new UnauthorizedException(String.format("Not authorized to edit user %s.", id));
        }
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id, Authentication auth) {
        if (!isAuthorized(id, auth)) {
            throw new UnauthorizedException(String.format("Not authorized to delete user %s.", id));
        }
        userService.deleteUserById(id);
    }

    private boolean isAuthorized(String id, Authentication auth) {
        return id.equals(userService.getUserByUsername(auth.getPrincipal().toString()).getId())
                || isAdmin(auth);
    }

    private boolean isAdmin(Authentication auth) {
        //todo: implementation
        return false;
    }

}
