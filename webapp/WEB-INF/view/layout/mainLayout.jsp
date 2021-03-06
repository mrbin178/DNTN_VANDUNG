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
<title><tile:insertAttribute name="title" /></title>

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
<c:set var="PRODUCT_CONTEXT_PATH"
	value="${pageContext.request.contextPath}/sanpham" scope="session" />
<script>
	var CONTEXT_PATH = '${CONTEXT_PATH}';
	var BREADCRUMB_CONTEXT_PATH = '${BREADCRUMB_CONTEXT_PATH}';
	var ADMIN_CONTEXT_PATH = '${ADMIN_CONTEXT_PATH}';
	var HOADON_DIENTU_CONTEXT_PATH = '${HOADON_DIENTU_CONTEXT_PATH}';
	var SEARCHINVOICE_CONTEXT_PATH = '${SEARCHINVOICE_CONTEXT_PATH}';
	var REPORT_CONTEXT_PATH = '${REPORT_CONTEXT_PATH}';
	var ADMIN_CALL_SERVICES_CONTEXT_PATH = '${ADMIN_CALL_SERVICES_CONTEXT_PATH}';
	var PRODUCT_CONTEXT_PATH = '${PRODUCT_CONTEXT_PATH}';
</script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet" type="text/css" href="<c:url value="/vendor/bootstrap/css/bootstrap.min.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/vendor/font-awesome/css/font-awesome.min.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/vendor/datatables/dataTables.bootstrap4.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/sb-admin.css"/>">

</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">
	<tile:insertAttribute name="headerLayout"></tile:insertAttribute>
	<div class="content-wrapper">
		<div class="container-fluid">
			<tile:insertAttribute name="pageContent"></tile:insertAttribute>
		</div>
		<tile:insertAttribute name="footerLayout"></tile:insertAttribute>
		<a class="scroll-to-top rounded" href="#page-top"> <i
			class="fa fa-angle-up"></i>
		</a>
		<!-- Logout Modal-->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Ready to
							Leave?</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">Select "Logout" below if you are
						ready to end your current session.</div>
					<div class="modal-footer">
						<button class="btn btn-secondary" type="button"
							data-dismiss="modal">Cancel</button>
						<a class="btn btn-primary" href="login.html">Logout</a>
					</div>
				</div>
			</div>
		</div>
		
		<!-- Bootstrap core JavaScript-->
		<script src="<c:url value="/vendor/jquery/jquery.min.js"/>"></script>
		<script src="<c:url value="/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
		<script src="<c:url value="/vendor/jquery-easing/jquery.easing.min.js"/>"></script>
		<script src="<c:url value="/vendor/chart.js/Chart.min.js"/>"></script>
		<script src="<c:url value="/vendor/datatables/jquery.dataTables.js"/>"></script>
		<script src="<c:url value="/vendor/datatables/dataTables.bootstrap4.js"/>"></script>
		<script src="<c:url value="/js/sb-admin.min.js"/>"></script>
		<script src="<c:url value="/js/sb-admin-datatables.min.js"/>"></script>
		<script src="<c:url value="/js/sb-admin-charts.min.js"/>"></script>
		
	</div>
</body>
</html>