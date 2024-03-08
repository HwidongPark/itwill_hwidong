package com.itwill.swingpractice;

public class Account {
    private String id;
    private String password;
    private String phone;
    private String email;
    
    // Constructor
    public Account() {}
    
    public Account(String id, String password, String phone, String email) {
        super();
        this.id = id;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    
    // Getter / Setter
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getId() {
        return id;
    }

    
    // equal, hash 재정의
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }
    
    
    
}
