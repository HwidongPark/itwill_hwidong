package com.itwill.inheritance02;

// Student -- subclass, Person - superclass
public class Student extends Person {
    // 필드
    private String school;
    
    
    // 생성자
    // 상위(부모) 타입의 객체가 먼저 생성되어 있어야 하위(자식) 타입 객체도 생성될 수 있다.
    // 생성자의 호출 순서: 상위 클래스 생성자 -> 하위 클래스 생성자
    // 하위 클래스 생성자에 상위 클래스 생성자를 명시적으로 호출하지 않으면,
    // 상위 클래스의 "기본 생성자"가 자동으로 호출된다.
    // 하위 클래스 생성자에서 명시적으로 상위 클래스 생성자를 호출하려면 super 키워드를 사용한다.
    // 상위 클래스의 아규먼트(들)을 가진 생성자를 갖는 생성자는 자동으로 호출되지 않음.
    // 상위 클래스의 아규먼트(들)을 갖는 생성자를 이용하려면 명시적으로 super(...)를 호출해야 함.
    // super(...) 호출은 생성자 안에서 가장 먼저 작성되어야 함!! **********
    
    // this의 의미:
    //   (1) 생성된 객체 주소(참조값): this.field, this.method()
    //   (2) overloading된 생성자: this(), this(arg)
    
    // super의 의미:
    //   (1) 생성된 부모 타입 객체 주소(참조값): super.field, super.method()
    //   (2) 상위 클래스의 생성자: super(), super(arg)
    
    // (1) 기본 생성자

    public Student() {
        super();    //-> 상위 클래스의 기본 생성자 명시적 호출.... super class의 default constructor의 경우 생략 가능.
        System.out.println("Student() 호출");
        // super();  //-> compile 오류. super() 호출은 첫 번재 문자이어야 함.
    }
    
    // (1) 아규먼트 1개(학교이름)을 갖는 생성자
    public Student(String school) {
        super();
        this.school = school;
        System.out.println("Student(school) 호출");
    }
    
    // (3) 아규먼트 2개(이름, 학교)를 갖는 생성자
    public Student(String name, String school) {
        super(name);        // 상위 클래스의 아규먼트를 갖는 생성자를 명시적 호출.
        this.school = school;
        System.out.println("Student(name, school) 생성자 호출");
    }



    public String getSchool() {
        return school;
    }



    public void setSchool(String school) {
        this.school = school;
    }
    
    // getter, setter 메서드
    
    
}
