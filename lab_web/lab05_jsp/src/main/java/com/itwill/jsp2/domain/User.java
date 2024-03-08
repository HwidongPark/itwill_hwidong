package com.itwill.jsp2.domain;

public class User {
    private long id;
    private String userid;
    private String password;
    private String email;
    private long points;
    
    public User() {}
    
    public User(long id, String userid, String password, String email, long points) {
        this.id = id;
        this.userid = userid;
        this.password = password;
        this.email = email;
        this.points = points;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }
    
    
    @Override
    public String toString() {
        return "User [id=" + id + ", userid=" + userid + ", password=" + password + ", email=" + email + ", points="
                + points + "]";
    }
    
    
    // ----- Builder(Factory) pattern 연습
    // 이 메서드를 호출하면 UserBuilider객체를 얻을 수 있음
    public static UserBuilder builder() {
        return new UserBuilder();
    }
    
    public static class UserBuilder {
        private long id;
        private String userid;
        private String password;
        private String email;
        private long points;
        
        private UserBuilder() {}    //  User라는 같은 클래스 안에 있기 때문에 호출 가능
        
        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }
        
        public UserBuilder userid(String userid) {
            this.userid = userid;
            return this;
        }
        
        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }
        
        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }
        
        public UserBuilder points(long points) {
            this.points = points;
            return this;
        }
        
        public User build() {
            return new User(id, userid, password, email, points);
        }
        
    }
    
    
}
