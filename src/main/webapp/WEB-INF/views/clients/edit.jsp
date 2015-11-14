<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<s:message code="add_client" var="addClient" />
<s:message code="client_age_msg" var="client_age_msg" />
<s:message code="name" var="name" />
<s:message code="address" var="address" />
<s:message code="age" var="age" />
<s:message code="create_client_profile" var="create_client_profile" />
<div id="editClient" class="container">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />

	<sf:form method="POST" modelAttribute="client" class="form-vertical">
		<!--  bind form to model attribute  -->
		<fieldset>
			<legend>${create_client_profile}</legend>
			<table cellspacing="0" border="0">
				<tr>
					<th align="left"><label for="client_name">${name}:</label></th>
					<td><sf:input path="firstname" id="client_name"
							class="form-control" required="true"/> <sf:errors path="firstname"
							cssClass="error" /></td>
				</tr>
				<tr>
					<th align=left><label for="client_address">${address}:</label></th>
					<td><sf:input path="address" id="client_address"
							class="form-control" required="true" /> <sf:errors path="address"
							cssClass="error" /></td>
				</tr>
				<tr>
					<th align=left><label for="client_age">${age}:</label></th>
					<td><sf:input path="age" id="client_age" class="form-control"
							required="true" /><small id="client_age_msg">${client_age_msg}</small><sf:errors path="age" cssClass="error" /></td>
				</tr>
				<tr>
					<th></th>
					<td><input class="btn btn-success" id="submit" type="submit"
						value="${addClient}" /></td>
				</tr>
			</table>
		</fieldset>
	</sf:form>
</div>

