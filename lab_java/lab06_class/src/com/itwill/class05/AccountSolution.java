package com.itwill.class05;

public class AccountSolution {
    // field
    int accountNo;
    int balance;
    
    
    // constructor - argument 2개를 갖는 생성자.
    public AccountSolution(int accountNo, int balance) {      // public이라는 의미는 다른 class에서 사용할 수 있다는 의미
        this.accountNo = accountNo;
        this.balance = balance;
    }
    
    // method
    /**
     * deposit(입금).
     * 
     * @param amount 입금액.
     * @return 입금 후 잔액.
     */
    public int deposit(int amount) {

        if (amount > 0) {
            this.balance += amount;
        } else {
            System.out.println("입금액은 0보다 커야 합니다...");           
        }
        
        return this.balance;
    }
    
    
    
    /**
     * withdraw(출금).
     * 
     * @param amount 출금액.
     * @return 출금 후 잔액.
     */
    public int withdraw(int amount) {
        if (amount > 0) {
            if (this.balance > amount) {
                this.balance -= amount;
            } else {
                System.out.println("잔액이 부족합니다.");
            }
        } else {
            System.out.println("출금액은 0보다 커야 합니다...");
        }
        
        return this.balance;
    }
    
    
    /**
     * transfer(이체).
     * 
     * @param to 이체할 계좌(Account 타입).
     * @param amount 이체할 금액.
     * @return true. 
     */
    public boolean transfer(AccountSolution to, int amount) {
//        this.balance -= amount;     // 내 계좌에서 출금.
//        to.balance += amount;       // 상대방 계좌에 입금.
//        this.withdraw(amount);
//        to.deposit(amount);
        boolean result = false;
        if (amount > 0) {
            
            if (amount <= this.balance) {
                this.withdraw(amount);      // 내 계좌에서 출금.
                to.deposit(amount);     // 상대방 계좌에 입금
                result = true;      // 이체 성공 반환 값
            } else {
                System.out.println("이체할 금액은 0보다 커야 합니다...");
                
            }
            
        } else {
            System.out.println("이체할 금액은 0보다 커야 합니다...");
        }
        
        
        return result;
    }
    
    
    /**
     * info(정보).
     * 계좌 번호, 현재 잔액을 출력.
     */
    public void info() {
        System.out.println("--- 계좌 정보 ---");
        System.out.println("계좌 번호: " + this.accountNo);
        System.out.println("잔고: " + this.balance);
        System.out.println("-----------------"
                + "");
    }

}
