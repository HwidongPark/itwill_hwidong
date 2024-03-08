package com.itwill.enum01;

public class EnumTest {

    public static void main(String[] args) {
        Meal3 meal3 = Meal3.BREAKFAST;
        meal3.getOrder(meal3);
        System.out.println(meal3.getOrder(meal3));
        
        
        Meal2 meal2 = Meal2.BREAKFAST;
        if (meal2 == Meal2.BREAKFAST) {
            
        } else if (meal2 == Meal2.LUNCH) {
            
        } else if (meal2 == Meal2.DINNER) {
            
        } else {
            
        }
        
        int meal = Meal.LUNCH;
        switch(meal) {
        case Season.SPRING:
            System.out.println("아침 굶고 온다");
            break;
        case Season.SUMMER:
            System.out.println("점심 학원에서 먹는다");
            break;
        case Season.FALL:
            System.out.println("저녁 술마신다");
            break;
        }
        

    }

}
