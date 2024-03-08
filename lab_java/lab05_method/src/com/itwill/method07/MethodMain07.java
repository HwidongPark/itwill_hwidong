package com.itwill.method07;

public class MethodMain07 {


    public static void main(String[] args) {
        // 가변 길이 인수(variable -length argument)
        // 메서드를 호출할 때 아규먼트의 개수가 정해져 있지 않은 경우.. 이러한 함수를 만드는 일은 많지는 않을 것이다.
    
        System.out.printf("안녕하세요.\n");
        System.out.printf("안녕하세요, 저는 %s입니다.\n", "박기모");
        System.out.printf("%s는 %d살 입니다.\n","박기모", 3);
        
        int addedNumber = addAll();
        System.out.println("합: " + addedNumber);
        
        addedNumber = addAll(1, 2, 3, 4);
        System.out.println("합: " + addedNumber);
        
        System.out.println("====================================");
        
        int calculator = calculator("+", 2, 3, 4);
        System.out.println("계산된 값: " + calculator);
        
        int instructorsCalculator = calculate("+", 1, 2, 4);
        System.out.println("계산된 값: " + instructorsCalculator);
        
    }       // main end
    
    
    // 가변길이 인수를 갖는 매서드를 선언(정의)하는 방법:
    // 가변인수를 저장하는 파라미터는 "변수타입...변수이름" 형식으로 선언.
    // 가변인수는 메서드 안에서 배열처럼 취급.
    
    // (주의 1) 가변인수를 저장하는 파라미터는 반드시 가장 마지막 파라미터여야 함.
    // (주의 2) 가변 인수를 저장하는 파라미터는 2개 이상 있을 수 없음.
    // 가변 인수를 갖는 메서드를 사용할 때는 전달하는 아규먼트 개수의 제한이 없음.
    // - 가변 인수 위치에 아규먼트를 전달하지 않아도 됨
    
    
    /**
     *  아규먼트로 전달받은 모든 정수들을 다 더해서 리턴.
     *  아규먼트가 없는 경우에는 0을 리턴
     *  
     *  @param args 더해줄 정수들. 개수 제한 없음.
     *  @return 0 또는 args들의 합
     */
    
    /**
     * @param args
     * @return
     */
    /**
     * @param args
     * @return
     */
    /**
     * @param args
     * @return
     */
    public static int addAll(int... args) {
        int result = 0;
        
        // 배열인수 args는 배열처럼 생각하자
        for (int num : args) {
            result += num;
        }
        
        return result;
    }
    
    
    /**
     * calculator
     * argument op의 값이 "+"인 경우, 가변 인수 values들의 합을 리턴. values가 없으면 0 리턴.
     * argument op의 값이 "*"인 경우, 가변 인수 values들의 곱을 리턴. values가 없으면 1 리턴
     * argument op의 값이 "+"나 "*"가 아닌 경우에는 0을 리턴.
     * 
     * @param op 연산자(operator). "+" 또는 "*"
     * @param values 가변 인수. 정수(int).
     * @return values의 합 또는 곱.
     */
    public static int calculator(String operator, int... numbers) {
        int result = 0;
        
        if (operator == "*") {
            result = 1;
        }
        
        for (int number: numbers) {
            if (operator == "+") {
                result += number;
            } else if (operator == "*") {
                result *= number;
            }
            
        }
        
        return result;
        
    }
    
    // 강사님 풀이
    public static int calculate(String op, int...values) {
        int result = 0;
        
        switch(op) {
        case "+":
            for (int x: values) {
                result += x;
            }
            break;
            
        case "*":
            result = 1;
            for (int x : values) {
                result *= x;
            }
            break;
        }
        
        return result;
           
    }

    
}   //Class end
