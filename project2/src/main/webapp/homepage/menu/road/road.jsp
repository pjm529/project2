<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
    
<% String sessId = (String)session.getAttribute("id"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영남인재교육원 : 찾아오는길</title>
<link rel="stylesheet" href="../../css/menu.css">
<link rel="stylesheet" 
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="../../js/jquery-3.6.0.min.js"></script>	
</head>
<body>
	<div class="top_btn">
		<a href="#"><img src="../../images/top_btn.png"></a>
	</div>
    
	<div id="wrap">

        <div id="header">
            <jsp:include page="../../header.jsp"></jsp:include>
        </div>
		
        <div id="container" text-align="center">
         
        	<div id="menu_title">
        		<h2>찾아오는길</h2>
        		<hr style="width: 1200px"> 
        		<br>
        		
        	</div>
        	
		     <div id="content" style="margin-left: 220px;">
		     	<jsp:include page="map.jsp"></jsp:include>
		     </div>
		     <br>
		     <img src="../../images/road.png" style="margin-left: 220px;">   
        </div>
		        
		<div id="footer">
		    <jsp:include page="../../footer.jsp"></jsp:include>
		
		</div>
	</div>
    
    <script>

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