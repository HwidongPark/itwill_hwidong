package com.itwill.class04;

public class Score {
    
    // 필드
    int korean;
    int english;
    int math;
    
    // 생성자: 기본생성자, 아규먼트를 갖는 생성자
    Score() {
        
    }
    
    Score(int korean, int english, int math) {
        this.korean = korean;
        this.english = english;
        this.math = math;
    }
    
    //매서드: 총점 리턴(getTotal), 평균 리턴(getMean).
    public int getTotal() {
        return korean + english + math;
    }
    
    public double getMean() {
        return (double) getTotal() / 3;
    }
    
    
}
