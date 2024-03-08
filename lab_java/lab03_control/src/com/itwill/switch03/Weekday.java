package com.itwill.switch03;


// enum: enumerate type(열거형 타입)이라는 뜻. 상수(constant)들을 정의하는 특별한 종류의 class.
//한번 값이 저장되고 그 다음부터 변경되지 않는 변수(상수)들은 upper case로 이름을 만든다.


// 변수(variable): 값(데이터)을 저장하기 위한 메모리 공간.
// 상수(constant): 초기화한 이후에는 값을 변경할 수 없는 변수.
// 리터럴(literal): 고정된 값. (예) 1, 2, 3, 4, 3.14, "abcd", ....
// int x = 1; -> x: 변수, 1: 리터럴
// final int x = 1; -> 여기서 x는 상수; x는 변수인데 그 value를 바꿀 수 없으므로

public enum Weekday {
    SUN, MON, TUE, WED, THU, FRI, SAT;
}
// Weekday라는 변수 type에 7가지 변수들이 있는 것임