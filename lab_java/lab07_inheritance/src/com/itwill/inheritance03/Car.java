package com.itwill.inheritance03;

public class Car {
    // 필드
    private int fuel;
    private int speed;
    
    // 생성자
    public Car(int fuel, int speed) {
        this.fuel = fuel;
        this.speed = speed;
    }
    
    // 메서드
    public int getFuel() {
        return this.fuel;
    }
    
    public int getSpeed() {
        return this.speed;
    }
    
    public void drive() {
        System.out.println("speed: " + speed + ", fuel: " + fuel);
    }
}
