package com.itwill.class05;

/**
 * 은행 계좌 정보.
 * 속성: 계좌번호(int), 잔고(int)
 * 기능: 입금, 출금, 이체, 정보 출력
 */
public class Account {
    // field
    int accountNumber;
    int balance;
    
    // constructor - argument 2개를 갖는 생성자. (생성자 하나만 만들자..)
    public Account(int accountNumber, int balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    
    
    // method
    /**
     * deposit(입금).
     * 
     * @param amount 입금액.
     * @return 입금 후 잔액.
     */
    public int deposit(int depositAmount) {
        System.out.printf("%d 원을 입금합니다.%n", depositAmount);
        balance += depositAmount;
        
        return balance;
    }
    // parameter 얼마를 입금하겠다. --> 잔고에서 +
    
    
    
    /**
     * withdraw(출금).
     * 
     * @param amount 출금액.
     * @return 출금 후 잔액.
     */
    // parameter 얼마를 출금하겠다. --> 잔고에서 -
    public int withdraw(int withdrawalAmount) {
        if (balance >= withdrawalAmount) {
            System.out.printf("%d 원을 출금합니다.%n", withdrawalAmount);
            balance -= withdrawalAmount;
        } else {
            System.out.println("잔액이 부족합니다.");
        }
        return balance;
    }
    
    
    
    /**
     * transfer(이체).
     * 
     * @param to 이체할 계좌(Account 타입).
     * @param amount 이체할 금액.
     * @return true. 
     */
    // 내 계좌에서 다른사람 계좌로 이체하겠다. parameter 상대방 계좌 object, 
    // 입금한 만큼 돈 빠지게하자
    public boolean transfer(Account account,int transferAmount) {
        if (this.balance >= transferAmount) {
            System.out.printf("%d 원을  계좌이체 합니다.%n", transferAmount);
            this.balance -= transferAmount;          // 내 계좌로부터 이체금액 출금
            account.balance += transferAmount;      // 계좌이체 받은 사람의 잔액 증가
            
        } else {
            System.out.println("잔액이 부족합니다.");
        }
        
        System.out.printf("현재 잔액은 %d 원 입니다.\n", this.balance);
        
        return account instanceof Account;
    }
    
    
    /**
     * info(정보).
     * 계좌 번호, 현재 잔액을 출력.
     */
    // 정보만 출력하고 끝.
    public void checkAccount() {
        System.out.println("계좌 번호: " +accountNumber);
        System.out.println("현재 잔액: " + balance);
    }
    
    
    
}