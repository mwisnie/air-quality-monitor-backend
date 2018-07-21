package mwisnie.project.airqualitymonitorbackend.service.data;

import mwisnie.project.airqualitymonitorbackend.entity.AirQualityIndexData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirQualityDataServiceImpl implements AirQualityDataService {

    @Value("${gios.api.url.quality}")
    private String apiGeneralQualityUrl;

    @Override
    public AirQualityIndexData getDataForStation(String stationId) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(String.format(apiGeneralQualityUrl, stationId), AirQualityIndexData.class);
    }

    public List<AirQualityIndexData> getDataForAllStations(List<String> stationIds) {
        List<AirQualityIndexData> results = new ArrayList<>();
        for (String stationId: stationIds) {
            results.add(getDataForStation(stationId));
        }
        return results;
    }

}
