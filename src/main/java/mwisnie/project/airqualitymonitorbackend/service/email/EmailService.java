package mwisnie.project.airqualitymonitorbackend.service.email;

import mwisnie.project.airqualitymonitorbackend.entity.AirQualityIndexData;
import mwisnie.project.airqualitymonitorbackend.entity.User;

import java.util.List;

public interface EmailService {

    void sendActivationEmail(User user, String token);

    void sendAlertEmail(User user, List<AirQualityIndexData> data);

}
