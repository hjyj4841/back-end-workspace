package com.kh.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.kh.model.dao.MemberDAO;
import com.kh.model.vo.Member;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberDAO dao = new MemberDAO();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member member = null;
		
		try {
			member = dao.searchMember(request.getParameter("id"));
		} catch (SQLException e) {
		}
		
		request.setAttribute("member", member);
		request.getRequestDispatcher("search.jsp").forward(request, response);
		
	}

}
