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
            <a href="/project2/index"><img src="/project2/homepage/images/index/logo.png" id="logo"></a>
        </div>

        <hr>

        <div id="container">

            <form action="insertProcess.jsp" method="POST">

               

                <div id="userinfo">

                    <div id="inputname">

                        <h4 class="title_name">
                            <label for="name">이름</label>
                        </h4>

                        <span>
                            <input id="name" name="name" type="text" autocomplete="off" maxlength="6">
                        </span>

                        <div class="err_name">

                            <span class="text_err_name">
                                이름을 6글자 이하로 입력해주세요.
                            </span>
                            
                        </div>

                    </div>
                    
                    <div id="inputphone">

                        <h4 class="title_phone">
                            <label for="phone">휴대전화</label>
                        </h4>

                        <span>
                            <input id="phone" name="phone" type="text" autocomplete="off" maxlength="11">
                        </span>

                        <div class="err_phone">

                            <span class="text_err_phone">
                                전화번호을 11글자 입력해주세요.
                            </span>
                            
                        </div>

                    </div>
                    
                </div>

                <div>
                </div>
            </form>

            <button type="submit" id="btn"><b>아이디 찾기</b></button>

        </div>

        <div id="footer">
            <jsp:include page="../../footer.jsp"></jsp:include>
        </div>

    </div>

    <script>
        $(function () {
            hide(2);
            
            // form에 있는 정보 받아오기
            let name = document.getElementById("name");
            let phone = document.getElementById("phone");
         

            $("#btn").on({
                "mouseover": function () {
                    $("#btn").css({ "background-color": "rgb(105, 180, 255)" });
                },
                "mouseleave": function () {
                    $("#btn").css({ "background-color": "rgb(155, 205, 255)" });
                }
            });

            $("#btn").click(function () {
            	if (name.value == "" || name.value.length > 6) {
                    $(".err_name").show();
                    hide(1);
                } else {
                    $(".err_name").hide();
                    
                    // phone 체크
                    if(phone.value.length != 11) {
                    	$(".err_phone").show();
                    } else {
                    	search();
                    }
                }
            });

            function search() {
                // DB로 회원정보 보내기
            	$("form").attr("action", "/project2/search/id").submit();
            }

            function hide(index) {
                let err_msg = [".err_name", ".err_phone"];

                for (let i = 1; i >= 2 - index; i--) {
                    $(err_msg[i]).hide();
                }
            }
        });
    </script>
</body>
</html>