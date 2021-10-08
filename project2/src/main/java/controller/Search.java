package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberVO;
import service.SearchIdDAO;

@WebServlet("/search/*")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SearchIdDAO dao;

	public void init() throws ServletException {
		dao = new SearchIdDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = null;

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String action = request.getPathInfo(); // URL에서 요청명을 가져옴.

		if (action.equals("/_id")) {

			nextPage = "/homepage/search_id_pw/search_id/search_id.jsp";

		} else if (action.equals("/id")) {

			String name = request.getParameter("name");
			String phone = request.getParameter("phone");

			String id = dao.searchId(name, phone);

			if (id == null) {
				nextPage = "/homepage/search_id_pw/search_id/search_fail.jsp";
			} else {
				request.setAttribute("id", id);
				nextPage = "/homepage/search_id_pw/search_id/search_success.jsp";
			}

		} else if (action.equals("/_pw")) {

			nextPage = "/homepage/search_id_pw/search_pw/search_pw.jsp";

		} else if (action.equals("/pw")) {

			String result = null;

			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String email_domain = request.getParameter("email_domain");

			MemberVO member = new MemberVO(id, name, phone, email, email_domain);

			result = dao.searchPw(member);

			if (result == null) {

				nextPage = "/homepage/search_id_pw/search_pw/search_fail.jsp";
			} else {
				request.setAttribute("memInfo", member);
				nextPage = "/homepage/search_id_pw/search_pw/update_pw_form.jsp";
			}

		} else if (action.equals("/modPw")) {

			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String email_domain = request.getParameter("email_domain");

			MemberVO member = new MemberVO(id, name, phone, email, email_domain);

			dao.modPw(member, pw);

			nextPage = "/homepage/index.jsp";

		} else {
			nextPage = "/error/error404.jsp";
		}

		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
