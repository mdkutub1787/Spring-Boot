package com.kutub.SecondSpringBoot.restcontroller;

import com.kutub.SecondSpringBoot.entity.Department;
import com.kutub.SecondSpringBoot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentRestController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public List<Department> getAllDepartment() {
        return departmentService.getAllDepartment();
    }

    @PostMapping("/save")
    public void saveDepartment(@RequestBody Department d) {
        departmentService.saveDepartment(d);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
    }

    @PutMapping("/update/{id}")
    public void updateDepartment(@RequestBody Department d) {
        departmentService.updateDepartment(d);
    }
}
