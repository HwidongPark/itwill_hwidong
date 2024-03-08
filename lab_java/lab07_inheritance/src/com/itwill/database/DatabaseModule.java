package com.itwill.database;

public interface DatabaseModule {
    // vlfem
    int DB_VERSION = 1; // public static final 생략.
    
    
    /**
     *  데이터베이스의 테이블에서 자료들을 읽어서 정수를 리턴하는 기능.
     *  
     *  @return 검색된 자료 개수. 
     */
    int select();
    // 인터페이스에는 이렇게 사용해도 문법오류 발생하지 않음. public abstract생략돼 있기 때문..
    // 만약 class에서 이렇게 했으면 오류임.. {}가 없어서
    
    
    
    /**
     *  데이터베이스의 테이블에 문자열을 저장하는 기능.
     *  
     *  @param value. 테이블에 저장할 문자열.
     *  @return 저장 상공하면 1, 실패하면 0.
     */
    int insert(String value);
}
