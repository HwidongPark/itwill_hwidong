package com.itwill.springboot2.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface Employee2Repository extends JpaRepository<Employee2, Integer> {
    
    
    // JPA query method 작성 방법:
    
    // 부서 번호가 일치하는 모든 직원들의 정보를 검색
    // select * from employees where department_id = ?
    List<Employee2> findByDepartmentDepartmentId(Integer departmentId);
    
    //부서 이름이 일치하는 (대소문자는 구분하지 않는) 모든 직원들의 정보를 검색    
    List<Employee2> findByDepartmentDepartmentNameIgnoreCase(String departmentName);
    
    // 성(lastName)이 일치하는 모든 직원들의 정보를 검색(where last_name = ?)
    List<Employee2> findByLastName(String lastName);
    
    
    // 성(lastName)에 문자열이 포함되는 직원들의 정보(where last_name like ?)
    List<Employee2> findByLastNameLike(String lastName);
    
    // 성(lastName)에 대소문자 구분없이 특정 문자열이 포함되는 직원들의 정보
    // where upper(last_name) like upper(?) order by last_name
    List<Employee2> findByLastNameLikeIgnoreCase(String lastName);
    
    // lastName에 대소문자 구분없이 특정 문자열이 포함되고, 정렬 순서는 lastName 오름차순
    List<Employee2> findByLastNameLikeIgnoreCaseOrderByLastNameAsc(String lastName);
    
    
    // 급여(salary) 어떤 값을 초과하는 직원들의(where salary > ?)
    List<Employee2> findBySalaryGreaterThan(double salary);
    
    // 급여가 어떤 값 미만인 직원들의 정보(where salary < ?)
    List<Employee2> findBySalaryLessThan(double salary);
    
    // 급여가 어떤 범위 안에 있는 직원들의 정보(where salary between ? and ?)
    List<Employee2> findBySalaryBetween(Double minSal, Double maxSal);
    
    // 입사날짜(hireDate)가 특정 날짜 이후인 직원들의 정보(where hire_date > ?)
    List<Employee2> findByHireDateAfter(LocalDateTime hireDate);
    
    // 입사날짜가 특정 날짜 이전인 직원들의 정보(where hire_date < ?)
    // 만약 페이징하고싶다면 두번째 파라미터를 Pageable로 만들면 된다!!!!
    List<Employee2> findByHireDateBefore(LocalDateTime hireDate, Pageable pageable);

    
    // 입사날짜가 날짜범위에 포함되는 직원들의 정보(where hire_date between ? and ?)
    List<Employee2> findByHireDateBetween(LocalDateTime start, LocalDateTime end);
    
    
    // 매니저가 null인 직원 찾기 (where manager_id is null)
    List<Employee2> findByManagerEmployeeIdIsNull();
    
    List<Employee2> findByFirstNameAndLastName(String firstName, String lastName);
    
    // JPQL(Java Persistence Query Language)
    // JPA에서 사용하는 객체지향 쿼리.
    // 테이블 이름과 테이블 컬럼으로 SQL을 작성하는게 아니라,
    // 엔터티 객체의 이름과 엔터티 필드 이름으로 쿼리를 작성하는 문법.
    // alias(별명)을 반드시 사용해야 함
    // 엔터티 이름과 엔터티 필드 이름들은 대소문자를 구분함
//    @Query("SELECT e FROM Employee2 e WHERE firstName = ?1 and e.lastName = ?2")
//    List<Employee2> findByName(String firstName, String lastName);

    @Query("SELECT e FROM Employee2 e " +
           "WHERE e.firstName = :first and e.lastName = :last")
    List<Employee2> findByName(@Param("first") String firstName, @Param("last") String lastName);
    
    
    @Query("SELECT e FROM Employee2 e Where e.department.departmentId = ?1")
    List<Employee2> findByDepartmentId(Integer departmentId);
    
    
    // firstName 또는 lastName에 특정 문자열을 포함하는 직원들의 정보. 대/소문자 구분 없이.
//    @Query("SELECT e FROM Employee2 e WHERE UPPER(e.firstName) like UPPER(CONCAT('%', ?1, '%')) OR UPPER(e.lastName) like UPPER(CONCAT('%', ?2, '%'))")
//    List<Employee2> findByNameLike(String firstName, String lastName);
    
    @Query("SELECT e FROM Employee2 e WHERE UPPER(e.firstName) like UPPER(CONCAT('%', :keyword, '%')) OR UPPER(e.lastName) like UPPER(CONCAT('%', :keyword, '%'))")
    List<Employee2> findByNameLike(@Param("keyword")String keyword);
    
    @Query("SELECT e FROM Employee2 e WHERE e.department.departmentName = ?1")
    List<Employee2> findByDeparmentName(String departmentName);
    
    // 특정 도시(예: Seattle, Toronto)에 근무하는 직원들의 정보 검색
    @Query("SELECT e FROM Employee2 e WHERE e.department.location.city = ?1")
    List<Employee2> findByCity(String city);
    
    // 특정 국가(예: Canada, United States of America)에 근무하는 직원들의 정보 검색
    @Query("SELECT e FROM Employee2 e WHERE e.department.location.country.countryName = ?1")
    List<Employee2> findByCountry(String country);    
    
    
    // 특정 국가에서 근무하는, 급여가 특정 금액 이상인, 입사일이 특정 날짜 이후인 직원 검색
    @Query("SELECT e FROM Employee2 e "
            + "WHERE e.department.location.country.countryName = ?1 "
            + "AND e.salary >= ?2 AND e.hireDate >= ?3")
    List<Employee2> findByCountrySalaryHireDate(String country, double salary, LocalDateTime hireDate);
    
    
    
}
