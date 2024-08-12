let idSubmit = false;
let pwdSubmit = false;

id.addEventListener("input", function(e){
	// 시작은 영어로, 영어, 숫자로만 10~14글자 -> 정규 표현식!
	const regExp = /^[a-zA-Z][a-zA-Z0-9]{9,13}$/;

	// 중복체크
	$.ajax({
		type: "post",
		url: "/check",
		data: {
			id: e.target.value
		},
		success: function(data){
			if(data){
				// 정보가 있을 때니까 중복된 아이디입니다!
				idCheck.innerHTML = "중복된 아이디입니다. 다시 입력해주세요 ㅡㅡ";
				idCheck.style.color = "red";
				idSubmit = false;
			}else{
				if(regExp.test(e.target.value)){
					idCheck.style.color = "green";
					idCheck.innerHTML = "OK!";
					idSubmit = true;
				}else if(e.target.value === ""){
					idCheck.innerHTML = "";
					idSubmit = false;
				}else{
					idCheck.style.color = "red";
					idCheck.innerHTML = "첫 글자는 영어로, 영어 또는 숫자로만 10~14글자로 입력하세요"
					idSubmit = false;
				} 
			}
			
		}
	});
});

password.addEventListener("input", function(e){
	// 특수문자 무조건 하나 이상 포함
	// 대문자, 소문자 하나씩 전부다 포함이 되어 있는 경우
	// 숫자도 하나씩 포함되어있는 경우
	const regExp = /^(?=.*[!@#$%^&*(),.?":{}|<>])(?=.*[A-Z])(?=.*[a-z])(?=.*\d).+$/;
	if(regExp.test(e.target.value)){
		pwdCheck.innerHTML = "OK";
		pwdCheck.style.color = "green";
		pwdSubmit = true;
	} else if(e.target.value === ""){
		pwdCheck.innerHTML = "";
		pwdSubmit = false;
	} 
	else{
		pwdCheck.innerHTML = "특수문자, 대문자, 소문자, 숫자 한 개 이상 무조건 포함되어야 합니다.";
		pwdCheck.style.color = "red";
		pwdSubmit = false;
	}
});

function validate(){
	if(!idSubmit) id.focus();
	else if(!pwdSubmit) password.focus();
	return idSubmit && pwdSubmit;
}