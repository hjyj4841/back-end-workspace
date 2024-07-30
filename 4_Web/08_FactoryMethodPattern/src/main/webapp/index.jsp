<%@page import="model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원 관리 기능</h1>
	
	<ul>
	<c:choose>
	<c:when test="${empty member}">
		<li><a href="/views/register.jsp">회원가입</a></li>
		<li><a href="/views/login.jsp">로그인</a></li>
	</c:when>
	<c:otherwise>
		<li><a href="/views/search.jsp">회원검색</a></li>
		<li><a href="/allMember.do">전체회원보기</a></li>
		<li><a href="/logout.do">로그아웃</a></li>
	</c:otherwise>
	</c:choose>
	</ul>
</body>
</html>