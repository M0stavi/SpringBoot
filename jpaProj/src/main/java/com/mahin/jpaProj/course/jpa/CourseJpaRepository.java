package com.mahin.jpaProj.course.jpa;

import com.mahin.jpaProj.course.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CourseJpaRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public CourseJpaRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    public void insert(Course course){
        entityManager.merge(course);
    }

    public Course findByID(long id){
        return entityManager.find(Course.class, id);
    }


    public void deleteByID(long id){
        Course delete = entityManager.find(Course.class, id);
        entityManager.remove(delete);
    }
}
