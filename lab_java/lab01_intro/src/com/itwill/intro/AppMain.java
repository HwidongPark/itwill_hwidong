package com.itwill.intro;  // 첫 줄은 package선언문으로 시작해야 한다. 우리가 작성하는 것이 com.itwill.intro밑에 있다는걸 선언하는 것..

// 자바의 comment에는 2개가 있다.. 1. inline comment는 //로 시작하고 그 줄의 끝가지.

/*
 * 2. block comment: 얘는 /*가 주석의 시작, 순서를 바꾸면 주석의 끝.. block주석의 같은 경우는 문서를 만들때(문서화주석, documentation comment) - 클래스에 대한 설명, method에 대한 설명 때 주로 사용한다.
*/

public class AppMain {      //Class선언 시작 부분. package선언문 다음에는 class선언문이 있어야 한다.
    
    // main method: 자바 프로그램 시작부분. 프로그램 전체에서 main은 하나 항상 있어야 한다.
    public static void main(String[] args) {
        // comment: 프로그램의 설명.
        System.out.println("안녕하세요.");
        System.out.println("Hello, Java!");
    }  // main method가 종료되면 자바 프로그램이 종료.

}  // class선언 끝 부분.
