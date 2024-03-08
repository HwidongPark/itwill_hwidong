package com.itwill.loop08;
    
public class LoopMain08 {
    public static void main(String[] args) {
        // while반복문 
        int n = 1;      //시작,,,, --> 지역변수가 선언된 위치가 바깥이기 때문에 loop가 끝나도 그 지역변수가 그대로 남아있음
        while (n <= 5) {        //반복할 조건
                //반복할 문장(들)
            System.out.println(n);
            n++;
        }
        
        System.out.println("==========[2]==========");
        
        // 5 4 3 2 1 순서로 출력(while문 활용해서)
        n = 5;
        while (n >= 1) {
            System.out.print(n + " ");
            n--;
        }
        
        System.out.println();
        
        System.out.println("==========[3]==========");
        // 0, 2, 4 ,6, 8, 10 출력
        n = 0;
        while (n <= 10) {
            System.out.print(n + " ");
            n += 2;
        }
        
        // 다른 풀이
        System.out.println();
        n = 0;
        while (n <= 5) {
            System.out.print((2 * n) + " ");
            n++;
        }
        
        
        System.out.println();
        
        System.out.println("==========[4]==========");
        // 9 7 5 3 1 출력
        n = 9;
        while ( n >= 1) {
            System.out.print(n + " ");
            n -= 2;
        }
        
        System.out.println();
        
        // 다른 풀이
        n = 9;
        while (n > 0) {
            if (n % 2 != 0) {
                System.out.print(n + " ");
            }
            
            n--;
        }
        
        System.out.println();

        // 구구단 9단 출력
        n = 2;
        while (n <= 9) {
            int i = 1;
            while (i <= 9) {
                System.out.println(n + " x " + i + " = " + (n * i));
                i++;
            }
            
            n++;
            
        }
        
        // ***꿀팁*** ALT + 위아래 방향키 -> 코드를 한 줄씩 위, 아래로 이동시켜준다.
    
        

    }
    

}