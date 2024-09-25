package com.kutub.InsuranceManagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "marineinsurancebills")
public class MarineInsuranceBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double marineRate;

    @Column(nullable = false)
    private double marinePremium;

    @Column(nullable = false)
    private double warSrccRate;

    @Column(nullable = false)
    private double netPremium;

    @Column(nullable = false)
    private double tax;


    @Column(nullable = false)
    private double stampDuty;

    @Column(nullable = false)
    private double grossPremium;

}
