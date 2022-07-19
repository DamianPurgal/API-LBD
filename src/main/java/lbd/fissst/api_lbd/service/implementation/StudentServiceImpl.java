package lbd.fissst.api_lbd.service.implementation;

import lbd.fissst.api_lbd.entity.Student;
import lbd.fissst.api_lbd.entity.enums.Subject;
import lbd.fissst.api_lbd.exception.StudentNotFoundException;
import lbd.fissst.api_lbd.service.definition.StudentService;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
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
                        StudentNotFoundException::new
                );
    }

    @PostConstruct
    private void addStudentsToList(){
        students.add(
                Student.builder()
                        .id(1L)
                        .firstName("Damian")
                        .lastName("Purgal")
                        .age(22)
                        .subjects(new ArrayList<>(List.of(Subject.ALGEBRA)))
                        .build()
        );
        students.add(
                Student.builder()
                        .id(2L)
                        .firstName("Jan")
                        .lastName("Kowalski")
                        .age(43)
                        .subjects(new ArrayList<>(List.of(Subject.ALGEBRA)))
                        .build()
        );
        students.add(
                Student.builder()
                        .id(3L)
                        .firstName("Adam")
                        .lastName("Nowak")
                        .age(21)
                        .subjects(new ArrayList<>(List.of(Subject.LAW)))
                        .build()
        );
        students.add(
                Student.builder()
                        .id(4L)
                        .firstName("Mariusz")
                        .lastName("Pudzian")
                        .age(51)
                        .subjects(new ArrayList<>(List.of(Subject.BIOLOGY)))
                        .build()
        );
        students.add(
                Student.builder()
                        .id(5L)
                        .firstName("Robert")
                        .lastName("Lewandowski")
                        .age(43)
                        .subjects(new ArrayList<>(List.of(Subject.LAW)))
                        .build()
        );
        students.add(
                Student.builder()
                        .id(6L)
                        .firstName("Zdzislaw")
                        .lastName("Beksinski")
                        .age(52)
                        .subjects(new ArrayList<>(List.of(Subject.ALGEBRA, Subject.LAW)))
                        .build()
        );
        students.add(
                Student.builder()
                        .id(7L)
                        .firstName("Elon")
                        .lastName("Musk")
                        .age(55)
                        .subjects(new ArrayList<>(List.of(Subject.ALGEBRA, Subject.BIOLOGY)))
                        .build()
        );
        students.add(
                Student.builder()
                        .id(8L)
                        .firstName("Adam")
                        .lastName("Malysz")
                        .age(44)
                        .subjects(new ArrayList<>(List.of(Subject.LAW, Subject.BIOLOGY)))
                        .build()
        );
        students.add(
                Student.builder()
                        .id(9L)
                        .firstName("Magda")
                        .lastName("Gessler")
                        .age(87)
                        .subjects(new ArrayList<>(List.of(Subject.BIOLOGY)))
                        .build()
        );
        students.add(
                Student.builder()
                        .id(10L)
                        .firstName("Robert")
                        .lastName("Maklowicz")
                        .age(19)
                        .subjects(new ArrayList<>(List.of(Subject.LAW, Subject.BIOLOGY)))
                        .build()
        );
    }
}
