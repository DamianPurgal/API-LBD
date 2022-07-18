package lbd.fissst.api_lbd.controller;

import lbd.fissst.api_lbd.DTO.mapper.StudentMapper;
import lbd.fissst.api_lbd.DTO.student.StudentDTO;
import lbd.fissst.api_lbd.DTO.student.StudentUpdateDTO;
import lbd.fissst.api_lbd.entity.Student;
import lbd.fissst.api_lbd.service.definition.StudentService;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    private final StudentMapper mapper = Mappers.getMapper(StudentMapper.class);

    @GetMapping
    public List<StudentDTO> getAllStudents(){
        return studentService.getAllStudents().stream()
                .map(mapper::mapStudentToStudentDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public StudentDTO getStudent(@PathVariable("id") Long id){
        return mapper.mapStudentToStudentDTO(
                studentService.getStudent(id)
        );
    }

    @PostMapping
    public StudentDTO addStudent(@RequestBody StudentDTO studentDTO){
        Student studentToAdd = mapper.mapStudentDTOToStudent(
                studentDTO
        );

        return mapper.mapStudentToStudentDTO(
                studentService.addStudent(studentToAdd)
        );
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent(@RequestBody StudentUpdateDTO studentUpdateDTO,
                                    @PathVariable("id") Long id){
        Student updatedStudent = studentService.editStudent(
                id,
                studentUpdateDTO.getAge(),
                studentUpdateDTO.getLastName()
        );

        return mapper.mapStudentToStudentDTO(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") Long id){
        studentService.deleteStudent(id);
    }

}
