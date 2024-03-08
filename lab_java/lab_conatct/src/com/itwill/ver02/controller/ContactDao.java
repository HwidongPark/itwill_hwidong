package com.itwill.ver02.controller;

import com.itwill.ver02.model.Contact;

public interface ContactDao {
    
    /**
     *  배열에 저장된 모든 연락처를 불러옴.
     *  @return Contact[] 배열.
     */
    Contact[] read();
    
    /**
     *  Contact을 아규먼트로 받아 배열에 추가
     *  name, phone, email을 받아 추가하는 것은???? 시도해보기
     *  @param name
     *  @param phone
     *  @param email
     *  @return 성공적으로 추가 시 1, 그렇지 않을시 0 리턴.
     */
    int add(Contact contact);
    
    /**
     *  int타입의 index를 아규먼트로 받아 해당 연락처의 contact을 반환
     *  @param index 검색하고 싶은 연락처의 index(int)
     *  @return 해당 index의 Contact반환. 만약 index에 해당 연락처 없을 시 null반환
     */
    Contact search(int index);
    
    /**
     * 수정하고 싶은 연락처의 index(int)를 아규먼트로 받아 수정
     * @param index int타입. 수정하고 싶은 연락처의 인덱스
     * @return 수정하고 싶은 index에 연락처가 존재할 시 1, 그렇지 않을 시 0 리턴.
     */
    int edit(int index, String name, String phone, String email);
}
