package com.sqc.academy.controller;

import com.sqc.academy.dto.ApiResponse;
import com.sqc.academy.dto.PageResponse;
import com.sqc.academy.entity.Student;
import com.sqc.academy.exception.AppException;
import com.sqc.academy.exception.ErrorCode;
import com.sqc.academy.service.IStudentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public ApiResponse<?> getStudents(String name,
                                     Double fromScore,
                                     Double toScore,
                                     @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) { // Tìm theo tên
        return ApiResponse.builder()
                .data(new PageResponse<>(studentService.findByAttr(name, fromScore, toScore, pageable)))
                .build();
    }

    // Get by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getById(@PathVariable Integer id) {
        Student student = studentService.findById(id).orElseThrow(() -> new AppException(ErrorCode.TEACHER_NOT_FOUNT));

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Student>builder()
                        .data(student)
                        .build());
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
