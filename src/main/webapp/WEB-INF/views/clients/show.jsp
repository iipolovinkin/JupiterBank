<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="showClient" class="container">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />
	<c:if test="${not empty client}">
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
				<tr>
					<td>${client.getId()}</td>
					<td>${client.getName()}</td>
					<td><c:out value="${client.getAddress()}" /></td>
					<td><c:out value="${client.getAge()}" /></td>
				</tr>
			</tbody>
		</table>
	</c:if>
</div>

