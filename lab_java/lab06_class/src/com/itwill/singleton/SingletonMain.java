package com.itwill.singleton;

// singleton(싱글톤): 오직 한 번만 생성할 수 있는 객체.
// 데이터베이스에서 읽어온 데이터들을 관리하는 클래스들은 singleton인 경우가 많다.

public class SingletonMain {

    public static void main(String[] args) {
        // Captain 타입 객체 생성
        Captain captain1 = Captain.getInstance();
        System.out.println("captain1: " + captain1);
        System.out.println(captain1.getName());
    
        Captain captain2 = Captain.getInstance();
        System.out.println("captain2: " + captain2);
        
        captain2.setName("못말리는 신짱구");
        
    }

}
