<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<img src="<c:url value="/images/ajax-loader.gif"/>" id="loading" style="z-index:9999;display: none;"/>
<div id="loading-b" class="widget-overlayzz" style="display: none;"></div>
	<c:if test="${not empty webSession }">
			<div class="f_right">
				Xin chào: <c:out value="${webSession.currentUser.username }"></c:out> |						
					<a href="${ADMIN_CONTEXT_PATH}/changePass.bv">Thay đổi mật khẩu</a> |
					<a href="<c:url value="/j_spring_security_logout"/>">Thoát</a> 
			</div>
		<c:if test="${not empty webSession.currentUser.username }">
			<div id="sidetreecontrol"><a href="?#">Đóng tất cả</a> | <a href="?#">Mở tất cả</a></div>
			<ul id="tree">
				<li class="open"><a href="${CONTEXT_PATH}/home.bv"><strong>Trang chủ</strong></a>
				<ul>
					<li>Quản lý người dùng
						<ul>
						    <li><a onclick="view('${ADMIN_CONTEXT_PATH}/danhsachnguoidung.bv');">Danh sách tài khoản</a></li>
							<li><a onclick="view('${ADMIN_CONTEXT_PATH}/createNewUser.bv');">Thêm tài khoản người dùng</a></li>
						</ul>
					</li>
					<li><a onclick="view('${HOADON_DIENTU_CONTEXT_PATH}/hoadon_view.bv');">Quản lý hóa đơn</a></li>
					<li>Báo cáo hóa đơn
						<ul>
						    <%-- <li><a onclick="view('${REPORT_CONTEXT_PATH}/reportUseOfInvoices.bv');"><label>Báo cáo tình hình sử dụng hóa đơn</label></a></li> --%>
							<%--<li><a onclick="view('${REPORT_CONTEXT_PATH}/bangKeBanRa.bv');"><label>Bảng kê mua vào bán ra</label></a></li> --%>
							<%-- <li><a onclick="view('${REPORT_CONTEXT_PATH}/bangKeBanRaTongHop.bv');"><label>Báo cáo tổng hợp</label></a></li> --%>
							<li><a onclick="view('${REPORT_CONTEXT_PATH}/reportThongBaoPhatHanh.bv');">Báo cáo theo phát hành hóa đơn</a></li>
							<li><a onclick="view('${REPORT_CONTEXT_PATH}/baoCaoBanHang.bv');">Báo cáo bán hàng</a></li>
						</ul>
					</li>
					<li><a onclick="view('${ADMIN_CONTEXT_PATH}/lock_mst.bv');">Khóa mã số thuế</a></li>
				</ul>
				</li>
			</ul>
			</c:if>
	</c:if>
<script type="text/javascript">
	$(function() {
		$("#tree").treeview({
			collapsed: false,
			animated: "medium",
			control:"#sidetreecontrol",
			persist: "location"
		});
	})
    
    function view(url){
		$("#loading").show();
		$("#loading-b").show();
		$.ajax({
            url: url,
            type: 'GET',
    	}).done(function (response) {
    		DEMOMVC.redirect(url);
    	});
    }
</script>


