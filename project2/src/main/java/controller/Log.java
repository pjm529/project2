package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.menu.LoginDAO;
 
@WebServlet("/log/*")
public class Log extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	LoginDAO loginDAO;

	public void init() throws ServletException {
		loginDAO = new LoginDAO();
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
		
		if (action == null || action.equals("/login")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String checkbox = request.getParameter("checkbox");
			
			String name = loginDAO.login(id, pw, session);
			
			if(name != null) {
				session.setAttribute("id", id);
				session.setAttribute("name", name);
				session.setAttribute("checkbox", checkbox);
				nextPage = "/homepage/logProcess/loginSuccess.jsp";
			} else{
				nextPage = "/homepage/logProcess/loginFail.jsp";
			}
			
		} else if (action.equals("/logout")) {
			
			nextPage = "/homepage/logProcess/logout.jsp";
			
		} else {
			
			nextPage = "/homepage/index.jsp";
			
		}
		
		
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); 
		dispatch.forward(request, response);
	}

}
