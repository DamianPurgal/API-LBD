package lbd.fissst.api_lbd.DTO.student;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;

}
