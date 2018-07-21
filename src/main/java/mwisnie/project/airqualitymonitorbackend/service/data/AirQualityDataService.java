package mwisnie.project.airqualitymonitorbackend.service.data;

import mwisnie.project.airqualitymonitorbackend.entity.AirQualityIndexData;

import java.util.List;

public interface AirQualityDataService {

    AirQualityIndexData getDataForStation(String stationId);

    List<AirQualityIndexData> getDataForAllStations(List<String> stationIds);

}
