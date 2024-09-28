package com.kutub.InsuranceManagement.service;

import com.kutub.InsuranceManagement.entity.MarineInsuranceDetails;
import com.kutub.InsuranceManagement.repository.MarineInsuranceDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class MarineInsuranceDetailsService {

    @Autowired
    private MarineInsuranceDetailsRepo marineInsuranceDetailsRepo;

    @Autowired
    private CurrencyService currencyService;

    // Create or update marine insurance details
    public MarineInsuranceDetails createOrUpdateMarineInsurance(MarineInsuranceDetails marineInsuranceDetails) {
        double exchangeRate = currencyService.fetchExchangeRate().doubleValue(); // Fetch current exchange rate
        marineInsuranceDetails.convertSumInsuredUsd(exchangeRate); // Convert the sum insured
        return marineInsuranceDetailsRepo.save(marineInsuranceDetails);
    }

    // Find marine insurance details by ID
    public MarineInsuranceDetails findById(long id) {
        return marineInsuranceDetailsRepo.findById(id).orElse(null);
    }

    // Get all marine insurance details
    public List<MarineInsuranceDetails> findAll() {
        return marineInsuranceDetailsRepo.findAll();
    }

    // Delete marine insurance details by ID
    public void deleteMarineInsuranceDetails(long id) {
        marineInsuranceDetailsRepo.deleteById(id);
    }
}
