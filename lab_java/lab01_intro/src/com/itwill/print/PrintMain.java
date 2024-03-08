package com.itwill.print; // 첫번째 문장은 항상 package declaration.

public class PrintMain {
    
    // main method - 프로그램의 시작
    public static void main(String[] args) {
        
        System.out.println("한 줄 출력");  // print line
        System.out.println("1 + 2");
        System.out.println(1 + 2);
        System.out.println("1 + 2 = " + (1 + 2)); // -> 1 + 2 = 3
        System.out.println("1 + 2 = " + 1 + 2); // -> 1 + 2 = 12
        System.out.print("Hello");  // print의 경우 println와 같이 한 줄 출력이 아니라 옆으로 넘어간다.
        System.out.print("안녕");  // println의 경우 print를하고 cursor가 아랫줄로 가 있는데, print의 경우 그냥 print출력하고 cursor가 그 자리에 대기하고 있다.
        System.out.println("Java");
        
        System.out.printf("문자열 포맷:%d, %f, %s", 100, 3.14, "Hello\n");  // printf에서 f는 format이라는 의미. %d자리에는 정수를 출력, &f는 실수를, %s는 문자열을 출력해라는 의미.. 정수 출력할 자리에 정수, 실수 출력할 자리에
        // 실수, 문자열 출력할 자리에 문자열 순서에 맞게 입력해야 한다. 참고로 printf는 println과 같이 자동으로 줄바꿈 되지 않는다. 줄 바꾸고 싶으면 \n붙여줘야 한다.
        // %d: digit, 정수 출력
        // %f: floating-point number, 실수
        // %s: string, 문자열 의미
        
        System.out.printf("%d + %d = %d\n", 1, 2, 1 + 2 );   
        
    }  // { }curly bracket같은 경우에는 뒤에 ; 써도되고 안서도 됨.

}