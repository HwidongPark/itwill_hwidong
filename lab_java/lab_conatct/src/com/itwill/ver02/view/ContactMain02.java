package com.itwill.ver02.view;

import java.util.Scanner;

import com.itwill.ver02.controller.ContactDaoImpl;
import com.itwill.ver02.model.Contact;

public class ContactMain02 {
    
    Scanner scanner = new Scanner(System.in);
    private ContactDaoImpl dao = ContactDaoImpl.getInstance();
    
    // main method 시작
    public static void main(String[] args) {
        
        ContactMain02 app = new ContactMain02();    
        System.out.println("*** 연락처 프로그램 v0.2 ***");
        
        
        boolean run = true;
        // while문 시작. 유저가 나갈때 까지 유지
        while (run) {
            int menu = app.showMainMenu();
            
            switch(menu) {
            case 0:
                run = false;
                System.out.println("프로그램을 종료합니다.");
                break;
            case 1:     // 연락처 목록
                app.allContacts();
                break;
            case 2:     // 새 연락처 추가
                app.addNewContact();
                break;
            case 3:     // 인덱스 검색
                app.lookUpContact();
                break;
            case 4:     // 연락처 수정
                app.editContact();
                break;
            default:
                System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.\n");
            }
        }
        
        
    }   // main method 끝
    
    
    private int showMainMenu() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("[0]종료 | [1]목록 | [2]새 연락처 | [3]인덱스 검색 | [4]수정");
        System.out.println("-----------------------------------------------------------");
        System.out.print("선택 >>> ");
        int menu = Integer.parseInt(scanner.nextLine());
        
        return menu;
    }
    
    
    private void allContacts() {
        System.out.println("----- 연락처 목록 -----");
    
        for (int i = 0; i < dao.getCount(); i++) {
            System.out.println("[" + i + "]" + dao.read()[i]);
        }
        
        // 연락처 없을 경우
        if (dao.getCount() == 0) {
            System.out.println("저장된 연락처가 없습니다.");
        }
        System.out.println("-----------------------");
    }
    
    
    private void addNewContact() {
        if (!dao.isMoreSpace()) {
            System.out.println("저장공간이 부족합니다.");
            return;
        }
        
        System.out.println("--- 새 연락처 등록 ---");
        System.out.print("이름 입력 >>> ");
        String name = scanner.nextLine();
        System.out.print("전화번호 입력 >>> ");
        String phone = scanner.nextLine();
        System.out.print("이메일 입력 >>> ");
        String email = scanner.nextLine();
        
        Contact newContact = new Contact(name, phone, email);
        dao.add(newContact);
        System.out.println("성공적으로 추가되었습니다.");
    }
    
    private void lookUpContact() {
        System.out.println("----- 연락처 검색 -----");
        System.out.print("검색할 인덱스 >>> ");
        int index = Integer.parseInt(scanner.nextLine());
        
        if (dao.isBeingUsedIndex(index)) {
            Contact[] contacts = dao.read();
            System.out.println(contacts[index]);
        } else {
            System.out.println("해당 인덱스에 연락처가 존재하지 않습니다.");
        }
    }
    
    
    private void editContact() {
        System.out.println("----- 연락처 수정 -----");
        
        System.out.print("수정할 인덱스 >>> ");
        int index = Integer.parseInt(scanner.nextLine());
        Contact[] contacts = dao.read();
        
        if (dao.isBeingUsedIndex(index)) {
            System.out.println("수정전 연락처: " + contacts[index]);
            
            System.out.print("이름 >>> ");
            String name = scanner.nextLine();
            System.out.print("번호 >>> ");
            String phone = scanner.nextLine();
            System.out.print("이메일 >>> ");
            String email = scanner.nextLine();
            
            dao.edit(index, name, phone, email);
        } else {
            System.out.println("해당 인덱스에 연락처가 존재하지 않습니다.");
        }
        
    }
    
    
    
} // contractMain02 class end
