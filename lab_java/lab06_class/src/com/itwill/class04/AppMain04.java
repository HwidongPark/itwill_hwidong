package com.itwill.class04;

public class AppMain04 {

    public static void main(String[] args) {
        // Score 객체를 기본 생성자를 사용해서 생성.
        Score score1 = new Score();
        System.out.println("score1 = " + score1);
        System.out.println("score1 Korean score = " + score1.korean);
        System.out.println("score1 Total score = " + score1.getTotal());
        
        // Score 객체를 아규먼트를 갖는 생성자를 사용해서 사용
        Score score2 = new Score(100, 50, 70);
        System.out.println("score2 Korean score = " + score2.korean);
        System.out.println("score2 Total score = " + score2.getTotal());
        System.out.println("score2 mean score = " + score2.getMean());
        
        
        // 학생 생성. 아규먼트 있는 constructor
        Student student1 = new Student(5684315, "홍길동", 100, 90, 80);
        student1.studentInformation();
        
        // 기본 생성자를 사용해서 Student 타입 객체 생성.
        Student student2 = new Student();
        student2.studentInformation();

    }

}
