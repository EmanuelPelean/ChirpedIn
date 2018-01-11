<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="resources/dashboardstyle.css" type="text/css" rel="stylesheet">

</head>
<body>

<a class="btn btn-primary" id="matches" href="matches">Matches</a>

	<div class="header">
		<h1>Welcome User!</h1>
		<button style="align: center;" type="button">Update Info</button>
		<button style="align: center;" type="button">Matches Page</button>
		<button style="align: center;" type="button">SignUp Page</button>

	</div>
	<div class="dash">
		<h2>Please find a summary of your details here!</h2>
	</div>
	<div class="main">
		<div class="item-1">
			<h3>Latest Matches!</h3>
			<c:forEach var="matchedDto" items="${matchresults}">
				<tr>
					<div class="item">
						<p>
							<img src="${matchedDto.linkedInLargePictureUrl}"
								onError="this.onerror=null;this.src='https://i.imgur.com/27CDISy.jpg';"
								style="width: 30px; height: 30px;" alt="No ImageFound" > ${matchedDto.linkedInFirstName}
							${matchedDto.linkedInLastName},
							${matchedDto.totalMatchPercentForDisplay}% Match!
						</p>
					</div>
				</tr>

			</c:forEach>
			<br>

		</div>
		<div class="item-2">
			<h3>Favorites</h3>
			<c:forEach var="favorite" items="${favorites}">
				<tr>
					<div class="item">
						<p>
							<img src="${favorite.linkedInLargePictureUrl}"
								onError="this.onerror=null;this.src='https://i.imgur.com/27CDISy.jpg';"
								style="width: 30px; height: 30px;" alt="No ImageFound"> ${favorite.linkedInFirstName}
							${favorite.linkedInLastName},
							${favorite.totalMatchPercentForDisplay}% Match!
						</p>
					</div>
				</tr>

			</c:forEach>
			<br>

		</div>
		<div class="item-3">
			<h3>Chirps</h3>
			<br>

		</div>

	</div>
	<div class="bottomDash">
		<h4>Notes:</h4>
	</div>
	<div class="footer">
		<h1>This is the footer!</h1>
	</div>
</body>
</html>



