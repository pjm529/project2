<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% session.removeAttribute("search_id"); %>
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

            <form action="insertProcess.jsp" method="POST">

                <div id="userinfo">

                    <div id="inputname">
                    
                    	<h4 class="title_id">
                            <label for="id">아이디</label>
                        </h4>

                        <span>
                            <input id="id" name="id" type="text" autocomplete="off" maxlength="12">
                        </span>

                        <div class="err_id">

                            <span class="text_err_id">
                                아이디를 6~12자 입력하세요.
                            </span>

                        </div>

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
                    
                    <div id="inputemail">

                        <h4 class="title_email">
                            <label for="email">이메일</label>
                        </h4>

                        <span>
                            <input id="email" name="email" type="text" autocomplete="off" maxlength="20">
                        </span>
                        
                        <span>
                            <input id="email_domain" name="email_domain" type="text" autocomplete="off"
                             readonly value="@google.com" maxlength="20">
                        </span>
                        
                        <span>
                            <select id="email_domain2" name="email_domain2">
                                <option value="@google.com" selected>@google.com</option>
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
                    
                </div>
                
            </form>

            <button type="submit" id="btn"><b>비밀번호 찾기</b></button>

        </div>

        <div id="footer">
            <jsp:include page="../../footer.jsp"></jsp:include>
        </div>

    </div>

    <script>
        $(function () {
            hide(4);
            
            // form에 있는 정보 받아오기
            let id = document.getElementById("id");
            let name = document.getElementById("name");
            let phone = document.getElementById("phone");
            let email = document.getElementById("email");
         	
            $("#id").bind("keyup",function(){
           	 let re = /[ \{\}\[\]\/?.,;:|\)*~`!^\-+┼<>@\#$%&\'\"\\\(\=]/gi;
           	 let temp=$("#id").val();
           	 
           	 if(re.test(temp)){ //특수문자가 포함되면 삭제하여 값으로 다시셋팅
           		 $("#id").val(temp.replace(re,"")); 
           	 } 
          	});
           	 
           $("#name").bind("keyup",function(){
	           	let re = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;
	           	let temp=$("#name").val();
	           	 
	           	if(re.test(temp)){ //특수문자가 포함되면 삭제하여 값으로 다시셋팅
	           		$("#name").val(temp.replace(re,""));
	           	} 
         	});            
	           	
	           	
         	$("#phone").on("keyup", function() {
         		$(this).val( $(this).val().replace(/[^0-9]/gi,"") );
         	});
         	
         	$("#email").bind("keyup",function(){
	           	let re = /[ \{\}\[\]\/?.,;:|\)*~`!^\+┼<>@\#$%&\'\"\\\(\=]/gi;
	           	let temp=$("#email").val();
	           	
	           	if(re.test(temp)){ //특수문자가 포함되면 삭제하여 값으로 다시셋팅
	           		$("#email").val(temp.replace(re,"")); 
	           	} 
         	});
	           	
         	$("#email_domain").bind("keyup",function(){
         		let re = /[ \{\}\[\]\/?,;:|\)*~`!^\-_+┼<>\#$%&\'\"\\\(\=]/gi;
	           	let temp=$("#email_domain").val();
	           	
	           	if(re.test(temp)){ //특수문자가 포함되면 삭제하여 값으로 다시셋팅
	           		$("#email_domain").val(temp.replace(re,"")); 
	           	} 
         	});
         		
            $("#email_domain2").change(function(){
        		if($(this).val() =='1') {
        			$("#email_domain").val('');
        			$("#email_domain").attr("readonly", false);
        			$("#email_domain").css("background-color", "white");
        			$("#email_domain").focus();
        		} else {
        			$("#email_domain").val($(this).val());
        			$("#email_domain").attr("readonly", true);
        			$("#email_domain").css({ "background-color": "rgb(240, 240, 240)"});
        		}
      	  	});

            $("#btn").on({
                "mouseover": function () {
                    $("#btn").css({ "background-color": "rgb(105, 180, 255)" });
                },
                "mouseleave": function () {
                    $("#btn").css({ "background-color": "rgb(155, 205, 255)" });
                }
            });

            $("#btn").click(function () {
            	if(id.value.length < 6) {
            		$(".err_id").show();
            		
            		hide(3);
            	} else {
            		$(".err_id").hide();
            		
            		if (name.value == "" || name.value.length > 6) {
                        $(".err_name").show();
                        
                        hide(2);
                    } else {
                        $(".err_name").hide();
                        
                        if(phone.value.length != 11) {
                        	$(".err_phone").show();
                        	hide(1);
                        } else {
                        	$(".err_phone").hide();
                        	
                        	if(email.value == "") {
                        		$(".err_email").show();
                        	} else {
                        		search();
                        	}
                        }
                    }
            	}
            });

            function search() {
                // DB로 회원정보 보내기
                $("form").attr("action", "/project2/search/pw").submit();
            }

            function hide(index) {
                let err_msg = [".err_id", ".err_name", ".err_phone", ".err_email"];

                for (let i = 3; i >= 4 - index; i--) {
                    $(err_msg[i]).hide();
                }
            }

        });
    </script>
</body>
</html>