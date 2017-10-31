<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<link href="media/css/webstyle.css" rel="stylesheet" type="text/css" />
<div class="frame">
	<div class="content">
		<div align="center" style="color: red">
		<c:if test="${statusViewHD eq '-1'}">Không tìm thấy XSL mẫu để hiển thị nội dung!</c:if>
		<c:if test="${statusViewHD eq '-2'}">Không tìm thấy file!</c:if>
		<c:if test="${statusViewHD eq '-3'}">Thông tin dữ liệu không đúng. Vui lòng thử lại!</c:if>
		<c:if test="${statusViewHD eq '-99'}">Lỗi trong quá trình hiển thị hóa đơn!</c:if>
		
		<c:if test="${statusViewBrowseFileHD eq '-99'}">Cấu trúc file không đúng định dạng hóa đơn!"</c:if>
		<c:if test="${statusViewBrowseFileHD eq '-2'}">Lỗi trong quá trình hiển thị hóa đơn!</c:if>
		</div>
		
	<div class="content">
		<c:if test="${not empty errorMessage}">
			<div align="center" style="color: red">
				${errorMessage}
			</div>
	    </c:if>
	</div>
	
	</div>
</div>
