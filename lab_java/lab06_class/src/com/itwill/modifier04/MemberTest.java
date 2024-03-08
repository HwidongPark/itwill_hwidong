package com.itwill.modifier04;

public class MemberTest {

    public static void main(String[] args) {
        // Member 타입 객체 생성
        Member member1 = new Member("cirche", "12345");
        Member member2 = new Member("panda", "54321");
        
        // 아이디와 비밀번호 출력
        System.out.println("member1의 아이디:" + member1.getMemberId());
        System.out.println("member1의 비밀번호: " + member1.getMemberPassword());
        
        // 비밀번호 변경, 확인
        member1.setMemberPassword("aabbcc");
        System.out.println("member1의 새 비밀번호: " + member1.getMemberPassword());
        
    }

}
