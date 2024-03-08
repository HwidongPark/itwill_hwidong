package com.itwill.switch02;

import java.util.Random;

public class SwitchMain02 {

    public static void main(String[] args) {
        
        //Random 타입을 이용해서 genderCode에 5미만의 정수를 저장
        Random random = new Random();
        int genderCode = random.nextInt(5);
        System.out.println("genderCode의 값은: " + genderCode);
        
        
        //switch 구문을 사용해서...
        // genderCode의 값이 1 또는 3이면 "남성"을 출력,
        // genderCode의 값이 2 또는 4이면 "여성"을 출력,
        // genderCode의 값이 1~4 이외의 값이면 "몰라요"라고 출력
        
        switch (genderCode) {
        case 1:
        case 3:
            System.out.println("남성");
            break;
        case 2:
        case 4:
            System.out.println("여성");
            break;
        default:
            System.out.println("몰라요");
        }
        
        // ,로 구분해도 됨
        switch (genderCode) {
        case 1, 3:
            System.out.println("남성");
            break;
        case 2, 4:
            System.out.println("여성");
            break;
        default:
            System.out.println("몰라요");
        }
        
        // 참고로 switch case로 만든 모든 statement는 if문으로 구현할 수 있다. 하지만 그 역은 성립하지 않는다.

    }

}