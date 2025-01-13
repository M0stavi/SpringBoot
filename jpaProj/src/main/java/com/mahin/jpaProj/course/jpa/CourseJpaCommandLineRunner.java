package com.mahin.jpaProj.course.jpa;


import com.mahin.jpaProj.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class CourseJpaCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    public CourseJpaCommandLineRunner(CourseJpaRepository courseJpaRepository){
        this.courseJpaRepository = courseJpaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        courseJpaRepository.insert(new Course( (long)1, "learn aws", "in28minutes"));
        courseJpaRepository.insert(new Course( (long)2, "learn java", "in28minutes"));
        courseJpaRepository.insert(new Course( (long)3, "learn spring", "in28minutes"));
        courseJpaRepository.deleteByID(2);
        System.out.println(courseJpaRepository.findByID(1));
        System.out.println(courseJpaRepository.findByID(3));
    }
}
