package lbd.fissst.api_lbd.controller;

import lbd.fissst.api_lbd.DTO.mapper.StudentMapper;
import lbd.fissst.api_lbd.DTO.student.StudentDTO;
import lbd.fissst.api_lbd.DTO.student.StudentUpdateDTO;
import lbd.fissst.api_lbd.entity.Student;
import lbd.fissst.api_lbd.service.definition.StudentService;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    private final StudentMapper mapper = Mappers.getMapper(StudentMapper.class);

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        try{
            List<StudentDTO> result =  studentService.getAllStudents().stream()
                    .map(mapper::mapStudentToStudentDTO)
                    .toList();

            return ResponseEntity.ok()
                    .header("successful", "true")
                    .body(result);

        }catch(ResponseStatusException exception){
            return ResponseEntity.status(exception.getStatus())
                    .header("successful", "false")
                    .build();
        }catch(Exception exception){
            return ResponseEntity.internalServerError()
                    .header("successful", "false")
                    .build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable("id") Long id){
        try{
            StudentDTO result = mapper.mapStudentToStudentDTO(
                    studentService.getStudent(id));

            return ResponseEntity.ok()
                    .header("successful", "true")
                    .body(result);

        }catch(ResponseStatusException exception){
            return ResponseEntity.status(exception.getStatus())
                    .header("successful", "false")
                    .build();

        }catch(Exception exception){
            return ResponseEntity.internalServerError()
                    .header("successful", "false")
                    .build();
        }
    }

    @PostMapping
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentDTO){
        Student studentToAdd = mapper.mapStudentDTOToStudent(studentDTO);

        try{
            StudentDTO result = mapper.mapStudentToStudentDTO(
                    studentService.addStudent(studentToAdd));

            return ResponseEntity.ok()
                    .header("successful", "true")
                    .body(result);

        }catch(ResponseStatusException exception){
            return ResponseEntity.status(exception.getStatus())
                    .header("successful", "false")
                    .build();
        }catch(Exception exception){
            return ResponseEntity.internalServerError()
                    .header("successful", "false")
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> editStudent(@RequestBody StudentUpdateDTO studentUpdateDTO,
                                    @PathVariable("id") Long id){
        Student updatedStudent = studentService.editStudent(
                id,
                studentUpdateDTO.getAge(),
                studentUpdateDTO.getLastName()
        );

        try{
            StudentDTO result = mapper.mapStudentToStudentDTO(updatedStudent);

            return ResponseEntity.ok()
                    .header("successful", "true")
                    .body(result);

        }catch(ResponseStatusException exception){
            return ResponseEntity.status(exception.getStatus())
                    .header("successful", "false")
                    .build();
        }catch(Exception exception){
            return ResponseEntity.internalServerError()
                    .header("successful", "false")
                    .build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id){

        try{
            studentService.deleteStudent(id);

            return ResponseEntity.ok()
                    .header("successful", "true")
                    .build();

        }catch(ResponseStatusException exception){
            return ResponseEntity.status(exception.getStatus())
                    .header("successful", "false")
                    .build();
        }catch(Exception exception){
            return ResponseEntity.internalServerError()
                    .header("successful", "false")
                    .build();
        }
    }

}
