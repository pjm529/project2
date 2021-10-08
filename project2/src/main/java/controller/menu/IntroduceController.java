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

import model.menu.RecruitVO;
import service.menu.RecruitDAO;

@WebServlet("/recruit/*")
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

	private void doHandle(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String action = request.getPathInfo(); // URL에서 요청명을 가져옴.
		
		if(action == null || action.equals("/listRecruit")) { // 최초 요청이거나 listMembers.do일때
			
			 String search_select = request.getParameter("search_select");
			 String search_text = request.getParameter("search_text");
			
			List<RecruitVO> recruitList = recruitDAO.listRecruits(search_select, search_text);
			
			request.setAttribute("recruitList", recruitList);
			nextPage = "/homepage/menu/recruit/recruit.jsp";
			
		} else if(action.equals("/addRecruit")) {
				
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			String writer_id = (String)session.getAttribute("id");
			String training_period = request.getParameter("training_period");
			String recruit_period = request.getParameter("recruit_period");
			String time = request.getParameter("time");
			String count = request.getParameter("count");
			String location = request.getParameter("location");
			String professor = request.getParameter("professor");
			
			RecruitVO recruitVO = new RecruitVO(title, content, writer, writer_id, training_period, recruit_period
					, time, count, location, professor);
			
			recruitDAO.addRecruit(recruitVO);
			
			List<RecruitVO> recruitList = recruitDAO.listRecruits(null, null);
			request.setAttribute("recruitList", recruitList);
			nextPage = "/homepage/menu/recruit/recruit.jsp";
			
		} else if(action.equals("/recruitForm")) {
			
			nextPage = "/homepage/menu/recruit/insertForm.jsp";
			
		} else if(action.equals("/viewRecruit")) {
			
			String num = request.getParameter("num");
			RecruitVO recruitInfo = recruitDAO.findRecruit(num);
			
			request.setAttribute("recruitInfo", recruitInfo);
			
			nextPage = "/homepage/menu/recruit/viewRecruit.jsp";
			
		} else if(action.equals("/delRecruit")) {
			
			String num = request.getParameter("num");
			String sessId = (String)session.getAttribute("id");
			String writer_id = request.getParameter("writer_id");
			
			recruitDAO.delRecruit(num, sessId, writer_id);
			
			List<RecruitVO> recruitList = recruitDAO.listRecruits(null, null);
			request.setAttribute("recruitList", recruitList);
			nextPage = "/homepage/menu/recruit/recruit.jsp";

		} else if(action.equals("/modRecruitForm")) {
			
			String num = request.getParameter("num");
			RecruitVO recruitInfo = recruitDAO.findRecruit(num);
			
			request.setAttribute("recruitInfo", recruitInfo);
			request.setAttribute("writer_id", recruitInfo.getWriter_id());
			
			nextPage = "/homepage/menu/recruit/updateForm.jsp";
			
		} else if(action.equals("/modRecruit")) {
			
			String num = request.getParameter("num");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			String writer_id = (String)session.getAttribute("id");
			String training_period = request.getParameter("training_period");
			String recruit_period = request.getParameter("recruit_period");
			String time = request.getParameter("time");
			String count = request.getParameter("count");
			String location = request.getParameter("location");
			String professor = request.getParameter("professor");
			
			RecruitVO recruitVO = new RecruitVO(num, title, content, writer, writer_id, training_period, recruit_period
					, time, count, location, professor, null);
			
			recruitDAO.modRecruit(recruitVO);
			
			RecruitVO recruitInfo = recruitDAO.findRecruit(num);
			request.setAttribute("recruitInfo", recruitInfo);
			nextPage = "/homepage/menu/recruit/viewRecruit.jsp";
	
		} else {
				
			nextPage = "/error/error404.jsp";
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); 
		dispatch.forward(request, response);
	}

}
