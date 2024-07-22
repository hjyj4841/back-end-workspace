package servlet.http;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/word")
public class WordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩 -> 한글처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		System.out.println("word로 요청 받음!");
		String keyword = request.getParameter("keyword");
		System.out.println(keyword);
		
		// 응답!
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1> Client Send Data : " + keyword + "</h1>");
		out.println("</body></html>");
		
		out.close();
	}

}
