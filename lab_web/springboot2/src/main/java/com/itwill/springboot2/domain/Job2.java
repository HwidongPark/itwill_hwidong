package com.itwill.springboot2.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "jobs")
public class Job2 {
    @Id
    private String jobId;
    
    private String jobTitle;
}
