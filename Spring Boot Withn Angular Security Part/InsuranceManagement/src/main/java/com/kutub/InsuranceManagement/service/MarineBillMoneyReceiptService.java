package com.kutub.InsuranceManagement.service;

import com.kutub.InsuranceManagement.entity.MarineBillMoneyReceipt;
import com.kutub.InsuranceManagement.repository.MarineBillMoneyReceiptRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarineBillMoneyReceiptService {

    @Autowired
    private MarineBillMoneyReceiptRepo marineBillMoneyReceiptRepo ;



    public List<MarineBillMoneyReceipt> getAllMarineBillMoneyReceipt() {
        return marineBillMoneyReceiptRepo.findAll();
    }



    public void saveMarineBillMoneyReceipt(MarineBillMoneyReceipt receipt) {
        marineBillMoneyReceiptRepo.save(receipt);
    }

    public MarineBillMoneyReceipt findById(long id) {
        return marineBillMoneyReceiptRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MarineBillMoneyReceipt not found with id: " + id));
    }




    public  void updateMarineBillMoneyReceipt(MarineBillMoneyReceipt receipt, long id){
        marineBillMoneyReceiptRepo.save(receipt);

    }


    public void deleteMarineBillMoneyReceipt(long id) {
        marineBillMoneyReceiptRepo.deleteById(id);
    }
}
