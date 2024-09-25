package com.kutub.SpringBoot.restcontroller;


import com.kutub.SpringBoot.entity.Faculty;
import com.kutub.SpringBoot.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
public class FacultyRestController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping("/")
    public List<Faculty> getAllFaculty() {
        return facultyService.getAllFaculty();
    }

    @PostMapping("/save")
    public void saveFaculty(@RequestBody Faculty f) {
        facultyService.saveFaculty(f);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteFaculty(@PathVariable int id) {
        facultyService.deleteFaculty(id);
    }

    @PutMapping("/update/{id}")
    public void updateDepartment(@RequestBody Faculty f) {
        facultyService.updateFaculty(f);
    }
}
