package com.itwill.variable03;

public class VariableMain03 {

    //Method이름은 lower camel case사용
    public static void main(String[] args) {
        // 변수를 사용할 때 주의할 점:
        // 같은 이름으로 변수를 2번 이상 선언할 수 없다. 하지만 선언한 변수에 새로운 값을 할당하는 것은 가능하다(재할당)
        int x = 1;  // variable을 이렇게 선언한다는 의미는, 이름이 x인 variable 저장공간에 4 byte만큼 저장공간 확보 의미
        System.out.println(x);
        //int x = 2; // 같은 이름으로 변수를 선언하면 에러.
        
        x = 2; // 이렇게는 가능.. 얘는 variable을 declare하는게 아니라 assign하는거기 때문에 가능(재할당).
        System.out.println(x);
        
    }

}
