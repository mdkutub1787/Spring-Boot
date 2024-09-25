package com.kutub.FirstSpringBoot.resrcontroller;

import com.kutub.FirstSpringBoot.entity.Student;
import com.kutub.FirstSpringBoot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public List<Student> getAllStudent(){
        return studentService.getAllStudent();
    }

    @PostMapping("/save")
    public void  saveStudent(@RequestBody Student s){
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
