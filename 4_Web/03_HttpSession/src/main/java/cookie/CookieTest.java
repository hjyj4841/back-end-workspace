package cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.http.Cookie;

@WebServlet("/cookie")
public class CookieTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청이 들어왔을 때
		// 1. 쿠키 생성
		Cookie c1 = new Cookie("id", "test1234");
		Cookie c2 = new Cookie("name", "테스트1234");
		
		// 2. 쿠키의 유효시간 지정
		c1.setMaxAge(20); // 20초
		c2.setMaxAge(60*60*24); // 하루
		
		// 3. 쿠키 브라우저로 전송
		response.addCookie(c1);
		response.addCookie(c2);
		
	}

}
