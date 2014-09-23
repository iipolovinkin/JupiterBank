<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>JupiterBank-Home</title>
</head>
<body>
	<h1>Client List</h1>

	<a id="param" class="textLink" href="<c:url value="/" />">Home</a>

	<table border="1">
		<c:forEach var="client" items="${clientList}">
			<tr>
				<td><c:out value="${client.toString()}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
