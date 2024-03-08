/*
연습문제:
o. https://github.com/JakeOh/202309_itwill_java146_lab_java/blob/master/lab_sql/hr.sql
파일을 다운받고 스크립트 전체 실행.
o. countries, departments, employees, jobs, locations, regions 테이블 사용.
*/

-- 1. 직원의 last_name과 부서 이름을 검색. (inner join)
SELECT e.last_name, d.department_name
FROM employees e
    JOIN departments d
        ON e.department_id = d.department_id;

-- 2. 직원의 last_name과 부서 이름을 검색. 부서 번호가 없는 직원도 출력. (left outer join)
SELECT e.last_name, d.department_name
FROM employees e
    LEFT JOIN departments d
        ON e.department_id = d.department_id;

-- 3. 직원의 last_name과 직무 이름(job title)을 검색.
SELECT e.last_name, j.job_title
FROM employees e
    JOIN jobs j
        ON e.job_id = j.job_id;

-- 4. 직원의 last_name과 직원이 근무하는 도시 이름(city)를 검색.
SELECT e.last_name, l.city
FROM employees e
    JOIN departments d
        ON e.department_id = d.department_id
    JOIN locations l
        ON d.location_id = l.location_id;

-- 5. 직원의 last_name과 직원이 근무하는 도시 이름(city)를 검색. 근무 도시를 알 수 없는 직원도 출력.
SELECT e.last_name, l.city
FROM employees e
    LEFT JOIN departments d
        ON e.department_id = d.department_id
    LEFT JOIN locations l
        ON d.location_id = l.location_id;

-- 6. 2008년에 입사한 직원들의 last_name을 검색.
SELECT last_name
FROM employees
WHERE TO_CHAR(hire_date, 'YYYY') = '2008';

-- 7. 2008년에 입사한 직원들의 부서 이름과 부서별 인원수 검색.
SELECT d.department_name, COUNT(*) AS "num_of_emps"
FROM employees e
    JOIN departments d
        ON e.department_id = d.department_id
WHERE TO_CHAR(e.hire_date, 'YYYY') = '2008'
GROUP BY d.department_name;

-- 8. 2008년에 입사한 직원들의 부서 이름과 부서별 인원수 검색. 
--    단, 부서별 인원수가 5명 이상인 경우만 출력.
SELECT d.department_name, COUNT(*) AS "num_of_emps"
FROM employees e
    JOIN departments d
        ON e.department_id = d.department_id
WHERE TO_CHAR(e.hire_date, 'YYYY') = '2008'
GROUP BY d.department_name
HAVING COUNT(*) >= 5;

-- 9. 부서번호, 부서별 급여 평균을 검색. 소숫점 한자리까지 반올림 출력.
SELECT d.department_id, d.department_name, ROUND(AVG(e.salary), 2)
FROM departments d
    JOIN employees e
        ON d.department_id = e.department_id
GROUP BY d.department_id, d.department_name
ORDER BY d.department_id;

-- 10. 부서별 급여 평균이 최대인 부서의 부서번호, 급여 평균을 검색.

SELECT d.department_id, ROUND(AVG(e.salary), 2) AS "avg_sal"
FROM departments d
    JOIN employees e
        ON d.department_id = e.department_id
GROUP BY d.department_id
HAVING ROUND(AVG(e.salary), 2) = (
    SELECT MAX("avg_sal")
    FROM (
        SELECT d.department_id, ROUND(AVG(e.salary), 2) AS "avg_sal"
        FROM departments d
            JOIN employees e
                ON d.department_id = e.department_id
        GROUP BY d.department_id
)
);


-- with-as-selet구문
with t as (
    SELECT department_id, AVG(salary) as "AVG_SAL"
    FROM employees
    group by department_id
)
SELECT department_id, round(AVG_SAL, 1)
FROM t
WHERE AVG_SAL = (
    SELECT MAX(AVG_SAL) FROM t
);

-- 11. 사번, 직원이름, 국가이름, 급여 검색.
-- employees department_id -> departments의 location_id -> locations의 country_id -> countries의 country_name
SELECT e.employee_id, e.first_name, e.last_name, c.country_name, e.salary
FROM employees e
    JOIN departments d
        ON e.department_id = d.department_id
    JOIN locations l
        ON d.location_id = l.location_id
    JOIN countries c
        ON l.country_id = c.country_id;
        
-- 12. 국가이름, 국가별 급여 합계 검색
SELECT c.country_name, SUM(e.salary) AS "sal_summed_up"
FROM employees e
    JOIN departments d
        ON e.department_id = d.department_id
    JOIN locations l
        ON d.location_id = l.location_id
    JOIN countries c
        ON l.country_id = c.country_id
GROUP BY c.country_name
ORDER BY "sal_summed_up" DESC;

-- 13. 사번, 직원이름, 직무 이름, 급여를 검색.
SELECT e.employee_id, e.first_name, e.last_name, j.job_title, e.salary
FROM employees e
    JOIN jobs j
        ON e.job_id = j.job_id;

-- 14. 직무 이름, 직무별 급여 평균, 최솟값, 최댓값 검색.
SELECT j.job_title, AVG(e.salary), MIN(e.salary), MAX(e.salary) 
FROM employees e
    JOIN jobs j
        ON e.job_id = j.job_id
GROUP BY j.job_title;

-- 15. 국가 이름, 직무 이름, 국가별 직무별 급여 평균 검색.
SELECT c.country_name, j.job_title, ROUND(AVG(e.salary), 2) AS "sal_avg"
FROM employees e
    JOIN jobs j
        ON e.job_id = j.job_id
    JOIN departments d
        ON e.department_id = d.department_id
    JOIN locations l
        ON d.location_id = l.location_id
    JOIN countries c
        ON l.country_id = c.country_id
GROUP BY c.country_name, j.job_title
ORDER BY c.country_name;

-- 16. 국가 이름, 직무 이름, 국가별 직무별 급여 합계을 출력.
--     미국에서, 국가별 직무별 급여 합계가 50,000 이상인 레코드만 출력.
SELECT c.country_name, j.job_title, SUM(e.salary) AS "sal_summed_up"
FROM employees e
    JOIN jobs j
        ON e.job_id = j.job_id
    JOIN departments d
        ON e.department_id = d.department_id
    JOIN locations l
        ON d.location_id = l.location_id
    JOIN countries c
        ON l.country_id = c.country_id
GROUP BY c.country_name, j.job_title
HAVING c.country_name != 'United States of America' OR
    c.country_name = 'United States of America' AND
    SUM(e.salary) >= 50000
ORDER BY c.country_name;
