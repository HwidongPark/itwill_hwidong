package com.itwill.condition03;

import java.util.Scanner;  // package statement와 class사이에 import statement가 들어옴... 우리는 java.util이라는 package내의 scanner라는 class사용할 것임

public class ConditionMain03 {
    
    public static void main(String[] args) {
        
        //키보드에서 입력받은 값을 변수에 저장하는 방법: 입력받는다는 method사용해야하고 객체를 불러와야 함.
        // (1) Scanner 타입의 객체 생성 - 입력 도구를 만듦.
        Scanner sc = new Scanner(System.in);  // Scanner type으로 variable을 선언하고, Scanner 객체를 저장
        
        System.out.print("Java점수를 입력해 주세요:  ");
        // (2) 키보드에서 정수를 입력받아서 int 타입 변수에 저장.
        int java = sc.nextInt();   // keyboard에서 int를 입력받는 방법..
        
        
        //SQL 과목의 점수를 입력 받고 변수에 저장.
        // JavaScript과목의 점수를 입력 받고 변수에 저장.
        System.out.print("SQL점수를 입력해 주세요:  ");
        int sql = sc.nextInt();
        
        System.out.print("JavaScript점수를 입력해 주세요:  ");
        int javaScript = sc.nextInt();
        
        //세 과목의 점수를 출력        
        System.out.println("당신의 Java 점수: " + java);
        System.out.println("당신의 SQL 점수: " + sql);
        System.out.println("당신의 JavaScript 점수: " + javaScript);
        
        
        //세 과목의 총점을 계산해서 출력
        int scoreTotal = java + sql + javaScript;
        System.out.println("세 과목의 점수 합: " + scoreTotal);
        
        //세 과목의 평균을 계산해서 출력, 평균값은 소숫점까지 계산
        double scoreAverage = (double) scoreTotal / 3;
        System.out.println("세 과목의 점수 평균: " + scoreAverage);
        
        //세 과목의 평균이 90점 이상이면 A
        //세 과목의 평균이 80점 이상이면 B
        //세 과목의 평균이 70점 이상이면 C
        //그 점수 이하는 F
        if (scoreAverage >= 90) {
            System.out.println("당신의 grade는 \"A\" 입니다.");
        } else if (scoreAverage >= 80) {
            System.out.println("당신의 grade는 \"B\" 입니다.");
        } else if (scoreAverage >= 70) {
            System.out.println("당신의 grade는 \"C\" 입니다.");
        } else {
            System.out.println("당신의 grade는 \"F\" 입니다.");
        }
        
        sc.close();
        
    }

}
