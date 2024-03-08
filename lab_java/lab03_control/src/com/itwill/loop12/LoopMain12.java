package com.itwill.loop12;

import java.util.Random;

public class LoopMain12 {

    public static void main(String[] args) {
        // 교재 연습문제 3번(p.161)
        // 주사위 2개를 던졌을 때의 결과를 (주사위1, 주사위2) 형식으로 출력.
        //주사위의 합이 5일 경우 중단.
        
        
        System.out.println("--------------교재 3번(p.161)--------------");
        Random random = new Random();
        int num1;
        int num2;
        String dice = "";
        
        int sum = 0;
        
        while (sum != 5) {
            num1 = random.nextInt(1, 7);
            num2 = random.nextInt(1, 7);
            
            sum = num1 + num2;
            
            if (sum != 5) {
//                dice += "(" + num1 + ", " + num2 + "), ";
                dice += String.format("(%d, %d), ", num1, num2);
            } else {
                dice += String.format("(%d, %d)", num1, num2);
            }     

        }
        
        System.out.println(dice);
        
        
        // 교제문제 4번
        // x, y: 10이하의 자연수이며 4x + 5y = 60의 모든 해를 구해서 (x, y)형태로 출력
        System.out.println("--------------교재 4번(p.161)--------------");

        for (int x = 1; x <= 10; x++) {     // x를 1부터 10까지 1씩 증가시키면서 반복
            for (int y = 1; y <= 10; y++) {     // y를 1부터 10까지 1씩 증가시키면서 반복
                if (4 * x + 5 * y == 60) {      // 방정식의 답이 되는지를 검사
                    System.out.printf("x = %d, y = %d\n", x, y);
                }
            }
            
        }
        
        
    }

}
