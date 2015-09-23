<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<s:message code="id" var="id" />
<s:message code="name" var="name" />
<s:message code="address" var="address" />
<s:message code="age" var="age" />
<div id="showClient" class="container">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />
	<c:if test="${not empty client}">
	<a id="param" class="textLink" href="<c:url value="/clients/${client.getId()}.xml?output=xml" />">xml</a>
		<table class="table">
			<thead>
				<tr>
					<th>${id}</th>
					<th>${name}</th>
					<th>${address}</th>
					<th>${age}</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${client.getId()}</td>
					<td>${client.getFirstname()}</td>
					<td><c:out value="${client.getAddress()}" /></td>
					<td><c:out value="${client.getAge()}" /></td>
				</tr>
			</tbody>
		</table>
	</c:if>
</div>

