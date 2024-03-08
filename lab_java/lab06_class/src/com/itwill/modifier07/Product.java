package com.itwill.modifier07;

// 상품 클래스
public class Product {
    // field
    private int productCode;    // 상품 코드값(읽기 전용)
    private String productName; // 상품 이름(읽기, 쓰기 가능)
    private int productPrice;   // 상품 가격(읽기, 쓰기 가능)
    
    
    // TODO: constructor - argument 3개를 갖는 생성자.
    public Product(int productCode, String productName, int productPrice) {
        this.productCode = productCode;
        this.productName = productName;
        this.productPrice = productPrice;
    }
    
    // TODO: getter/setter method
    public int getProductCode() {       // productCode 반환
        return this.productCode;
    }
    
    public String getProductName() {        // productName 반환
        return this.productName;
    }
    
    public int getProductPrice() {      // productPrice 반환
        return this.productPrice;
    }
    
    public void setProductName(String productName) {        // productName 새로이 할당
        this.productName = productName;
    }
    
    public void setProductPrice(int productPrice) {     // productPrice 새로이 할당
        this.productPrice = productPrice;
    }
    
    
    // TODO: 상품 정보(코드, 이름, 가격)를 출력하는 메서드
    public void info() {
        System.out.println("상품 코드: " + this.productCode);
        System.out.println("상품 이름: " + this.productName);
        System.out.println("상품 가격: " + this.productPrice);
        
    }
    
}
