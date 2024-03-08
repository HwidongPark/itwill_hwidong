package com.itwill.class03;


// 클래스: 데이터, 속성(필드) + 초기화(생성자) + 기능(메서드) ==> 데이터 타입
public class Circle {
    // 필드(field)
    double radius;
    static double PI = 3.1415926;
    
    // 생성자
    // 기본생성자(default constructor)
    public Circle() {
        
    }
    
    // argument를 갖는 생성자
    public Circle(double radius) {
        this.radius = radius;
    }
    
    // 메서드
    // area: 원의 넓이 리턴
    // perimeter: 원의 둘레 길이 리턴
    public double area() {
//        return PI * radius * radius;
        return PI * Math.pow(radius, 2);
    }
    
    public double perimeter() {
        return 2 * PI * radius;
    }

}
