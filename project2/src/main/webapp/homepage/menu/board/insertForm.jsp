
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
    
<%
	String sessName = (String)session.getAttribute("name");
	String sessId= (String)session.getAttribute("id");
	
	if(sessId == null) { 
%>
	<script>
	 		alert("접근 권한이 없습니다.");
	 		window.location.href = '/project2/index';
	</script>
		
<%	} else { 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영남인재교육원 : 게시판</title>
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
                    <h2>게시판</h2>
                </div>
                <hr id="noticehr">
                <div>

                    <form action="" method="post" onsubmit="return false;">
						
                        <br>
						<input type="hidden" name="writer_id" value=<%=sessId %>>
                        <div id=" title">

                            <div>
                                <b>제목</b>
                            </div>

                            <div>
                                <textarea cols="130" rows="1" id="title_text" name="title" style="resize: none;"
                                    autocomplete="off" placeholder="제목을 입력하세요."></textarea> 
                            </div>

                        </div>

                        <br>

                        <div id="content">

                            <div>
                                <b>내용</b>
                            </div>

                            <div>
                                <textarea cols="130" rows="10" id="content_text" name="content" style="resize: none;"
                                    placeholder="내용을 입력하세요."></textarea>
                            </div>

                        </div>

                        <br>

                        <div id="writer">

                            <div>
                                <b>작성자</b>
                            </div>

                            <div>
                                <textarea cols="130" rows="1" id="writer_text" name="writer" style="resize: none;"
                                    readonly><%=sessName %></textarea>
                            </div>

                        </div>

                        <br>

                        <div id="btn">

                            <button id="insert_btn"><b>등록</b></button>
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
            let writer = document.getElementById("writer_text");
            
			$("#insert_btn").click(function(){
				if(title.value == "" || content.value == "" || writer.value == "") {
					alert("입력 내용을 확인해주세요.");
				} else {
					if(writer.value.length > 6) {
						alert("작성자 명은 6글자를 초과할 수 없습니다.");
					} else {
						alert("등록이 완료되었습니다.");
						$("form").attr("onsubmit", "return true;");
						$("form").attr("action", "/project2/board/addBoard").submit();
					}
				}
			});
			
			$("#list_btn").click(function() {
				window.location.href = '/project2/board/listBoard';
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
<%}%>