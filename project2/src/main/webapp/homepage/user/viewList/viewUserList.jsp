<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	String sessId = (String) session.getAttribute("id");

	if(sessId == null) { 
%>
	<script>
	 		alert("접근 권한이 없습니다.");
	 		window.location.href = '/project2/index';
	</script>
<%	
	} else {
		if(sessId.equals("admin")) {
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영남인재교육원 : 회원관리</title>
</head>

<style>
	a{
		text-decoration: none;
	}
</style>

<body style="text-align:center; background-color: #eee;">
	<br>
	<div id="header">
           <a href="/project2/index"><img src="/project2/images/index/logo.png" id="logo"></a>
       </div>

       <hr style="width:1200px;">

	<h2><a href="member.jsp" style="color: black">회원목록</a></h2>
	<table  border="1" align="center" style="background-color: white">
	
		<tr>
			<td><b>회원번호</b></td>
			<td><b>아이디</b></td>
			<td><b>이름</b></td>
			<td><b>휴대전화</b></td>
			<td><b>이메일</b></td>
			<td><b>생년월일</b></td>
			<td><b>성별</b></td>
			<td><b>가입일</b></td>
		</tr>
		
		
			<c:forEach var="mem" items="${membersList }">
				<tr>
					<td>
						<div style="width:80px;">
							${mem.num }
						</div>
					</td>
					
					<td>
						<div style="width: 150px">
							<a href="/project2/member/viewMember?num=${mem.num }">
								${mem.id }</a>
						</div>			
					</td>
					
					<td>
						<div style="width: 150px;">
							${mem.name }
						</div> 
					</td>
					
					<td>
						<div style="width: 150px;">
							${mem.phone}
						</div> 
					</td>
					
					<td>
						<div style="width: 200px;">
							${mem.email}${mem.email_domain }
						</div> 
					</td>
					
					<td>
						<div style="width: 100px;">
							${mem.year }${mem.month }${mem.day }
						</div> 
					</td>
					
					<td>
						<div style="width: 80px;">
							${mem.gender }
							<%-- <%if(mem.gender}.equals("male")){
		                    %>		남 
		                    <%} else { 
		                    %>		여 
		                    <% }
		                    %> --%>
						</div> 
					</td>
					
					<td>
						<div style="width: 170px;">
							${mem.reg_date }
						</div>
					</td>
				</tr>
			</c:forEach>

		
		
	</table>
	
</body>
</html>

<%
		} else {
%>	
		<script>
	 		alert("접근 권한이 없습니다.");
	 		window.location.href = '/project2/index'; 
		</script>
<%	
		}
	}
%>