package com.kutub.InsuranceManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "marineInsuranceDetails")
public class MarineInsuranceDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date date;

    @NotBlank(message = "Bank name cannot be empty")
    @Column(nullable = false)
    private String bankName;

    @NotBlank(message = "Policyholder cannot be empty")
    @Column(nullable = false)
    private String policyholder;

    @NotBlank(message = "Address cannot be empty")
    @Column(nullable = false)
    private String address;

    @NotBlank(message = "Voyage from cannot be empty")
    @Column(nullable = false)
    private String voyageFrom;

    @NotBlank(message = "Voyage to cannot be empty")
    @Column(nullable = false)
    private String voyageTo;

    @NotBlank(message = "Via cannot be empty")
    @Column(nullable = false)
    private String via;

    @NotBlank(message = "Stock item cannot be empty")
    @Column(nullable = false)
    private String stockItem;

    @Positive(message = "Sum insured in USD must be positive")
    @Column(nullable = false)
    private double sumInsuredUsd;

    @Column(nullable = false)
    private double usdRate;

    @Column(nullable = false)
    private double sumInsured;

    @NotBlank(message = "Coverage cannot be empty")
    @Column(nullable = false)
    private String coverage;

    @JsonIgnore
    @OneToMany(mappedBy = "marineDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MarineInsuranceBill> marineInsuranceBills;

    public void convertSumInsuredUsd(double exchangeRate) {
        this.sumInsured = this.sumInsuredUsd * exchangeRate;
        this.usdRate = exchangeRate;
    }
}
