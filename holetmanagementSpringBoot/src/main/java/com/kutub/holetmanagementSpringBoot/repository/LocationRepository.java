package com.kutub.holetmanagementSpringBoot.repository;


import com.kutub.holetmanagementSpringBoot.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location,Integer> {
}
