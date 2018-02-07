<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Criteria</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
<link href="resources/searchcriteriastyle.css" type="text/css" rel="stylesheet">

</head>
<body>
	<div class="title">
		<h2 class="h2">Update Search Criteria</h2>
	</div>

	<form:form action="updatesearchcriteria" method="post" modelAttribute="newUserDto">
		<div id="mydiv2" class="Top-Left">
			<div class="form-group form-inline justify-content-left">
				<label for="email">ID :</label>
				<form:input id="fancy-inputs" class="input form-control" type="text"
					name="linkedInId" path="linkedInId" value="${data.linkedInId }"></form:input>
				<br />
			</div>
			<div class="form-group form-inline justify-content-left">
				<label for="email">First Name :</label>
				<form:input id="fancy-inputs" class="form-control" type="text"
					name="linkedInFirstName" path="linkedInFirstName"
					value="${data.linkedInFirstName }"></form:input>
				<br />
			</div>
			<div class="form-group form-inline justify-content-left">
				<label for="email">Last Name :</label>
				<form:input id="fancy-inputs" class="form-control" type="text"
					name="linkedInLastName" path="linkedInLastName"
					value="${data.linkedInLastName }"></form:input>
				<br />
			</div>
			<div class="form-group form-inline justify-content-left">
				<label for="email">E-mail :</label>
				<form:input id="fancy-inputs" class="form-control" type="text"
					name="linkedInEmail" path="linkedInEmail"
					value="${data.linkedInEmail }"></form:input>
				<br />
			</div>
		</div>
		<div class="row">
			<form:input type="hidden" name="linkedInHeadline"
				path="linkedInHeadline" value="${data.linkedInHeadline }"></form:input>
			<br />
			<form:input type="hidden" name="linkedInLocation"
				path="linkedInLocation" value="${data.linkedInLocation }"></form:input>
			<br />
			<form:input type="hidden" name="linkedInPictureUrl"
				path="linkedInPictureUrl" value="${data.linkedInPictureUrl }"></form:input>
			<br />
			<form:input type="hidden" name="linkedInLargePictureUrl"
				path="linkedInLargePictureUrl"
				value="${data.linkedInLargePictureUrl }"></form:input>
			<br />
			<form:input type="hidden" name="linkedInPublicProfileUrl"
				path="linkedInPublicProfileUrl"
				value="${data.linkedInPublicProfileUrl }"></form:input>
			<br />
		</div>

		<div id="mydiv" class="text-center Absolute-Center">
			<div class="container">
				<div class="row">
					<div class="col-sm-4">
						<h2>FIND a Mentor</h2>
						<p>For which skills?</p>
						<h3>Front End</h3>
						<form:checkbox path="mentorSkillsPhp" value="true" checked = "checked"/>
						PHP <br>
						<form:checkbox path="mentorSkillsJavaScript" value="true" checked = "${mentorSkillsJavaScript}" />
						JavaScript<br>
						<form:checkbox name="mentorskills" path="mentorSkillsHTML"
							value="true" checked = "${mentorSkillsHTML}"/>
						HTML<br>
						<form:checkbox name="mentorskills" path="mentorSkillsCSS"
							value="true" checked = "${mentorSkillsCSS}"/>
						CSS<br>

						<h3>Back End</h3>
						<form:checkbox name="mentorskills" path="mentorSkillsJava"
							value="true" checked = "${mentorSkillsJava}"/>
						Java<br>
						<form:checkbox name="mentorskills" path="mentorSkillsJsp"
							value="true" checked = "${mentorSkillsJsp}"/>
						JSP<br>
						<form:checkbox name="mentorskills" path="mentorSkillsJstl"
							value="true" checked = "${mentorSkillsJstl}"/>
						JSTL<br>
						<form:checkbox name="mentorskills" path="mentorSkillsSql"
							value="true" checked = "${mentorSkillsSql}"/>
						SQL<br>
						<form:checkbox name="mentorskills" path="mentorSkillsSpringMVC"
							value="true" checked = "${mentorSkillsSpringMVC}"/>
						Spring MVC<br>
						<form:checkbox name="mentorskills" path="mentorSkillsHibernate"
							value="true" checked = "${mentorSkillsHibernate}"/>
						Hibernate<br>
						<form:checkbox name="mentorskills" path="mentorSkillsJdbc"
							value="true" checked = "${mentorSkillsJdbc}"/>
						JDBC<br>
					</div>



					<div class="col-sm-4">
						<h2>BE a Mentor</h2>
						<p>For which skills?</p>
						<h3>Front End</h3>
						<form:checkbox name="menteeskills" path="menteeSkillsPhp"
							value="true" checked = "${menteeSkillsPhp}"/>
						PHP<br>
						<form:checkbox name="menteeskills" path="menteeSkillsJavaScript"
							value="true" checked = "${menteeSkillsJavaScript}"/>
						JavaScript<br>
						<form:checkbox name="menteeskills" path="menteeSkillsHTML"
							value="true" checked = "${menteeSkillsHTML}"/>
						HTML<br>
						<form:checkbox name="menteeskills" path="menteeSkillsCSS"
							value="true" checked = "${menteeSkillsCSS}"/>
						CSS<br>

						<h3>Back End</h3>
						<form:checkbox name="menteeskills" path="menteeSkillsJava"
							value="true" checked = "${menteeSkillsJava}"/>
						Java<br>
						<form:checkbox name="menteeskills" path="menteeSkillsJsp"
							value="true" checked = "${menteeSkillsJsp}"/>
						JSP<br>
						<form:checkbox name="menteeskills" path="menteeSkillsJstl"
							value="true" checked = "${menteeSkillsJstl}"/>
						JSTL<br>
						<form:checkbox name="menteeskills" path="menteeSkillsSql"
							value="true" checked = "${menteeSkillsSql}"/>
						SQL<br>
						<form:checkbox name="menteeskills" path="menteeSkillsSpringMVC"
							value="true" checked = "${menteeSkillsSpringMVC}"/>
						Spring MVC<br>
						<form:checkbox name="menteeskills" path="menteeSkillsHibernate"
							value="true" checked = "${menteeSkillsHibernate}"/>
						Hibernate<br>
						<form:checkbox name="menteeskills" path="menteeSkillsJdbc"
							value="true" checked = "${menteeSkillsJdbc}"/>
						JDBC<br>
					</div>

					<div class="col-sm-4">
						<h2>Network</h2>
						<p>OR if you would like to just network?</p>
						<h3>Front End</h3>
						<form:checkbox name="networkingskills" path="networkingSkillsPhp"
							value="true" />
						PHP<br>
						<form:checkbox name="networkingskills"
							path="networkingSkillsJavaScript" value="true" />
						JavaScript<br>
						<form:checkbox name="networkingskills" path="networkingSkillsHTML"
							value="true" />
						HTML<br>
						<form:checkbox name="networkingskills" path="networkingSkillsCSS"
							value="true" />
						CSS<br>

						<h3>Back End</h3>
						<form:checkbox name="networkingskills" path="networkingSkillsJava"
							value="true" />
						Java<br>
						<form:checkbox name="networkingskills" path="networkingSkillsJsp"
							value="true" />
						JSP<br>
						<form:checkbox name="networkingskills" path="networkingSkillsJstl"
							value="true" />
						JSTL<br>
						<form:checkbox name="networkingskills" path="networkingSkillsSql"
							value="true" />
						SQL<br>
						<form:checkbox name="networkingskills"
							path="networkingSkillsSpringMVC" value="true" />
						Spring MVC<br>
						<form:checkbox name="networkingskills"
							path="networkingSkillsHibernate" value="true" />
						Hibernate<br>
						<form:checkbox name="networkingskills" path="networkingSkillsJdbc"
							value="true" />
						JDBC

						<h3>Extracurricular</h3>
						<form:checkbox name="networkskills" path="networkingFoodie" />
						Foodie<br>
						<form:checkbox name="networkskills" path="networkingGaming" />
						Gamer<br>
						<form:checkbox name="networkskills" path="networkingSports" />
						Sports<br>
						<form:checkbox name="networkskills" path="networkingAnime" />
						Anime<br>
						<form:checkbox name="networkskills" path="networkingFun" />
						Fun<br> <br>
					</div>
				</div>
			</div>
		</div>

		<input type="submit" value="Update" class="btn btn-primary">
		</div>
			<a class="btn btn-primary btn-dashboard" id="dashboard" href="dashboard2">Dashboard</a>


	</form:form>
	
		<!-- jQuery library -->
		<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

		<!-- Popper JS -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
		
		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
</body>
</html>