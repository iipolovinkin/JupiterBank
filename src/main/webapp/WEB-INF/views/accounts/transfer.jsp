<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="transfer">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />
	<s:message code="accountFrom" var="accountFrom" />
	<s:message code="accountTo" var="accountTo" />
	<s:message code="amount" var="amount" />
	<s:message code="Transfer" var="Transfer" />

	<sf:form method="POST" modelAttribute="broker">
		<table>

			<tr>
				<th align=left>${accountFrom}</th>
				<td><sf:input path="accountFrom" class="form-control"
						required="true" value="" /></td>

			</tr>
			<tr>
				<th align=left>${accountTo}</th>
				<td><sf:input path="accountTo" class="form-control"
						required="true" value="" /></td>
			</tr>
			<tr>
				<th align=left>${amount}</th>
				<td><sf:input path="amount" class="form-control"
						required="true" value="" /></td>
			</tr>
			<tr>
				<th></th>
				<td><br></td>
			</tr>
			<tr>
				<th></th>
				<td><input name="submit" class="btn btn-success" type="submit"
					value="${Transfer}" /></td>
			</tr>
		</table>

	</sf:form>
</div>

