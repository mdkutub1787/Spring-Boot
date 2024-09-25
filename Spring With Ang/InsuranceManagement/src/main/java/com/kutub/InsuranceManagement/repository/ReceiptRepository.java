package com.kutub.InsuranceManagement.repository;


import com.kutub.InsuranceManagement.entity.FireBill;
import com.kutub.InsuranceManagement.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {


    // Query to find Receipt by policyholder name in FirePolicy via FireBill
    @Query("SELECT r FROM Receipt r WHERE r.fireBill.firePolicy.policyholder = :policyholder")
    List<Receipt> findReceiptByPolicyholder(@Param("policyholder") String policyholder);
}


