/**
 * hoist.js
 * 08_function.html에 포함.
 * 
 * 호이스팅(hoisting): function 키워드를 사용한 선언은 스크립트에 먼저 실행됨.
 * function 키워드를 사용해서 선언한 (이름이 있는) 함수는 언제든지 호출할 수 있음.
 *   - 스크립트 파일에서 함수 호출 코드가 먼저 작성되고, 함수 선언이 나중에 작성되도 됨.
 * 
 * 함수 객체를 저장하는 (지역) 변수 이름으로 함수를 호출할 때는 변수가 선언된 다음에만 가능.
 * 
 */



function test1() {
    console.log('test1');
}

test1();


test2();    // -> function으로 선언된 함수는 언제든지 호출 가능.
// -> 함수 선언보다 먼저 함수 호출이 가능.

function test2() {
    console.log('test2');
}

// test3();    // -> ReferrenceError: 변수 선언(초기화) 이전에 호출하면 안됨!
const test3 = function() {
    console.log('test3');
};

test3();

// console.log(plus(1, 2));
const plus = (x, y) => x + y;
console.log(plus(1, 2));

// x = 100;
// let x = 1;

// var는 전역변수라 아래와 같이 사용해도 에러 안남.. 그래서 var잘 안씀
// var도 function과 같이 hoisting을 해버리기 때문에 그런 현상이 일어나는 것임.
// y = 200;
console.log('y =', y);
var y = 2;

var x = 1;
function testVar() {
    console.log('x =', x);
    
    x= 10;
    console.log('x =', x);
}

testVar();
console.log('x =', x);

var i = 10;


testVar2();
console.log(i);