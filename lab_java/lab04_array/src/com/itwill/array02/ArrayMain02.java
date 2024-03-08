package com.itwill.array02;

import java.util.Random;

public class ArrayMain02 {

    public static void main(String[] args) {
        // 시험 점수를 저장하는 배열의 점수들을 저장하고,
        // 배열의 내용을 출력, 총점 계산 출력, 평균 계산 출력.
        Random random = new Random();
        
        // 1. 정수 10개를 저장할 수 있는 배열(scores) 선언.
        int[] scores = new int[10];
        
        // 2. for 구문 이용해서 배열 scores에 정수 10개를 저장.
        // 점수는 0~10의 정수 난수
        
        // 3. 배열에 저장된 점수들을 한 줄에 출력
        
        System.out.print("scores: ");
        for (int i = 0; i < scores.length; i++) {
            scores[i] = random.nextInt(11);
            if (i != scores.length - 1) {
                System.out.print(scores[i] + ", ");
            } else {
                System.out.print(scores[i]);
            }
        }
        
        
        System.out.println();
        

        
        // 4. 배열의 모든 점수들의 합계를 계산하고 출력
        int scoresSum = 0;
        for (int score : scores) {
            scoresSum += score;
        }
        
        System.out.println("점수 합: " + scoresSum);
        
        // 5. 평균을 계산하고 출력.
        double scoresMean = 0;
        scoresMean = (double) scoresSum / scores.length;
        System.out.println("평균: " + scoresMean);

    }

}
