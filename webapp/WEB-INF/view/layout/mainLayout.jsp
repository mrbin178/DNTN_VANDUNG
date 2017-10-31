<%@page import="ts24.com.vn.dal.model.LoginAdmin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tile" uri="/WEB-INF/tld/tiles-jsp.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=utf-8;charset=UTF-8">
<meta http-equiv="content-language" content="vi" />
<title><tile:insertAttribute name="title" /></title>

<%-- <link rel="shortcut icon" href="<c:url value="/images/xhd_icon_32.png"/>" type="image/png" />
<link rel="shortcut icon" href="<c:url value="/images/xhd_icon.ico"/>" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/cssError.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/quicklinks.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap/bootstrap-theme.css"/>"> --%>

<!-- Bootstrap core CSS-->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom fonts for this template-->
<link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<!-- Page level plugin CSS-->
<link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
<!-- Custom styles for this template-->
<link href="css/sb-admin.css" rel="stylesheet">


<script src="<c:url value="/scripts/table.js"/>" type="text/javascript"></script>
<!--[if lt IE 9]>
  <script type="text/javascript" src="<c:url value="/scripts/jquery/jquery-1.11.1.js"/>"></script>
<![endif]-->
<!--[if gte IE 9]><!-->
<script type="text/javascript"
	src="<c:url value="/scripts/jquery/jquery-2.1.1.js"/>"></script>
<!--<![endif]-->
<script type="text/javascript" src="<c:url value="/scripts/common.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/scripts/jquery/jquery-ui-1.10.3.custom.js"/>"></script>
<script src="<c:url value="/scripts/buttons.js"/>"
	type="text/javascript"></script>
<script src="<c:url value="/scripts/util.js"/>" type="text/javascript"></script>
<%-- <script type="text/javascript" src="<c:url value="/ckeditor/ckeditor.js"/>"></script> --%>
<script type="text/javascript"
	src="<c:url value="/scripts/format.number.min.js"/>"></script>
<!-- jTable plugin -->
<link
	href="<c:url value="/scripts/jtable/themes/metro/blue/jtable.css"/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/theme/metroblue/jquery-ui.css"/>"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value="/scripts/jtable/jquery.jtable.js"/>"></script>
<script type="text/javascript" src="<c:url value="/scripts/moment.js"/>"></script>

<!-- Validate Engine plugin -->
<!-- <script src="scripts/validationEngine/additional-methods.js" type="text/javascript" charset="UTF-8"></script>
 -->
<!-- tree view -->
<script type="text/javascript"
	src="<c:url value="/treeview/lib/jquery.cookie.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/treeview/jquery.treeview.js"/>"></script>
<link href="<c:url value="/treeview/jquery.treeview.css"/>"
	rel="stylesheet" type="text/css" />

<script
	src="<c:url value="/scripts/validationEngine/jquery.validationEngine-en.js"/>"
	type="text/javascript" charset="UTF-8"></script>
<script
	src="<c:url value="/scripts/validationEngine/jquery.validationEngine.js"/>"
	type="text/javascript" charset="UTF-8"></script>
<link rel="stylesheet"
	href="<c:url value="/css/validationEngine.jquery.css"/>"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/canlendar/tcal.css"/>" />
<script type="text/javascript"
	src="<c:url value="/css/canlendar/tcal.js"/>"></script>
<!-- RECAPTCHA GOOGLE VERSION 2 -->
<!-- <script src='https://www.google.com/recaptcha/api.js'></script> -->
<script src='https://www.google.com/recaptcha/api.js?hl=vi'></script>

<!-- Global javascript variable -->
<c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}"
	scope="session" />
<c:set var="BREADCRUMB_CONTEXT_PATH"
	value="${pageContext.request.contextPath}/breadcrumb" scope="session" />
<c:set var="ADMIN_CONTEXT_PATH"
	value="${pageContext.request.contextPath}/admin" scope="session" />
<c:set var="HOADON_DIENTU_CONTEXT_PATH"
	value="${pageContext.request.contextPath}/invoice" scope="session" />
<c:set var="SEARCHINVOICE_CONTEXT_PATH"
	value="${pageContext.request.contextPath}/tracuu" scope="session" />
<c:set var="REPORT_CONTEXT_PATH"
	value="${pageContext.request.contextPath}/report" scope="session" />
<c:set var="ADMIN_CALL_SERVICES_CONTEXT_PATH"
	value="${pageContext.request.contextPath}/admincallservices"
	scope="session" />
<c:set var="SEARCH_RECEIPT_CONTEXT_PATH"
	value="${pageContext.request.contextPath}/receipt" scope="session" />

<script>
	var CONTEXT_PATH = '${CONTEXT_PATH}';
	var BREADCRUMB_CONTEXT_PATH = '${BREADCRUMB_CONTEXT_PATH}';
	var ADMIN_CONTEXT_PATH = '${ADMIN_CONTEXT_PATH}';
	var HOADON_DIENTU_CONTEXT_PATH = '${HOADON_DIENTU_CONTEXT_PATH}';
	var SEARCHINVOICE_CONTEXT_PATH = '${SEARCHINVOICE_CONTEXT_PATH}';
	var REPORT_CONTEXT_PATH = '${REPORT_CONTEXT_PATH}';
	var ADMIN_CALL_SERVICES_CONTEXT_PATH = '${ADMIN_CALL_SERVICES_CONTEXT_PATH}';
	var SEARCH_RECEIPT_CONTEXT_PATH = '${SEARCH_RECEIPT_CONTEXT_PATH}';
</script>
<!-- DEMO javascript library -->
<script src="<c:url value="/scripts/demomvc.js"/>"
	type="text/javascript" charset="UTF-8"></script>

<script type="text/javascript">
	$(document).ready(function() {
		//register error dialog
		$("#errorDialog").dialog({
			autoOpen : false,
			show : 'fade',
			hide : 'fade',
			modal : true,
			title : 'Warning messasge',
			buttons : [ {
				text : 'Close',
				click : function() {
					$("#errorDialog").dialog('close');
				}
			} ]
		});
	});
</script>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">

	<tile:insertAttribute name="headerLayout"></tile:insertAttribute>
	<c:if test="${empty keylogin || keylogin != '' }">
		<div id="breadcrumb">
			<tile:insertAttribute name="menuNavigator" />
		</div>
	</c:if>
	<div class="mainpage ">
		<div class="maincontent">
			<img src="<c:url value="/images/ajax-loader.gif"/>" id="loading"
				style="z-index: 9999; display: none;" />
			<div id="loading-b" class="widget-overlayzz" style="display: none;"></div>
			<tile:insertAttribute name="pageContent"></tile:insertAttribute>
		</div>
	</div>
	<tile:insertAttribute name="footerLayout"></tile:insertAttribute>

<!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Page level plugin JavaScript-->
    <script src="vendor/chart.js/Chart.min.js"></script>
    <script src="vendor/datatables/jquery.dataTables.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin.min.js"></script>
    <!-- Custom scripts for this page-->
    <script src="js/sb-admin-datatables.min.js"></script>
    <script src="js/sb-admin-charts.min.js"></script>
</body>
</html>