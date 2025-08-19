package com.lgcns.study_micros.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT DISTINCT s FROM Student s " +
            "LEFT JOIN FETCH s.registrations")
    List<Student> findAllWithCourses();
}
