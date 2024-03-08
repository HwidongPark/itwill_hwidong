package com.itwill.swing04;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        List<File> images = Arrays.asList(new File("images\\flower1"), new File("images\\flower2"),
                new File("images\\flower3"), new File("images\\flower4"), new File("images\\flower5"));
        
        System.out.println("인덱스 위치: " + images.get(0).getPath());
//        System.out.println(path);\

        
    }

}
