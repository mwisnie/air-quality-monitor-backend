package mwisnie.project.airqualitymonitorbackend.service.email;

import mwisnie.project.airqualitymonitorbackend.entity.User;

public interface EmailService {

    void sendActivationEmail(User user, String token);

}
