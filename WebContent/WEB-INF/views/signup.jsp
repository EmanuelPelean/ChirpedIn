<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
</head>
<body>
	
	Sign Up Page
	<form:form action="signup" method="post" modelAttribute="command">
		<form:input type="text" name="linkedInId" path="linkedInId" value="${data.linkedInId }"></form:input><br/>
		<form:input type="text" name="linkedInFirstName" path="linkedInFirstName" value="${data.linkedInFirstName }"></form:input><br/>
		<form:input type="text" name="linkedInLastName" path="linkedInLastName" value="${data.linkedInLastName }"></form:input><br/>
		<form:input type="text" name="linkedInHeadline" path="linkedInHeadline" value="${data.linkedInHeadline }"></form:input><br/>
		<form:input type="text" name="linkedInLocation" path="linkedInLocation" value="${data.linkedInLocation }"></form:input><br/>
		<form:input type="text" name="linkedInSummary" path="linkedInSummary" value="${data.linkedInSummary }"></form:input><br/>
		<form:input type="text" name="linkedInPictureUrl" path="linkedInPictureUrl" value="${data.linkedInPictureUrl }"></form:input><br/>
		<form:input type="text" name="linkedInLargePictureUrl" path="linkedInLargePictureUrl" value="${data.linkedInLargePictureUrl }"></form:input><br/>
		<form:input type="text" name="linkedInPublicProfileUrl" path="linkedInPublicProfileUrl" value="${data.linkedInPublicProfileUrl }"></form:input><br/>
		<form:input type="text" name="linkedInEmail" path="linkedInEmail" value="${data.linkedInEmail }"></form:input><br/>

	
	<h2>MENTEE SECTION</h2>
	<p>For which skills would you like to HAVE a mentor?</p>
		<h3>Front End</h3>
		<form:checkbox path="mentorSkillsPhp" value="true"/> PHP <br>
		<form:checkbox path="mentorSkillsJavaScript" value="true"/> JavaScript<br>
		<form:checkbox name="mentorskills" path="mentorSkillsHTML" value="true"/> HTML<br>
		<form:checkbox name="mentorskills" path="mentorSkillsCSS" value="true"/> CSS<br>
		

		<h3>Back End</h3>
		<form:checkbox name="mentorskills" path="mentorSkillsJava" value="true"/> Java<br>
		<form:checkbox name="mentorskills" path="mentorSkillsJsp" value="true"/> JSP<br>
		<form:checkbox name="mentorskills" path="mentorSkillsJstl" value="true"/> JSTL<br>
		<form:checkbox name="mentorskills" path="mentorSkillsSql" value="true"/> SQL<br>
		<form:checkbox name="mentorskills" path="mentorSkillsSpringMVC" value="true"/> Spring MVC<br>
		<form:checkbox name="mentorskills" path="mentorSkillsHibernate" value="true"/> Hibernate<br>
		<form:checkbox name="mentorskills" path="mentorSkillsJdbc" value="true"/> JDBC<br>
	<!-- <input type="button" onclick="saveSkills(mentorskills, 'mentor')"
			value="Save Mentee Data"> <br>
		<br> <input type="text" id="mentorSkillsSummary" size="50">
		<input type="submit" value="Submit">  -->	


	
	<h2>MENTOR SECTION</h2>
	<p>For which skills would you like to BE a mentor?</p>
		<h3>Front End</h3>
		<form:checkbox name="menteeskills" path="menteeSkillsPhp" value="true"/> PHP<br>
		<form:checkbox name="menteeskills" path="menteeSkillsJavaScript" value="true"/> JavaScript<br>
		<form:checkbox name="menteeskills" path="menteeSkillsHTML" value="true"/> HTML<br>
		<form:checkbox name="menteeskills" path="menteeSkillsCSS" value="true"/> CSS<br>
		

		<h3>Back End</h3>
		<form:checkbox name="menteeskills" path="menteeSkillsJava" value="true"/> Java<br>
		<form:checkbox name="menteeskills" path="menteeSkillsJsp" value="true"/> JSP<br>
		<form:checkbox name="menteeskills" path="menteeSkillsJstl" value="true"/> JSTL<br>
		<form:checkbox name="menteeskills" path="menteeSkillsSql" value="true"/> SQL<br>
		<form:checkbox name="menteeskills" path="menteeSkillsSpringMVC" value="true"/> Spring MVC<br>
		<form:checkbox name="menteeskills" path="menteeSkillsHibernate" value="true"/> Hibernate<br>
		<form:checkbox name="menteeskills" path="menteeSkillsJdbc" value="true"/> JDBC<br>
		<!-- <input type="button" onclick="saveSkills(menteeskills, 'mentee')"
			value="Save Mentor Data"> <br>
		<br> <input type="text" id="menteeSkillsSummary" size="50">
		<input type="submit" value="Submit">  -->

	<h2>NETWORKING SECTION</h2>
	<p>OR if you would like to just network?</p>
		<h3>Extracurricular</h3>
		<form:checkbox name="networkskills" path="networkingFoodie"/> Foodie<br>
		<form:checkbox name="networkskills" path="networkingGaming"/> Gamer<br>
		<form:checkbox name="networkskills" path="networkingSports"/> Sports<br>
		<form:checkbox name="networkskills" path="networkingAnime"/> Anime<br>
		<form:checkbox name="networkskills" path="networkingFun"/> Fun<br> 
		<br>
		
		<h3>Front End</h3>
		<form:checkbox name="networkingskills" path="networkingSkillsPhp" value="true"/> PHP<br>
		<form:checkbox name="networkingskills" path="networkingSkillsJavaScript" value="true"/> JavaScript<br>
		<form:checkbox name="networkingskills" path="networkingSkillsHTML" value="true"/> HTML<br>
		<form:checkbox name="networkingskills" path="networkingSkillsCSS" value="true"/> CSS<br>
		

		<h3>Back End</h3>
		<form:checkbox name="networkingskills" path="networkingSkillsJava" value="true"/> Java<br>
		<form:checkbox name="networkingskills" path="networkingSkillsJsp" value="true"/> JSP<br>
		<form:checkbox name="networkingskills" path="networkingSkillsJstl" value="true"/> JSTL<br>
		<form:checkbox name="networkingskills" path="networkingSkillsSql" value="true"/> SQL<br>
		<form:checkbox name="networkingskills" path="networkingSkillsSpringMVC" value="true"/> Spring MVC<br>
		<form:checkbox name="networkingskills" path="networkingSkillsHibernate" value="true"/> Hibernate<br>
				<form:checkbox name="networkingskills" path="networkingSkillsJdbc" value="true"/> JDBC

		<input type="button" onclick="saveSkills(networkskills, 'network')"
			value="Save Networker Data"> <br>
		<br> <input type="text" id="networkSkillsSummary" size="50">
		<input type="submit" value="Submit">
		
		<button type="submit">Update</button>
	</form:form>

	<script>
		function saveSkills(object, context) {
			var index;
			console.log(document.forms);
			if (context === "mentor") {
				index = 1;
			}else if (context === "mentee"){
				index = 2;
			}else {
				index = 3;
			}
			var object = document.forms[index];
			var txt = "";
			var i;
			for (i = 0; i < object.length; i++) {
				if (object[i].checked) {
					txt = txt + object[i].value + " ";
					console.log(txt);
				}
			}

			if (context === "mentor") {
				document.getElementById("mentorSkillsSummary").value = "A mentor for: "
						+ txt;
			}else if (context === "mentee") {
				document.getElementById("menteeSkillsSummary").value = "A mentee for: "
					+ txt;
			}else {
				document.getElementById("networkSkillsSummary").value = "A networkee for: "
					+ txt;
			}
		}
	</script>
	
</body>
</html>