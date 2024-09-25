package com.kutub.InsuranceManagement.service;

import com.kutub.InsuranceManagement.entity.FireBill;
import com.kutub.InsuranceManagement.entity.FirePolicy;
import com.kutub.InsuranceManagement.repository.FireBillRepository;
import com.kutub.InsuranceManagement.repository.FirePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FireBillService {

    @Autowired
    private FireBillRepository fireBillRepository;

    @Autowired
    private FirePolicyRepository firePolicyRepository ;

    // Get all FireBills
    public List<FireBill> getAllFireBill() {
        return fireBillRepository.findAll();
    }

    // Add a new FireBill
    public void saveFireBill(FireBill d) {
        FirePolicy policy = firePolicyRepository.findById(d.getFirePolicy().getId())
                .orElseThrow(
                        () -> new RuntimeException("User not found " + d.getFirePolicy().getId())
                );
        d.setFirePolicy(policy);
        fireBillRepository.save(d);
    }



    // Get FireBill by id
    public FireBill findById(int id) {
        return fireBillRepository.findById(id).get();
    }


    // Update FireBill by id
    public void updateFireBill(FireBill fb, int id) {
        fireBillRepository.save(fb);

    }


    // Delete FireBill by id
    public void deleteFireBill(int id) {
        fireBillRepository.deleteById(id);
    }


    // Find FireBills by policyholder name
    public List<FireBill> findByPolicyHolderName(String policyholder) {
        return fireBillRepository.findFireBillsByPolicyholder(policyholder);
    }

}


