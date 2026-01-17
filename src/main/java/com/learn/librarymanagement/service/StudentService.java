package com.learn.librarymanagement.service;

import com.learn.librarymanagement.dto.StudentRequest;
import com.learn.librarymanagement.dto.StudentResponse;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface StudentService {
    public String addStudent(StudentRequest student) throws ParseException;

    StudentResponse findStudent(int id);

    List<StudentResponse> findAll();
    StudentResponse updateMobile(String mobile, int id);
    StudentResponse findByMail(String mail);
    String deleteById(int id);
}
