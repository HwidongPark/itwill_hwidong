package com.itwill.class01;

public class AppMain01 {

    public static void main(String[] args) {
        // 클래싀: 데이터 타입.
        
        // 생성자(constructor): 클래스에 선언된 변수(필드)들을 초기화.
        // blueprint를 가지고 객체를 만듦. field들에 값을 넣어주는 등..
        
        Subject sub1 = new Subject();        // 생성자를 호출. 객체 생성. 인스턴스화.
        // 여기서 지역변수는 subject이며 객체(Subject())가 생성된 메모리 시작주소를 저장.
        // Subject subject에서 Subject는 reference type인데, 이걸 굳이 사용해야 하는 이유가 "시작점 부터 몇 바이트를 봐야하는지"알려주기 위함이다.
        
        System.out.println(sub1);
        
        // 앞으로 .은 "가지고 있는"이라는 식으로 생각하면 된다.
        // . 은 참조연산자(reference operator)라고 한다.
        System.out.println(sub1.sql);
        System.out.println(sub1.java);
        System.out.println(sub1.js);
        
        sub1.java = 100;
        sub1.sql = 100;
        sub1.js = 50;
        System.out.println(sub1.java);
        
        Subject sub2 = new Subject(50, 60, 70);
        
        System.out.println(sub2.sql);
        System.out.println(sub2.java);
        System.out.println(sub2.js);
        
        // 함수형 프로그래밍(functional programming)
        int total = getTotal(sub1);
        System.out.println("sub1 total = " + total);
        System.out.println("sub2 total = " + getTotal(sub2));
        
        
        // 객체지향 프로그래밍(Object-oriented programming)
        System.out.println("sub1 total = " + sub1.getTotal());
        System.out.println("sub1 mean = " + sub1.getMean());
        System.out.println("sub2 total = " + sub2.getTotal());
        System.out.println("sub2 mean = " + sub2.getMean());
        
        
    }       // main method end

    
    // Subject 타입의 객체를 아규먼트로 전달받아서 세 과목의 총점을 반화하는 기능.
//     이 기능은 오로지 Subject type만 필요한 기능.. 그래서 굳이 여기서 method를 만드는 것 보다 Subject class에 만드는 게 더 낫다.
    public static int getTotal(Subject subject) {
        return subject.java + subject.sql + subject.js;
    }
    
    
}       // AppMain01 class end