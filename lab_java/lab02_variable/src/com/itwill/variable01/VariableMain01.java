package com.itwill.variable01;

public class VariableMain01 {

    public static void main(String[] args) {
        
        // Variable: 프로그램에서 필요한 데이터를 저장하는 메모리 공간을 의미.
        // 1. 변수 선언: 변수의 데이터 타입과 이름을 선언. --> Syntax는 type variableName;
        int age;  // 정수를 저장하는 변수 age를 선언.
        
        // 2.변수 초기화: 변수의 값을 처음으로 저장. --> Syntax는 variableName = value;
        //Value initialization이란 initial value를 variable에 할당하는 것
        age = 16;   //16이라는value를 age라는 variable에 할당.
        
        System.out.printf("나이: %d\n", age);
        
        //변수 선언과 초기화를 한 문장으로 작성
        int x = 100;
        System.out.println("x = " + x);
        
        //자바의 기본 데이터 타입(primitive data type):
        // 정수 타입: byte, short, int, long, char  -> 각각 1byte, 2byte, 4byte, 8byte의 메모리공간을 차지함.
        // 실수 타입: float, double --> 4byte, 8byte의 메모리 공간 차지.. 주로 double사용.. 걍 double 사용한다 생각
        // 논리 타입: boolean -> 
        
        // 변수 이름 작성 규칙:
        // - 변수 이름은 알파벳, 숫자, _, $만 사용 가능. 이건 반드시 지켜야 하는 규칙(문법)
        // - 변수 이름은 대/소문자 구분함.. age와 Age는 다른 variable임
        // - 변수 이름은 숫자로 시작할 수 없다. (문법).. 예를 들어, age1은 가능, 1age는 불가능 
        // - 변수 이름으로 자바의 키워드는 사용할 수 없다.
        // - 변수 이름은 변수의 역할에 어울리는 "의미"있는 단어를 사용해서 만드는 것을 권장.
        // - 변수 이름을 2개 이상의 단어를 붙여서 만들 경우에는 lower camel case를 권장.
    }

}