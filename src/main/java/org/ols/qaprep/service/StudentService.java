package org.ols.qaprep.service;

import org.ols.qaprep.entity.Student;
import org.ols.qaprep.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student) {
        Optional<Student> optStudent = studentRepository.findStudentByName(student.getName());
        if (optStudent.isPresent()) {

        }
        Student result = studentRepository.save(student);
        System.out.println("Service : " + result);
        return result;
    }

    public void updateStudent(Long id, Student student) {
        student.setId(id);
        student.setNoOfPresence(updatePresence(student.getNoOfPresence()));
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    private Integer updatePresence(Integer value) {
        return value + 2;
    }
}
