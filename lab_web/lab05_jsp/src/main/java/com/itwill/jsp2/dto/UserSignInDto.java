package com.itwill.jsp2.dto;

public class UserSignInDto {
    private String userid;
    private String password;
    
    // TODO: 알아서 만들기
    UserSignInDto() {}

    public UserSignInDto(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }
    
    // Getters / Setters
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "UserSignInDto [userid=" + userid + ", password=" + password + "]";
    }
    
    
    
    
}
