package com.kutub.FirstSpringBoot.service;

import com.kutub.FirstSpringBoot.entity.Department;
import com.kutub.FirstSpringBoot.entity.Student;
import com.kutub.FirstSpringBoot.repository.DepartmentRepository;
import com.kutub.FirstSpringBoot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public void saveStudent(Student s) {
        Department newDepartment = departmentRepository.findById(s.getDepartment().getId())
                .orElseThrow(
                        () -> new RuntimeException()
                );
        studentRepository.save(s);
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id).get();
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public void updateStudent(Student s) {
        studentRepository.save(s);
    }
}
