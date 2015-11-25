<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<s:message code="show_account" var="show_account"/>
<s:message code="id" var="id"/>
<s:message code="accountNo" var="accountNo"/>
<s:message code="amount" var="amount"/>
<s:message code="state" var="state"/>
<s:message code="save" var="save"/>
<div id="showAccount" class="container">
	<jsp:directive.page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" />
	<sf:form method="POST" modelAttribute="account" class="form-vertical">
    		<!--  bind form to model attribute  -->
    		<fieldset>
    			<legend>${show_account}</legend>
    			<table cellspacing="0" border="0">
    				<tr>
    					<th align="left"><label for="id">${id}:</label></th>
    					<td><sf:input path="id" id="id" class="form-control" readonly="true"/></</td>
    				</tr>
    				<tr>
    					<th align=left><label for="accountNo">${accountNo}:</label></th>
    					<td><sf:input path="accountNo" id="accountNo"
    							class="form-control" readonly="true"/></td>
    				</tr>
    				<tr>
    					<th align=left><label for="state">${state}:</state></th>
    					<td><sf:input path="state" id="state" class="form-control"
    							required="true" readonly="true"/></td>
    				</tr>
    				<tr>
    					<th align=left><label for="balance">${amount}:</state></th>
    					<td><sf:input path="balance" id="balance" class="form-control"
    							required="true" readonly="true"/></td>
    				</tr>
    				<tr>
    					<th></th>
    					<td><input class="btn btn-success" id="save" type="submit" en
    						value="${save}" disabled="true"/></td>
    				</tr>
    			</table>
    		</fieldset>
    	</sf:form>
</div>

