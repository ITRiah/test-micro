package com.lgcns.study_micros.Student;

import com.lgcns.study_micros.Course.Course;
import com.lgcns.study_micros.Course.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vă/students")
public class StudentController {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @GetMapping
    public List<Student> getStudents() {
        return studentRepository.findAllWithCourses();
    }

    @GetMapping("/courses")
    public List<Course> getCourse() {
        return courseRepository.findCourseWithStudentAndCourses();
    }
}