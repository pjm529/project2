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

@WebServlet("/game/*")
public class GameController extends HttpServlet {

	private static final long serialVersionUID = 1L;

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

		if (action == null || action.equals("/race")) { // 최초 요청이거나 listMembers.do일때

			nextPage = "/homepage/game/race.html?#";

		} else if (action.equals("/rsp")) {

			nextPage = "/homepage/game/rsp.html";

		} else if (action.equals("/worldcup")) {

			nextPage = "/homepage/game/worldcup.html";

		} else {

			nextPage = "/error/error404.jsp";

		}

		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
