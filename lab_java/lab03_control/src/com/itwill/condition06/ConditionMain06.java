package com.itwill.condition06;

import java.util.Random;  //Random이라고 하는 클래스를 사용하기 위해서 import문이 반드시 있어야 한다.

public class ConditionMain06 {

    public static void main(String[] args) {
        // 조건문, 비교/논리 연산자 연습
        
        // 난수를 생성할 수 있는 Random type변수를 선언, 초기화:
        Random random = new Random();
        int java = random.nextInt(101);   // 0이상 101미만의 정수 난수를 만들고, java라는 variable에 저장
        System.out.println("Java: " + java);
        
        int sql = random.nextInt(101);
        System.out.println("SQL: " + sql);
        
        int js = random.nextInt(101);
        System.out.println("JavaScript: " + js);
        
        int scoreTotal = java + sql + js;
        System.out.println("점수 총합: " + scoreTotal);
        
        double scoreMean = scoreTotal / 3.0;
        System.out.println("과목 평균: " + scoreMean);
        
        // 세 과목의 점수가 모두 40점 이상이고, 평균이 60점 이상이면 합격, 그렇지 않으면 불합격
        
        if (scoreMean >= 60 && java >=40 && sql>=40 && js >=40) {
            System.out.println("합격");
        } else {
            System.out.println("불합격");
        }
        
        if (java < 40 || sql < 40 || js < 40) {
            System.out.println("불합격");
        } else if (scoreMean < 60) {
            System.out.println("불합격");
        } else {
            System.out.println("합격");
        }
        
    }

}
