/*
	GROUP BY
	- 그룹 기준을 제시할 수 있는 구문
    - 여러 개의 값들을 하나의 그룹으로 묶어서 처리할 목적으로 사용
*/
-- 각 부서별 조회
SELECT 
	DEPT_CODE, 
    COUNT(*) "사원 수",
    FORMAT(SUM(SALARY), 0) "총 급여",
    FORMAT(AVG(SALARY), 0) "평균 급여",
    MIN(SALARY) "최저 급여",
    MAX(SALARY) "최고 급여"
FROM EMPLOYEE
GROUP BY DEPT_CODE;

-- 직급코드(JOB_CODE)별 기준 사원 수 조회
SELECT JOB_CODE, COUNT(*) '사원수'
FROM EMPLOYEE
GROUP BY JOB_CODE;

-- 성별(남자/여자) 별 사원수 조회
SELECT 
 	IF(SUBSTR(EMP_NO, 8, 1) = 1, "남자", "여자") 성별,
    COUNT(*) "성별 별 사원수"
FROM EMPLOYEE
GROUP BY 성별;

/*
	HAVING
    - 그룹에 대한 조건을 제시할 때 사용하는 구문
    
    5 SELECT	* | 조회하고자 하는 컬럼명 AS 별칭 | 함수
    1 FROM		조회하고자 하는 테이블명
    2 WHERE		조건식 (연산자들 가지고 기술)
    3 GROUP BY	그룹 기준에 해당하는 컬럼명 | 함수
    4 HAVING		조건식 (그룹 함수를 가지고 기술)
    6 ORDER BY	컬럼명 | 컬럼순번 | 별칭 [ASC | DESC];
    7 LIMIT
*/
-- 부서별 평균 급여가 300만원 이상인 부서의 평균 급여 조회
SELECT DEPT_CODE, FORMAT(AVG(SALARY),0) 평균급여
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING AVG(SALARY) >= 3000000;

-- 직급별 총 급여의 합이 1000만원 이상인 직급만 조회
SELECT JOB_CODE, SUM(SALARY) 급여의합
FROM EMPLOYEE
GROUP BY JOB_CODE
HAVING 급여의합 >= 10000000;

-- 부서별 보너스를 받는 사원이 없는 부서만 조회
SELECT DEPT_CODE
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING COUNT(BONUS) = 0;

-- 부서별 보너스를 받는 사원들 수 조회
SELECT DEPT_CODE, COUNT(BONUS) "보너스 받는 사원 수"
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING COUNT(BONUS) != 0;

SELECT DEPT_CODE, COUNT(*) "보너스 받는 사원 수" -- 보너스가 NULL이 아닌 경우
FROM EMPLOYEE
WHERE BONUS IS NOT NULL
GROUP BY DEPT_CODE;

/*
	ROLLUP|CUBE(컬럼1,컬럼2) (CUBE는 MYSQL X) - 실제 볼일은 없는 집계 함수
    - 그룹별 산출한 결과 값의 중간 집계를 계산 해주는 함수
    - ROLLUP : 컬럼1을 가지고 다시 중간집계를 내는 함수
    - CUBE : 컬럼1을 가지고 중간집계도 내고, 컬럼2를 가지고도 중간집계를 냄
    
    MYSQL에서의 ROLLUP
    컬럼1, 컬럼2 WITH ROLLUP
    
    GROUPING : ROLLUP이나 CUBE에 의해 산출된 값이 해당 컬럼의 집합의 산출물이면 0, 아니면 1
    -> 집계된 값인지, 아닌지 정도만 구분
    
    SQLD 자격증 시험에서 꼭 이상하게 나오기도 하지만... 실제 쓰이는 일은 없음
    실습문제에서도 없음
*/
-- 부서별 직급별 급여 합 조회
SELECT DEPT_CODE, JOB_CODE, SUM(SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE, JOB_CODE WITH ROLLUP;

/*
	집합 연산자
    - 여러 개의 쿼리문을 하나의 쿼리문으로 만드는 연산자
    - 여러 개의 쿼리문에서 조회하려 하는 컬럼의 개수와 이름이 같아야 사용할 수 있다.
    
    주의! ORDER BY 절은 쓰려면 마지막에만 기술 가능
    
    UNION (합집합) : 두 쿼리문을 수행한 결과값을 하나로 합쳐서 추출 (중복되는 행 제거)
    UNION ALL (합집합) : 두 쿼리문을 수행한 결과값을 하나로 합쳐서 추출 (중복되는 행 제거 X)
    INTERSECT (교집합) : 두 쿼리문을 수행한 결과값에 중복된 결과값만 추출 (MYSQL X)
    MINUS (차집합) : 선행 쿼리문의 결과값에서 후행 쿼리문의 결과값을 뺀 나머지 결과값만 추출(MYSQL X)
		--> INTERSECT, MINUS : WHERE절에서 AND 연산자를 사용해서 처리 가능
*/
-- 1. UNION
-- (1) 부서 코드가 D5인 사원들의 사번, 사원명, 부서코드, 급여 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'
UNION
-- (2) 급여가 300만원 초과인 사원들의 사번, 사원명, 부서코드, 급여 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY > 3000000
ORDER BY EMP_ID;

-- 부서 코드가 D5이거나 급여가 300만원 초과인 사원들의 사번, 사원명, 부서코드, 급여 조회
-- 위 쿼리문 대신 WHERE 절에서 OR 연산자를 사용해서 처리 가능
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'
	OR SALARY > 3000000;

-- 2. UNION ALL
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'
UNION ALL
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY > 3000000
ORDER BY EMP_ID;






