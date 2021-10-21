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
	String sessId = (String)session.getAttribute("id"); 
	String search_text = request.getParameter("search_text");
	String search_select = request.getParameter("search_select");

	if(search_text == null) {
		search_text = "";
	}
	
	if(search_select == null) {
		search_select = "title";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영남인재교육원 : 게시판</title>
<link rel="stylesheet" href="/project2/css/menu.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="/project2/js/jquery-3.6.0.min.js"></script>
</head>
<body>

	<div class="top_btn">
		<a href="#"><img src="/project2/images/top_btn.png"></a>
	</div>
	
    <div id="wrap">

        <div id="header">
            <jsp:include page="../../header.jsp"></jsp:include>
        </div>
		
        <div id="container">
		        
        	<div id="menu_title">
        		<h2>게시판</h2>
        		<br>
        	</div>
        	
        	
        	<div id="search">
        		<form action="board.jsp" method="get">
	        		<table>
	        			<tr>
	        				<td>
	        					<span>
	                        		<select id="search_select" name="search_select">
	                               	 	<option value="title">제목</option>
	                               	 	<option value="content">내용</option>
	                               	 	<option value="writer">작성자</option>
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
        		
        	<br>
        	
			<div id="board_div">
				<table id="board" align="center">
	                <tr id="title">
	                    <td style="width: 100px;"><b>번호</b></td>
	                    <td style="width: 680px;"><b>제목</b></td>
	                    <td style="width: 100px;"><b>작성자</b></td>
	                    <td style="width: 80px;"><b>조회수</b></td>
	                    <td style="width: 100px;"><b>등록일</b></td>
	                </tr>
	
	                
	                <c:forEach var="board" items="${boardList }">
	                
					<tr>
					
						<td>
							<div>
								${board.num }
							</div>
						</td>
						
						<td>
							<div class="hide" style="width: 700px">
								<a href="/project2/board/viewBoard?num=${board.num }">
									${board.title } [${board.comment }]</a>
							</div>			
						</td>
						
						
						<td>
							<div class="hide">
								${board.writer }
							</div> 
						</td>
						
						<td>
							<div>
								${board.views }
							</div>
						</td>
						
						<td>
							<div>
								${board.reg_date }
							</div>
						</td>
						
					</tr>
					
					</c:forEach>
			    </table>
			    
				<br>
				
				<div id="insert_btn">
					<button id="add_btn"><b>글 작성</b></button>
				</div>
				
			</div>
		
		    <br>
			
		</div>
		
		<div id="footer">
		    	<jsp:include page="../../footer.jsp"></jsp:include>
		</div>
			
	</div>
    
    <script>
	    $("#search_btn").on({
	        "mouseover": function () {
	            $("#search_btn").css({ "background-color": "rgb(105, 180, 255)" });
	        },
	        "mouseleave": function () {
	            $("#search_btn").css({ "background-color": "rgb(155, 205, 255)" });
	        }
	      
	    });

        $(function () {
			let id = "null";
			let sessId = "<%=sessId %>";
			
            $("#add_btn").click(function () {
                if(id == sessId) {
                	alert("로그인 후 사용하세요.");
                	location.href='/project2/index';
                } else {
                	location.href='/project2/board/boardForm';
                }
            });
        });
        
	    $('.top_btn').hide();
	    $(document).scroll(function () {
			var y = $(this).scrollTop();
			if (y > 800) {
				$('.top_btn').show();
			} else {
				$('.top_btn').hide();
			}
		});
    </script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>