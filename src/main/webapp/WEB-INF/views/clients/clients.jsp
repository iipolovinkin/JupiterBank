<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="clients" xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:spring="http://www.springframework.org/tags" version="2.0"
	class="container">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />
	<c:if test="${not empty clientList}">
		<br>
		<table class="table">
			<thead>
				<tr>
					<th>Identity Number</th>
					<th>Name</th>
					<th>Address</th>
					<th>Age</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="client" items="${clientList}">
					<tr>
						<td><a id="param" class="textLink"
							href="<c:url value="/clients/${client.getId()}/" />"><c:out
									value="${client.getId()}" /></a></td>
						<td><a id="param" class="textLink"
							href="<c:url value="/clients/${client.getId()}/" />"><c:out
									value="${client.getFirstname()}" /></a></td>
						<td><c:out value="${client.getAddress()}" /></td>
						<td><c:out value="${client.getAge()}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</div>

