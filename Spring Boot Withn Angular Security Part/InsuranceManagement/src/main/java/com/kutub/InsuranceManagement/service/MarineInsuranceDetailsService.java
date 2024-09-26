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

    // Retrieve all marine insurance details
    public List<MarineInsuranceDetails> getAllMarineInsuranceDetails() {
        return marineInsuranceDetailsRepo.findAll();
    }

    // Save or update marine insurance details
    public void saveMarineInsuranceDetails(MarineInsuranceDetails md) {
        marineInsuranceDetailsRepo.save(md);
    }

    // Find marine insurance details by ID
    public MarineInsuranceDetails findById(long id) {
        return marineInsuranceDetailsRepo.findById(id).orElse(null);
    }

    // Delete marine insurance details by ID
    public void deleteMarineInsuranceDetails(long id) {
        marineInsuranceDetailsRepo.deleteById(id);
    }

    // Fetch exchange rate from a third-party API (BDT to USD)
    public BigDecimal getExchangeRate() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.exchangeratesapi.io/latest?base=USD&symbols=BDT";

        // Call external API to fetch the exchange rate
        try {
            var response = restTemplate.getForObject(url, Map.class);
            if (response != null && response.containsKey("rates")) {
                var rates = (Map<String, BigDecimal>) response.get("rates");
                return rates.get("BDT");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Default exchange rate in case of error
        return BigDecimal.ONE;
    }
}
