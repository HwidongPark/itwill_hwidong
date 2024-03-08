/**
 * post-modify.js
 */

document.addEventListener("DOMContentLoaded", function() {
    const btnDelete = document.querySelector('button#btnDelete');
    const btnUpdate = document.querySelector('button#btnUpdate');
    
    // form요소를 찾음
    const form = document.querySelector("form#postModifyForm");
    const id = document.querySelector('input#id');
    const title = document.querySelector('input#title');
    const content= document.querySelector('textarea#content');
    const author = document.querySelector('input#author');
    const created_time = document.querySelector('input#created_time');
    const modified_time = document.querySelector('input#modified_time');
    
    btnDelete.addEventListener('click', function() {
        console.log('delete button clicked');
        
        const proceed = confirm("정말 삭제하시겠습니까?");
        console.log(`proceed=${proceed}`);
        
        if (proceed) {
            location.href=`delete?id=${id.value}`;
        }
    });
    
    
    btnUpdate.addEventListener('click', function() {
       console.log('update button clicked123');
       
       if (title.value == '' || content.value == '') {
           alert("제목과 내용은 반드시 입력해야 합니다.");
           return;
       }
       
       const proceed = confirm("정말 수정 하시겠습니까?");
       console.log(`proceed=${proceed}`);
       if (proceed) {
           console.log('수정 진행');
            form.action = '/spring2/post/update';   // 폼(양식)을 제출(submit)할 요청 주소. 기본값은 현재 URL.
            form.method = 'post';   // 폼 요청 방식(get/post). 기본값은 'get'
            form.submit();  // 폼 제출(서버로 요청 보냄.)
       }
       
        
    });
    
})