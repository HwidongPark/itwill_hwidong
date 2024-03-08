package com.itwill.springboot2.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Entity
@Table(name = "EMPLOYEES")
public class Employee2 {
    @Id
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDateTime hireDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name="job_id")
    private Job2 job;
    private Double salary;
    private Double commissionPct;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="manager_id")
    private Employee2 manager;
    
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "department_id")
    private Department2 department;
    
}