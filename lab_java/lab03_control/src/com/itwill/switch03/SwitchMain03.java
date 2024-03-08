package com.itwill.switch03;

public class SwitchMain03 {
    
    public static void main(String[] args) {
        
        //Weekday타입의 변수를 선언하고 초기화
        Weekday day = Weekday.SAT;
        //이러면 Weekday가 가지고 있는 값들 중 하나로만 저장할 수 있음.
        System.out.println("오늘은 어떤요일? " + day);
        
        switch(day) {
        case MON:
            System.out.println("회사가기 싫어...");
            break;
        case TUE:
        case WED:
        case THU:
            System.out.println("일해야지...");
            break;
        case FRI:
            System.out.println("불금");
            break;
        case SAT:
        case SUN:
            System.out.println("쉬어야지");
            break;
        default:
            System.out.println("월월요일");
        }
            
            
        
    }

}
