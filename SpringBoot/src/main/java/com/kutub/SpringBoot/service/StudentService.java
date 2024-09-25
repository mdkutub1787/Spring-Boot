package com.kutub.SpringBoot.service;

import com.kutub.SpringBoot.entity.Department;
import com.kutub.SpringBoot.entity.Faculty;
import com.kutub.SpringBoot.entity.Student;
import com.kutub.SpringBoot.repository.DepartmentRepository;
import com.kutub.SpringBoot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    public void saveStudent(Student s) {
        Department newDepartment=departmentRepository.findById(s.getDepartment().getId())
                .orElseThrow(
                        ()-> new RuntimeException("user not found"+ s.getDepartment().getId())
                );
        s.setDepartment(newDepartment);
        studentRepository.save(s);
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public void deleteStuden(int id) {
        studentRepository.deleteById(id);
    }

    public Student findStudentById(int id) {
        return studentRepository.findById(id).get();

    }


    public void updateStudent(Student s ,int id){
        studentRepository.save(s);
    }


}
