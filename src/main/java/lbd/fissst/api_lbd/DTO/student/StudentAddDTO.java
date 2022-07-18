package lbd.fissst.api_lbd.DTO.student;

import lbd.fissst.api_lbd.entity.enums.Subject;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudentAddDTO {

    private String firstName;
    private String lastName;
    private Integer age;
    private List<Subject> subjects;
}
