package mwisnie.project.airqualitymonitorbackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AirQualityIndexData {

    @JsonProperty(value = "id")
    private String stationId;

    private QualityLevel stIndexLevel;
    private QualityLevel so2IndexLevel;
    private QualityLevel no2IndexLevel;
    private QualityLevel coIndexLevel;
    private QualityLevel pm10IndexLevel;
    private QualityLevel pm25IndexLevel;
    private QualityLevel o3IndexLevel;
    private QualityLevel c6h6IndexLevel;

}
