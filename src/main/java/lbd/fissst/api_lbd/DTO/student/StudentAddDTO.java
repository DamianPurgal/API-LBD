package lbd.fissst.api_lbd.DTO.student;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentAddDTO {

    private String firstName;
    private String lastName;
    private Integer age;
}
