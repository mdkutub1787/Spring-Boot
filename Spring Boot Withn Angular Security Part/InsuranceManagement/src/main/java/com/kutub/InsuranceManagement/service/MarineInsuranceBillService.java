package com.kutub.InsuranceManagement.service;


import com.kutub.InsuranceManagement.entity.MarineInsuranceBill;
import com.kutub.InsuranceManagement.entity.MarineInsuranceDetails;
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








}
