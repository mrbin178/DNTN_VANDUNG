<!DOCTYPE html>
<%@ taglib prefix="tile" uri="/WEB-INF/tld/tiles-jsp.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<meta charset="utf-8">
<title><tile:insertAttribute name="title" /></title>
<!-- Bootstrap core CSS-->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom fonts for this template-->
<link href="vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- Custom styles for this template-->
<link href="css/sb-admin.css" rel="stylesheet">
</head>

<body class="bg-dark">
	<spring:form id="loginData" name="loginData"
		action="${pageContext.request.contextPath}/login.vandung" commandName="loginView"
		method='POST'>
		<div class="container">
			<div class="card card-login mx-auto mt-5">
				<div class="card-header" align="center" style="color: red;">
					<strong><h3>DNTN SẮT THÉP VĂN DUNG</h3></strong>
				</div>
				<div class="card-body">
					<form>
						<div class="form-group">
							<input class="form-control"  name="username" id="username"
								aria-describedby="emailHelp" placeholder="Nhập tên đăng nhập">
						</div>
						<div class="form-group">
							<input class="form-control" name="password" id="password"
								type="password" placeholder="Nhập mật khẩu">
						</div>
						<!-- <a class="btn btn-primary btn-block" href="index.html">Đăng
							nhập</a> -->
							<input class="btn btn-primary btn-block" value="Đăng nhập" type="submit" onClick="form.submit();this.disabled=true;document.body.style.cursor = 'wait'; this.className='formButton-disabled';" />
					</form>
					<div class="text-center">
						<a class="d-block small mt-3" href="register.html">Tạo mới tài
							khoản</a> <a class="d-block small" href="forgot-password.html">Quên
							mật khẩu</a>
					</div>
				</div>
			</div>
			<%-- <tile:insertAttribute name="footerLayout"></tile:insertAttribute> --%>
			<footer class="sticky-footer" style="width: 100%">
				<div class="container">
					<div class="text-center">
						<small>Copyright © DNTN sắt thép Văn Dung 2017</small>
					</div>
				</div>
			</footer>
		</div>
		<!-- Bootstrap core JavaScript-->
		<script src="vendor/jquery/jquery.min.js"></script>
		<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
		<!-- Core plugin JavaScript-->
		<script src="vendor/jquery-easing/jquery.easing.min.js"></script>
	</spring:form>
</body>

</html>
