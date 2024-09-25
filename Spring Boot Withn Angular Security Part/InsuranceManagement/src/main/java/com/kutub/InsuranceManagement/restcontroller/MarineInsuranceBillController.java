package com.kutub.InsuranceManagement.restcontroller;



import com.kutub.InsuranceManagement.entity.MarineInsuranceBill;
import com.kutub.InsuranceManagement.service.MarineInsuranceBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/marinebill")
@CrossOrigin("*")
public class MarineInsuranceBillController {

    @Autowired
    private MarineInsuranceBillService marineInsuranceBillService ;


    @GetMapping("/")
    public List<MarineInsuranceBill> getAllBills() {
        return marineInsuranceBillService.getAllMarineInsuranceBills();
    }
}
