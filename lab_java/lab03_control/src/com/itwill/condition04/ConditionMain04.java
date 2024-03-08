package com.itwill.condition04;

public class ConditionMain04 {

    public static void main(String[] args) {

        // 단항 연산자 --> operand가 하나만 필요한 operator..ex) !isWhiteBear
        // 이항 연산자 --> operands가 두 개가 필요한 operator.. ex) 1 + 2 --> +를 위해 operands가 2개 필요함
        // 삼항 연산자 --> if-else 구문을 간단히 만든 문법.
        
        int x = 1;
        int y = 5;
        
        //x와 y 중에 더 큰 값을 big이라는 variable에 저장하고 싶음
        int big;
        if (x >= y) {
            big = x;
        } else {
            big = y;
        }
        
        System.out.println(big);
        
        // (조건식) ? (조건식을 만족할 때 채택할 값) : (조건식을 만족하지 못할때 채택할 값)
        int big2 = (x >= y) ? x : y;  //참고로 preference상 = 는 항상 마지막에
        System.out.println(big2);

    }

}