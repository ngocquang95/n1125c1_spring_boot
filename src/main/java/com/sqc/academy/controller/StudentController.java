package com.sqc.academy.controller;

import com.sqc.academy.dto.ApiResponse;
import com.sqc.academy.exception.AppException;
import com.sqc.academy.exception.ErrorCode;
import com.sqc.academy.model.Student;
import com.sqc.academy.service.IStudentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/students")
public class StudentController {
    IStudentService studentService;

    //Get list (Search)
    // @RequestMapping(value = "/students", method = RequestMethod.GET)
    @GetMapping
    public List<Student> getStudents() {
        return studentService.findAll();
    }

    // Get by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getById(@PathVariable Integer id) {
        Student student = studentService.findById(id);

        if (student != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    ApiResponse.<Student>builder()
                            .data(student)
                            .build());
        }

        throw new AppException(ErrorCode.TEACHER_NOT_FOUNT);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> save(@RequestBody Student student) {
        studentService.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Student>builder()
                        .data(student)
                        .build()
        );
    }
}
