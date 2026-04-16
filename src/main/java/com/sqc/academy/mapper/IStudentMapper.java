package com.sqc.academy.mapper;

import com.sqc.academy.dto.student.StudentRequest;
import com.sqc.academy.dto.student.StudentResponse;
import com.sqc.academy.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IStudentMapper {
    Student studentRequestToStudent(StudentRequest studentRequest);

    @Mapping(source = "clazz.name", target = "clazzName")
    StudentResponse studentToStudentResponse(Student student);
}
