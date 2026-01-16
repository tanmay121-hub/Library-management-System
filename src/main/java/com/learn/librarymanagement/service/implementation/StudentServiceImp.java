package com.learn.librarymanagement.service.implementation;

import com.learn.librarymanagement.dto.StudentRequest;
import com.learn.librarymanagement.dto.StudentResponse;
import com.learn.librarymanagement.enums.Status;
import com.learn.librarymanagement.model.Card;
import com.learn.librarymanagement.model.Student;
import com.learn.librarymanagement.repository.StudentRepository;
import com.learn.librarymanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    private StudentResponse mapToResponse(Student student) {
        StudentResponse response = new StudentResponse();

        // Map Student Details
        response.setId(student.getStdId());
        response.setName(student.getName());
        response.setAge(student.getAge());
        response.setDepartment(student.getDepartment());
        response.setMobNo(student.getMobNo());
        response.setMail(student.getMail());

        if (student.getCard() != null) {
            response.setCardId(student.getCard().getCardId());
            response.setCardStatus(student.getCard().getCardstatus());
            response.setCardExpiryDate(student.getCard().getExpiryDate());
        }

        return response;
    }

    @Override
    public String addStudent(StudentRequest studentData) throws ParseException {
        Student student = new Student();
        student.setAge(studentData.getAge());
        student.setName(studentData.getName());
        student.setMobNo(studentData.getMobNo());
        student.setDepartment(studentData.getDepartment());

        Card card = new Card();
        card.setCardstatus(Status.ACTIVATED);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(LocalDateTime.now().plusYears(4).toString());
        card.setExpiryDate((date));

        card.setStudent(student);
        student.setCard(card);

        studentRepository.save(student);
        return "Student Added Successfully";
    }

    @Override
    public StudentResponse findStudent(int id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null ) return null;
        return mapToResponse(student);
    }

    @Override
    public List<StudentResponse> findAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponse> responseList = new ArrayList<>();
        for (Student s : students){
            responseList.add(mapToResponse(s));
        }
        return responseList;
    }

    @Override
    public Student updateMobile(String mobile, int id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setMobNo(mobile);
            return studentRepository.save(student);
        }
        return null;
    }

    @Override
    public StudentRequest findByMail(String mail) {
        return studentRepository.findByMail(mail);
    }
}
