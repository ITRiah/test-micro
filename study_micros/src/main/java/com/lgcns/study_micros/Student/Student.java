package com.lgcns.study_micros.Student;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lgcns.study_micros.Course.Course;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Course> registrations = new ArrayList<>();
}
