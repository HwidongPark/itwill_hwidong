package com.itwill.inheritance07;

public class InheritanceMain07 {
    
    public static void main(String[] args) {
        
        User user1 = new User("Hwidong", "12345");
        User user2 = new User("Gildong", "54321");
        User user3 = new User("Hwidong", "56789");
        
        
        //toString() override확인
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3.toString());
        
        
        // equals() override확인
        if (user1.equals(user2)) {
            System.out.println("user1과 user2는 같은 유저입니다.");
        } else {
            System.out.println("user1과 user2는 서로 다른 유저입니다.");
        }
        
        
        if (user1.equals(user3)) {
            System.out.println("user1과 user3는 같은 유저입니다.");
        } else {
            System.out.println("user1과 user3는 서로 다른 유저입니다.");
        }
        
        if (user2.equals(user3)) {
            System.out.println("user2과 user3는 같은 유저입니다.");
        } else {
            System.out.println("user2과 user3는 서로 다른 유저입니다.");
        }
        

        
        // hashCode override확인
        if (user1.hashCode() == user2.hashCode()) {
            System.out.println("user1과 user2는 같은 그룹안에 분류 되었습니다.");
        } else {
            System.out.println("user1과 user2는 다른 그룹안에 분류 되었습니다.");
        }
        
        
        if (user1.hashCode() == user3.hashCode()) {
            System.out.println("user1과 user3는 같은 그룹안에 분류 되었습니다.");
        } else {
            System.out.println("user1과 user3는 다른 그룹안에 분류 되었습니다.");
        }
        
        
        if (user2.hashCode() == user3.hashCode()) {
            System.out.println("user2과 user3는 같은 그룹안에 분류 되었습니다.");
        } else {
            System.out.println("user2과 user3는 다른 그룹안에 분류 되었습니다.");
        }
        
    }
    
}
