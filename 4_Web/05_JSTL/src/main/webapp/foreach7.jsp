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
	<%
		String[] arr = {"두루치기", "삼계탕", "김치찌개", "삼겹살", "순두부찌개"};
		for(String menu : arr){
			System.out.println(menu);
		}
	%>
	<!-- 이 부분에 대한 설명은 MemberJSTL -->
	<c:forEach items="${arr }" var="menu" varStatus="status">
		- ${menu }<br>
	</c:forEach>
</body>
</html>