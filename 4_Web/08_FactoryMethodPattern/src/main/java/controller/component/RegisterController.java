package controller.component;

import controller.Controller;
import controller.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.MemberDAO;
import model.vo.Member;

/*
 * 컴포넌트
 *  - 인터페이스 기반으로 작성된 재사용성이 강한 자바 클래스
 */
public class RegisterController implements Controller{

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Member member = new Member();
		
		member.setId(request.getParameter("id"));
		member.setPassword(request.getParameter("password"));
		member.setName(request.getParameter("name"));
		
		MemberDAO dao = new MemberDAO();
		
		dao.registerMember(member);
				
		return new ModelAndView("index.jsp", true);
	}

}
