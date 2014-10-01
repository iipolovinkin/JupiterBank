<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="accounts" xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:spring="http://www.springframework.org/tags" version="2.0">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />
	<s:message code="accounts_menu_transfer" var="accountsMenuTransfer" />
	<s:message code="accounts_menu_transferTo" var="accountsMenuTransferTo" />
		
	<s:url value="?transfer" var="transferUrl" />
	<s:url value="?transferTo" var="transferUrlTo" />

	<a id="param" class="textLink" href="<c:url value="${transferUrl}" />">${accountsMenuTransfer}</a>
	<a id="param" class="textLink" href="<c:url value="${transferUrlTo}" />">${accountsMenuTransferTo}</a>
	<c:if test="${not empty client }">
		<sf:form method="POST" modelAttribute="client" id="addEmptyAccount">
			<!--  bind form to model attribute  -->
			<sf:input path="id" type="hidden" value="${client.getId()}" />
			<input name="submit" type="submit" value="Добавить счёт" />
		</sf:form>
	</c:if>

	<c:if test="${not empty accounts}">
		<br>
		<table cellspacing="0" border="1">
			<thead>
				<tr>
					<td>Identity Number</td>
					<td>Balance</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="account" items="${accounts}">
					<tr>
						<td>
							<!-- <a id="param" class="textLink"
							href="<c:url value="/accounts/${account.getId()}/" />">
							--> <c:out value="${account.getId()}" /></a>
						</td>
						<td><c:out value="${account.getBalance()}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</div>

