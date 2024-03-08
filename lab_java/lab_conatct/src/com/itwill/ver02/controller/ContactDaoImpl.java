package com.itwill.ver02.controller;

import com.itwill.ver02.model.Contact;

public class ContactDaoImpl implements ContactDao {
    
    // Singleton 생성
    private static ContactDaoImpl instance = null;
    
    private ContactDaoImpl() {}
    
    public static ContactDaoImpl getInstance() {
        if (instance == null) {
            instance = new ContactDaoImpl();
        }
        
        return instance;
    }
    
    // 필드
    private int MAX_LENGTH = 2;
    private int count = 0; // 저장된 배열의 개수 count 필드
    Contact[] contacts = new Contact[MAX_LENGTH];   // 연락처 저장될 배열 필드
    
    // getter/setter method
    public int getMAX_LENGTH() {
        return MAX_LENGTH;
    }

    public int getCount() {
        return count;
    }
    
    
    //메서드
    public boolean isMoreSpace() {
        boolean result = false;
        
        if(count < MAX_LENGTH) {
            result = true;
        }
        
        return result;
    }
    

    public boolean isBeingUsedIndex(int index) {
        boolean result = false;
        
        if(index >= 0 && index < count) {
            result = true;
        }
        
        return result;
    }
    
    // 목록 메서드. 배열 전체 반환
    @Override
    public Contact[] read() {
        return contacts;
    }

    // 연락처 추가 메서드.
    @Override
    public int add(Contact contact) {
        int result = 0;
        
        // 저장된 연락처 개수가 메모리 범위 넘어가지 않으면 추가.
        if (isMoreSpace()) {
            contacts[count] = contact;
            count++;
            result = 1;
        }
        
        return result;
    }

    // 연락처 검색 메서드.
    @Override
    public Contact search(int index) {
        Contact searchResult = null;
        
        if (isBeingUsedIndex(index)) {
            searchResult = contacts[index];
        }
        
        return searchResult;
    }
    
    
    // 연락처 수정 메서드.
    @Override
    public int edit(int index, String name, String phone, String email) {
        int result = 0;
        if (isBeingUsedIndex(index)) {
            contacts[index] = new Contact(name, phone, email);
            result = 1;
        }
        
        return result;
    }
    
}
