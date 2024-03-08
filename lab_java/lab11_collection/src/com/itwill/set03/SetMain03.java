package com.itwill.set03;

import java.util.HashSet;
import java.util.Random;

public class SetMain03 {

    public static void main(String[] args) {
        Random random = new Random();
        // 정수를 저장하는 HasSet을 선언, 객체 생성.
        HashSet<Integer> set = new HashSet<>();
        
        // 난[0,10) 범위의 서로 다른 정수 난수 5개를 set에 저장.
        int countRepeat = 0;
        while(true) {
            if (set.size() == 5) {
                break;
            }
            set.add(random.nextInt(10));
            countRepeat++;
        }
        
        // 그 결과를 출력.
        System.out.println(countRepeat + "번 반복함");
        System.out.println(set);
    }   // main method 끝

}   // main class 끝
