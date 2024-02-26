package org.ols.qaprep.controller;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void testGetAllStudents() throws Exception {
        // Arrange
        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("Alice");
        student1.setPresent(true);

        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Bob");
        student2.setPresent(false);

        when(studentService.getAllStudents()).thenReturn(Arrays.asList(student1, student2));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();

        // Act & Assert
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[0].present").value(true))
                .andExpect(jsonPath("$[1].name").value("Bob"))
                .andExpect(jsonPath("$[1].present").value(false));

        verify(studentService, times(1)).getAllStudents();
    }

    // Write similar test methods for other API endpoints
}
