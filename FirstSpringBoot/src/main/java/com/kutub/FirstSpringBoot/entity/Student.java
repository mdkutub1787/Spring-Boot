package com.kutub.FirstSpringBoot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false,unique = true)
    private String gender;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false,unique = true)
    private int cell;

    @ManyToOne
    @JoinColumn(name="departmentId")
    private Department department;
}
