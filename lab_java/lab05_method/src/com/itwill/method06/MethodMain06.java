package com.itwill.method06;

import java.util.Random;

public class MethodMain06 {

    public static void main(String[] args) {
        // 메서드 작성 연습:
        
        Random random = new Random();
        
        int genderCode = random.nextInt(10);        // [0, 10)범위의 정수 난수를 저장.
        System.out.println("genderCode = " + genderCode);
        
        // parseGenderCode()는 argument가 1 또는 3이면 "male" 리턴,
        // 2 또는 4이면 "female" 리턴, 그 이외의 숫자인 경우에는 "unknown" 리턴
        String gender = parseGenderCode(genderCode);
        System.out.println("gender = " + gender);
        
        
        // exercise 2
        int number = random.nextInt(100);       // [0, 100) 범위의 난주를 저장.
        
        // isEven(정수): 정수가 짝수이면 true, 그렇지 않으면 false를 리턴
        boolean result = isEven(number);
        
        System.out.println("number = " + number);
        System.out.println("result = " + result);
        
        // exercise 3
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        System.out.println("x = " + x + ", y = " + y);
        
        // whoIsBigger(x, y): x, y 중에서 더 큰 수를 리턴
        int bigger = whoIsBigger(x, y);
        System.out.println("bigger = " + bigger);
        
    }
    
    
    public static String parseGenderCode(int genderCode) {
        String gender = "";
        
        switch (genderCode) {
        case 1: case 3:
            gender = "male";
            break;
        case 2: case 4:
            gender = "female";
            break;
        default:
            gender = "unknown";
        }
        
        return gender;
    }
    
    
    //참고로 type 에 따라서 우선 변수를 선언해 놓고 그걸 반환하는경우가 많은데, 
    // 정수는 보통 0, 실수는 0.0, 불리언은 false, 스트링은 ""로 초기화한다.
    public static boolean isEven(int number) {
        
//        if (number % 2 == 0) {
//            return true;
//        } else {
//            return false;
//        }
        
        // 이렇게 하면 그냥 끝남..
        return (number % 2 == 0);
        
    }
    
    public static int whoIsBigger(int x, int y) {
        int biggerNumber = x;
        if (x > y) {
            biggerNumber = x;
        } else {
            biggerNumber = y;
        }
        
        return biggerNumber;
        
        //아니면 그냥 이렇게 할 수 있음
        // return (x > y) ? x : y;
    }
    
    

}
