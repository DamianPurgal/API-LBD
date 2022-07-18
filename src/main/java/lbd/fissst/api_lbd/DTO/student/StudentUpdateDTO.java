package lbd.fissst.api_lbd.DTO.student;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentUpdateDTO {

    private String lastName;
    private Integer age;

}
