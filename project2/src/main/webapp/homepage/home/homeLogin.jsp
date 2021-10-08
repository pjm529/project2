<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	String cookie = "";
	Cookie[] cookies = request.getCookies();
	String check ="";

	if (cookies != null && cookies.length > 0) {

		for (int i = 0; i < cookies.length; i++) {

			if (cookies[i].getName().equals("userId")) {
				cookie = cookies[i].getValue();
				check = "checked";
			}
		}
	}
%>

<!DOCTYPE html>
<div id="login">

	<form action="" method="post" onsubmit="return false;">
	
		<input type="text" id="id" name="id" class="user_id" placeholder="아이디" value="<%=cookie%>" required autocomplete="off">
		<br> 
		
		<input type="password" id="pw" name="pw" class="user_pw" placeholder="비밀번호" required>
		
		
		<button id="loginbtn" class="btn btn-secondary btn">로그인</button>
		
		<div id="checkbox">
			<input type="checkbox" name="checkbox" <%=check %>>
				<span style="font-size: 13px;"> ID 저장 
				<a id="search_id" href="/project2/search/_id">아이디</a> / 
				<a id="search_pw" href="/project2/search/_pw">비밀번호찾기</a> 
				<a id ="signup" href="/project2/member/memberForm">회원가입</a></span>
		</div>
	</form>	
</div> 



