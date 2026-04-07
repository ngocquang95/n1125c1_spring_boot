package com.sqc.academy.repository;

import com.sqc.academy.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IStudentRepository extends JpaRepository<Student, Integer> {
    // Way 1: Use method name
    List<Student> findByNameContaining(String name);

    // Way 2: Use HQL
//    @Query("""
//            FROM Student WHERE (:name IS NULL OR name LIKE concat('%', :name, '%'))
//                 AND (:fromScore IS NULL OR score >= :fromScore)
//                      AND (:toScore IS NULL OR score <= :toScore )
//            """)
//    List<Student> findByAttr(@Param("name") String name,
//                             @Param("fromScore") Double fromScore,
//                             @Param("toScore") Double toScore);

    // Way 3: Use native Query
    @Query(value = """
            SELECT * FROM student WHERE (:name IS NULL OR name LIKE concat('%', :name, '%'))
               AND (:fromScore IS NULL OR score >= :fromScore)
                    AND (:toScore IS NULL OR score <= :toScore )
            """, nativeQuery = true)
    List<Student> findByAttr(@Param("name") String name,
                             @Param("fromScore") Double fromScore,
                             @Param("toScore") Double toScore);
}
