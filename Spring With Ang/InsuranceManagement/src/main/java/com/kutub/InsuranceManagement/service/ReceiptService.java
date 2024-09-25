package com.kutub.InsuranceManagement.service;

import com.kutub.InsuranceManagement.entity.FireBill;
import com.kutub.InsuranceManagement.entity.FirePolicy;
import com.kutub.InsuranceManagement.entity.Receipt;
import com.kutub.InsuranceManagement.repository.FireBillRepository;
import com.kutub.InsuranceManagement.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private FireBillRepository fireBillRepository;

    // Get all FireBills
    public List<Receipt> getAllReceipt() {
        return receiptRepository.findAll();
    }

    // Add a new FireBill
    public void saveReceipt(Receipt r) {
        FireBill bill = fireBillRepository.findById(r.getFireBill().getId())
                .orElseThrow(
                        () -> new RuntimeException("User not found " + r.getFireBill().getId())
                );
        r.setFireBill(bill);
        receiptRepository.save(r);
    }

    // Get FireBill by id
    public Receipt findById(int id) {
        return receiptRepository.findById(id).get();
    }


    // Update FireBill by id
    public void updateReceipt(Receipt r, int id) {
        receiptRepository.save(r);

    }


    // Delete FireBill by id
    public void deleteReceipt(int id) {
        receiptRepository.deleteById(id);
    }

    // Find FireBills by policyholder name
    public List<Receipt> findByPolicyHolderName(String policyholder) {
        return receiptRepository.findReceiptByPolicyholder(policyholder);
    }
}
