/**
 * array_fn.js
 * 09_arrayfn.html에 포함
 */



document.addEventListener('DOMContentLoaded', function () {
    
    let numbers = []; // 빈 배열
    
    
    // numbers 배열에 정수 1 ~ 10까지 차례로 저장하고 콘솔 로그로 출력.
    for (let i = 1; i <= 10; i++) {
        // -> 원본 배열에 원소를 추가. 원본 배열이 변경.
        //numbers.push(i);
        
        // -> concat은 원본 배열을 변경하지 않음! 새 원소가 추가된 새로운 배열을 만들어서 리턴.
        numbers = numbers.concat(i);
        
    }
    
    
    console.log(numbers);
    
    
    // numbers에서 홀수들만 찾아서 저장하는 배열을 만들고 콘솔 로그로 출력
    const numbers2 = numbers.filter(function (number) {
       return number % 2 === 1; 
    });
    
    console.log(numbers2);
    
    
    let odds = [];  // 다른 풀이
    for (let x of numbers) {
        if (x % 2) {    // x % 2 === 1
            odds = odds.concat(x);
        }
    }
    
    console.log(odds);
        
    // numbers 원소들의 제곱을 저장하는 배열을 만들고, 콘솔 로그 출력.
    const numbers3 = numbers.map((x) => x * x);    // 거듭제곱 연산자
    
    console.log(numbers3);
    
    // numbers에서 홀수들의 제곱을 저장하는 배열을 만들고, 콘솔 로그 출력.
    const numbers4 = numbers.filter((x) => x % 2 ===1).map((x) => x*x);
    
    console.log(numbers4);
    
});
