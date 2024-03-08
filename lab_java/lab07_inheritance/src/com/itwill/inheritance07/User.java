package com.itwill.inheritance07;

public class User {
    //field
    private String id;
    private String password;
    
    
    // TODO: 생성자 - 기본 생성자, argument 2개 짜리
    // 기본생성자
    public User() {}
    
    //argument 2개짜리 생성자
    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }
    
    
    // TODO: toString 재정의. - 
    @Override
    public String toString() {
        return "id: " + this.id + ", password: " + this.password;
    }

    
    // TODO: equals를 재정의. - 아이디만 일치하면 true, 그렇지 않으면 false 반환
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof User) {
            User user2 = (User) obj;
//            result = (this.id == user2.id);
            // 위의 코드는 안좋음.. String에서 == 는 참조값 비교(O), 값 비교(X)
            if (this.id != null) {  // null이 아닌 경우에만 메서드 호출 가능!!
                result = this.id.equals(user2.id);      // String 비교할 때 equals method를 사용해야 함
            }
        }

        return result;
    }

    
    // TODO: hashCode 재정의.
    @Override
    public int hashCode() {
        if(this.id != null) {
            return (int) id.charAt(0);
        } else {
            return 0;
        }
    }    
    
    
    
}
