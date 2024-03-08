package com.itwill.inner03;

import com.itwill.inner03.Button.OnClickListener;

public class InnerMain03 {

    public static void main(String[] args) {
        // Button 타입 객체를 생성
        Button btnOpen = new Button("열기");
        
        // 버튼이 할 일(메서드)을 가지고 있는 객체(listener)를 생성
        OpenButtonListener listener = new OpenButtonListener();
        
        // listener 객체를 button에 등록
        btnOpen.setOnClickListener(listener);
        
        
        btnOpen.click();    // 4. 버튼을 클릭
        
        // 로컬 클래스 활용
        // 1. Button 타입 객체 생성
        Button btnClose = new Button("닫기");
        
        // 2. OnClickListener를 구현하는 클래스를 선언 -> 지역 클래스(local class)
        class CloseButtonListener implements OnClickListener {
            @Override
            public void onClick() {
                System.out.println("파일 닫기 실행...");
                
            }
        }
        
        // 3. 리스터 객체를 생성
        OnClickListener closeListener = new CloseButtonListener();
        
        // 4. 버튼에 리스너를 설정
        btnClose.setOnClickListener(closeListener);
        btnClose.click();
        
        
        
        // 익명 클래스 활용
        // 1. 버튼 타입 객체 생성
        Button saveButton = new Button("저장");
        
        // 2. 리스터 객체 생성 - 익명클래스
        OnClickListener saveListener = new OnClickListener() {
            
            @Override
            public void onClick() {
                System.out.println("저장 완료...");
                
            }
        };
        
        // 3. 버튼에 리스너 설정
        saveButton.setOnClickListener(saveListener);
        saveButton.click();
        
        
        // 1. 버튼 객체 생성
        Button btnCancel = new Button("취소");
        
        // 2. 버튼 할 일 등록 - 버튼 리스너 등록
        btnCancel.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick() {
                System.out.println("취소...");
                
            }
        });
        
        // 3. 버튼 클릭
        btnCancel.click();
        
        
    }   // end main

}
