package com.itwill.array03;

import java.util.Random;

public class ArrayMain03 {
    
    public static void main(String[] args) {
        // 향상된 for 구문은 배열에 저장된 값들을 순서대로 읽는 경우에만 사용.
        // (주의) 일반적으로 향상된 for 구문은 배열의 원소를 저장/변경할 때 이용할 수 없음.
        // 배열의 원소를 저장/변경할 때는 반드시 "배열[인덱스] = 값;" 구문을 이용해야 한다.
        
        Random random = new Random();
        
        int[] numbers = new int[3];     // 정수(int) 3개를 저장할 수 있는 배열.
        for (int number : numbers) {
            number = 10;
            
        }
        
        for (int number : numbers) {
            System.out.print(number + " ");
        }
        
        System.out.println();
        
        // (예제)
        // (1) 정수(int) 10개를 저장할 수 있는 배열을 만들고,
        int len = 10;       // 배열의 길이(원소 개수)로 사용할 변수.
        
        int[] idCodes = new int[len];

        // (2) 1 이상 4이하 (5 미만)의 정수들을 임의로 저장하고 그 내용을 출력.
        System.out.print("임의 생성 숫자: ");
        
        for (int i = 0; i < len; i++) {
            idCodes[i] = random.nextInt(1, 5);
            if (i < 9) {
                System.out.printf("[%d] %d,  ",(i + 1) ,idCodes[i]);
            } else {
                System.out.printf("[%d] %d", (i + 1), idCodes[i]);
            }
            
        }
        
        System.out.println();
        // (3) 문자열 10개를 저장할 수 있는 배열을 만들고,
        String[] genders = new String[len];
        
        // (4) (1)에서 만든 배열의 원소가 1 또는 3이면 "남성"을 저장,
        //      2 또는 4이면 "여성"을 저장.
        System.out.print("대응되는 성별: ");
        
        for (int i = 0; i < len; i++) {
            switch (idCodes[i]) {
            case 1: case 3:
                genders[i] = "남성";
                break;
            case 2: case 4:
                genders[i] = "여성";
                break;
            }
            
            if (i < 9) {
                System.out.printf("[%d]%s, ", (i + 1), genders[i]);    
            } else {
                System.out.printf("[%d]%s", (i + 1), genders[i]);
            }     
            
        }
        
        System.out.println();
        
    }

}
