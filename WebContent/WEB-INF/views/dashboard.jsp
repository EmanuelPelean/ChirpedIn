<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<style type="text/css">
.header {
	text-align: left;
	background-color: light blue;
	height: 100px;
	width: 100%;
}

.footer {
	text-align: center;
	background-color: light blue;
	height: 100px;
	width: 100%;
}

.dash {
	text-align: center;
	height: 100px;
	width: 100%;
	border: 3px solid black;
}

.bottomDash {
	text-align: left;
	height: 100px;
	width: 100%;
	border: 3px solid black;
}

.main {
	text-align: center;
	margin: 0px auto;
	padding: 60px;
	border: 0;
	justify-content: center;
	width: 100%;
	display: inline-table;
	padding-right: 5px;
}

.item-1 {
	margin-right: 5px;
	float: left;
	height: 550px;
	width: 30%;
	/*background-color: red;*/
	border: 3px solid black;
}

.item-2 {
	margin-right: 5px;
	float: left;
	height: 550px;
	width: 30%;
	/*background-color: blue;*/
	border: 3px solid black;
}

.item-3 {
	margin: 0;
	float: left;
	height: 550px;
	width: 30%;
	/*background-color: green;*/
	border: 3px solid black;
}

.h3 {
	text-align: center;
}

p {
	text-align: left;
	margin-left: 5px;
}
</style>

</head>
<body>

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
			<c:forEach var="matchedDto" items="${mentorresults}">
				<tr>
					<div class="item">
						<p>
							<img src="${matchedDto.linkedInLargePictureUrl}"
								onError="this.onerror=null;this.src='https://i.imgur.com/27CDISy.jpg';"
								style="width: 30px; height: 30px;" alt="No ImageFound"
								align=center> ${matchedDto.linkedInFirstName}
							${matchedDto.linkedInLastName},
							${matchedDto.totalMatchPercentForDisplay}% Match!
						</p>
					</div>
				</tr>

			</c:forEach>
			<br>

		</div>
		<div class="item-2">
			<h3>Favorited</h3>
			<br>

		</div>
		<div class="item-3">
			<h3>Chirped</h3>
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



