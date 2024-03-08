package com.itwill.springboot1.domain;

import java.math.BigDecimal;

import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.EmbeddableInstantiator;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity // JPA의 엔터티 클래스
@Table(name = "USERS")  // 데이터베이스의 테이블 이름을 설정.
// 엔터티 클래스가 상속하는 상위 클래스에는 @MappedSuperClass 애너테이션을 사용해야 함.  
public class User extends BaseTimeEntity {
    
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //-> Oracle의 generated as identity, MySQL의 auto increment와 비슷한 기능.
    private Long id;
    
    @NaturalId  // Unique 제약 조건.
    @Basic(optional = false)    // 데이터베이스 기본 타입으로 매핑. Not Null 제약조건.    
    private String username;
    
    @Enumerated(EnumType.STRING)
    //-> @Enumerated(EnumType.Ordinal) 기본값. 기본값인 경우에는 생략 가능.
    //-> Ordinal: 숫자 타입 컬럼으로 매핑.
    //-> String: 문자열 타입으로 매핑.
    private Gender gender;
    
    //@Column(length = 1000)
    //private String address;
    
    @Column(name="SAL", precision = 6, scale=2) 
    //-> name: (엔터티 객체의 필드 이름과 테이블 칼럼 이름이 다를 때) 테이블의 칼럼 이름을 설정.
    //-> precision, scale: DDL을 자동으로 실행할 때. 오라클에서는 NUMBER(precision, scale)
    private BigDecimal salary;
    
    // @Embedded // @Embeddable 애너테이션을 사용한 객체를 포함. 
    private Address address;
    
    
}
