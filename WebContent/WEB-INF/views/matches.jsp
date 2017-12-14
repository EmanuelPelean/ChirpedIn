<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Matches</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/15.1.0/react.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/15.1.0/react-dom.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-standalone/6.24.0/babel.js"></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js'></script>


<style type="text/css">
*
  box-sizing border-box
  
body
  margin 0
  font-family monospace
  
a
  color inherit
  text-decoration inherit
  
button
  cursor pointer
  
section
  display flex
  justify-content center
  align-items center
  flex-direction column
  width 100%
  height 100vh
  background-image linear-gradient(
    to bottom right,
    hsl(210, 100%, 40%),
    hsl(330, 100%, 70%)
  )
  overflow hidden
  
.controls
  margin-top 1rem
  font-weight bold
  
.next,
.prev
  position relative
  display inline-block
  margin 1rem
  border-radius 0.25rem
  background-color white
  
  &:before 
    content ''
    position absolute
    z-index 0
    top -0.5rem
    left -0.5rem
    width 100%
    height 100%
    padding 0.5rem
    background-color white
    border-radius 0.25rem
    box-shadow 0.25rem 0.25rem 0.125rem hsla(210, 0%, 0%, 0.25)
    transition background-color .25s ease
      
  & span
    position relative
    z-index 1
  
.wrapper
  position relative
  flex-shrink 0
  max-width 32rem
  width 75vmin
  max-height 32rem
  height 75vmin
  
.container
  position absolute
  top 0
  left 0
  display flex
  width 100%
  height 100%
  transition transform .5s ease
  
.item
  position relative
  flex-shrink 0
  padding 8vmin
  width 100%
  height 100%
  transform scale(0.75)
  opacity 0.75
  transition opacity .5s ease, transform .5s ease
    
  & div
    position absolute
    top 0
    left 0
    display flex
    flex-direction column
    justify-content center
    padding 16vmin
    width 100%
    height 100%
    border-radius 50%
    background-color white
    box-shadow 0.25rem 0.25rem 0.125rem hsla(210, 0%, 0%, 0.1)
    
    @media screen and (max-width 600px)
      padding 4rem
    
    @media screen and (max-width 425px)
      text-align center
      
    @media screen and (max-height 600px)
      text-align center
    
    & p
      @media screen and (max-width 425px)
        display none
      @media screen and (max-height 600px)
        display none
   
  &.active
    transform scale(1)
    opacity 1
  </style>
</head>
<body>


<section>
  <div class="wrapper">
    <div class="container">
      <a class="item active" href="item-1">
        <div>
       
	<h2>These are the best matches:</h2>
          <table border="1">
		<c:forEach var="matchedDto" items="${mentorresults}">
			<tr>
				<td> <img src="${matchedDto.linkedInPictureUrl}" style="width:100px;height:100px;"></td>
				<td>${matchedDto.linkedInFirstName}</td>
				<td>${matchedDto.linkedInLastName}</td>
				<td>${matchedDto.totalMatchPercent}</td>
				<td>Mentor Skills: ${matchedDto.matchingMentorSkills}</td>
				<td>${matchedDto.linkedInId }</td>  <!--  Pratima added for favorites-->
				<form:form method="post" modelAttribute="command" onsubmit="event.preventDefault(); return loadDoc(this);">
				<td><form:input type="hidden" name="linkedInId" path="linkedInId" value="${matchedDto.linkedInId}"></form:input><br/>
				<td><form:input type="hidden" name="newUserlinkedInId" path="newUserlinkedInId" value="${newUser.linkedInId}"></form:input><br/>
				<input type="submit" value="Add to Fav"></td>
				</form:form>
				<td></td>
				
				<!-- <td>Headline: ${matchedDto.linkedInHeadline}</td> -->
			</tr>
		</c:forEach>
		
	</table>
          <p>Mentor</p>
        </div>
      </a>
      <a class="item" href="item-2">
        <div>
          <h2>Item 2</h2>
          <p>Mentor</p>
        </div>
      </a>
      <a class="item" href="item-3">
        <div>
          <h2>Item 3</h2>
          <p>Mentor</p>
        </div>
      </a>
      <a class="item" href="item-4">
        <div>
          <h2>Item 4</h2>
          <p>Mentor</p>
        </div>
      </a>
      <a class="item" href="item-5">
        <div>
          <h2>Item 5</h2>
          <p>Mentor</p>
        </div>
      </a>
    </div>
  </div>
  <div class="controls">
    <button data-to="item-1" class="prev"><span>&lt;</span></button>
    <button data-to="item-2" class="next"><span>&gt;</span></button>
  </div>
</section>

<script>
function loadDoc(form) {
	
	
	
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("demo").innerHTML =
      this.responseText;
    }
  };
  xhttp.open("GET", "ajax_info.txt", true);
  xhttp.send();
}
</script>

</body>
</html>