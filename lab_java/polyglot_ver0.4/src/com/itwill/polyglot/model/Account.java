package com.itwill.polyglot.model;

public class Account {
    
    private String account;
    private String passsword;
    private String phone;
    private String email;
    
    // Constructors
    public Account() {}

    public Account(String account, String passsword, String phone, String email) {
        super();
        this.account = account;
        this.passsword = passsword;
        this.phone = phone;
        this.email = email;
    }
    
    
    // Getters
    public String getAccount() {
        return account;
    }

    public String getPasssword() {
        return passsword;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Account [account=" + account + ", passsword= ******" + ", phone=" + phone + ", email=" + email
                + "]";
    }
    
    
    
    
}
