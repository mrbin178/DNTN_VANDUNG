<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tile" uri="/WEB-INF/tld/tiles-jsp.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
	id="mainNav">
	<a class="navbar-brand" href="index.html">DNTN SẮT THÉP VĂN DUNG</a>
	<button class="navbar-toggler navbar-toggler-right" type="button"
		data-toggle="collapse" data-target="#navbarResponsive"
		aria-controls="navbarResponsive" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarResponsive">
		<ul class="navbar-nav sidenav-toggler">
			<li class="nav-item"><a class="nav-link text-center"
				id="sidenavToggler"> <i class="fa fa-fw fa-angle-left"></i>
			</a></li>
		</ul>
		<ul class="navbar-nav ml-auto">
			<li class="nav-item"><a class="nav-link" data-toggle="modal"
				data-target="#exampleModal"> <i class="fa fa-fw fa-sign-out"></i>Logout
			</a></li>
		</ul>
	</div>
</nav>
<!-- <script type="text/javascript" charset="utf-8">
	$(document)
			.ready(
					function() {
						if (window.location.href.indexOf("tracuu/") > 0) {
							//              $("#navigation li#tracuuhoadon a").addClass("active");
							//              $("#navigation li#coquanthue a").removeClass("active")
							//              $("#navigation li#huongdan a").removeClass("active")

							$("#navigation li a").removeClass("active")
							$("#navigation li#huongdan a.last").removeClass(
									"active");
							$("#navigation li#tracuuhoadon a").addClass(
									"active");
						} else if (window.location.href.indexOf("home") > 0
								|| window.location.href.indexOf("admin/") > 0
								|| window.location.href.indexOf("hoadon_view") > 0
								//|| window.location.href.indexOf("login") > 0
								|| window.location.href
										.indexOf("searchInvoiceFromExcel") > 0
								|| window.location.href.indexOf("report") > 0) {

							//              $("#navigation li#coquanthue a").addClass("active");
							//              $("#navigation li#tracuuhoadon a").removeClass("active")
							//              $("#navigation li#huongdan a").removeClass("active")
							$("#navigation li a").removeClass("active")
							$("#navigation li#huongdan a.last").removeClass(
									"active");
							$("#navigation li#coquanthue a").addClass("active");
						} else if (window.location.href
								.indexOf("admincallservices") > 0) {
							$("#navigation li a").removeClass("active")
							$(".navigation_login").addClass(
									"navigation_login_active");
							//$("#logints24id a").addClass("active");
							//$("#logints24id_icon_user .f_right").addClass("bg");
						} else if (window.location.href.indexOf("useGuide") > 0) {
							$("#navigation li a").removeClass("active")
							$("#navigation li#huongdan a.last").addClass(
									"active");
						} else if (window.location.href.indexOf("receipt/") > 0) {
							$("#navigation li a").removeClass("active")
							$("#navigation li#tracuuphieuthu a.last").addClass(
									"active");
						}
					});
</script> -->
