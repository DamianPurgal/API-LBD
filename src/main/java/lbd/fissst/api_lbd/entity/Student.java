package lbd.fissst.api_lbd.entity;

import lbd.fissst.api_lbd.entity.enums.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class Student {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private List<Subject> subjects = new ArrayList<>();

}
