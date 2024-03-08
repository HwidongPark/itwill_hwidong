package com.itwill.variable05;

public class VariableMain05 {

    public static void main(String[] args) {
        
        // boolean type: 논리 타입. true or false 두 가지 상태만을 저장할 수 있는 데이터 타입. 참고로 true, false도 keywords이다.
        // 비교 연산자: ==, !=, >, >=, <, <=    --> 왼쪽과 오른족을 비교해서 맞으면 true, 틀리면 false를 return함.
        // 논리 연산자: &&(and 의미), ||(파이프 기호, or 의미), !(not의미)
        
        int x = 2;
        int y = 3;
        
        boolean b1 = x != y;
        System.out.println(b1);
        
        boolean b2 = (x > 0) && (x < 10);
        System.out.println(b2);
    }

}