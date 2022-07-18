package lbd.fissst.api_lbd.DTO.mapper;

import lbd.fissst.api_lbd.DTO.student.StudentDTO;
import lbd.fissst.api_lbd.DTO.student.StudentUpdateDTO;
import lbd.fissst.api_lbd.entity.Student;
import org.mapstruct.Mapper;

@Mapper
public interface StudentMapper {

    Student mapStudentDTOToStudent(StudentDTO studentDTO);

    StudentDTO mapStudentToStudentDTO(Student student);

}
