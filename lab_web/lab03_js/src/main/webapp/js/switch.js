/**
 * switch.js
 * 03_switch.html 파일에 포함.
 * switch-case 구문.
 */
 
 // document.addEventListener('이벤트 이름', 이벤트핸들러 함수);
 // document는 HTML문서를 의미
 // -> 이벤트가 발생되면 브라우저가 등록된 이벤트핸들러 함수를 호출
 document.addEventListener('DOMContentLoaded', function () {
     // HTML DOM(Document Object Model) 요소(객체)들이 모두 만들어졌을 때 실행되는 코드.
     
     // select#weekday요소를 찾음:
     // const weekday = document.getElementById('weekday');
     const weekday = document.querySelector('#weekday');
     console.log(weekday);
     
     // div#result요소를 찾음
     const result = document.querySelector('div#result');
     
     // button#btn 요소를 찾음:
     const btn = document.querySelector('button#btn');
     // btn 객체에 이벤트 핸들러를 등록:
     btn.addEventListener('click', function() {
         alert('동작할까요?');
         // select에서 선택된 값 찾음:
         const value = weekday.value;
         
         switch(value) {
             case 'mon':
             case 'tue':
             case 'wed':
             case 'thu':
             case 'fri':
                 result.innerHTML = '학원 출석';
                 break;
             case 'sat':
             case 'sun':
                 result.innerHTML = '쉼...';
                 break;
            default:
                result.innerHTML = '몰라요!';
         }
         
     })
     
 });
 