<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
    
<% 
	session.invalidate();
	response.sendRedirect("/project2/index");
%>
