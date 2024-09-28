package com.kutub.InsuranceManagement.service;

import com.kutub.InsuranceManagement.entity.MarineInsuranceDetails;
import com.kutub.InsuranceManagement.repository.MarineInsuranceDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MarineInsuranceDetailsService {

    @Autowired
    private MarineInsuranceDetailsRepo marineInsuranceDetailsRepo;

    @Autowired
    private CurrencyService currencyService;

    public MarineInsuranceDetails createOrUpdateMarineInsurance(MarineInsuranceDetails marineInsuranceDetails) {
        double exchangeRate = currencyService.fetchExchangeRate().doubleValue(); // Fetch current exchange rate
        System.out.println("Current USD to BDT Exchange Rate: " + exchangeRate); // Print current rate
        marineInsuranceDetails.convertSumInsuredUsd(exchangeRate); // Convert the sum insured
        return marineInsuranceDetailsRepo.save(marineInsuranceDetails);
    }

    public MarineInsuranceDetails findById(long id) {
        return marineInsuranceDetailsRepo.findById(id).orElse(null);
    }

    public List<MarineInsuranceDetails> findAll() {
        return marineInsuranceDetailsRepo.findAll();
    }

    public void deleteMarineInsuranceDetails(long id) {
        marineInsuranceDetailsRepo.deleteById(id);
    }
}
