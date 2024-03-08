package com.itwill.swingpractice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccountDao {
    
    // Singletone
    private static AccountDao instance = null;
    
    private AccountDao() {};
    
    public static AccountDao getInstance() {
        
        if (instance == null) {
            instance = new AccountDao();
        }
        
        return instance;
    }
    
    // 필드
    private Set<Account> accountSet;
    
    
    
    // 메서드
    // 유저 리스트 전달.
    public Set<Account> getUserset() {
        return this.accountSet;
    }
    
    // 로그인
    /**
     * 로그인
     * @param signInAccount
     * @return 성공시 true, 실패시 false
     */
    public boolean signIn(Account signInAccount) {
        boolean result = false;
        
        if (accountSet.contains(signInAccount)) {
            System.out.println("로그인 성공");
            result = true;
        } else {
            System.out.println("로그인 실패");
        }
        
        return result;
    }
    
    
    // 회원가입
    /**
     * 회원가입. 
     * @param signUpAccount
     * @return 성공시 true, 실패시 false
     */
    public boolean signUp(Account signUpAccount) {
       boolean result = false;
        for (Account account : accountSet) {
           if (account.getId().equals(signUpAccount.getId())) {
               System.out.println("회원가입 실패");
               return false;
           }
        }
        
        accountSet.add(signUpAccount);
        System.out.println("회원가입 성공");
        result = true;
        
        return result;
    }
    
    
    
}
