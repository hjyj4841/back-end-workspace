<%@page import="com.kh.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% Member member = (Member)request.getAttribute("member"); %>
	<h1>검색한 회원정보</h1>
	<div>아이디 : <%=member.getId() %></div>
	<div>비밀번호 : <%=member.getPassword() %></div>
	<div>이름 : <%=member.getName() %></div>
	<a href="/">홈으로 돌아가기</a>
</body>
</html>