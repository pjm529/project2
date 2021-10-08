<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<br>
<div id="logo">
	<a href="/project2/index"><img
		src="/project2/homepage/images/index/logo.png" id="logo"></a>
</div>
<br>

<div class="navi">

	<ul class="menu">
		<li><span id="menu_notice"><a href="/project2/notice/listNotice">공지사항</a></span></li>
		<li><span id="menu_process"><a href="/project2/process/listProcess">진행교육과정</a></span></li>
		<li><span id="menu_recruit"><a href="/project2/recruit/listRecruit">모집중인과정</a></span></li>
		<li><span id="menu_board"><a href="/project2/board/listBoard">게시판</a></span></li>
		<li><span id="menu_ad"><a href="/project2/ad/listAd">구인정보</a></span></li>
		<li><span id="menu_enter"><a href="/project2/enter/listEnter">문의사항</a></span></li>
		<li><span id="menu_introduce"><a href="/project2/introduce/introduce">교육원소개</a></span></li>
		<li><span id="menu_road"><a href="/project2/homepage/menu/road/road.jsp">찾아오는길</a></span></li>
	</ul>

</div>

<hr>

<script>
/* ===========================================header menu css================================================ */

	$("#menu_notice").on({
	    "mouseover": function () {
	        $("#menu_notice a").css({ "color": "gray",
	        						  "border-bottom": "1px solid gray"});
	    },
	    "mouseleave": function () {
	        $("#menu_notice a").css({ "color": "black",
				  					  "border-bottom": "none"});
	    }
	});
	
	$("#menu_process").on({
		"mouseover": function () {
	        $("#menu_process a").css({ "color": "gray",
	        						  "border-bottom": "1px solid gray"});
	    },
	    "mouseleave": function () {
	        $("#menu_process a").css({ "color": "black",
				  					  "border-bottom": "none"});
	    }
	});
	
	$("#menu_recruit").on({
		"mouseover": function () {
	        $("#menu_recruit a").css({ "color": "gray",
	        						  "border-bottom": "1px solid gray"});
	    },
	    "mouseleave": function () {
	        $("#menu_recruit a").css({ "color": "black",
				  					  "border-bottom": "none"});
	    }
	});
	
	$("#menu_board").on({
		"mouseover": function () {
	        $("#menu_board a").css({ "color": "gray",
	        						  "border-bottom": "1px solid gray"});
	    },
	    "mouseleave": function () {
	        $("#menu_board a").css({ "color": "black",
				  					  "border-bottom": "none"});
	    }
	});
	
	$("#menu_ad").on({
		"mouseover": function () {
	        $("#menu_ad a").css({ "color": "gray",
	        						  "border-bottom": "1px solid gray"});
	    },
	    "mouseleave": function () {
	        $("#menu_ad a").css({ "color": "black",
				  					  "border-bottom": "none"});
	    }
	});
	
	$("#menu_enter").on({
		"mouseover": function () {
	        $("#menu_enter a").css({ "color": "gray",
	        						  "border-bottom": "1px solid gray"});
	    },
	    "mouseleave": function () {
	        $("#menu_enter a").css({ "color": "black",
				  					  "border-bottom": "none"});
	    }
	});
	
	$("#menu_introduce").on({
		"mouseover": function () {
	        $("#menu_introduce a").css({ "color": "gray",
	        						  "border-bottom": "1px solid gray"});
	    },
	    "mouseleave": function () {
	        $("#menu_introduce a").css({ "color": "black",
				  					  "border-bottom": "none"});
	    }
	});
	
	$("#menu_road").on({
		"mouseover": function () {
	        $("#menu_road a").css({ "color": "gray",
	        						  "border-bottom": "1px solid gray"});
	    },
	    "mouseleave": function () {
	        $("#menu_road a").css({ "color": "black",
				  					  "border-bottom": "none"});
	    }
	});

/* ======================================================================================================= */
</script>