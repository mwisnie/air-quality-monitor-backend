package mwisnie.project.airqualitymonitorbackend.service.data;

import mwisnie.project.airqualitymonitorbackend.entity.AirQualityIndexData;
import mwisnie.project.airqualitymonitorbackend.entity.MeasurementPoint;
import mwisnie.project.airqualitymonitorbackend.entity.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirQualityDataServiceImpl implements AirQualityDataService {

    @Value("${gios.api.url.quality}")
    private String apiGeneralQualityUrl;

    @Value("${gios.api.url.stations}")
    private String apiStationsUrl;

    @Value("${gios.api.url.station-detail}")
    private String apiStationDetailUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public AirQualityIndexData getDataForStation(String stationId) {
        return restTemplate.getForObject(String.format(apiGeneralQualityUrl, stationId), AirQualityIndexData.class);
    }

    @Override
    public List<Station> getAllStations() {
        ResponseEntity<List<Station>> response = restTemplate.exchange(
                apiStationsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Station>>(){});
        return response.getBody();
    }

    @Override
    public List<MeasurementPoint> getStationDetail(int id) {
        ResponseEntity<List<MeasurementPoint>> response = restTemplate.exchange(
                String.format(apiStationDetailUrl, id),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MeasurementPoint>>(){});
        return response.getBody();
    }

    public List<AirQualityIndexData> getDataForAllStations(List<String> stationIds) {
        List<AirQualityIndexData> results = new ArrayList<>();
        for (String stationId: stationIds) {
            results.add(getDataForStation(stationId));
        }
        return results;
    }

}
