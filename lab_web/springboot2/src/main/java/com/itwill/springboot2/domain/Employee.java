package com.itwill.springboot2.domain;

import java.time.LocalDateTime;

import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "EMP")
public class Employee {
    
    @Id
    @Column(name = "EMPNO")
    private Integer id;
    
    private String ename;
    
    private String job;
    
//    @Column(name = "MGR")
//    private Integer manager;
    
    @ToString.Exclude   // toString 메서드에서 제외.. 필드 이름 앞에서만 붙일 수 있는 annotation
    @ManyToOne     // ManyToOne일 때는 특별한 경우가 아니면 LAZY로 하는게 좋음
    @JoinColumn(name = "mgr")
    private Employee manager;
    
    private LocalDateTime hiredate;
    
    @Column(name = "SAL")
    private Double salary;
    
    @Column(name = "COMM")
    private Double commission;
    
//    private Integer deptno;
    
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deptno")
    private Department department;
    
}
