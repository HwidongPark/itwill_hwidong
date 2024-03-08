package com.itwill.file07;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileMain07 {

    public static void main(String[] args) {
        // 쓰기(write), 읽기(read)를 하기 위한 파일 이름
        String file = "data/student_list.dat";
        
        // Student를 원소로 하는 ArrayList를 생성, 1_000_000개의 Student객체를 저장
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            studentList.add(new Student(i, Integer.toString(i), new Score(i, i+1, i+2)));
        }
        
        
        // 파일에 ArrayList를 write
        try (                
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);    
        ) {
            // try문 시작
            long start = System.currentTimeMillis();
            
            oos.writeObject(studentList);
            
            long end = System.currentTimeMillis();
            
            System.out.println("write 소요 시간: " + (end - start) + "ms");
            // try문 끝
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        // 파일에서 ArrayList를 read
        try (
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);    
        ) {
            // try문 시작
            long start = System.currentTimeMillis();
            
            List<Student> readStudentList = (List<Student>) ois.readObject();
            
            long end = System.currentTimeMillis();
            
            System.out.println("read 소요 시간: " + (end - start) + "ms");
            System.out.println("읽어드린 readStudentList의 크기 = " + readStudentList.size());
            System.out.println("읽어드린 readStudentList의 첫번째 element= " + readStudentList.get(0));
            
            // try문 끝
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }   // main method 끝

}
