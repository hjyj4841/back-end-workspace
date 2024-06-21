/*
	JOIN
    - 두 개 이상의 테이블에서 데이터를 조회하고자 할 때 사용하는 구문
    - 조회 결과는 하나의 결과물(RESULT SET)으로 나옴
    - 관계형 데이터베이스는 최소한의 데이터로 각각의 테이블에 담고 있음
		(중복을 최소화하기 위해 최대한 쪼개서 관리)
        부서 데이터는 부서 테이블, 사원에 대한 데이터는 사원 테이블, 직급 테이블 등...
        
        만약 어떤 사원이 어떤 부서에 속해있는지 부서명과 같이 조회하고 싶다면?
        만약 어떤 사원이 어떤 직급인지 직급명과 같이 조회하고 싶다면?
        
        => 즉, 관계형 데이터베이스에서 SQL 문을 이용한 테이블 간에 "관계"를 맺어
			원하는 데이터만 조회하는 방법
*/
/*
	1. 내부 조인(INNER JOIN)
    - 연결시키는 컬럼의 값이 일치하는 행들만 조인되어 조회 (일치하는 값이 없는 행은 조회 X)
    
    1) WHERE 구문
    SELECT 컬럼, 컬럼, ...
    FROM 테이블1, 테이블2
    WHERE 테이블1.컬럼명 = 테이블2.컬럼명;
    
    - FROM 절에 조회하고자 하는 테이블들을(,)로 구분하여 나열
    - WHERE 절에 매칭시킬 컬럼명에 대한 조건 제시
    
    2) ANSI(미국국립표준협회: 산업표준을 제정하는 단체) 표준 구문 -> 다른 DB에서도 사용 가능
    SELECT 컬럼, 컬럼, ...
    FROM 테이블1
    [INNER] JOIN 테이블2 ON (테이블1.컬럼명 = 테이블2.컬럼명);
    
    - FROM 절에서 기준이 되는 테이블을 기술
    - JOIN 절에서 같이 조회하고자 하는 테이블을 기술 후 매칭 시킬 컬럼에 대한 조건을 기술 (ON, USING)
    - 연결에 사용하려는 컬럼명이 같은 경우 ON 구문 대신 USING(컬럼명) 구문 사용
*/
-- 1) 연결할 두 컬럼명이 다른 경우 (EMPLOYEE : DEPT_CODE - DEPARTMENT : DEPT_ID)
-- 사번, 사원명, 부서코드, 부서명 조회
-- >> ANSI 구문
SELECT EMP_ID, EMP_NAME, DEPT_CODE, DEPT_TITLE
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);

-- >> WHERE 절
SELECT EMP_ID, EMP_NAME, DEPT_CODE, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID;

-- 일치하는 값이 없는 행은 조회에서 제외된 것 확인
-- DEPT_CODE가 NULL인 사원 조회 X

-- 2) 연결할 두 컬럼명이 같은 경우 (EMPLOYEE : JOB_CODE - JOB : JOB_CODE)
-- 사번, 사원명, 직급코드, 직급명 조회
-- >> ANSI 구문
-- 두 컬럼명이 같을 때만 USING 구문 사용 가능
SELECT EMP_ID, EMP_NAME, JOB_CODE, JOB_NAME
FROM EMPLOYEE
JOIN JOB USING (JOB_CODE);

SELECT EMP_ID, EMP_NAME, E.JOB_CODE, JOB_NAME
FROM EMPLOYEE E
JOIN JOB J ON (E.JOB_CODE = J.JOB_CODE);

-- >> WHERE 절
SELECT EMP_ID, EMP_NAME, EMPLOYEE.JOB_CODE, JOB_NAME
FROM EMPLOYEE, JOB
WHERE EMPLOYEE.JOB_CODE = JOB.JOB_CODE; -- AMBIGUOUS : 애매한, 모호한 / 에러 발생!

-- 해결방법 1) 테이블명 이용
SELECT EMP_ID, EMP_NAME, EMPLOYEE.JOB_CODE, JOB_NAME
FROM EMPLOYEE, JOB
WHERE EMPLOYEE.JOB_CODE = JOB.JOB_CODE;

-- 해결방법 2) 테이블에 별칭 부여해서 이용
SELECT EMP_ID, EMP_NAME, E.JOB_CODE, JOB_NAME
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE = J.JOB_CODE;

-- 자연조인(NATURAL JOIN) : 각 테이블마다 동일한 컬럼이 한 개만 존재할 경우
-- 주의사항! 쓰지 말 것...
SELECT EMP_ID, EMP_NAME, JOB_CODE, JOB_NAME
FROM EMPLOYEE
NATURAL JOIN JOB;

-- 직급이 대리인 사원의 사번, 이름, 직급명, 급여 조회
-- >> ANSI 구문
SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE
	JOIN JOB USING (JOB_CODE)
WHERE JOB_NAME = "대리";

-- >> WHERE 절
SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE = J.JOB_CODE
	AND JOB_NAME = "대리";

-- 실습문제 --
-- 1. 부서가 인사관리부인 사원들의 사번, 이름, 보너스 조회(EMPLOYEE, DEPARTMENT)
SELECT EMP_ID, EMP_NAME, BONUS
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE DEPT_TITLE = '인사관리부';

SELECT EMP_ID, EMP_NAME, BONUS
FROM EMPLOYEE, DEPARTMENT
WHERE EMPLOYEE.DEPT_CODE = DEPARTMENT.DEPT_ID
	AND DEPT_TITLE = '인사관리부';

-- 2. 전체 부서의 부서코드, 부서명, 지역코드, 지역명 조회(DEPARTMENT, LOCATION)
SELECT DEPT_ID, DEPT_TITLE, NATIONAL_CODE, LOCAL_NAME
FROM DEPARTMENT
JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE);


SELECT DEPT_ID, DEPT_TITLE, NATIONAL_CODE, NATIONAL_NAME
FROM DEPARTMENT
JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE)
JOIN NATIONAL USING (NATIONAL_CODE);

-- 3. 보너스를 받는 사원들의 사번, 사원명, 보너스, 부서명 조회(EMPLOYEE, DEPARTMENT)
SELECT EMP_ID, EMP_NAME, BONUS, DEPT_TITLE
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE BONUS IS NOT NULL;

-- 4. 부서가 총무부가 아닌 사원들의 사원명, 급여 조회(EMPLOYEE, DEPARTMENT)
SELECT EMP_NAME, SALARY
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE DEPT_TITLE != '총무부';

/*
	2. 외부 조인(OUTER JOIN) : MYSQL은 ANSI 구문만 가능
    - 두 테이블 간의 JOIN 시 일치하지 않는 행도 포함시켜서 조회가 가능하다.
    - 단, 반드시 기준이 되는 테이블(컬럼)을 지정해야 한다. (LEFT, RIGHT, FULL)
*/
-- 사원명, 부서명, 급여, 연봉(급여*12) 조회
SELECT EMP_NAME, DEPT_TITLE, SALARY, SALARY*12
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);
-- > 부서 배치가 안된 사원 2명에 대한 정보 조회 X
-- > 부서에 배정된 사원이 없는 부서도 정보 조회 X

-- 1) LEFT JOIN : 두 테이블 중 왼쪽에 기술된 테이블을 기준으로 JOIN
SELECT EMP_NAME, DEPT_TITLE, SALARY, SALARY*12
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);

-- 2) RIGHT JOIN : 두 테이블 중 오른쪽에 기술된 테이블을 기준으로 JOIN
SELECT EMP_NAME, DEPT_TITLE, SALARY, SALARY*12
FROM EMPLOYEE
RIGHT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);

-- 3) FULL JOIN : 두 테이블이 가진 모든 행을 조회할 수 있음 - MYSQL X

/*
	3. 비등가 조인 (NON EQUAL JOIN)
    - 매칭시킬 컬럼에 대한 조건 작성시 '=()'를 사용하지 않는 조인문
    - 값의 범위에 포함되는 행동을 연결하는 방식
    - ANSI 구문으로는 JOIN ON만 사용 가능 (USING 사용 불가)
*/
SELECT * FROM EMPLOYEE; -- EMP_NAME, SALARY 
SELECT * FROM SAL_GRADE; -- 급여 등급 테이블 : SAL_LEVEL, MIN_SAL, MAX_SAL -> SALARY

-- 사원명, 급여, 급여 레벨 조회
SELECT EMP_NAME, SALARY, SAL_LEVEL
FROM EMPLOYEE
JOIN SAL_GRADE ON (SALARY BETWEEN MIN_SAL AND MAX_SAL);

SELECT EMP_NAME, SALARY, SAL_LEVEL
FROM EMPLOYEE, SAL_GRADE
WHERE SALARY BETWEEN MIN_SAL AND MAX_SAL;

/*
	4. 자체 조인(SELF JOIN)
    - 같은 테이블을 다시 한번 조인하는 경우 (자기 자신과 조인)
*/
SELECT * FROM EMPLOYEE;

-- 전체 사원의 사원사번(EMP_ID), 사원명(EMP_NAME), 사원부서코드(DEPT_CODE), 사수사번(MANAGER_ID)
-- 		사수사번(EMP_ID), 사수명(EMP_NAME), 사수부서코드(DEPT_CODE) 조회
SELECT 
	E.EMP_ID 사원사번, E.EMP_NAME 사원명, 
    E.DEPT_CODE 사원부서코드, E.MANAGER_ID 사수사번,
    M.EMP_ID 사수사번, M.EMP_NAME 사수이름, M.DEPT_CODE 사수부서코드
FROM EMPLOYEE E
LEFT JOIN EMPLOYEE M ON (M.EMP_ID = E.MANAGER_ID);

/*
	5. 카테시안곱(CARTESIAN PRODUCT) / 교차 조인 (CROSS JOIN)
    - 조인되는 모든 테이블의 각 행들이 서로서로 모두 매핑된 데이터가 검색된다. (곱집합)
    - 두 테이블의 행들이 모두 곱해진 행들의 조합이 출력 -> 방대한 데이터 출력 -> 과부하 위험
*/
-- 사원명, 부서명 조회 (EMPLOYEE - EMP_NAME, DEPARTMENT - DEPT_TITLE)
-- >> WHERE 구문
SELECT EMP_NAME, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT;

-- >> ANSI 구문
SELECT EMP_NAME, DEPT_TITLE
FROM EMPLOYEE
CROSS JOIN DEPARTMENT;

/*
	6. 다중 JOIN
    - 여러 개의 테이블을 조인하는 경우
*/
-- 사번, 사원명, 부서명, 직급명 조회
SELECT * FROM EMPLOYEE; -- EMP_ID, EMP_NAME, 	DEPT_CODE, 	JOB_CODE <-- FOREIGN KEY (외래키)
SELECT * FROM DEPARTMENT; -- DEPT_TITLE, 		DEPT_ID 			 <-- PRIMARY KEY (주요키)
SELECT * FROM JOB; -- JOB_NAME, 							JOB_CODE <-- PRIMARY KEY (주요키)

SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME
FROM EMPLOYEE
	LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
	JOIN JOB USING (JOB_CODE);

-- 실습 문제 --
-- 1. 직급이 대리면서 ASIA 지역에서 근무하는 직원들의 사번, 직원명, 직급명, 부서명, 근무지역, 급여 조회
SELECT EMP_ID, EMP_NAME, JOB_NAME, DEPT_TITLE, LOCAL_NAME, SALARY
FROM EMPLOYEE 
	JOIN DEPARTMENT ON (DEPT_ID = DEPT_CODE)
	JOIN JOB USING (JOB_CODE)
    JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE)
WHERE JOB_NAME = '대리'
	AND LOCAL_NAME LIKE 'ASIA%';

-- 2. 70년대생이면서 여자이고, 성이 전 씨인 직원들의 직원명, 주민번호, 부서명, 직급명 조회
SELECT EMP_NAME, EMP_NO, DEPT_TITLE, JOB_NAME
FROM EMPLOYEE
	LEFT JOIN DEPARTMENT ON (DEPT_ID = DEPT_CODE)
	JOIN JOB USING (JOB_CODE)
WHERE SUBSTR(EMP_NO, 1, 2) BETWEEN 70 AND 79
-- 		EMP_NO LIKE '7%'
-- 		SUBSTR(EMP_NO, 1, 1) = 7
	AND SUBSTR(EMP_NO, 8, 1) = 2
-- 		EMP_NO LIKE '7______2%'
    AND EMP_NAME LIKE '전%';

-- 3. 보너스를 받은 직원들의 직원명, 보너스, 연봉, 부서명, 근무지역 조회
-- 	부서가 없는 직원들도 나타내고 싶다면 LEFT JOIN (EMPLOYEE 테이블 왼쪽에 있을 때)
SELECT EMP_NAME, BONUS, FORMAT((SALARY+SALARY*BONUS)*12, 0) '연봉', DEPT_TITLE, LOCAL_NAME
FROM EMPLOYEE
	LEFT JOIN DEPARTMENT ON (DEPT_ID = DEPT_CODE)
    LEFT JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE)
WHERE BONUS IS NOT NULL;

-- 4. 한국과 일본에서 근무하는 직원들의 직원명, 부서명, 근무지역, 근무국가 조회
SELECT EMP_NAME, DEPT_TITLE, LOCAL_NAME, NATIONAL_NAME
FROM EMPLOYEE
	JOIN DEPARTMENT ON (DEPT_ID = DEPT_CODE)
    JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE)
    JOIN NATIONAL USING (NATIONAL_CODE)
WHERE NATIONAL_NAME IN ('한국', '일본');

-- 5. 각 부서별 평균 급여를 조회하여 부서명, 평균 급여조회
SELECT DEPT_TITLE, FORMAT(IFNULL(AVG(SALARY), 0), 0) '평균 급여'
FROM DEPARTMENT
	LEFT JOIN EMPLOYEE ON (DEPT_CODE = DEPT_ID)
GROUP BY DEPT_TITLE;

-- 6. 각 부서별 총 급여의 합이 1000만원 이상인 부서명, 급여 합 조회
SELECT DEPT_TITLE, FORMAT(SUM(SALARY), 0) '급여의 합'
FROM DEPARTMENT
	JOIN EMPLOYEE ON (DEPT_CODE = DEPT_ID)
GROUP BY DEPT_TITLE
HAVING SUM(SALARY) >= 10000000;

-- 7. 사번, 직원명, 직급명, 급여 등급, 구분 조회
-- 이때 구분에 해당하는 값은 아래 참고
-- 급여 등급이 S1, S2인 경우 '고급'
-- 급여 등급이 S3, S4인 경우 '중급'
-- 급여 등급이 S5, S6인 경우 '초급'
SELECT 
	EMP_ID, EMP_NAME, 
	JOB_NAME, SAL_LEVEL,
    CASE WHEN SAL_LEVEL IN ('S1', 'S2') THEN '고급'
		WHEN SAL_LEVEL IN ('S3', 'S4') THEN '중급'
		ELSE '초급' 
	END '구분'
FROM EMPLOYEE
	JOIN JOB USING (JOB_CODE)
    JOIN SAL_GRADE ON (SALARY BETWEEN MIN_SAL AND MAX_SAL);

SELECT 
	EMP_ID, EMP_NAME, 
	JOB_NAME, SAL_LEVEL,
    IF(SAL_LEVEL IN ('S1', 'S2'), '고급', 
    IF(SAL_LEVEL IN ('S3', 'S4'), '중급', 
    '초급')) '구분'
FROM EMPLOYEE
	JOIN JOB USING (JOB_CODE)
    JOIN SAL_GRADE ON (SALARY BETWEEN MIN_SAL AND MAX_SAL);
    
-- 8. 보너스를 받지 않은 직원들 중 직급 코드가 J4 또는 J7인 직원들의 직원명, 직급명, 급여 조회 
SELECT EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE
	JOIN JOB USING (JOB_CODE)
WHERE BONUS IS NULL
	AND JOB_CODE IN ('J4', 'J7');

-- 9. 부서가 있는 직원들의 직원명, 직급명, 부서명, 근무지역 조회
-- INNER JOIN을 하는 경우 NULL이 포함되어 있는 것처럼 일치하는 걸 못찾는다면 제외
SELECT EMP_NAME, JOB_NAME, DEPT_TITLE, LOCAL_NAME
FROM EMPLOYEE
	JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
    JOIN JOB USING (JOB_CODE)
    JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE);

-- 10. 해외영업팀에 근무하는 직원들의 직원명, 직급명, 부서코드, 부서명 조회
SELECT EMP_NAME, JOB_NAME, DEPT_CODE, DEPT_TITLE
FROM EMPLOYEE
	JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
    JOIN JOB USING (JOB_CODE)
WHERE DEPT_TITLE LIKE '해외영업%';

-- 11. 이름에 '형'자가 들어있는 직원들의 사번, 직원명, 직급명 조회
SELECT EMP_ID, EMP_NAME, JOB_NAME
FROM EMPLOYEE
	JOIN JOB USING (JOB_CODE)
WHERE EMP_NAME LIKE '%형%';

-- 12. 사번, 사원명, 부서명, 직급명, 지역명, 국가명, 급여등급 조회
-- 참고로 모든 테이블 JOIN
SELECT 
	EMP_ID, EMP_NAME, 
    DEPT_TITLE, JOB_NAME, 
    LOCAL_NAME, NATIONAL_NAME, 
    SAL_LEVEL
FROM EMPLOYEE
	LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
    LEFT JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE)
	LEFT JOIN NATIONAL USING (NATIONAL_CODE)
    JOIN JOB USING (JOB_CODE)
    JOIN SAL_GRADE ON (SALARY BETWEEN MIN_SAL AND MAX_SAL);