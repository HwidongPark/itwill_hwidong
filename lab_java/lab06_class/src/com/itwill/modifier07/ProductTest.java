package com.itwill.modifier07;

public class ProductTest {

    public static void main(String[] args) {
        // TODO: Product 타입 객체 생성, getter/setter 메서드 테스트.
        Product bag = new Product(12345, "악어가죽 가방", 100_000);
        Product shoe = new Product(54321, "나이키 신발", 50_000);
        
        //상품 코드, 이름, 가격 확인
        System.out.println("bag의 상품 코드: " + bag.getProductCode());
        System.out.println("bag의 상품 이름: " + bag.getProductName());
        System.out.println("bag의 상품 가격: " + bag.getProductPrice());
        
        System.out.println("shoe의 상품 코드: " + shoe.getProductCode());
        System.out.println("shoe의 상품 이름: " + shoe.getProductName());
        System.out.println("shoe의 상품 가격: " + shoe.getProductPrice());
        
        // shoe instance의 이름, 가격 변화 및 확인
        shoe.setProductName("아디다스 신발");
        shoe.setProductPrice(80_000);
        
        System.out.println("shoe의 상품 이름: " + shoe.getProductName());
        System.out.println("shoe의 상품 가격: " + shoe.getProductPrice());
        
        // 메서드 테스트
        bag.info();
        shoe.info();
        
    }

}
