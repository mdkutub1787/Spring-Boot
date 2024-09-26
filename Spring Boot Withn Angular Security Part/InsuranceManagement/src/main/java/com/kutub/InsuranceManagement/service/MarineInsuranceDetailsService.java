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

    public void saveMarineInsuranceDetails(MarineInsuranceDetails md, double exchangeRate) {
        // Convert sumInsured from dollars to BDT
        md.setSumInsured(md.getSumInsured() * exchangeRate);
        marineInsuranceDetailsRepo.save(md);
    }


    // Update existing marine insurance details
    public void updateMarineInsuranceDetails(MarineInsuranceDetails md, long id, double exchangeRate) {
        MarineInsuranceDetails existingDetails = findById(id);
        if (existingDetails != null) {
            // Update fields
            existingDetails.setDate(md.getDate());
            existingDetails.setBankName(md.getBankName());
            existingDetails.setPolicyholder(md.getPolicyholder());
            existingDetails.setAddress(md.getAddress());
            existingDetails.setVoyageFrom(md.getVoyageFrom());
            existingDetails.setVoyageTo(md.getVoyageTo());
            existingDetails.setVia(md.getVia());
            existingDetails.setStockItem(md.getStockItem());
            existingDetails.setCoverage(md.getCoverage());

            // Convert sumInsured from dollars to BDT if it has changed
            if (existingDetails.getSumInsured() != md.getSumInsured()) {
                existingDetails.setSumInsured(md.getSumInsured() * exchangeRate);
            }

            marineInsuranceDetailsRepo.save(existingDetails);
        }
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

        try {
            var response = restTemplate.getForObject(url, Map.class);
            if (response != null && response.containsKey("rates")) {
                var rates = (Map<String, BigDecimal>) response.get("rates");
                return rates.get("BDT");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return BigDecimal.ONE; // Default to 1 if exchange rate fetch fails
    }
}
