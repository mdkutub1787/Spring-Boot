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

    // Create or update marine insurance details
    public MarineInsuranceDetails createOrUpdateMarineInsurance(MarineInsuranceDetails marineInsuranceDetails) {
        double exchangeRate = getExchangeRate().doubleValue(); // Fetch current exchange rate
        marineInsuranceDetails.convertSumInsuredUsd(exchangeRate); // Convert the sum insured
        return marineInsuranceDetailsRepo.save(marineInsuranceDetails);
    }

    // Find marine insurance details by ID
    public MarineInsuranceDetails findById(long id) {
        return marineInsuranceDetailsRepo.findById(id).orElse(null);
    }

    // Retrieve all marine insurance details
    public List<MarineInsuranceDetails> findAll() {
        return marineInsuranceDetailsRepo.findAll();
    }

    // Delete marine insurance details by ID
    public void deleteMarineInsuranceDetails(long id) {
        marineInsuranceDetailsRepo.deleteById(id);
    }

    // Get the current exchange rate from USD to BDT
    public BigDecimal getExchangeRate() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.exchangeratesapi.io/latest?base=USD&symbols=BDT";

        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            if (response != null && response.containsKey("rates")) {
                Map<String, BigDecimal> rates = (Map<String, BigDecimal>) response.get("rates");
                return rates.get("BDT");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BigDecimal.ONE; // Default to 1 if there is an issue
    }
}
