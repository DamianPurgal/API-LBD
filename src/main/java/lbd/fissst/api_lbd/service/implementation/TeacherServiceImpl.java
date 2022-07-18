package lbd.fissst.api_lbd.service.implementation;

import lbd.fissst.api_lbd.entity.Student;
import lbd.fissst.api_lbd.entity.Teacher;
import lbd.fissst.api_lbd.entity.enums.Subject;
import lbd.fissst.api_lbd.service.definition.StudentService;
import lbd.fissst.api_lbd.service.definition.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final List<Teacher> teachers;

    //Uzywam implementacji serwisu, poniewaz traktuje to jako repozytorium(nie mam jak inaczej dostac sie do listy studentow)
    //Uzywajac bazy danych korzystalbym z StudentRepository.
    private final StudentServiceImpl studentService;

    @Override
    public Teacher addTeacher(Teacher teacher) {
        Long teacherId = teachers.stream()
                .map(Teacher::getId)
                .max(Long::compare)
                .orElse(0L);
        teacherId += 1L;

        teacher.setId(teacherId);
        teachers.add(teacher);

        return teacher;
    }

    @Override
    public void deleteTeacher(Long id) {
        Teacher teacher = getTeacherById(id);

        teachers.remove(teacher);
    }

    @Override
    public Teacher getTeacher(Long id) {
        return getTeacherById(id);
    }

    @Override
    public List<Student> getTeacherClass(Long id) {
        Subject subjectOfTeacher = getTeacherById(id).getSubject();

        return studentService.getStudents()
                .stream()
                .filter(
                        subjects -> subjects.getSubjects()
                                .stream()
                                .anyMatch(s -> s.equals(subjectOfTeacher))
                )
                .toList();
    }

    @Override
    public void deleteStudentFromClassByTeacherId(Long studentId, Long teacherId) {
        Subject subjectOfTeacher = getTeacherById(teacherId).getSubject();

        Student student = studentService.getStudents()
                .stream()
                .filter(s -> s.getId().equals(studentId))
                .findFirst()
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found")
                );

        student.getSubjects().remove(subjectOfTeacher);
    }

    private Teacher getTeacherById(Long id){
        return teachers.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found")
                );
    }

    @PostConstruct
    private void addTeachersToList(){
        teachers.add(
          Teacher.builder()
                  .id(1L)
                  .firstName("Robert")
                  .lastName("Kubica")
                  .subject(Subject.ALGEBRA)
                  .build()
        );
        teachers.add(
                Teacher.builder()
                        .id(2L)
                        .firstName("Krzystof")
                        .lastName("Krawczyk")
                        .subject(Subject.LAW)
                        .build()
        );
        teachers.add(
                Teacher.builder()
                        .id(3L)
                        .firstName("Tomasz")
                        .lastName("Fornal")
                        .subject(Subject.BIOLOGY)
                        .build()
        );
    }
}
