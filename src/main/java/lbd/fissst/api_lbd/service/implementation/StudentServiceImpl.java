package lbd.fissst.api_lbd.service.implementation;

import lbd.fissst.api_lbd.entity.Student;
import lbd.fissst.api_lbd.service.definition.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    @Override
    public Student addStudent(Student student) {
        Long studentId = students.stream()
                .map(Student::getId)
                .max(Long::compare)
                .orElse(0L);
        studentId += 1L;

        student.setId(studentId);
        students.add(student);

        return student;
    }

    @Override
    public Student editStudent(Long id, Integer age, String lastName) {
        Student student = getStudentById(id);

        student.setAge(age);
        student.setLastName(lastName);

        return student;
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);

        students.remove(student);
    }

    @Override
    public Student getStudent(Long id) {
        return getStudentById(id);
    }

    private Student getStudentById(Long id){
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found")
                );
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
