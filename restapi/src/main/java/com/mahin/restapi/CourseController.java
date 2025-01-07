package com.mahin.restapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {
    // /course
    // id, name, author
    @RequestMapping("/courses")
    public List<Course> retrieveAllCourses(){
        return Arrays.asList(
                new Course(1, "Spring", "mm"),
                new Course(2, "Rest API", "mm"),
                new Course(3, "spring Boot", "mm"),
                new Course(4, "spring Boot 3", "mm"),
                new Course(5, "spring Boot 4", "mm")
        );
    }
}
