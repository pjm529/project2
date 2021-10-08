
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>


<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?key=AIzaSyADRboOk38IpAZAh-r-6SLCAJBi2UiRNn4"></script>
<style>
#map_ma {
	width: 1060px;
	height: 400px;
	clear: both;
}
</style>
</head>
<body>
	<div id="map_ma"></div>
	
	<script type="text/javascript"> 
		$(document).ready(function() { 
			var myLatlng = new google.maps.LatLng(35.860541,128.557111);  
			var Y_point = 35.860541;  
			var X_point = 128.557111;
			var zoomLevel = 17;
			var markerTitle = "영남인재교육원";
			var markerMaxWidth = 500;
			var contentString = '<b>(주)영남인재교육원</b>' ;

			var myLatlng = new google.maps.LatLng(Y_point, X_point); 
			var mapOptions = { 
								zoom: zoomLevel, 
								center: myLatlng, 
								mapTypeId: google.maps.MapTypeId.ROADMAP 
							} 
			var map = new google.maps.Map(document.getElementById('map_ma'), mapOptions); 
			var marker = new google.maps.Marker({ 
											position: myLatlng, 
											map: map, 
											title: markerTitle }); 
			
			var infowindow = new google.maps.InfoWindow( { 
										content: contentString, 
										maxWizzzdth: markerMaxWidth } );
			
			google.maps.event.addListener(marker, 'click', function() { 
				infowindow.open(map, marker); 
				}); 
			}); 
		</script>
</body>
</html>

