package com.kutub.InsuranceManagement.restcontroller;

import com.kutub.InsuranceManagement.entity.FireBill;
import com.kutub.InsuranceManagement.entity.FirePolicy;
import com.kutub.InsuranceManagement.service.FireBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/firebill")
@CrossOrigin("*")
public class FireBillController {

    @Autowired
    private FireBillService fireBillService;

    // Get all FireBills
    @GetMapping("/")
    public List<FireBill> getAllFireBills() {
        return fireBillService.getAllFireBill();
    }


    // Create a new FireBill
    @PostMapping("/save")
    public void saveFireBill(@RequestBody FireBill fb) {
         fireBillService.saveFireBill(fb);
    }

    // Update a FireBill by ID
    @PutMapping("/update/{id}")
    public  void updateFireBill(@RequestBody FireBill fb){
        fireBillService.saveFireBill(fb);
    }

    // Delete a FireBill by ID
    @DeleteMapping("/delete/{id}")
    public void deleteFireBillById(@PathVariable int id) {
        fireBillService.deleteFireBill(id);
    }

    // Endpoint to search FireBills by policyholder name
    @GetMapping("/searchpolicyholder")
    public List<FireBill> findFireBillsByPolicyholder(@RequestParam String policyholder) {
        return fireBillService.findByPolicyHolderName(policyholder);
    }
}
