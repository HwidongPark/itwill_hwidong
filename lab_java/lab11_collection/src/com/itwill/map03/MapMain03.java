package com.itwill.map03;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

public class MapMain03 {

    public static void main(String[] args) {
        // 단어 개수 세기(word counting)
        // 문자열 sentence에 등장하는 단어를 key로 하고 
        // 그 단어가 문자열에 등장하는 횟수를 value로 하는 Map 객체를 만들고 출력.
        // 결과: {땅=2, 바다=1, 사람=1, 하늘=3, sky=2}
        String sentence = "하늘 바다 땅 하늘 땅 사람 하늘 sky sky";
        String[] stringArray = sentence.split(" ");
        // 뭐있는지 한번 보자
        System.out.println(Arrays.toString(stringArray));
        
        TreeMap<String, Integer> map = new TreeMap<>();
        for (String key : stringArray) {
            map.put(key, map.get(key) == null ? 1 : map.get(key) + 1);
        }
        
        System.out.println(map);
        
        
        
        System.out.println("--------------------");
        HashMap<String, Integer> wordCounts2 = new HashMap<>();
        System.out.println(wordCounts2);
        for(String w : stringArray) {
            Integer count = wordCounts2.getOrDefault(w, 0);
            wordCounts2.put(w, count+1);
        }
        
        System.out.println(wordCounts2);
        
        
    }

}