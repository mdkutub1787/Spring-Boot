package com.kutub.InsuranceManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "firebills")
public class FireBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private double fire;

    @Column(nullable = false)
    private double rsd;

    @Column(nullable = false)
    private double netPremium;

    @Column(nullable = false)
    private double tax;

    @Column(nullable = false)
    private double grossPremium;

    // A FireBill is associated with one FirePolicy
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "firePolicyId", nullable = false)  // FirePolicy foreign key
    private FirePolicy firePolicy;

    // A FireBill can have many receipts, and receipts are mapped by the "fireBill" field in the Receipt entity
    @JsonIgnore
    @OneToMany(mappedBy = "fireBill", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Receipt> receipts;

    // Additional utility method to handle adding a receipt to the FireBill
    public void addReceipt(Receipt receipt) {
        receipts.add(receipt);
        receipt.setFireBill(this);
    }
}
