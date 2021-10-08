<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${memInfo == null}">
	<script>
	 		alert("비정상적인 접근입니다.");
	 		window.location.href = '/project2/index';
	</script>
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영남인재교육원 : 비밀번호찾기</title>
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

            <form action="" method="POST">
            	<input type="hidden" name="id" value=${memInfo.id }>
            	<input type="hidden" name="name" value=${memInfo.name }>
            	<input type="hidden" name="phone" value=${memInfo.phone }>
            	<input type="hidden" name="email" value=${memInfo.email }>
            	<input type="hidden" name="email_domain" value=${memInfo.email_domain }>

                <div id="idpw">
                    <div id="inputpw">

                        <h4 class="title_pw">
                            <label for="pw">비밀번호</label>
                        </h4>

                        <span>
                            <input id="pw" name="pw" type="password" maxlength="16" >
                        </span>

                        <div class="err_pw">

                            <span class="text_err_pw">
                                비밀번호를 8~16자 입력하세요.
                            </span>

                        </div>

                    </div>

                    <div id="inputchkpw">

                        <h4 class="title_chkpw">
                            <label for="pw">비밀번호 재확인</label>
                        </h4>

                        <span>
                            <input id="chkpw" type="password" maxlength="16">
                        </span>

                        <div class="err_chkpw">

                            <span class="text_err_chkpw">
                                비밀번호가 일치하지 않습니다.
                            </span>

                        </div>
                    </div>
                   
                </div>

                
            </form>

            <button type="submit" id="update_btn"><b>비밀번호 변경하기</b></button><br>

        </div>

        <div id="footer">
            <jsp:include page="../../footer.jsp"></jsp:include>
        </div>

    </div>
    
    <script>
        $(function () {
            hide(2);
            let pw = document.getElementById("pw");

            $("#update_btn").on({
                "mouseover": function () {
                    $("#update_btn").css({ "background-color": "rgb(105, 180, 255)" });
                },
                "mouseleave": function () {
                    $("#update_btn").css({ "background-color": "rgb(155, 205, 255)" });
                }
            });

            $("#update_btn").click(function () {
				
            	if (pw.value == "" || (pw.value.length < 8 || pw.value.length > 17)) {

                    $(".err_pw").show();
                    hide(1);

                } else {

                    $(".err_pw").hide();

                    if (pw.value != chkpw.value || chkpw.value == "") {

                        $(".err_chkpw").show();

                    } else {

                        $(".err_chkpw").hide();
                        alert("비밀번호 변경이 완료되었습니다.");
            			$("form").attr("onsubmit", "return true;");
            			$("form").attr("action", "/project2/search/modPw").submit();
                    }
                }
            });

            function hide(index) {
                let err_msg = [".err_pw", ".err_chkpw"];

                for (let i = 1; i >= 2 - index; i--) {
                    $(err_msg[i]).hide();
                }
            }
        });
    </script>		
</body>
</html>
