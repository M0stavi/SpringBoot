package com.mahin.jpaProj.course.jdbc;

import com.mahin.jpaProj.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository {

    @Autowired
    private JdbcTemplate springJdbcTemplate;

    public CourseJdbcRepository(JdbcTemplate springJdbcTemplate){
        this.springJdbcTemplate = springJdbcTemplate;
    }

    public static String INSERT_QUERY =
            """
                insert into course (id,name,author)
                values(?, ?, ?);
            """;

    public static String DELETE_QUERY =
            """
                delete from course where id=?;
            """;

    public static String SELECT_QUERY =
            """
                select *  from course where id=?;
            """;

    public void insert(Course course){
        springJdbcTemplate.update(INSERT_QUERY, course.getId(), course.getName(), course.getAuthor());
    }
    public void delete(long id){
        springJdbcTemplate.update(DELETE_QUERY, id);
    }
    public Course selectByID(long id){
        return springJdbcTemplate.queryForObject(SELECT_QUERY, new BeanPropertyRowMapper<>(Course.class), id);
    }

}
