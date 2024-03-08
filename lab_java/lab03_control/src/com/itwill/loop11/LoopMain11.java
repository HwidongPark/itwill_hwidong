package com.itwill.loop11;

public class LoopMain11 {

    public static void main(String[] args) {
        // ex1. 1부터 10까지 자연수들의 합: sum = 1 + 2 + .. +  10 = 55
        int sum = 0;
        for (int i = 1; i <= 10; i++) {
            sum += i;
            System.out.println("sum = " + sum);
        }
        
        System.out.println("1~10까지 수들의 합은 >>> " + sum );
        
        
        // 문제 2. 1부터 100가지 자연수들 중에서 짝수들의 합을 출력:
        // sum = 2 + 4 + 6 + 8 + ... + 98 + 100 = ?
        
        // 문제 2번 for문 사용한 풀이
        sum = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                sum += i;           
            }

        }
        
        System.out.println("1~100까지 짝수들의 합은 >>> " + sum );
        
        // 문제 2번 while문 사용한 풀이
        sum = 0;
        int n = 1;
        
        while (n <= 100) {
            if (n % 2 == 0) {
                sum += n;
            }
            n++;
        }
        
        System.out.println("1~100까지 짝수들의 합은 >>> " + sum );
        
        
        
        // 문제 3. 1부터 100까지 자연수들 중에서 홀수들의 합을 출력:
        // sum = 1 + 3 + 5 + 7 + ... + 97 + 99 = ?
        
        //문제 3번 for문 사용한 풀이
        sum = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 1) {
                sum += i;              
            }

        }
        
        System.out.println("1~100까지 홀수들의 합은 >>> " + sum );
        
        //문제 3번 while문 사용한 풀이
        sum = 0;
        n = 1;
        
        while (n <= 100) {
            if (n % 2 == 1) {
                sum += n;
            }
            n++;
        }
        
        System.out.println("1~100까지 홀수들의 합은 >>> " + sum );
    
        


    }

}
