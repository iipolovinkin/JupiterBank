<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<s:message code="id" var="id" />
<s:message code="time" var="time" />
<s:message code="locale" var="locale" />
<s:message code="amount" var="amount" />
<s:message code="accountFrom" var="accountFrom" />
<s:message code="accountTo" var="accountTo" />
<s:message code="filter" var="filter" />
<s:message code="clear" var="clear" />
<s:message code="dateFrom" var="dateFrom" />
<s:message code="dateTo" var="dateTo" />
<div id="transactions">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />
	<sf:form method="POST" modelAttribute="formFilter" class="form-inline" id="filterTransactions">
		<!--  bind form to model attribute  -->
		<table>
			<tr>
				<td>${accountFrom}:</td>
				<td>${accountTo}:</td>
				<td>${dateFrom}:</td>
				<td>${dateTo}:</td>
			</tr>
			<tr>
				<td><sf:input path="idFrom" class="form-control" value="" /></td>
				<td><sf:input path="idTo" class="form-control" value="" /></td>
				<td>
				    <sf:input path="startTime" class="form-control date" type="text" value="" />
				</td>
				<td>
				    <sf:input path="endTime" class="form-control date" type="text" value="" />
				</td>
			</tr>
		</table>
		<br>
		<input class="btn btn-success" id="filter" name="submit" type="submit"
			value="${filter}" />
		<input class="btn btn-default" id="clear" name="clear" type="button"
			onclick="clearInput()" value="${clear}" />
	</sf:form>
	<script>
		function clearInput() {
			document.getElementById('idFrom').value = '';
			document.getElementById('idTo').value = '';
			document.getElementById('startTime').value = '';
			document.getElementById('endTime').value = '';
		}
		$(document).ready(function() {
			$('.date').datepicker({
    			format: "dd.mm.yyyy",
   				clearBtn: true,
    			todayBtn: "linked",
    			autoclose: true,
    			language: "${locale}"
    		});
         });
	</script>
	<a href="/transactions.xls?output=excel">excel</a>
	<c:if test="${paginator.getPrevPage() > 1 }">
        <a href="/transactions?page=1"><<</a>
    </c:if>
    <c:if test="${paginator.getPrevPage() > 0 }">
        <a href="/transactions?page=${paginator.getPrevPage()}">${paginator.getPrevPage()}</a>
    </c:if>
    ${paginator.getCurrentPage()}
    <c:if test="${paginator.getNextPage() > 0 }">
        <a href="/transactions?page=${paginator.getNextPage()}">${paginator.getNextPage()}</a>
    </c:if>
    <c:if test="${paginator.getCurrentPage() < (paginator.getPageCount()-1) }">
        <a href="/transactions?page=${paginator.getPageCount()}">>></a>
    </c:if>
	<c:if test="${not empty transactions}">
		<br>
		<table class="table">
			<thead>
				<tr>
					<th>${id}</th>
					<th>${accountFrom}</th>
					<th>${accountTo}</th>
					<th>${amount}</th>
					<th>${time}</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="transaction" items="${transactions}">
					<tr>
						<td><c:out value="${transaction.getId()}" /></td>
						<td><c:out value="${transaction.getSender().getId()}" /></td>
						<td><c:out value="${transaction.getReceiver().getId()}" /></td>
						<td><c:out value="${transaction.getAmount()}" /></td>
						<td><joda:format value="${transaction.getTime()}" pattern='dd.MM.yyyy HH:mm:SS' /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</div>

