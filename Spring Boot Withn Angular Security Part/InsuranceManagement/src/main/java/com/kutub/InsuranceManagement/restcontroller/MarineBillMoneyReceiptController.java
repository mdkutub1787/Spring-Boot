package com.kutub.InsuranceManagement.restcontroller;

import com.kutub.InsuranceManagement.entity.MarineBillMoneyReceipt;
import com.kutub.InsuranceManagement.entity.MoneyReceipt;
import com.kutub.InsuranceManagement.entity.Policy;
import com.kutub.InsuranceManagement.service.MarineBillMoneyReceiptService;
import com.kutub.InsuranceManagement.service.MoneyReceiptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/marinemoneyreceipt")
@CrossOrigin("*")
public class MarineBillMoneyReceiptController {

    private MarineBillMoneyReceiptService marineBillMoneyReceiptService;

    // Get all Receipt
    @GetMapping("/")
    public List<MarineBillMoneyReceipt> getAllMarineBillMoneyReceipt() {
        return marineBillMoneyReceiptService.getAllMarineBillMoneyReceipt();
    }


    // Create a new Receipt
    @PostMapping("/save")
    public void saveMarineBillMoneyReceipt(@RequestBody MarineBillMoneyReceipt mr) {
        marineBillMoneyReceiptService.saveMarineBillMoneyReceipt(mr);
    }


    @PutMapping("/update/{id}")
    public  void updateMarineBillMoneyReceipt(@RequestBody MarineBillMoneyReceipt mr){
        marineBillMoneyReceiptService.saveMarineBillMoneyReceipt(mr);
    }

    // Delete a Receipt by ID
    @DeleteMapping("/delete/{id}")
    public void deleteMarineBillMoneyReceiptById(@PathVariable long id) {
        marineBillMoneyReceiptService.deleteMarineBillMoneyReceipt(id);
    }


    @GetMapping("/{id}")
    public MarineBillMoneyReceipt getMarineBillMoneyReceiptById(@PathVariable ("id") long id){
        return   marineBillMoneyReceiptService.findById(id);
    }
}
