package com.itwill.array04;

import java.util.Random;

public class ArrayMain04 {
    
    public static void main(String[] args) {
        
        int max = 0;
        int[] array = {1, 5, 3, 8, 2};
        int min = array[0];
        
        for (int num : array) {
            if (num >= max) {       //더 큰 숫자가 있으면 그 숫자를 max에 할당
                max = num;
                
            }
            
            if (num <= min) {       // 더 작은 숫자가 있으면 그 숫자를 min에 할당
                min = num;
            }
            
        }
        
        
        System.out.println("max: " + max);
        System.out.println("min: " + min);
        
        
        // 강사님 풀이 (문제 약간 변형해서 풀 예정)
        // 정수(int) 5개를 저장할 수 있는 배열을 선언 & 초기화
        int[] arrayAnswer = new int[5];
        
        // 배열에 정수 난수 ([0,11))들을 저장:
        Random random = new Random();           // ********기본 타입이 아닌 type들의 참조는 항상 new라는 것을 쓴다 ***********
        
        for(int i = 0; i < array.length; i++) {
            arrayAnswer[i] = random.nextInt(11);
                    
        }
        
        
        // 배열의 내용을 출력:
        for (int num : arrayAnswer) {
            System.out.print(num + " ");
            
        }
        max = arrayAnswer[0];
        min = arrayAnswer[0];
        System.out.println();
        
        // 배열 원소들 중 최댓값 찾기:
        for (int num : arrayAnswer) {
            if (num >= max) {       //더 큰 숫자가 있으면 그 숫자를 max에 할당
                max = num;
                
            }
            
            min = (num < min) ? num : min;      // 더 작은 값으로 min의 값을 변경
            
        }
        
        System.out.println("max: " + max);
        System.out.println("min: " + min);
        

        
        
        
    }
    
}
