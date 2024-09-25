package com.kutub.FirstSpringBoot.service;

import com.kutub.FirstSpringBoot.entity.Department;
import com.kutub.FirstSpringBoot.entity.Faculty;
import com.kutub.FirstSpringBoot.repository.DepartmentRepository;
import com.kutub.FirstSpringBoot.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private FacultyRepository facultyRepository;

    public List<Department> getAlDepartment() {
        return departmentRepository.findAll();
    }

    public void saveDepartment(Department d) {
        Faculty newFaculty = facultyRepository.findById(d.getFaculty().getId())
                .orElseThrow(
                        () -> new RuntimeException("User not found" + d.getFaculty().getId())
                );

        departmentRepository.save(d);
    }

    public Department findDepartment(int id) {
        return departmentRepository.findById(id).get();
    }

    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }

    public void updateDepartment(Department d) {
        departmentRepository.save(d);
    }

}
