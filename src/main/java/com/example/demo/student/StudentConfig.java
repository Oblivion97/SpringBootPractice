package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class StudentConfig {
    private List<Student> studentList;

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student mahmudul = new Student(
                    "Mahmudul",
                    LocalDate.of(1997, Month.FEBRUARY, 11),
                    "mahmudul@gmail.com"
            );
            Student talia = new Student(
                    "Talia",
                    LocalDate.of(1996, Month.JULY, 21),
                    "talia@gmail.com"
            );
            studentList = new ArrayList<>();
            studentList.add(mahmudul);
            studentList.add(talia);
            repository.saveAll(studentList);
        };
    }
}
