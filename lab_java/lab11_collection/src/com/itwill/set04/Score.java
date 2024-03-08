package com.itwill.set04;

import java.util.Objects;

public class Score {
    private int java;
    private int sql;
    private int web;
    
    public Score() {}

    public Score(int java, int sql, int web) {
        super();
        this.java = java;
        this.sql = sql;
        this.web = web;
    }

    public int getJava() {
        return java;
    }

    public void setJava(int java) {
        this.java = java;
    }

    public int getSql() {
        return sql;
    }

    public void setSql(int sql) {
        this.sql = sql;
    }

    public int getWeb() {
        return web;
    }

    public void setWeb(int web) {
        this.web = web;
    }

    
    // equals와 hashCode 메서드 override
    // java, sql, web의 값이 모두 같으면 equals가 true를 리턴.
    @Override
    public int hashCode() {
        return java + sql + web;
    }
    
    // resturn Objects.hash(java, sql, web);    // -> Objects 클래스의 hash 메서드 사용하면 value가 같을 경우 같은 hash로 반환시킴.

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof Score scores) {
            result = java == scores.java && sql == scores.sql && web == scores.web;
               
        }
        
        return result;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Subject(" + "Java:" + java + ", SQL:" + sql + ", Web: " + web + ")";
    }
    
    
    

    
    
}
