package com.itwill.springboot1.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Questions")
public class Question {
    
    @Id
    @GeneratedValue(generator = "QUESTION_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "QUESTION_SEQ", allocationSize = 1)
    private Long id;
    
    @Basic(optional = false)
    private String title;
    
    @Basic(optional = false)
    private String content;
    
}
