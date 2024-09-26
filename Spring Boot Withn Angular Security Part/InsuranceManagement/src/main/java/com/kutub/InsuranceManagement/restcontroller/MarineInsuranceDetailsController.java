package com.kutub.InsuranceManagement.restcontroller;

import com.kutub.InsuranceManagement.entity.MarineInsuranceDetails;
import com.kutub.InsuranceManagement.service.MarineInsuranceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/marine")
@CrossOrigin("*")
public class MarineInsuranceDetailsController {

    @Autowired
    private MarineInsuranceDetailsService marineInsuranceDetailsService;

    // Get all marine insurance details
    @GetMapping("/")
    public List<MarineInsuranceDetails> getAllMarineInsuranceDetails() {
        return marineInsuranceDetailsService.getAllMarineInsuranceDetails();
    }

    // Save new marine insurance details
    @PostMapping("/save")
    public void saveMarineInsuranceDetails(@RequestBody MarineInsuranceDetails md) {
        marineInsuranceDetailsService.saveMarineInsuranceDetails(md);
    }

    // Update marine insurance details by ID
    @PutMapping("/update/{id}")
    public void updateMarineInsuranceDetails(@PathVariable long id, @RequestBody MarineInsuranceDetails md) {
        MarineInsuranceDetails existingDetails = marineInsuranceDetailsService.findById(id);
        if (existingDetails != null) {
            marineInsuranceDetailsService.saveMarineInsuranceDetails(md);
        }
    }

    // Delete marine insurance details by ID
    @DeleteMapping("/delete/{id}")
    public void deleteMarineInsuranceDetailsById(@PathVariable long id) {
        marineInsuranceDetailsService.deleteMarineInsuranceDetails(id);
    }

    // Get marine insurance detail by ID
    @GetMapping("/{id}")
    public MarineInsuranceDetails getMarineInsuranceDetailsById(@PathVariable("id") long id) {
        return marineInsuranceDetailsService.findById(id);
    }

    // Get exchange rate
    @GetMapping("/exchange-rate")
    public BigDecimal getExchangeRate() {
        return marineInsuranceDetailsService.getExchangeRate();
    }
}
