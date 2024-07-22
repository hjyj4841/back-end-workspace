package servlet.http;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/form")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String[] menuList = request.getParameterValues("menu");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><body>");
		out.println("<h1>정보를 출력합니다...</h1>");
		out.println("<p>당신의 아이디는 " + request.getParameter("userId") + "</p>");
		out.println("<p>당신의 비밀번호는 " + request.getParameter("userPwd") + "</p>");
		
		if(request.getParameter("gender").equals("M")) 
			out.println("<p>당신의 성별은 남자</p>");
		else out.println("<p>당신의 성별은 여자</p>");
		
		out.println("<ul>");
		if(menuList != null)
			for(String menu : menuList) out.println("<li>" + menu + "</li>");
		out.println("</ul>");
		
		out.println("</html></body>");
		
		out.close();
	}

}
