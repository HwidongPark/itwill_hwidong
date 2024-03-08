package com.itwill.list02;

import java.util.LinkedList;
import java.util.Random;

public class ListMain02 {

    public static void main(String[] args) {
        
        Random random = new Random();
        // 1. 정수를 저장하는 LinkedList 변수(numbers) 선언, 객체 생성.
        LinkedList<Integer> numbers = new LinkedList<>();
        
        // 2. numbers에 [0, 100) 범위의 정수 난수 20개 저장
        for (int i = 0; i < 20; i++) {
            numbers.add(random.nextInt(100));
        }
        
        // 3. numbers를 출력
        System.out.println(numbers);
        
        // 4. 홀수들을 저장하는 LinkedList 변수(odds) 선언, 객체 생성.
        LinkedList<Integer> odds = new LinkedList<>();
        
        // 5. numbers에 있는 정수들 중에서 홀수들만 odds에 저장.
        for (int number : numbers) {
            if (number % 2 == 1) {
                odds.add(number);
            }
        }
        
        
        // 6. odds를 출력.
        System.out.println(odds);
        
        // numbers의 정수들 중에서 짝수들의 제곱을 저장하는 리스트
        LinkedList<Integer> evenSquares = new LinkedList<>();
        for(Integer n : numbers) {
            if (n % 2 == 0) {
                evenSquares.add(n * n);
            }
        }
        
        System.out.println(evenSquares);
        
        
    }   // main method end

}   // main class end
