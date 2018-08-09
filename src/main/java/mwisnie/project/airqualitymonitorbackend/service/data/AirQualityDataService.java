package mwisnie.project.airqualitymonitorbackend.service.data;

import mwisnie.project.airqualitymonitorbackend.entity.AirQualityIndexData;
import mwisnie.project.airqualitymonitorbackend.entity.MeasurementPoint;
import mwisnie.project.airqualitymonitorbackend.entity.Station;

import java.util.List;

public interface AirQualityDataService {

    AirQualityIndexData getDataForStation(String stationId);

    List<AirQualityIndexData> getDataForAllStations(List<String> stationIds);

    List<Station> getAllStations();

    List<MeasurementPoint> getStationDetail(int id);

}
