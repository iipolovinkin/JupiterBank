<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="editClient" xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:spring="http://www.springframework.org/tags" version="2.0">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />
	<h2>Create a Client profile</h2>

	<sf:form method="POST" modelAttribute="client">
		<!--  bind form to model attribute  -->
		<fieldset>
			<table cellspacing="0" border="0" alig="left">
				<tr>
					<th><label for="client_name">Client name:</label></th>
					<td><sf:input path="name" size="15" id="client_name" /></td>
				</tr>
				<tr>
					<th><label for="client_address">Client address:</label></th>
					<td><sf:input path="address" size="50" id="client_address" /></td>
				</tr>
				<tr>
					<th><label for="client_age">Client age:</label></th>
					<td><sf:input path="age" size="3" id="client_age" /> <small
						id="client_age_msg">Between 0 and 150, please.</small></td>
				</tr>
				<tr>
					<th></th>
					<td><input name="commit" type="submit"
							value="Create Client" /></td>
				</tr>
			</table>
		</fieldset>
	</sf:form>
</div>

