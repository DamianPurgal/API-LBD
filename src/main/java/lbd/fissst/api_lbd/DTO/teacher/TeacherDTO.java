package lbd.fissst.api_lbd.DTO.teacher;

import lbd.fissst.api_lbd.entity.enums.Subject;
import lombok.Data;

@Data
public class TeacherDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Subject subject;

}
