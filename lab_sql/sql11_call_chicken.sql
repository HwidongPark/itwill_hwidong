create table CALL_CHICKEN (
    call_date date,
    call_day varchar2(1 char),
    district varchar2(4 char),
    ages varchar2(5 char),
    gender varchar2(1 char),
    calls number(4)
);

select * from call_chicken;

ALTER TABLE CALL_CHICKEN RENAME Column call to calls;

-- 3. 통화건수의 최솟값, 최댓값을 찾으세요
SELECT max(calls), min(calls)
FROM call_chicken;


-- 4. 통화건수가 최솟값이거나 최댓값인 레코드(행 전체)를 출력하세요.
SELECT *
FROM call_chicken
WHERE calls = (
    SELECT MIN(calls)
    FROM call_chicken
    ) OR
    calls = (
    SELECT MAX(calls)
    FROM call_chicken
    )
ORDER BY calls DESC;

-- 5. 평균적으로 어떤 요일에서 치킨 주문이 많을까요?
SELECT call_day, ROUND(AVG(calls), 2) AS "avg_calls"
FROM call_chicken
GROUP BY call_day
HAVING AVG(calls) = (
    SELECT MAX(AVG(calls))
    FROM call_chicken
    GROUP BY call_day
);


-- 6. 평균적으로 어떤 연령대가 치킨 주문을 많이 할까요?
SELECT ages, ROUND(avg(calls), 2) AS "avg_calls"
FROM call_chicken
GROUP BY ages
HAVING AVG(calls) = (
    SELECT MAX(AVG(calls))
    FROM call_chicken
    GROUP BY ages
);

-- 7. 평균적으로 어느 지역에서 치킨 주문을 많이 할까요?
SELECT district, ROUND(AVG(calls), 2) AS "avg_calls"
FROM call_chicken
GROUP BY district
HAVING AVG(calls) = (
    SELECT MAX(AVG(calls))
    FROM call_chicken
    GROUP BY district
)
ORDER BY AVG(calls) DESC;

-- 8. 치킨 주문에 성별 차이가 있을까요?
SELECT gender, ROUND(AVG(calls), 2) AS "avg_calls"
FROM call_chicken
GROUP BY gender
ORDER BY AVG(calls) DESC;

-- 9. 요일별, 연령대별 통화건수의 평균을 찾으세요.
SELECT call_day, ages, ROUND(AVG(calls), 2) AS "avg_calls"
FROM call_chicken
GROUP BY call_day, ages
ORDER BY AVG(calls) DESC;

-- 10. 구별, 성별 통화건수의 평균을 찾으세요.
SELECT district, gender, ROUND(AVG(calls), 2) AS "avg_calls"
FROM call_chicken
GROUP BY district, gender
ORDER BY AVG(calls) DESC;

-- 11. 요일별, 구별, 연령대별 통화건수의 평균을 찾으세요.
SELECT call_day, district, ages, ROUND(AVG(calls), 2) AS "avg_calls"
FROM call_chicken
GROUP BY call_day, district, ages
ORDER BY AVG(calls) DESC;

-- 3 ~ 11 문제의 출력은 통화건수 평균의 내림차순 정렬, 소숫점 2자리까지 반올림.