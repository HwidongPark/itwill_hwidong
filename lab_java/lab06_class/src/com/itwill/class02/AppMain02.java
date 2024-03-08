package com.itwill.class02;

import java.lang.instrument.Instrumentation;


public class AppMain02 {

    public static void main(String[] args) {
        // Rectangle 타입의 변수 선언, 초기화(객체 생성)
        Rectangle rect1 = new Rectangle();
        System.out.println(rect1);
        System.out.println(rect1.height);
        
        // argument를 갖는 생성자를 사용해서 객체를 생성.
        Rectangle rect2 = new Rectangle(3.0, 4.0);
    
        System.out.println("rect2 width = " + rect2.width);
        System.out.println("rect2 height = " + rect2.height);
        System.out.println("rect2 넓이 = " + rect2.area());
        System.out.println("rect2 둘레 = " + rect2.perimeter());
        
    }

    

}
