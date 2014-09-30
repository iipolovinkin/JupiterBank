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
	<s:message code="accountFrom" var="accountFrom" />
	<s:message code="accountTo" var="accountTo" />
	<s:message code="amount" var="amount" />
	<s:message code="Transfer" var="Transfer" />

	<sf:form method="POST" modelAttribute="broker">
		<table cellspacing="0" border="1">

			<tr>
				<th align = left>${accountFrom}</th>
				<td><sf:input path="accountFrom" size="40" value="" /></td>

			</tr>
			<tr>
				<th align = left>${accountTo}</th>
				<td><sf:input path="accountTo" size="40" value="" /></td>
			</tr>
			<tr>
				<th align = left>${amount}</th>
				<td><sf:input path="amount" size="40" value="" /></td>
			</tr>
		</table>
		<input name="submit" type="submit" value="${Transfer}" />
	</sf:form>
</div>

