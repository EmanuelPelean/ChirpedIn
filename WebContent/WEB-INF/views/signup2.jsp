<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sign Up</title>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
	<link href="resources/signupstyle2.css" type="text/css" rel="stylesheet">
</head>
<body>

<div id="number1">
	<div class="title">
		<h2 class="h2">Signup Now!</h2>
	</div>
	
	<form:form action="signup" method="post" modelAttribute="newUserDto">
	<div id="signup">
			<form:input type="text" name="id" placeholder="id" path="linkedInId" value="${data.linkedInId}"></form:input>
			<form:input type="text" name="firstName" placeholder="First Name" path="linkedInFirstName" value="${data.linkedInFirstName}"></form:input>
			<form:input type="text" name="lastName" placeholder="Last Name" path="linkedInLastName" value="${data.linkedInLastName}"></form:input>
			<form:input type="email" name="email" placeholder="E-mail" path="linkedInEmail" value="${data.linkedInEmail}"></form:input>
	</div>
	 
	<input type="submit" value="Submit" class="btn btn-primary">
</div>

<div id="number2">
	<div class="find">
        <div class="top-row">
		  <h2>FIND<br>a mentor</h2>
		  <p>For which skills?</p>
        </div>
		<div class="skills">
			<h3>Front-End</h3>
				<div>
				    <form:checkbox path="mentorSkillsPhp" name="mentorSkillsPhp" value="true"/>
    				<label for="mentorSkillsPhp">PHP</label>
    			</div>
    			<div>
	    			<form:checkbox path="mentorSkillsJavaScript" name="mentorSkillsJavaScript" value="true"/>
	    			<label for="mentorSkillsJavaScript">Javascript</label>
    			</div>
    			<div>
	    			<form:checkbox path="mentorSkillsHTML" name="mentorSkillsHTML" value="true"/>
	    			<label for="mentorSkillsHTML">HTML</label>
    			</div>
    			<div>
	    			<form:checkbox path="mentorSkillsCSS" name="mentorSkillsCSS" value="true"/>
	    			<label for="mentorSkillsCSS">CSS</label>
    			</div>
		</div>
		<div class="skills">
			<h3>Back-End</h3>
				<div>
				    <form:checkbox path="mentorSkillsJava" name="mentorSkillsJava" value="true"/>
	    			<label for="mentorSkillsJava">Java</label>
    			</div>
    			<div>
	    			<form:checkbox path="mentorSkillsJsp" name="mentorSkillsJsp" value="true"/>
	    			<label for="mentorSkillsJsp">JSP</label>
    			</div>
    			<div>
	    			<form:checkbox path="mentorSkillsJstl" name="mentorSkillsJstl" value="true"/>
	    			<label for="mentorSkillsJstl">JSTL</label>
    			</div>
    			<div>
	    			<form:checkbox path="mentorSkillsSql" name="mentorSkillsSql" value="true"/>
	    			<label for="mentorSkillsSql">SQL</label>
	   			</div>
	   			<div>
	    			<form:checkbox path="mentorSkillsSpringMVC" name="mentorSkillsSpringMVC" value="true"/>
	    			<label for="mentorSkillsSpringMVC">Spring MVC</label>
	   			</div>
	   			<div>
	    			<form:checkbox path="mentorSkillsHibernate" name="mentorSkillsHibernate" value="true"/>
	    			<label for="mentorSkillsHibernate">Hibernate</label>
	   			</div>
					<div>
	    			<form:checkbox path="mentorSkillsJdbc" name="mentorSkillsJdbc" value="true"/>
	    			<label for="mentorSkillsJdbc">JDBC</label>
	   			</div>
		</div>
	</div>
    
    <div class="be">
		<h2>BE<br>a mentor</h2>
		<p>For which skills?</p>
		<div class="skills">
			<h3>Front-End</h3>
				<div>
				    <form:checkbox  path="menteeSkillsPhp" name="menteeSkillsPhp" value="true"/>
    				<label for="menteeSkillsPhp">PHP</label>
    			</div>
    			<div>
	    			<form:checkbox path="menteeSkillsJavaScript" name="menteeSkillsJavaScript" value="true"/>
	    			<label for="menteeSkillsJavaScript">Javascript</label>
    			</div>
    			<div>
	    			<form:checkbox path="menteeSkillsHTML" name="menteeSkillsHTML" value="true"/>
	    			<label for="menteeSkillsHTML">HTML</label>
    			</div>
    			<div>
	    			<form:checkbox path="menteeSkillsCSS" name="menteeSkillsCSS" value="true"/>
	    			<label for="menteeSkillsCSS">CSS</label>
    			</div>
		</div>
		<div class="skills">
			<h3>Back-End</h3>
				<div>
				    <form:checkbox  path="menteeSkillsJava" name="menteeSkillsJava" value="true"/>
	    			<label for="menteeSkillsJava">Java</label>
    			</div>
    			<div>
	    			<form:checkbox path="menteeSkillsJsp" name="menteeSkillsJsp" value="true"/>
	    			<label for="menteeSkillsJsp">JSP</label>
    			</div>
    			<div>
	    			<form:checkbox path="menteeSkillsJstl" name="menteeSkillsJstl" value="true"/>
	    			<label for="menteeSkillsJstl">JSTL</label>
    			</div>
    			<div>
	    			<form:checkbox path="menteeSkillsSql" name="menteeSkillsSql" value="true"/>
	    			<label for="menteeSkillsSql">SQL</label>
	   			</div>
	   			<div>
	    			<form:checkbox path="menteeSkillsSpringMVC" name="menteeSkillsSpringMVC" value="true"/>
	    			<label for="menteeSkillsSpringMVC">Spring MVC</label>
	   			</div>
	   			<div>
	    			<form:checkbox path="menteeSkillsHibernate" name="menteeSkillsHibernate" value="true"/>
	    			<label for="menteeSkillsHibernate">Hibernate</label>
	   			</div>
					<div>
	    			<form:checkbox path="menteeSkillsJdbc" name="menteeSkillsJdbc" value="true"/>
	    			<label for="menteeSkillsJdbc">JDBC</label>
	   			</div>
		</div>
	</div>
    
    <div class="networking">
		<h2>Network<br>&nbsp;</h2>
		<p>For which interests?</p>
		<div class="skills">
			<h3>Front-End</h3>
				<div>
				    <form:checkbox path="networkingSkillsPhp" name="networkingSkillsPhp" value="true"/>
    				<label for="networkingSkillsPhp">PHP</label>
    			</div>
    			<div>
	    			<form:checkbox path="networkingSkillsJavaScript" name="networkingSkillsJavaScript" value="true"/>
	    			<label for="networkingSkillsJavaScript">Javascript</label>
    			</div>
    			<div>
	    			<form:checkbox path="networkingSkillsHTML" name="networkingSkillsHTML" value="true"/>
	    			<label for="networkingSkillsHTML">HTML</label>
    			</div>
    			<div>
	    			<form:checkbox path="networkingSkillsCSS" name="networkingSkillsCSS" value="true"/>
	    			<label for="networkingSkillsCSS">CSS</label>
    			</div>
		</div>
		<div class="skills">
			<h3>Back-End</h3>
				<div>
				    <form:checkbox path="networkingskillsJava" name="networkingSkillsJava" value="true"/>
	    			<label for="networkingSkillsJava">Java</label>
    			</div>
    			<div>
	    			<form:checkbox path="networkingSkillsJsp" name="networkingSkillsJsp" value="true"/>
	    			<label for="networkingSkillsJsp">JSP</label>
    			</div>
    			<div>
	    			<form:checkbox path="networkingSkillsJstl" name="networkingSkillsJstl" value="true"/>
	    			<label for="networkingSkillsJstl">JSTL</label>
    			</div>
    			<div>
	    			<form:checkbox path="networkingSkillsSql" name="networkingSkillsSql" value="true"/>
	    			<label for="networkingSkillsSql">SQL</label>
	   			</div>
	   			<div>
	    			<form:checkbox path="networkingSkillsSpringMVC" name="networkingSkillsSpringMVC" value="true"/>
	    			<label for="networkingSkillsSpringMVC">Spring MVC</label>
	   			</div>
	   			<div>
	    			<form:checkbox path="networkingSkillsHibernate" name="networkingSkillsHibernate" value="true"/>
	    			<label for="networkingSkillsHibernate">Hibernate</label>
	   			</div>
					<div>
	    			<form:checkbox path="networkingSkillsJdbc" name="networkingSkillsJdbc" value="true"/>
	    			<label for="networkingSkillsJdbc">JDBC</label>
	   			</div>
	   	</div>		
	   	<div class="skills">
	   			<h3>Extracurricular</h3>
	   				<div>
						<form:checkbox path="networkingFoodie" name="networkingskills" value="true"/>
						<label for="networkingSkillsFoodie">Foodie</label>
					</div>
					<div>
						<form:checkbox path="networkingGaming" name="networkingskills" value="true"/>
						<label for="networkingSkillsGaming">Gamer</label>
					</div>
					<div>
						<form:checkbox path="networkingSports" name="networkingskills" value="true"/>
						<label for="networkingSkillsSports">Sportsball</label>
					</div>
					<div>
						<form:checkbox path="networkingAnime" name="networkingskills" value="true"/>
						<label for="networkingSkillsAnime">Anime</label>
					</div>
					<div>
						<form:checkbox path="networkingFun" name="networkingskills" value="true"/>
						<label for="networkingSkillsFun">Fun</label>
					</div>
		</div>
		</div>
		</div>
</form:form>
</body>
</html>