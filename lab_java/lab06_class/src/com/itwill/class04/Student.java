package com.itwill.class04;

public class Student {
    // 필드
    int studentNo;      //학생 번호
    String studentName;     //학생 이름
    Score score;        //학생 수강 과목(국어, 영어, 수학) 점수
    
    // 생성자: 기본 생성자, 아규먼트를 갖는 생성자
    public Student() {
        this.score = new Score();
    }
    
    public Student(int studentNo, String studentName, int korean, int english, int math) {
        this.studentNo = studentNo;
        this.studentName = studentName;
        this.score = new Score(korean, english, math);
    }



    // 메서드: 학생의 정보를 출력하는 메서드
    // 번호, 이름, 국어 점수, 영어 점수, 수학 점수, 총점, 평균을 출력.
    public void studentInformation() {
        System.out.println("학생 번호: " + studentNo + "\n학생 이름:" + studentName + "\n국어 점수: " + score.korean + "\n영어 점수: " + score.english + "\n수학 점수: " + score.math
                + "\n총점: " + score.getTotal() + "\n평균: " + score.getMean());
        
    }
    
    
    
}
