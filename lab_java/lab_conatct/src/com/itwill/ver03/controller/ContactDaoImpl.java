package com.itwill.ver03.controller;

import java.util.ArrayList;
import java.util.List;
import com.itwill.ver03.model.Contact;


public class ContactDaoImpl implements ContactDao {
    
    // Fields
    private static ContactDaoImpl instance = null;
    private List<Contact> contacts = new ArrayList<>();
    
    
    // Constructors
    private ContactDaoImpl() {}
    
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
        return 1;
    }

    @Override
    public int update(int index, Contact contact) {
        int result = 0;
        
        if (isValidIndex(index)) {
            contacts.set(index, contact);
            result = 1;
        }
        
        return result;
    }

    @Override
    public int delete(int index) {
        int result = 0;
        if (isValidIndex(index)) {
            contacts.remove(index);
            result = 1;
        }
        return result;
    }
    
    // Methods
    public boolean isValidIndex(int index) {
        return index >= 0 && index < contacts.size();
    }

    


}
