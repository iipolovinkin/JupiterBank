<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="clientInfo" xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:spring="http://www.springframework.org/tags" version="2.0">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />
	<spring:message code="menu_header_text" var="menuHeaderText" />
	<spring:message code="menu_add_client" var="menuAddClient" />
	<c:if test="${not empty client }">
		<sf:form method="POST" modelAttribute="client"
			id="addEmptytransaction">
			<!--  bind form to model attribute  -->
			<sf:input path="id" type="hidden" value="${client.getId()}" />
			<input name="submit" type="submit" value="Добавить счёт" />
		</sf:form>
	</c:if>

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

