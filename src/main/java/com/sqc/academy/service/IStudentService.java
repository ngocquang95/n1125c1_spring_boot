package com.sqc.academy.service;

import com.sqc.academy.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    List<Student> findAll();

    Page<Student> findByAttr(String name, Double fromScore, Double toScore, Pageable pageable);

    Optional<Student> findById(Integer id);

    Student save(Student student);
}
