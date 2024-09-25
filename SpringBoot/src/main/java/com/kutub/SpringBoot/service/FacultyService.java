package com.kutub.SpringBoot.service;

import com.kutub.SpringBoot.entity.Department;
import com.kutub.SpringBoot.entity.Faculty;
import com.kutub.SpringBoot.repository.DepartmentRepository;
import com.kutub.SpringBoot.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;


    public List<Faculty>getAllFaculty(){
        return  facultyRepository.findAll();
    }

    public void saveFaculty(Faculty f){

        facultyRepository.save(f);
    }

    public  Faculty getAllFacultyById(int id){
        return facultyRepository.findById(id).get();
    }

    public  void  deleteFaculty(int id){
        facultyRepository.deleteById(id);
    }

    public void updateFaculty(Faculty f){
        facultyRepository.save(f);
    }
}
