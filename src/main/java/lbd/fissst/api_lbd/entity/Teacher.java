package lbd.fissst.api_lbd.entity;

import lbd.fissst.api_lbd.entity.enums.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class Teacher {

    private Long id;
    private String firstName;
    private String lastName;
    private Subject subject;
}
