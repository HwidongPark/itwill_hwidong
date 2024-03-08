package com.itwill.loop02;

public class LoopMain02 {

    public static void main(String[] args) {
        // 0 2 4 6 8 10으로 출력되도록 for문 작성하기
        for (int i = 0; i <= 10 ; i += 2) {
            System.out.print(i + " ");
        }
        
        System.out.println();
        
        for (int i = 0; i <= 5; i++) {
            System.out.print((i*2) + " ");
        }
        
        System.out.println();
        
        for (int i = 0; i <= 10; i++) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
        }

    }

}
