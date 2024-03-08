package com.itwill.inheritance02;

public class InheritanceMain02 {
    
    public static void main(String[] args) {
        // Person 타입 객체를 기본 생성자를 사용해서 생성.
        Person p1 = new Person();
        System.out.println("p1 이름: " + p1.getName());
        System.out.println("---------------------");
        
        
        // Person 타입 객체를 아규먼트를 갖는 생성자를 사용해서 생성.
        Person p2 = new Person("홍길동");
        System.out.println("p2 이름: " + p2.getName());
        System.out.println("---------------------");
        
        // Student타입의 객체를 기본 생성자를 사용해서 생성.
        // Student타입의 객체를 만들 때 부모타입의 생성자 먼저 -> 스튜던트 생성
        Student s1 = new Student();
        System.out.println("s1 이름: " + s1.getName());       // superclass Person에서 상속받은 getter
        System.out.println("s1 학교: " + s1.getSchool());     // subclass Student에서 작성한 getter
        
        System.out.println("---------------------");
        // 아규먼트 1개인 생성자를 사용해서 Student 객체 생성
        Student s2 = new Student("아이티윌");
        System.out.println("s2 이름: " + s2.getName());
        System.out.println("s2 학교: " + s2.getSchool());
        
        Student s3 = new Student("고길동", "아이티윌");
        System.out.println("s3 이름: " + s3.getName());
        System.out.println("s3 학교: " + s3.getSchool());
        
        
        
        
        
    }
}
 