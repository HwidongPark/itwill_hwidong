package com.itwill.loop07;

public class LoopMain07 {
    
    public static void main(String[] args) {
        
        // 구구단 출력: 
        // 2단은 2 x 2 가지만, 3단은 3 X 3까지만, ..., n단은 n x n까지만 , ... , 9단은 9x9까지만
        
        
        //풀이 1
        for (int dan = 2; dan <= 9; dan++) {        // n단
            System.out.println("============" + dan + "단============");
            for (int i = 1; i <= dan; i++) {        // 곱하기를 n단 dan까지만 시키기
                System.out.println(dan + " x " + i + " = " + (dan * i));
            }
        }
        
        
        //풀이 2
        for (int dan = 2; dan <= 9; dan++) {
            System.out.println("============" + dan + "단============");
            for (int i = 1; i <= 9; i++) {
                if (i > dan) {      // 단보다 높으면 탈출
                    break;
                } 
                System.out.println(dan + " x " + i + " = " + (dan * i));
            }
        }
        
    }

}