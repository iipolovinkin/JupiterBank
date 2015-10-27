<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<s:message code="id" var="id" />
<s:message code="name" var="name" />
<s:message code="address" var="address" />
<s:message code="age" var="age" />
<div id="clients" xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:spring="http://www.springframework.org/tags" version="2.0"
	class="container">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />
	<a href="clients.xls?output=excel">excel</a>

	<c:if test="${paginator.getPrevPage() > 1 }">
        <a href="clients?page=1"><<</a>
    </c:if>
	<c:if test="${paginator.getPrevPage() > 0 }">
		<a href="clients?page=${paginator.getPrevPage()}">${paginator.getPrevPage()}</a>
	</c:if>
    ${paginator.getCurrentPage()}
    <c:if test="${paginator.getNextPage() > 0 }">
        <a href="clients?page=${paginator.getNextPage()}">${paginator.getNextPage()}</a>
    </c:if>
    <c:if test="${paginator.getCurrentPage() < (paginator.getPageCount()-1) }">
        <a href="clients?page=${paginator.getPageCount()}">>></a>
    </c:if>
	<br/>
     <form method="post" action="upload.form" enctype="multipart/form-data">
                <input type="file" name="file"/>
                <input type="submit"/>
            </form>
	<c:if test="${not empty clientList}">
		<br>
		<table class="table">
			<thead>
				<tr>
					<th>${id}</th>
					<th>${name}</th>
					<th>${address}</th>
					<th>${age}</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="client" items="${clientList}">
					<tr>
						<td><a id="param" class="textLink"
							href="<c:url value="/clients/${client.getId()}/" />"><c:out
									value="${client.getId()}" /></a></td>
						<td><a id="param" class="textLink"
							href="<c:url value="/clients/${client.getId()}/" />"><c:out
									value="${client.getFirstname()}" /></a></td>
						<td><c:out value="${client.getAddress()}" /></td>
						<td><c:out value="${client.getAge()}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</div>

