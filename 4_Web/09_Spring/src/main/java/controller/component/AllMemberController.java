package controller.component;

import java.util.ArrayList;

import com.kh.model.dao.MemberDAO;
import com.kh.model.vo.Member;

import controller.Controller;
import controller.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AllMemberController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Member> list = null;
		
		MemberDAO dao = new MemberDAO();
		
		list = dao.allSearchMember();
		
		request.setAttribute("list", list);
		return new ModelAndView("/views/allMember.jsp");
	}

}
