package lbd.fissst.api_lbd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class Student {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;

}
