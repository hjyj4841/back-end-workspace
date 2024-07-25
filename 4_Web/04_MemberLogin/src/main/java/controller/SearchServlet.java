package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.MemberDAO;
import model.vo.Member;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberDAO dao = new MemberDAO();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String id = request.getParameter("id");
		
		Member member = null;
		
		try {
			member = dao.searchMember(id);
		} catch (SQLException e) {}
		
		if(member != null) {
			request.setAttribute("member", member);
			request.getRequestDispatcher("/views/search_ok.jsp").forward(request, response);
		}else {
			response.sendRedirect("/views/search_fail.jsp");
		}
	}

}
