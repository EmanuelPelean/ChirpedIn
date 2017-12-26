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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
<link href="resources/signupstyle.css" rel="stylesheet" />
<style>

@import url(https://fonts.googleapis.com/css?family=Raleway:400,700);



body{background:#59ABE3;
	margin:0
}

.form{width:340px;
	height:440px;
	background:#e6e6e6;
	border-radius:8px;
	box-shadow:0 0 40px -10px #000;
	margin:calc(50vh - 220px) auto;
	padding:20px 30px;
	max-width:calc(100vw - 40px);
	box-sizing:border-box;
	font-family:'Montserrat',sans-serif;
	position:relative}

.title{margin:10px 0;
	padding-bottom:10px;
	width:180px;color:#78788c;
	left: 60px;
	border-bottom:3px solid #78788c}

input{width:30%;
	padding:10px;
	box-sizing:border-box;
	background:none;
	outline:none;
	resize:none;
	border:0;
	font-family:'Montserrat',sans-serif;
	transition:all .3s;
	border-bottom:2px solid #bebed2;}

input:focus{border-bottom:2px solid #78788c}

#mydiv{content:'Hi';
	bottom:-380px;
	background:#50505a;
	right: -500px;
	color:#fff;
	width:60%;
	padding:16px 4px 16px 0;
	border-radius:6px;
	font-size:13px;
	box-shadow:10px 10px 40px -14px #000}

#mydiv2{content:'Hi';
	bottom:-380px;
	right: -400px;
	background:#50505a;
	color:#fff;
	width:25%;
	padding:16px 4px 16px 0;
	border-radius:6px;
	font-size:13px;
	box-shadow:10px 10px 40px -14px #000}

span{margin:0 5px 0 15px}

.Absolute-Center {
  margin: auto;
  position: absolute;
  top: 0; left: 0; bottom: 0; right: 15;
  border: 3px solid #73AD21;
}

.Top-Left {
   position: relative;
    left: 60px;
    top: 60px;
    border: 3px solid #73AD21;
  }

</style>
</head>
<body>
		<div class="title">
		<h2>Signup Now!</h2>
		</div>
	
		<form:form action="signup" method="post" modelAttribute="command">
		<div id="mydiv2" class="Top-Left">
		<div class="form-group form-inline justify-content-center">
		<label for="email">ID :</label>
		<form:input id="fancy-inputs" class="input form-control" type="text" name="linkedInId" path="linkedInId" value="${data.linkedInId }"></form:input><br/>
		</div>
		<div class="form-group form-inline justify-content-center">
		<label for="email">First Name :</label>
		<form:input id="fancy-inputs" class="form-control" type="text" name="linkedInFirstName" path="linkedInFirstName" value="${data.linkedInFirstName }"></form:input><br/>
		</div>
		<div class="form-group form-inline justify-content-center">
		<label for="email">Last Name :</label>
		<form:input id="fancy-inputs" class="form-control" type="text" name="linkedInLastName" path="linkedInLastName" value="${data.linkedInLastName }"></form:input><br/>
		</div>
		<div class="form-group form-inline justify-content-center">
		<label for="email">E-mail :</label>
		<form:input id="fancy-inputs" class="form-control" type="text" name="linkedInEmail" path="linkedInEmail" value="${data.linkedInEmail }"></form:input><br/>
		</div>
		</div>
			 <div class="row">
		<form:input type="hidden" name="linkedInHeadline" path="linkedInHeadline" value="${data.linkedInHeadline }"></form:input><br/>
		<form:input type="hidden" name="linkedInLocation" path="linkedInLocation" value="${data.linkedInLocation }"></form:input><br/>
		<form:input type="hidden" name="linkedInPictureUrl" path="linkedInPictureUrl" value="${data.linkedInPictureUrl }"></form:input><br/>
		<form:input type="hidden" name="linkedInLargePictureUrl" path="linkedInLargePictureUrl" value="${data.linkedInLargePictureUrl }"></form:input><br/>
		<form:input type="hidden" name="linkedInPublicProfileUrl" path="linkedInPublicProfileUrl" value="${data.linkedInPublicProfileUrl }"></form:input><br/>
		     </div>
		     
	<div id="mydiv" class="text-center Absolute-Center">
<div class="container">
  <div class="row">
    <div class="col-sm-4">
	<h2>MENTEE SECTION</h2>
	<p>For which skills would you like to HAVE a mentor?</p>
		<h3>Front End</h3>
		<form:checkbox  path="mentorSkillsPhp" value="true"/> PHP <br>
		<form:checkbox  path="mentorSkillsJavaScript" value="true"/> JavaScript<br>
		<form:checkbox  name="mentorskills" path="mentorSkillsHTML" value="true"/> HTML<br>
		<form:checkbox  name="mentorskills" path="mentorSkillsCSS" value="true"/> CSS<br>
		
		<h3>Back End</h3>
		<form:checkbox name="mentorskills" path="mentorSkillsJava" value="true"/> Java<br>
		<form:checkbox name="mentorskills" path="mentorSkillsJsp" value="true"/> JSP<br>
		<form:checkbox name="mentorskills" path="mentorSkillsJstl" value="true"/> JSTL<br>
		<form:checkbox name="mentorskills" path="mentorSkillsSql" value="true"/> SQL<br>
		<form:checkbox name="mentorskills" path="mentorSkillsSpringMVC" value="true"/> Spring MVC<br>
		<form:checkbox name="mentorskills" path="mentorSkillsHibernate" value="true"/> Hibernate<br>
		<form:checkbox name="mentorskills" path="mentorSkillsJdbc" value="true"/> JDBC<br>
		</div>

	<!-- <input type="button" onclick="saveSkills(mentorskills, 'mentor')"
			value="Save Mentee Data"> <br>
		<br> <input type="text" id="mentorSkillsSummary" size="50">
		<input type="submit" value="Submit">  -->	


	<div class="col-sm-4">
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
		</div>
		<!-- <input type="button" onclick="saveSkills(menteeskills, 'mentee')"
			value="Save Mentor Data"> <br>
		<br> <input type="text" id="menteeSkillsSummary" size="50">
		<input type="submit" value="Submit">  -->
	<div class="col-sm-4">
	<h2>NETWORKING SECTION</h2>
	<p>OR if you would like to just network?</p>
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
		
		<h3>Extracurricular</h3>
		<form:checkbox name="networkskills" path="networkingFoodie"/> Foodie<br>
		<form:checkbox name="networkskills" path="networkingGaming"/> Gamer<br>
		<form:checkbox name="networkskills" path="networkingSports"/> Sports<br>
		<form:checkbox name="networkskills" path="networkingAnime"/> Anime<br>
		<form:checkbox name="networkskills" path="networkingFun"/> Fun<br> 
		<br>
					</div>
				</div>
			</div>
			
			</div>
		<!-- <input type="button" onclick="saveSkills(networkskills, 'network')"
			value="Save Networker Data"> <br>
		<br> <input type="text" id="networkSkillsSummary" size="50">  -->
		<div class="text-left">
		<input type="submit" value="Submit" class="btn btn-primary">
		</div>
		<!-- <button type="submit">Update</button> -->
			
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