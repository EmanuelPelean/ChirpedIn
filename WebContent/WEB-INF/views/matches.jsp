<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Matches</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/react/15.1.0/react.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/react/15.1.0/react-dom.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/babel-standalone/6.24.0/babel.js"></script>
<script
	src='https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js'></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	type="text/css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>
<style>
#success_message {
	display: none;
}
</style>
<style>
#content {
	width: 80%;
	margin: 0 auto;
}
</style>
<style>
body {
	background: #76b852; /* fallback for old browsers */
	background: -webkit-linear-gradient(right, #F7DC6F, #F4D03F);
	background: -moz-linear-gradient(right, #F7DC6F, #F4D03F);
	background: -o-linear-gradient(right, #F7DC6F, #F4D03F);
	background: linear-gradient(to left, #F7DC6F, #F4D03F);
	font-family: "Roboto", sans-serif;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
}
</style>
</head>
<body>


	
		
<div id="content">
	<div class="container">
  <h2 >Browse through your top matches!</h2>
  <div id="myCarousel" style="width:50%;height:50%;" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
	<ol class="carousel-indicators">
		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		<li data-target="#myCarousel" data-slide-to="1"></li>
		<li data-target="#myCarousel" data-slide-to="2"></li>
	</ol>

	<!-- Wrapper for slides -->
	<div class="carousel-inner">


          <div class="item active">
			<img src="https://media.licdn.com/mpr/mprx/0_p4PFpjnimxqDjJ9cKB5bfl338v1QjOGVr-5wAqKi8JQWjMGUSAboGjNT8xQWjg_HSAFZG4N2tM8Xlr3jut-gi4nKtM8blKh4ut-r_UnptMQXljviK-bbtlnFiri8Tp1gd6Qa"
				style="width: 100%; height: 100%;" alt= "NoUser">
			<div class="carousel-caption d-none d-md-block">
				<h3>Emanuel Pelean 100% Match!</h3>
				<br>
				<h6>Java Developer focused on increasing patient care by
					decreasing virtual wait times.</h6>
				<br>
				<p>Mentor Match for: Java, MySQL</p>
			</div>
		</div>
		
		
		
		<c:forEach var="matchedDto" items="${mentorresults}">
			<tr>
				<div class="item">
					<img src="${matchedDto.linkedInPictureUrl}" style="width: 100%; height: 100%;" alt= "No ImageFound">
					<div class="carousel-caption d-none d-md-block">
						<h3>${matchedDto.linkedInFirstName} ${matchedDto.linkedInLastName} ${matchedDto.mentorMatchPercent}</h3><br>
						<h6>${matchedDto.linkedInHeadline}</h6><br>
						<p>Mentor Match for: ${matchedDto.matchingMentorSkills}</p>
						<a href="chirp?fName=${matchedDto.linkedInFirstName}&lName= ${matchedDto.linkedInLastName}">Chirp</a>
					</div>
				</div>
			</tr>
			
		</c:forEach>
		
		<div class="item">
			<img src="https://media.licdn.com/mpr/mprx/0_p4PFpjnimxqDjJ9cKB5bfl338v1QjOGVr-5wAqKi8JQWjMGUSAboGjNT8xQWjg_HSAFZG4N2tM8Xlr3jut-gi4nKtM8blKh4ut-r_UnptMQXljviK-bbtlnFiri8Tp1gd6Qa"
				style="width: 100%; height: 100%;" alt= "NoUser">
			<div class="carousel-caption d-none d-md-block">
				<h3>Emanuel Pelean 100% Match!</h3>
				<br>
				<h6>Java Developer focused on increasing patient care by
					decreasing virtual wait times.</h6>
				<br>
				<p>Mentor Match for: Java, MySQL</p>
			</div>
		</div>

		
		<a class="left carousel-control" href="#myCarousel" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right"></span> <span
			class="sr-only">Next</span>
		</a>
	</div>
	<div>
	<form method="GET" onsubmit="event.preventDefault(); return loadDoc(this);">
			<input type="hidden" id="linkedInName" name="linkedInName" value="george@gmailcom">
			<input id="chirp" type= "submit" value= "Add">
		</form>
		<button type="button" class="btn btn-primary" onclick="event.preventDefault(); return loadDoc();">
			<span class="glyphicon glyphicon-plus-sign"></span>Add Favorite
		</button>
		<button type="button" class="btn btn-primary">
			<span class="glyphicon glyphicon-envelope"></span> Chirp
		</button>
	</div>
	</div>

	</div>



	<script>
		function loadDoc() {

			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					
			};
			xhttp.open("GET", "chirp?", true);
			xhttp.send();
		}}
	</script>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

</body>
</html>
