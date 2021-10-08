<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영남인재교육원 : 에러</title>
</head>
<style>
#btn {
	margin-top : 10px;
    height: 50px;
    width: 255px;
    background-color: rgb(155, 205, 255);
    border: none;
}
</style>

<body style="text-align:center;">
	<br>
	
	<img src="/project2/homepage/images/index/logo.png" id="logo">
	
	<br><br>
	
	<h2>서버내부에서 에러가 발생하였습니다.</h2>
	
	<br><br>

	
	<button id="btn"><b>홈으로가기</b></button>
	
	<script src="/project2/homepage/js/jquery-3.6.0.min.js"></script>
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
        });
    </script>
</body>
</html>