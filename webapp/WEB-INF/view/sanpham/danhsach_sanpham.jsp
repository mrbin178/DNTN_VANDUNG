<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tile" uri="/WEB-INF/tld/tiles-jsp.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld"%>
<div class="card mb-3">
	<div class="card-header">
	<table width="100%">
		<tr>
			<td align="left"><i class="fa fa-table"></i> Danh mục sản phẩm</td>
			<td align="right"><a class="btn btn-primary" href="#" id="">Thêm mới <i class="fa fa-plus"></i></a></td>
		</tr>
	</table>
	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-bordered" id="dataTable" width="100%"	cellspacing="0">
				<thead>
					<tr>
						<th>Mã sản phẩm</th>
						<th>Tên sản phẩm</th>
						<th>Giá bán</th>
						<th>Giá vốn</th>
						<th>Đơn vị tính</th>
						<th>Tồn kho</th>
						<th></th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th>Mã sản phẩm</th>
						<th>Tên sản phẩm</th>
						<th>Giá bán</th>
						<th>Giá vốn</th>
						<th>Đơn vị tính</th>
						<th>Tồn kho</th>
						<th></th>
					</tr>
				</tfoot>
				<tbody>
					<c:if test="${ not empty listSp && listSp != '' }">
						<c:forEach items="${listSp}" var="item">
							<tr>
								<td><c:out value="${item.maSanPham}"></c:out></td>
								<td><c:out value="${item.tenSanPham}"></c:out></td>
								<td align="right"><c:out value="${item.giaBan}"></c:out></td>
								<td align="right"><c:out value="${item.giaVon}"></c:out></td>
								<td><c:out value="${item.donViTinh}"></c:out></td>
								<td></td>
								<td>
									<a title="Chỉnh sửa" href="#"><i class="fa fa-pencil-square-o"></i></a>
									<a title="Xóa" href="#"><i class="fa fa-trash-o"></i></a>
								</td>
								<%-- <td><c:out value="${item.tonKho}"></c:out></td> --%>
							</tr>
						</c:forEach>					
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
	<!-- <div class="card-footer small text-muted"></div> -->
</div>