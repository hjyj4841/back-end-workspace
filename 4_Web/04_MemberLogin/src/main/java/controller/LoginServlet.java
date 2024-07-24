package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.MemberDAO;
import model.vo.Member;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberDAO dao = new MemberDAO();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = null;
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		try {
			member = dao.loginMember(id, password);
		} catch (SQLException e) {}
		
		if(member != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
		} 
		response.sendRedirect("index.jsp");
	}

}
