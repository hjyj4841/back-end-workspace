<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
    crossorigin="anonymous" />
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>게시물 정보</h1>
		<form action="/update" method="post" enctype="multipart/form-data">
			<input type="hidden" name="no" value="${board.no }">
			<div>
				<img alt="${board.url }" src="${board.url }" style="width: 100%; height: 500px; object-fit: cover">
			</div>
			<div class="form-group">
				<label>Title</label>
				<input class="form-control" name="title" type="text" value="${board.title }">
			</div>
			<div class="form-group">
				<label>Content</label>
				<textarea class="form-control" name="content" rows="10">${board.content }</textarea>
			</div>
			<!-- 
				img 태그 사용 
				수정시 file이 있다면 기존 파일은 삭제 하고 새로 추가된 파일로 업로드 하고 DB 수정
				삭제시 업로드한 파일도 삭제 (파일 삭제 : File 객체의 delete() 메서드 사용)
			-->
			<div class="form-group">
				<label>Add File</label>
				<input class="form-control" name="file" type="file" accept="image/*">
			</div>
			<button type="submit" class="btn btn-outline-warning">수정</button>
			<a class="btn btn-outline-danger" href="/delete?no=${board.no }">삭제</a>
			<a class="btn btn-outline-info" href="/list">목록</a>
		</form>
	</div>
</body>
</html>