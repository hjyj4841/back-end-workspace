<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>검색할 회원의 아이디</h1>
	<form action="/search" method="get">
		<div>아이디 : <input type="text" name="id"></div>
		<input type="submit" value="검색">
	</form>
</body>
</html>