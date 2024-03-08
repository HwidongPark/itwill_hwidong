package com.itwill.file07;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private Score score;
    
    // TODO: 생성자
    public Student() {}

    public Student(int id, String name, Score score) {
        super();
        this.id = id;
        this.name = name;
        this.score = score;
    }
    
    
    // TODO: getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
    
    // TODO: toString 메서드 override
    @Override
    public String toString() {
        return "Student(이름=" + name + ", id=" + id + ", score=" + score + ")";
    }
    
    
    
    
}
