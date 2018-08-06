package mwisnie.project.airqualitymonitorbackend.controller;

import lombok.RequiredArgsConstructor;
import mwisnie.project.airqualitymonitorbackend.entity.Station;
import mwisnie.project.airqualitymonitorbackend.entity.User;
import mwisnie.project.airqualitymonitorbackend.entity.UserActivationToken;
import mwisnie.project.airqualitymonitorbackend.service.activation.UserActivationTokenServiceImpl;
import mwisnie.project.airqualitymonitorbackend.service.data.AirQualityDataServiceImpl;
import mwisnie.project.airqualitymonitorbackend.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class UtilityController {

    @Autowired
    private final UserActivationTokenServiceImpl activationTokenService;

    @Autowired
    private final UserServiceImpl userService;

    @Autowired
    private final AirQualityDataServiceImpl dataService;

    @GetMapping("/confirmRegistration")
    public User confirmRegistration(@RequestParam("token") String token) {
        UserActivationToken obtainedToken = activationTokenService.getAccountActivationTokenByToken(token);
        if (token == null) {
            // todo: throw exception invalid token
            return null;
        }

        User user = activationTokenService.getUserByToken(obtainedToken);
        if (user == null) {
            //todo: throw exception invalid token
            return null;
        }
        user.setActive(true);

        userService.updateUser(user);

        return user;
    }

    @GetMapping("/stations")
    public List<Station> getAllMeasurementStations() {
        return dataService.getAllStations();
    }

    @GetMapping("/test")
    public String initialize(Authentication auth) {
        System.out.println(dataService.getAllStations());
        return "la";
    }

}
