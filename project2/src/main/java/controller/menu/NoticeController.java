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

import model.menu.NoticeVO;
import service.menu.NoticeDAO;

@WebServlet("/notice/*")
public class NoticeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	NoticeDAO noticeDAO;

	public void init() throws ServletException {
		noticeDAO = new NoticeDAO();
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
		
		if(action == null || action.equals("/listNotice")) { // 최초 요청이거나 listMembers.do일때
			
			 String search_select = request.getParameter("search_select");
			 String search_text = request.getParameter("search_text");
			
			List<NoticeVO> noticeList = noticeDAO.listNotices(search_select, search_text);
			
			request.setAttribute("noticeList", noticeList);
			nextPage = "/homepage/menu/notice/notice.jsp";
			
		} else if(action.equals("/addNotice")) {
				
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			String writer_id = (String)session.getAttribute("id");
			
			NoticeVO noticeVO = new NoticeVO(title, content, writer, writer_id);
			
			noticeDAO.addNotice(noticeVO);
			
			List<NoticeVO> noticeList = noticeDAO.listNotices(null, null);
			request.setAttribute("noticeList", noticeList);
			nextPage = "/homepage/menu/notice/notice.jsp";
			
		} else if(action.equals("/noticeForm")) {
			
			nextPage = "/homepage/menu/notice/insertForm.jsp";
			
		} else if(action.equals("/viewNotice")) {
			
			String num = request.getParameter("num");
			NoticeVO noticeInfo = noticeDAO.findNotice(num);
			
			request.setAttribute("noticeInfo", noticeInfo);
			
			nextPage = "/homepage/menu/notice/viewNotice.jsp";
			
		} else if(action.equals("/delNotice")) {
			
			String num = request.getParameter("num");
			String sessId = (String)session.getAttribute("id");
			String writer_id = request.getParameter("writer_id");
			
			noticeDAO.delNotice(num, sessId, writer_id);
			
			List<NoticeVO> noticeList = noticeDAO.listNotices(null, null);
			request.setAttribute("noticeList", noticeList);
			nextPage = "/homepage/menu/notice/notice.jsp";

		} else if(action.equals("/modNoticeForm")) {
			
			String num = request.getParameter("num");
			NoticeVO noticeInfo = noticeDAO.findNotice(num);
			
			request.setAttribute("noticeInfo", noticeInfo);
			request.setAttribute("writer_id", noticeInfo.getWriter_id());
			
			nextPage = "/homepage/menu/notice/updateForm.jsp";
			
		} else if(action.equals("/modNotice")) {
			
			String num = request.getParameter("num");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			String writer_id = request.getParameter("writer_id");
			
			NoticeVO noticeVO = new NoticeVO(num, title, content, writer, writer_id, null);
			noticeDAO.modNotice(noticeVO);
			
			NoticeVO noticeInfo = noticeDAO.findNotice(num);
			request.setAttribute("noticeInfo", noticeInfo);
			nextPage = "/homepage/menu/notice/viewNotice.jsp";
	
		} else {
				
			nextPage = "/homepage/index.jsp";
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); 
		dispatch.forward(request, response);
	}

}
