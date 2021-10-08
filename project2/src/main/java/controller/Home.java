package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.HomeVO;
import model.MemberVO;
import service.HomeDAO;
import service.MemberDAO;


 
@WebServlet("/index")
public class Home extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	HomeDAO homeDAO;

	public void init() throws ServletException {
		homeDAO = new HomeDAO();
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
		
		List<HomeVO> adList = homeDAO.list("ad");
		request.setAttribute("adList", adList);
		
		List<HomeVO> noticeList = homeDAO.list("notice");
		request.setAttribute("noticeList", noticeList);
		
		List<HomeVO> boardList = homeDAO.list("board");
		request.setAttribute("boardList", boardList);
		
		List<HomeVO> processList = homeDAO.list("process");
		request.setAttribute("processList", processList);
		
		List<HomeVO> recruitList = homeDAO.list("recruit");
		request.setAttribute("recruitList", recruitList);
		
		List<HomeVO> enterList = homeDAO.list("enter");
		request.setAttribute("enterList", enterList);
		
		nextPage = "/homepage/index2.jsp";
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); 
		dispatch.forward(request, response);
	}

}
