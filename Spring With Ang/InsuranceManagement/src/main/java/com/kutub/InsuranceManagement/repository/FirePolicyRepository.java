package com.kutub.InsuranceManagement.repository;

import com.kutub.InsuranceManagement.entity.FirePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirePolicyRepository extends JpaRepository<FirePolicy,Integer> {
}
