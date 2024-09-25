package com.kutub.SpringBoot.controller;

import com.kutub.SpringBoot.entity.Student;
import com.kutub.SpringBoot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;


    @RequestMapping("/savestudentform")
    public String saveStudent(Model m) {
        m.addAttribute("student", new Student());
        m.addAttribute("title", "Add New Student");
        return "savestudentform";
    }

    @PostMapping("/savestudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);

        return "redirect:/showAllStudent";
    }

    @GetMapping("/showAllStudent")
    public String showAllStudent(Model m) {
        List<Student> sList = studentService.getAllStudent();
        m.addAttribute("sList", sList);
        return "showAllStudent";
    }

    @RequestMapping("deletestudent/{id}")
    public String deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStuden(id);
        return "redirect:/showAllStudent";
    }

    @RequestMapping("editstudent/{id}")
    public String editStudent(@PathVariable("id") int id, Model m) {
        Student s = studentService.findStudentById(id);
        m.addAttribute("student", s);
        return "savestudentform";
    }

}
