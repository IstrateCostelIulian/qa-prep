package org.ols.qaprep.repository;

import org.ols.qaprep.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
