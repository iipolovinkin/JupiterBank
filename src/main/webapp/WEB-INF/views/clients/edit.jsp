<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="editClient" class="container">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />

	<sf:form method="POST" modelAttribute="client" class="form-vertical">
		<!--  bind form to model attribute  -->
		<fieldset>
			<legend>Create Client profile</legend>
			<table cellspacing="0" border="0">
				<tr>
					<th align="left"><label for="client_name">Client name:</label></th>
					<td><sf:input path="name" id="client_name"
							class="form-control" required="true" /> <sf:errors path="name"
							cssClass="error" /></td>
				</tr>
				<tr>
					<th align=left><label for="client_address">Client
							address:</label></th>
					<td><sf:input path="address" id="client_address"
							class="form-control" required="true" /> <sf:errors path="address"
							cssClass="error" /></td>
				</tr>
				<tr>
					<th align=left><label for="client_age">Client age:</label></th>
					<td><sf:input path="age" id="client_age" class="form-control"
							required="true" /><small id="client_age_msg">Between 0
							and 150, please.</small> <sf:errors path="age" cssClass="error" /></td>
				</tr>
				<tr>
					<th></th>
					<td><input class="btn btn-success" name="commit" type="submit"
						value="Create Client" /></td>
				</tr>
			</table>
		</fieldset>
	</sf:form>
</div>

