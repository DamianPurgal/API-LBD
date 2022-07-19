package lbd.fissst.api_lbd.exception;

import org.springframework.http.HttpStatus;

public class StudentNotFoundException extends BusinessException {

    public StudentNotFoundException(){
        super(HttpStatus.NOT_FOUND, "Student not found!");
    }

}
