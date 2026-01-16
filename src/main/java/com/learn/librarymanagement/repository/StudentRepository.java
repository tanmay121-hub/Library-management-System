package com.learn.librarymanagement.repository;

import com.learn.librarymanagement.dto.StudentResponse;
import com.learn.librarymanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    public StudentResponse findByMail(String mail);
}
