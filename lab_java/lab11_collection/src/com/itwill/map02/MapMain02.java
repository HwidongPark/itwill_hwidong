package com.itwill.map02;

import java.util.Set;
import java.util.TreeMap;

public class MapMain02 {

    public static void main(String[] args) {
        // 문자열을 키로하고, 정수를 값으로 하는 TreeMap을 선언, 생성.
        TreeMap<String, Integer> menu = new TreeMap<>();
        
        // 데이터 저장
        menu.put("짜장면", 8000);
        menu.put("짬뽕", 9000);
        menu.put("볶음밥", 9000);
        
        System.out.println(menu);
        
         
        Set<String> kset = menu.keySet();   // -> TreeMap에서는 오름차순 정렬된 키들의 집합
        for (String key : kset) {
            System.out.println(key + ": " + menu.get(key));
        }
        
        System.out.println("------------------------");
        Set<String> kset2 = menu.descendingKeySet();
        for (String key : kset2) {
            System.out.println(key + ": " + menu.get(key));
        }
        

    }

}
