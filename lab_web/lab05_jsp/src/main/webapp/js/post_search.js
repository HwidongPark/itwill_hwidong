/**
 * 
 */

document.addEventListener('DOMContentLoaded', function() {
    
    const toList = document.querySelector("li#toList");
    const searchBtn = document.querySelector("button#searchBtn");
    
    toList.display = 'none';
    
    console.log("we here!");
    
    searchBtn.addEventListener('click', function() {
        toList.display = "inline-block";
        console.log("clicked");
    })
    
    searchBtn.addEventListener('keypress', function(e) {
        if(e.key === 'Enter') {
            toList.display = "inline-block";
            console.log("clicked");
        }
    })
      
})