package com.itwill.inner01;


// 내부 클래스 이름 import
import com.itwill.inner01.Outer.Inner;
import com.itwill.inner01.Enclosing.Nested;

/*
 * 변수 선언 위치:
 * 1. 필드: 클래스의 멤버로 선언하는 변수. 접근 수식어(private, public, ...)를 사용할 수 있음.
 *     (1) 인스턴스 필드: static이 아닌 필드.
 *         - 객체를 생성한 후에, 그 참조변수로 접근해서 사용하는 필드.
 *     (2) 클래스 필드: static으로 선언된 필드.
 *         - 프로그램이 시작될 때, 클래스 로더에 의해서 메모리에 미리 로딩되는 변수.
 * 2. 지역 변수: 메서드 안에서 선언하는 변수.
 *     - 선언된 위치에서부터 변수가 포함된 블록이 끝날 때까지 사용 가능.
 *     - 파라미터도 지역변수의 한 종류.
 *     - 지역변수는 static을 사용할 수 없음.
 *     
 *     
 * 외부 클래스/인터페이스(outer, enclosing class/interface):
 *   내부 클래스/인터페이스를 감싸고 있는 클래스/인터페이스.
 *   
 */

public class InnerMain01 {

    public static void main(String[] args) {
        // Outer 클래스 타입 객체 생성
        Outer outer1 = new Outer(1, 2, "java");
        System.out.println(outer1);
        
        // Outer.Inner 클래스 타입 객체 생성
        Outer.Inner inner1 = outer1.new Inner(100);
        inner1.info();
        
        Outer.Inner inner2 = outer1.new Inner(123);
        inner2.info();
        
//        System.out.println(inner1.getX());  // -> 상속과 내부 클래스의 다른 점.
        
        Outer outer2 = new Outer(0, 0, "점심시간");
        System.out.println(outer2);
        
        // import 문장이 있는 경우
        Inner inner3 = outer2.new Inner(111);
        inner3.info();
        
        
        // Enclosing 클래스의 static 멤버 사용
        System.out.println(Enclosing.var);
        Enclosing.test();
        
        
        // Enclosing 타입 객체 선언
        Enclosing en = new Enclosing(100);
        System.out.println(en); // en.toString() 확인
        
        // Enclosing.Nested 타입 선언
        Enclosing.Nested nested = new Enclosing.Nested(111);
        nested.info();
        
        // import 문장이 있는 경우
        Nested nested2 = new Nested(222);
        nested2.info();
        
    }

}
