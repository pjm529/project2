<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영남인재교육원 : 교육원소개</title>
<link rel="stylesheet" href="/project2/homepage/css/menu.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="/project2/homepage/js/jquery-3.6.0.min.js"></script>	
</head>
<body>

	<div class="top_btn">
		<a href="#"><img src="/project2/homepage/images/top_btn.png"></a>
	</div>
	
	<div id="wrap">

        <div id="header">
            <jsp:include page="../../header.jsp"></jsp:include>
        </div>
		
        <div id="container">
        
        	<div id="menu_title">
        	
        		<div id="intro_menu">
        			<table align= center>
        				<tr>
        					<td id="intro1"><a href="/project2/introduce/introduce">인사말</a></td>
        					<td id="intro2"><a href="/project2/introduce/introduce2">조직체계</a></td>
        					<td id="intro3"><a href="/project2/introduce/introduce3">교육시설 및 장비</a></td>
        				</tr>
        			</table>
        		</div><br>
        		
        		<h2>인사말</h2> 
        		<hr style="width: 1200px"> 
        		<br>
        	</div>
        	
		     <div id="content">+
		     	<img src="/project2/homepage/images/introduce/introduce.png" style="margin-left: 200px;">  
		     </div>   
        </div>
		        
		<div id="footer">
		    <jsp:include page="../../footer.jsp"></jsp:include>
		
		</div>
	</div>
    
    <script>
    
	    $("#intro1").on({
	        "mouseover": function () {
	        	$("#intro1").css({ "background-color": "rgb(204,204,204)" }); 
	        },
	        "mouseleave": function () {
	        	$("#intro1").css({ "background-color": "rgb(240,240,240)" });
	        }
	    });
	    
	    $("#intro2").on({
	        "mouseover": function () {
	        	$("#intro2").css({ "background-color": "rgb(204,204,204)" }); 
	        },
	        "mouseleave": function () {
	        	$("#intro2").css({ "background-color": "rgb(240,240,240)" });
	        }
	    });
	    
	    $("#intro3").on({
	        "mouseover": function () {
	        	$("#intro3").css({ "background-color": "rgb(204,204,204)" }); 
	        },
	        "mouseleave": function () {
	        	$("#intro3").css({ "background-color": "rgb(240,240,240)" });
	        }
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