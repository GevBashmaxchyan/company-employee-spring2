package com.example.companyemployeespring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employee")

public class Employee {
    @Id
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String salary;
    private String position;
    private String picUrl;
    @ManyToOne
    private Company company;
}
