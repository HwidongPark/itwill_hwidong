package com.itwill.loop01;

public class LoopMain01 {

    public static void main(String[] args) {
 
        System.out.println("Java 1");
        System.out.println("Java 2");
        System.out.println("Java 3");
        System.out.println("Java 4");
        System.out.println("Java 5");
        System.out.println("----------");
        
        
        // for ( 1번 ; 2번 ; 3번)
        // 1번에서는 보통 변수가 선언되는 부분. 딱 한번만 실행됨
        // 2번은 조건식(condition)
        // 3번은 increment step
        // syntax를 정리해보면 -> for(초기화 문장; 반복 조건식; 증가/감소) {반복할 실행 코드 블록;}
        
        // 만약 code block을 4번이라고 한다면 실행순서는 condition이 만족하지 않을때 까지 다음과 같다. 1 -> 2-> 4 -> 3 -> 2 -> 4 -> 3 -> 2 -> 4 -> ...
        for(int i = 1; i <=5; i++) {
            System.out.println("Java " + i);
        }
        
        // 증가연산자(++), 감소연산자(--): 변수의 값을 1증가/감소.
        // 단항 연산 - 변수 이름 앞 또는 뒤에 사용.
        // x += 1; --> 이런 operator를 복합 할당 연한자라고 한다. +=, -=, *=, /=, %=, ...
        
        System.out.println("-----------");
        // countdown: 5, 4, 3, 2, 1 출력
        for(int i = 5; i >= 1; i--) {
            System.out.println(i);
        }
        

    }

}
