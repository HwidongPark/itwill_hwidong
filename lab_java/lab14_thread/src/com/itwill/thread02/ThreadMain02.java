package com.itwill.thread02;

/*
 * 자바는 다중 상속을 지원하지 않음!
 *   즉, Class A extends B, C {} -> 문법 오류
 * 다른 클래스를 이미 상속하는 클래스는 Thread를 상속할 수 없음.
 * 자바는 인터페이스 구현은 개수 제한이 없음.
 *   Class A extends B implements C , D, E {}  -> 가능
 *   
 * Thread 사용 방법 2:
 * (1) Runnable 인터페이스를 구현하는 클래스를 선언.
 * (2) (1)에서 선언한 클래스에서 run() 메서드를 재정의(override).
 * (3) Thread Constructor를 호출할 때 (2)에서 선언된 클래스의 객체를 아규먼트로 전달.
 * (4) (3)에서 생성된 Thread객체에서 start() 메서드를 호출 -> Thread실행 됨
 *   
 */

public class ThreadMain02 {

    public static void main(String[] args) {
        
        int test = 0;
        
        // (1) Runnable 인터페이스를 구현하는 클래스 선언
        class MyRunnable implements Runnable {
            
            private String name;
            private MyRunnable(String name) {
                this.name = name;
            }
            
            // (2) static method인 run method를 재정의
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(i + " - " + name);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            
        }
        
        // (3) Runnable 객체를 아규먼트로 갖는 Thread생성자 호출
        Thread th1 = new Thread(new MyRunnable("지역 클래스"));

        // 익명 클래스를 사용한 쓰레드 객체 생성
        Thread th2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(i + " - 익명 클래스");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            
        });
        
        
        // 람다 표현식을 사용한 쓰레드 객체 생성
        Thread th3 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(i + " - 람다 표현식");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        
        // (4) 쓰레드 객체에서 start() 메서드 호출 -> 쓰레드 실행
        long start = System.currentTimeMillis();
        th1.start();
        th2.start();
        th3.start();
        
        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        long end = System.currentTimeMillis();
        System.out.println("실행 소요 시간 = " + (end - start) + "ms");
        
        
        System.out.println("===== main 메서드 종료 =====");
        
        
        
    }

}