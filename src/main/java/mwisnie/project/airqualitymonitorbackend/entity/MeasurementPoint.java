package mwisnie.project.airqualitymonitorbackend.entity;

import lombok.Data;

@Data
public class MeasurementPoint {

    private int id;
    private int stationId;
    private MeasuredEntity param;

}
