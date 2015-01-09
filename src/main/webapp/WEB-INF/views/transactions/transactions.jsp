<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="transactions">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />
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
				<td><sf:input path="idFrom" class="form-control" value="" /></td>
				<td><sf:input path="idTo" class="form-control" value="" /></td>
				<td><sf:input path="startTime" class="form-control"
						disabled="true" value="" /></td>
				<td><sf:input path="endTime" class="form-control"
						disabled="true" value="" /></td>
			</tr>
		</table>
		<br>
		<input class="btn btn-success" id="filter" name="submit" type="submit"
			value="Filter" />
		<input class="btn btn-default" id="clear" name="clear" type="button"
			onclick="clearInput()" value="Clear" />
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
		<table class="table">
			<thead>
				<tr>
					<th>Identity Number</th>
					<th>Account From</th>
					<th>Account To</th>
					<th>Amount</th>
					<th>Time</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="transaction" items="${transactions}">
					<tr>
						<td><c:out value="${transaction.getId()}" /></td>
						<td><c:out value="${transaction.getSender().getId()}" /></td>
						<td><c:out value="${transaction.getReceiver().getId()}" /></td>
						<td><c:out value="${transaction.getAmount()}" /></td>
						<td><c:out value="${transaction.getTime()}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</div>

