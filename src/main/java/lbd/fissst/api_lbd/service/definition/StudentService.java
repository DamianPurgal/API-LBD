package lbd.fissst.api_lbd.service.definition;

import lbd.fissst.api_lbd.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    Student addStudent(Student student);

    Student editStudent(Long id, Integer age, String lastName);

    void deleteStudent(Long id);

    Student getStudent(Long id);
}
