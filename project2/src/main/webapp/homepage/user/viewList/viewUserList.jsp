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
<link rel="stylesheet" href="/project2/css/viewUserList.css">
<script src="/project2/js/jquery-3.6.0.min.js"></script>
<body>
	<br>
	<div id="header">
           <a href="/project2/index"><img src="/project2/images/index/logo.png" id="logo"></a>
    </div>


	<div id="search">
		<form action="/project2/member/listMembers" method="get">
			<table>
				<tr>
					<td>
						<span>
	                		<select id="search_select" name="search_select">
	                       	 	<option value="id">아이디</option>
	                       	 	<option value="name">이름</option>
	                       	 	<option value="phone">휴대전화</option>
	                    	</select>
	                	</span>
	                </td>
	                
					<td>
						<input id="search_text" name="search_text" type="text" autocomplete="off">
					</td>
					
					<td>
						<button type="submit" id="search_btn"><b>검색</b></button>
					</td>
					
				</tr>
			</table>
		</form>
	</div>
	<c:if test="${not empty membersList}">  
		<table id="userList" border="1">
		
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
								<c:if test="${mem.gender  == 'male'}">
									남
								</c:if>
							
								<c:if test="${mem.gender  == 'female'}">
									여
								</c:if>
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
	
	</c:if>
	
	<c:if test="${empty membersList}">
		<div id="not_search">
			<h1>검색결과 없음</h1>
		</div>
		  
	</c:if>
	
	<script>
	    $("#search_btn").on({
	        "mouseover": function () {
	            $("#search_btn").css({ "background-color": "rgb(105, 180, 255)" });
	        },
	        "mouseleave": function () {
	            $("#search_btn").css({ "background-color": "rgb(155, 205, 255)" });
	        }
	      
	    });
    </script>
	
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