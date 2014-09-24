<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="clients" xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:spring="http://www.springframework.org/tags" version="2.0">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />
	<spring:message code="menu_header_text" var="menuHeaderText" />
	<spring:message code="menu_add_client" var="menuAddClient" />
	<spring:message code="menu_add_client" var="menuAddClient" />
	<c:if test="${not empty clientList}">
		<table border="1">
			<thead>
				<tr>
					<td>Identity Number</td>
					<td>Name</td>
					<td>Address</td>
					<td>Age</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="client" items="${clientList}">
					<tr>
						<td><a id="param" class="textLink"
							href="<c:url value="/client/${client.getId()}/" />"><c:out
									value="${client.getId()}" /></a></td>
						<td><a id="param" class="textLink"
							href="<c:url value="/client/${client.getId()}/" />"><c:out
									value="${client.getName()}" /></a></td>
						<td><c:out value="${client.getAddress()}" /></td>
						<td><c:out value="${client.getAge()}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</div>

