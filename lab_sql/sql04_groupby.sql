/*
오라클 함수(function)
1. 단일 행 함수:
  행(row) 하나씩 함수의 아규먼트로 전달되고, 행 마다 하나씩 결과가 리턴되는 함수
  (예) to_date, to_char, lower, upper, ...
2. 다중 행 함수: 
  여러개의 행이 함수의 아규먼트로 전달되고, 하나의 결과가 리턴되는 함수.
  (예) 통계 관련 함수: count, sum, avg, max, min, variance, stddev
*/

-- 단일행 함수 예 - 모든 행의 값을 소문자로 바꾸기:
SELECT ename, LOWER(ename), job, lower(job)
FROM emp;

-- to_char() 함수: 다른 타입의 값을 문자열로 변환. 날짜 -> 문자열.
SELECT hiredate, TO_CHAR(hiredate, 'YYYY/MM/dd HH:mi:ss')
FROM emp;


-- nvl(변수, 값): 변수가 null이면 값을 리턴하고, null이 아니면 원래 값을 리턴.
-- nvl2(변수, 값1, 값2): 변수가 null이 아니면 값1을 리턴하고, null이 아니면 값2를 리턴.
-- nvl2(var, var, 값)은 nvl(변수, 값)과 같은 역할
SELECT comm, nvl(comm, -1), nvl2(comm, comm, -1) FROM emp;

-- 다중 행 함수 예:
-- count(칼럼): null이 아닌 행의 개수를 리턴
SELECT COUNT(empno), COUNT(mgr), COUNT(comm)
FROM emp;

SELECT COUNT(*) FROM emp;   -- 테이블 행의 개수 찾음


-- 통계 함수:  합계, 평균, 최댓값, 최솟값, 분산, 표준편차
SELECT SUM(sal), ROUND(AVG(sal), 2), MAX(sal), MIN(sal), VARIANCE(sal), STDDEV(sal)
FROM emp;

-- (중요) 단일행 함수와 다중행 함수는 함께 select할 수 없음!!!
/*
SELECT sal, SUM(sal) 
FROM EMP;
*/
/*
SELECT NVL(sal, 0), SUM(sal)
FROM emp;
*/

-- 만약 각 직원의 본인 salary - 평균 salary를 알고싶으면 어떻게 하지?

/*
그룹별 쿼리: 
(예) 부서별 급여 평균, 부서별 인원수
(문법)
SELECT 컬럼, 함수 호출, ...
FROM 테이블
WHERE 조건 (1)
GROUP BY 그룹별로 묶어줄 변수
HAVING 조건식 (2)
ORDER BY 정렬 기준 변수(컬럼), ...
*/


-- 부서별 급여 평균
SELECT deptno, ROUND(AVG(sal), 2) AS "avg_salary"
FROM EMP
GROUP BY deptno    -- group by에 있는 column은 다중행과 함께 select에 넣을 수 있음
ORDER BY deptno;

/*
SELECT ename, sal, sal - avg(sal)
FROM emp;
*/


-- 모든 문제에서 소수점은 반올림해서 소수점 이하 2자리까지 표시
-- Ex1. 부서별 급여 평균, 표준편차를 부서번호 오름차순으로 출력.
SELECT deptno, Round(avg(sal), 2) AS "average salary", ROUND(STDDEV(sal), 2) AS "Std salary"
FROM emp
GROUP BY deptno
ORDER BY deptno;

-- Ex2. 직무별 직무, 직원수, 급여 최댓값, 최솟값, 평균을 직무 오름차순으로 출력
SELECT 
    job, COUNT(job), MAX(sal) AS "Max sal",
    MIN(sal) AS "Min sal",
    ROUND(AVG(sal), 2) AS "Average sal" 
FROM emp
GROUP BY job
ORDER BY job;

-- Ex3. 부서별, 직무별 부서번호, 직무, 직원수, 급여 평균을 검색
--      정렬 순서: (1) 부서번호 (2) 직무
SELECT deptno, job, COUNT(*), AVG(sal)
FROM emp
GROUP BY deptno, job
ORDER BY deptno, job;


-- Ex4. 입사연도별 사원수를 검색. (힌트) to_char(날짜, 포맷) 이용.
SELECT TO_CHAR(hiredate, 'YYYY') AS "Year", COUNT(*) AS "Count" -- SELECT에서 준 alias는 ORDER BY에서만 사용가능함
FROM emp
GROUP BY TO_CHAR(hiredate, 'YYYY')  -- 여기서는 Alias이름 못씀
ORDER BY "Year";     -- 여기서는 Alias이름 쓸 수 있음. 모든 검색이 끝나고 나서 검색결과가 만들어진 후 하는 처리기 때문에 alias가능

-- SELECT절에서 만든 별명 Alias는 ORDER BY 절에서만 사용 가능!!

-- WHERE절은 테이블에서 조건에 맞는 행들을 선택할 때.
-- HAVING절은 그룹별 쿼리에서 조건에 맞는 그룹을 찾는 경우.

-- 부서별 급여 평균:
SELECT deptno, ROUND(AVG(sal), 2)
FROM emp
GROUP BY deptno;

-- 부서별 급여 평균 검색. 급여 평균이 2000 이상인 부서만 검색:
SELECT deptno, ROUND(AVG(sal), 2) AS avg_sal
FROM emp
GROUP BY deptno
HAVING AVG(sal) >= 2000;

-- Ex. mgr 컬럼이 null이 아닌 직원들 중에서 부서별 급여 평균을 검색.
-- 정렬순서: 부서번호 오름차순.
SELECT deptno, ROUND(AVG(sal), 2) AS "mean salary"
FROM emp
WHERE mgr IS NOT NULL
GROUP BY deptno
ORDER BY deptno;


-- Ex. 직무별 사원수를 검색. PRESIDENT는 검색 제외.
-- 직무별 사원수가 3명 이상인 직무만 검색.
-- 정렬순서: 직무의 오름차순.
SELECT job, count(*)
FROM emp
WHERE job != 'PRESIDENT'
GROUP BY job
HAVING COUNT(*) >=3
ORDER BY job;

-- Ex. 입사연도, 부서번호, 입사 연도별 부서별 사원수 검색
-- 1980년은 검색에서 제외
-- 연도별 부서별 사원수가 2명 이상인 경우만 선택.
-- (1) 연도별, (2) 부서별 오름차순 출력.
SELECT TO_CHAR(hiredate, 'YYYY') AS "Hired_year", deptno, COUNT(*) AS "Number of people"
FROM emp
WHERE TO_CHAR(hiredate, 'YYYY') != 1980
GROUP BY TO_CHAR(hiredate, 'YYYY'), deptno
HAVING COUNT(*) >= 2
ORDER BY "Hired_year", deptno;




