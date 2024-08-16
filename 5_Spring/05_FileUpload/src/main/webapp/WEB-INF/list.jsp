<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
    crossorigin="anonymous" />
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="header">
			<h1>List Page</h1>
			<a href="/write" class="btn btn-outline-warning">게시글 등록</a>
		</div>
		<table class="table">
			<thead>
				<tr>
					<th>#번호</th>
					<th>이미지</th>
					<th>제목</th>
					<th>작성시간</th>
				</tr>
			</thead>
			<tbody>
				<!-- 리스트 가져다가 뿌리기 -->
				<c:forEach items="${list }" var="board">
						<tr class="boardList" data-code="${board.no }">
							<td>${board.no }</td>
							<td><img alt="${board.url }" src="${board.url }" style="width: 100px; height: 100px; object-fit: cover"></td>
							<td>${board.title }</td>
							<td>${board.date }</td>
						</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<script>
		const boardList = document.querySelectorAll(".boardList");
		
		boardList.forEach(board =>{
			board.addEventListener("click", function(){
				alert("!");
				location.href = '/boardDetail?no=' + board.getAttribute("data-code");
			});
		});
	</script>
</body>
</html>