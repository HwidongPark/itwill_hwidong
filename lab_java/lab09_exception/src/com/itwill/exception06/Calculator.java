package com.itwill.exception06;

/*
 * 예외 클래스들의 상속 관계:
 * Object
 * |__ Throwable
 *      |__Exception
 *          |__ RuntimeException, IOException, ...
 *              |__ArithmeticException, NullPointerException, NumberFormatException, ...
 *              
 * thorws 선언문이 있는 메서드들 중에서 RuntimeException과 그 하위 예외 클래스들을 던지는(throws)
 * 메서드들은 try-catch를 사용하지 않아도 컴파일 가능.
 * RuntimeException이 아닌 예외를 던진다고 선언한 메서드들은 반드시  try-catch를 사용해야 함.
 */

public class Calculator {
    
    public int divide(int x, int y) throws Exception {
        if (y != 0) {
            return x / y;   // 메서드를 호출한 곳에 값을 반환
        }
        
        // 메서드를 호출한 곳에 예외를 던짐.
        throw new Exception("y는 0이 될 수 없습니다.");
    }
    

}
