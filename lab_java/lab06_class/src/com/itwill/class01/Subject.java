package com.itwill.class01;


// 클래스 = 데이터(필드) + 초기화(생성자) + 기능(메서드)   ==> 데이터 타입
public class Subject {
    
    // 변수 선언:
    // 타입 이름;
    
    // 필드(field). 멤버변수.
    int java;
    int sql;
    int js;
    
    
    // 생성자(constructor)
    // 객체를 생성하고, 객체의 필드들을 초기화하는 역할
    // 생성자의 이름은 반드시 class의 이름과 같아야 한다. 대소문자도 같아야 함.
    // method를 작성하는 방법과 비슷. 단, constructor에서는 return type이 없음!!!
    //클래스를 작성할 때 생성자를 하나도 정의하지 않으면, 자바 컴파일러가 "기본 생성자(default constructor)"를 자동으로 만들어 줌.
    // 그러나 클래스를 작성할 때 생성자를 직접 작성하면, 자바 컴파일러는 기본생성자(default constructor)를 자동으로 만들지 않음!
    // 생성자 오버로딩(overloading): 생성자를 여러개 정의하는 것.
    
    
    // 기본 생성자(default constructor):
    // argument를 갖지 않는 생성자.
    // 일반적으로 (특별한 코드가 없어도) 필드 타입의 기본값으로 필드들을 초기화.
    // - 정수 기본값: 0, 실수 기본값: 0.0, boolean 기본값: false, 참조타입: null.
    
    public Subject() {
        // 아무코드를 작성하지 않아도 됨.
        // 하지만 블록 괄호({})는 생략할 수 없음.
        // 굳이 아무것도 없는 constructor만든 이유는 아래에 parameter를 가진 생성자가 있기 때문에, argument를 안 넣어도 객체가 생성되게 하려면 빈 constructor를 만들어야 함.
    }
    
    // argument를 갖는 생성자   - 아규먼트 값들로 필드를 초기화.
    public Subject (int java, int sql, int js) {
        this.java = java;
        this.sql = sql;
        this.js = js;
    }
    
    
    // 앞으로 class에 method를 만들 때 특별한 경우가 아니라면 public을 붙이고 static은 안 붙일거다.
    // 메서드(기능)
    // class안의 field를 argument로 넣지 않아도 바로 사용할 수 있음
    public int getTotal() {
        return this.java + this.sql + this.js;
        
    }
    
    public double getMean() {
        return (double) this.getTotal() / 3;
    }
    
    
}
