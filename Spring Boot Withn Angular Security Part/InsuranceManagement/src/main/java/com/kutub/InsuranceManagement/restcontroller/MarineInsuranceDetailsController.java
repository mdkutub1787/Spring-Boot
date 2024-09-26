package com.kutub.InsuranceManagement.restcontroller;

import com.kutub.InsuranceManagement.entity.MarineInsuranceDetails;
import com.kutub.InsuranceManagement.service.MarineInsuranceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/marine")
@CrossOrigin("*")
public class MarineInsuranceDetailsController {

    @Autowired
    private MarineInsuranceDetailsService marineInsuranceDetailsService;

    @GetMapping("/")
    public List<MarineInsuranceDetails> getAllMarineInsuranceDetails() {
        return marineInsuranceDetailsService.getAllMarineInsuranceDetails();
    }

    // Save new marine insurance details
    @PostMapping("/save")
    public void saveMarineInsuranceDetails(@RequestBody MarineInsuranceDetails md) {
        double exchangeRate = marineInsuranceDetailsService.getExchangeRate().doubleValue();
        md.convertSumInsured(exchangeRate); // Convert sumInsured from dollars to BDT
        marineInsuranceDetailsService.saveMarineInsuranceDetails(md ,exchangeRate);
    }

    // Update existing marine insurance details
    @PutMapping("/update/{id}")
    public void updateMarineInsuranceDetails(@PathVariable long id, @RequestBody MarineInsuranceDetails md) {
        double exchangeRate = marineInsuranceDetailsService.getExchangeRate().doubleValue();
        marineInsuranceDetailsService.updateMarineInsuranceDetails(md, id, exchangeRate); // Ensure update method includes exchangeRate
    }



    @DeleteMapping("/delete/{id}")
    public void deleteMarineInsuranceDetailsById(@PathVariable long id) {
        marineInsuranceDetailsService.deleteMarineInsuranceDetails(id);
    }

    @GetMapping("/{id}")
    public MarineInsuranceDetails getMarineInsuranceDetailsById(@PathVariable("id") long id) {
        return marineInsuranceDetailsService.findById(id);
    }
}
