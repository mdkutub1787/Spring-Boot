package com.kutub.SpringBoot.restcontroller;


import com.kutub.SpringBoot.entity.Student;
import com.kutub.SpringBoot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentRestController {
    @Autowired
    private StudentService studentService;


    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> student = studentService.getAllStudent();
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveStudent(@RequestBody Student s) {
        studentService.saveStudent(s);
        return new ResponseEntity<>("Student Created", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        studentService.deleteStuden(id);
        return new ResponseEntity<>("Student Deleted", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStudent(@RequestBody Student s) {
        studentService.saveStudent(s);
        return new ResponseEntity<>("Student Updated", HttpStatus.OK);
    }

}
