package controller.component;

import controller.Controller;
import controller.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.MemberDAO;
import model.vo.Member;

public class SearchController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		Member member = null;
		
		MemberDAO dao = new MemberDAO();
		
		member = dao.searchMember(id);
		
		if(member != null) {
			request.setAttribute("member", member);
			return new ModelAndView("/views/search_ok.jsp");
		}else {
			return new ModelAndView("/views/search_fail.jsp", true);
		}
	}

}
