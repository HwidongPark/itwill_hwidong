package com.itwill.loop04;

public class LoopMain04 {

    public static void main(String[] args) {
        /* 369게임: 아래와 같이 출력하세요.
            1   2   *   4   5   *   7   8   *   10
            11  12  *   14  15  *   17  18  *   20
            21  22  *   24  25  *   27  28  *   *
            *   *   *   *   *   *   *   *   *   40
            41  42  *   44  45  *   47  48  *   50
            51  52  *   54  55  *   57  58  *   *
            *   *   *   *   *   *   *   *   *   70
            71  72  *   74  75  *   77  78  *   80
            81  82  *   84  85  *   87  88  *   *
            *   *   *   *   *   *   *   *   *   100
         */
        
        
        // 두번째 풀이
        for (int i = 1; i <= 100; i++) {
            
            if ((i >= 30 && i<40) || (i >= 60 && i < 70) || (i >= 90 && i <100)) {  // 30, 60, 90대 * 표시
                System.out.print("*");
            } else if ((i - 10 * (i / 10)) == 3 || (i - 10 * (i / 10)) == 6 || (i - 10 * (i / 10)) == 9) {      // 끝자리 3, 6, 9 * 표시
                System.out.print("*");
            } else {        // 그 외 숫자 그대로 출력
                System.out.print(i);
            }
            
            System.out.print("\t");     // * 또는 숫자간 간격 벌림
            
            // 10마다 줄 바꿈
            if (i % 10 == 0) {      // 10단위로 줄 바꿈
                System.out.println();
            }
        }
        
        System.out.println("-------------------------------------------------------------------------------------------------");
        
         //강사님 풀이
        for (int n = 1; n <= 100; n++) {
            int n1 = n % 10;    //1의자리 수
            int n10 = n / 10;    // 10으로 나눈 몫 ==> 10의 자리수.
            
            // 1의 자리 숫자 3, 6, 9이면 true, 그렇지 않으면 false가 되는 변수.
            boolean b1 = (n1 == 3) || (n1 == 6) || (n1 == 9);
            
            // 10의 자리 숫자가 3, 6, 9이면 true, 그렇지 않으면 false가 되는 변수.
            boolean b10 = (n10 == 3) || (n10 == 6) || (n10 == 9);
            
            if(b1 || b10) { //1의자리 숫자가 3, 6, 9이거나, 10의자리 숫자가 3, 6, 9인 경우
                System.out.print("*\t");
            } else {
                System.out.print(n + "\t");
            }
            
            if (n1 == 0) {
                System.out.println();
            }
        }
        
        
        System.out.println("-------------------------------------------------------------------------------------------------");
        
        //만약 1의자리 10의자리 모두 3, 6, 9 표기하고 싶으면.. 예) 33을 **로 표기하고, 13을 *로 표기함
        for (int n = 1; n <= 100; n++) {
            int n1 = n % 10;    //1의자리 수
            int n10 = n / 10;    // 10으로 나눈 몫 ==> 10의 자리수.
            
            // 1의 자리 숫자 3, 6, 9이면 true, 그렇지 않으면 false가 되는 변수.
            boolean b1 = (n1 == 3) || (n1 == 6) || (n1 == 9);
            
            // 10의 자리 숫자가 3, 6, 9이면 true, 그렇지 않으면 false가 되는 변수.
            boolean b10 = (n10 == 3) || (n10 == 6) || (n10 == 9);
            
            if(b1 && b10) { //1의자리 숫자가 3, 6, 9이거나, 10의자리 숫자가 3, 6, 9인 경우
                System.out.print("**\t");
            } else if (b1 || b10) {
                System.out.print("*\t");
            } else {
                System.out.print(n + "\t");
            }
            
            if (n1 == 0) {
                System.out.println();
            }
        }
        
        
    }
        

}