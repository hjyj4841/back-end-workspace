-- Sakila : DVD 대여 및 영화 정보와 관련된 데이터 포함

SELECT * FROM category; -- 영화 카테고리 정보 : category_id
SELECT * FROM rental; -- DVD 대여 정보 : customer_id, inventory_id
SELECT * FROM inventory; -- DVD 대여점에서 관리하는 정보 : inventory_id, film_id
SELECT * FROM film; -- 영화 정보 : film_id
SELECT * FROM actor; -- 영화 배우 정보 : actor_id

SELECT * FROM film_category; -- film과 category 연결 : film_id, category_id
SELECT * FROM film_actor; -- film과 actor 연결 : film_id, actor_id

-- DVD 대여 고객 정보 : customer_id, address_id, first_name, last_name
SELECT * FROM customer; 

SELECT * FROM address; -- 고객 주소 정보 : address_id, city_id, address, district
SELECT * FROM city; -- city_id, country_id, city
SELECT * FROM country; -- country_id, country

-- 1. customer 테이블의 first_name이 TRACY인 사람들 조회 
SELECT * FROM customer; -- FIRST_NAME, LAST_NAME, 	ADDRESS_ID
SELECT * FROM address; -- ADDRESS, 					ADDRESS_ID, CITY_ID
SELECT * FROM city; -- CITY							CITY_ID, COUNTRY_ID
SELECT * FROM country; -- COUNTRY					COUNTRY_ID

SELECT COUNTRY, CITY, ADDRESS, DISTRICT, FIRST_NAME, LAST_NAME
FROM CUSTOMER
	JOIN ADDRESS USING (ADDRESS_ID)
    JOIN CITY USING (CITY_ID)
    JOIN COUNTRY USING (COUNTRY_ID)
WHERE FIRST_NAME = 'TRACY';

-- 2. 배우 JULIA MCQUEEN이 찍은 영화 제목 조회 (title 기준 정렬 10개까지)
SELECT * FROM actor; -- FIRST_NAME, LAST_NAME, 		ACTOR_ID
SELECT * FROM film_actor; -- 						ACTOR_ID, FILM_ID
SELECT * FROM film; -- TITLE 						FILM_ID

SELECT FIRST_NAME, LAST_NAME, TITLE
FROM ACTOR
	JOIN FILM_ACTOR USING (ACTOR_ID)
    JOIN FILM USING (FILM_ID)
WHERE FIRST_NAME = 'JULIA'
	AND LAST_NAME = 'MCQUEEN'
ORDER BY TITLE
LIMIT 10;

-- 3. 영화 NOON PAPI에 나오는 배우들의 이름 조회
SELECT * FROM actor; -- FIRST_NAME, LAST_NAME, 		ACTOR_ID
SELECT * FROM film_actor; -- 						ACTOR_ID, FILM_ID
SELECT * FROM film; -- TITLE 						FILM_ID

SELECT FIRST_NAME, LAST_NAME
FROM FILM
	JOIN FILM_ACTOR USING (FILM_ID)
    JOIN ACTOR USING (ACTOR_ID)
WHERE TITLE = 'NOON PAPI';

-- 4. 각 카테고리별 이메일이 JOYCE.EDWARDS@sakilacustomer.org인 고객이 빌린 DVD 대여 수 조회
SELECT * FROM category; -- NAME <- CATEGORY 컬럼								CATEGORY_ID
SELECT * FROM film_category; --  									FILM_ID, CATEGORY_ID
SELECT * FROM inventory; -- 						 				INVENTORY_ID, FILM_ID
SELECT * FROM rental; -- 			 								CUSTOMER_ID, INVENTORY_ID
SELECT * FROM customer; -- EMAIL									CUSTOMER_ID

SELECT NAME 'CATEGORY', COUNT(RENTAL.CUSTOMER_ID) 'COUNT'
FROM CATEGORY
	JOIN FILM_CATEGORY USING (CATEGORY_ID)
    JOIN INVENTORY USING (FILM_ID)
    JOIN RENTAL USING (INVENTORY_ID)
    JOIN CUSTOMER USING (CUSTOMER_ID)
WHERE EMAIL = 'JOYCE.EDWARDS@SAKILACUSTOMER.ORG'
GROUP BY NAME;

-- 5. 이메일이 JOYCE.EDWARDS@sakilacustomer.org인 고객이 가장 최근에 빌린 영화 제목과 영화 내용을 조회 
SELECT * FROM customer; -- EMAIL				CUSTOMER_ID, STORE_ID
SELECT * FROM rental; -- RENTAL_DATE			RENTAL_ID, INVENTORY_ID, CUSTOMER_ID
SELECT * FROM inventory; -- 					INVENTORY_ID, FILM_ID, STORE_ID
SELECT * FROM film; -- TITLE, DESCRIPTION		FILM_ID

SELECT TITLE, DESCRIPTION
FROM CUSTOMER
	JOIN RENTAL USING (CUSTOMER_ID)
	JOIN INVENTORY USING (INVENTORY_ID)
    JOIN FILM USING (FILM_ID)
WHERE (RENTAL_DATE, EMAIL) = (
		SELECT MAX(RENTAL_DATE), EMAIL -- 2005.08.21>MAX
		FROM CUSTOMER
			JOIN RENTAL USING (CUSTOMER_ID)
		WHERE EMAIL = 'JOYCE.EDWARDS@SAKILACUSTOMER.ORG');