package com.itwill.loop05;

public class LoopMain05 {

    public static void main(String[] args) {
        // 반복문에서 break와 continue
        // break: 반복문 실행 중 break 문장을 만나면, break가 포함된 가장 가까운 반복문 블록을 끝냄. (예를 들어, break가 반복문 안에 반복문에 포함돼 있을 경우 반복문 안의 반복문만 끝냄)
        // continue: 반복문 실행 중에 continue 문장을 만나면, continue 아래의 코드들은 무시하고 그 다음의 반복 과정을 계속 수행.
        
        // break예시 ... 무한루프 종류시킬 때 많이 활용된다.
        for (int n = 1; n <= 5; n++) {
            if (n == 3) {
                break;
            }
            System.out.println(n);
        }
        
        System.out.println("=======================================");
        
        // continue 예시... 사실 continue는 많이 사용되지 않는다.
        for (int n = 1; n <= 5; n++) {
            if (n == 3) {
                continue;
            }
            System.out.println(n);
        }
        
        System.out.println("=======================================");
        
        // break 문장 활용 - 무한 반복문을 종료시킬 때.
        // for문에서 조건식이 없는 경우는 무한 반복문.
        for (int n = 1; ; n++) {
            System.out.println(n);
            if (n == 5
                    ) {
                break;
            }
        }
        
    }

}
