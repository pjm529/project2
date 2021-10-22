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

import model.menu.BoardCommentVO;
import model.menu.BoardVO;
import service.menu.BoardCommentDAO;
import service.menu.BoardDAO;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	BoardCommentDAO boardCommentDAO;
	BoardDAO boardDAO;

	public void init() throws ServletException {
		boardDAO = new BoardDAO();
		boardCommentDAO = new BoardCommentDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		doHandle(request, response, session);;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		doHandle(request, response, session);;
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response, HttpSession session) 
			throws ServletException, IOException {
		
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String action = request.getPathInfo(); // URL에서 요청명을 가져옴.
		
		if(action == null || action.equals("/listBoard")) { // 최초 요청이거나 listMembers.do일때
			
			 String search_select = request.getParameter("search_select");
			 String search_text = request.getParameter("search_text");
			
			List<BoardVO> boardList = boardDAO.listBoards(search_select, search_text);
			
			request.setAttribute("boardList", boardList);
			nextPage = "/homepage/menu/board/board.jsp";
			
		} else if(action.equals("/addBoard")) {
				
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			String writer_id = request.getParameter("writer_id");
			
			title = XssReplace(title);
			content = XssReplace(content);
			writer = XssReplace(writer);
			writer_id = XssReplace(writer_id);
			
			BoardVO boardVO = new BoardVO(title, content, writer, writer_id);
			
			boardDAO.addBoard(boardVO);
			
			List<BoardVO> boardList = boardDAO.listBoards(null, null);
			request.setAttribute("boardList", boardList);
			nextPage = "/homepage/menu/board/add_success.jsp";
			
		} else if(action.equals("/boardForm")) {
			
			nextPage = "/homepage/menu/board/insertForm.jsp";
			
		} else if(action.equals("/viewBoard")) {
			
			String num = request.getParameter("num");
			BoardVO boardInfo = boardDAO.findBoard(num);
			
			List<BoardCommentVO> commentList = boardDAO.listComments(num);
			
			
			request.setAttribute("boardInfo", boardInfo);
			request.setAttribute("commentList", commentList);
			
			nextPage = "/homepage/menu/board/viewBoard.jsp";
			
		} else if(action.equals("/viewBoardFail")) {
			
			String num = request.getParameter("num");
			BoardVO boardInfo = boardDAO.findBoard(num);
			
			List<BoardCommentVO> commentList = boardDAO.listComments(num);
			
			
			request.setAttribute("boardInfo", boardInfo);
			request.setAttribute("commentList", commentList);
			
			nextPage = "/homepage/menu/board/viewBoard_fail.jsp";
			
		} else if(action.equals("/delBoard")) {
			
			String num = request.getParameter("num");
			String sessId = (String)session.getAttribute("id");
			String writer_id = request.getParameter("writer_id");
			
			boardDAO.delBoard(num, sessId, writer_id);
			
			List<BoardVO> boardList = boardDAO.listBoards(null, null);
			request.setAttribute("boardList", boardList);
			nextPage = "/homepage/menu/board/board.jsp";

		} else if(action.equals("/modBoardForm")) {
			
			String num = request.getParameter("num");
			BoardVO boardInfo = boardDAO.findBoard(num);
			
			request.setAttribute("boardInfo", boardInfo);
			request.setAttribute("writer_id", boardInfo.getWriter_id());
			
			nextPage = "/homepage/menu/board/updateForm.jsp";
			
		} else if(action.equals("/modBoard")) {
			
			String num = request.getParameter("num");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			String writer_id = request.getParameter("writer_id");
			
			title = XssReplace(title);
			content = XssReplace(content);
			writer = XssReplace(writer);
			writer_id = XssReplace(writer_id);
			
			BoardVO boardVO = new BoardVO(num, title, content, writer, writer_id, null);
			boardDAO.modBoard(boardVO);
			
			BoardVO boardInfo = boardDAO.findBoard(num);
			request.setAttribute("boardInfo", boardInfo);
			nextPage = "/homepage/menu/board/viewBoard.jsp";
	
		} else if(action.equals("/addComment")) {
			
			String writer_id = request.getParameter("writer_id");
			String writer = request.getParameter("writer");
			String comment = request.getParameter("comment_text");
			String board_no = request.getParameter("board_no");
			
			comment = XssReplace(comment);
			writer_id = XssReplace(writer_id);
			writer = XssReplace(writer);
			
			BoardCommentVO boardCommentVO = new BoardCommentVO(null, comment, writer, writer_id, board_no, null);
			
			boardCommentDAO.addComment(boardCommentVO);
			
			nextPage = "/board/viewBoard?num=" + board_no;
		
		}  else if(action.equals("/delComment")) {
			
			String board_no = request.getParameter("board_no");
			String sessId = (String)session.getAttribute("id");
			String num = request.getParameter("num");
			String comment_writer_id = request.getParameter("comment_writer_id");
			
			String result = boardCommentDAO.delComment(num, sessId, comment_writer_id);
			
			if(result.equals("true")) {
				nextPage = "/board/viewBoard?num=" + board_no;
			} else {
				nextPage = "/board/viewBoardFail?num=" + board_no;
			}

		}else {
				
			nextPage = "/error/error404.jsp";
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); 
		dispatch.forward(request, response);
	}
	
	public static String XssReplace(String str) {
		
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("'", "&apos;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\r", "<br>");
		str = str.replaceAll("\n", "<p>");

		return str;
	}

}
