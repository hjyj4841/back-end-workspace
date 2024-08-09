<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입</h1>
	
	<form id="frm">
		아이디 : <input type="text" name="id" id="id"><br>
		비밀번호 : <input type="password" name="password" id="password"><br>
		이름 : <input type="text" name="name" id="name"><br>
		<input type="button" value="가입" id="btn">
	</form>
	
	<div id="result"></div>
	
	<script>
		$("#btn").click(() => {
			$.ajax({
				// post 방식으로
				type: "post",
				// /serial
				url: "/serial",
				// id, password, name 값 넘겨서 회원가입 하고
				data: $("#frm").serialize(),
				// 돌아와서 result에 정보 기재
				success: function(member){
					alert("회원가입 완료!");
					$("#result").html("아이디 : " + member.id 
							+ "<br> 비밀번호 : "+member.password 
							+ "<br> 이름 : "+member.name);
				},
				error: function(){
					alert("회원가입 실패!");
				}
			});
		});
	</script>
</body>
</html>