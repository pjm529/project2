<%@page import="javax.websocket.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>

<%
	String num = request.getParameter("num");
	String sessId = (String)session.getAttribute("id");
	
	if(num == null) { 
%>
		<script>
		 		alert("비정상적인 접근입니다.");
		 		window.location.href = '/project2/index';
		</script>
<% 	
	} else if(sessId == null) {
%>
	<script>
		alert("로그인 후 이용해주세요.");
		window.location.href = '/project2/index';
	</script>
<%	
	} else if(sessId.equals("admin")) {
		session.setAttribute("pw_text", "1");
		response.sendRedirect("/project2/enter/viewEnter?num=" + num);
	} else {
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영남인재교육원 : 입학상담</title>
<link rel="stylesheet" href="/project2/homepage/css/menu.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="/project2/homepage/js/jquery-3.6.0.min.js"></script>	
</head>
<body>

	<div class="top_btn">
		<a href="#"><img src="/project2/homepage/images/top_btn.png"></a>
	</div>
	
    <div id="wrap">

         <div id="header">
            <jsp:include page="../../header.jsp"></jsp:include>
        </div>

        <div id="container" style="text-align: center;">

            <div>
                <div>
                	<h4>비밀번호</h4><br>
                </div>
                
                <form action="" method="post">
                	<input type="hidden" name="num" value=<%=num %>>
                	
                	<input type="password" id="pw_text" name="pw_text">
                	<button id="pw_btn" name="pw_btn">입력</button>
                	<br><br>
                </form>
            </div>

        </div>
        
        <div id="footer">
            <jsp:include page="../../footer.jsp"></jsp:include>
        </div>
    </div>
    
    <script>
		$(function () {
            alert("비밀번호가 틀립니다.");
            
            $("#pw_btn").on({ 
                "mouseover": function () {
                    $("#pw_btn").css({ "background-color": "rgb(105, 180, 255)" });
                },
                
                "mouseleave": function () {
                    $("#pw_btn").css({ "background-color": "rgb(155, 205, 255)" });
                },
                
                "click": function() {
                	$("form").attr("action", "/project2/enter/logInEnter").submit();
                }
            });
            
			$("#list_btn").click(function() {
				 window.location.href = '/project2/homepage/enter.jsp';
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
<%
	}
%>