-- sakila, 정렬은 결과 화면대로

-- 1. actor 테이블에서 first_name이 A로 시작하는 배우들만 조회 
SELECT *
FROM ACTOR
WHERE FIRST_NAME LIKE 'A%';

-- 2. film 테이블에서 rating이 PG면서 영화 제목(title)에 GO가 포함되는 영화 제목 조회
SELECT TITLE
FROM FILM_LIST
WHERE CATEGORY IN ('Sci-Fi', 'Family')
	AND RATING = 'PG'
 	AND TITLE LIKE '%GO%';

-- 3. film 테이블에서 film_id가 7 이하면서 4, 6은 아닌 film_id, title 조회
SELECT FID, TITLE
FROM FILM_LIST
WHERE FID < 7
	AND FID NOT IN (4, 6);

-- 4. film 테이블에서 가격(replacement_cost)은 10 이상 15 이하이면서 
-- special_features 중 Trailers가 포함되어 있는 영화 제목(title)만 조회 
SELECT TITLE
FROM FILM_LIST
WHERE PRICE BETWEEN 2 AND 4
	AND CATEGORY IN ('Documentary', 'Animation')
    AND ACTORS LIKE '%BOB%';

-- 5. address 테이블에서 거리(district)가 A로 시작하는 주소(address)만 앞에 숫자 제외 주소만 10개 조회 
SELECT 
	SUBSTR(ADDRESS, INSTR(ADDRESS, " ")) ADDRESS,
	DISTRICT " "
FROM ADDRESS
WHERE NOT DISTRICT = ""
ORDER BY DISTRICT, ADDRESS DESC
LIMIT 0, 10;

-- 6. customer 테이블에서 id가 6인 사람부터 10명 조회
SELECT ID, NAME
FROM CUSTOMER_LIST
ORDER BY ID
LIMIT 5, 10;

-- 7. actor 테이블에서 first_name이 J로 시작하는 사람의 last_name의 글자수 조회 (공백 X, 정렬은 글자수가 많은 사람 순으로)
SELECT 
	CONCAT(FIRST_NAME, " ", LAST_NAME) 이름,
	LENGTH(CONCAT(FIRST_NAME, LAST_NAME)) 글자수
FROM ACTOR
WHERE FIRST_NAME LIKE 'J%'
ORDER BY 글자수 DESC
LIMIT 0, 10;

-- 8. film 테이블에서 description에서 of 이전 문장만 중복 없이 10개만 추출해서 조회
SELECT DISTINCT SUBSTR(DESCRIPTION, 1, INSTR(DESCRIPTION, 'of')-2) "of 이전 문장"
FROM FILM
ORDER BY 1 DESC
LIMIT 0, 10;

-- 9. film 테이블에서 replacement_cost 최소 비용과 최대 비용 조회
SELECT MIN(REPLACEMENT_COST) "최소 비용", MAX(REPLACEMENT_COST) "최대 비용"
FROM FILM;