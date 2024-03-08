package com.itwill.inheritance03;

public class HybridCar extends Car {
    
    private int battery;
    
    // 상위 클래스에서 작성된 메서드를 하위 클래서에서 재정의 하는 것.
    // 리턴 타입, 메서드 이름, 파라미터 선언은 모두 같아야 함.
    // 접근 수식어는 상위 클래스의 가시성 범위와 같거나 더 넓어질 수 있음
    
    
    public HybridCar(int fuel, int speed, int battery) {
        super(fuel, speed);
        this.battery = battery;
        // TODO Auto-generated constructor stub
    }

    @Override       // -> annotation이라고 부름. 이걸 사용하면 컴파일러가 정말 override인지 확인해 줌. 작동에 영향을 끼치는건 아님
    public void drive() {
        //super.drive(); // -> 상위 객체의 재정의 전 메서드 호출 기능.
        System.out.println("speed: " + getSpeed() + "km/h, fuel:" + getFuel() + "L, battery: " + battery + "%");
    }
    
    public int charge(int battery) {
        this.battery = battery;
        return this.battery;
    }
    
    public int checkBattery() {
        return this.battery;
    }
    

}
