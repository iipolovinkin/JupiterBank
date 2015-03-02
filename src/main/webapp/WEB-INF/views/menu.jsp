<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<s:message code="footer_home" var="footerHome" />
<s:message code="menu_header_text" var="menuHeaderText" />
<s:message code="menu_client_list" var="menuClientList" />
<s:message code="add_client" var="addClient" />
<s:message code="menu_account_list" var="menuAccountList" />
<s:message code="accounts_menu_transfer" var="accountsMenuTransfer" />
<s:message code="accounts_menu_transferTo" var="accountsMenuTransferTo" />
<s:message code="accounts_menu_transferFrom"
	var="accountsMenuTransferFrom" />
<s:url value="/accounts" var="accountsUrl" />
<s:url value="${accountsUrl}?transfer" var="transferUrl" />
<s:url value="${accountsUrl}?transferTo" var="transferUrlTo" />
<s:url value="${accountsUrl}?transferFrom" var="transferUrlFrom" />
<s:message code="menu_transaction_list" var="menuTransactionList" />

<s:message code="lang" var="lang" />
<!-- Menu -->
<div id="menuDiv">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />

	<ul class="nav nav-pills navbar">
		<!--ul class="nav nav-pills navbar navbar-fixed-top"-->
		<li class="active"><a href="<c:url value='/' />">${footerHome}</a></li>
		<li class="dropdown active"><a href="#" data-toggle="dropdown"
			role="button" id="drop4">${menuClientList}<span class="caret"></span></a>
			<ul aria-labelledby="drop4" role="menu" class="dropdown-menu"
				id="menu1">
				<li role="presentation"><a role="menuitem" tabindex="-1"
					href="/clients">${menuClientList}</a></li>
				<li role="presentation"><a role="menuitem" tabindex="-1"
					href="<c:url value="/clients?new" />">${addClient}</a></li>
				<!--li role="presentation" class="divider"></li-->
			</ul></li>
		<li class="dropdown active"><a href="#" data-toggle="dropdown"
			role="button" id="drop5">${menuAccountList}<span class="caret"></span></a>
			<ul aria-labelledby="drop5" role="menu" class="dropdown-menu"
				id="menu2">
				<li role="presentation"><a role="menuitem" tabindex="-1"
					href="${accountsUrl}">${menuAccountList}</a></li>
				<li role="presentation"><a role="menuitem" tabindex="-1"
					href="${transferUrl}">${accountsMenuTransfer}</a></li>
				<li role="presentation"><a role="menuitem" tabindex="-1"
					href="${transferUrlTo}">${accountsMenuTransferTo}</a></li>
				<li role="presentation"><a role="menuitem" tabindex="-1"
					href="${transferUrlFrom}">${accountsMenuTransferFrom}</a></li>
			</ul></li>
		<li class="dropdown active"><a href="#" data-toggle="dropdown"
			role="button" id="drop6">${menuTransactionList}<span
				class="caret"></span></a>
			<ul aria-labelledby="drop6" role="menu" class="dropdown-menu"
				id="menu3">
				<li role="presentation"><a role="menuitem" tabindex="-1"
					href="/transactions">${menuTransactionList}</a></li>
			</ul></li>
	<!-- /pills -->
	<span style="float:right" >
	${lang}:
		<a href="?lang=en_US">en</a>
		|
		<a href="?lang=ru_RU">ru</a>
	</span>
	<span style="float:right" >
    	<a href="/login">login</a>
    	|
    	<a href="/logout">logout</a>
    </span>
	</ul>
</div>