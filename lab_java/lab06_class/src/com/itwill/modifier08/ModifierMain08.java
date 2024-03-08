package com.itwill.modifier08;

// access 수식어(private, protected, public)는 지역 변수에는 사용할 수 없음
// final 수식어: 클래스, 클래스 멤버(필드, 생성자, 메서드), 지역 변수를 수식\
// final의 의미: 변경할 수 없는.
// *지역 변수: 메서드 안에서 선언하는 변수

// final 필드/지역변수는 초기화된 값을 변경할 수 없는 변수. 상수(constant)
// final 필드는 
//   (1) 선언과 동시에 초기화를 하거나
//   (2) 반드시 argument를 갖는 생성자를 선언해서 명시적으로 초기화해야 함.


// 객체(object): 프로그램에서 만들 대상. (비유) 붕어빵
// 클래스(class): 객체를 생성하기 위한 프로그램 코드, 설계도. 데이터 타입. (비유) 붕어빵 틀
// 인스턴스(instance): 메모리에 생성된 객체. (비유) 만들어진 붕어빵.
// instantiate: 인스턴스화 하다. 객체를 생성하다

// 클래스 멤버(필드, 메서드): 인스턴스 멤버, static 멤버.
// 1. 인스턴스 멤버(필드, 메서드):
//   (1) static 수식어가 사용되지 않은 멤버
//   (2) 객체를 생성한 후에 참조 변수를 이용해서 사용하는 멤버.
//   (3) JRE(Java Runtime Environment)이 사용하는 메모리 공간 중에서 힙(heap)에 생성되는 변수.
// 2. static(정적) 멤버 (필드, 메서드):
//   (1) static 수식어가 사용된 멤버.
//   (2) 객체를 생성하지 않아도 사용할 수 있는 멤버.
//   (3) 클래스 이름으로 사용하는 멤버.  (예) Math.PI, Math.sqrt()
//   (4) JRE가 사용하는 메모리 공간 중에서 메서드(method) 영역에 생성되는 변수.
//   (5) 프로그램 main() 메서드가 호출되기 전에 프로그램 로딩 시점에 메모리에 생성. *****
//   (6) static 메서드는 static 필드와 메서드만 접근 가능.*****


public final class ModifierMain08 {     // final  클래스
    
    // (1) fianl 필드를 선언과 동시에 초기화.
    private final String s = "hello";       // final 멤버
    
    // (2) final 필드를 선언할 때 초기화하지 않는 경우에는 생성자에서 명시적으로 초기화해야 함.
    private final int n;
    // 생성자
    public ModifierMain08(int n) {
        this.n = n;     // final필드를 명시적으로 초기화!
    }
    
    public static void test2() {
        System.out.println("ModifierMain08 스태틱 메서드");
        
    }
    
    
    public static void main(String[] args) {
        test2();    // ModifierMain08.test2();라고 해도됨        
        ModifierMain08 app = new ModifierMain08(1);
        System.out.println(app.s);
        System.out.println(app.n);
        
        
        
        final int x = 1;      // final 지역변수
//        x = 2;        // -> compile error. final 지역 변수는 새로운 값을 할당(저장)할 수 없다.
        
        // static 멤버 사용 방법: 클래스 이름을 접두사로 사용. 객체 생성 전에도 사용 가능.
        System.out.println(Test.staticVar);
        Test.staticVar = 1;
        Test.printVariables2();
        
        // instance 멤버 사용 방법: 참조변수를 이용. 객체를 생성한 후에 사용 가능.
        Test t1 = new Test();
        System.out.println(t1.instanceVar);
        t1.instanceVar = 100;
        t1.printVariables();
        
        Test t2 = new Test();
        t2.instanceVar = 200;
        t2.printVariables();
        t2.staticVar = 12345;       // 이건 static한 방법으로 Test.staticVar를 바꾼게 아니므로 옳지 않은 코드임.. 그래도 작동은 함
        // 다시 정리.. 경고(warning): static필드를 마치 인스턴스 변수처럼 사용.. 나쁜 코드.
        // 좋은 코드: Test.staticVar = 12345;
        
        t2.printVariables();
        
        t1.printVariables();
        
        
    }

}