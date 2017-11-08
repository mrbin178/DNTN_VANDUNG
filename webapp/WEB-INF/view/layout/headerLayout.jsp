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
		<ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
			<li class="nav-item" data-toggle="tooltip" data-placement="right"
				title="Trang chủ"><a class="nav-link" href="index.html"> <i
					class="fa fa-fw fa-home"></i> <span class="nav-link-text">Trang chủ</span>
			</a></li>
			<li class="nav-item" data-toggle="tooltip" data-placement="right"
				title="Quản lý kho"><a
				class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
				href="#collapseKho" data-parent="#exampleAccordion"> <i
					class="fa fa-fw fa-cubes"></i> <span class="nav-link-text">Quản lý kho</span>
			</a>
				<ul class="sidenav-second-level collapse" id="collapseKho">
					<li><a href="#">Nhập kho</a></li>
					<li><a href="#">Xuất kho</a></li>
					<li><a href="#">Thống kê sản phẩm</a></li>
				</ul>
			</li>
			<li class="nav-item" data-toggle="tooltip" data-placement="right"
				title="Quản lý sản phẩm"><a
				class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
				href="#collapseSanPham" data-parent="#exampleAccordion"> <i
					class="fa fa-fw fa-cubes"></i> <span class="nav-link-text">Quản lý sản phẩm</span>
			</a>
				<ul class="sidenav-second-level collapse" id="collapseSanPham">
					<li><a href="${PRODUCT_CONTEXT_PATH}/list_product.vandung"><i class="fa fa-fw fa-cube"></i> Sản phẩm</a></li>
				</ul>
			</li>
			<li class="nav-item" data-toggle="tooltip" data-placement="right"
				title="Quản lý sản phẩm"><a
				class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
				href="#collapseDoiTac" data-parent="#exampleAccordion"> <i
					class="fa fa-fw fa-male"></i> <span class="nav-link-text">Đối tác</span>
			</a>
				<ul class="sidenav-second-level collapse" id="collapseDoiTac">
					<li><a href="#"><i class="fa fa-fw fa-user"></i> Khách hàng</a></li>
					<li><a href="#"><i class="fa fa-fw fa-truck"></i> Nhà cung cấp</a></li>
				</ul>
			</li>
		</ul>
		<!-- <ul class="navbar-nav sidenav-toggler">
			<li class="nav-item"><a class="nav-link text-center"
				id="sidenavToggler"> <i class="fa fa-fw fa-angle-left"></i>
			</a></li>
		</ul> -->
		<ul class="navbar-nav ml-auto">
			<li class="nav-item"><a class="nav-link" data-toggle="modal"
				data-target="#exampleModal"> <i class="fa fa-fw fa-sign-out"></i>Logout
			</a></li>
		</ul>
	</div>
</nav>