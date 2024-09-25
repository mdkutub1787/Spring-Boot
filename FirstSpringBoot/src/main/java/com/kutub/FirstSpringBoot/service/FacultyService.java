package com.kutub.FirstSpringBoot.service;

import com.kutub.FirstSpringBoot.entity.Faculty;
import com.kutub.FirstSpringBoot.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;

    public void saveFaculty(Faculty f) {
        facultyRepository.save(f);
    }

    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public Faculty findById(int id){
       return facultyRepository.findById(id).get();
    }

    public void deleteFaculty(int id) {
        facultyRepository.deleteById(id);
    }

    public void updateFaculty(Faculty f) {
        facultyRepository.save(f);
    }

}
