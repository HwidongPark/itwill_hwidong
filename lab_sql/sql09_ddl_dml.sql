/*
DDL(Data Definition Langauge): create, alter, drop, truncate
DML(Data Manipulation Language): insert, update, delete
*/

-- create table ... as select ...
-- 테이블의 모양(컬럼 이름, 데이터타입)과 데이터를 복사
-- emp 테이블의 전체 데이터를 ex_emp3 테이블을 생성하면서 복사:
create table ex_emp3
as select * from emp;

select * from ex_emp3;


-- emp 테이블의 모양(컬럼, 데이터 타입)과 똑같은 테이블 ex_emp4을 생성
create table ex_emp4
as select * from emp where empno = 0;

select * from ex_emp4;

-- truncate table: 테이블의 모든 행을 삭제하는 DDL
truncate table ex_emp3;
select * from ex_emp3;

-- drop table: 테이블을 삭제하는 DDL
drop table ex_emp4;
select * from ex_emp4; --> 오류 발생: 테이블이 존재하지 않음


-- DML
-- insert into table (col, ...) values (val, ...)
-- 1개 행을 삽입(insert)
-- insert into table (col, ...) select ...;
-- select의 결과에 따라서 여러개의 행들을 삽입(insert)
select* from ex_emp3;

insert into ex_emp3
select * from emp where deptno = 10;

select * from bonus;
-- emp table에서 comm이 null이 아닌 레코드를 bonus 테이블에 삽입:
insert into bonus 
select ename, job, sal, comm
from emp
where comm is not null;

-- 테이블 데이터 업데이트
-- update 테이블이름 set 변수=값, ... [where 조건식];
select * from emp;

-- 사번이 1004인 직원의 급여를 6000으로 변경
update emp set sal= 6000 where empno = 1004;
update emp set sal=6000;
commit;  -- 현재 세션에서 지금까지의 트랜젝션을 DB에 영구 저장.

-- 사번이 1004인 직원의 직무를 'MANAGER', 입사 날짜는 '2023/10/24',
-- 부서번호를 40으로 업데이트:
update emp set job = 'MANAGER' where empno = 1004;
update emp set hiredate = '2023/10/24', deptno = 40 where empno = 1004;
-- 'ACCOUNTING' 부서에서 일하는 직원들의 급여를 10% 인상:
/*
update emp set sal = sal * 1.1 where deptno = 10;

SELECT *
FROM emp e
    JOIN dept d ON e.deptno = d.deptno
WHERE d.dname = 'ACCOUNTING'; 생각 잘못함 아래 다시 작성
*/

/*
update (
SELECT *
FROM emp e
    JOIN dept d ON e.deptno = d.deptno
WHERE d.dname = 'ACCOUNTING'
) SET sal = sal * 1.1;
*/
-- 오쌤의 더 나은 풀이 --> 단일 subquery사용해서 풀이.
select * from emp
where deptno = (
    select deptno from dept where dname = 'ACCOUNTING'
);

update emp set sal = sal * 1.1 where deptno = (
    select deptno from dept where dname = 'ACCOUNTING'
);
        

-- salgrade가 1인 직원들이 급여를 20% 인상:
/*
UPDATE (SELECT e.*, s.*
FROM emp e
    JOIN salgrade s ON e.sal BETWEEN s.losal AND s.hisal
WHERE s.grade=1) SET sal = sal*1.2;
*/

-- 오쌤의더 나은 풀이
UPDATE emp SET sal = sal * 1.2
WHERE sal between (
    SELECT losal FROM salgrade where grade = 1
) AND (
    SELECT hisal FROM salgrade where grade = 1
);

commit;


-- delete: 테이블에서 행을 삭제하는 DML
-- delete from 테이블 [where 조건식];
select * from emp;

delete from emp; -- 테이블에서 전체 행을 삭제.

rollback; -- 최종 커밋 상태(삭제 전)으로 되돌림.

-- 사번이 1004인 직원 레코드(행)을 삭제
delete from emp where empno = 1004;

-- 1987년에 입사한 직원(들)을 삭제:
select *
from emp
where to_char(hiredate, 'YYYY') = '1987';  -- 오쌤 풀이. 날짜 -> 문자열. 연도만 뽑아서 비교

delete from emp where hiredate >= '1987/01/01' and hiredate <= '1987/12/12';

