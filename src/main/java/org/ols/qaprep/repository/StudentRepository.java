package org.ols.qaprep.repository;

import org.ols.qaprep.entity.Student;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findStudentByName(String name);
}
