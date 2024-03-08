package com.itwill.switch01;

public class SwitchMain01 {
    
    public static void main(String[] args) {
        // switch-case 구문:
        // 해당 case위치로 이동해서  ***break; 문장을 만날 때 까지 실행한다.***
        // 해당 case가 없으면 default 블록을 실행한다.
        // default block은 switch 구문에서 항상 가장 마지막에 작성해야한다. default는 가장 마지막 문장이기 때문에 break; 필요없다.
        
        //switch (변수 또는 식)에서 사용할 수 있는 데이터 타입:
        // (1) 정수 - byte, short, int, long, char
        // (2) String
        // (3) enum
        // (주의) 실수 타입(float, double)과 boolean퓨   은 사용할 수 없음!!
        // 즉, if (condition)과 달리 switch는 switch(값) {}과 같다.
        String season = "fall";
        switch (season) {
        case "spring":
            System.out.println("봄");
            break;
        case "summer":
            System.out.println("여름");
            break;
        case "fall":
            System.out.println("가을");
            break;
        case "winter":
            System.out.println("겨울");
            break;
        default:
            System.out.println("몰라요");
        }
        
        
        int number = 10;
        switch (number % 2) {
        case 0:
            System.out.println("짝수");
            break;
        default:
            System.out.println("홀수");
        }
        
    }

}
