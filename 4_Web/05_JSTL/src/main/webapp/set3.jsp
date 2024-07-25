<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	JSTL 사용하려면..
	1. 라이브러리 추가
	2. taglib 속성지정
 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- c:set - 변수 지정 -->
	<c:set var="num1" value="7" scope="request"/>
	<c:set var="num2" value="9"/>
	<c:set var="multiple" value="${num1 * num2 }"/>
	
	<h4>${num1 }과 ${num2 }의 곱은 ${multiple }</h4>
</body>
</html>