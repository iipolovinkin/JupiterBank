<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<div id="footer" xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:spring="http://www.springframework.org/tags" version="2.0">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />
	<s:message code="footer_home" var="footerHome" />

	<h3>Footer</h3>
	<a id="param" class="textLink" href="<c:url value='/' />">${footerHome}</a>
</div>