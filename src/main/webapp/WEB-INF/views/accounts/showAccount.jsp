<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<s:message code="id" var="id" />
<s:message code="accountNo" var="accountNo" />
<s:message code="amount" var="amount" />
<s:message code="state" var="state" />
<div id="showAccount" class="container">
	<jsp:directive.page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" />
	<c:if test="${not empty account}">
		<table class="table" id="tableAccount">
			<thead>
				<tr>
					<th>${id}</th>
					<th>${accountNo}</th>
					<th>${state}</th>
					<th>${amount}</th>
					<th>${client}</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${account.getId()}</td>
					<td>${account.getAccountNo()}</td>
					<td>${account.getState()}</td>
					<td>${account.getBalance()}</td>
					<td>${account.getOwner().getId()}</td>
				</tr>
			</tbody>
		</table>
	</c:if>
</div>

