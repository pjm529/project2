package controller.menu;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.menu.ProcessVO;
import service.menu.ProcessDAO;

@WebServlet("/process/*")
public class ProcessController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ProcessDAO processDAO;

	public void init() throws ServletException {
		processDAO = new ProcessDAO();
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

		if (action == null || action.equals("/listProcess")) { // 최초 요청이거나 listMembers.do일때

			String search_select = request.getParameter("search_select");
			String search_text = request.getParameter("search_text");

			List<ProcessVO> processList = processDAO.listProcesss(search_select, search_text);

			request.setAttribute("processList", processList);
			nextPage = "/homepage/menu/process/process.jsp";

		} else if (action.equals("/addProcess")) {

			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			String writer_id = (String) session.getAttribute("id");

			if (writer_id.equals("admin")) {

				ProcessVO processVO = new ProcessVO(title, content, writer, writer_id);

				processDAO.addProcess(processVO);

				List<ProcessVO> processList = processDAO.listProcesss(null, null);
				request.setAttribute("processList", processList);
				nextPage = "/homepage/menu/process/add_success.jsp";

			} else {

				nextPage = "/error/error404.jsp";

			}

		} else if (action.equals("/processForm")) {

			nextPage = "/homepage/menu/process/insertForm.jsp";

		} else if (action.equals("/viewProcess")) {

			String num = request.getParameter("num");
			ProcessVO processInfo = processDAO.findProcess(num);

			request.setAttribute("processInfo", processInfo);

			nextPage = "/homepage/menu/process/viewProcess.jsp";

		} else if (action.equals("/delProcess")) {

			String num = request.getParameter("num");
			String sessId = (String) session.getAttribute("id");
			String writer_id = request.getParameter("writer_id");

			processDAO.delProcess(num, sessId, writer_id);

			List<ProcessVO> processList = processDAO.listProcesss(null, null);
			request.setAttribute("processList", processList);
			nextPage = "/homepage/menu/process/del_success.jsp";

		} else if (action.equals("/modProcessForm")) {

			String num = request.getParameter("num");
			ProcessVO processInfo = processDAO.findProcess(num);

			request.setAttribute("processInfo", processInfo);
			request.setAttribute("writer_id", processInfo.getWriter_id());

			nextPage = "/homepage/menu/process/updateForm.jsp";

		} else if (action.equals("/modProcess")) {

			String num = request.getParameter("num");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			String writer_id = (String) session.getAttribute("id");

			if (writer_id.equals("admin")) {

				ProcessVO processVO = new ProcessVO(num, title, content, writer, writer_id, null);
				processDAO.modProcess(processVO);

				ProcessVO processInfo = processDAO.findProcess(num);
				request.setAttribute("processInfo", processInfo);
				nextPage = "/homepage/menu/process/viewProcess.jsp";

			} else {

				nextPage = "/error/error404.jsp";

			}

		} else {

			nextPage = "/error/error404.jsp";
		}

		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
