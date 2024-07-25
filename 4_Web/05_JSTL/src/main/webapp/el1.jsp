<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setAttribute("result", "request");
		session.setAttribute("result2", "session");
		request.getRequestDispatcher("el1_view.jsp").forward(request, response);
	%>
</body>
</html>