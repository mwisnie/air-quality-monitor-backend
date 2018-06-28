package mwisnie.project.airqualitymonitorbackend.service.email;

import lombok.RequiredArgsConstructor;
import mwisnie.project.airqualitymonitorbackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailServiceImpl implements EmailService {

    private static final String MAIL_SUBJECT = "Air Quality Monitor account creation confirmation";

    //configured by SpringBoot with application.properties
    @Autowired
    private final JavaMailSenderImpl mailSender;

    @Override
    public void sendActivationEmail(User user, String token) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(user.getEmail());
        message.setSubject(MAIL_SUBJECT);

        String machineAddress = getExternalIPAddress();
        message.setText("todo: from properties" + machineAddress + "/activateAccount?token=" + token);

        mailSender.send(message);
    }

    private String getExternalIPAddress() {
        String machineIP = null;
        URL checkIPUrl = null;

        try {
            checkIPUrl = new URL("http://checkip.amazonaws.com");
        } catch (MalformedURLException e) {
            //should never happen, anyway, todo: throw specific error
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(checkIPUrl.openStream()))) {
            machineIP = reader.readLine();
        } catch (IOException e) {
            //todo: throw specific error
        }

        return machineIP;
    }

}
