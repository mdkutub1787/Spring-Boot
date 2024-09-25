package com.kutub.SpringBoot.repository;

import com.kutub.SpringBoot.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository  extends JpaRepository<Faculty , Integer> {
}
