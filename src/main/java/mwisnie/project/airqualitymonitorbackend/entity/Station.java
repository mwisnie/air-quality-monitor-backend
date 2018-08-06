package mwisnie.project.airqualitymonitorbackend.entity;

import lombok.Data;

@Data
public class Station {

    private int id;
    private String stationName;
    private String gegrLat;
    private String gegrLon;

}
