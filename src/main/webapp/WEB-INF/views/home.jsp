<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>JupiterBank-Home</title>
</head>
<body>
	<h1>Hello dear Client!</h1>

	<a id="param" class="textLink" href="<c:url value="/clients" />">List
		of clients</a>

	<P>The time on the server is ${serverTime}.</P>
</body>
</html>
