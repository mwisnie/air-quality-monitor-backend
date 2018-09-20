package mwisnie.project.airqualitymonitorbackend.scheduler;

import mwisnie.project.airqualitymonitorbackend.entity.AirQualityIndexData;
import mwisnie.project.airqualitymonitorbackend.entity.QualityLevel;
import mwisnie.project.airqualitymonitorbackend.entity.User;
import mwisnie.project.airqualitymonitorbackend.service.data.AirQualityDataServiceImpl;
import mwisnie.project.airqualitymonitorbackend.service.email.EmailServiceImpl;
import mwisnie.project.airqualitymonitorbackend.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlertScheduler {

//    private static final long APP_CHECK_RATE = 1000 * 60 * 60L;

    @Autowired
    private AirQualityDataServiceImpl dataService;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private UserServiceImpl userService;

//    @Scheduled(fixedRate = APP_CHECK_RATE)
    @Scheduled(fixedRate = 10000)
    public void scheduleAlertTaskWithFixedRate() {
        List<User> users = userService.getAllUsers();
        users = users.stream().filter(User::isActive).filter(User::isAlertOn).collect(Collectors.toList());

        for (User user: users) {
            List<String> stationsToProcess = new ArrayList<>();
            stationsToProcess.addAll(user.getStationIds().keySet());
            List<AirQualityIndexData> dataList = dataService.getDataForAllStations(stationsToProcess);
            List<AirQualityIndexData> dataExceedingLimits = new ArrayList<>();

            for (AirQualityIndexData data: dataList) {
                if (isAnyQualityIndexAbove(data, 0)) {
                    dataExceedingLimits.add(data);
                }
            }

            if (!dataExceedingLimits.isEmpty()) {
                emailService.sendAlertEmail(user, dataExceedingLimits);
            }
        }
    }

    private boolean isAnyQualityIndexAbove(AirQualityIndexData data, int index) {
        return isQualityIndexAbove(data.getStIndexLevel(), index)
                || isQualityIndexAbove(data.getSo2IndexLevel(), index)
                || isQualityIndexAbove(data.getNo2IndexLevel(), index)
                || isQualityIndexAbove(data.getCoIndexLevel(), index)
                || isQualityIndexAbove(data.getPm10IndexLevel(), index)
                || isQualityIndexAbove(data.getPm25IndexLevel(), index)
                || isQualityIndexAbove(data.getO3IndexLevel(), index)
                || isQualityIndexAbove(data.getC6h6IndexLevel(), index);
    }

    private boolean isQualityIndexAbove(QualityLevel level, int index) {
        return level != null && Integer.parseInt(level.getId()) > index;
    }

}
