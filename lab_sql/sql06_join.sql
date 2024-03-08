/*
join: 2개 이상의 테이블에서 검색하는 것.
문법:
(1) ANSI 표준 문법:
    select 칼럼, ...
    from 테이블1 join종류 테이블2 on join조건;
(2) Oracle문법:
    select 칼럼, ...
    from 테이블1, 테이블2, ...
    where join조건;

join 종류:
(1) inner join
(2) outer join
    - left outer join
    - right outer join
    - full outer join
*/

-- inner join과 outer join의 차이점을 보기 위해서.
insert into emp (empno, ename, sal, deptno)
values (1004, '오쌤', 3500, 50);

commit;

-- 사번, 이름, 부서번호, 부서이름을 검색
-- ANSI 표준 문법. inner join인 경우에 inner는 생략 가능
select emp.empno, emp.ename, emp.deptno, dept.dname
from emp
    join dept on emp.deptno = dept.deptno;

-- Oracle 문법:
select emp.empno, emp.ename, emp.deptno, dept.dname
from emp, dept
where emp.deptno = dept.deptno;

-- left outer join. outer는 생략 가능
select e.empno, e.ename, e.deptno, d.dname
from emp e
    left join dept d on e.deptno = d.deptno;

-- Oracle에서 left outer join:
select e.empno, e.ename, e.deptno, d.dname
from emp e, dept d
where e.deptno = d.deptno(+);

-- right outer join. outer는 생략 가능.
select e.empno, e.ename, d.deptno, d.dname
from emp e
    right join dept d on e.deptno= d.deptno;

-- Oracle에서 right outer join:
select e.empno, e.ename, d.deptno, d.dname
from emp e, dept d
where e.deptno(+) = d.deptno;

-- full outer join:
select e.empno, e.ename, e.deptno, d.deptno, d.dname
from emp e
    full join dept d on e.deptno = d.deptno;

-- Oracle은 full outer join 문법을 제공하지 않음.
-- left outer join 결과와 right outer join 결과를 합집합(union)할 수 있음
select e.empno, e.ename, e.deptno, d.deptno, d.dname
from emp e, dept d
where e.deptno = d.deptno(+)  -- left outer join
union  -- 합집합
    select e.empno, e.ename, e.deptno, d.deptno, d.dname
    from emp e, dept d
    where e.deptno(+) = d.deptno;  -- right outer join


-- EQUI-JOIN: join의 조건식이 = 연산자를 사용해서 만들어진 경우.
-- NON-EQUI JOIN: join의 조건식이 부등호를 사용해서 만들어진 경우.
-- 사번, 이름, 급여, 급여 등급을 검색
SELECT e.empno, e.ename, e.sal, s.grade
FROM emp e
    JOIN salgrade s on e.sal >= s.losal and e.sal <= s.hisal;

-- 또 다른 풀이
select e.empno, e.ename, e.sal, s.grade
from emp e
    join salgrade s on e.sal between s.losal and s.hisal;

-- Oracle
select e.empno, e.ename, e.sal, s.grade
from emp e, salgrade s
where e.sal between s.losal and s.hisal;


-- self- join: 같은 테이블에서 join하는 것.
-- 사번, 이름, 매니저 사번, 매니저 이름을 검색:
select e1.empno as "사번", e1.ename as "이름", e2.mgr as "매니저 사번", e2.ename as "매니저 이름"
from emp e1
    right join emp e2 on e1.mgr = e2.empno;



-- ex1. 직원 이름, 직원 근무 도시를 검색. 근무 도시 오름차순 정렬.
select e.ename, d.loc
from emp e
    join dept d ON e.deptno = d.deptno;

select e.ename, d.loc
from emp e, dept d
where e.deptno = d.deptno;
-- ex2. 직원 이름, 매니저 이름, 급여, 급여 등급을 검색.
--   정렬순서: (1)매니저, (2)급여 등급
select e1.ename, e2.ename as "manager_name", e1.sal, s.grade
from emp e1
    join emp e2 on e1.mgr = e2.empno
    join salgrade s on e1.sal >= s.losal and e1.sal <= s.hisal
order by e2.empno, s.grade;

select e1.ename, e2.ename as "manager_name", e1.sal, s.grade
from emp e1, emp e2, salgrade s
where e1.mgr = e2.empno AND e1.sal between s.losal and s.hisal
order by e2.empno, s.grade;

-- ex3. 직원 이름, 부서 이름, 급여, 급여 등급을 검색.
--   정렬 순서: (1)부서 이름, (2)급여 등급
select e.ename, d.dname, e.sal, s.grade
from emp e
    join dept d on e.deptno = d.deptno
    join salgrade s on e.sal BETWEEN s.losal and s.hisal
order by d.dname, s.grade;


-- ex4. 부서 이름, 부서 위치, 부서의 직원수를 검색. 부서 번호 오름 차순.
select d.dname, d.loc, count(e.empno)
from dept d
    join emp e on d.deptno = e.deptno
GROUP BY d.deptno, d.dname, d.loc
order by d.deptno;


-- ex5. 부서 번호, 부서 이름, 부서 직원수, 부서의 급여 최솟값, 
-- 부서의 급여 최댓값 검색. 부서 번호 오름 차순.
select d.deptno, d.dname, count(*), min(e.sal), max(e.sal)
from dept d
    join emp e on d.deptno = e.deptno
group by d.deptno, d.dname
order by deptno;

-- ex6. 부서 번호, 부서 이름, 사번, 이름, 매니저 사번, 매니저 이름, 
-- 급여, 급여 등급을 검색. 급여가 3000 이상인 직원들만 검색.
-- 정렬 순서: (1) 부서 번호, (2) 사번 오름차순.
select d.deptno, d.dname, e1.empno as "사번", e1.ename as "사원 이름",
    e2.empno as "매니저 사번", e2.ename as "매니저 이름", s.grade
from dept d
    right join emp e1 on d.deptno = e1.deptno
    left join emp e2 on e1.mgr = e2.empno
    join salgrade  s on e1.sal between s.losal and s.hisal
where e1.sal >= 3000
order by d.deptno, e1.empno;

