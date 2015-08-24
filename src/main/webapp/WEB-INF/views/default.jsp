<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<s:message code="main_title" var="mainTitle" />
<jsp:directive.page contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<title>${mainTitle}</title>
<link href="<c:url value="/resources/css/messages/messages.css" />"
	rel="stylesheet" type="text/css" />
<!--  Bootstrap  -->
<link href="<c:url value="/webjars/bootstrap/3.3.5/css/bootstrap.min.css" />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/webjars/bootstrap/3.3.5/css/bootstrap-theme.min.css" />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/webjars/bootstrap/3.3.5/css/bootstrap-theme.css.map" />"
    rel="stylesheet" type="text/css" />
<link href="<c:url value="/webjars/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.css" />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/webjars/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker3.css" />"
	rel="stylesheet" type="text/css" />

<!--  JQuery  -->
<script src="/webjars/jquery/2.1.4/jquery.min.js"></script>
<script>
	window.jQuery
			|| document.write('<script src="/var/js/jquery.min.js"><\/script>')
</script>

<!--  Bootstrap  -->
<script src="/webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!--  <script src="/webjars/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.js"></script> -->
<script src="/resources/js/bootstrap-datepicker.js"></script>
<!-- use if -->
<script src="/webjars/bootstrap-datepicker/1.4.0/locales/bootstrap-datepicker.ru.min.js"></script>
<script>
	window.jQuery
			|| document.write('<script src="/var/js/bootstrap.min.js"><\/script>');
	$(document).ready(function() {
        $('.date').datepicker({
            format: "dd.mm.yyyy",
            clearBtn: true,
            todayBtn: "linked",		autoclose: true,
            language: "${locale}"
        });
        $("#message").fadeOut("fast");
        $("#message").fadeIn("fast");
     });
</script>
</head>
<body class="container">
	<div id="headerWrapper">
		<tiles:insertAttribute name="header" ignore="true" />
	</div>
	<div id="wrapper">
		<tiles:insertAttribute name="menu" ignore="true" />
		<div id="main">
			<tiles:insertAttribute name="body" />
			<tiles:insertAttribute name="body2" ignore="true" />
			<tiles:insertAttribute name="footer" ignore="true" />
		</div>
	</div>
</body>
<script>

</script>
</html>
