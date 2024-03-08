/*
서브쿼리(subquery): SQL 문장의 일부로 다른 SQL 문장이 포함되는 것.
*/

-- 급여 평균보다 더 많은 급여를 받는 직원들?
SELECT AVG(sal) FROM emp;

SELECT *
FROM emp
WHERE sal >= 2073.2;


SELECT *
FROM emp
WHERE sal >= (select avg(sal) FROM emp);

SELECT ename, sal, ROUND(sal - (SELECT AVG(sal) FROM emp), 2) AS "diff"
FROM emp;

-- ALLEN보다 적은 급여를 받는 직원들의 사번, 이름, 급여를 검색.
SELECT empno, ename, sal
FROM EMP
WHERE 
    sal < (
    SELECT sal
    FROM emp
    WHERE ename = 'ALLEN'
    )
ORDER BY empno;

-- ALLEN과 같은 직무를 갖는 직원들의 사번, 이름, 부서번호, 직무, 급여를 검색
SELECT empno, ename, deptno, job, sal
FROM emp
WHERE job = (
    SELECT job
    FROM emp
    WHERE ename = 'ALLEN'
    )
ORDER BY empno;

-- SALESMAN의 급여 최댓값보다 더 많은 급여를 받는 직원들의 이름, 급여, 직무를 검색
SELECT ename, sal, job
FROM emp
WHERE sal > (
    SELECT max(sal)
    FROM emp
    WHERE job = 'SALESMAN'
    );

-- WARD의 연봉보다 더 많은 연봉을 받는 직원들의 이름, 급여, 수당, 연봉을 검색.
-- 연봉 = SAL * 12 + comm. comm(수당)이 null인 경우는 0으로 계산
SELECT ename, sal, comm, (sal * 12 + NVL(comm, 0)) AS "ANNUAL_SALARY"
FROM emp
WHERE sal * 12 + NVL(comm, 0) > (
    SELECT (sal * 12 + NVL(comm, 0)) AS "연봉"
    FROM emp
    WHERE ename = 'WARD'
    )
    
ORDER BY "ANNUAL_SALARY" DESC;


-- SCOTT과 같은 급여를  받는 직원들의 이름과 급여를 검색.
SELECT ename, sal
FROM emp
WHERE sal = (
    SELECT sal FROM emp WHERE ename = 'SCOTT'
    )
ORDER BY ename;

-- 위 결과에서 SCOTT은 제외하고 검색.
SELECT ename, sal
FROM emp
WHERE sal = (
    SELECT sal FROM emp WHERE ename = 'SCOTT'
    ) AND
    ename != 'SCOTT'
ORDER BY ename;

-- ALLEN보다 늦게 입사한 직원들의 이름, 입사날짜를 최근입사일부터 출력.
SELECT ename, TO_CHAR(hiredate, 'YY/MM/DD HH:mi:ss')
FROM emp
WHERE TO_CHAR(hiredate, 'YY/MM/DD HH:mi:ss') > TO_CHAR((
    SELECT hiredate FROM emp WHERE ename = 'ALLEN'
    ), 'YY/MM/DD HH:mi:ss')
ORDER BY hiredate DESC;

-- 매니저가 KING인 직원들의 사번, 이름, 매니저 사번을 검색.
SELECT empno, ename, mgr
FROM emp
WHERE mgr = (
    SELECT empno FROM emp WHERE ename = 'KING'
    );

-- ACCOUNTING 부서에 일하는 직원들의 이름, 급여, 부서번호를 검색.
SELECT ename, sal, deptno
FROM emp
WHERE empno = (SELECT empno FROM DEPT WHERE dname = 'ACCOUNTING');


-- CHICAGO에서 근무하는 직원들의 이름, 급여, 부서 번호를 검색.
SELECT ename, sal, deptno
FROM emp
WHERE deptno = (SELECT deptno FROM dept WHERE loc = 'CHICAGO');

SELECT  ename, TO_CHAR(hiredate, 'RRRR-MM-DD')
FROM emp
where ename = 'ALLEN';

SELECT *
FROM NLS_SESSION_PARAMETERS
WHERE PARAMETERS = 'NLS_DATE_FORMAT';


-- 단일행 서브쿼리: 서브쿼리의 결과 행이 1개 이하인 경우.
-- 단일행 서브쿼리는 단순 비교(=, !=, >, >=, <, <=)를 할 수 있음.
-- 다중행 서브쿼리: 서브쿼리의 결과 행이 2개 이상인 경우.
-- 다중행 서브쿼리는 단순비교를 할 수 없음!
-- 다중행 서브쿼리에서는 in, any, all과 같은 키워드를 함께 사용.

-- 각 부서에서 급여를 가장 많이 받는 직원의 레코드(모든 컬럼) 검색
SELECT *
FROM emp
WHERE (deptno, sal) in (
    SELECT deptno, max(sal)
    FROM emp
    GROUP BY deptno
);

-- 각 부서에서 급여를 가장 적게 받는 직원의 레코드(모든 컬럼) 검색
SELECT
    *
FROM
    emp
WHERE
    (deptno, sal) in (
    SELECT deptno, MIN(sal)
    FROM emp
    GROUP BY deptno
    )
ORDER BY deptno;


-- 다중행 서브쿼리에서 any vs all:
-- (1) any: 여러개 중에서 적어도 하나.
-- (2) all: 여러개 모두(전부)
SELECT sal
FROM emp
WHERE sal < all (
    SELECT sal FROM emp WHERE deptno = 10
    );



