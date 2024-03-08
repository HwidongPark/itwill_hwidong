--  *는 전부를 의미
-- 아래 문장의 의미는 emp 테이블에서 모든 "컬럼"의 내용을 검색.
select * from emp;


-- SQL 문장에서 명령어(키워드), 테이블 이름, 컬럼 이름은 대/소문자를 구분하지 않음.
-- 테이블에 저장된 문자열 값들은 대/소문자를 구분함.
SELECT * FROM EMP;
SELECT * FROM emp;
select * from EMP;

/*
DDL(Data Definition Language): CREATE, DROP, ALTER, TRUNCATE
DQL(Data Query Language): SELECT
DML(Data Manipulation Language): INSERT, UPDATE, DELETE
TCL(Transaction Control Language): COMMIT, ROLLBACK

PL/SQL: Procedural Language extension to SQL
SQL을 이용한 절차적 프로그래밍 언어
변수선언과 사용, 제어문(IF, CASE, LOOP), 함수, 프로시저, 예외처리 등
*/


/*
 블록 주석(block comment)
*/

-- inline comment
-- SQL 문장은 세미콜론(;)으로 끝남
-- Ctrl + Enter :
-- (1) 현재 커서가 있는 위치의 한 문장을 실행.
-- (2) 드래그(drag)로 선택된 문장을 실행.
-- F5: 스크립트(파일) 전체를 실행.