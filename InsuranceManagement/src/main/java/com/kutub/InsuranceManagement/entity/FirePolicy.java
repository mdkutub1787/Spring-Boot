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
@Table(name = "firepolicies")
public class FirePolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String date;
    private String bankName;
    private String policyholder;
    private String address;
    private String stockInsured;
    private double sumInsured;
    private String interestInsured;
    private String coverage;
    private String location;
    private String construction;
    private String owner;
    private String usedAs;
    private String periodFrom;
    private String periodTo;



//    @JsonIgnore
//    @OneToMany(mappedBy = "firePolicy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<FireBill> fireBills;


}
