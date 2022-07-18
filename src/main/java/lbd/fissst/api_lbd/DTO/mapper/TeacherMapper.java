package lbd.fissst.api_lbd.DTO.mapper;

import lbd.fissst.api_lbd.DTO.teacher.TeacherAddDTO;
import lbd.fissst.api_lbd.DTO.teacher.TeacherDTO;
import lbd.fissst.api_lbd.entity.Teacher;
import org.mapstruct.Mapper;

@Mapper
public interface TeacherMapper {

    Teacher mapTeacherDTOToTeacher(TeacherDTO teacherDTO);

    TeacherDTO mapTeacherToTeacherDTO(Teacher teacher);

    Teacher mapTeacherAddDTOToTeacher(TeacherAddDTO teacherAddDTO);
}
