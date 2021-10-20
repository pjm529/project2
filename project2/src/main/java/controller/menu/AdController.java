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

import com.mysql.jdbc.StringUtils;

import model.menu.AdVO;
import service.menu.AdDAO;

@WebServlet("/ad/*")
public class AdController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	AdDAO adDAO;

	public void init() throws ServletException {
		adDAO = new AdDAO();
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
		
		if(action == null || action.equals("/listAd")) { // 최초 요청이거나 listMembers.do일때
			
			 String search_select = request.getParameter("search_select");
			 String search_text = request.getParameter("search_text");
			
			List<AdVO> adList = adDAO.listAds(search_select, search_text);
			
			request.setAttribute("adList", adList);
			nextPage = "/homepage/menu/ad/ad.jsp";
			
		} else if(action.equals("/addAd")) {
				
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			String writer_id = request.getParameter("writer_id");
			
			title = XssReplace(title);
			content = XssReplace(content);
			writer = XssReplace(writer);
			writer_id = XssReplace(writer_id);
			
			AdVO adVO = new AdVO(title, content, writer, writer_id);
			
			adDAO.addAd(adVO);
			
			List<AdVO> adList = adDAO.listAds(null, null);
			request.setAttribute("adList", adList);
			nextPage = "/homepage/menu/ad/ad.jsp";
			
		} else if(action.equals("/adForm")) {
			
			nextPage = "/homepage/menu/ad/insertForm.jsp";
			
		} else if(action.equals("/viewAd")) {
			
			String num = request.getParameter("num");
			AdVO adInfo = adDAO.findAd(num);
			
			request.setAttribute("adInfo", adInfo);
			
			nextPage = "/homepage/menu/ad/viewAd.jsp";
			
		} else if(action.equals("/delAd")) {
			
			String num = request.getParameter("num");
			String sessId = (String)session.getAttribute("id");
			String writer_id = request.getParameter("writer_id");
			
			adDAO.delAd(num, sessId, writer_id);
			
			List<AdVO> adList = adDAO.listAds(null, null);
			request.setAttribute("adList", adList);
			nextPage = "/homepage/menu/ad/ad.jsp";

		} else if(action.equals("/modAdForm")) {
			
			String num = request.getParameter("num");
			AdVO adInfo = adDAO.findAd(num);
			
			request.setAttribute("adInfo", adInfo);
			request.setAttribute("writer_id", adInfo.getWriter_id());
			
			nextPage = "/homepage/menu/ad/updateForm.jsp";
			
		} else if(action.equals("/modAd")) {
			
			String num = request.getParameter("num");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			String writer_id = request.getParameter("writer_id");
			
			title = XssReplace(title);
			content = XssReplace(content);
			writer = XssReplace(writer);
			writer_id = XssReplace(writer_id);
			
			AdVO adVO = new AdVO(num, title, content, writer, writer_id, null);
			adDAO.modAd(adVO);
			
			AdVO adInfo = adDAO.findAd(num);
			request.setAttribute("adInfo", adInfo);
			nextPage = "/homepage/menu/ad/viewAd.jsp";
	
		} else {
				
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

	