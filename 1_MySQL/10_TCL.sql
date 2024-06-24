/*
	TCL(TRANSACTION CONTROL LANGUAGE)
    - 트랜잭션을 제어하는 언어
    - 데이터베이스는 데이터 변경 사항(INSERT, UPDATE, DELETE)들을 묶어서 하나의 트랜잭션에 담아서 처리
    
    트랜잭션(TRANSACTION)
    - 하나의 논리적인 작업 단위
    EX) ATM에서 현금 출력
    1. 카드 삽입
    2. 메뉴 선택
    3. 금액 확인
    4. 인증 - 비밀번호 입력
    5. 실제 계좌에서 금액만큼 인출
    6. 현금 인출
    7. 완료
    -- > 각각의 업무들을 묶어서 하나의 작업 단위로 만드는 것을 트랜잭션
    
    COMMIT : 모든 작업들을 정상적으로 처리하겠다고 확정하는 구문
    ROLLBACK : 모든 작업들을 취소하겠다고 확정하는 구문 (마지막 COMMIT 시점으로 돌아간다.)
*/
-- MYSQL은 기본적으로 AUTOCOMMIT 모드가 활성화되어 있음
-- 비활성화
SET AUTOCOMMIT = 0;

-- 트랜잭션 시작
START TRANSACTION;

CREATE TABLE DEPT_COPY
SELECT * FROM KH.DEPARTMENT;

CREATE TABLE EMP_SALARY
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY, BONUS
FROM KH.EMPLOYEE;

-- DEPT_COPY 테이블에서 DEPT_ID가 'D9'인 부서명을 '전략기획팀'으로 수정
-- UPDATE 테이블명 SET 변경할내용 WHERE 조건;
UPDATE DEPT_COPY 
SET DEPT_TITLE = '전략기획팀' 
WHERE DEPT_ID = 'D9';

ROLLBACK;

COMMIT;

-- EMP_SALARY 테이블에서 노옹철 사원의 급여를 3000000원으로 변경
UPDATE EMP_SALARY
SET SALARY = 3000000
WHERE EMP_NAME = '노옹철';

-- EMP_SALARY에서 EMP_ID가 213, 218인 사원 삭제
DELETE FROM EMP_SALARY
WHERE EMP_ID IN (213, 218);

SELECT * FROM DEPT_COPY;
SELECT * FROM EMP_SALARY;











