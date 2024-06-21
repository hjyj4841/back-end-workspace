/*
	서브쿼리(SUBQUERY), 중첩쿼리
    - 하나의 SQL문 안에 포함된 또 다른 SQL문
    - 서브쿼리를 수행한 결과값이 몇 행 몇 열이냐에 따라 분류
    - 서브쿼리 종류에 따라 서브쿼리와 사용하는 연산자가 달라짐
    
    1. 단일행 서브쿼리(SINGLE ROW SUBQUERY)
    - 서브쿼리의 조회 결과값의 개수가 오로지 1개일 때 (한 행 한 열)
    - 일반 비교연산자 사용 가능 : =, !=, <>, >, <, >=, <=, ...
*/
-- 노옹철 사원과 같은 부서원들을 조회
-- 1) 노옹철 사원의 부서코드 조회
SELECT DEPT_CODE
FROM EMPLOYEE
WHERE EMP_NAME = '노옹철'; -- D9

-- 2) 부서코드가 'D9'인 사원들 조회
SELECT *
FROM EMPLOYEE
WHERE DEPT_CODE = 'D9';

-- 3) 위의 2단계를 하나의 쿼리문으로!
SELECT *
FROM EMPLOYEE
WHERE DEPT_CODE = (SELECT DEPT_CODE
					FROM EMPLOYEE
					WHERE EMP_NAME = '노옹철');

-- 전 직원의 급여보다 더 많은 급여를 받는 사원들의 사번, 사원명, 직급코드, 급여 조회
-- 1) 전 직원의 평균 급여 조회
SELECT AVG(SALARY)
FROM EMPLOYEE; -- 3047662.6087

-- 2) 3047662.6087원 보다 많이 받는 사원들의 사번, 사원명, 직급코드, 급여 조회
SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY > 3047662.6087;

-- 3) 합치기
SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY > 
	(SELECT AVG(SALARY)
	FROM EMPLOYEE);

-- 전지연 사원이 속해있는 부서원들의 사번, 직원명, 전화번호, 직급명, 부서명, 입사일 조회
-- 단, 전지연 사원은 제외
-- 1) 전지연 사원이 속해있는 부서코드 조회
SELECT DEPT_CODE
FROM EMPLOYEE
WHERE EMP_NAME = '전지연'; -- D1

-- 2) 부서코드가 D1인 직원들의 사번, 직원명, 전화번호, 직급명, 부서명, 입사일 조회
SELECT EMP_ID, EMP_NAME, PHONE, JOB_NAME, DEPT_TITLE, HIRE_DATE
FROM EMPLOYEE
	JOIN DEPARTMENT ON (DEPT_ID = DEPT_CODE)
    JOIN JOB USING (JOB_CODE)
WHERE DEPT_CODE = 'D1';

-- 3) 합치면서 전지연 사원 조회
SELECT EMP_ID, EMP_NAME, PHONE, JOB_NAME, DEPT_TITLE, HIRE_DATE
FROM EMPLOYEE
	JOIN DEPARTMENT ON (DEPT_ID = DEPT_CODE)
    JOIN JOB USING (JOB_CODE)
WHERE DEPT_CODE = (
	SELECT DEPT_CODE
	FROM EMPLOYEE
	WHERE EMP_NAME = '전지연')
    AND EMP_NAME != '전지연';

-- 부서별 급여의 합이 가장 큰 부서의 부서 코드, 급여의 합 조회
-- >> NO SUBQUERY
SELECT DEPT_CODE, SUM(SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE
ORDER BY 2 DESC
LIMIT 1;

-- >> SUBQUERY
-- 서브쿼리 특징! 쿼리 자체는 직관적!
-- 쿼리 속도 중요시! 서브쿼리 상대적으로 느림.
-- 가급적으로 서브쿼리를 사용하지 않아도 되는 거면 안 쓰고 하는 걸 추천!
SELECT DEPT_CODE, SUM(SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING SUM(SALARY) = (
	SELECT MAX(SUM_SAL)
	FROM (
		SELECT DEPT_CODE, SUM(SALARY) SUM_SAL
		FROM EMPLOYEE
		GROUP BY DEPT_CODE
	) DEPT_SUM
);

/*
	2. 다중행 서브쿼리
    - 서브쿼리의 조회 결과 값의 개수가 여러 행 일 때 (여러행 한열)
    
    IN 서브쿼리 : 여러개의 결과값 중에서 한개라도 일치하는 값이 있다면 
*/
-- 각 부서별 최고 급여를 받는 직원의 이름, 직급코드, 부서코드, 급여 조회
-- 1) 각 부서별 최고 급여
SELECT MAX(SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE; -- 최고 급여들...

-- 2) 위의 값들을 받는 직원의 이름, 직급코드, 부서코드, 급여 조회
SELECT EMP_NAME, JOB_CODE, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY IN (2890000, 3660000, 2490000, 3760000, 390000, 2550000, 8000000);

-- 3) 합치기
SELECT EMP_NAME, JOB_CODE, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY IN (
	SELECT MAX(SALARY)
	FROM EMPLOYEE
	GROUP BY DEPT_CODE
);

-- 직원에 대한 사번, 이름, 부서코드, 구분(사수/사원) 조회
-- 누군가의 사수인 사람들의 ID가 필요
SELECT DISTINCT MANAGER_ID 
FROM EMPLOYEE
WHERE MANAGER_ID IS NOT NULL;

SELECT 
	EMP_ID, EMP_NAME, DEPT_CODE, 
    IF(EMP_ID IN (
		SELECT DISTINCT MANAGER_ID 
		FROM EMPLOYEE
        WHERE MANAGER_ID IS NOT NULL
        ),
	'사수', '사원') '구분'
FROM EMPLOYEE;

/*
	컬럼 > ANY (서브쿼리) : 여러개의 결과값 중에서 "한개라도" 클 경우
    컬럼 < ANY (서브쿼리) : 여러개의 결과값 중에서 "한개라도" 작을 경우
    --> OR
*/
-- 대리 직급임에도 과장 직급들의 최소 급여보다 많이 받는 직원들의 사번, 이름, 직급, 급여 조회
SELECT SALARY
FROM EMPLOYEE
	JOIN JOB USING (JOB_CODE)
WHERE JOB_NAME = '과장'; -- 과장 SALARY

SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE
	JOIN JOB USING (JOB_CODE)
WHERE SALARY > ANY (
		SELECT SALARY
		FROM EMPLOYEE
			JOIN JOB USING (JOB_CODE)
		WHERE JOB_NAME = '과장' -- 과장 SALARY
		)
	AND JOB_NAME = '대리';
    
/*
	컬럼 > ALL (서브쿼리) : 여러개의 "모든" 결과값들 보다 클 경우
    컬럼 < ALL (서브쿼리) : 여러개의 "모든" 결과값들 보다 작을 경우
    --> AND
*/
-- 과장 직급임에도 차장 직급의 최대 급여보다 더 많이 받는 사원들의 사번, 이름, 직급, 급여 조회
SELECT SALARY
FROM EMPLOYEE
	JOIN JOB USING (JOB_CODE)
WHERE JOB_NAME = '차장';

SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE
	JOIN JOB USING (JOB_CODE)
WHERE JOB_NAME = '과장'
	AND SALARY > ALL (
		SELECT SALARY
		FROM EMPLOYEE
			JOIN JOB USING (JOB_CODE)
		WHERE JOB_NAME = '차장');

/*
	3. 다중열 서브쿼리
    - 서브쿼리의 조회 결과값이 한 행이지만 컬럼이 여러개일 때 (한 행 여러 열)
*/
-- 하이유 사원과 같은 부서 코드, 같은 직급 코드에 해당하는 사원들의 사원명, 부서코드, 직급코드 조회
SELECT DEPT_CODE
FROM EMPLOYEE
WHERE EMP_NAME = '하이유';

SELECT JOB_CODE
FROM EMPLOYEE
WHERE EMP_NAME = '하이유';

-- 다중열 서브쿼리 X
SELECT EMP_NAME, DEPT_CODE, JOB_CODE
FROM EMPLOYEE
WHERE DEPT_CODE = (
		SELECT DEPT_CODE
		FROM EMPLOYEE
		WHERE EMP_NAME = '하이유')
	AND JOB_CODE = (
		SELECT JOB_CODE
		FROM EMPLOYEE
		WHERE EMP_NAME = '하이유');

-- 다중열 서브쿼리 O
SELECT DEPT_CODE, JOB_CODE
FROM EMPLOYEE
WHERE EMP_NAME = '하이유';

SELECT EMP_NAME, DEPT_CODE, JOB_CODE
FROM EMPLOYEE
WHERE (DEPT_CODE, JOB_CODE) = ('D5', 'J5');

SELECT EMP_NAME, DEPT_CODE, JOB_CODE
FROM EMPLOYEE
WHERE (DEPT_CODE, JOB_CODE) = (
		SELECT DEPT_CODE, JOB_CODE
		FROM EMPLOYEE
		WHERE EMP_NAME = '하이유');

-- 박나라 사원과 직급 코드가 일치하면서 같은 사수를 가지고 있는
-- 사원의 사번, 이름, 직급코드, 사수사번 조회
SELECT JOB_CODE, MANAGER_ID
FROM EMPLOYEE
WHERE EMP_NAME = '박나라';

SELECT EMP_ID, EMP_NAME, JOB_CODE, MANAGER_ID
FROM EMPLOYEE
WHERE (JOB_CODE, MANAGER_ID) = 
		(SELECT JOB_CODE, MANAGER_ID
		FROM EMPLOYEE
		WHERE EMP_NAME = '박나라');

/*
	4. 다중행 다중열 서브쿼리
    - 서브쿼리의 조회 결과값이 여러 행, 여러 열일 경우
*/
-- 각 직급별로 최소 급여를 받는 사원들의 사번, 이름, 직급코드, 급여 조회
-- 각 직급별로 최소 급여
SELECT JOB_CODE, MIN(SALARY)
FROM EMPLOYEE
GROUP BY JOB_CODE;

SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE (JOB_CODE, SALARY) IN (
		SELECT JOB_CODE, MIN(SALARY)
		FROM EMPLOYEE
		GROUP BY JOB_CODE);

-- 각 부서별 최대 급여를 받는 사원들의 사번, 이름, 부서코드, 급여 조회 (부서없음 명시)
SELECT DEPT_CODE, MAX(SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE;

SELECT EMP_ID, EMP_NAME, IFNULL(DEPT_CODE, '부서없음'), SALARY
FROM EMPLOYEE
WHERE (IFNULL(DEPT_CODE, '부서없음'), SALARY) IN (
		SELECT IFNULL(DEPT_CODE, '부서없음'), MAX(SALARY)
		FROM EMPLOYEE
		GROUP BY DEPT_CODE);
