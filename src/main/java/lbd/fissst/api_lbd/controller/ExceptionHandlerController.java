package lbd.fissst.api_lbd.controller;


import lbd.fissst.api_lbd.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorMessage> handleBusinessException(BusinessException exception){
        return ResponseEntity.status(exception.getStatus())
                .header("successful", "false")
                .body(
                        new ErrorMessage(
                                exception.getStatus().value(),
                                exception.getMessage()
                        )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleDefaultException(Exception exception){
        return ResponseEntity.internalServerError()
                .header("successful", "false")
                .body(
                        new ErrorMessage(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                "Internal server error!"
                        )
                );
    }

    @Data
    @AllArgsConstructor
    static class ErrorMessage{
        Integer status;
        String message;
    }
}
