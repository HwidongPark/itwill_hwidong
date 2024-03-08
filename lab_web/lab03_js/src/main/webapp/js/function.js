/**
 * function.js
 * 08_function.html에 포함
 */

/*
  자바스크립트에서 함수를 선언(정의)하는 방법:
  
  function 함수이름 ([파라미터 선언, ...]) {
      실행 코드;
      [return [반환값]];
  }
  
  함수의 리턴 타입을 선언하지 않음.
  파라미터를 선언할 때는 const, let, var와 같은 키워드를 사용하지 않음!! 변수 이름만 만들면 됨
*/

// 함수 선언:
function add(x, y) {
    console.log('x =', x, ', y =', y);
    return x + y;
}


// 자바스크립트의 함수는 파라미터의 타입을 검사하지 않음!
let result = add(2, 8); // 함수 호출
console.log('result =', result);

let stringResult = add('ㄱ', 'ㄴ');
console.log('stringResult =', stringResult);

let mixedResult = add('a', 8);
console.log('mixedResult =', mixedResult);

// 자바스크립트 함수는 파라미터의 개수도 검사하지 않음!
result = add(1);    // 파라미터 개수보다 적은 수의 아규먼트를 전달.
console.log('result = ', result);
// -> 아규먼트를 전달하지 않은 파라미터는 undefined(값이 할당되지 않은 변수).
// -> 1 + undefined number = NaN(Not a Number)

result = add(1, 2, 5);  // 파라미터 개수보다 많은 수의 아규먼트를 전달.
console.log('result =', result);    // 앞에 두 개의 아규먼트만반영됨

// 자바스크립트 함수는 arguments 속성(property)를 가지고 있음.
// arguments 속성은 함수를 호출하는 곳에서 전달한 모든 아규먼트를 저장하는 객체.
function testArgs() {
    console.log(arguments);
    
    for (let x of arguments) {
        console.log(x);
    }
    
    console.log(arguments[0]);
}

testArgs();

testArgs('안녕', 10, 20, 'Hello');


/*
 자바스크립트 함수는 객체(object)! 
   --> 자바스크립트에서 함수도 변수에 값을 저장할 수 있고 나중에 사용할 수 있음
 1. 프로퍼티(property, 멤버)를 가질 수 있음. (예) arguments
 2. 변수에 저장할 수 있음
 3. 다른 함수를 호출할 때 아규먼트로 함수를 전달할 수 있음.
 4. 함수 내부에서 다른 함수를 선언(정의)할 수 있음.
 5. 함수 자체를 리턴할 수 있음.
*/

const plus = add;   // 함수 객체 add를 변수 plus에 할당(저장).
console.log(plus);  // 함수 객체를 출력.
console.log(plus(1, 5));    // 함수 호출 결과를 출력.


// 익명 함수를 선언하고, 변수에 저장.
const minus = function (x, y) {
    return x - y;
};

console.log(minus(5, 3));


// 함수를 아규먼트로 갖는 함수:
function calculate(x, y, op) {
    return op(x, y);
}

console.log('plus =', calculate(1, 2, plus));
console.log('minus =', calculate(1, 2, minus));
console.log('divide =', calculate(1, 2, function(x, y) {
    return x / y;
}));

// 콜백(callback): (나중에 호출하기 위해서) 다른 함수의 아규먼트로 전달되는 함수. 
// 이벤트 리스너(listener), 핸들러(handler), 콜백.

function increase(n) {
    // 지역(내부) 함수: 함수 내부에서 선언하는 함수
    function addN(x) {
        return x + n;
    }
    
    return addN;    // 함수 객체를 리턴.
}


const increaseByTwo = increase(2);
console.log(increaseByTwo(5));
console.log(increase(2)(6));

const increaseByFive = increase(5);
console.log(increaseByFive(10));


// 화살표 함수(Arrow Function)
// (파라미터, ...) => { 실행 코드; }
// (파라미터, ...) => 리턴값

const multiply = (x, y) => { return x * y; };
const multiply2 = (x, y) => x * y;

console.log(multiply(2, 3));
console.log(multiply2(3, 4));