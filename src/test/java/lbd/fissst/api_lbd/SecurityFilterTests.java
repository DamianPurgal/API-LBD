package lbd.fissst.api_lbd;

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
public class SecurityFilterTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenCorrectRole_whenRequestToStudentController_thenOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/student")
                        .header("role", "STUDENT_ROLE")
                )
                .andExpect(status().isOk());
    }

    @Test
    void givenInvalidRole_whenRequestToStudentController_thenUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/student")
                        .header("role", "NOT_EXISTING_ROLE")
                )
                .andExpect(status().isUnauthorized());
    }

    @Test
    void givenMissingHeader_whenRequestToStudentController_thenUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/student"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void givenCorrectRole_whenRequestToTeacherController_thenOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher/1")
                        .header("role", "TEACHER_ROLE")
                )
                .andExpect(status().isOk());
    }

    @Test
    void givenInvalidRole_whenRequestToTeacherController_thenUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher/1")
                        .header("role", "NOT_EXISTING_ROLE")
                )
                .andExpect(status().isUnauthorized());
    }

    @Test
    void givenMissingHeader_whenRequestToTeacherController_thenUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher/1"))
                .andExpect(status().isUnauthorized());
    }

}
