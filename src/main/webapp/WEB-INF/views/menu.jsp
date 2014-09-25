<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="menu" xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:spring="http://www.springframework.org/tags" version="2.0">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />
	<s:message code="menu_header_text" var="menuHeaderText" />
	<s:message code="menu_add_client" var="menuAddClient" />
	<s:url value="/clients?new" var="addClientUrl" />
	<h3>Menu:${menuHeaderText}</h3>
	<a href="${addClientUrl}"><h3>${menuAddClient}</h3></a><br /> <a
		id="param" class="textLink" href="<c:url value="/clients" />">List
		of clients</a>
</div>