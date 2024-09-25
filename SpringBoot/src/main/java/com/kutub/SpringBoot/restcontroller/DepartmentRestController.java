package com.kutub.SpringBoot.restcontroller;

import com.kutub.SpringBoot.entity.Department;
import com.kutub.SpringBoot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dep")
public class DepartmentRestController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public List<Department>getAllDepartment(){
        return departmentService.getAllDepartment();
    }

    @PostMapping("/save")
    public void saveDepartment(@RequestBody Department d){
         departmentService.saveDepartment(d);
    }


    @DeleteMapping("/delete/{id}")
    public  void deleteDepartment(@PathVariable int id){
        departmentService.deleteDepartmet(id);
    }

    @PutMapping("/update/{id}")
    public  void  updateDepartment(@RequestBody Department d){
        departmentService.updateDepartment(d);
    }
}
