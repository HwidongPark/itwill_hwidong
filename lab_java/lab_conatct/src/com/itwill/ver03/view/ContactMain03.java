package com.itwill.ver03.view;

import java.util.List;
import java.util.Scanner;

import com.itwill.ver03.controller.ContactDaoImpl;
import com.itwill.ver03.model.Contact;

public class ContactMain03 {
    
    Scanner scanner = new Scanner(System.in);
    ContactDaoImpl dao = ContactDaoImpl.getInstance();
    
    public static void main(String[] args) {
        ContactMain03 app = new ContactMain03();
        System.out.println("*** 연락처 프로그램 v0.3 ***");
        
        boolean run = true;
        while (run) {
            int menu = app.selectMenu();
            
            switch(menu) {
            case 0:
                run = false;
                break;
                
            case 1:
                System.out.println("--- 새 연락처 추가 ---");
                app.readAllContacts();
                break;
                
            case 2:
                System.out.println("--- 연락처 추가 ---");
                System.out.print("이름 >>> ");
                String nameToAdd = app.scanner.nextLine();
                System.out.print("전화 번호 >>> ");
                String phoneToAdd = app.scanner.nextLine();
                System.out.print("이메일 >>> ");
                String emailToAdd = app.scanner.nextLine();
                
                Contact contactToAdd = new Contact(nameToAdd, phoneToAdd, emailToAdd);
                
                app.addContact(contactToAdd);
                System.out.println(contactToAdd);
                System.out.println("연락처를 추가하였습니다.");
                
                break;
                
            case 3:
                System.out.println("--- 인덱스로 연락처 검색 --- ");
                while (true) {
                    try {
                        System.out.print("검색할 인덱스를 입력하세요. >>> ");
                        int indexToSearch = Integer.parseInt(app.scanner.nextLine());
                        // 범위내 index입력 시 break로 탈출, 그렇지 못할 시 다시 검색 while문으로 돌아감
                        if (app.searchContactByIndex(indexToSearch)) {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("정수만 입력 가능합니다. 다시 입력해주세요.");
                    }
                }
                break;
                
            case 4:
                System.out.println("--- 연락처 업데이트 ---");
                while (true) {
                    try {
                        System.out.print("업데이트할 인덱스 >>> ");
                        int indexToUpdate = Integer.parseInt(app.scanner.nextLine());
                        
                        // Method에 있긴하지만 유저 편의성을 위해 추가함.
                        if (app.dao.isValidIndex(indexToUpdate)) {
                            System.out.println("업데이트할 연락처: " + app.dao.read(indexToUpdate));
                        } else {
                            System.out.println("해당 index에 연락처가 존재하지 않습니다.");
                            continue;
                        }
                        
                        System.out.print("이름 >>> ");
                        String nameToUpdate = app.scanner.nextLine();
                        System.out.print("전화 번호 >>> ");
                        String phoneToUpdate = app.scanner.nextLine();
                        System.out.print("이메일 >>> ");
                        String emailToUpdate = app.scanner.nextLine();
                        
                        app.updateContact(indexToUpdate, nameToUpdate, phoneToUpdate, emailToUpdate);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("정수만 입력 가능합니다. 다시 입력해 주세요.");
                    }
                }
                break;
                
                default:
                    System.out.println("메뉴 범위 내의 숫자만 입력해 주세요");
                
            }
            
        }   // while문 끝
        
        System.out.println("프로그램을 종료합니다.");
    }   // Main method끝
    
    // 메뉴 메서드
    private int selectMenu() {
        try {
            
            System.out.println("\n---------------------------------------------------------------");
            System.out.println("[0]종료 | [1]목록 | [2]새 연락처 | [3]인덱스 검색 | [4]업데이트");
            System.out.println("---------------------------------------------------------------");
            System.out.print("선택> ");
            return Integer.parseInt(scanner.nextLine());
            
            
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력 가능합니다. 다시 입력해 주세요");
            return selectMenu();
        }


    }
    
    // Contacts 목록 읽는 메서드
    private void readAllContacts() {
        List<Contact> contacts = dao.read();
        for (int i = 0; i < contacts.size(); i++) {
            System.out.printf("[%d] " + contacts.get(i) + "%n", i);
        }
    }
    
    // 연락처 추가 메서드
    private void addContact(Contact contact) {
        dao.create(contact);
    }
    
    // 연락처 검색 메서드
    private boolean searchContactByIndex(int index) {
        boolean result = false;
        if (dao.isValidIndex(index)) {
            System.out.println(dao.read(index));
            result = true;
        } else {
            System.out.println("해당 index에 연락처가 존재하지 않습니다.");
        }
        return result;
    }
    
    // 연락처 추가
    private void updateContact(int index, String name, String phone, String email) {
        if (dao.isValidIndex(index)) {
            Contact contact = new Contact(name, phone, email);
            dao.update(index, contact);
            System.out.println("업데이트 성공.");
        } else {
            System.out.println("해당 index에 연락처가 존재하지 않습니다.");
        }
    }
    
    

}
