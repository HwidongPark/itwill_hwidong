package com.itwill.class05;

public class AppMainSolution05 {
    
    
    public static void main(String[] args) {
     // Account 타입 객체를 생성하고 메서드를 테스트.
        AccountSolution account1 = new AccountSolution(123456, 10000);   
        System.out.println(account1);
        account1.info();
        
        AccountSolution account2 = new AccountSolution(123456789, 1000);
        System.out.println(account2);
        account2.info();
        
        account1.deposit(2000);
        account1.info();
        
        account1.deposit(-1000);
        account1.info();
        
        account1.withdraw(3000);
        account1.info();
        
        account1.withdraw(10000);
        account1.info();
        
        account1.transfer(account2, 3000);
        account1.info();
        account2.info();
        
        
    }
    
    
    

}
