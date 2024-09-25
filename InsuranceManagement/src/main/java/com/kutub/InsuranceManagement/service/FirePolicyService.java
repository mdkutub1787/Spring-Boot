package com.kutub.InsuranceManagement.service;

import com.kutub.InsuranceManagement.entity.FirePolicy;
import com.kutub.InsuranceManagement.repository.FirePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FirePolicyService {

    @Autowired
    private FirePolicyRepository firePolicyRepository;


    // Get all FirePolicies
    public List<FirePolicy> getAllFirePolicy() {
        return firePolicyRepository.findAll();
    }


    // Add a new FirePolicy
    public void saveFirePolicy(FirePolicy fp) {
         firePolicyRepository.save(fp);
    }

    // Find a FirePolicy entity by its ID
    public  FirePolicy findById(int id){

        return  firePolicyRepository.findById(id).get();
    }

    // Update FirePolicy by id
    public  void updateFirePolicy(FirePolicy fp, int id){
        firePolicyRepository.save(fp);

    }

    // Delete FirePolicy by id
    public void deleteFirePolicy(int id) {
        firePolicyRepository.deleteById(id);
    }

}
