DROP TABLE rent;
DROP TABLE member;
DROP TABLE book;
DROP TABLE publisher;

-- 실습 문제
-- 도서관리 프로그램을 만들기 위한 테이블 만들기

-- 1. 출판사들에 대한 데이터를 담기 위한 출판사 테이블(publisher) 
--    컬럼 : pub_no(출판사번호) -- 기본 키
--           pub_name(출판사명) -- NOT NULL
--           phone(전화번호)
CREATE TABLE PUBLISHER(
	PUB_NO INT AUTO_INCREMENT PRIMARY KEY,
    PUB_NAME VARCHAR(50) NOT NULL,
    PHONE VARCHAR(15)
);

DESC PUBLISHER;

INSERT INTO PUBLISHER(PUB_NAME, PHONE) VALUES('프리렉', '032-326-7282');
INSERT INTO PUBLISHER(PUB_NAME, PHONE) VALUES('인사이트', '02-322-5143');
INSERT INTO PUBLISHER(PUB_NAME, PHONE) VALUES('길벗', '02-332-0931');

SELECT * FROM PUBLISHER;

-- 2. 도서들에 대한 데이터를 담기 위한 도서 테이블 (book)
--    컬럼 : bk_no (도서번호) -- 기본 키
--           bk_title (도서명) -- NOT NULL
--           bk_author(저자명) -- NOT NULL
--           bk_price(가격)
--           bk_pub_no(출판사 번호) -- 외래 키(publisher 테이블을 참조하도록)
--    조건 : 이때 참조하고 있는 부모 데이터 삭제 시 자식 데이터도 삭제 되도록 옵션 지정
CREATE TABLE BOOK(
	BK_NO INT AUTO_INCREMENT PRIMARY KEY,
    BK_TITLE VARCHAR(100) NOT NULL,
    BK_AUTHOR VARCHAR(50) NOT NULL,
    BK_PRICE INT,
    BK_PUB_NO INT, 
    
    FOREIGN KEY (BK_PUB_NO) REFERENCES PUBLISHER(PUB_NO) ON DELETE CASCADE
);

DESC BOOK;

INSERT INTO BOOK(BK_TITLE, BK_AUTHOR, BK_PRICE, BK_PUB_NO) 
VALUES('개발자를 위한 생각의 정리, 문서 작성법', '카이마이 미즈히로', 20000, 1);
INSERT INTO BOOK(BK_TITLE, BK_AUTHOR, BK_PRICE, BK_PUB_NO) 
VALUES('1일 1로그 100일 완성 IT 지식', '브라이언 W. 커니핸', 200000, 2);
INSERT INTO BOOK(BK_TITLE, BK_AUTHOR, BK_PRICE, BK_PUB_NO) 
VALUES('개발자가 영어도 잘해야 하나요?', '최희철', 27000, 3);
INSERT INTO BOOK(BK_TITLE, BK_AUTHOR, BK_PRICE, BK_PUB_NO) 
VALUES('피플웨어', '톰 드마르코', 16800, 2);
INSERT INTO BOOK(BK_TITLE, BK_AUTHOR, BK_PRICE, BK_PUB_NO) 
VALUES('그로스 해킹', '라이언 홀리데이', 13800, 3);

SELECT * FROM BOOK;

-- 3. 회원에 대한 데이터를 담기 위한 회원 테이블 (member)
--    컬럼 : member_no(회원번호) -- 기본 키
--           member_id(아이디)   -- 중복 금지
--           member_pwd(비밀번호) -- NOT NULL
--           member_name(회원명) -- NOT NULL
--           gender(성별)        -- 'M' 또는 'F'로 입력되도록 제한
--           address(주소)       
--           phone(연락처)       
--           status(탈퇴여부)     -- 기본값 'N' / 'Y' 혹은 'N'만 입력되도록 제약조건
--           enroll_date(가입일)  -- 기본값 현재날짜
CREATE TABLE MEMBER(
	MEMBER_NO INT AUTO_INCREMENT PRIMARY KEY,
    MEMBER_ID VARCHAR(50) UNIQUE NOT NULL,
    MEMBER_PWD VARCHAR(50) NOT NULL,
    MEMBER_NAME VARCHAR(20) NOT NULL,
    GENDER CHAR(1),
    ADDRESS VARCHAR(100),
    PHONE VARCHAR(15),
    STATUS CHAR(1) DEFAULT 'N',
    ENROLL_DATE DATE DEFAULT (CURRENT_DATE),
    
    CONSTRAINT MEMBER_GENDER_CHECK CHECK(GENDER IN ('M', 'F')),
    CONSTRAINT MEMBER_STATUS_CHECK CHECK(STATUS IN ('N', 'Y'))
);

DESC MEMBER;

INSERT INTO MEMBER(MEMBER_ID, MEMBER_PWD, MEMBER_NAME, GENDER, ADDRESS, PHONE) 
VALUES('user01', 'pass01', '가나다', 'M', '서울시 강남구', '010-1111-2222');
INSERT INTO MEMBER(MEMBER_ID, MEMBER_PWD, MEMBER_NAME, GENDER, ADDRESS, PHONE) 
VALUES('user02', 'pass02', '라마바', 'M', '서울시 서초구', '010-3333-4444');
INSERT INTO MEMBER(MEMBER_ID, MEMBER_PWD, MEMBER_NAME, GENDER, ADDRESS, PHONE) 
VALUES('user03', 'pass03', '사아자', 'F', '경기도 광주시', '010-5555-6666');

SELECT * FROM MEMBER;

-- 4. 도서를 대여한 회원에 대한 데이터를 담기 위한 대여 목록 테이블(rent)
--    컬럼 : rent_no(대여번호) -- 기본 키
--           rent_mem_no(대여 회원번호) -- 외래 키(member와 참조)
--           rent_book_no(대여 도서번호) -- 외래 키(book와 참조)
--           rent_date(대여일) -- 기본값 현재날짜
--    조건 : 이때 부모 데이터 삭제 시 NULL 값이 되도록 옵션 설정

CREATE TABLE RENT(
	RENT_NO INT AUTO_INCREMENT PRIMARY KEY,
    RENT_MEM_NO INT,
    RENT_BOOK_NO INT,
    RENT_DATE DATE DEFAULT (CURRENT_DATE)
);

DESC RENT;

-- ALTER로 FOREIGN KEY만 관리
ALTER TABLE RENT
	ADD FOREIGN KEY (RENT_MEM_NO) REFERENCES MEMBER(MEMBER_NO) ON DELETE SET NULL,
	ADD FOREIGN KEY (RENT_BOOK_NO) REFERENCES BOOK(BK_NO) ON DELETE SET NULL;

DESC RENT;

INSERT INTO RENT(RENT_MEM_NO, RENT_BOOK_NO) VALUES(1, 2);
INSERT INTO RENT(RENT_MEM_NO, RENT_BOOK_NO) VALUES(1, 3);
INSERT INTO RENT(RENT_MEM_NO, RENT_BOOK_NO) VALUES(2, 1);
INSERT INTO RENT(RENT_MEM_NO, RENT_BOOK_NO) VALUES(2, 2);
INSERT INTO RENT(RENT_MEM_NO, RENT_BOOK_NO) VALUES(1, 5);

SELECT * FROM RENT;

-- 5. 2번 도서를 대여한 회원의 이름, 아이디, 대여일, 반납 예정일(대여일 + 7일)을 조회하시오.
SELECT 
	MEMBER_NAME '회원이름', 
	MEMBER_ID '아이디', 
    RENT_DATE '대여일', 
    ADDDATE(RENT_DATE, INTERVAL 7 DAY) '반납 예정일'
FROM MEMBER
	JOIN RENT ON (RENT_MEM_NO = MEMBER_NO)
WHERE RENT_BOOK_NO = 2;

-- 6. 회원번호가 1번인 회원이 대여한 도서들의 도서명, 출판사명, 대여일, 반납예정일을 조회하시오.
SELECT 
	BK_TITLE '도서명', 
    PUB_NAME '출판사명', 
    RENT_DATE '대여일', 
    ADDDATE(RENT_DATE, INTERVAL 7 DAY) '반납 예정일'
FROM MEMBER
	JOIN RENT ON (RENT_MEM_NO = MEMBER_NO)
    JOIN BOOK ON (RENT_BOOK_NO = BK_NO)
    JOIN PUBLISHER ON (BK_PUB_NO = PUB_NO)
WHERE MEMBER_NO = 1;
