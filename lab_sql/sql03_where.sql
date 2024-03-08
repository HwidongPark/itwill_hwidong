/*
테이블에서 데이터 검색:
    (1) projection: 테이블에서 원하는 칼럼 선택.
    (2) selection: 테이블에서 조건을 만족하는 행(레코드)들을 검색.
문법: select 컬럼 이름, ... from 테이블이름 where 조건식 order by 컬럼, ...;

조건식에서 사용되는 연산자들:
  (1) 비교 연산자: =, !=, >, >=, <, <=, is null, is not null, ...
  (2) 논리 연산자: and, or, not
  (3) 
*/

-- 문제: 직원 테이블에서 10번 부서에서 근무하는 직원들의 부서 번호, 사번, 이름을 출력
-- 정렬 순서: (1) 부서번호 (2) 사번
SELECT deptno, empno, ename
FROM emp
WHERE deptno = 10
ORDER BY empno;

-- 직원 테이블에서 수당(comm)이 null이 아닌 직원들의 사번, 부서 번호, 이름, 수당을 출력
-- 정렬 순서: 사번
SELECT empno, deptno, ename, comm
FROM emp
WHERE comm is not null
ORDER BY empno;


-- 직원테이블에서 급여가 2000 이상인 직원들의 이름, 직무, 급여를 출력.
SELECT ename, job, sal
FROM emp
WHERE sal >= 2000;

-- 직원 테이블에서 급여가 2000 이상, 3000 이하인 직원들의 이름 직무 급여를 출력
-- 정렬 순서: 급여, 
SELECT ename, job, sal
FROM emp
-- WHERE sal >= 2000 AND sal <= 3000
WHERE sal BETWEEN 2000 AND 3000     -- BETWEEN A AND B와 같이 사용 가능.
ORDER BY sal;


-- 직원 테이블에서 10번 부서 또는 20번 부서에서 근무하는
-- 직원들의 부서번호, 이름, 급여를 검색.
-- 정렬 순서: (1) 부서 번호 오름차순, (2) 급여 내림차순
SELECT deptno, ename, sal
FROM emp
WHERE deptno = 10 or deptno = 20
ORDER BY deptno, sal DESC;
 

SELECT deptno, ename, sal
FROM emp
WHERE deptno in (10, 20)
ORDER BY deptno, sal DESC;


-- 직원 테이블에서 직무가 'CLERK'인 
-- 직원들의 직무, 이름, 급여를 출력.
-- 정렬 순서: 이름.
SELECT job, ename, sal
FROM emp
WHERE job = 'CLERK'     -- 안의 값들은 case sensitive!
ORDER BY ename;


-- 직원 테이블에서 직무가 'CLERK' 또는 'MANAGER'인 
-- 직원들의 직무, 이름, 급여를 검색.
-- 정렬 순서: (1) 직무, (2) 급여.
SELECT job, ename, sal
FROM emp
WHERE job in ('CLERK', 'MANAGER')
ORDER BY job, sal;



-- 직원 테이블에서 20번 부서에서 근무하는 CLERK의 
-- 모든 정보(컬럼)을 검색.
SELECT *
FROM emp
WHERE deptno = 20 AND job = 'CLERK';

-- 직원 테이블에서 CLERK, ANALYST, MANAGER가 아닌 
-- 직원들의 사번, 이름, 직무, 급여를 검색
-- 정렬 순서: 사번.
SELECT empno, ename, job, sal
FROM emp
WHERE job NOT IN ('CLERK', 'ANALYST', 'MANAGER');


-- 숫자 타입 뿐만 아니라, 문자열, 날짜 타입도 대소비교가 가능.
-- (예) 'a' < 'b', 2023/10/19 < 2023/10/20

-- '1987/01/01' 이후에 입사한 직원들의 레코드(모든 칼럼)를 출력.
-- 정렬 순서: 입사일 오름차순
SELECT *
FROM emp
WHERE hiredate >= '1987-01-01'
ORDER BY hiredate;
-- 오라클은 hiredate 컬럼의 값(날짜 타입)을 문자열 타입으로 변환해서
-- '1987/01/01' 문자열과 크기 비료를 수행 - 암시적 타입 변환.

SELECT * FROM emp
WHERE hiredate >= to_date('1987/01/01')
ORDER BY hiredate;
-- to_date(문자열): 문자열을 날짜 타입으로 변환하는 함수.
-- 명시적으로 날짜 타입으로 변환해서 비교한 것임


-- 특정 문자열로 시작하거나, 특정 문자열이 포함된 값을 찾는 쿼리 - like 검색
-- 이름이 'A'로 시작하는 직원들:
SELECT ename FROM emp WHERE ename like 'A%';

-- like 검색에서 사용되는 wildcard
--   (1) %: 글자 수 제한 없음.
--   (2) _: 밑줄이 있는 자리에 어떤 문자가 있어도 됨.
SELECT * FROM emp WHERE job LIKE '%ERK';


-- 30번 부서에서 근무하고, 직무에 'SALES'를 포함하는 직원들의
-- 사번, 이름, 급여, 수당, 부서번호, 직무를 출력.
-- 정렬 순서: 사번.
SELECT empno, ename, sal, comm, deptno, job
FROM emp
WHERE deptno = 30 AND job LIKE '%SALES%'
ORDER BY empno;


SELECT empno, ename, sal, comm, deptno, LOWER(job)
FROM emp
WHERE deptno = 30 AND LOWER(job) LIKE '%sales%'
ORDER BY empno;