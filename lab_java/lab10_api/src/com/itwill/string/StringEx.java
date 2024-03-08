package com.itwill.string;

public class StringEx {

    public static void main(String[] args) {
        // String 클래스의 메서드 사용법
        
        // ex1. 주민번호 문자열에서 성별을 표시하는 위치의 문자를 출력.
        String ssn = "990904-1234567";
        System.out.println(ssn.charAt(7));

        // ex2. 아래의 문자열 배열에서 5글자 이상인 문자열들만 출력.
        // 결과: JavaScript Servlet Spring
        String[] array = {"Java", "SQL", "JavaScript", "HTML", "CSS", "Servlet", "Spring"};
        System.out.print("문제 2번: ");       
        for (String element : array) {
            if (element.length() >= 5) {
                System.out.print(element + " ");
            }
        }
        System.out.println();
        // ex3. 아래의 문자열 배열에서 "홍길동" 문자열이 처음 등장하는 인덱스를 출력.
        // 만약에 "홍길동"이 배열에 없는 경우에는 -1을 출력.
        // 결과: 2
        String[] names = {"오쌤", "John", "홍길동", "Gildong", "홍길동"};
        int index = -1;
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals("홍길동")) {
                index = i;
                break;
            }
        }
        System.out.println("문제 3번: " + index);
        // ex4. 아래의 문자열 배열에서 대소문자 구별없이 "est"를 포함하는 문자열들을 출력.
        // 결과: TEST test TeSt tEsT
        String[] tests = {"TEST", "test", "TeSt", "tEsT", "테스트"};
        System.out.print("문제 4번: ");
        for (int i = 0; i < tests.length; i++) {
            if (tests[i].toLowerCase().contains("est")) {
                System.out.print(tests[i] + " ");
            }
        }
        System.out.println();
        
        // ex5. 아래의 "YYYY-MM-DD" 형식의 날짜 문자열에서 년/월/일 정보를 int 타입 변수에 저장하고 출력.
        // 결과: year=2023, month=10, day=4
        String date = "2023-10-04";
        String[] dateArray = date.split("-");
        int year = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int day = Integer.parseInt(dateArray[2]);
        
        System.out.println("문제 5번: year=" + year + ", month=" + month + ", day=" + day);
        
    }   // main method 끝

}