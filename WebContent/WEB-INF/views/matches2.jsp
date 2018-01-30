<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Matches</title>

<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="resources/matches2style.css" type="text/css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lato:100,300,300i,400" rel="stylesheet">

</head>
<body>
	

	<div class="container row">
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<a class="btn btn-primary btn-dashboard" id="dashboard" href="dashboard2">Dashboard</a>
			<h1>Browse top matches!</h1>
			<div class="container row filter">
				<form method="post" action="#" class="match-filter">
					<label> Filter matches
					</label>
					<select name="match-filter" id="match-filter">
					<option value="overall" select>Overall</option>
					<option value="mentors" select>Mentors</option>
					<option value="mentees" select>Mentees</option>
					<option value="networking" select>Networking</option>	
					</select>
				</form>
			</div>
			<!-- Wrapper for slides -->
			<div class="carousel-inner">
				<div class="item active">
					<img
						src="https://media.licdn.com/mpr/mprx/0_p4PFpjnimxqDjJ9cKB5bfl338v1QjOGVr-5wAqKi8JQWjMGUSAboGjNT8xQWjg_HSAFZG4N2tM8Xlr3jut-gi4nKtM8blKh4ut-r_UnptMQXljviK-bbtlnFiri8Tp1gd6Qa"
						alt="NoUser">
					<div class="match-details">
						<h2>Emanuel Pelean</h2>
						<h3>100% Match!</h3>
						<br>
						<h4>"Java Developer focused on increasing patient care by
							decreasing virtual wait times."
						</h4>
							<br>
						<p>Mentor Match for: ALL!</p>
						<a id="favoritelink"
							href="addFavorites?favoriteLinkedInId=${matchedDto.linkedInId}">
							<span class="glyphicon glyphicon-plus-sign"></span> Add Favorite
						</a> <a id="chirplink"
							href="chirp?fName=${matchedDto.linkedInFirstName}&lName= ${matchedDto.linkedInLastName}&Email= ${matchedDto.linkedInEmail}">
							Chirp </a>
					</div>
				</div>

				<c:forEach var="matchedDto" items="${matchresults}">
					<tr>
						<div class="item">
							<img src="${matchedDto.linkedInLargePictureUrl}"
								onError="this.onerror=null;this.src='https://i.imgur.com/27CDISy.jpg';"
								alt="No ImageFound" border-radius: 50%;>
							<div class="match-details">
								<h2>${matchedDto.linkedInFirstName} <br>
										${matchedDto.linkedInLastName}</h2>
								<h3>${matchedDto.totalMatchPercentForDisplay}% Match!</h3>
								<br>
								<h4 class="h4class">" ${matchedDto.linkedInHeadline} "</h4>
								<br>
								<p>
									Mentor: ${matchedDto.matchingMentorSkills}<br> 
									Mentee: ${matchedDto.matchingMenteeSkills}<br> 
									Networking: ${matchedDto.matchingNetworkingSkills}
								</p>
								<a id="favoritelink"
									href="addFavorites?favoriteLinkedInId=${matchedDto.linkedInId}" class="glyphicon glyphicon-plus-sign"></span> Add
									Favorite
								</a> <a id="chirplink"
									href="chirp?fName=${matchedDto.linkedInFirstName}&lName= ${matchedDto.linkedInLastName}&Email= ${matchedDto.linkedInEmail}">
									Chirp </a>
							</div>
						</div>
					</tr>

				</c:forEach>

				<a class="left carousel-control" href="#myCarousel"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left"></span> <span
					class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#myCarousel"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right"></span> <span
					class="sr-only">Next</span>
				</a>
			</div>
		</div>
	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/react/15.1.0/react.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/react/15.1.0/react-dom.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/babel-standalone/6.24.0/babel.js"></script>
	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js'></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"
		integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
		crossorigin="anonymous"></script>

</body>
</html>
