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

import encoding.SHA256;
import model.menu.EnterCommentVO;
import model.menu.EnterVO;
import service.menu.EnterCommentDAO;
import service.menu.EnterDAO;

@WebServlet("/enter/*")
public class EnterController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	EnterCommentDAO enterCommentDAO;
	EnterDAO enterDAO;

	public void init() throws ServletException {
		enterDAO = new EnterDAO();
		enterCommentDAO = new EnterCommentDAO();
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
		
		if(action == null || action.equals("/listEnter")) { // 최초 요청이거나 listMembers.do일때
			
			 String search_select = request.getParameter("search_select");
			 String search_text = request.getParameter("search_text");
			
			List<EnterVO> enterList = enterDAO.listEnters(search_select, search_text);
			
			request.setAttribute("enterList", enterList);
			nextPage = "/homepage/menu/enter/enter.jsp";
			
		} else if(action.equals("/addEnter")) {
				
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			String writer_id = request.getParameter("writer_id");
			String pw = request.getParameter("pw");
			
			String hashpw = SHA256.encodeSha256(pw);
			EnterVO enterVO = new EnterVO(title, content, writer, writer_id, hashpw);
			
			enterDAO.addEnter(enterVO);
			
			List<EnterVO> enterList = enterDAO.listEnters(null, null);
			request.setAttribute("enterList", enterList);
			nextPage = "/homepage/menu/enter/enter.jsp";
			 
		} else if(action.equals("/enterForm")) {
			
			nextPage = "/homepage/menu/enter/insertForm.jsp";
			
		} else if(action.equals("/view")) {
			
			String num = request.getParameter("num");
			nextPage = "/homepage/menu/enter/view.jsp?num="+num;
			
		} else if(action.equals("/logInEnter")) {
			
			String sessId = (String)session.getAttribute("id");
			String num = request.getParameter("num");
			String pw_text = request.getParameter("pw_text");
			
			String result = enterDAO.logInEnter(sessId, num, pw_text);
			
			if(result.equals("true")) {
				session.setAttribute("pw_text", pw_text);
				nextPage = "/enter/viewEnter?num=" + num;
			} else {
				nextPage = "/homepage/menu/enter/view_false.jsp?num="+num;
			}
			
			
		} else if(action.equals("/viewEnter")) {
			
			String sessId = (String)session.getAttribute("id");
			String num = request.getParameter("num");
			String pw_text = (String)session.getAttribute("pw_text");
			EnterVO enterInfo = enterDAO.findEnter(num, pw_text);
			
			
			List<EnterCommentVO> commentList = enterDAO.listComments(num);
			
			request.setAttribute("enterInfo", enterInfo);
			request.setAttribute("commentList", commentList);
			session.setAttribute("hashpw", SHA256.encodeSha256(pw_text));
			session.setAttribute("enterPw", enterInfo.getPw());
			
			nextPage = "/homepage/menu/enter/viewEnter.jsp";
			
		} else if(action.equals("/delEnter")) {
			
			String num = request.getParameter("num");
			String sessId = (String)session.getAttribute("id");
			String writer_id = request.getParameter("writer_id");
			
			enterDAO.delEnter(num, sessId, writer_id);
			
			List<EnterVO> enterList = enterDAO.listEnters(null, null);
			request.setAttribute("enterList", enterList);
			nextPage = "/homepage/menu/enter/enter.jsp";

		} else if(action.equals("/modEnterForm")) {
			
			String num = request.getParameter("num");
			String pw_text = (String)session.getAttribute("pw_text");
			EnterVO enterInfo = enterDAO.findEnter(num, pw_text);
			
			request.setAttribute("enterInfo", enterInfo);
			request.setAttribute("writer_id", enterInfo.getWriter_id());
			
			nextPage = "/homepage/menu/enter/updateForm.jsp";
			
		} else if(action.equals("/modEnter")) {
			
			String num = request.getParameter("num");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			String writer_id = request.getParameter("writer_id");
			
			EnterVO enterVO = new EnterVO(num, title, content, writer, writer_id, null);
			enterDAO.modEnter(enterVO);
			String pw_text = (String)session.getAttribute("pw_text");
			EnterVO enterInfo = enterDAO.findEnter(num, pw_text);
			request.setAttribute("enterInfo", enterInfo);
			nextPage = "/homepage/menu/enter/viewEnter.jsp";
	
		} else if(action.equals("/addComment")) {
			
			String writer_id = request.getParameter("writer_id");
			String writer = request.getParameter("writer");
			String comment = request.getParameter("comment_text");
			String enter_no = request.getParameter("enter_no");
			
			
			EnterCommentVO enterCommentVO = new EnterCommentVO(null, comment, writer, writer_id, enter_no, null);
			
			enterCommentDAO.addComment(enterCommentVO);
			
			nextPage = "/enter/viewEnter?num=" + enter_no;
		
		}  else if(action.equals("/delComment")) {
			
			String enter_no = request.getParameter("enter_no");
			String sessId = request.getParameter("sessId");
			String num = request.getParameter("num");
			String comment_writer_id = request.getParameter("comment_writer_id");
			
			enterCommentDAO.delComment(num, sessId, comment_writer_id);
			
			nextPage = "/enter/viewEnter?num=" + enter_no;

	}else {
				
			nextPage = "/homepage/index.jsp";
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); 
		dispatch.forward(request, response);
	}

}
