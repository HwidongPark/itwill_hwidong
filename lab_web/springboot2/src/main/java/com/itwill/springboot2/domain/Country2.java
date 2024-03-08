package com.itwill.springboot2.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "countries")
public class Country2 {
    
    @Id
    private String countryId;
    private String countryName;
    private Integer regionId;
    
    
}
