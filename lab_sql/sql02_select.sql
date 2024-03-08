-- Alt + F10: SQL 새 워크시트 만들기.


-- SELECT 컬럼 이름, ... FROM 테이블 이름;
-- SELECT * FROM table; 테이블에서 모든 컬럼 검색.

-- emp: 직원 테이블, dept: 부서 테이블
-- 직원 테이블에서 사번(empno), 직원 이름(ename)을 검색:
SELECT empno, ename FROM emp;
SELECT ename, empno FROM emp;
-- 컬럼 이름 입력한 순으로 출력이됨. 테이블이 어떻게 생겼는지가 중요한게 아님.
SELECT * FROM dept;
SELECT deptno, dname, loc FROM dept;

-- 컬럼 이름에 별명(alias) 주기: as "별명"
-- 별명을 줄 때는 반드시 큰 따옴표("")만 사용함.
-- 별명 이외의 문자열 값을 표현할 때는 작은 따옴표('')만 사용! 자바랑 다른 점
SELECT deptno as "부서 번호", dname as "부서 이름"
FROM dept;

-- 검색할 때 원하는 컬럼에만 별명을 줄 수 있음.
SELECT deptno as "부서 번호", dname FROM dept;

-- 연결 연산자(||): 2개 이상의 컬럼을 합쳐서 하나의 컬럼으로 출력.
-- '부서 번호-부서이름' 형식의 문자열을 "부서 정보"라는 칼럼으로 출력:
SELECT deptno ||' - ' || dname as "부서 정보" FROM dept;

-- 부서 테이블을 검색해서 '... 부서는 ...에 있습니다.' 형식으로 결과 출력:
SELECT dname || '부서는 ' || loc ||' 에 있습니다.' as "문제1" FROM dept;

-- 검색 결과를 정렬해서 출력하기:
-- SELECT 컬럼 이름, ... FROM 테이블 order by 컬럼 [asc/desc];
-- asc: 오름차순 정렬(ascending order). 기본 값. asc는 생략 가능
-- desc: 내림차순 정렬(descending order).


-- 부서 테이블의 모든 컬럼을 검색, 부서 번호 내림차순으로 출력.
SELECT * FROM dept ORDER BY deptno DESC;

-- 부서 테이블의 모든 컬럼을 검색, 부서 이름 오름차순으로 출력
SELECT * FROM dept ORDER BY dname;

-- 직원 테이블에서 사번, 이름, 급여를 검색, 사번 오름차순으로 출력
SELECT empno, ename, sal FROM emp order by empno;


-- 직원 테이블에서 사번, 이름, 급여를 검색, 급여 내림차순으로 출력:
SELECT empno, ename, sal FROM emp ORDER BY sal DESC;

-- 직원 테이블에서 부서 번호, 사번, 이름을 검색.
-- 정렬 순서: (1) 부서번호 오름차순, (2) 사번 오름차순
SELECT deptno, empno, ename
FROM emp
ORDER BY deptno, empno;

-- 직원 테이블에서 부서번호, 이름. 급여를 검색.
-- 정렬 순서: (1) 부서 번호 오름차순 (2) 급여 내림차순
SELECT deptno, ename, sal FROM emp ORDER BY deptno, sal DESC;


-- 중복되지 않는 결과만 출력하기:
SELECT job FROM emp;
SELECT DISTINCT job FROM emp;  -- 중복되지 않는 업무 이름들을 검색

-- (중복되지 않는) 업무 이름을 오름차순으로 정렬 출력:
SELECT job FROM emp;
SELECT DISTINCT job FROM emp
ORDER BY job;


-- 중복되지 않는 부서번호, 직무, 부서번호 오름차순 정렬
-- 둘이 묶어서 중복된 것을 제외함
 SELECT DISTINCT deptno, job
FROM EMP
ORDER BY deptno;