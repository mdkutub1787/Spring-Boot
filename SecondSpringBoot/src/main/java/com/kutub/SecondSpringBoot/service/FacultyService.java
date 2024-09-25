package com.kutub.SecondSpringBoot.service;

import com.kutub.SecondSpringBoot.entity.Department;
import com.kutub.SecondSpringBoot.entity.Faculty;
import com.kutub.SecondSpringBoot.repository.DepartmentRepository;
import com.kutub.SecondSpringBoot.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public void saveFaculty(Faculty f) {
        Department newDepartment = departmentRepository.findById(f.getDepartment()
                .getId()).get();
        f.setDepartment(newDepartment);
        facultyRepository.save(f);
    }

    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public Faculty findFacultyById(int id) {
        return facultyRepository.findById(id).get();
    }

    public void deleteFaculty(int id) {
        facultyRepository.deleteById(id);
    }

    public void updateFaculty(Faculty f) {
        facultyRepository.save(f);
    }
}
