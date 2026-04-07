package com.sqc.academy.repository.impl;

import com.sqc.academy.entity.Student;
import com.sqc.academy.repository.IStudentRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentRepository implements IStudentRepository {
    public List<Student> findAll() {
        Session session = ConnectionUtil.sessionFactory.openSession();
        List<Student> students = session.createQuery("FROM Student").getResultList(); // HQL
        session.close();
        return students;
    }

    public Student findById(Integer id) {
        Session session = ConnectionUtil.sessionFactory.openSession();

        Student student = (Student) session.createQuery("FROM Student WHERE id = :id")
                .setParameter("id", id)
                .uniqueResult(); // HQL

        session.close();

        return student;
    }

    public Student save(Student student) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(student);
        /*
        id tồn tại: Update
        id k tồn tại: Create
         */
        transaction.commit();
        session.close();

        return student;
    }
}
