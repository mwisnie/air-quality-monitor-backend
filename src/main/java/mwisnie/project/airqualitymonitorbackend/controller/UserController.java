package mwisnie.project.airqualitymonitorbackend.controller;

import lombok.RequiredArgsConstructor;
import mwisnie.project.airqualitymonitorbackend.entity.User;
import mwisnie.project.airqualitymonitorbackend.listener.OnRegistrationEvent;
import mwisnie.project.airqualitymonitorbackend.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping(path = {"/api/users", "/api/users/"})
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class UserController {

    @Autowired
    private final UserServiceImpl userService;

    @Autowired
    private final ApplicationEventPublisher applicationEventPublisher;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user, WebRequest request) {
        //if username exists, throw throw error

        User createdUser = userService.createUser(user);
        if (createdUser == null) {
            //todo: throw specific error
            return null;
        }

        applicationEventPublisher.publishEvent(new OnRegistrationEvent(createdUser));
        return createdUser;
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUserById(id);
    }

}
