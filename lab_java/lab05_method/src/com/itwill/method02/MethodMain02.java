package com.itwill.method02;

public class MethodMain02 {

    public static void main(String[] args) {
        // 리턴 타입과 아규먼트를 갖는 메서드 작성, 호출
        
        // 같은 클래스의 메서드를 호출할 때는 메서드 이름만으로 호출할 수 있음. 즉, System.out.println()과 같이 따로 불러줄 필요가 없다는 이야기인듯...
        
        double result = add(1.0, 2.0);
        System.out.println(result);
        
        result = subtraction(1, 2);
        System.out.println(result);
        
        result = multiplication(3, 2);
        System.out.println(result);

        result = division(4, 2);
        System.out.println(result);

        
    }   // end main
    
    
    /**
     * 숫자 2개를 전달받아서, 두 숫자의 덧셈 결과를 반환하는 메서드.
     * 
     * @param x double type이다.
     * @param y double type이다.
     * @return x + y를 반환함
     */
    public static double add(double x, double y) {
        return x + y;
    }   // end add
    
    // *******같은 class안에서 만들어진 method는 그냥 method이름만 갖고 사용할 수 있다.*******
    
    
    /**
     * 숫자 2개를 전달 받아서, 첫번째 argument에서 두번째 argument를 뺀 결과를 리턴.
     * @param x double.
     * @param y double.
     * @return x - y를 반환함
     */
    public static double subtraction(double x, double y) {
        return x - y;
    }
    
    /**
     * multiply.
     * 숫자 2개를 전달 받아서 두 수의 곱셈 결과를 리턴.
     * 
     * @param x double
     * @param y double
     * @return x * y
     */
    public static double multiplication(double x, double y) {
        return x * y;
    }
    
    /**
     * division
     * 숫자 2개를 전달 받아서 첫번째 argument를 두번째 argument로 나눈 결과를 리턴.
     * 
     * @param x double.
     * @param y double
     * @return x / y
     */
    public static double division (double x, double y) {
        return x / y;
    }
}   // end class
