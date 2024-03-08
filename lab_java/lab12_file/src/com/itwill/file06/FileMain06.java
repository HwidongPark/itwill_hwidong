package com.itwill.file06;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.itwill.file05.Product;

public class FileMain06 {

    public static void main(String[] args) {
        // 1) com.itwill.file05.Product 타입 객체 1,000,000개를 저장하는 ArrayList를 파일에 write
        // FileOutPutStream, BufferOutputStream, ObjectOutputStream 사용.
        // ArrayList를 파일에 쓰는 시간을 측정하고 출력.
        String file = "data/homework_product_list.dat";
        
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            Product pseudoProduct = new Product(i+1, Integer.toString(i+1), i+1);
            productList.add(pseudoProduct);
        }
        
        try (
                FileOutputStream out = new FileOutputStream(file);
                BufferedOutputStream bout = new BufferedOutputStream(out);
                ObjectOutputStream oout = new ObjectOutputStream(bout);
        ) {
            // try 실행 코드
            long start = System.currentTimeMillis();
            
            
            oout.writeObject(productList);
            
            long end = System.currentTimeMillis();
            System.out.println("time elapsed in writing=" + (end-start) + "ms");
            
            // try 실행코드 끝
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        // 2) 1)에서 작성된 파일에서 ArrayList를 읽고, 내용 확인:
        // FileInputStream, ButterInputStream, ObjectInputStream 사용.
        // ArrayList를 파일에서 읽는 시간을 측정하고 출력.
        List<Product> readProductList = new ArrayList<>();
        
        try (
                FileInputStream in = new FileInputStream(file);
                BufferedInputStream bin = new BufferedInputStream(in);
                ObjectInputStream ois = new ObjectInputStream(bin);
        ) {
            // try 실행 코드
            long start = System.currentTimeMillis();
            
            readProductList = (List<Product>) ois.readObject();
//            System.out.println(readProductList);
//            for (int i = 0; i < 50; i++) {
//                System.out.println(readProductList[i]);
//            }
            System.out.println("productList의 0번 index만 확인해보자." + readProductList.get(0));
            System.out.println("productList의 elements개수= " + readProductList.size());
            
            long end = System.currentTimeMillis();
            System.out.println("time elapsed in reading=" + (end-start) + "ms");
            // try 실행 코드 끝
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
    }   // main method 끝

}   // Main class 끝
