package com.itwill.variable04;

public class VariableMain04 {

    public static void main(String[] args) {
        
        //자바 기본 데이터 타입:
        byte a = -128; // -> byte 타입의 정수는 -128 ~ 127 범위의 정수만 저장 가능.
        short b = -32768; // -> short type의 정수는 -32,768 ~ 32,767 범위의 정수 저장 가능.
        int c = 1_000_000_000; // -> 약 -20억 ~ 20억까지 (2billions range)저장 가능
        long d = 1_000_000_000_000_000_000L; // 자바는 4byte만으로 정수를 만들려고 함.. 그래서 20억 이상의 숫자를 작성할 수가
        // 없음. 따라서 long type variable에 저장하기도 전에 20억보다 큰 숫자를 할당할 수가 없음.. 그래서 suffix l 또는 L을
        // 입력해서 정수를 long type으로 만들어줘야 함.
        //정리하자면 literal numeric value만들 때 default로 4byte의 공간만 사용함으로 suffix L을 붙여 정수에 더큰 저장공간을
        //할당해서 literal을 작성해줘야 한다.
        
        
        // 자바에서 정수의 기본 타입은 int이다.
        // 자바에서 실수(literal)의 기본타입은 double이다.
        double x = 1.0;  //double 은 8byte, float은 4byte저장공간을 할당한다.
        float y = 1.0f; // literl 1.0이 할당받은 저장공간은 double의 저장공간인 8 byte로 저장공간 4byte인 float에 넣을 수 없다.
        // 그래서 float y = 1.0f;와 같이 표기하여 1.0 literal이 4byte저장공간에 들어가도록 suffix f를 붙여줘야 한다.
        
        
        // <정리>
        // 1. 변수는 같은 타입의 값만 저장할 수 있음
        // 2. 큰 범위의 타입에는 작은 범위의 타입의 값을 저장할 수 있음.
        // 3. 작은 범위 타입에 큰 범위의 값을 저장하려고 하면 에러.
        // 4. 정수 literal은 범위만 넘지 않으면 byte, short, char에 저장 가능. 얘네 같은 경우는 예외적으로 가능함.. 따로 suffix없음.
        // 다만 int variable을 byte나 short variable에 assign할 수 없음. 오직 정수 literal만 예외임.
        
        
        // char type: 문자 1개의 유니코드(unicode)값(정수)을 저장하는 타입. -> char도 결국 정수를 저장하는 type
        // char는 ''사용한다.
        // java는 문자열(string)은 큰 따옴표("")사용, 문자(character)는 작은 따옴표('')사용!!
        char ch1 = 'a';
        char ch2 = 98;
        System.out.println(ch1);
        System.out.println((int) ch1);
        System.out.println(ch2);
        
        
        short s1 = 10; // 10은 int리터럴 - (범위만 넘지 않으면) short variable인 s1에 저장 가능
        short s2 = 1;
        
        //short s3 = s1 + s2; // 애는 s1, s2라는 variable에 저장돼 있는 value가 int이기 때문에 variable + variable때는 error가 생김. java는 variable에 있는 value가 int이기 때문에 애초에 계산하지 않고 error메세지 줌.
        short s3 = (short) (s1 + s2); // casting(강제type 변환)을 함으로써 data type이 short임을 명시해줘야 함.. 일종의 개발자가 data type보증해줘서 컴퓨터가 접근해서 계산할 수 있도록 하는 거라고 볼 수 있음.
        // **(주의)** 작은 단위의 data type에서 casting사용 시 overflow의 위험이 있다.+
        
        
    }

}
