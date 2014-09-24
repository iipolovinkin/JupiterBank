<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="footer" xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:spring="http://www.springframework.org/tags" version="2.0">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />
	<spring:message code="home_text" var="homeText" />
	<spring:url value="/clients" var="homeUrl" />
	<h3>Footer</h3>
	<a href="${homeUrl}"><h3>${homeText}</h3></a> <a id="param"
		class="textLink" href="<c:url value="/" />">Home</a>
</div>