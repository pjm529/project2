<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String sessId =(String) session.getAttribute("id");
	String sessName=(String) session.getAttribute("name");
	String sessNum=(String) session.getAttribute("num");
	session.removeAttribute("search_id");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영남인재교육원</title>
<link rel="stylesheet" href="/project2/css/mine.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

</head>
<script src="/project2/js/jquery-3.6.0.min.js"></script>
<body>
    <div id="wrap">

        <div id="header">
            <jsp:include page="header.jsp"></jsp:include>
        </div>

        <div id="container">

            <div id="contents">

                <div id="display">
                
                    <div id="notice">
                        <a href="/project2/notice/listNotice">
							<p>공지사항　　　　　　　　　　　　　　　　　　　　　　　<span class="plus">+</span></p>
						</a>
						<hr>
                     	<c:forEach var="notice" items="${noticeList }">
                        <div class="hide" style="width: 450px">
							<span><a href="/project2/notice/viewNotice?num=${notice.num }">· ${notice.title }</a></span>
						</div>
						</c:forEach>
                    </div>

                    <div id="board">
                        <a href="/project2/board/listBoard">
							<p>게시판　　　　　　　　　　　　　　　　　　　　　　　　<span class="plus">+</span></p>
						</a>
						<hr>
                     	<c:forEach var="board" items="${boardList }">
                        <div class="hide" style="width: 450px">
							<span><a href="/project2/board/viewBoard?num=${board.num }">· ${board.title }</a></span>
						</div>
						</c:forEach>
                    </div>

                    <div id="ad">
                    	<a href="/project2/ad/listAd">
							<p>구인정보　　　　　　　　　　　　　　　　　　　　　　　<span class="plus">+</span></p>
						</a>
						<hr>
                     	<c:forEach var="ad" items="${adList }">
                        <div class="hide" style="width: 450px">
							<span><a href="/project2/ad/viewAd?num=${ad.num }">· ${ad.title }</a></span>
						</div>
						</c:forEach>
                    </div>

                    <div id="process">    
                        <a href="/project2/process/listProcess">
							<p>진행교육과정　　　　　　　　　　　　　　　　　　　　　<span class="plus">+</span></p>
						</a>
						<hr>
                     	<c:forEach var="process" items="${processList }">
                        <div class="hide" style="width: 450px">
							<span><a href="/project2/process/viewProcess?num=${process.num }">· ${process.title }</a></span>
						</div>
						</c:forEach>
                    </div>

                    <div id="recruit">
                        <a href="/project2/recruit/listRecruit">
							<p>모집중인과정　　　　　　　　　　　　　　　　　　　　　<span class="plus">+</span></p>
						</a>
						<hr>
                     	<c:forEach var="recruit" items="${recruitList }">
                        <div class="hide" style="width: 450px">
							<span><a href="/project2/recruit/viewRecruit?num=${recruit.num }">· ${recruit.title }</a></span>
						</div>
						</c:forEach>
                    </div>

                    <div id="enter">
                        <a href="/project2/enter/listEnter">
							<p>문의사항　　　　　　　　　　　　　　　　　　　　　　　<span class="plus">+</span></p>
						</a>
						<hr>
                     	<c:forEach var="enter" items="${enterList }">
                        <div class="hide" style="width: 450px">
							<span><a href="/project2/enter/view?num=${enter.num }">· ${enter.title }</a></span>
						</div>
						</c:forEach>
                    </div>

                </div>

            </div>

            <div id="side">
            	<div id="sidebar">
					<%
					if(sessId == null) {
					%>
					<jsp:include page="home/homeLogin.jsp"></jsp:include> <!-- 로그인창 -->                
              		  
					<% 		
					} else {
						%>
					<jsp:include page="home/homeLogined.jsp"></jsp:include>   
					<%
						}
					%>
				</div>
                

                <div id="bkcolor">
                    <button id="chgcolor" class="btn btn-secondary btn">배경색상 바꾸기</button>
                    <button id="reset" class="btn btn-secondary btn">색상 초기화</button>
                </div>

                <div id="sns">
					<jsp:include page="home/homeSns.jsp"></jsp:include> <!-- sns 창-->   
                </div>

                <div id="game">
                    <p>게임</p>
                    <hr>
                    <span><a href="/project2/game/rsp">가위바위보</a></span><br>
                    <span><a href="/project2/game/worldcup">이상형 월드컵</a></span><br>
                    <span><a href="/project2/game/race?#">경주 게임</a></span>
                </div>


            </div>

        </div>

        <br>
        
        <div id="footer">
            <jsp:include page="footer.jsp"></jsp:include>
        </div>

    </div>


    <script src="js/index.js"></script>
    <script>
   		 let id = document.getElementById("id");
   		 let pw = document.getElementById("pw");
   		 
   		 $("form").attr("onsubmit", "return false;");
   		 
   		$("#search_id").on({
            "mouseover": function () {
                $("#search_id").css({ "font-weight": "bold" });
            },
            "mouseleave": function () {
                $("#search_id").css({ "font-weight": "normal" });
            }
        });
        
   		$("#search_pw").on({
            "mouseover": function () {
                $("#search_pw").css({ "font-weight": "bold" });
            },
            "mouseleave": function () {
                $("#search_pw").css({ "font-weight": "normal" });
            }
        });
   		
   		$("#signup").on({
            "mouseover": function () {
                $("#signup").css({ "font-weight": "bold" });
            },
            "mouseleave": function () {
                $("#signup").css({ "font-weight": "normal" });
            }
        });
   		
   		
   		
    	$("#loginbtn").click(function (){
    		if(id.value == "" || pw.value == ""){
    			alert("로그인 정보가 올바르지 않습니다.");
    		} else {
    			 $("form").attr("onsubmit", "return true;");
    			$("form").attr("action", "/project2/log/login").submit();
    			
    		}
			
		});
    	
        $("#chgcolor").click(function () {
            $("body").css({ "background-color": rndColor() });
        });

        $("#reset").click(function () {
            $("body").removeAttr("style");
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