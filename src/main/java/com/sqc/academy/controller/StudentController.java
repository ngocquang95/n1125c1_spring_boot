package com.sqc.academy.controller;

import com.sqc.academy.Student;
import com.sqc.academy.dto.ApiResponse;
import com.sqc.academy.exception.AppException;
import com.sqc.academy.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(1, "Lương", 2.0),
                    new Student(2, "Thiên", 2.5),
                    new Student(3, "Luân", 2.3)
            )
    );

    //Get list (Search)
    // @RequestMapping(value = "/students", method = RequestMethod.GET)
    @GetMapping
    public List<Student> getStudents() {
        return students;
    }

    // Get by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getById(@PathVariable Integer id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        ApiResponse.<Student>builder()
                                .data(student)
                                .build()
                );
            }
        }

        throw new AppException(ErrorCode.TEACHER_NOT_FOUNT);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> save(@RequestBody Student student) {
        student.setId((int) (Math.random() * 100000000));
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Student>builder()
                        .data(student)
                        .build()
        );
    }
}
