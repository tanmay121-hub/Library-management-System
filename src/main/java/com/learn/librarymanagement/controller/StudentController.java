package com.learn.librarymanagement.controller;

import com.learn.librarymanagement.dto.StudentRequest;
import com.learn.librarymanagement.model.Student;
import com.learn.librarymanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/student/add")
    public ResponseEntity<String> addStudent(@RequestBody StudentRequest student) throws ParseException {
        String last = studentService.addStudent(student);
        return new ResponseEntity<>(last, HttpStatus.CREATED);
    }


    @GetMapping("/student/getAll")
    public ResponseEntity<List<Student>> getAll (@RequestBody Student student) throws ParseException{
        List<Student> students = studentService.findAll();

        if (students.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(students,HttpStatus.OK);
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id){
        Student student = studentService.findStudent(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }


    @PostMapping("/students/update")
    public ResponseEntity<String> updateMobile(@RequestParam String mobile, @RequestParam int id){
        Student updated = studentService.updateMobile(mobile,id);
        if (updated != null) {
            return new ResponseEntity<>("Mobile Number Updated Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Student Not Found", HttpStatus.NOT_FOUND);
        }
    }


}
