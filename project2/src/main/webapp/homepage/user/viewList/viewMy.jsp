<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String sessId = (String) session.getAttribute("id"); 
	String sessNum = (String) session.getAttribute("num");
	String num = request.getParameter("num");
	if(sessId == null || !num.equals(sessNum)) { 
%>
	<script>
	 		alert("접근 권한이 없습니다.");
	 		window.location.href = '/project2/index';
	</script>
		
<%	
	} else {

		
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영남인재교육원 : 마이페이지</title>
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

            <form action="insertProcess.jsp" method="POST">
				<input type="hidden" name="num" value=${memInfo.num }>
				<input type="hidden" name="sessId" value=<%=sessId %>>
				<input type="hidden" name="sessNum" value=<%=sessNum %>>
                <div id="idpw">

                    <div id="inputid">

                        <h4 class="title_id">
                            <label for="id">아이디</label>
                        </h4>

                        <span>
                            <input id="id" name="id" type="text" value=${memInfo.id } readonly>
                        </span>
                       
                    </div>
                   
                </div>

                <div id="userinfo">

                    <div id="inputname">

                        <h4 class="title_name">
                            <label for="name">이름</label>
                        </h4>

                        <span>
                            <input id="name" name="name" type="text" value=${memInfo.name } readonly>
                        </span>

                    </div>
                    
                    <div id="inputphone">

                        <h4 class="title_phone">
                            <label for="phone">휴대전화</label>
                        </h4>

                        <span>
                            <input id="phone" name="phone" type="text" value=${memInfo.phone } readonly>
                        </span>

                    </div>
                    
                    <div id="inputemail">

                        <h4 class="title_email">
                            <label for="email">이메일</label>
                        </h4>

                        <span>
                            <input id="sel_email" name="email" type="text" value=${memInfo.email }${memInfo.email_domain } readonly>
                        </span>

                    </div>

                    <div id="inputbirth">

                        <h4 class="title_birth">
                            <label for="year">생년월일</label>
                        </h4>

                        <span>
                            <input id="year" name="year" type="text" value=${memInfo.year } readonly>
                        </span>

                        <span>
                        	<input id="month" name="month" type="text"
                        	value=${memInfo.month }월 style="width: 71px; height: 30px;" readonly>
                        </span>

                        <span>
                        	<input id="day" name="day" type="text"
                        	value=${memInfo.day }일 style="width: 71px; height: 30px;" readonly>
                        </span>
       
                    </div>

                    <div id="inputgender">

                        <h4 class="title_gender">
                            <label for="gender">성별</label>
                        </h4>

                        <span>
                       		<input id="gender" name="gender" type="text"
                        	value=${memInfo.gender } style="width: 250px; height: 30px;" readonly>
                        </span>
                        
                    </div>

                </div>

                <div>
                </div>
            </form>
			<button type="submit" id="update_btn"><b>수정하기</b></button><br>
            <button type="submit" id="delete_btn"><b>탈퇴하기</b></button><br>
            
        </div>

        <div id="footer">
            <jsp:include page="../../footer.jsp"></jsp:include>
        </div>

    </div>
    
    <script>
        $(function () {

            // form에 있는 정보 받아오기
            let name = document.getElementById("name");
            let phone = document.getElementById("phone");
            let year = document.getElementById("year");
            let month = document.getElementById("month");
            let day = document.getElementById("day");
            let gender = document.getElementById("gender");

            let result;

            $("#update_btn").on({
                "mouseover": function () {
                    $("#update_btn").css({ "background-color": "rgb(105, 180, 255)" });
                },
                "mouseleave": function () {
                    $("#update_btn").css({ "background-color": "rgb(155, 205, 255)" });
                }
            });

            $("#delete_btn").on({
                "mouseover": function () {
                    $("#delete_btn").css({ "background-color": "rgb(105, 180, 255)" });
                },
                "mouseleave": function () {
                    $("#delete_btn").css({ "background-color": "rgb(155, 205, 255)" });
                }
            });
            
            $("#update_btn").click(function () {
            	$("form").attr("onsubmit", "return true;");
				$("form").attr("action", "/project2/member/modMemberFormMy").submit();
            });

            $("#delete_btn").click(function() {
            	result = confirm("탈퇴하시겠습니까?");
            	
            	if(result){
	                alert("탈퇴가 완료되었습니다.");
	                $("form").attr("onsubmit", "return true;");
	                $("form").attr("action", "/project2/member/delMy").submit();
            	}
			});
        });
    </script>		
</body>
</html>

<%	

	}
%>
