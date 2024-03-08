package com.itwill.inheritance09;

public class Rectangle extends Shape {
    private double width;   // 직사각형 가로길이
    private double height;  // 직사각형 세로 길이
    
    public Rectangle(double width, double height) {
        super("직사각형");
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        // TODO Auto-generated method stub
        return width * height;
    }

    @Override
    public double perimeter() {
        // TODO Auto-generated method stub
        return 2 * (width + height);
    }
    
}
