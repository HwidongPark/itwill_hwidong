package com.itwill.exception06;

public class ExceptionMain06 {

    public static void main(String[] args) {
        // Calculator 타입 객체 생성
        Calculator calc = new Calculator();
        
        // Calculator 타입 객체에 메서드 호출.
        try {
            int result = calc.divide(100, 0);
            System.out.println("몫 = " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
