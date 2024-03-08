package com.itwill.singleton;


// singleton = (1) private static field + (2) private constructor + (3) public static method -> Captain type을 return해주는 메서드

public class Captain {
    // (1) private static field 자기자신 타입(Captain)
    private static Captain instance = null;
    
    private String name = "캡틴 아메리카";
    
    // (2) private constructor
    private Captain() {
        // Do nothing
    }
    
    // (3) public static이고 자기 자신 타입(Captain)을 리턴하는 메서드
    public static Captain getInstance() {
        if(instance == null) {          // instance는 Captain.instance를 줄여서 사용한것. 같은 class안에 있으니까
            instance = new Captain();
        }
        
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    
}
