/**
 * user.js
 * 유저관련 자바스크립트
 */

 document.addEventListener('DOMContentLoaded', function() {
     
     const useridElement = document.querySelector('#userid');
     const passwordElement = document.querySelector('#password');
     const emailElement = document.querySelector('#email');
     const btnSignUp = document.querySelector('input#btnSignUP');
     let useridCheck = false;
     let passwordCheck = false;
     let emailCheck = false;
     
     let isExisting = 0;
     
     userid.addEventListener('change', function() {
         let userid = useridElement.value;
         console.log(`userInput = ${userid}`);
         
         axios.post('/spring2/api/isExistingUserid', {userid: userid})
            .then((response) => {
                // console.log(response);                
                isExisting = document.querySelector('#isExistingUser');
                
                if(response.data === 1) {
                    isExisting.innerHTML = '이미 존재하는 아이디 입니다.';
                    isExisting.style.color = 'red';
                    
                    
                } else {
                    isExisting.innerHTML = '';
                    if (userid !== '') {
                        useridCheck = true;
                        activateButton();
                    } else {
                        useridCheck = false;
                    }
                    
                }
            })
            .catch((error) => {
                console.log(error);
            })
     })
     
     
     passwordElement.addEventListener('change', function(){
        if (passwordElement.value !== '') {
            passwordCheck = true;
            activateButton();
        } else {
            passwordCheck = false;
        }
     })
     
     emailElement.addEventListener('change', function() {
         if (emailElement.value !== '') {
             emailCheck = true;
             activateButton();
         } else {
             emailCheck = false;
         }
     })
     
     
     // 버튼 활성화 시키는 함수
     function activateButton() {
         if(useridCheck === true && passwordCheck === true && emailCheck === true) {
             btnSignUp.classList.remove('disabled');
         } else {
             btnSignUp.classList.add('disabled');
         }
     }
     
     
     
 })