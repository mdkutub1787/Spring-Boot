package com.kutub.InsuranceManagement.service;

import com.kutub.InsuranceManagement.entity.Policy;
import com.kutub.InsuranceManagement.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;



    public List<Policy> getAllPolicy() {
        return policyRepository.findAll();
    }



    public void savePolicy(Policy p) {
        policyRepository.save(p);
    }


    public Policy findById(int id){
        return  policyRepository.findById(id).get();
    }



    public  void updatePolicy(Policy p, int id){
        policyRepository.save(p);

    }


    public void deletePolicy(int id) {
        policyRepository.deleteById(id);
    }



}
