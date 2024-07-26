<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입</h1>
	<form action="/front" method="post">
		<input type="hidden" name="command" value="register">
		<div>아이디 : <input type="text" name="id"></div>
		<div>비밀번호 : <input type="password" name="password"></div>
		<div>이름 : <input type="text" name="name"></div>
		<input type="submit" value="회원가입">
	</form>
</body>
</html>