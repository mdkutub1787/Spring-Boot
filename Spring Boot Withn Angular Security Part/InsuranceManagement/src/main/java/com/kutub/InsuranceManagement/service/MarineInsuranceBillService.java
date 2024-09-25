package com.kutub.InsuranceManagement.service;


import com.kutub.InsuranceManagement.entity.Bill;
import com.kutub.InsuranceManagement.entity.MarineInsuranceBill;
import com.kutub.InsuranceManagement.entity.MarineInsuranceDetails;
import com.kutub.InsuranceManagement.entity.Policy;
import com.kutub.InsuranceManagement.repository.MarineInsuranceBillRepo;
import com.kutub.InsuranceManagement.repository.MarineInsuranceDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarineInsuranceBillService {

    @Autowired
    private MarineInsuranceBillRepo marineInsuranceBillRepo;

    @Autowired
    private MarineInsuranceDetailsRepo marineInsuranceDetailsRepo  ;


    public List<MarineInsuranceBill> getAllMarineInsuranceBills() {
        return marineInsuranceBillRepo.findAll();
    }


    public MarineInsuranceBill getMarineInsuranceBillById(long id) {
        return marineInsuranceBillRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("MarineInsuranceBill not found with ID: " + id));
    }

    public void updateMarineInsuranceBill(MarineInsuranceBill mb, long id) {
        marineInsuranceBillRepo.save(mb);

    }

    public void deleteMarineInsuranceBill(long id) {
        marineInsuranceBillRepo.deleteById(id);
    }








}
