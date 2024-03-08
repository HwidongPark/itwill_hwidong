package com.itwill.inheritance08;

/*
 *  추상 메서드(abstract method):
 *  - 메서드의 signature(수식어, 리턴 타입, 메서드 이름, 파라미터 선언)
 *  본체(body)가 정의되어 있지 않은 메서드.
 *  - 추상 메서드는 반드시 abstract 키워드로 수식해야 함.
 *  - 추상 메서드 선언은 반드시 세미콜론(;)으로 끝나야 함
 *  
 *  추상 클래스(abstract class):
 *  - abstract 수식어가 사용된 클래스.
 *  - 대부분의 경우, 추상 클래스는 1개 이상의 추상 메서드를 갖고 있는 경우가 많음.
 *  - 클래스에 추상 메서드가 있는 경우 반드시 추상 클래스로 선언해야 함.
 *  - 추상 클래스는 객체를 생성할 수 없음. 생성자를 호출할 수 없음.
 *  
 *  추상 메서드와 추상 클래스는 상속하는 하위 클래스에서 구현을 목적으로 함.
 *  
 */


abstract class Animal {
    public abstract void move();
}


class Dog extends Animal {
    public void move() {
        System.out.println("강아지는 총총총...");
    }
    
}

class Fish extends Animal {
    public void move() {
        System.out.println("물고기는 스윔스윔...");
        
    }
}

class Bird extends Animal {
    public void move() {
        System.out.println("새는 훨훨~~");
    }
}


public class InheritanceMain08 {

    public static void main(String[] args) {
        // 추상 클래스 Animal 타입으로는 객체 생성이 되지 않음!
//        Animal a = new Animal();
        
        Animal[] animals = {new Dog(), new Fish(), new Bird()};
        for (Animal a : animals) {
            a.move();
        }
        
    }

}
