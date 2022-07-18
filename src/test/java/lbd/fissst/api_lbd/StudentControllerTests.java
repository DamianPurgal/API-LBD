package lbd.fissst.api_lbd;

import com.fasterxml.jackson.databind.ObjectMapper;
import lbd.fissst.api_lbd.DTO.student.StudentAddDTO;
import lbd.fissst.api_lbd.DTO.student.StudentUpdateDTO;
import lbd.fissst.api_lbd.entity.Student;
import lbd.fissst.api_lbd.entity.enums.Subject;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllStudents() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/student"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].firstName", Is.is("Damian")));
    }

    @Test
    void getStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/student/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.firstName", Is.is("Damian")));
    }

    @Test
    void addStudent() throws Exception {
        StudentAddDTO studentToAdd = StudentAddDTO.builder()
                .firstName("Damian")
                .lastName("Purgal")
                .age(22)
                .subjects(new ArrayList<>(List.of(Subject.ALGEBRA)))
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/student")
                        .content(asJsonString(studentToAdd))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.firstName", Is.is("Damian")));
    }

    @Test
    void updateStudent() throws Exception {
        StudentUpdateDTO studentUpdateDTO = StudentUpdateDTO.builder()
                .age(12)
                .lastName("Kamil")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.put("/api/student/{id}", 2)
                        .content(asJsonString(studentUpdateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.lastName", Is.is("Kamil")))
                .andExpect(jsonPath("$.age", Is.is(12)));
    }

    @Test
    void deleteStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/student/{id}", 10))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
