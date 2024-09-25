package com.kutub.SecondSpringBoot.restcontroller;


import com.kutub.SecondSpringBoot.entity.Student;
import com.kutub.SecondSpringBoot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public List <Student>getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping("/save")
    public void saveStudent(@RequestBody Student s){
        studentService.saveStudent(s);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable int id){
         studentService.deleteStudent(id);
    }

    @PutMapping("/update/{id}")
    public void updateStudent(@RequestBody Student s){
        studentService.updateStudent(s);
    }



}
