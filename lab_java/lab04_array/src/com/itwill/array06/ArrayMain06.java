package com.itwill.array06;

public class ArrayMain06 {
    public static void main(String[] args) {

        // 문자열 1차원 배열
        String[] newJeans = {"민지", "하니", "다니엘", "해린", "혜인"};
        String[] bts = {"RM", "진", "슈가", "제이홉", "지민", "뷔", "정국"};
        
        // 문자열 2차원 배열
        String[][] idols = {newJeans, bts};
        
        // 향상된 for 구문 활용
        for(String[] group : idols) {
            for (String member : group) {
                System.out.print(member +  " ");
            }
            System.out.println();
        }
        
        System.out.println("==================================");
        // for구문 활용
        for (int i = 0; i < idols.length; i++) {
            for(int j = 0; j < idols[i].length; j++) {
                System.out.print(idols[i][j] + " ");
            }
            System.out.println();
        }
        
        
    }


    
}
