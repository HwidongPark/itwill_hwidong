package com.itwill.loop10;

public class LoopMain10 {

    public static void main(String[] args) {
        // while 문:
        // while (condntion) { ... }
        // while 문장은 반복 블록을 시작하기 전에 (반복) 조건식을 검사하기 때문에,
        // 실행 블록이 한번도 실행되지 않는 경우가 있을 수 있음.
        
        int n = 5;
        while (n < 0) {
            System.out.println(n);
            n--;
        }
        System.out.println("end while");
        
        // do-while 문: 
        // do { ... } while (condition);
        // do-while 문장은 무조건 실행 블록을 먼저 실행하고 난 후
        // 반복을 계속할 지를 결정하기 위해서 조건식 검사를 함.
        // 실제로는 거의 사용하지 않는다함.. 가끔씩 사용되는 경우가 있음
        n = 5;
        do {
            System.out.println(n);
            n--;
        } while (n < 0);
        
        System.out.println("end do-while");
        
    }

}
