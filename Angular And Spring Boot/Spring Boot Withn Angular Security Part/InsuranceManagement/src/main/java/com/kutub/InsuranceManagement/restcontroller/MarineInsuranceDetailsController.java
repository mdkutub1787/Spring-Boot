package com.kutub.InsuranceManagement.restcontroller;

import com.kutub.InsuranceManagement.entity.MarineInsuranceDetails;
import com.kutub.InsuranceManagement.service.MarineInsuranceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/marine")
@CrossOrigin("*")
public class MarineInsuranceDetailsController {

    @Autowired
    private MarineInsuranceDetailsService marineInsuranceDetailsService;

    // Save new Marine Insurance details
    @PostMapping("/save")
    public ResponseEntity<MarineInsuranceDetails> createMarineInsurance(@RequestBody MarineInsuranceDetails marineInsuranceDetails) {
        MarineInsuranceDetails createdMarineInsurance = marineInsuranceDetailsService.createOrUpdateMarineInsurance(marineInsuranceDetails);
        return new ResponseEntity<>(createdMarineInsurance, HttpStatus.CREATED);
    }

    // Update existing Marine Insurance details by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<MarineInsuranceDetails> updateMarineInsurance(@PathVariable long id, @RequestBody MarineInsuranceDetails marineInsuranceDetails) {
        marineInsuranceDetails.setId(id);
        MarineInsuranceDetails updatedMarineInsurance = marineInsuranceDetailsService.createOrUpdateMarineInsurance(marineInsuranceDetails);
        return new ResponseEntity<>(updatedMarineInsurance, HttpStatus.OK);
    }

    // Get all Marine Insurance details
    @GetMapping("/")
    public ResponseEntity<List<MarineInsuranceDetails>> getAllMarineInsuranceDetails() {
        List<MarineInsuranceDetails> marineInsuranceDetailsList = marineInsuranceDetailsService.findAll();
        return new ResponseEntity<>(marineInsuranceDetailsList, HttpStatus.OK);
    }

    // Get Marine Insurance details by ID
    @GetMapping("/{id}")
    public ResponseEntity<MarineInsuranceDetails> getMarineInsuranceById(@PathVariable long id) {
        MarineInsuranceDetails marineInsuranceDetails = marineInsuranceDetailsService.findById(id);
        if (marineInsuranceDetails != null) {
            return new ResponseEntity<>(marineInsuranceDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Marine Insurance details by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMarineInsurance(@PathVariable long id) {
        try {
            marineInsuranceDetailsService.deleteMarineInsuranceDetails(id);
            return new ResponseEntity<>("Marine Insurance Details deleted successfully.", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
