package com.sqc.academy.controller;

import com.sqc.academy.dto.ApiResponse;
import com.sqc.academy.dto.PageResponse;
import com.sqc.academy.dto.clazz.ClazzResponse;
import com.sqc.academy.dto.student.StudentRequest;
import com.sqc.academy.dto.student.StudentResponse;
import com.sqc.academy.entity.Clazz;
import com.sqc.academy.entity.Student;
import com.sqc.academy.exception.AppException;
import com.sqc.academy.exception.ErrorCode;
import com.sqc.academy.mapper.IStudentMapper;
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
    IStudentMapper studentMapper;

    //Get list (Search)
    // @RequestMapping(value = "/students", method = RequestMethod.GET)
    @GetMapping
    public ApiResponse<?> getStudents(String name,
                                     Double fromScore,
                                     Double toScore,
                                     @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) { // Tìm theo tên
        return ApiResponse.builder()
                .data(new PageResponse<>(studentService.findByAttr(name, fromScore, toScore, pageable)
                        .map(studentMapper::studentToStudentResponse)))
                .build();
    }

    // Get by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> getById(@PathVariable Integer id) {
        Student student = studentService.findById(id).orElseThrow(() -> new AppException(ErrorCode.TEACHER_NOT_FOUNT));

        StudentResponse studentResponse = studentMapper.studentToStudentResponse(student);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<StudentResponse>builder()
                        .data(studentResponse)
                        .build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponse>> save(@RequestBody StudentRequest studentRequest) { // Bước 1: Nhận vào StudentRequest
        /*
       Bước 1: Nhận vào StudentRequest
       Bước 2: Chuyển StudentRequest ->  Student
       Bước 3: Lưu Student
       Bước 4: Chuyển Student -> StudentResponse
       Bước 5: return StudentResponse
         */

       // Bước 2: Chuyển StudentRequest ->  Student
        Student student = studentMapper.studentRequestToStudent(studentRequest);

        //  Bước 3: Lưu Student
        studentService.save(student);

        //  Bước 4: Chuyển Student -> StudentResponse
        StudentResponse studentResponse = studentMapper.studentToStudentResponse(student);

        // Bước 5: return StudentResponse
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<StudentResponse>builder()
                        .data(studentResponse)
                        .build()
        );
    }
}
