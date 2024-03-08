package com.itwill.set04;

import java.util.HashSet;

public class SetMain04 {

    public static void main(String[] args) {
        // Score 타입 객체를 저장할 수 있는 HashSet선언, 생성.
        HashSet<Score> scores = new HashSet<>();
        // set에 3개의 Score 객체 저장.
        scores.add(new Score(100, 90, 80));
        scores.add(new Score(100, 80, 90));
        
        // 같다고 여겨져서 추가 안되는 객체
        scores.add(new Score(100, 90, 80));
        
        scores.add(new Score(100, 100, 100));
        
        System.out.println(scores);

    }

}
