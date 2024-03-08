package com.itwill.springboot2.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "departments")
public class Department2 {
    @Id
    private Integer departmentId;
    private String departmentName;
    
    @OneToOne
    @JoinColumn(name="manager_id")
    private Employee2 manager;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location2 location;
    
    @OneToMany(mappedBy = "department")
    private List<Employee2> employees;
}