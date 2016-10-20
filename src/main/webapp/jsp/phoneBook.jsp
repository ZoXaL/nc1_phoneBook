<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<link rel="stylesheet" type="text/css" href="css/common.css">
<body>
<h2>Hello World! Waiting to process java code...</h2>
<form action="app" method="GET"> 

	<select name="name">
		<c:forEach items="${names}" var="name">
			<option>${name}</option>
		</c:forEach>
	</select>
	<input type="submit" value="Get phone number"/>
</form>
<br>
<jsp:text>${phoneNumber}</jsp:text>
</body>
</html>
