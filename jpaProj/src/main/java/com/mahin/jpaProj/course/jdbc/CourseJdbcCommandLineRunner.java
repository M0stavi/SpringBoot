package com.mahin.jpaProj.course.jdbc;

import com.mahin.jpaProj.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class CourseJdbcCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CourseJdbcRepository jdbcRepository;

    private CourseJdbcCommandLineRunner(CourseJdbcRepository jdbcRepository){
        this.jdbcRepository = jdbcRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        jdbcRepository.insert(new Course((long)1, "learn aws", "in28minutes"));
        jdbcRepository.insert(new Course((long)2, "learn java", "in28minutes"));
        jdbcRepository.insert(new Course((long)3, "learn spring", "in28minutes"));
        jdbcRepository.delete((long) 2);
        System.out.println(jdbcRepository.selectByID((long) 1));
        System.out.println(jdbcRepository.selectByID((long) 3));

    }
}
