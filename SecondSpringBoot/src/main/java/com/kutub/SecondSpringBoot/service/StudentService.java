package com.kutub.SecondSpringBoot.service;

import com.kutub.SecondSpringBoot.entity.Student;
import com.kutub.SecondSpringBoot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void saveStudent(Student s) {
        studentRepository.save(s);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public Student findById(int id) {
        return studentRepository.findById(id).get();
    }

    public void updateStudent(Student s) {
        studentRepository.save(s);
    }

}
