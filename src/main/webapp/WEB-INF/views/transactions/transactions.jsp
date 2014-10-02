<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="transactions" xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:spring="http://www.springframework.org/tags" version="2.0">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />
	<spring:message code="menu_header_text" var="menuHeaderText" />
	<spring:message code="menu_add_client" var="menuAddClient" />
	<sf:form method="POST" modelAttribute="formFilter"
		id="filterTransactions">
		<!--  bind form to model attribute  -->
		<table>
			<tr>
				<td>idFrom:</td>
				<td>idTo:</td>
				<td>startTime:</td>
				<td>endTime:</td>
			</tr>
			<tr>
				<td><sf:input path="idFrom" value="" /></td>
				<td><sf:input path="idTo" value="" /></td>
				<td><sf:input path="startTime" disabled="true" value="" /></td>
				<td><sf:input path="endTime" disabled="true" value="" /></td>
			</tr>
		</table>
		<br><input id="filter" name="submit" type="submit" value="Filter" />
		<input id="clear" name="clear" type="button" onclick="clearInput()"
			value="Clear" />
	</sf:form>
	<script>
		function clearInput() {
			document.getElementById('idFrom').value = '';
			document.getElementById('idTo').value = '';
			document.getElementById('startTime').value = '';
			document.getElementById('endTime').value = '';
		}
	</script>
	<c:if test="${not empty transactions}">
		<br>
		<table cellspacing="0" border="1">
			<thead>
				<tr>
					<td>Identity Number</td>
					<td>Account From</td>
					<td>Account To</td>
					<td>Amount</td>
					<td>Time</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="transaction" items="${transactions}">
					<tr>
						<td><c:out value="${transaction.getId()}" /></td>
						<td><c:out value="${transaction.getSender().getId()}" /></td>
						<td><c:out value="${transaction.getReciver().getId()}" /></td>
						<td><c:out value="${transaction.getAmount()}" /></td>
						<td><c:out value="${transaction.getTime()}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</div>

