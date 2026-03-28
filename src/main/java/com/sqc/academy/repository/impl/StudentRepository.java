package com.sqc.academy.repository.impl;

import com.sqc.academy.model.Student;
import com.sqc.academy.repository.IStudentRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentRepository implements IStudentRepository {
    List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(1, "Lương", 2.0),
                    new Student(2, "Thiên", 2.5),
                    new Student(3, "Luân", 2.3)
            )
    );

    public List<Student> findAll() {
        return students;
    }

    public Student findById(Integer id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    public Student save(Student student) {
        student.setId((int) (Math.random() * 100000000));
        students.add(student);

        return student;
    }
}
