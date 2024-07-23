<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- ViewServlet : 전체 회원 보기 관련 비즈니스 로직 구현 --%>
	<a href="/view">전체 회원 보기</a>

	<h1>회원가입</h1>
	<form action="register">
		<label>아이디 : <input type="text" name="id"></label><br>
		<label>비밀번호 : <input type="password" name="pwd"></label><br>
		<label>이름 : <input type="text" name="name"></label><br>
		<input type="submit" value="회원가입">
	</form>
</body>
</html>