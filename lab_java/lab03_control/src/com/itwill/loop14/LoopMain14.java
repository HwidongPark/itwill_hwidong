package com.itwill.loop14;

import java.util.Scanner;

public class LoopMain14 {

    public static void main(String[] args) {
        // Scanner 타입을 사용한 입력 받기: 
        
        Scanner sc = new Scanner(System.in);
        
        
        // nextInt(): 입력버퍼(임시 메모리)에서 토큰(공백, 탭, 엔터)으로 구분된 입력값을 읽어서 정수(int)를 반환
        // 즉, 버퍼가 비어있으면 입력값을 기다리고, 버퍼가 비어있지 않으면 토큰으로 구분된 값들을 순차적으로 반환한다.
        // nextDouble(): 입력버퍼(임시 메모리)에서 토큰(공백, 탭, 엔터)으로 구분된 입력값을 읽어서 실수(double)를 반환
        // next(): 입력버퍼(임시 메모리)에서 토큰(공백, 탭, 엔터)으로 구분된 입력값을 읽어서 문자열(String)을 반환.
        // nextInt(), nextDouble(), next(): 값을 읽은 후에도 입력 버퍼에 남아있는 내용이 있을 수 있음.
        
        
        
        
        System.out.print("정수 입력 >>> ");
        int n1 = sc.nextInt();
        System.out.println("n1 = " + n1);
        
        
        System.out.print("정수 입력 >>> ");
        int n2 = sc.nextInt();
        System.out.println("n2 = " + n2);
        
        System.out.print("문자열 입력 >>> ");
        String s = sc.next();
        System.out.println("s = " + s);
        
        System.out.print("정수 입력 >>> ");
        int n3 = sc.nextInt();
        System.out.println("n3 = " + n3);
        
        
        // nextLine(): 입력 버퍼에서 엔터를 만날 때까지 모든 값을 읽어서 "문자열"을 리턴
        // -> nextLine(); 호출한 이후에는 입력 버퍼의 임시 메모리에 모든 내용이 지워지게됨
        // 앞으로는 어지간하면 nextInt(), nextDouble(), next()사용하지말고 그냥 nextLine() 사용하자.
        
        sc.nextLine();      //위의 next~~~()쓰면서 남아있는 \n을 지움
        System.out.print("문자열 입력 >>> ");
        String s1 = sc.nextLine();
        System.out.println("msg = " + s1);
        
        System.out.print("실수 입력 >>> ");
        double n4 = sc.nextDouble();
        System.out.println("n4 = " + n4);
    
        
    }

}
