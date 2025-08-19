package com.lgcns.study_micros.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("SELECT c FROM Course c " +
            "JOIN FETCH c.student s ")
    List<Course> findCourseWithStudentAndCourses();
}