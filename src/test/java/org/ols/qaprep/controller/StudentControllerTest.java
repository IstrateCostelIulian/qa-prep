package org.ols.qaprep.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ols.qaprep.entity.Student;
import org.ols.qaprep.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetAllStudents() throws Exception {
        // Arrange
        Student student1 = new Student();
        student1.setName("Alice");
        student1.setNoOfPresence(0);

        Student student2 = new Student();
        student2.setName("Bob");
        student2.setNoOfPresence(0);

        when(studentService.getAllStudents()).thenReturn(Arrays.asList(student1, student2));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();

        // Act & Assert
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[0].noOfPresence").value(0))
                .andExpect(jsonPath("$[1].name").value("Bob"))
                .andExpect(jsonPath("$[1].noOfPresence").value(0));

        verify(studentService, times(1)).getAllStudents();
    }

    @Test
    public void testPostStudent() throws Exception {
        Student student = new Student();
        student.setName("Alice");
        student.setNoOfPresence(0);

        when(studentService.createStudent(student)).thenReturn(student);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();

        mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON)
                        .content(this.getStudentBodyAsJsonStrHelper(student)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Alice"))
                .andExpect(jsonPath("$.noOfPresence").value(0))
        ;
        verify(studentService, times(1)).createStudent(any());
    }

    // Write similar test methods for other API endpoints

    // Write InvalidRequest test methods for other API endpoints

    private String getStudentBodyAsJsonHelper(Student student) {
        return new Gson().toJson(student);
    }

    private String getStudentBodyAsJsonStrHelper(Student student) {
        return "{\"id\":" + student.getId() + "," +
                "\"name\":\"" + student.getName() + "\"," +
                "\"noOfPresence\": \"" + student.getNoOfPresence() + "\"" +
                " }";
    }


}
