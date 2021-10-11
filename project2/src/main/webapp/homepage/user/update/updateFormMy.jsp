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

	if(sessId == null) { 
%>
	<script>
	 		alert("접근 권한이 없습니다.");
	 		window.location.href = "/project2/index';
	</script>
		
<%	} else {
		if(sessId.equals("admin")||sessNum.equals(num)) {
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영남인재교육원 : 회원수정</title>
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
				<input type="hidden" name="num" value=<%=sessNum %>>
				<input type="hidden" name="sessId" value=<%=sessId %>>
				<input type="hidden" name="sessNum" value=<%=sessNum %>>
                <div id="idpw">

                    <div id="inputid">

                        <h4 class="title_id">
                            <label for="id">아이디</label>
                        </h4>

                        <span>
                            <input id="id" name="id" type="text" autocomplete="off" value=${memInfo.id } readonly>
                        </span>
                       
                    </div>
                    
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

                <div id="userinfo">

                    <div id="inputname">

                        <h4 class="title_name">
                            <label for="name">이름</label>
                        </h4>

                        <span>
                            <input id="name" name="name" type="text" autocomplete="off" value=${memInfo.name }>
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
                            <input id="phone" name="phone" type="text" autocomplete="off" value=${memInfo.phone }>
                        </span>

                        <div class="err_phone">

                            <span class="text_err_phone">
                                전화번호을 11글자 입력해주세요.
                            </span>
                            
                        </div>

                    </div>

					<div id="inputemail">

                        <h4 class="title_email">
                            <label for="email">이메일</label>
                        </h4>

                        <span>
                            <input id="email" name="email" type="text" autocomplete="off" value=${memInfo.email }>
                        </span>
                        
                        <span>
                            <input id="email_domain" name="email_domain" type="text" autocomplete="off"
                             disabled value=${memInfo.email_domain }>
                        </span>
                        
                        <span>
                            <select id="email_domain2" name="email_domain2">
                            	<option value=${memInfo.email_domain } selected>${memInfo.email_domain }</option>
                                <option value="@google.com">@google.com</option>
                                <option value="@naver.com">@naver.com</option>
                                <option value="@daum.net">@daum.net</option>
                                <option value="@nate.com">@nate.com</option>
                                <option value="1">직접입력</option>
                            </select>
                        </span>
                        
                        <div class="err_email">

                            <span class="text_err_email">
                                이메일을 입력해주세요.
                            </span>
                            
                        </div>

                    </div>
                    
                    <div id="inputbirth">

                        <h4 class="title_birth">
                            <label for="year">생년월일</label>
                        </h4>

                        <span>
                            <input id="year" name="year" type="text" autocomplete="off" placeholder="년 (4자리)" value=${memInfo.year }>
                        </span>

                        <span>
                            <select id="month" name="month">
                                <option value=${memInfo.month }>${memInfo.month }월</option>
                                <option value="01">01월</option>
                                <option value="02">02월</option>
                                <option value="03">03월</option>
                                <option value="04">04월</option>
                                <option value="05">05월</option>
                                <option value="06">06월</option>
                                <option value="07">07월</option>
                                <option value="08">08월</option>
                                <option value="09">09월</option>
                                <option value="10">10월</option>
                                <option value="11">11월</option>
                                <option value="12">12월</option>
                            </select>
                        </span>

                        <span>
                        	<select id="day" name="day">
                                <option value=${memInfo.day }>${memInfo.day }일</option>
                                <option value="01">01일</option>
                                <option value="02">02일</option>
                                <option value="03">03일</option>
                                <option value="04">04일</option>
                                <option value="05">05일</option>
                                <option value="06">06일</option>
                                <option value="07">07일</option>
                                <option value="08">08일</option>
                                <option value="09">09일</option>
                                <option value="10">10일</option>
                                <option value="11">11일</option>
                                <option value="12">12일</option>
                                <option value="13">13일</option>
                                <option value="14">14일</option>
                                <option value="15">15일</option>
                                <option value="16">16일</option>
                                <option value="17">17일</option>
                                <option value="18">18일</option>
                                <option value="19">19일</option>
                                <option value="20">20일</option>
                                <option value="21">21일</option>
                                <option value="22">22일</option>
                                <option value="23">23일</option>
                                <option value="24">24일</option>
                                <option value="25">25일</option>
                                <option value="26">26일</option>
                                <option value="27">27일</option>
                                <option value="28">28일</option>
                                <option value="29">29일</option>
                                <option value="30">30일</option>
                                <option value="31">31일</option>
                            </select>
                        </span>
                        
                        <div class="err_birth">

                            <span class="text_err_birth">
                                생년월일을 입력해주세요.
                            </span>

                        </div>
                    </div>

                    <div id="inputgender">

                        <h4 class="title_gender">
                            <label for="gender">성별</label>
                        </h4>

                        <span>
                            <select id="gender" name="gender">
                                <option value=${memInfo.gender }>${memInfo.gender }</option>
                                <option value="male">남</option>
                                <option value="female">여</option>
                        	</select>
                        </span>

						<div class="err_gender">

                            <span class="text_err_gender">
                                성별을 선택해주세요.
                            </span>

                        </div>
                        
                    </div>

                </div>

                <div>
                </div>
            </form>

            <button type="submit" id="update_btn"><b>수정하기</b></button><br>

        </div>

        <div id="footer">
            <jsp:include page="../../footer.jsp"></jsp:include>
        </div>

    </div>
    
    <script>
        $(function () {
            hide(7);
            // form에 있는 정보 받아오기
            let name = document.getElementById("name");
            let phone = document.getElementById("phone");
            let email = document.getElementById("email");
            let email_domain = document.getElementById("email_domain");
            let year = document.getElementById("year");
            let month = document.getElementById("month");
            let day = document.getElementById("day");
            let gender = document.getElementById("gender");
            
            $("#email_domain2").change(function(){
            	$("#email_domain2 option:selected").each(function() {
            		if($(this).val() =='1') {
            			$("#email_domain").val('');
            			$("#email_domain").attr("disabled", false);
            		} else {
            			$("#email_domain").val($(this).val());
            			$("#email_domain").attr("disabled", true);
            		}
            	});
            	
            });

            
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
                    hide(6);

                } else {

                    $(".err_pw").hide();

                    if (pw.value != chkpw.value || chkpw.value == "") {

                        $(".err_chkpw").show();
                        hide(5);

                    } else {

                        $(".err_chkpw").hide();
                        
                     // name값 체크
                        if (name.value == "" || name.value.length > 6) {
                            $(".err_name").show();
                            hide(4);
                        }
                        else {
                            $(".err_name").hide();
                            
                            // phone 체크
                            if(phone.value.length != 11) {
                            	$(".err_phone").show();
                            	hide(3);
                            } else {
                            	$(".err_phone").hide();
                            	
                            	if(email.value == "" || email_domain.value == "") {
                            		$(".err_email").show();
                            		hide(2);
                            	} else {
                            		$(".err_email").hide();
                            		
                            		//birth 체크
                                	if (year.value.length != 4 || month.value == "" || day.value.length != 2) {
                                        $(".err_birth").show();
                                        hide(1);
                                    } else {
                                        $(".err_birth").hide();

                                        // gender 체크
                                        if (gender.value == "") {
                                            $(".err_gender").show();
                                        } else {
                                    	alert("수정이 완료되었습니다.");
            							$("form").attr("onsubmit", "return true;");
            							$("form").attr("action", "/project2/member/modMemberMy").submit();;
                                    	}
                                	}
                           	 	}
                            }
                        }
                    }
                }
            });

            function hide(index) {
                let err_msg = [".err_pw", ".err_chkpw", ".err_name", ".err_phone", ".err_email", ".err_birth", ".err_gender"];

                for (let i = 6; i >= 7 - index; i--) {
                    $(err_msg[i]).hide();
                }
            }
        });
    </script>		
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