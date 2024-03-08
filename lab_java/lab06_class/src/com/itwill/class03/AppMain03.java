package com.itwill.class03;

public class AppMain03 {
    
    public static void main(String[] args) {
        // 객체 생성, 초기화, 메서드를 호출 연습
        Circle c1 = new Circle();
        
        System.out.println("원의 반지름: " + c1.radius);
        System.out.println("원의 넓이: " + c1.area());
        System.out.println("원의 둘레: " + c1.perimeter());
        
        Circle c2 = new Circle(3);
        
        System.out.println("원의 반지름: " + c2.radius);
        System.out.println("원의 넓이: " + c2.area());
        System.out.println("원의 둘레: " + c2.perimeter());
        
        
        
    }

}
