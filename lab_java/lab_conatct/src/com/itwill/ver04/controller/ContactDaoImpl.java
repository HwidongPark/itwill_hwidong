package com.itwill.ver04.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.itwill.ver04.fileutil.FileUtil;
import com.itwill.ver04.model.Contact;

public class ContactDaoImpl implements ContactDao, Serializable {
    
    // Fields
    private static ContactDaoImpl instance = null;
    private List<Contact> contacts;
    
    
    // Constructors
    private ContactDaoImpl() {
        // 데이터 폴더 초기화: 폴더가 없으면 생성, 있으면 그대로 사용.
        FileUtil.initDataDir();
        
        // 데이터 초기화: 파일이 있으면 파일에서 읽어오기, 없으면 빈 리스트 만들기
        contacts = FileUtil.initData();
        
    }
    
    // singleton method
    public static ContactDaoImpl getInstance() {
        if (instance == null) {
            instance = new ContactDaoImpl();
        }
        
        return instance;
    }
    
    // Interface methods
    @Override
    public List<Contact> read() {
        return contacts;
    }

    @Override
    public Contact read(int index) {
//        if (isValidIndex(index)) {
//            return contacts.get(index);            
//        }
//        
//        return null;
        try {
            return contacts.get(index);
        } catch(IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public int create(Contact contact) {
        contacts.add(contact);
        
        // 바뀐 list를 파일에 쓰기.
        FileUtil.writeDataToFile(contacts);
        
        return 1;
    }

    @Override
    public int update(int index, Contact contact) {
        int result = 0;
        
        if (isValidIndex(index)) {
            contacts.set(index, contact);
            result = 1;
        }
        
     // 바뀐 list를 파일에 쓰기.
        FileUtil.writeDataToFile(contacts);
        
        return result;
    }

    @Override
    public int delete(int index) {
        int result = 0;
        if (isValidIndex(index)) {
            contacts.remove(index);
            result = 1;
        }
        
     // 바뀐 list를 파일에 쓰기.
        FileUtil.writeDataToFile(contacts);
        
        return result;
    }
    
    // Methods
    public boolean isValidIndex(int index) {
        return index >= 0 && index < contacts.size();
    }
    
    
    public List<Contact> searchContacts(String text) {
        List<Contact> contactSearched = new ArrayList<Contact>();
        
        
        for (Contact contact : contacts) {
            // 이름과 비교
            if (contact.getName().toLowerCase().contains(text.toLowerCase())) {
                contactSearched.add(contact);
            }
            
            // 전화번호와 비교
            if (contact.getPhone().toLowerCase().contains(text.toLowerCase())) {
                if (!contactSearched.contains(contact)) {
                    contactSearched.add(contact);
                }
            }
            
            // 이메일과 비교
            if (contact.getEmail().toLowerCase().contains(text.toLowerCase())) {
                if (!contactSearched.contains(contact)) {
                    contactSearched.add(contact);
                }
            }
            
        }
        
        return contactSearched;
    }

    


}
