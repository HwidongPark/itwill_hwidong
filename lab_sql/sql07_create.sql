/*
SQL 종류:
1. DQL (Data Query Language): select 문장.
2. DML(Data Manipulation Language): insert, update, delete 문장.
3. DDL(Data Definition Language): create, alter, truncate, drop 문장.
4. TCL(Transaction Control Language): commit, rollback
    - commit: 데이터 베이스의 변경 내용을 영구 저장.
    - rollback: 직전의 commit 상태로 되돌아가기.


테이블 생성:
create table 테이블 이름 (
    컬럼이름 데이터타입 [제약조건 기본값],
    컬럼2이름 데이터타입 [제약조건 기본값],
    ...
);

데이터 타입에서 사용되는 키워드(예약어)는 데이터베이스 종류에 따라서 다름.
오라클 데이터 타입: number, varchar2, date, timestamp, clob, blob, ...
clob는 character large object로 varchar2로 담을 수 없는 문자열이 와야할 경우 사용
blob는 binary large object로 binary데이터가 들어가는데 이미지, 동영상 등 파일들도 저장할 수 있음.
그러나 데이터베이스에 파일을 저장하는 것은 좋은 생각이 아님.
*/


/*
테이블 이름: students
컬럼:
    - stuno: 학생 번호. 숫자(6자리 정수)
    - stuname: 학생 이름. 문자열(10글자)
    - birthday: 학생 생일. 날짜.
*/
create table Students (
     stuno number(6),
     stuname varchar(10 char),
     birthday date
);

desc students;  -- describe
describe students;

/*
테이블에 행 추가:
insert into 테이블 이름 (컬럼, ...)
values (값, ... );
*/
insert into STUDENTS
values (1, '홍길동', '00/01/01');


insert into students (stuno, stuname, birthday)
values (1, '홍길동', '2000/01/01');


select * from students;

alter table student rename to students;

insert into students (stuno, stuname)
values (3, 'Gildong');

commit;    -- insert한 내용들을 데이터베이스에 영구적으로 저장

insert into students (stuname, stuno) values ('gildong', 10);

select * from students;

insert into students (stuname, stuno) values (30, 10);

-- 테이블 생성할 때 기본값 설정하기:
create table ex_users(
    no number(4),
    userid varchar2(20 byte),
    password varchar2(100 byte),
    age number(3) default 0;
    created_date date default sysdate -- 현재 날짜라는 뜻
);

create table ex_users (
    no number(4),
    userid varchar2(20 byte),
    password varchar2(100 byte),
    age number(3) default 0,
    created_date date default sysdate
);

insert into ex_users (no, userid, password)
values (1, 'admin', '0000');
--> insert할 때 기본값이 설정된 칼럼들은 insert하는 값이 없으면 설정된 기본값이
-- 자동으로 insert됨

insert into ex_users(no, userid, age)
values (2, 'guest', 10);
--> password 칼럼은 기본값이 설정되어 있지 않기 때문에 null.


-- 테이블을 생성할 때 제약조건 만들기
-- (1) primary key(고유키)
-- (2) not null
-- (3) unique
-- (4) check
-- (5) foreign key(외래키)

-- 테이블을 생성할 때 제약조건 만들기: 제약조건 이름을 설정하지 않음.
create table ex1 (
    id number(2) primary key,
    name varchar2(10 char) not null,
    phone varchar2(13 char) unique,
    age number(3) check (age >= 0),
    memo varchar2(1000 char)
);

insert into ex1
values (1, '오쌤', '01012345678', 16, '안녕하세요');

insert into ex1 (id, name)
values (1, '홍길동');
--> 고유키(PK) 제약조건 위배. PK = Not Null + Unique
-- PK는 유일해야 함

insert into ex1 (name) values ('홍길동');
--> PK 제약조건 위배: PK는 null이 될 수 없음.

insert into ex1 (id, phone) value (2, '010-0000-0000');
--> not null 제약조건 위배

insert into ex1(id, name, phone) values (2, '홍길동', '010-1234-5678');
insert into ex1(id, name, phone) values (2, '홍길동', '01012345678');
--> Unique 제약조건 위배: phone 칼럼은 unique.

insert into ex1 (id, name, age) values (2, 홍길동, -1);
--> check 제약조건 위배: age >= 0

select * from ex1;
commit;

-- 테이블을 생성할때 제약조건 만들기: 제약조건 이름을 설정.
create table ex2 (
    id number(4)
        constraint ex2_id_pk primary key,
    name varchar2(10 char)
        constraint ex2_name_not_nul not null,
    phone varchar2(13 char)
        constraint ex2_phone_uq unique,
    gender varchar(1 char) constraint ex2_gender_chck check(gender in ('M', 'F'))
);

insert into ex2 values (1, '홍길동', '010-0000-0000','M');

insert into ex2 (id, name) values (1, '홍길동'); --> PK 위배
insert into ex2 (id) values (2); --> NN 위배
insert into ex2 (id, name, gender) values (2, '홍길동', 'm'); -- check 위배


select * from ex2;

-- 테이블을 생성할 때 제약조건 만들기: 컬럼 정의 따로, 제약조건 따로.
create table ex3 (
    -- 컬럼 정위(이름 & 데이터 타입)
    id number(4),
    name varchar2(10 char),
    phone varchar2(13 char),
    gender varchar2(1 char),
    
    -- 제약조건 정의(제약조건의 이름 & 내용)                                                                                                                                                                
    constraint ex3_id_pk primary key (id),
    constraint ex3_name_nn check (name is not null),  -- 주의 이 경우 (not null)사용 불가. 대신 check사용해야함
    constraint ex3_phone_uq unique (phone),
    constraint ex_gender_chck check(gender in ('M', 'F'))
);

-- foreign key(외래키): 다른 테이블이 고유키(primary key)를 참조하는 제약조건.
-- 데이터를 insert할 때 다른 테이블의 PK에 없는 값이 insert되지 않도록 하기뒤해서 foreign key를 만듦
-- 테이블을 생성할 때 FK를 설정하려면, PK가 있는 다른 테이블이 먼저 생성돼 있어야 한다.

create table ex_dept (
    deptno number(2)
        constraint ex_dept_deptno_pk primary key,
        dname varchar2(100 char) not null
);

create table ex_emp1 (
    empno number(4)
        constraint ex_emp1_empno_pk primary key,
    ename varchar2(100 char) not null,
    deptno number(2)
        constraint ex_emp1_deptno_fk references ex_dept (deptno)
);

insert into ex_dept values(10, '아이티윌');
insert into ex_emp1 values (1000, '오쎔', 10);  -- FK 존재
insert into ex_emp1 values (1001, '홍길동', 20); -- FK가 위배

select * from ex_emp1;
select * from ex_dept;
commit;

create table ex_emp2 (
    -- 컬럼 선언(이름 & 데이터 타입)
    empno number(4),
    ename varchar2(100 char) not null,
    deptno number(2),
    
    -- 제약조건 선언
    constraint ex_emp2_empno_pk primary key (empno),
    constraint ex_emp2_deptno_fk foreign key (deptno) references ex_dept (deptno)
    
);
