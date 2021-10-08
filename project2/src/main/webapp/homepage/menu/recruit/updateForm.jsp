<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
    
<%
	String writer_id = request.getParameter("writer_id");
	String writer = (String)session.getAttribute("name");
	String sessId= (String)session.getAttribute("id");

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
<title>영남인재교육원 : 모집중인과정</title>
<link rel="stylesheet" href="/project2/css/insert.css">
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

       <div id="container" style="text-align: center;">

            <div>
                <div>
                    <h2>모집중인과정</h2>
                </div>
                <hr id="noticehr">
                <div>

                    <form action="" method="post" onsubmit="return false;">
						<input type="hidden" name="num" value=${recruitInfo.num }>
                        <br>

                        <div id="title">

                            <div>
                                <b>제목</b>
                            </div>

                            <div>
                                <textarea cols="130" rows="1" id="title_text" name="title" style="resize: none;"
                                    autocomplete="off" placeholder="제목을 입력하세요.">${recruitInfo.title }</textarea> 
                            </div>

                        </div>

                        <br>

						<div id="info">
                            <div>
                                <b>과정 정보</b>
                            </div>
                      
                            <table style="width: 1000px; margin-left: 260px;">
                                <tr>
                                    <td>
                                        <textarea cols="63" rows="1"
                                         id="training_period" name="training_period" style="resize: none;" 
                                            placeholder="교육기간을 입력하세요.">${recruitInfo.training_period }</textarea>
                                    </td>

                                    <td>
                                        <textarea cols="64" rows="1" 
                                        id="recruit_period" name="recruit_period" style="resize: none;" 
                                        placeholder="모집기간을 입력하세요.">${recruitInfo.recruit_period }</textarea>
                                    </td>

                                </tr>

                                <tr>
                                    <td>
                                        <textarea cols="63" rows="1" id="time" name="time" style="resize: none;"
                                            placeholder="수업시간을 입력하세요.">${recruitInfo.time}</textarea>
                                    </td>

                                    <td>
                                        <textarea cols="64" rows="1" id="count" name="count" style="resize: none;"
                                            placeholder="인원을 입력하세요.">${recruitInfo.count}</textarea>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <textarea cols="63" rows="1" id="location" name="location" style="resize: none;"
                                            placeholder="교육장소를 입력하세요.">${recruitInfo.location}</textarea>
                                    </td>

                                    <td>
                                        <textarea cols="64" rows="1" 
                                        id="professor" name="professor" style="resize: none;" 
                                        placeholder="담당교수를 입력하세요.">${recruitInfo.professor}</textarea>
                                    </td>
                                </tr>
                            </table>

                        </div>

                        <br>
                        
                        <div id="content">

                            <div>
                                <b>내용</b>
                            </div>

                            <div>
                                <textarea cols="130" rows="10" id="content_text" name="content" style="resize: none;"
                                    placeholder="내용을 입력하세요.">${recruitInfo.content }</textarea>
                            </div>

                        </div>

                        <br>

                        <div id="writer">

                            <div>
                                <b>작성자</b>
                            </div>

                            <div>
                                <textarea cols="130" rows="1" id="writer_text" name="writer" style="resize: none;"
                                    readonly>${recruitInfo.writer }</textarea> 
                            </div>

                        </div>

                        <br>

                        <div id="btn">

                            <button id="insert_btn"><b>수정</b></button>
                            <button id="list_btn"><b>목록</b></button>

                        </div>

                    </form>

                    <br>

                </div>

            </div>

        </div>
        
        <div id="footer">
            <jsp:include page="../../footer.jsp"></jsp:include>
        </div>
    </div>

    <script>
		$(function () {
			
			let title = document.getElementById("title_text");
            let content = document.getElementById("content_text");
            let training_period = document.getElementById("training_period");
            let recruit_period = document.getElementById("recruit_period");
            let time = document.getElementById("time");
            let count = document.getElementById("count");
            let location = document.getElementById("location");
            let professor = document.getElementById("professor");
            let writer = document.getElementById("writer_text");
            
			$("#insert_btn").click(function(){
				if (title.value == "" || content.value == "" || writer.value == "" || professor.value == "" || location.value == "" ||
	                    training_period.value == "" || recruit_period.value == "" || time.value == "" || count.value == "") {
					alert("입력 내용을 확인해주세요.");
				} else {
					if(writer.value.length > 6) {
						alert("작성자 명은 6글자를 초과할 수 없습니다.");
					} else {
						alert("수정이 완료되었습니다.");
						$("form").attr("onsubmit", "return true;");
						$("form").attr("action", "/project2/recruit/modRecruit").submit();
					}
				}
			});
			
			$("#list_btn").click(function() {
				window.location.href = '/project2/recruit/listRecruit';
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