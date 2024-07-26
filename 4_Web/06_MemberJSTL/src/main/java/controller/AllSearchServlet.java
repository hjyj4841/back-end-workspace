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
import java.util.ArrayList;

@WebServlet("/allMember")
public class AllSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberDAO dao = new MemberDAO();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Member> list = null;
		
		try {
			list = dao.allSearchMember();
		} catch (SQLException e) {}
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/allMember.jsp").forward(request, response);
	}

}
