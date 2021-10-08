<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
   

<c:if test="${id == null}">
	<script>
	 		alert("비정상적인 접근입니다.");
	 		window.location.href = '/project2/index';
	</script>
</c:if>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영남인재교육원 : 아이디찾기</title>
<link rel="stylesheet" href="/project2/css/signup.css">
</head>

<body>
    <script src="/project2/js/jquery-3.6.0.min.js"></script>

    <div id="wrap">

        <div id="header">
            <a href="/project2/index"><img src="/project2/images/index/logo.png" id="logo"></a>
        </div>

        <hr>

        <div id="container">
			<div id="inputid">
				<h4 class="title_id">
            		<label for="id">아이디</label>
            	</h4>
               
 				<span>
       				<input id="id" name="id" type="text" value=${id } readonly>
    			</span>
 
                <br>
                
            	<button id="btn" style="width: 127px"><b>로그인하기</b></button>
            	<button id="search_pw_btn" style="width: 127px"><b>비밀번호찾기</b></button>
			</div>
		</div>
		
        <div id="footer">
            <jsp:include page="../../footer.jsp"></jsp:include>
        </div>

    </div>

    <script>
        $(function () {

            $("#btn").on({
                "mouseover": function () {
                    $("#btn").css({ "background-color": "rgb(105, 180, 255)" });
                },
                
                "mouseleave": function () {
                    $("#btn").css({ "background-color": "rgb(155, 205, 255)" });
                },
                
                "click": function() {
                	window.location.href = '/project2/index';
                }
            });

            $("#search_pw_btn").on({
                "mouseover": function () {
                    $("#search_pw_btn").css({ "background-color": "rgb(105, 180, 255)" });
                },
                "mouseleave": function () {
                    $("#search_pw_btn").css({ "background-color": "rgb(155, 205, 255)" });
                },
                
                "click": function() {
                	window.location.href = '/project2/search/_pw';
                }
            });
        });
    </script>
</body>
</html>
