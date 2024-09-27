package com.kutub.InsuranceManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "marinbillmoneyreceipts")
public class MarineBillMoneyReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String issuingOffice;

    private String classOfInsurance;

    private String date;

    private String modeOfPayment;

    private String issuedAgainst;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "marinebillId")
    private MarineInsuranceBill marinebill;

}
