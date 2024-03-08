package com.itwill.file07;

import java.io.Serializable;

public class Score implements Serializable {
    private int java;
    private int sql;
    private int web;
    
    // 생성자 2개(기본생성자, argument갖는 생성자)
    public Score() {}

    public Score(int java, int sql, int web) {
        this.java = java;
        this.sql = sql;
        this.web = web;
    }

    
    // getters & setters
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

    
    // toString 메서드 override
    @Override
    public String toString() {
        return "Score(java=" + java + ", SQL=" + sql + ", web=" + web + ")";
    }    
    
    
    
    
    
}
