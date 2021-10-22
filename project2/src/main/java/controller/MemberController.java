package controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberVO;
import service.MemberDAO;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	MemberDAO memberDAO;

	public void init() throws ServletException {
		memberDAO = new MemberDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		doHandle(request, response, session);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		doHandle(request, response, session);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws ServletException, IOException {

		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String action = request.getPathInfo(); // URL에서 요청명을 가져옴.

		if (action == null || action.equals("/listMembers")) { // 최초 요청이거나 listMembers일때
			
			String search_select = request.getParameter("search_select");
			String search_text = request.getParameter("search_text");

			List<MemberVO> membersList = memberDAO.listMembers(search_select, search_text);
			
			request.setAttribute("membersList", membersList);
			nextPage = "/homepage/user/viewList/viewUserList.jsp";

		} else if (action.equals("/addMember")) {

			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String email_domain = request.getParameter("email_domain");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String day = request.getParameter("day");
			String gender = request.getParameter("gender");

			MemberVO memberVO = new MemberVO(id, pw, name, phone, email, email_domain, year, month, day, gender);
			memberDAO.addMember(memberVO);

			nextPage = "/homepage/index.jsp";

		} else if (action.equals("/memberForm")) {

			nextPage = "/homepage/user/insert/insertForm.jsp";

		} else if (action.equals("/viewMember")) {

			String num = request.getParameter("num");
			MemberVO memInfo = memberDAO.findMember(num);

			request.setAttribute("memInfo", memInfo);

			nextPage = "/homepage/user/viewList/viewUser.jsp";

		} else if (action.equals("/viewMy")) {

			String num = request.getParameter("num");
			MemberVO memInfo = memberDAO.findMember(num);

			request.setAttribute("memInfo", memInfo);

			nextPage = "/homepage/user/viewList/viewMy.jsp";

		} else if (action.equals("/modMemberForm")) {

			String num = request.getParameter("num");
			MemberVO memInfo = memberDAO.findMember(num);

			request.setAttribute("memInfo", memInfo);

			nextPage = "/homepage/user/update/updateForm.jsp";

		} else if (action.equals("/modMember")) {

			String num = request.getParameter("num");
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String email_domain = request.getParameter("email_domain");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String day = request.getParameter("day");
			String gender = request.getParameter("gender");
			
			MemberVO memberVO = new MemberVO(num, id, pw, name, phone, email, email_domain, year, month, day, gender);
			memberDAO.modMember(memberVO);

			nextPage = "/member/listMembers";

		} else if (action.equals("/modMemberFormMy")) {

			String num = request.getParameter("num");
			MemberVO memInfo = memberDAO.findMember(num);

			request.setAttribute("memInfo", memInfo);

			nextPage = "/homepage/user/update/updateFormMy.jsp";

		} else if (action.equals("/modMemberMy")) {

			String num = request.getParameter("num");
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String email_domain = request.getParameter("email_domain");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String day = request.getParameter("day");
			String gender = request.getParameter("gender");

			MemberVO memberVO = new MemberVO(num, id, pw, name, phone, email, email_domain, year, month, day, gender);
			memberDAO.modMemberMy(memberVO);

			session.setAttribute("name", name);
			nextPage = "/homepage/index.jsp";

		} else if (action.equals("/delMember")) {

			String num = request.getParameter("num");
			String sessId = request.getParameter("sessId");
			String sessNum = request.getParameter("sessNum");
			memberDAO.delMember(num, sessId, sessNum);

			nextPage = "/homepage/user/delete/del_success.jsp";

		} else if (action.equals("/delMy")) {

			String num = request.getParameter("num");
			String sessId = request.getParameter("sessId");
			String sessNum = request.getParameter("sessNum");
			memberDAO.delMember(num, sessId, sessNum);

			session.invalidate();
			nextPage = "/homepage/index.jsp";

		} else {

			nextPage = "/error/error404.jsp";
		}

		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
