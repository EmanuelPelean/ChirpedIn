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

body{background:#59ABE3;
	margin:0
}
#mydiv{content:'Hi';
	bottom:-380px;
	background:#50505a;
	right: -200px;
	color:#fff;
	width:60%;
	padding:16px 4px 16px 0;
	border-radius:6px;
	font-size:13px;
	box-shadow:10px 10px 40px -14px #000}
	
}
.Absolute-Center {
  margin: auto;
  position: absolute;
  top: 0; left: 0; bottom: 0; right: 15;
  border: 3px solid #73AD21;
}

</style>
<style>

#chirplink, #favoritelink, a:visited {
	border-radius: 25px;
    background-color: #4286f4;
    color: white;
    padding: 14px 25px;
    font-size: 20px;
    text-align: center; 
    text-decoration: none;
}

chirplink:hover, a:active {
    background-color: #F4D03F;
}




}
</style>

</head>
<body>


	
		
<div id="mydiv" class="Absolute-Center">
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
				<h5>Java Developer focused on increasing patient care by
					decreasing virtual wait times.</h5>
				<br>
				<p>Mentor Match for: ALL!</p>
			</div>
		</div>
		
		
		
		<c:forEach var="matchedDto" items="${mentorresults}">
			<tr>
				<div class="item">
					<img src="${matchedDto.linkedInLargePictureUrl}" style="width: 100%; height: 100%;" alt= "No ImageFound">
					<div class="carousel-caption d-none d-md-block">
						<h3>${matchedDto.linkedInFirstName} ${matchedDto.linkedInLastName} ${matchedDto.totalMatchPercentForDisplay}% Match!</h3><br>
						<h6>${matchedDto.linkedInHeadline}</h6><br>
						<p>Mentor: ${matchedDto.matchingMentorSkills}</p>
						<p>Mentee: ${matchedDto.matchingMenteeSkills}</p>
						<p>Networking: ${matchedDto.matchingNetworkingSkills}</p>
						<a id="chirplink" href="chirp?fName=${matchedDto.linkedInFirstName}&lName= ${matchedDto.linkedInLastName}&Email= ${matchedDto.linkedInEmail}">Chirp</a>
						<a id="favoritelink" href="addFavorites?userLinkedInId=${matchedDto.linkedInFirstName}&matchedLinkedInId= ${matchedDto.linkedInLastName}&Email= ${matchedDto.linkedInEmail}">Add Favorite</a>
					</div>
				</div>
			</tr>
			
		</c:forEach>
		
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
		<button type="button" class="btn btn-primary">
			<span class="glyphicon glyphicon-plus-sign"></span> Add Favorite
		</button>
		<button type="button" class="btn btn-primary">
			<span class="glyphicon glyphicon-envelope"></span> Chirp
		</button>
	</div>
	</div>

	</div>
	</div>


</body>
</html>
