package com.itwill.database;

public class OracleDatabase implements DatabaseModule {

    @Override
    public int select() {
        System.out.println("Oracle 테이블 검색");
        return 0;
    }

    @Override
    public int insert(String value) {
        System.out.println("Oracle 테이블 삽입");
        return 0;
    }

}