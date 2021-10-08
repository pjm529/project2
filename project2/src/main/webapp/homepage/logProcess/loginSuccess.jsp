
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
 

    
<% 
	String checkbox = (String)session.getAttribute("checkbox");
		
	if(checkbox == null) {
		Cookie cookie = new Cookie("userId", "");
		cookie.setHttpOnly(false); 
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
%>
		<script>
			window.location.href='/project2/index';
		</script>
<%

	} else {
		
		if (checkbox.equals("on")) {
			
			Cookie cookie = new Cookie("userId",request.getParameter("id"));
			cookie.setHttpOnly(false); 
			cookie.setPath("/");
			
			response.addCookie(cookie);
		%>
			<script>
				window.location.href='/project2/index';
			</script>
		<%
		} else {
			
			Cookie cookie = new Cookie("userId", "");
			cookie.setHttpOnly(false); 
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
	}
	
%>
		<script>
			window.location.href='/project2/index';
		</script>
<%
	}
%>
