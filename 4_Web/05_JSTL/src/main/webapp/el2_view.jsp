<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>1) 기존 방식</h2>
	<%=request.getParameter("id") %><br>
	<% String[] hobbyList = request.getParameterValues("hobby"); 
		for(String hobby : hobbyList){ %>
	- <%=hobby %><br>
	<% } %>
	
	<h2>2) EL</h2>
	${param.id }<br>
	- ${paramValues.hobby[0]}<br>
	- ${paramValues.hobby[1]}<br>
	- ${paramValues.hobby[2]}<br>
	- ${paramValues.hobby[3]}<br>
</body>
</html>