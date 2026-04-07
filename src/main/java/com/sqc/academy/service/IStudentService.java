package com.sqc.academy.service;

import com.sqc.academy.entity.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    List<Student> findAll();

    List<Student> findByAttr(String name, Double fromScore, Double toScore);

    Optional<Student> findById(Integer id);

    Student save(Student student);
}
