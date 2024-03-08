package com.itwill.modifier02;

import com.itwill.modifier01.AccessTest;
// 다른 패키지에서 선언된 클래스 이름으로 변수를 선언하거나 사용하려고 할 때
// (1) import 문장을 작성하고, 클래스 이름만 사용.
// (2) 패키지이름을 포함한 클래스 전체 이름을 사용할 수도 있음. - import 문장이 필요 없음.
//      com.itwill.modifier01.AccessTest
// (3) java.lang 패키지에 포함된 클래스들은 import문장 없이 클래스 이름을 사용할 수 있음
//      예) java.lang.String,    java.lang.System,   java.lang.Math, ...


public class ModifierMain02 {

    public static void main(String[] args) {
        // AccessTest 타입 객체 생성
        AccessTest test = new AccessTest(1, 2, 3, 4);
        System.out.println(test.d);     // public인 변수만 보임
//        System.out.println(test.c);   // -> compile error: 보이지 않음(not visible)
        test.d = 100;   // 다른 패키지에 있는 클래스 에서는 public으로 공개된 멤버만 사용가능
        test.info();
        
        
        
    }

}
