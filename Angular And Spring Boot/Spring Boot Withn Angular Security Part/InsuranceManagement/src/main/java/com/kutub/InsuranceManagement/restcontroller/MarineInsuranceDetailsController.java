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
public class MarineInsuranceDetailsController {

    @Autowired
    private MarineInsuranceDetailsService marineInsuranceDetailsService;

    @PostMapping("/save")
    public ResponseEntity<MarineInsuranceDetails> createMarineInsurance(@RequestBody MarineInsuranceDetails marineInsuranceDetails) {
        MarineInsuranceDetails createdMarineInsurance = marineInsuranceDetailsService.createOrUpdateMarineInsurance(marineInsuranceDetails);
        return new ResponseEntity<>(createdMarineInsurance, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MarineInsuranceDetails> updateMarineInsurance(@PathVariable long id, @RequestBody MarineInsuranceDetails marineInsuranceDetails) {
        marineInsuranceDetails.setId(id);
        MarineInsuranceDetails updatedMarineInsurance = marineInsuranceDetailsService.createOrUpdateMarineInsurance(marineInsuranceDetails);
        return new ResponseEntity<>(updatedMarineInsurance, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarineInsuranceDetails> getMarineInsurance(@PathVariable long id) {
        MarineInsuranceDetails marineInsuranceDetails = marineInsuranceDetailsService.findById(id);
        if (marineInsuranceDetails != null) {
            return new ResponseEntity<>(marineInsuranceDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public List<MarineInsuranceDetails> getAllPolicies() {
        return marineInsuranceDetailsService.findAll();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteMarineInsurance(@PathVariable long id) {
        if (marineInsuranceDetailsService.findById(id) != null) {
            marineInsuranceDetailsService.deleteMarineInsuranceDetails(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
