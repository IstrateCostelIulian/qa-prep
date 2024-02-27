package org.ols.qaprep.integration.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.ols.qaprep.config.TestConfig;
import org.ols.qaprep.entity.Student;
import org.ols.qaprep.repository.StudentRepository;
import org.ols.qaprep.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@SpringJUnitConfig(TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentServiceIntegrationTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        Student student1 = new Student();
        student1.setName("Alice");
        student1.setPresent(1);

        Student student2 = new Student();
        student2.setName("Bob");
        student2.setPresent(2);

        studentRepository.save(student1);
        studentRepository.save(student2);
    }

    @AfterEach
    public void tearDown() {
        studentRepository.deleteAll();
    }

    @Test
    public void testGetAllStudents() {
        // Act
        List<Student> students = studentService.getAllStudents();

        // Assert
        assertEquals(2, students.size());
    }

    @Test
    public void testGetStudentById() {
        // Arrange
        Long id = studentRepository.findAll().get(0).getId();

        // Act
        Student student = studentService.getStudentById(id).orElse(null);

        // Assert
        assertTrue(student != null && student.getId() == id);
    }

    // Write similar test methods for other CRUD operations
}
