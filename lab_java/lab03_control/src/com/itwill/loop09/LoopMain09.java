package com.itwill.loop09;

public class LoopMain09 {
    public static void main(String[] args) {
        // 중첩 while문을 사용해서 구구단을 출력. 2단부터 9단까지
        int n = 2;
        while (n <= 9) {        // 9단이 될때까지
            System.out.println("================[" + n + "단]================");
            
            int i = 1;      // n단 x i에서 i를 1로 초기화
            while (i <= 9) {
                System.out.println(n + " x " + i + " = " + (n * i));
                i++;
            }
            
            n++;        // 단 증가
            
        }
  
                
    }

}
