/*
	인덱스(INDEX)
    - SQL 명령문의 처리 속도를 향상시키기 위해 행들의 위치 정보를 가지고 있다.
    
    * 데이터 검색 방식
    TABLE FULL SCAN : 테이블 데이터를 처음부터 끝까지 검색하여 원하는 데이터를 찾는 방식
    INDEX SCAN : 인덱스를 통해 데이터를 찾는 방식
    
    기본키(PRIMARY KEY)는 테이블의 각 행을 고유하게 식별한다.
    실제 테이블 데이터가 모두 인덱스 구조로 저장된다.
*/
-- 테이블에서 인덱스 조회
SHOW INDEX FROM EMPLOYEE;

-- 특정 스키마에 있는 인덱스를 한꺼번에 조회
SELECT *
FROM INFORMATION_SCHEMA.STATISTICS
WHERE TABLE_SCHEMA = 'KH';

/*
	INDEX 생성
    
    CREATE INDEX 인덱스명
    ON 테이블명(컬럼, 컬럼, ...);
*/
SELECT *
FROM SAKILA.PAYMENT
WHERE AMOUNT = 0.99;

CREATE INDEX IDX_AMOUNT
ON SAKILA.PAYMENT(AMOUNT); -- 비고유 인덱스 생성

CREATE INDEX IDX_AMOUNT_ID
ON SAKILA.PAYMENT(AMOUNT, PAYMENT_ID); -- 결합 인덱스 생성

SELECT * FROM SAKILA.PAYMENT;
SHOW INDEX FROM SAKILA.PAYMENT;

/*
	INDEX 삭제
    
    DROP INDEX 인덱스명
    ON 테이블명;
*/

DROP INDEX IDX_AMOUNT ON SAKILA.PAYMENT;
DROP INDEX IDX_AMOUNT_ID ON SAKILA.PAYMENT;

/*
	프로시저(PROCEDURE)
    - SQL문을 저장하여 필요할 때마다 다시 입력할 필요 없이
		간단하게 호출해서 사용 가능하게 하는 코드 블록
        
	DELIMITER //
    
    CREATE PROCEDURE 프로시저명(매개변수)
    BEGIN
		저장할 SQL문
	END //
    
    DELIMITER ;
*/
CREATE TABLE EMP_COPY
SELECT * FROM EMPLOYEE;

-- EMP_COPY 테이블의 모든 데이터를 삭제하는 프로시저 생성
DELIMITER //

CREATE PROCEDURE DEL_ALL_EMP()
BEGIN
	DELETE FROM EMP_COPY;
END //

DELIMITER ;

-- 만들어진 프로시저 확인
SHOW PROCEDURE STATUS;

-- DEL_ALL_EMP 프로시저 호출
CALL DEL_ALL_EMP();

-- 프로시저 삭제
DROP PROCEDURE DEL_ALL_EMP;

-- 매개변수가 있는 프로시저
DELIMITER //

CREATE PROCEDURE DEL_ALL_EMP(IN ID INT)
BEGIN
	DELETE FROM EMP_COPY
    WHERE EMP_ID = ID;
END //

DELIMITER ;

-- 프로시저 실행
CALL DEL_ALL_EMP(200);
CALL DEL_ALL_EMP(205);

INSERT INTO EMP_COPY
SELECT * FROM EMPLOYEE;

SELECT * FROM EMP_COPY;

/*
	함수(FUNCTION)
    - 프로시저와 거의 유사한 용도로 값을 반환하는 재사용 가능한 코드 블록
    - 특정 계산이나 로직을 함수로 묶어 사용한다
    
    DELIMITER //
    
    CREATE FUNCTION 함수명(매개변수)
    RETURNS 자료형
    DETERMINISTIC
    BEGIN
		DECLARE 반환값 자료형
        결과에 해당하는 SQL문 등 계산식 (RESULT로 처리)
        RETURN 반환값
    END //
    
    DELIMITER ;
*/
-- 사번을 입력받아 해당 사원의 연봉을 계산하고 리턴하는 함수 생성
DELIMITER //
CREATE FUNCTION SALARY_CALC(ID INT)
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE RESULT INT;
    
    SELECT SALARY*12 AS RESULT
    INTO RESULT
    FROM EMPLOYEE 
    WHERE EMP_ID = ID;
    
    RETURN RESULT;
END //
DELIMITER ;

-- 함수 호출
SELECT SALARY_CALC(200);

-- 방금 만든 SALARY_CALC 함수를 이용해서 연봉이 4000만원 이상인
-- 사원의 사번, 사원명, 급여, 연봉 조회 (정렬 연봉 높은 순)
SELECT EMP_ID, EMP_NAME, SALARY, SALARY_CALC(EMP_ID) 연봉
FROM EMPLOYEE
WHERE SALARY_CALC(EMP_ID) >= 40000000
ORDER BY 연봉 DESC;

-- 함수 삭제
DROP FUNCTION IF EXISTS SALARY_CALC;

/*
	트리거(TRIGGER)
    - 특정 이벤트가 발생할 때 자동으로 실행될 내용을 정의하여 저장
    
    EX)
    - 회원탈퇴시 기존 회원 테이블에 데이터 DELETE 후
		곧바로 탈퇴된 회원들만 따로 보관하는 테이블에 자동으로 INSERT 처리
	- 신고횟수가 일정 수를 넘었을 때 해당 회원을 블랙리스트로 처리
    - 입출고에 대한 데이터가 기록(INSERT) 될 때마다
		해당 상품에 대한 재고수량을 매번 수정(UPDATE) 해야 되는 경우
        
	DELIMITER //
    
    CREATE TRIGGER 트리거명
    BEFORE|AFTER INSERT|UPDATE|DELETE ON 테이블명
    FOR EACH ROW
    BEGIN
		이벤트 발생시 실행할 SQL 구문
    END //
    
    DELIMITER ;
*/
SHOW TRIGGERS;

CREATE TABLE LOG(
	LOG_CODE INT AUTO_INCREMENT PRIMARY KEY,
    EVENT_TYPE VARCHAR(50),
    EVENT_DESC TEXT,
    EVENT_TIMESTAMP TIMESTAMP DEFAULT NOW()
);

-- EMPLOYEE 테이블에 새로운 행이 INSERT 될 때마다 '신입사원이 입사했습니다'라는
-- 메시지를 LOG 테이블에 자동으로 INSERT 되는 트리거 생성
DELIMITER //
CREATE TRIGGER TRG_01
AFTER INSERT ON EMPLOYEE
FOR EACH ROW
BEGIN
	INSERT INTO LOG(EVENT_TYPE, EVENT_DESC) 
    VALUES('INSERT', '신입사원이 입사했습니다.');
END //
DELIMITER ;

INSERT INTO EMPLOYEE(EMP_ID, EMP_NAME, EMP_NO) 
VALUES(800, '서동문', '000000-1111111');

SELECT * FROM EMPLOYEE;
SELECT * FROM LOG;

-- EMPLOYEE 테이블에서 UPDATE 수행 후 '업데이트 실행' 메시지를 LOG에 담는
-- TRG_02 트리거 생성
-- OLD : 수정, 삭제 전 데이터에 접근 가능
-- NEW : 추가, 수정 후 데이터에 접근 가능
DELIMITER //
CREATE TRIGGER TRG_02
AFTER UPDATE ON EMPLOYEE
FOR EACH ROW
BEGIN
	INSERT INTO LOG(EVENT_TYPE, EVENT_DESC)
    VALUES('UPDATE', CONCAT('변경 전 : ', OLD.DEPT_CODE, '변경 후 : ', NEW.DEPT_CODE));
END //
DELIMITER ;

-- 트리거 삭제
DROP TRIGGER IF EXISTS TRG_02;

-- EMPLOYEE 테이블에서 부서 코드가 D6인 직원들의 부서 코드를 D3으로 변경
UPDATE EMPLOYEE
SET DEPT_CODE = 'D6'
WHERE DEPT_CODE = 'D3';

SELECT * FROM LOG;
SELECT * FROM EMPLOYEE;

-- 상품 입/출고
-- 1. 상품에 대한 데이터 보관할 테이블 생성(PRODUCT)
/*
	컬럼 : PCODE / INT / 기본키 / AUTO_INCREMENT
			PNAME / VARCHAR(30) / NOT NULL
            BRAND / VARCHAR(30) / NOT NULL
            PRICE / INT
            STOCK / INT / 기본값 0
*/
CREATE TABLE PRODUCT(
	PCODE INT AUTO_INCREMENT PRIMARY KEY,
    PNAME VARCHAR(30) NOT NULL,
    BRAND VARCHAR(30) NOT NULL,
    PRICE INT,
    STOCK INT DEFAULT 0
);
-- 데이터 3개 추가
INSERT INTO PRODUCT(PNAME, BRAND, PRICE) VALUES('갤럭시S24', '삼성', 1910000);
INSERT INTO PRODUCT(PNAME, BRAND, PRICE, STOCK) VALUES('갤럭시Z 플립4', '삼성', 1780000, 10);
INSERT INTO PRODUCT(PNAME, BRAND, PRICE, STOCK) VALUES('아이폰15', '애플', 1250000, 20);

SELECT * FROM PRODUCT;

-- 2. 상품 입/출고 상세 이력 테이블 생성 (PRODETAIL)
/*
	컬럼 : DCODE / INT / 기본키 / AUTO_INCREMENT
			PCODE / INT / 외래키
            PDATE / DATE / 기본값 현재날짜
            AMOUNT / INT / NOT NULL
            STATUS / CHAR(2) / 입고, 출고 둘 중 하나
*/
CREATE TABLE PRODETAIL(
	DCODE INT AUTO_INCREMENT PRIMARY KEY,
    PCODE INT,
    PDATE DATE DEFAULT (CURRENT_DATE),
    AMOUNT INT NOT NULL,
    STATUS CHAR(2) CHECK(STATUS IN('입고', '출고')),
    
	FOREIGN KEY(PCODE) REFERENCES PRODUCT(PCODE)
);

-- 1번 상품이 오늘날짜로 10개 입고 (PRODETAIL)
INSERT INTO PRODETAIL(PCODE, AMOUNT, STATUS)
VALUES(1, 10, '입고');
-- 1번 상품의 재고수량 10 증가 (PRODUCT)
UPDATE PRODUCT
SET	STOCK = STOCK + 10
WHERE PCODE = 1;

-- 3번 상품이 오늘날짜로 5개 출고
INSERT INTO PRODETAIL(PCODE, AMOUNT, STATUS)
VALUES(3, 5, '출고');
-- 3번 상품의 재고수량 5 감소
UPDATE PRODUCT
SET	STOCK = STOCK - 5
WHERE PCODE = 3;

/*
	PRODETAIL 테이블에 INSERT 발생시
    PRODUCT 테이블에 매번 자동으로 재고수량 UPDATE 되게끔 트리거 정의
    
    트리거명 : TRG_03
    IF 조건
    THEN SQL문
    ELSE SQL문
    END IF;
*/

DELIMITER //
CREATE TRIGGER TRG_03
AFTER INSERT ON PRODETAIL
FOR EACH ROW
BEGIN
	IF 
		NEW.STATUS = '입고'
    THEN
		UPDATE PRODUCT
		SET	STOCK = STOCK + NEW.AMOUNT
		WHERE PCODE = NEW.PCODE;
    ELSE
		UPDATE PRODUCT
		SET	STOCK = STOCK - NEW.AMOUNT
		WHERE PCODE = NEW.PCODE;
    END IF;
END //
DELIMITER ;

DROP TRIGGER TRG_03;

-- 2번 상품이 오늘날짜로 20개 입고
INSERT INTO PRODETAIL(PCODE, AMOUNT, STATUS)
VALUES(2, 20, '입고');

-- 3번 상품이 오늘날짜로 7개 출고
INSERT INTO PRODETAIL(PCODE, AMOUNT, STATUS)
VALUES(3, 7, '출고');

-- 1번 상품이 오늘날짜로 100개 입고
INSERT INTO PRODETAIL(PCODE, AMOUNT, STATUS)
VALUES(1, 100, '입고');

SELECT * FROM PRODUCT;
SELECT * FROM PRODETAIL;

/*
	데이터베이스 모델링 (DB 모델링)
    - 데이터베이스를 설계하는 프로세스
    - 테이블 간의 관계 정의 및 구조 결정
    
    작업 순서
    1. 개념적 모델링
		- 엔티티(ENTITY) 추출
        - 엔티티 간의 관계 설정
    2. 논리적 모델링 : ERD(ENTITY RELATIONSHIP DIAGRAM) 툴 - EXERD
		- 정규화 작업 (1 ~ 5) ... 3까지만 정규화
        --> 너무 쪼개면 JOIN만 많아짐
    3. 물리적 모델링
		- 테이블을 실직적으로 구성
*/