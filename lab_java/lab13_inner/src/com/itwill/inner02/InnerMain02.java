package com.itwill.inner02;

public class InnerMain02 {

    public static void main(String[] args) {
        // Book 타입 객체 생성
        Book book1 = new Book("일론 머스크", "월터 아이작슨", "21세기북스");
        System.out.println(book1);
        
        Book book2 = new Book("일론 머스크", "월터 아이작슨");
        System.out.println(book2);
        
        Book book3 = new Book("월터 아이작슨", "일론 머스크");
        System.out.println(book3);
        
        
        // Builder 패턴을 이용한 객체 생성
        Book book4 = Book.builder().build();
        System.out.println(book4);
        
        Book book5 = Book.builder().author("월터 아이작슨").build();
        System.out.println(book5);
        
        // 빌더패턴의 장점: 예를 들어 constructor를 사용해서 2개의 parameter를 받을 경우,
        // publisher, title 또는 author, title이런식으로 서로 다른 2개의 파라미터를 받아서 constructor를 만들 수 없음.(overloading문법의 한계)
        // builder를 사용하면 이런 면에서 flexible함.
        Book book6 = Book.builder()
                        .publisher("21세기북스")
                        .title("일론 머스크")
                        .author("월터 아이작슨")
                        .build();
        System.out.println(book6);
                        
        
    }

}
