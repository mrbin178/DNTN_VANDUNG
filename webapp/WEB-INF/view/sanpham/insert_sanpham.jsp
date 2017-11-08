<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tile" uri="/WEB-INF/tld/tiles-jsp.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld"%>
<div class="card mb-3">
	<spring:form commandName="sanPhamForm" id="sanPhamForm" name="sanPhamForm"
		action="${PRODUCT_CONTEXT_PATH}/insert_product.vandung">
		<div class="card-header">Thêm mới sản phẩm</div>
		<div class="card-body">
			<div class="form-group">
				<div class="form-row">
					<div class="col-md-6">
						<input class="form-control" id="exampleInputName" type="text"
							aria-describedby="nameHelp" placeholder="Mã sản phẩm">
					</div>
					<div class="col-md-6">
						<a class="btn btn-primary" href="login.html"><i
							class="fa fa-ban"></i> Chọn ảnh</a>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="form-row">
					<div class="col-md-6">
						<input class="form-control" id="exampleInputName" type="text"
							aria-describedby="nameHelp" placeholder="Tên sản phẩm">
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="form-row">
					<div class="col-md-6">
						<input class="form-control" id="exampleInputName" type="text"
							aria-describedby="nameHelp" placeholder="Giá vốn">
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="form-row">
					<div class="col-md-6">
						<input class="form-control" id="exampleInputName" type="text"
							aria-describedby="nameHelp" placeholder="Giá bán">
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="form-row">
					<div class="col-md-6">
						<input class="form-control" id="exampleInputName" type="text"
							aria-describedby="nameHelp" placeholder="Tồn kho">
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="form-row">
					<div class="col-md-6">
						<input class="form-control" id="exampleInputName" type="text"
							aria-describedby="nameHelp" placeholder="Đơn vị tính">
					</div>
				</div>
			</div>
			<div class="text-right">
				<a class="btn btn-primary" href="login.html"><i
					class="fa fa-floppy-o"></i> Lưu</a> 
				<input class="btn btn-primary" value="Lưu" type="submit" onClick="form.submit();this.disabled=true;document.body.style.cursor = 'wait'; this.className='formButton-disabled';" />
				<a class="btn btn-primary"
					href="login.html"><i class="fa fa-ban"></i> Bỏ qua</a>
			</div>
		</div>
	</spring:form>
</div>