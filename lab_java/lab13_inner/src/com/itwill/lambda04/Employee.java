package com.itwill.lambda04;

public class Employee {
    // field
    private int empId;  // 사번
    private String empName; // 사원 이름
    private String dept;    // 부서(department)
    private String empTitle;    // 사원의 직급(대리, 과장, 부장, ...)
    private double salary;
    
    
    // Constructor    
    public Employee(int empId, String empName, String dept, String empTitle, int salary) {
        super();
        this.empId = empId;
        this.empName = empName;
        this.dept = dept;
        this.empTitle = empTitle;
        this.salary = salary;
    }

    // Getter / Setter
    public int getEmpId() {
        return empId;
    }
    
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    
    public String getEmpName() {
        return empName;
    }
    
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    
    public String getDept() {
        return dept;
    }
    
    public void setDept(String dept) {
        this.dept = dept;
    }
    
    public String getEmpTitle() {
        return empTitle;
    }
    
    public void setEmpTitle(String empTitle) {
        this.empTitle = empTitle;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public void setSalary(int salary) {
        this.salary = salary;
    }
    
    
    @Override
    public String toString() {
        return String.format("Employee [empId=%s, empName=%s, dept=%s, empTitle=%s"
                + ", salary= %.2f]", empId, empName, dept, empTitle, salary);
    }
    
    
    
    
    

}
