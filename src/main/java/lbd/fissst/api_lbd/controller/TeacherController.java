package lbd.fissst.api_lbd.controller;

import lbd.fissst.api_lbd.DTO.mapper.StudentMapper;
import lbd.fissst.api_lbd.DTO.mapper.TeacherMapper;
import lbd.fissst.api_lbd.DTO.student.StudentDTO;
import lbd.fissst.api_lbd.DTO.teacher.TeacherAddDTO;
import lbd.fissst.api_lbd.DTO.teacher.TeacherDTO;
import lbd.fissst.api_lbd.entity.Teacher;
import lbd.fissst.api_lbd.service.definition.TeacherService;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    private final TeacherMapper teacherMapper = Mappers.getMapper(TeacherMapper.class);

    private final StudentMapper studentMapper = Mappers.getMapper(StudentMapper.class);

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacher(@PathVariable("id") Long id){
        return ResponseEntity.ok()
                .header("successful", "true")
                .body(
                        teacherMapper.mapTeacherToTeacherDTO(
                                teacherService.getTeacher(id)
                        )
                );
    }

    @GetMapping("/{id}/class/student")
    public ResponseEntity<List<StudentDTO>> getTeacherClass(@PathVariable Long id){
        return ResponseEntity.ok()
                .header("successful", "true")
                .body(
                        teacherService.getTeacherClass(id)
                                .stream()
                                .map(studentMapper::mapStudentToStudentDTO)
                                .toList()
                );
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> addTeacher(@RequestBody TeacherAddDTO  teacherAddDTO){
        Teacher teacherToAdd = teacherMapper.mapTeacherAddDTOToTeacher(teacherAddDTO);

        return ResponseEntity.ok()
                .header("successful", "true")
                .body(
                        teacherMapper.mapTeacherToTeacherDTO(
                                teacherService.addTeacher(teacherToAdd)
                        )
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id){
        teacherService.deleteTeacher(id);

        return ResponseEntity.ok()
                .header("successful", "true")
                .build();
    }

    @DeleteMapping("/{teacherId}/class/student/{studentId}")
    public ResponseEntity<Void> deleteStudentFromClassByTeacherId(@PathVariable("teacherId") Long teacherId,
                                                                    @PathVariable("studentId") Long studentId){
        teacherService.deleteStudentFromClassByTeacherId(studentId, teacherId);

        return ResponseEntity.ok()
                .header("successful", "true")
                .build();
    }

}
