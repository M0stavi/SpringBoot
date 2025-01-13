package com.mahin.jpaProj.course.springdatajpa;

import com.mahin.jpaProj.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseSpringDataJpaCommandLineRunner implements CommandLineRunner {
    @Autowired
    private CourseSpringDataJpaRepository courseSpringDataJpaRepository;


    @Override
    public void run(String... args) throws Exception {
        courseSpringDataJpaRepository.save(new Course((long)1, "learn aws", "in28minutes"));
        courseSpringDataJpaRepository.save(new Course((long)2, "learn java", "in28minutes"));
        courseSpringDataJpaRepository.save(new Course((long)3, "learn spring", "in28minutes"));
        courseSpringDataJpaRepository.deleteById((long)2);
        System.out.println(courseSpringDataJpaRepository.findAll());
        System.out.println(courseSpringDataJpaRepository.findByName("learn aws"));
        System.out.println(courseSpringDataJpaRepository.findByAuthor("in28minutes"));
    }
}
