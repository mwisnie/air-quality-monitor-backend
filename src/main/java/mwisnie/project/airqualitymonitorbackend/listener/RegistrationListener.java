package mwisnie.project.airqualitymonitorbackend.listener;

import lombok.RequiredArgsConstructor;
import mwisnie.project.airqualitymonitorbackend.entity.User;
import mwisnie.project.airqualitymonitorbackend.service.activation.UserActivationTokenServiceImpl;
import mwisnie.project.airqualitymonitorbackend.service.email.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegistrationListener implements ApplicationListener<OnRegistrationEvent> {

    @Autowired
    private final EmailServiceImpl emailService;

    @Autowired
    private final UserActivationTokenServiceImpl tokenService;


    @Override
    public void onApplicationEvent(OnRegistrationEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();

        tokenService.createEmailVerificationToken(user.getId(), token);
        emailService.sendActivationEmail(user, token);
    }

}
