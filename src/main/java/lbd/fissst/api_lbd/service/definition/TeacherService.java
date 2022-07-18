package lbd.fissst.api_lbd.service.definition;

import lbd.fissst.api_lbd.entity.Student;
import lbd.fissst.api_lbd.entity.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher addTeacher(Teacher teacher);

    void deleteTeacher(Long id);

    Teacher getTeacher(Long id);

    List<Student> getTeacherClass(Long id);

    void deleteStudentFromClassByTeacherId(Long studentId, Long teacherId);
}
