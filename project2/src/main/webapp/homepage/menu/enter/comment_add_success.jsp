
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>

<%String num = request.getParameter("num"); %>
		<script>
			window.location.href='/project2/enter/viewEnter?num='+<%=num%> ;
		</script>