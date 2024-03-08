package com.itwill.array05;

public class ArrayMain05 {

    public static void main(String[] args) {
        // 다차원 배열: 배열들을 원소로 갖는 배열.
        // 2차원 배열: 1차원 배열들을 원소로 갖는 배열.      -->     배열 안의 배열의 value를 찾고 싶으면 2개의 index사용. ex) array[0][0];
        // 2차원 배열은 index를 2개 갖음.
        
        // 2차원 배열 선언 & 초기화 방법 1:
        // type[][] 배열이름 = { {...}, {...}, {...}, ... };
        int[][] array = {
                {1, 2, 3, 4},
                {5, 6, 7},
                {8, 9, 10, 11, 12}
        };
        
        // 배열 array의 길이는?       --> 3
        System.out.println("array length = " + array.length);
        
        
        // array[0]의 타입: int[]
        // array[0]의 길이: 4
        System.out.println("array[0] length = " + array[0].length);
        
        // array[0][0]의 타입:int
        System.out.println(array[0][0]);
        
        System.out.println("===========================");
        
        // 배열 array의 내용을 전부 출력
        // 1) for 구문 사용
        for (int i = 0; i < array.length; i++) {
            
            for(int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            
            System.out.println();
        }
        
        System.out.println("===========================");
        
        // 2) 향상된 for문 사용
        for (int[] element :array) {
            
            for (int num : element) {
                System.out.print(num + " ");
            }
            
            System.out.println();
            
        }
        
        
    }

}
