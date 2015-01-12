<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<s:message code="add_account" var="addAccount" />
<s:message code="amount" var="amount" />
<s:message code="id" var="id" />
<div id="accounts" class="container">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />
	<c:if test="${not empty client }">
		<br>
		<sf:form method="POST" modelAttribute="client" id="addEmptyAccount">
			<!--  bind form to model attribute  -->
			<sf:input path="id" type="hidden" value="${client.getId()}" />
			<input name="submit" type="submit" value="${addAccount}"
				class="btn btn-success" />
		</sf:form>
	</c:if>

	<c:if test="${not empty accounts}">
		<br>
		<table class="table">
			<thead>
				<tr>
					<th>${id}</th>
					<th>${amount}</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="account" items="${accounts}">
					<tr>
						<td>
							<!-- <a id="param" class="textLink"
							href="<c:url value="/accounts/${account.getId()}/" />">
							</a> --> <c:out value="${account.getId()}" />
						</td>
						<td><c:out value="${account.getBalance()}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</div>

