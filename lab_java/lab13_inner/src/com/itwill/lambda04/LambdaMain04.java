package com.itwill.lambda04;

import java.util.Arrays;
import java.util.List;

public class LambdaMain04 {

    public static void main(String[] args) {
        // 사원들의 리스트: Employee를 원소로 갖는 리스트를 선언, 초기화
        List<Employee> employees = Arrays.asList(
                new Employee(100, "강동균", "개발1팀", "사원", 1_000),
                new Employee(101, "심채원", "개발2팀", "대리", 900),
                new Employee(200, "김연수", "개발1팀", "대리", 1_100),
                new Employee(201, "임유정", "개발2팀", "대리", 2_000),
                new Employee(300, "박선희", "인사팀", "대리", 1_500),
                new Employee(301, "권오중", "인사팀", "사원", 700)
        );
        
        // 1. 모든 직원들의 정보를 한줄에 한 명씩 출력.
        employees.forEach(System.out::println);
        
        // 2. 개발팀(1, 2)에서 일하는 사원들의 급여 합계를 출력.
        double salarySum = employees.stream()
                .filter(employee -> employee.getDept().contains("개발"))
                .mapToDouble(Employee::getSalary).sum();
        System.out.println("개발1팀, 2팀의 salary합 = " + salarySum);
        
        // 3. 개발팀에서 일하는 사원들의 급여 평균을 출력.
        double meanDeveloperSalary = employees.stream()
                .filter(employee -> employee.getDept().contains("개발"))
                .mapToDouble(employee -> employee.getSalary()).average().orElseThrow();
        
        System.out.println("개발팀의 급여 평균은 = " + meanDeveloperSalary);
        
        // 4. 직급이 "사원"인 직원들의 급여 합계을 출력.
        double sumNewbieSalary = employees.stream()
                .filter(employee -> employee.getEmpTitle().equals("사원"))
                .mapToDouble(employee -> employee.getSalary()).sum();
        System.out.println("사원들의 급여 합은 = " + sumNewbieSalary);
        
        // 5. 직급이 "사원"인 직원들의 급여 평균을 출력.
        double meanNewbieSalary = employees.stream()
                .filter(employee -> employee.getEmpTitle().equals("사원"))
                .mapToDouble(employee -> employee.getSalary()).average().orElseThrow();
        
        System.out.println("사원들의 급여 평균은 = " + meanNewbieSalary);
        
        // 6. 급여가 1_000을 초과하는 사원들의 정보를 한 줄에 한명씩 출력.
        List<Employee> employeeWithOver1000 = employees.stream()
                .filter(employee -> employee.getSalary() > 1000).toList();
        employeeWithOver1000.forEach(System.out::println);
        
        
        // 7. 개발 1팀 사원들의 급여를 10% 인상하고, 인상한 급여의 평균을 출력.
        double meanDeveloperTeam1SalaryAfterRaise = employees.stream()
                .filter(employee -> employee.getDept().equals("개발1팀"))
                .mapToDouble(employee -> employee.getSalary() * 1.1).average().getAsDouble();
        System.out.println("연봉 인상 후 개발1팀의 평균 연봉은 = " + meanDeveloperTeam1SalaryAfterRaise);
        
        
        // 8. 직급 "대리"은 몇 명?
//        List<Employee> DaeriEmployees = employees.stream()
//                .filter(employee -> employee.getEmpTitle().equals("대리")).toList();
//        System.out.println("직급이 대리인 직원의 수는 = " + DaeriEmployees.size());
        
        long cnt = employees.stream()
                .filter((x) -> x.getEmpTitle().equals("대리"))
                .count();
        System.out.println("직급이 대리인 직원의 수는 = " + cnt);
        
        
        
    }   // main method 끝

}   // main class 끝
