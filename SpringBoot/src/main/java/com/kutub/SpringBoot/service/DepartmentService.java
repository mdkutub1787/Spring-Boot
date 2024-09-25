package com.kutub.SpringBoot.service;

import com.kutub.SpringBoot.entity.Department;
import com.kutub.SpringBoot.entity.Faculty;
import com.kutub.SpringBoot.repository.DepartmentRepository;
import com.kutub.SpringBoot.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private FacultyRepository facultyRepository;

    public void saveDepartment(Department d) {
        Faculty newFaculty=facultyRepository.findById(d.getFaculty().getId())
                        .orElseThrow(
                                ()-> new RuntimeException("user not found"+ d.getFaculty().getId())
                        );
        d.setFaculty(newFaculty);
        departmentRepository.save(d);
    }

    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    public void deleteDepartmet(int id) {
        departmentRepository.deleteById(id);
    }

    public Department finDepartmentById(int id) {
        return departmentRepository.findById(id).get();
    }

    public void updateDepartment(Department d){
        departmentRepository.save(d);
    }
}
