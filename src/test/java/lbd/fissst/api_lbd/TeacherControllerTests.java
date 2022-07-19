package lbd.fissst.api_lbd;

import com.fasterxml.jackson.databind.ObjectMapper;
import lbd.fissst.api_lbd.DTO.teacher.TeacherAddDTO;
import lbd.fissst.api_lbd.entity.enums.Subject;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class TeacherControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getTeacher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher/{id}", 1)
                        .header("role", "TEACHER_ROLE")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.firstName", Is.is("Robert")));
    }

    @Test
    public void getTeacherClass() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher/{id}/class/student", 2)
                        .header("role", "TEACHER_ROLE")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].firstName", Is.is("Adam")))
                .andExpect(jsonPath("$.[0].lastName", Is.is("Nowak")));
    }

    @Test
    public void addTeacher() throws Exception {
        TeacherAddDTO teacherAddDTO = TeacherAddDTO.builder()
                .firstName("Magnus")
                .lastName("Carlsen")
                .subject(Subject.ALGEBRA)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/teacher")
                        .content(asJsonString(teacherAddDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("role", "TEACHER_ROLE")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.firstName", Is.is("Magnus")));
    }

    @Test
    public void deleteTeacher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/teacher/{id}", 3)
                        .header("role", "TEACHER_ROLE")
                )
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNotExistingTeacher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/teacher/{id}", 50)
                        .header("role", "TEACHER_ROLE")
                )
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteStudentFromClassByTeacherId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/teacher/{teacherId}/class/student/{studentId}", 2, 5)
                        .header("role", "TEACHER_ROLE")
                )
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNotExistingStudentFromClassByTeacherId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/teacher/{teacherId}/class/student/{studentId}", 2, 50)
                        .header("role", "TEACHER_ROLE")
                )
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteStudentFromClassByNotExistingTeacherId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/teacher/{teacherId}/class/student/{studentId}", 50, 2)
                        .header("role", "TEACHER_ROLE")
                )
                .andExpect(status().isNotFound());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
