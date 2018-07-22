package mwisnie.project.airqualitymonitorbackend.service.email;

import mwisnie.project.airqualitymonitorbackend.entity.AirQualityIndexData;
import mwisnie.project.airqualitymonitorbackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    private static final String IP_CHECK_SERVICE = "http://checkip.amazonaws.com";

    //configured by SpringBoot with application.properties
    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private EmailConfig emailConfig;

    @Override
    public void sendActivationEmail(User user, String token) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(user.getEmail());
        message.setSubject(emailConfig.getActivationSubject());

        String machineAddress = getExternalIPAddress();
        String activationLink = machineAddress + "/confirmRegistration?token=" + token;

        message.setText(String.format(emailConfig.getActivationText(), user.getUsername(), activationLink));

        mailSender.send(message);
    }

    @Override
    public void sendAlertEmail(User user, List<AirQualityIndexData> dataList) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(user.getEmail());
        message.setSubject(emailConfig.getAlertSubject());

        StringBuilder builder = new StringBuilder();
        for (AirQualityIndexData data: dataList) {
            builder.append(String.format(emailConfig.getAlertText(), data.getStationId(), data.toString()));

        }
        message.setText(builder.toString());

        mailSender.send(message);
    }


    private String getExternalIPAddress() {
        String machineIP = null;
        URL checkIPUrl = null;

        try {
            checkIPUrl = new URL(IP_CHECK_SERVICE);
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
