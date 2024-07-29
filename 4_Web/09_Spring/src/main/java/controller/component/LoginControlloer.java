package controller.component;

import com.kh.model.dao.MemberDAO;
import com.kh.model.vo.Member;

import controller.Controller;
import controller.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginControlloer implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Member member = null;
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		MemberDAO dao = new MemberDAO();
		
		member = dao.loginMember(id, password);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("member", member);
		
		return  new ModelAndView("index.jsp", true);
	}
	
	
}
