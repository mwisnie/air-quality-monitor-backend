package mwisnie.project.airqualitymonitorbackend.entity;

import lombok.Data;

@Data
public class MeasuredEntity {

    private String paramName;
    private String paramFormula;
    private String paramCode;
    private int idParam;

}
