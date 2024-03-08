package com.itwill.inheritance09;

public class InheritanceMain09 {

    public static void main(String[] args) {
        // Rectangle 타입 객체 생성
        Rectangle rect1 = new Rectangle(4, 3);
        rect1.draw();
        System.out.println("rect1의 넓이: " + rect1.area());
        System.out.println("rect1의 둘레: " + rect1.perimeter());
        
        Circle c1 = new Circle(9);
        c1.draw();
        
        
        
    }

}
