package com.sqc.academy.dto.student;

import com.sqc.academy.dto.clazz.ClazzRequest;
import com.sqc.academy.entity.Clazz;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentRequest {
    String name;
    double score;
    ClazzRequest clazz;
}
