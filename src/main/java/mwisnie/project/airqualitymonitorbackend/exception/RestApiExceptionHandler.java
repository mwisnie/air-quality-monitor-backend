package mwisnie.project.airqualitymonitorbackend.exception;

import mwisnie.project.airqualitymonitorbackend.exception.types.RegistrationException;
import mwisnie.project.airqualitymonitorbackend.exception.types.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<Object> handleRegistrationException(RegistrationException exception, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(HttpStatus.CONFLICT.toString(), request.getDescription(false),
                                                    exception.getMessage(), new Date().toString());
        return new ResponseEntity<>(errorDetail, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException exception, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(HttpStatus.UNAUTHORIZED.toString(), request.getDescription(false),
                exception.getMessage(), new Date().toString());
        return new ResponseEntity<>(errorDetail, HttpStatus.UNAUTHORIZED);
    }


}
