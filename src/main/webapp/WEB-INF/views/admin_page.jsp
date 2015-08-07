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
		<sf:form method="POST" modelAttribute="adminClass" class="form-vertical">
		<!--  bind form to model attribute  -->
		<fieldset>
		<table>
			<tr>
				<td>1:</td>
				<td>2:</td>
				<td>3:</td>
				<td>4:</td>
			</tr>
			<tr>
				<td><sf:input path="clientsCount" class="form-control" value="" /></td>
				<td><sf:input path="accountsCount" class="form-control" value="" /></td>
				<td><sf:input path="transfersCount" class="form-control"value="" /></td>
				<td><sf:input path="threadsCount" class="form-control" value="" /></td>
			</tr>
		</table>
		<br/>
		<input class="btn btn-success" name="commit" type="submit" value="processFourAttributes" />
		<table>
        			<tr>
        				<td>1:</td>
        				<td>2:</td>
        				<td>3:</td>
        				<td>4:</td>
        			</tr>
        			<tr>
        				<td><sf:input path="attr05" class="form-control" value="" /></td>
        				<td><sf:input path="attr06" class="form-control" value="" /></td>
        				<td><sf:input path="attr07" class="form-control date" type="text" value="" /></td>
        				<td><sf:input path="attr08" class="form-control date" type="text" value="" /></td>
        			</tr>
        		</table>
        		<br/>
        		<input class="btn btn-success" name="commit" type="submit" value="processFourAttributes" />
		</fieldset>
		</sf:form>
		<br>

	<br/>

	<script>
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
</div>

