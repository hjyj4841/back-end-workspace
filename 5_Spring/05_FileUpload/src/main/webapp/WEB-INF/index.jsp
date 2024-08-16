<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="/list">게시판 글 목록</a>

	<h2>File Upload Test</h2>
	<form action="/upload" method="post" enctype="multipart/form-data">
		<input type="file" name="file"><br>
		<input type="submit" value="파일업로드">
	</form>
	
	<h2>Multi File Upload Test</h2>
	<form action="/multiUpload" method="post" enctype="multipart/form-data">
		<input type="file" name="files" multiple accept="image/*"><br>
		<input type="submit" value="파일업로드">
	</form>
</body>
</html>