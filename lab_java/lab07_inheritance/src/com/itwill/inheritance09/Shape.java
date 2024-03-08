package com.itwill.inheritance09;

public abstract class Shape {
    // 필드 선언
    protected String type;  // 직사각형, 원, 삼각형, ...
    
    
    // 생성자
    protected Shape(String type) {
        this.type = type;
    }
    
    // 추상 메서드
    public abstract double area();      // 도형의 넓이를 리턴하는 메서드.
    public abstract double perimeter(); // 도형이 둘레 길이를 리턴하는 메서드.
    
    // final method - 하위 클래스에서 override할 수 없는 메서드
    public final void draw() {
        String info = String.format("%s[넓이 = %f, 둘레 = %f]",
                type, area(), perimeter());
        System.out.println(info);
    }
    
    
}
