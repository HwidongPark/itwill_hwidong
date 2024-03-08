package com.itwill.lambda03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LambdaMain03 {

    public static void main(String[] args) {
        
        // Stream 클래스
        
        Random random = new Random();
        
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(random.nextInt(10));
        }
        System.out.println(numbers);
        
        
        // numbers의 원소들 중에서 짝수들만 저장하는 리스트를 만들고 출력.
        ArrayList<Integer> evens = new ArrayList<>();   // 짝수들을 저장하기 위한 리스트를 생성
        for (Integer x : numbers) { // numbers의 모든 정수들을 순서대로 순회하면서 
            if (x % 2 == 0) {
                evens.add(x);   // evens에 저장
            }
        }
        System.out.println(evens);
        
        
        // Stream사용해서 하면 간편함
        // Stream은 통로같은 개념인데 통로에 filter를 껴서 조건에 맞는 것들만 필터를 통과할 수 있도록 하여 다시 리스트로 변환
        // -> 더 간편하게 원하는 값들만 포함하는 리스트를 가질 수 있음
        List<Integer> evens2 = numbers.stream().filter((x) -> x % 2 == 0).toList();
        System.out.println(evens2);
        
        
        
        // numbers의 원소들 중에서 홀수들만 저장하는 리스트를 만들고 출력.
        List<Integer> odds = numbers.stream().filter((x) ->  x % 2 == 1).toList();
        System.out.println(odds);
        
        
        // numbers의 정수들을 제곱한 값들을 원소로 갖는 리스트를 만들고 출력. (스트림 사용x 시)
        ArrayList<Integer> squares = new ArrayList<>(); // 제곱들을 저장할 리스트를 생성
        for (Integer x : numbers) {
            squares.add(x * x); // numbers에서 꺼낸 숫자의 제곱을 squares에 추가.
        }
        System.out.println(squares);
        
        // stream사용해서 제곱한 값들을 원소로 갖는 리스트 만들기
        List<Integer> squares2 = numbers.stream().map(x ->  x * x).toList();
        System.out.println(squares2);
        
        
        // numbers의 원소들 중에서 홀수들의 제곱을 저장하는 리스트를 만들고 출력.
        List<Integer> oddSquares = new ArrayList<Integer>();
        for (Integer x : numbers) {
            if (x % 2 == 1) {
                oddSquares.add(x);
            }
        }
        
        for (int i = 0; i < oddSquares.size(); i++) {
            oddSquares.set(i, oddSquares.get(i) * oddSquares.get(i));
        }
        System.out.println(oddSquares);
        
        // numbers의 원소들 중에서 홀수들의 제곱을 저장하는 리스트를 만들고 출력. Stream 사용
        List<Integer> oddSquares2 = numbers.stream().filter((x) -> x % 2 == 1).map(x -> x * x).toList();
        System.out.println(oddSquares2);
        
        
        List<String> languages = Arrays.asList("Java", "JavaScript", "Python", "Kotlin", "C");
        
        // languages의 문자열들 중에서 5글자 이상인 문자열들을 소문자로 변환한 리스트를 만들고 출력. Stream사용
        List<String> result = languages.stream().filter((s) -> s.length() >= 5).map(String::toLowerCase).toList();
        System.out.println(result);
        
        // 람다 표현식이 아규먼트를 1개만 갖고,
        // 람다의 리턴값이 그 아규먼트에서 파라미터가 없는 메서드 호출 결과인 경우
        // 메서드 참조 방식으로 람다 표현식을 작성할 수 있음
        // (x) -> x.toLowerCase() 와 String::toLowerCase는 같은 람다 표현식.
        // (x) -> x.length()와 String::length는 같은 표현식.
        
        // 람다 표현식이 아규먼트를 1개만 전달 받고,
        // 람다의 구현부가 메서드 1개 호출이고, 그 메서드 람다의 아규먼트를 전달받는 경우,
        // 메서드 참조 방식을 사용할 수 있음
        // (x) -> System.out.println(x)와 System.out::println은 같은 표현식.
        languages.forEach(x -> System.out.println(x));
        languages.forEach(System.out::println);
        
    }   // main method 끝

}   // main class 끝
