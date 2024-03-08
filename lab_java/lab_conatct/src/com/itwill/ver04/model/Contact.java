package com.itwill.ver04.model;

import java.io.Serializable;

// MVC 아키텍쳐에서 Model역할. VO, DTO.
public class Contact implements Serializable {
    private int id; // 데이터베이스 테이블에서 고유키(primary key)로 사용.
    private String name;
    private String phone;
    private String email;
    
    public Contact() {}
    
    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    
    // getter / setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    // toString
    @Override
    public String toString() {
        return "Contact(id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ")";
    }
    
    
    
    
    
    

}
