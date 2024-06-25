/*
	DML(DATA MANIPULATION LANGUAGE)
    - 데이터 조작 언어로 테이블에 값을 삽입(INSERT)하거나,
		수정(UPDATE)하거나, 삭제(DELETE)하는 구문
        
	INSERT
    - 테이블에 새로운 행을 추가하는 구문
    
    1) INSERT INTO 테이블명 VALUES (값, 값, ...);
		- 테이블에 모든 컬럼에 대한 값을 INSERT 하고자 할 때 사용한다.
        - 컬럼 순번을 지켜서 VALUES 값을 나열해야 한다.
	2) INSERT INTO 테이블명(컬럼명, 컬럼명, ...) VALUES(값, 값, ...);
		- 테이블의 특정 컬럼에 대한 값만 INSERT 하고자 할 때 사용한다.
        - 선택이 안된 컬럼들은 기본적으로 NULL값이 들어간다.
			(NOT NULL 조건이 걸려 있는 컬럼은 반드시 값을 입력해야 한다.
		- 기본값(DEFAULT)이 지정되어 있으면 NULL이 아닌 기본값이 들어간다.
	3) INSERT INTO 테이블명 서브쿼리;
		- VALUES 대신 서브쿼리 조회한 결과값이 통째로 INSERT 한다.
        - 서브쿼리 결과값이 INSERT 문에 지정된 컬럼 개수와 같아야 한다.
*/
-- 사용할 테이블 생성
CREATE TABLE EMP(
	EMP_ID INT PRIMARY KEY AUTO_INCREMENT,
    EMP_NAME VARCHAR(30) NOT NULL,
    DEPT_TITLE VARCHAR(30) DEFAULT '개발팀',
    HIRE_DATE DATE DEFAULT (CURRENT_DATE)
);

-- 1)
INSERT INTO EMP 
VALUES(1, '윤대훈', '서비스 개발팀', DEFAULT);

-- 모든 컬럼값을 지정하지 않아서 에러
INSERT INTO EMP
VALUES('이동엽', '개발 지원팀', DEFAULT);

-- 에러는 발생하지 않지만 데이터가 잘못 저장
INSERT INTO EMP
VALUES(2, '개발 지원팀', '이동엽', DEFAULT);

-- 데이터 타입이 맞지 않아서 에러
INSERT INTO EMP
VALUES('개발 지원팀', 3, '이동엽', DEFAULT);

-- 2)
INSERT INTO EMP(EMP_ID, EMP_NAME, DEPT_TITLE, HIRE_DATE)
VALUES(3, '이준용', '보안팀', NULL);

INSERT INTO EMP(EMP_NAME)
VALUES('윤유진');

INSERT INTO EMP(DEPT_TITLE, EMP_NAME)
VALUES('인사팀', '유영민');

-- EMP_NAME 컬럼에 NOT NULL 제약조건으로 인한 에러
INSERT INTO EMP(DEPT_TITLE)
VALUES('마케팅팀');

-- 3)
-- KH.EMPLOYEE 테이블에서 사번, 이름, 부서명, 입사일 가져오기
INSERT INTO EMP
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, HIRE_DATE
FROM KH.EMPLOYEE
	JOIN KH.DEPARTMENT ON (DEPT_ID = DEPT_CODE);
    
-- 컬럼명을 명시 => 순서 상관 없음
INSERT INTO EMP(DEPT_TITLE, EMP_NAME, HIRE_DATE)
SELECT DEPT_TITLE, EMP_NAME, HIRE_DATE
FROM KH.EMPLOYEE
	JOIN KH.DEPARTMENT ON (DEPT_ID = DEPT_CODE);
    
-- 테이블 구조만 복사
CREATE TABLE EMP_DEPT
SELECT EMP_ID, EMP_NAME, DEPT_CODE, HIRE_DATE
FROM KH.EMPLOYEE
WHERE 1 = 0;

CREATE TABLE EMP_MANAGER
SELECT EMP_ID, EMP_NAME, MANAGER_ID
FROM KH.EMPLOYEE
WHERE 1 = 0;

-- EMP_DEPT 테이블에 EMPLOYEE에서 부서 코드가 D1인 직원의 사번, 이름, 부서코드, 입사일 추가하고
INSERT INTO EMP_DEPT
SELECT EMP_ID, EMP_NAME, DEPT_CODE, HIRE_DATE
FROM KH.EMPLOYEE
WHERE DEPT_CODE = 'D1';

-- EMP_MANAGER 테이블에 EMPLOYEE에서 부서 코드가 D1인 직원의 사번, 이름, 관리자 사번 추가
INSERT INTO EMP_MANAGER
SELECT EMP_ID, EMP_NAME, MANAGER_ID
FROM KH.EMPLOYEE
WHERE DEPT_CODE = 'D1';


SELECT * FROM EMP_DEPT;
SELECT * FROM EMP_MANAGER;

/*
	UPDATE
    - 테이블에 기록된 데이터를 수정하는 구문
    
    UPDATE 테이블명
    SET 컬럼명 = 변경하려는 값,
		컬럼명 = 변경하려는 값, ...
	WHERE 조건;
    
    - SET 절에서 여러 개의 컬럼을 콤마(,)로 나열해서 값을 동시에 변경할 수 있다.
    - WHERE 절을 생략하면 모든 행의 데이터가 변경된다. (MYSQL 방지)
    - 사실 서브쿼리 사용 가능, 잘 쓰이지도 않고 버전마다 상황이 다름
*/
SELECT * FROM DEPT_COPY;

-- EMP_SALARY에서 이태림 사원의 급여를 3000000원으로, 보너스를 0.4로 변경
UPDATE EMP_SALARY
SET SALARY = 3000000,
	BONUS = 0.4
WHERE EMP_NAME = '이태림';

-- 모든 사원의 급여를 기존 급여에서 10프로 인상한 금액(기존 급여 * 1.1)으로 변경
UPDATE EMP_SALARY
SET SALARY = SALARY*1.1;

-- 사번이 201인 사원의 사원번호를 NULL로 변경
UPDATE EMP_SALARY
SET EMP_ID = NULL
WHERE EMP_ID = 201;
-- >> EMP_ID 주요키(PRIMARY KEY) 제약조건 위배 (NOT NULL 위배)

-- 아시아 지역에 근무하는 직원들의 보너스를 0.3으로 변경
UPDATE EMP_SALARY
	JOIN KH.DEPARTMENT ON (DEPT_ID = DEPT_CODE)
    JOIN KH.LOCATION ON (LOCATION_ID = LOCAL_CODE)
SET BONUS = 0.3
WHERE LOCAL_NAME LIKE 'ASIA%';

-- 서브쿼리
UPDATE EMP_SALARY
SET BONUS = 0.3
WHERE EMP_ID IN ( 
		SELECT EMP_ID
		FROM EMP_SALARY
			JOIN KH.DEPARTMENT ON (DEPT_ID = DEPT_CODE)
			JOIN KH.LOCATION ON (LOCATION_ID = LOCAL_CODE)
		WHERE LOCAL_NAME LIKE 'ASIA%');

/*
	DELETE
    - 테이블에 기록된 데이터를 삭제하는 구문
    
    DELETE FROM 테이블명
    WHERE 조건식;
    
    - WHERE 절을 제시하지 않으면 전체 행이 삭제된다.
*/
-- EMP_SALARY에서 DEPT_CODE가 D5인 직원들을 삭제
DELETE FROM EMP_SALARY
WHERE DEPT_CODE = 'D5';

/*
	TRUNCATE
    - 테이블 전체 행을 삭제할 때 사용하는 구문
    - DELETE 보다 수행 속도가 빠르다.
    - 별도의 조건을 제시할 수 없고 ROLLBACK이 불가능하다.
	
    TRUNCATE TABLE 테이블명;
*/
START TRANSACTION;

DELETE FROM EMP_SALARY;
DELETE FROM DEPT_COPY;

ROLLBACK; -- DELETE는 ROLLBACK 가능

TRUNCATE TABLE EMP_SALARY;
TRUNCATE TABLE DEPT_COPY;

ROLLBACK; -- TRUNCATE는 ROLLBACK 불가능

SELECT * FROM EMP_SALARY;
SELECT * FROM DEPT_COPY;