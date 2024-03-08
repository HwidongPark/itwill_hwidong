package com.itwill.lambda01;

import com.itwill.lambda01.Calculator.Calculable;

public class LambdaMain01 {

    public static void main(String[] args) {

        Calculator calc = new Calculator(1, 2);
        
        Calculable adder = new Adder();
        
        double result = calc.calculate(adder);
        
        class Subtractor implements Calculable {
            @Override
            public double calculate(double x, double y) {
                return x - y;
            }
            
        }
        
        result = calc.calculate(new Subtractor());
        System.out.println("result = " + result);
        
        // 3. 익명 클래스 (anonymous class)
        result = calc.calculate(new Calculable() {

            @Override
            public double calculate(double x, double y) {
                return x * y;
            }
            
        });
        
        // 4. 람다 표현식(Lambda expression) - 함수형 표기법
        result = calc.calculate((x, y) -> x / y);
        System.out.println("result = " + result);
        
        
        /*
         * 람다 표현식:
         * 클래스 생성과 객체 생성을 동시에 하는 익명 클래스를 간단히 작성하기 위한 문법
         * 함수형 인터페이스 타입 객체만 람다표현식으로 작성할 수 있음.
         * 
         * 람다 표현식 문법: (파라미터 선언) -> { 메서드 코드; }
         * - 람다 표현식의 파라미터 선언에서는 변수 타입을 생략할 수 있음
         * - 람다 표현식에서 메서드 본체의 문장이 하나만 있는 경우에는 {}와 ;을 생략할 수 있음.
         * - 람다 표현식이 return문장만 있는 경우에는 {}, return, ;을 모두 생략하고, 리턴값만 표기.
         *     (예) (x, y) -> x + y
         * - 람다 표현식에서 파라미터가 1개만 있는 경우에는 ()도 생략 가능
         *     (예) x -> 2 * x * 3
         */
        
        
    }   // main end

}   // main class end
