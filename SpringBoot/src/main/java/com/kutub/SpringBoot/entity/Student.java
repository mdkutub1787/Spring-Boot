package com.kutub.SpringBoot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Date;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;

    private String gender;

    @Column(nullable = false, unique = true)
    private String cell;
    @Column(nullable = false, unique = true)
    private Date dob;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;

}
