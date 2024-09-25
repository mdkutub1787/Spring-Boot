package com.kutub.SecondSpringBoot.repository;

import com.kutub.SecondSpringBoot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student ,Integer> {
}
