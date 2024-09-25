package com.kutub.FirstSpringBoot.repository;

import com.kutub.FirstSpringBoot.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository <Faculty,Integer> {
}
