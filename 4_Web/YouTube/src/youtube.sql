DROP TABLE VIDEO;
DROP TABLE CHANNEL;
DROP TABLE MEMBER;
DROP TABLE COMMENT;
DROP TABLE VIDEO_LIKE;
DROP TABLE SUBSCRIBE;

-- 회원
CREATE TABLE MEMBER(
	ID VARCHAR(20) PRIMARY KEY,
    PASSWORD VARCHAR(20),
    EMAIL VARCHAR(50),
    PHONE VARCHAR(13)
);

-- 채널
CREATE TABLE CHANNEL(
	CHANNEL_CODE INT PRIMARY KEY AUTO_INCREMENT,
    CHANNEL_IMG VARCHAR(100),
    CHANNEL_NAME VARCHAR(50),
    ID VARCHAR(20),
    
    FOREIGN KEY(ID) REFERENCES MEMBER(ID)
);

-- 동영상
CREATE TABLE VIDEO(
	VIDEO_CODE INT PRIMARY KEY AUTO_INCREMENT,
    VIDEO_URL VARCHAR(100),
    VIDEO_IMG VARCHAR(100),
    VIDEO_TITLE VARCHAR(80),
    VIDEO_COUNT INT DEFAULT 0,
    VIDEO_DATE DATE DEFAULT (CURRENT_DATE),
    VIDEO_DESC TEXT,
    CHANNEL_CODE INT,
    
    FOREIGN KEY(CHANNEL_CODE) REFERENCES CHANNEL(CHANNEL_CODE)
);

-- 댓글
CREATE TABLE COMMENT(
	COMMENT_CODE INT PRIMARY KEY AUTO_INCREMENT,
    COMMENT_TEXT TEXT,
    COMMENT_DATE DATE DEFAULT (CURRENT_DATE),
    ID VARCHAR(20),
    VIDEO_CODE INT,
    PARENT_CODE INT,
    
    FOREIGN KEY(ID) REFERENCES MEMBER(ID),
    FOREIGN KEY(VIDEO_CODE) REFERENCES VIDEO(VIDEO_CODE)
);

-- 구독
CREATE TABLE SUBSCRIBE(
	SUB_CODE INT PRIMARY KEY AUTO_INCREMENT,
    ID VARCHAR(20),
    CHANNEL_CODE INT,
    
    FOREIGN KEY(ID) REFERENCES MEMBER(ID),
    FOREIGN KEY(CHANNEL_CODE) REFERENCES CHANNEL(CHANNEL_CODE)
);

-- 좋아요
CREATE TABLE VIDEO_LIKE(
	LIKE_CODE INT PRIMARY KEY AUTO_INCREMENT,
    ID VARCHAR(20),
    VIDEO_CODE INT,
    
    FOREIGN KEY(ID) REFERENCES MEMBER(ID),
    FOREIGN KEY(VIDEO_CODE) REFERENCES VIDEO(VIDEO_CODE)
);

INSERT INTO MEMBER(ID, PASSWORD, EMAIL, PHONE) VALUES('akmu', '1234', 'akmu@gmail.com', '010-0000-0000');
SELECT * FROM MEMBER;

INSERT INTO CHANNEL(CHANNEL_IMG, CHANNEL_NAME, ID) VALUES('http://192.168.10.51:8082/channel/akmu.jpg', 'AKMU' ,'akmu');
SELECT * FROM CHANNEL;

INSERT INTO VIDEO(VIDEO_URL, VIDEO_IMG, VIDEO_TITLE, VIDEO_DESC, CHANNEL_CODE) 
VALUES('http://192.168.10.51:8082/video/AKMU1.mp4', 'http://192.168.10.51:8082/thumbnail/akmu.webp', 'AKMU - 후라이의 꿈 LIVE CLIP (FESTIVAL ver.)', 'More about AKMU', 1);

SELECT * FROM VIDEO
JOIN channel USING(channel_code);

select * from user;

insert into user (user_email, user_password, user_nickname, user_img, user_date, user_ent_yn, user_spotify_yn, user_gender, user_birth, user_manager, user_enroll_date) values ('kilett0@opensource.org', 'Cherokee', 'rwane0', 'http://dummyimage.com/165x100.png/5fa2dd/ffffff', '2024-02-28', 'Y', 'N', 'M', '2000-10-17', null, '2024-05-05');
insert into user (user_email, user_password, user_nickname, user_img, user_date, user_ent_yn, user_spotify_yn, user_gender, user_birth, user_manager, user_enroll_date) values ('hmolesworth1@buzzfeed.com', 'Aleut', 'mbenardet1', 'http://dummyimage.com/124x100.png/dddddd/000000', '2024-04-22', 'Y', 'Y', 'F', '1967-12-20', null, '2024-07-19');
insert into user (user_email, user_password, user_nickname, user_img, user_date, user_ent_yn, user_spotify_yn, user_gender, user_birth, user_manager, user_enroll_date) values ('dlandsborough2@springer.com', 'Vietnamese', 'aiwanowicz2', 'http://dummyimage.com/226x100.png/ff4444/ffffff', '2023-08-27', 'Y', 'Y', 'M', '1995-05-04', null, '2024-07-01');
insert into user (user_email, user_password, user_nickname, user_img, user_date, user_ent_yn, user_spotify_yn, user_gender, user_birth, user_manager, user_enroll_date) values ('ymacmeanma3@gmpg.org', 'Pakistani', 'sduthy3', 'http://dummyimage.com/199x100.png/5fa2dd/ffffff', '2023-11-02', 'Y', 'N', 'M', '1979-04-19', null, '2024-01-08');
insert into user (user_email, user_password, user_nickname, user_img, user_date, user_ent_yn, user_spotify_yn, user_gender, user_birth, user_manager, user_enroll_date) values ('ecaney4@delicious.com', 'Shoshone', 'rknight4', 'http://dummyimage.com/139x100.png/dddddd/000000', '2023-09-23', 'Y', 'N', 'M', '2002-08-21', null, '2024-07-20');
insert into user (user_email, user_password, user_nickname, user_img, user_date, user_ent_yn, user_spotify_yn, user_gender, user_birth, user_manager, user_enroll_date) values ('tnigh5@moonfruit.com', 'Indonesian', 'lhanshaw5', 'http://dummyimage.com/200x100.png/cc0000/ffffff', '2024-07-19', 'N', 'N', 'F', '1982-05-07', null, null);
insert into user (user_email, user_password, user_nickname, user_img, user_date, user_ent_yn, user_spotify_yn, user_gender, user_birth, user_manager, user_enroll_date) values ('kbedle6@sohu.com', 'Choctaw', 'ceskrigg6', 'http://dummyimage.com/176x100.png/cc0000/ffffff', '2023-08-14', 'N', 'Y', 'M', '1999-04-04', null, null);
insert into user (user_email, user_password, user_nickname, user_img, user_date, user_ent_yn, user_spotify_yn, user_gender, user_birth, user_manager, user_enroll_date) values ('ggergher7@t.co', 'Nicaraguan', 'llinguard7', 'http://dummyimage.com/249x100.png/cc0000/ffffff', '2024-05-12', 'N', 'N', 'F', '1963-01-14', null, null);
insert into user (user_email, user_password, user_nickname, user_img, user_date, user_ent_yn, user_spotify_yn, user_gender, user_birth, user_manager, user_enroll_date) values ('rcranham8@cmu.edu', 'Guatemalan', 'mshiels8', 'http://dummyimage.com/172x100.png/ff4444/ffffff', '2024-07-11', 'N', 'N', 'M', '1972-06-15', null, null);
insert into user (user_email, user_password, user_nickname, user_img, user_date, user_ent_yn, user_spotify_yn, user_gender, user_birth, user_manager, user_enroll_date) values ('agrigs9@opensource.org', 'Dominican (Dominican Republic)', 'zgaskal9', 'http://dummyimage.com/221x100.png/cc0000/ffffff', '2024-06-15', 'N', 'Y', 'M', '1971-06-18', null, null);

update user
set user_ent_yn = 'Y'
, user_enroll_date = '2024-07-27'
where user_email = 'test@qqq.com';

select user_email, adddate(user_enroll_date, 7)-current_date() from user where user_enroll_date is not null;

SELECT * FROM information_schema.events;

DELIMITER //
CREATE EVENT user_delete
ON SCHEDULE EVERY 1 day
STARTS '2024-08-02 13:43:00'
DO
begin
    delete from user 
    where user_email IN (
		select user_email
        from user
		where adddate(user_enroll_date, 7) - current_date() <= 0);
END//
DELIMITER ;
    
DROP EVENT user_delete;

select * from user;

SHOW EVENTS;

SET GLOBAL time_zone = '+9:00';

select * 
from user 
where user_email IN (
		select user_email
        from user
		where not adddate(user_enroll_date, 7) - current_date() <= 0
    );

SELECT @@global.time_zone, @@session.time_zone;

select user_email
        from user
		where adddate(user_enroll_date, 7) - current_date() <= 0;

show variables like 'event%';