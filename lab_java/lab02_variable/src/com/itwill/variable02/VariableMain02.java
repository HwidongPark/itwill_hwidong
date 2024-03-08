package com.itwill.variable02;

public class VariableMain02 {

    public static void main(String[] args) {
        
        // 정수를 저장하는 변수를 선언하고, 값을 저장(초기화).
        int firstNumber = 3;
        
        // 정수를 저장하는 변수를 선언하고, 값을 저장(초기화).
        int secondNumber = 2;
        
        //두 변수 x와 y의 사칙연산(+, -, *, /)결과를 출력.
        // 산술 연산자: +, -, *, /, %(remainder operator)
        // 더하기
        System.out.println(firstNumber + " + "+ secondNumber + " = " + (firstNumber+secondNumber));
        
        //빼기
        System.out.println(firstNumber + " - "+ secondNumber + " = " + (firstNumber-secondNumber));
        
        //곱하기
        System.out.println(firstNumber + " * "+ secondNumber + " = " + (firstNumber*secondNumber));
        
        //나누기
        System.out.println(firstNumber + " / "+ secondNumber + " = " + ((double) firstNumber / secondNumber));
        
        //나머지
        System.out.printf("%d %% %d = %d\n", 100, 3, 100 % 3);
        
        //  나누기 연산자(/):
        // (1)정수 / 정수 결과는 나눈 "몫". 절대로 소숫점이 나오지 않음
        // (2) 실수 / 실수 결과는 소숫점까지 나옴..  *****참고로 분자, 분모 둘 중 하나만 실수여도 실수의 결과값이 나옴
        // 만약 variables를 이용한 나눗셈에서 실수의 결과값을 갖고 싶으면 declare단계에서부터 실수로 declare해야한다.
        
        double number1 = 100;
        double number2 = 3;
        System.out.println(number1 / number2);
    }

}