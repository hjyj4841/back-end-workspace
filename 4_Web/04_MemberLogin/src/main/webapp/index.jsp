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
	<h1>회원 관리 기능</h1>
	
	<ul>
		<% Member member = (Member)session.getAttribute("member");
		if(member == null) { %>
		<li><a href="/views/register.jsp">회원가입</a></li>
		<li><a href="/views/login.jsp">로그인</a></li>
		
		<% } else { %>
		<li><a href="/views/search.jsp">회원검색</a></li>
		<li><a href="/allMember">전체회원보기</a></li>
		<li><a href="/logout">로그아웃</a></li>
		<% } %>
	</ul>
</body>
</html>