package com.kutub.InsuranceManagement.restcontroller;

import com.kutub.InsuranceManagement.entity.MarineBillMoneyReceipt;
import com.kutub.InsuranceManagement.service.MarineBillMoneyReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/marinemoneyreceipt")
@CrossOrigin("*")
public class MarineBillMoneyReceiptController {

    private final MarineBillMoneyReceiptService marineBillMoneyReceiptService;

    // Constructor-based Dependency Injection
    @Autowired
    public MarineBillMoneyReceiptController(MarineBillMoneyReceiptService marineBillMoneyReceiptService) {
        this.marineBillMoneyReceiptService = marineBillMoneyReceiptService;
    }

    // Get all receipts
    @GetMapping("/")
    public ResponseEntity<List<MarineBillMoneyReceipt>> getAllMarineBillMoneyReceipt() {
        List<MarineBillMoneyReceipt> receipts = marineBillMoneyReceiptService.getAllMarineBillMoneyReceipt();
        return ResponseEntity.ok(receipts);
    }

    // Create a new receipt
    @PostMapping("/save")
    public ResponseEntity<MarineBillMoneyReceipt> saveMarineBillMoneyReceipt(@RequestBody MarineBillMoneyReceipt mr) {
        MarineBillMoneyReceipt savedReceipt = marineBillMoneyReceiptService.saveMarineBillMoneyReceipt(mr);
        return new ResponseEntity<>(savedReceipt, HttpStatus.CREATED);
    }

    // Update an existing receipt
    @PutMapping("/update/{id}")
    public ResponseEntity<MarineBillMoneyReceipt> updateMarineBillMoneyReceipt(
            @RequestBody MarineBillMoneyReceipt mr,
            @PathVariable long id) {
        MarineBillMoneyReceipt updatedReceipt = marineBillMoneyReceiptService.updateMarineBillMoneyReceipt(mr, id);
        return ResponseEntity.ok(updatedReceipt);
    }

    // Delete a receipt by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMarineBillMoneyReceiptById(@PathVariable long id) {
        marineBillMoneyReceiptService.deleteMarineBillMoneyReceipt(id);
        return ResponseEntity.noContent().build(); // Returns 204 No Content
    }

    // Get a specific receipt by ID
    @GetMapping("/{id}")
    public ResponseEntity<MarineBillMoneyReceipt> getMarineBillMoneyReceiptById(@PathVariable long id) {
        MarineBillMoneyReceipt receipt = marineBillMoneyReceiptService.findById(id);
        return ResponseEntity.ok(receipt);
    }
    

}
