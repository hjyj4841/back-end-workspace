<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Form값을 EL로 받아오기</h1>
	<form action="el2_view.jsp" method="post">
		<p>ID : <input type="text" name="id"></p>
		
		<h2>취미</h2>
		<label><input type="checkbox" name="hobby" value="movie">영화</label>
		<label><input type="checkbox" name="hobby" value="game">게임</label>
		<label><input type="checkbox" name="hobby" value="exercise">운동</label>
		<label><input type="checkbox" name="hobby" value="song">노래</label>
		
		<p><input type="submit" value="데이터 전송"></p>
	</form>
</body>
</html>