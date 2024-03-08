package com.itwiill.exception03;

import java.util.Scanner;

public class ExceptionMain03 {
    
    private Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        // TODO ExceptionMain03 타입의 객체 생성
        ExceptionMain03 app = new ExceptionMain03();
        
        int x = app.inputInteger();
        
        System.out.println("x = " + x);
    }   // main method end
    
    
    public int inputInteger() {
        int result = 0;
        
        while (true) {
            try {
                System.out.print("정수 입력 > ");
                result = Integer.parseInt(sc.nextLine());
                break;
            } catch(NumberFormatException e) {
                System.out.println("입력한 값은 정수가 아닙니다.");
            }
        }
        
        return result;
    }
    
    public int inputInteger2() {
        int result= 0;
        try {
            return Integer.parseInt(sc.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("입력한 값은 정수가 아닙니다.");
            return inputInteger2();
        }
    }
     
}
