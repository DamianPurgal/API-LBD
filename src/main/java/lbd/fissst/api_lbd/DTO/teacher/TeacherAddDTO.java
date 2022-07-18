package lbd.fissst.api_lbd.DTO.teacher;

import lbd.fissst.api_lbd.entity.enums.Subject;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherAddDTO {

    private String firstName;
    private String lastName;
    private Subject subject;
}
