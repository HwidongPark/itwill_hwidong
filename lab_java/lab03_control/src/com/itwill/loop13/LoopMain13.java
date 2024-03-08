package com.itwill.loop13;

import java.util.Scanner;


public class LoopMain13 {
    public static void main(String[] args) {
        
        //5번
        System.out.println("====================5번====================");
        
        String astrisk = "";
        for (int i = 1; i <= 5; i++) {
            astrisk += "*";
            System.out.println(astrisk);
        }
        
        // 강사님 풀이       ==> 여기서 x는 라인의 숫자, y는 출력하려는 별표의 숫자
        for (int x= 1; x <= 4; x++) {           // ***TIP*** n번 반복해야하는 경우 for문을 활용하면 편하다.
            for (int y = 1; y <= x; y++) {      // 즉, 이중반복문을 활용해서 안쪽의 반복문을 바깥의 반복문의 숫자와 동일하게 하여 만듦..
                System.out.print("*");
            }
            System.out.println();
        }
        
        
        
        //6번
        System.out.println("====================6번====================");
        
        astrisk = "";
        String space = "";
        
        int numRepeat = 4;
            
        for (int i = 1; i <= 5; i++) {
            space = "";
            astrisk += "*";
            
            for (int n = 1; n <= numRepeat; n++) {
                space += " ";
            }
            
            System.out.println(space + astrisk);
            
            numRepeat -= 1;
            
        }
        
        // 강사님 풀이: 여기서의 포인트는 무조건 4개를 출력하는 것(다만 출력하는 종류가 2가지)
        for (int x = 1; x <= 4; x++) {
            for (int y = 1; y <= 4; y++) {
                if (y <= 4 - x) {           // 서로다른  두 가지 종류를 일정한 개수만큼 출력해야할 때 유용한 테크닉..... x <= n - y 
                    System.out.print(" ");  // 왜 이 생각을 못 했을까
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
        
        // 또 다른 강사님의 풀이
        for (int x = 1; x <= 4; x++) {
            for (int y = 4; y >= 1; y--) {      // 공백과 별표의 개수를 4개로 고정시킴
                if (y > x) {                    // if문으로 각 줄마다 공백과 별표의 개수를 조정
                    System.out.print(" ");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
        
        
        
        
        //7번
        System.out.println("====================7번====================");
        
        boolean run = true;
        int balance = 0;
        Scanner scanner = new Scanner(System.in);
        
        String userInput;
        
        while (run) {
            System.out.println("--------------------------------------");
            System.out.println("1. 예금 | 2. 출금 | 3. 잔고 | 4. 종료");
            System.out.println("--------------------------------------");
            System.out.print("선택 >>> ");
            
            // 숫자 입력을 받음 (1 ~ 4)
            userInput = scanner.nextLine();
            
            // switch문을 사용하여 유저 인풋에 따라 어떤 작동을 할지 결정
            switch (userInput) {
            case "1":
                System.out.print("예금액 >>> ");
                int deposit = scanner.nextInt();
                
                // balance 업데이트
                balance += deposit;
                System.out.printf("예금을 %d원 만큼 합니다.\n잔고:%d\n", deposit, balance);
                break;
                
            case "2":
                System.out.print("출금액 >>> ");
                int withdrawal = scanner.nextInt();
                
                // balance업데이트
                balance -= withdrawal;
                System.out.printf("출금을 %d원 만큼 합니다.\n잔고:%d\n", withdrawal, balance);
                break;
                
            case "3":
                System.out.printf("당신의 잔고는 %d원 입니다.\n", balance);
                break;
                
            case "4":
                run = false;
                break;
                
            default:
                System.out.println("잘못 입력하셨습니다. 1 ~ 4 번 중 하나를 입력하세요.");
                
            }
            
        }
        
        System.out.println("프로그램 종료");
        
        scanner.close();
        
    }

}
