package lbd.fissst.api_lbd.exception;

import org.springframework.http.HttpStatus;

public class TeacherNotFoundException extends BusinessException {

    public TeacherNotFoundException(){
        super(HttpStatus.NOT_FOUND, "Teacher not found!");
    }
}
