<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영남인재교육원 : 아이디찾기</title>
<link rel="stylesheet" href="/project2/homepage/css/signup.css">
</head>

<body>
    <script src="/project2/homepage/js/jquery-3.6.0.min.js"></script>


    <div id="wrap">


        <div id="header">
            <a href="/project2/index";><img src="/project2/homepage/images/index/logo.png" id="logo"></a>
        </div>

        <hr>

        <div id="container">
			<div id="inputid">
				<h4 class="title_id">
            		<h3>검색된 아이디가 없습니다.</h3>
            	</h4>
               
                 <br>
                
            	<button id="btn"><b>홈으로가기</b></button>
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
        });
    </script>
</body>
</html>
 