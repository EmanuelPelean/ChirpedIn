<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<link href="resources/homestyle.css" type="text/css" rel="stylesheet">

</head>
<body>


	<div class="vid-container">
		<video class="bgvid" autoplay="autoplay" muted="muted" preload="auto"
			loop>
			<source
				src="http://mazwai.com/system/posts/videos/000/000/109/webm/leif_eliasson--glaciartopp.webm?1410742112"
				type="video/webm">
		</video>
		<div class="inner-container">
			<video class="bgvid inner" autoplay="autoplay" muted="muted"
				preload="auto" loop>
				<source
					src="http://mazwai.com/system/posts/videos/000/000/109/webm/leif_eliasson--glaciartopp.webm?random=1"
					type="video/webm">
			</video>
			<div class="box">
				<img alt="ChirpedInLogo" height="50%" id="hplogo"
					src="https://i.imgur.com/aiH9Lj8.png">
				<h1>ChirpedIn</h1>
				<h2>Connect with your Grand Circus peers like never before.</h2>
				<button type="submit" onclick="relocate_home()">Login with
					LinkedIn!</button>
			</div>
		</div>
	</div>

	<script>
		function relocate_home() {
			location.href = "https://www.linkedin.com/oauth/v2/authorization?&response_type=code&client_id=86n54w1pjwv6o5&redirect_uri=http://localhost:8080/ChirpedIn/result&scope=r_basicprofile r_emailaddress&state=12345";
		}
	</script>

</body>
</html>