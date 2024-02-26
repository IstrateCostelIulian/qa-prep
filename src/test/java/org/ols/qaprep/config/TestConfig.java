package org.ols.qaprep.config;

import org.ols.qaprep.service.StudentService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public StudentService studentService() {
        return new StudentService(); // You may need to provide any necessary dependencies here
    }
}
