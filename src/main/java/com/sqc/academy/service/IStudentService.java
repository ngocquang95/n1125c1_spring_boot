package com.sqc.academy.service;

import com.sqc.academy.entity.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();

    Student findById(Integer id);

    Student save(Student student);
}
