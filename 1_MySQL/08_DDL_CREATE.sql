/*
	DDL(DATA DEFINITION LANGUAGE) : 데이터 정의어
    - 객체(OBJECT)/스키마(SCHEMA)를 만들고(CREATE), 변경하고(ALTER), 삭제(DROP)하는 언어
    - 즉, 실제 데이터 값이 아닌 구조 자체를 정의하는 언어
    
	MYSQL에서 객체(구조) : 테이블(TABLE), 뷰(VIEW), 인덱스(INDEX), 프로시저(PROCEDURE), 트리거(TRIGGER), 함수(FUNCTION)
*/
/*
	CREATE
    - 객체를 생성하는 구문
    
    테이블 생성
    CREATE TABLE 테이블명(
		 컬럼명 자료형(크기),
         컬럼명 자료형(크기),
         ...
	);
    
    * 자료형
    1. 문자
		- CHAR / **VARCHAR** : 고정 및 가변 길이 문자, 반드시 크기 지정
        - TEXT : 매우 긴 문자열을 저장하는데 사용
	2. 숫자
		- **INT** : 정수값 저장하는데 사용
        - FLOAT / DOUBLE : 부동소수점 저장하는데 사용
        - DECIMAL : 고정소수점 저장하는데 사용
	3. 날짜 및 시간
		- DATE : 날짜 저장하는데 사용
        - TIME : 시간 저장하는데 사용
        - DATETIME / TIMESTAMP : 날짜와 시간을 함께 저장
	4. 불리언
		- BOOLEAN / BOOL : 참(TRUE) 또는 거짓(FALSE) 값을 저장하는데 사용
	5. 이진 자료형
		- BOLB : 이진 데이터를 저장하는데 사용. 이미지나 동영상과 같은 이진 파일
        --> 실제로는 이미지나 동영상은 따로 관리함 (URL만 문자형으로 저장하는 편)
*/
DROP TABLE MEMBER;
-- 회원에 대한 데이터를 담을 MEMBER 테이블 생성
CREATE TABLE MEMBER(
	MEM_NO INT,
    MEM_ID VARCHAR(20),
    MEM_PWD VARCHAR(20),
	MEM_NAME VARCHAR(20),
    GENDER CHAR(3),
    PHONE VARCHAR(13),
    EMAIL VARCHAR(50),
    MEM_DATE DATE
);

-- 테이블 구조를 표시해주는 구문
DESC MEMBER;

SELECT * FROM MEMBER;

-- 테이블에 데이터를 추가시키는 구문 (DML: INSERT)
-- INSERT INTO 테이블명 VALUES(값, 값, ...);
INSERT INTO MEMBER VALUES(1, 'user01', 'pass01', '공재욱', '남', '010-7310-4769', 'aaa@google.com', '2024-05-28');
INSERT INTO MEMBER VALUES(2, 'user02', 'pass02', '공재욱', '남', NULL, NULL, NOW());
INSERT INTO MEMBER VALUES(2, 'user02', 'pass02', '공재욱', '남', NULL, NULL, CURRENT_DATE());
INSERT INTO MEMBER VALUES(NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

/*
	제약조건(CONSTRAINTS)
    - 사용자가 원하는 조건의 데이터만 유지하기 위해서 각 컬럼에 대해 저장될 값에 대한 제약조건 설정
    - 제약조건은 데이터 무결성 보장을 목적으로 한다.
    - 조류 : NOT NULL, UNIQUE, CHECK, PRIMARY KEY, FOREIGN KEY
*/
/*
	NOT NULL
    - 해당 컬럼에 반드시 값이 있어야만 하는 경우
		(즉, 해당 컬럼에 절대 NULL이 들어와서는 안되는 경우)
	- 추가/수정시 NULL 값을 허용하지 않도록 제한
*/
CREATE TABLE MEM_NOTNULL(
	MEM_NO INT NOT NULL,
    MEM_ID VARCHAR(20) NOT NULL,
    MEM_PWD VARCHAR(20) NOT NULL,
	MEM_NAME VARCHAR(20) NOT NULL,
    GENDER CHAR(3),
    PHONE VARCHAR(13),
    EMAIL VARCHAR(50),
    MEM_DATE DATE
);
DESC MEM_NOTNULL;

INSERT INTO MEM_NOTNULL VALUES(1, 'user_01', 'pass01', '채승훈', '남', null, null, null);
INSERT INTO MEM_NOTNULL VALUES(2, 'user_02', NULL, '손정배', '남', null, null, current_date()); -- >> NOT NULL 제약조건에 위배되어 오류 발생
INSERT INTO MEM_NOTNULL VALUES(2, 'user_01', 'pass01', '손정배', '남', null, null, current_date());

SELECT * FROM MEM_NOTNULL;

/*
	UNIQUE
    - 해당 컬럼에 중복된 값이 들어와서는 안되는 경우
    - 추가/수정 시 기존에 있는 데이터값 중 중복값이 있을 경우 오류 발생
*/
CREATE TABLE MEM_UNIQUE(
	MEM_NO INT NOT NULL,
    MEM_ID VARCHAR(20) NOT NULL UNIQUE,
    MEM_PWD VARCHAR(20) NOT NULL,
	MEM_NAME VARCHAR(20) NOT NULL,
    GENDER CHAR(3),
    PHONE VARCHAR(13),
    EMAIL VARCHAR(50),
    MEM_DATE DATE
);
DESC MEM_UNIQUE;

INSERT INTO MEM_UNIQUE VALUES(1, 'user01', 'pass01', '김진주', '여', null, null, null);
INSERT INTO MEM_UNIQUE VALUES(2, 'user01', 'pass02', '주준영', '남', null, null, null); -- >> UNIQUE 제약조건에 위배되어 INSERT 실패
INSERT INTO MEM_UNIQUE VALUES(2, 'user02', 'pass02', '주준영', 'ㄴ', null, null, null); -- >> 성별에 유효한 값이 아니어도 들어가고 있음

SELECT * FROM MEM_UNIQUE;

/*
	CHECK(조건식)
    - 해당 컬럼에 들어올 수 있는 값에 대한 조건을 제시해볼 수 있음!
    - 해당 조건에 만족하는 데이터값만 담길 수 있음
*/
CREATE TABLE MEM_CHECK(
	MEM_NO INT NOT NULL,
    MEM_ID VARCHAR(20) NOT NULL UNIQUE,
    MEM_PWD VARCHAR(20) NOT NULL,
	MEM_NAME VARCHAR(20) NOT NULL,
    GENDER CHAR(3),
    PHONE VARCHAR(13),
    EMAIL VARCHAR(50),
    MEM_DATE DATE,
    CONSTRAINT MEM_GENDER_CHECK CHECK (GENDER IN ('남', '여')),
    CONSTRAINT MEM_GENDER_NOT_NULL CHECK (GENDER IS NOT NULL)
);
DESC MEM_CHECK;

INSERT INTO mem_check VALUES (1, 'user01', 'pass01', '장성일', '남', null, null, null);
INSERT INTO mem_check VALUES (1, 'user02', 'pass02', '이동호', 'S', null, null, null); -- >> CHECK 제약조건에 위배되었기 때문에 오류 발생
INSERT INTO mem_check VALUES (1, 'user02', 'pass02', '이동호', NULL, null, null, null); -- >> CHECK 제약조건으로 NOT NULL 조건또한 위배되었음을 확인
INSERT INTO mem_check VALUES (1, 'user02', 'pass02', '이동호', '남', null, null, null); -- >> 회원번호(MEM_NO)가 동일해도 INSERT가 되는게 문제

SELECT * FROM MEM_CHECK;

/*
	PRIMARY KEY (기본키)
    - 테이블에서 각 행들을 식별하기 위해 사용될 컬럼에 부여하는 제약조건 (식별자 역할)
    EX) 회원번호, 학번, 사원코드, 부서코드, 직급코드, 제품번호, 주문번호, 운송장번호, ...
    - PRIMARY KEY 제약조건을 부여하면 그 컬럼에 자동으로 NOT NULL + UNIQUE 조건이 설정
    - 한 테이블 당 오로지 한 개만 설정
    --> 간혹 복합키 설정도 가능
*/
CREATE TABLE MEM_PRI(
	MEM_NO INT PRIMARY KEY,
    MEM_ID VARCHAR(20) NOT NULL UNIQUE,
    MEM_PWD VARCHAR(20) NOT NULL,
	MEM_NAME VARCHAR(20) NOT NULL,
    GENDER CHAR(3) CHECK (GENDER IN ('남', '여')) NOT NULL,
    PHONE VARCHAR(13),
    EMAIL VARCHAR(50),
    MEM_DATE DATE
);
DESC MEM_PRI;

INSERT INTO MEM_PRI VALUES(1, 'USER01', 'PASS01', '박세영', '남', NULL, NULL, NULL);
INSERT INTO MEM_PRI VALUES(1, 'USER02', 'PASS01', '배영운', '남', NULL, NULL, NULL); -- >> 기본키에 중복값을 담으려고 해서 문제 발생 (UNIQUE 제약조건 위배)
INSERT INTO MEM_PRI VALUES(NULL, 'USER02', 'PASS01', '배영운', '남', NULL, NULL, NULL); -- >> 기본키에 NULL을 담으려고 해서 문제 발생 (NOT NULL 제약조건 위배)
INSERT INTO MEM_PRI VALUES(2, 'USER02', 'PASS01', '배영운', '남', NULL, NULL, NULL);

SELECT * FROM MEM_PRI;

-- 복합키 사용 예시 (어떤 회원이 어떤 상품을 찜했는지에 대한 데이터를 보관하는 테이블)
-- >> 복합키 방식으로 지정해서 쓰는 건 가급적 추천 X
CREATE TABLE PRO_LIKE(
	MEM_NO INT,
    PRODUCT_NAME VARCHAR(10),
    LIKE_DATE DATE,
    PRIMARY KEY (MEM_NO, PRODUCT_NAME)
);
DESC PRO_LIKE;

INSERT INTO PRO_LIKE VALUES(1, 'A', current_date());
INSERT INTO PRO_LIKE VALUES(1, 'B', current_date());
INSERT INTO PRO_LIKE VALUES(2, 'B', current_date());
INSERT INTO PRO_LIKE VALUES(1, 'A', current_date());

SELECT * FROM PRO_LIKE;

-- 회원등급에 대한 데이터를 따로 보관하는 테이블
DROP TABLE MEM_GRADE;
CREATE TABLE MEM_GRADE(
	GRADE_CODE INT PRIMARY KEY AUTO_INCREMENT,
    GRADE_NAME VARCHAR(30) NOT NULL
);
DESC MEM_GRADE;

INSERT INTO MEM_GRADE(GRADE_NAME) VALUES('일반회원');
INSERT INTO MEM_GRADE(GRADE_NAME) VALUES('우수회원');
INSERT INTO MEM_GRADE(GRADE_NAME) VALUES('특별회원');

SELECT * FROM MEM_GRADE;

DROP TABLE MEMBER;
CREATE TABLE MEMBER(
	MEM_NO INT PRIMARY KEY AUTO_INCREMENT,
    MEM_ID VARCHAR(20) NOT NULL UNIQUE,
    MEM_PWD VARCHAR(20) NOT NULL,
    MEM_NAME VARCHAR(20) NOT NULL,
    GRADE_ID INT -- > 회원 등급 번호 같이 보관할 컬럼
);
DESC MEMBER;

INSERT INTO MEMBER(MEM_ID, MEM_PWD, MEM_NAME, GRADE_ID) 
VALUES('USER01', 'PASS01', '최제환', NULL);
INSERT INTO MEMBER(MEM_ID, MEM_PWD, MEM_NAME, GRADE_ID) 
VALUES('USER02', 'PASS02', '김경주', 1);
INSERT INTO MEMBER(MEM_ID, MEM_PWD, MEM_NAME, GRADE_ID) 
VALUES('USER03', 'PASS03', '우현성', 4); -- >> 유효한 회원등급번호가 아니어도 INSERT 가능

SELECT * FROM MEMBER;

SELECT *
FROM MEMBER
	LEFT JOIN MEM_GRADE ON (GRADE_ID = GRADE_CODE);

/*
	FOREIGN KEY (외래키)
    - 외래키 역할을 하는 컬럼에 부여하는 제약조건
    - 다른 테이블에 존재하는 값만 들어와야 되는 특정 컬럼에 부여하는 제약조건
		(단, NULL 값은 가질 수 있음)
        
	-- >> 다른 테이블을 참조한다고 표현
    -- >> 주로 FOREIGN KEY 제약조건에 의해 테이블 간의 관계가 형성됨
*/
DROP TABLE MEMBER;
CREATE TABLE MEMBER(
	MEM_NO INT PRIMARY KEY AUTO_INCREMENT,
    MEM_ID VARCHAR(20) NOT NULL UNIQUE,
    MEM_PWD VARCHAR(20) NOT NULL,
    MEM_NAME VARCHAR(20) NOT NULL,
    GRADE_ID INT,
    FOREIGN KEY (GRADE_ID) REFERENCES MEM_GRADE(GRADE_CODE)
);
DESC MEMBER;

INSERT INTO MEMBER(MEM_ID, MEM_PWD, MEM_NAME, GRADE_ID) 
VALUES('USER01', 'PASS01', '최제환', NULL); -- >> 외래키 제약조건이 부여되도 기본적으로 NULL 허용
INSERT INTO MEMBER(MEM_ID, MEM_PWD, MEM_NAME, GRADE_ID) 
VALUES('USER02', 'PASS02', '김경주', 1);
INSERT INTO MEMBER(MEM_ID, MEM_PWD, MEM_NAME, GRADE_ID) 
VALUES('USER03', 'PASS03', '우현성', 4); -- >> 부모키(PARENT KEY)를 찾을 수 없다는 오류 발생
INSERT INTO MEMBER(MEM_ID, MEM_PWD, MEM_NAME, GRADE_ID) 
VALUES('USER03', 'PASS03', '우현성', 2);

SELECT * FROM MEMBER;

-- >> 부모 테이블(MEM_GRADE)에서 데이터값을 삭제할 경우 어떤 문제가 발생하는지
-- 데이터 삭제 : DELETE FROM 테이블명 WHERE 조건;

-- MEM_GRADE 테이블에서 GRADE_CODE가 2인 등급 삭제
DELETE FROM MEM_GRADE
WHERE GRADE_CODE = 2; -- >> 자식 테이블(MEMBER)에 2라는 값을 사용하고 있기 때문에 삭제 X

-- MEM_GRADE 테이블에서 GRADE_CODE가 3인 등급 삭제
DELETE FROM MEM_GRADE
WHERE GRADE_CODE = 3; -- >> 자식 테이블(MEMBER)에 3이라는 값을 사용하지 않기 때문에 삭제 O

DELETE FROM MEMBER WHERE MEM_NO = 4;

/*
	자식테이블 생성시 외래키 제약조건을 부여할 때 삭제옵션 지정 가능
    - 삭제옵션 : 부모테이블의 데이터 삭제 시 그 데이터를 사용하고 있는 자식테이블의 값을 어떻게 처리할건지
    
    1. ON DELETE RESTRICTED (기본값)
		: 자식데이터로 쓰이는 부모데이터는 삭제가 아예 안되게끔
	2. ON DELETE SET NULL
		: 부모데이터 삭제시 해당 데이터를 쓰고 있는 자식데이터의 값을 NULL로 처리
    3. ON DELETE CASCADE
		: 부모데이터 삭제시 해당 데이터를 쓰고 있는 자식데이터도 같이 삭제
*/
DROP TABLE MEMBER;
CREATE TABLE MEMBER(
	MEM_NO INT PRIMARY KEY AUTO_INCREMENT, -- 컬럼 레벨 방식
    MEM_ID VARCHAR(20) NOT NULL UNIQUE,
    MEM_PWD VARCHAR(20) NOT NULL,
    MEM_NAME VARCHAR(20) NOT NULL,
    GRADE_ID INT,
    FOREIGN KEY (GRADE_ID) REFERENCES MEM_GRADE(GRADE_CODE) ON DELETE SET NULL -- 테이블 레벨 방식
);
DESC MEMBER;

INSERT INTO MEMBER(MEM_ID, MEM_PWD, MEM_NAME, GRADE_ID) 
VALUES('USER01', 'PASS01', '최제환', NULL); -- >> 외래키 제약조건이 부여되도 기본적으로 NULL 허용
INSERT INTO MEMBER(MEM_ID, MEM_PWD, MEM_NAME, GRADE_ID) 
VALUES('USER02', 'PASS02', '김경주', 1);
INSERT INTO MEMBER(MEM_ID, MEM_PWD, MEM_NAME, GRADE_ID) 
VALUES('USER03', 'PASS03', '우현성', 2);

SELECT * FROM MEMBER;

-- MEM_GRADE에서 GRADE_CODE가 1인 등급 삭제
DELETE FROM MEM_GRADE WHERE GRADE_CODE = 1; -- > 삭제 가능, 단 1을 가져다 쓰고 있는 자식 데이터 값이 NULL로 변경

INSERT INTO MEM_GRADE(GRADE_NAME) VALUES('일반회원');

SELECT * FROM MEM_GRADE;

-- ON DELETE CASCADE
DROP TABLE MEMBER;
CREATE TABLE MEMBER(
	MEM_NO INT PRIMARY KEY AUTO_INCREMENT, -- 컬럼 레벨 방식
    MEM_ID VARCHAR(20) NOT NULL UNIQUE,
    MEM_PWD VARCHAR(20) NOT NULL,
    MEM_NAME VARCHAR(20) NOT NULL,
    GRADE_ID INT,
    FOREIGN KEY (GRADE_ID) REFERENCES MEM_GRADE(GRADE_CODE) ON DELETE CASCADE -- 테이블 레벨 방식
);
DESC MEMBER;

INSERT INTO MEMBER(MEM_ID, MEM_PWD, MEM_NAME, GRADE_ID) 
VALUES('USER01', 'PASS01', '최제환', NULL); -- >> 외래키 제약조건이 부여되도 기본적으로 NULL 허용
INSERT INTO MEMBER(MEM_ID, MEM_PWD, MEM_NAME, GRADE_ID) 
VALUES('USER02', 'PASS02', '김경주', 4);
INSERT INTO MEMBER(MEM_ID, MEM_PWD, MEM_NAME, GRADE_ID) 
VALUES('USER03', 'PASS03', '우현성', 2);

SELECT * FROM MEMBER;

-- MEM_GRADE 테이블에서 GRADE_CODE가 2인 데이터 삭제
DELETE FROM MEM_GRADE WHERE GRADE_CODE = 2;

/*
	DEFAULT 기본값
    - 제약조건 아님
    - 컬럼을 설정하지 않고 INSERT시 NULL이 아닌 기본값을 세팅해주는 값
*/
DROP TABLE MEMBER;
CREATE TABLE MEMBER(
	MEM_NO INT PRIMARY KEY AUTO_INCREMENT,
    MEM_NAME VARCHAR(20) NOT NULL,
    HOBBY VARCHAR(20) DEFAULT '패션',
    ENROLL_DATE DATE DEFAULT (CURRENT_DATE)
);

INSERT INTO MEMBER(MEM_NAME) VALUES('김현호');

SELECT * FROM MEMBER;

/*
	서브쿼리를 이용한 테이블 생성
    - 컬럼명, 데이터 타입, 값 모두 복사 / 제약조건은 NOT NULL만
    
    CREATE TABLE 테이블명
    AS 서브쿼리;
*/
-- KH 스키마에 있는 EMPLOYEE 테이블 복사하여 새로운 테이블 생성
CREATE TABLE EMPLOYEE_COPY
SELECT * FROM KH.EMPLOYEE;

SELECT * FROM EMPLOYEE_COPY;

DESC KH.EMPLOYEE;
DESC EMPLOYEE_COPY;

DROP TABLE EMPLOYEE_COPY;

-- 테이블만 생성하고 데이터 값은 복사하지 않는 방법
CREATE TABLE EMPLOYEE_COPY
SELECT * FROM KH.EMPLOYEE WHERE 1 = 0;
-- 모든 행에 대해서 매번 FALSE이기 때문에 테이블 구조만 복사

-- EMPLOYEE 테이블에서 사번, 직원명, 급여, 연봉만 저장한 테이블 EMPLOYEE_COPY 테이블로 생성
CREATE TABLE EMPLOYEE_COPY
SELECT EMP_ID, EMP_NAME, SALARY, SALARY*12
FROM KH.EMPLOYEE;

SELECT * FROM EMPLOYEE_COPY;






