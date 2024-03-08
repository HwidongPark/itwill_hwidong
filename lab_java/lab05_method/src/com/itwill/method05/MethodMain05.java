package com.itwill.method05;

public class MethodMain05 {

    public static void main(String[] args) {
        
        int[] scores = {70, 60, 100, 50, 81};
        
        int sum = sum(scores);
        System.out.println("총점 = " + sum);
        
        //***참고*** Java Compiler는 method를 호출할 수 있는지 없는지 까지만 본다.
        
        double avg = mean(scores);
        System.out.println("평균 = " + avg);
        
        String max = max(scores);
        System.out.println("최댓값 = " + max);
        
        String min = min(scores);
        System.out.println("최솟값 = " + min);
        
    }       // main end
    
    
    /**
     *arguments로 전달받은 정수들의 1차원 배열의 모든 원소들의 합계를 return
     * @param scores   합계를 구하기 위한 정수들의 배열.
     * @return      배열들의 합계를 정수로 반환
     */
    public static int sum(int[] scores) {           // sum method
        int sum = 0;
        for (int num : scores) {
            sum += num;
        }
        
        return sum;
    }
    
    /**
     * 원소들의 평균
     * @param scores    
     * @return
     */
    public static double mean(int[] scores) {           // mean method
//        double mean;
//        int numOfElements = 0;
//        int sum = 0;
//        for (int num : scores) {
//            sum += num;
//        }
//        
//        mean = (double) sum / scores.length;
//        return mean;
        
        return (double) sum(scores) / scores.length;            // 위에 sum을 만들어 놨으니 sum method를 활용하는 것을 추천함
    }
    
    
    
    /**
     * 정수들이ㅡ 배열의 array의 원소들 중 최댓값을 찾아서 return
     * @param scores array 정수(int)들의 배열
     * @return  최댓값
     */
    public static String max(int[] scores) {               // max method와 index를 반환
        int maxNumber = scores[0];
        int whereIsMax = 0;
        
        int index = 0;
        
        String indexMax;
        
        for (int num: scores) {
//            maxNumber = (num > maxNumber) ? num : maxNumber;
            if (num > maxNumber) {
                maxNumber = num;
                whereIsMax = index;
            }
            
            index ++;
        }
        
        indexMax = "[%d]%d".formatted(whereIsMax, maxNumber);
        
        return indexMax;
    }
    
    
    /**
     * 정수들의 배열 array의 원소들 중 최솟값을 리턴
     * 
     * @param scores array의 최솟값을 찾을 정수들의 배열.
     * @return  array의 최솟값
     */
    public static String min(int[] scores) {           // min method
        int minNumber = scores[0];
        int whereIsMin = 0;
        
        int index = 0;
        
        String indexMin;
        
        for (int num: scores) {
//            maxNumber = (num > minNumber) ? num : maxNumber;
            if (num < minNumber) {
                minNumber = num;
                whereIsMin = index;
            }
            
            index ++;
        }
        
        indexMin = "[%d]%d".formatted(whereIsMin, minNumber);
        
        return indexMin;
    }
    
    

    
// 풀이: 인덱스 찾는 다른 방법 메소드    
    /**
     * 정수들의 1차원 배열 array에서 최솟값의 인덱스를 리턴.
     * 만약 최솟값이 2개 이상이면, 첫번재 최솟값의 인덱스를 리턴
     * 
     * @param scores 정수들의 배열
     * return 첫번째 최솟값의 인덱스를 리턴
     */
    public static int indexOfMin(int[] scores) {
        
        int min = scores[0];    // 최솟값을 저장할 변수
        int minIndex = 0;   // 최솟값의 인덱스를 저장할 변수
        for (int i = 0; i < scores.length; i++) {
            if (min < scores[i]) {
                minIndex = i;
            }
        }
        
        return minIndex;
        
        
    }
    // 발견한 사실.. method name과 그 안의 local variable의 이름이 같아도 상관없다.. 근데 헷갈릴거 같다
    // methodName()과 localVariableName은 서로 ()로 구분되기 때문이다. 다른 언어에서는 상관있을수도 있음
    
    // **** Method가 만들어진 순서는 중요하지 않음 ****  ==> 앞뒤와 상관없이 호출만 하면 된다.
    

}       // class end
