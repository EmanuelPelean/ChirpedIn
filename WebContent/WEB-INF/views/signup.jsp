<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Sign Up Page
<form:form action="signup" method="post" modelAttribute="command">
<form:input type="text" name="firstname" path="firstname"></form:input>
<button type="submit">Sign Up</button>
</form:form>

<!DOCTYPE html>
<html>
<body>

<p>For which skills would you like to HAVE a mentor?</p>

<form action="/signup">
<input type="checkbox" name="mentorskills" value="java"> Java<br>
<input type="checkbox" name="mentorskills" value="php"> PHP<br>
<br>
<input type="button" onclick="saveSkills(mentorskills)" value="Save Mentee Data">
<br><br>
<input type="text" id="order" size="50">
<input type="submit" value="Submit">
</form>
  
  
  <p>For which skills would you like to BE a mentor?</p>

<form action="/signup">
<input type="checkbox" name="menteeskills" value="java"> Java<br>
<input type="checkbox" name="menteeskills" value="php"> PHP<br>
<br>
<input type="button" onclick="saveSkills(mentorskills)" value="Save Mentoring Data">
<br><br>
<input type="text" id="order" size="50">
<input type="submit" value="Submit">
</form>
  
  

<script>
function saveSkills(object) {
    var object = document.forms[0];
    var txt = "";
    var i;
    for (i = 0; i < object.length; i++) {
        if (object[i].checked) {
            txt = txt + object[i].value + " ";
        }
    }
    document.getElementById("order").value = "A mentor for: " + txt;
}
</script>

</body>
</html>

</body>
</html>