package com.itwill.springboot2.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "LOCATIONS")
public class Location2 {
    
    @Id
    private Integer locationId;
    
    private String streetAddress;
    
    private String postalCode;
    
    private String city;
    
    private String stateProvince;
    
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country2 country;
    
    
}
