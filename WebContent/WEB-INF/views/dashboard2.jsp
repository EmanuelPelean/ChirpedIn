<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">

<link href="dashboardstyle.css" type="text/css" rel="stylesheet">
</head>
<body>
	<br>
	<h1>Welcome User!</h1>
<nav>
		<div>
			<button type="button">Matches Page</button>
			<button type="button">SignUp Page</button>
		</div>
		<div>	
			<i class="material-icons">&#xE851;</i>
		</div>
</nav>
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
								style="width: 30px; height: 30px;" alt="No ImageFound" >${matchedDto.linkedInFirstName}
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
								style="width: 30px; height: 30px;" alt="No ImageFound">${favorite.linkedInFirstName}
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
		<p>This is the footer!</p>
	</div>
</body>
</html>
