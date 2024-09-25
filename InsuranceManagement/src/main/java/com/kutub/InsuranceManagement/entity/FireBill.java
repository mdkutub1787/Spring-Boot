package com.kutub.InsuranceManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JoinColumn(name = "firePolicyId")  // FirePolicy foreign key
    private FirePolicy firePolicy;
}
