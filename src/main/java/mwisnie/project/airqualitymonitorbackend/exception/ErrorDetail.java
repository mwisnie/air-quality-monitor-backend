package mwisnie.project.airqualitymonitorbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetail {

    private String errorCode;
    private String path;
    private String errorMsg;
    private String timestamp;

}
