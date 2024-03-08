package com.itwill.condition07;

import java.util.Random;   // 난수를 생성할 수 이쓴 타입
import java.util.Scanner;  // 입력을 받을 수 있는 타입

public class RpsMain {

    public static void main(String[] args) {
        // 가위바위보 게임:
        // 가위 - 0, 바위 - 1, 보 - 2
        // 가정: 사용자는 0, 1, 2 이외의 값은 입력하지 않는다.
        
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        
        // 사용자에게 콘솔에서 키보드로 가위(0)/바위(1)/보(2)를 입력하도록 안내
        System.out.println("가위바위보 게임");
        System.out.println("----------------");
        System.out.println("[0] 가위");
        System.out.println("[1] 바위");
        System.out.println("[2] 보");
        System.out.print("선택>>> ");
        
        // 사용자가 입력한 숫자를 변수에 저장
        int user = scanner.nextInt();
        System.out.println("user: " + user);
        
        //컴퓨터의 선택은 난수로 만듦.
        int computer = random.nextInt(3);
        System.out.println("computer: " + computer);
        
        if (user == computer) {
            System.out.println("무승부");
        } else if (user == 2) {
            if (computer == 1) {
                System.out.println("승리");
            } else {
                System.out.println("패배");
            }
        } else if (user == 1) {
            if (computer == 2) {
                System.out.println("패배");
            } else {
                System.out.println("승리");
            }
        } else {
            if (computer == 2) {
                System.out.println("승리");
            } else {
                System.out.println("패배");
            }
        }
        
    }

}
