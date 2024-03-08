/**
 * event.js
 * 13_event.html에 포함
 */

 document.addEventListener("DOMContentLoaded", function(e) {
     // console.log(e);
     
     // 1. button#btnInput 버튼에 클릭 이벤트 리스너를 등록:
     // input에 입력된 내용을 ul의 리스트 아이템으로 추가.
     const list = document.getElementById("itemList");
     const btn = document.getElementById("btnInput");
     const inputList = document.getElementById("itemInput");
     
     btn.addEventListener("click", function(e) {
         // console.log(e); //-> PointerEvent
         let text = inputList.value;    // input#itemInput에 입력된 값
         // list.innerHTML += '<li>' + text + '</li>';
         list.innerHTML += `<li>${text}</li>`;
         inputList.value = '';
         inputList.focus(); // input에 포커스를 줌.
     });
     
     
     // 2. #itemInput2 요소에 'keydown' 이벤트 리스너를 등록:
     // 엔터키가 눌렸을 때, input에 입력된 내용을 ul에 리스트 아이템으로 추가.
     const inputList2 = document.getElementById("itemInput2");
     
     inputList2.addEventListener('keydown', function(e) {
         let text = inputList2.value;
         if (e.key == "Enter") {
             // list.innerHTML += '<li>' + text + '</li>';
             list.innerHTML += `<li>${text}</li>`;
             inputList2.value = '';
         }
     });
     
     
     // 3. #username 요소에 이벤트 리스너를 등록: 
     // input에 입력된 내용이 바뀔 때마다 div에 입력된 내용을 씀.
     const inputUsername = document.querySelector("input#username");
     const div = document.querySelector("div#div");
     
    inputUsername.addEventListener('change', function(e) {
        // console.log(e);
        // change이벤트 언제발생? --> 1) 내용 바뀌고 Enter누르거나, 2) Focus를 잃어버릴 때
        div.innerHTML = `사용자 이름: <strong>${inputUsername.value}</strong>`;
     });
     
     
     // 다른 방식 도전
     const inputUsername2 = document.querySelector("input#username2");
     const div2 = document.querySelector("div#div2");
     
     inputUsername2.addEventListener('keyup', function(e) {
        
        div2.innerHTML = inputUsername2.value;
     });
     
     
     // 다른 방식 도전
     const inputUsername3 = document.querySelector("input#username3");
     const div3 = document.querySelector("div#div3");
     inputUsername3.addEventListener('keydown', function(e) {
         
         console.log(e);
         
        /* if (e.key == 'Backspace') {
             div3.innerHTML = div3.innerHTML.substring(0 , div3.innerHTML.length - 2);
             return;
         }*/
         
         div3.innerHTML += e.key;
         //inputUsername3.focus();
         document.querySelector('#username3').focus();
        
     });
     
     
     
 });