package controller.menu;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.menu.RecruitDAO;

@WebServlet("/introduce/*")
public class IntroduceController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	RecruitDAO recruitDAO;

	public void init() throws ServletException {
		recruitDAO = new RecruitDAO();
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

		if (action == null || action.equals("/introduce")) { // 최초 요청이거나 listMembers.do일때

			nextPage = "/homepage/menu/introduce/introduce.jsp";

		} else if (action.equals("/introduce2")) {

			nextPage = "/homepage/menu/introduce/introduce2.jsp";

		} else if (action.equals("/introduce3")) {

			nextPage = "/homepage/menu/introduce/introduce3.jsp";

		} else {

			nextPage = "/error/error404.jsp";

		}

		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
