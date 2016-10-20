<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<link rel="stylesheet" type="text/css" href="css/common.css">
<body>
<div class="center-block center-content">
<h2>Select the person name in the list below to get phone number.</h2>
<form action="app" method="GET"> 
	<select name="personName">
		<c:forEach items="${names}" var="name">
			<c:choose>
			  <c:when test="${personName == name}">
			    <option selected>${name}</option>
			  </c:when>
			  <c:otherwise>
			    <option >${name}</option>
			  </c:otherwise>
			</c:choose>
			
		</c:forEach>
	</select>
	<input type="submit" value="Get phone number"/>
</form>
<br>

<c:if test="${phoneNumber!=null}">
   <jsp:text>${personName}'s phone number is: ${phoneNumber}</jsp:text>
</c:if>

</div>

</body>
</html>
