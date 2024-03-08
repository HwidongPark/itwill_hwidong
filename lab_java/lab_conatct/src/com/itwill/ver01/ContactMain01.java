package com.itwill.ver01;

import java.util.Scanner;

public class ContactMain01 {
    
    private static final int MAX_LENGTH = 5; // 배열의 길이(원소 개수)
    
    private Scanner scanner = new Scanner(System.in);   // 입력 도구
    private Contact[] contacts = new Contact[MAX_LENGTH];       // 연락처 들을 저장할 배열
    private int count = 0;  // 배열 contacts에 저장된 연락처 개수를 저장하는 변수.
    // 배열에 새로운 연락처가 저장될 때마다 증가(++).
    
    public static void main(String[] args) {
        System.out.println("*** 연락처 프로그램 v0.1 ***");
        
        ContactMain01 app = new ContactMain01();
        
        boolean run = true;     // 프로그램 실행/종료 여부 저장하는 변수.
        while(run) {
            int menu = app.showMainMenu();
            switch (menu) {
            case 0:     // 종료
                run = false;
                break;
            case 1:     // 배열에 저장된 전체 목록 출력
                app.readAllContacts();
                break;
            case 2:     // 새 연락처 등록
                app.createContact();
                break;
            case 3:     // 인덱스 검색
                app.readContactByIndex();
                break;
            case 4:     // 연락처 수정 기능
                app.updateContact();
                break;
            default:
                System.out.println("메뉴를 다시 선택하세요...");
            }
            
            
        }   // end while (run)문
        
        System.out.println("*** 프로그램 종료 ***");
    }   // main method 종료
    
    
    // 메인 메뉴 메서드
    private int showMainMenu() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("[0]종료 | [1]목록 | [2]새 연락처 | [3]인덱스 검색 | [4]수정");
        System.out.println("-----------------------------------------------------------");
        System.out.print("선택 >>> ");
        int menu = Integer.parseInt(scanner.nextLine());
        
        return menu;
    }
    
    // 목록 출력하는 메서드
    private void readAllContacts() {
        System.out.println("--- 연락처 목록 ---");
        for (int i = 0; i < count; i++) {   // 배열에 실제로 저장된 연락처 개수만큼 반복
            System.out.printf("[%d] %s%n", i, contacts[i].toString());
        }
        System.out.println("-------------------");
    }
    
    // 새 연락처 추가 메서드
    private void createContact() {
//        if (count < MAX_LENGTH) {
//            System.out.println("--- 새 연락처 등록 ---");
//            System.out.print("이름 입력 >>> ");
//            String name = scanner.nextLine();
//            System.out.print("전화번호 입력 >>> ");
//            String phone = scanner.nextLine();
//            System.out.print("이메일 입력 >>> ");
//            String email = scanner.nextLine();
//            
//            // count가 배열의 개수를 넘어가지 못하도록 만들어야함.
//            
//            Contact c = new Contact(name, phone, email);        // contact type객체 생성
//            contacts[count] = c;    // 연락처 객체를 배열에 저장.
//            count++;        // 배열에 저장된 연락처 개수를 1 증가.
//        } else {
//            System.out.println("저장 공간이 부족합니다. 더 이상 연락처를 추가할 수 없습니다.");
//        }

        
        // 아래와 같은 방법이 선호될 수 있다고 함. 왜냐하면 긴 코드블록이 indented되지 않아도 되기 때문에!!!******
        if (count >= MAX_LENGTH) {
            System.out.println("저장 공간이 부족합니다. 더 이상 연락처를 추가할 수 없습니다.");
            return;     // 만약 count >= MAX_LENGTH일 경우 return함으로써 아래의 코드가 실행되지 않도록 함.
        }
        System.out.println("--- 새 연락처 등록 ---");
        System.out.print("이름 입력 >>> ");
        String name = scanner.nextLine();
        System.out.print("전화번호 입력 >>> ");
        String phone = scanner.nextLine();
        System.out.print("이메일 입력 >>> ");
        String email = scanner.nextLine();
        
        // count가 배열의 개수를 넘어가지 못하도록 만들어야함.
        
        Contact c = new Contact(name, phone, email);        // contact type객체 생성
        contacts[count] = c;    // 연락처 객체를 배열에 저장.
        count++;        // 배열에 저장된 연락처 개수를 1 증가.


    }
    
    
    // 인덱스 검색
    private void readContactByIndex() {
        System.out.println("--- 인덱스 검색 ---");
        System.out.print("검색할 인덱스 >>> ");
        int index = Integer.parseInt(scanner.nextLine());
        
        
        // 내가 저지른 실수: index <= 0 인경우를 생각 안함
        if (index < count && index >= 0) {
            System.out.println(contacts[index].toString());
        } else {
            System.out.println("해당 인덱스의 연락처가 존재하지 않습니다.");
        }
        // 여기도 if else문 대신 return사용 가능할 듯
    }
    
    private void updateContact() {
        System.out.println("--- 연락처 수정 ---");
        // 수정할 연락처의 인덱스를 입력받아서 이름, 전화번호, 이메일을 수정.
        
        System.out.print("수정할 인덱스 >>> ");
        int index = Integer.parseInt(scanner.nextLine());
        
        if (index < 0 || index > count) {
            System.out.println("해당 인덱스의 연락처가 존재하지 않습니다.");
            return;     // 매서드 종료
        }
        
        System.out.println("수정 전: " + contacts[index].toString());
        System.out.print("이름 >>> ");
        String name = scanner.nextLine();
        System.out.print("번호 >>> ");
        String phone = scanner.nextLine();
        System.out.print("이메일 >>> ");
        String email = scanner.nextLine();
        
        // 방법 1: setter 메서드 사용
//        contacts[index].setName(name);
//        contacts[index].setPhone(phone);
//        contacts[index].setEmail(email);
        
        // 방법 2: 새 객체로 변경
        contacts[index] = new Contact(name, phone, email);
        
        System.out.println("연락처 수정 성공");
    }
    

}       // main class 종료
