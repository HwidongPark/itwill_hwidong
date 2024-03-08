package com.itwill.class05;

import java.util.Scanner;

public class AppMain05 {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        // TODO: Account 타입 객체 생성를 생성하고 메서드들을 테스트.
        
        Account myAccount = new Account(123456789, 0);      // 내 계좌
        Account randomAccount = new Account(54321, 100_000);        // 다른 계좌
        
        boolean transactionDone = false;
        String transaction;
        
        while (!transactionDone) {       //유저 거래가 끝나면 while loop탈출
            System.out.println("[1] 입금   [2] 출금   [3] 계좌 이체   [4] 계좌 정보 확인   [5] 거래 종료");
            System.out.print("원하는 거래를 선택하십시요.  >>> ");
            transaction = scanner.nextLine();
            
            switch (transaction) {
            case "1":       // 입금
                System.out.print("얼마를 입금하시겠습니까? >>> ");
                int depositAmount = Integer.parseInt(scanner.nextLine());
                
                System.out.printf("현재 잔액은 %d 원 입니다.\n", myAccount.deposit(depositAmount));
                break;
                
            case "2":       // 출금
                System.out.println("얼마를 출금하시겠습니까? >>> ");
                int withdrawalAmount = Integer.parseInt(scanner.nextLine());
                
                System.out.printf("현재 잔액은 %d 원 입니다.\n", myAccount.withdraw(withdrawalAmount));
                break;
                
            case "3":       // 계좌 이체
                System.out.println("이체를 하실 계좌 이름과 금액을 입력해 주세요");
                
                System.out.println("이체할 계좌번호를 입력해 주세요 >>> ");
                int accountNumber = Integer.parseInt(scanner.nextLine());
                
                
                if (randomAccount.accountNumber == accountNumber) {
                    System.out.print("이체 금액 >>> ");
                    int transferAmount = Integer.parseInt(scanner.nextLine());
                    myAccount.transfer(randomAccount, transferAmount);
                } else {
                    System.out.println("매칭되는 계좌 번호가 없습니다.");
                }
                break;
                
            case "4":           // 계좌 확인
                myAccount.checkAccount();
                break;
                
            case "5":       // 거래 종료
                transactionDone = true;
                break;
                
            default:        // 유저가 잘못 입력 했을때
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                        
            }
            
//            if (transaction == "1") {     // 입금
//                System.out.print("얼마를 입금하시겠습니까? >>> ");
//                int depositAmount = Integer.parseInt(scanner.nextLine());
//                
//                myAccount.deposit(depositAmount);
//                
//            } else if (transaction == "2") {          // 출금
//                System.out.println("얼마를 출금하시겠습니까? >>> ");
//                int withdrawalAmount = Integer.parseInt(scanner.nextLine());
//                
//                myAccount.withdraw(withdrawalAmount);
//                
//            } else if (transaction == "3") {          // 계좌 이체
//                System.out.println("이체를 하실 계좌 이름과 금액을 입력해 주세요");
//                
//                System.out.println("이체할 계좌번호를 입력해 주세요 >>> ");
//                int accountNumber = Integer.parseInt(scanner.nextLine());
//                
//                
//                if (randomAccount.accountNumber == accountNumber) {
//                    System.out.print("이체 금액 >>> ");
//                    int transferAmount = Integer.parseInt(scanner.nextLine());
//                    myAccount.transfer(randomAccount, transferAmount);
//                    
//                } else {
//                    System.out.println("매칭되는 계좌 번호가 없습니다.");
//                }
//                
//                
//            } else if (transaction == "4") {          // 계좌 정보 확인
//                myAccount.checkAccount();
//                
//            } else if (transaction == "5") {          // 거래 종료
//                transactionDone = true;
//            } else {            // 잘못된 값 입력. 고객에게 다시 입력 요청
//                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
//            }
//            
//            System.out.printf("현재 잔액은 %d 원 입니다.\n", myAccount.balance);
            System.out.println("===============================================");
            
            
            
        }
        
        System.out.println("오늘도 거래해주셔서 감사합니다.");

    }

}