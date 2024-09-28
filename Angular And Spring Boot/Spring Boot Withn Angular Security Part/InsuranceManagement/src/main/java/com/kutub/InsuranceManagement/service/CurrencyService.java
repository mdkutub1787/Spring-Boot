package com.kutub.InsuranceManagement.service;




import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class CurrencyService {

    private static final String EXCHANGE_RATE_API_URL = "https://api.exchangeratesapi.io/latest?base=USD&symbols=BDT";

    public BigDecimal fetchExchangeRate() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            Map<String, Object> response = restTemplate.getForObject(EXCHANGE_RATE_API_URL, Map.class);
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
