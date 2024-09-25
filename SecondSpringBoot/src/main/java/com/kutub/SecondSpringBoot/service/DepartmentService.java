package com.kutub.SecondSpringBoot.service;

import com.kutub.SecondSpringBoot.entity.Department;
import com.kutub.SecondSpringBoot.entity.Student;
import com.kutub.SecondSpringBoot.repository.DepartmentRepository;
import com.kutub.SecondSpringBoot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public void saveDepartment(Department d) {
        departmentRepository.save(d);
    }

    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }

    public Department findById(int id) {
        return departmentRepository.findById(id).get();
    }

    public void updateDepartment(Department d) {
        departmentRepository.save(d);
    }
}
