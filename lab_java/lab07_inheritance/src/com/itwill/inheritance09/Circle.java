package com.itwill.inheritance09;

public class Circle extends Shape {
    
    private double radius;
    
    public Circle(double radius) {
        super("동그라미");
        this.radius = radius;
        
    }

    @Override
    public double area() {

        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        // TODO Auto-generated method stub
        return 2 * Math.PI * radius;
    }
    
    
    
    

}
