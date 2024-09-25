package com.kutub.InsuranceManagement.repository;

import com.kutub.InsuranceManagement.entity.FireBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface FireBillRepository extends JpaRepository<FireBill, Integer> {

    // Query to find FireBills by policyholder name in FirePolicy
    @Query("SELECT fb FROM FireBill fb WHERE fb.firePolicy.policyholder = :policyholder")
    List<FireBill> findFireBillsByPolicyholder(@Param("policyholder") String policyholder);

}
