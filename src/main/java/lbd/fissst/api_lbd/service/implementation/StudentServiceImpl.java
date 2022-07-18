package lbd.fissst.api_lbd.service.implementation;

import lbd.fissst.api_lbd.entity.Student;
import lbd.fissst.api_lbd.service.definition.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final List<Student> students = new ArrayList<>();

    @Override
    public List<Student> getAllStudents() {
        return students;
    }

    @PostConstruct
    private void addStudentsToList(){
        students.add(
            new Student(1L, "Damian", "Purgal", 22)
        );
        students.add(
                new Student(2L, "Jan", "Kowalski", 45)
        );
        students.add(
                new Student(3L, "Adam", "Nowak", 19)
        );
        students.add(
                new Student(4L, "Mariusz", "Pudzian", 52)
        );
        students.add(
                new Student(5L, "Robert", "Lewandowski", 43)
        );
    }
}
