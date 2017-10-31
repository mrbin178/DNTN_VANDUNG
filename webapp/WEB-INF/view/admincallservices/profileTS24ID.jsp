<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<div class="page-title">
	<h3>Thông tin tài khoản</h3>
</div>
<form action="" method="post" id="profileView" name="profileView"><div class="fieldset">
		<p>Tên đăng nhập: <strong>${webSession.userInfoViewID.username}</strong></p>
		<p><a href="${ADMIN_CALL_SERVICES_CONTEXT_PATH}/changeInfoAccountTS24ID.bv">Thay đổi thông tin</a></p>
		<h4 class="legend">Thông tin liên hệ</h4>
		<ul class="form-list">
			<c:if test="${not empty webSession.userInfoViewID.fullname}">
				<li class="fields"><label for="fullname" class="required">
					Tên khách hàng: </label> <strong>${webSession.userInfoViewID.fullname}</strong>
				<div class="input-box"></div></li>
			</c:if>
			<c:if test="${not empty webSession.userInfoViewID.email}">
				<li><label for="email_address" class="required"> Địa chỉ
					email: </label> <strong>${webSession.userInfoViewID.email}</strong>
				<div class="input-box"></div></li>
			</c:if>
		</ul>
	</div>
	<div class="fieldset">
		<input name="success_url" value="" type="hidden"> <input
			name="error_url" value="" type="hidden">
		<h4 class="legend">Địa chỉ liên hệ</h4>
		<ul class="form-list">
			<c:if test="${not empty webSession.userInfoViewID.telnumber}">
				<li><label for="telnumber" class="required"> Điện thoại:
				</label> <strong>${webSession.userInfoViewID.telnumber}</strong>
					<div class="input-box"></div></li>
			</c:if>
			<c:if test="${not empty webSession.userInfoViewID.diachi}">
			<li><label for="diachi" class="required"> Địa chỉ: </label> <strong>${webSession.userInfoViewID.diachi}</strong>
				<div class="input-box"></div></li>
			</c:if>
		</ul>
	</div>
	<div class="clr">&nbsp;</div>
</form>
<h4 class="legend"></h4>
<div>
	<div id="sidetreecontrol"><a href="?#">Đóng tất cả</a> | <a href="?#">Mở tất cả</a></div>
	<ul id="tree">
		<li class="open">
		<a href="#"><strong>Quản lý</strong></a>
		<ul>
			<li><a onclick="view('${ADMIN_CALL_SERVICES_CONTEXT_PATH}/hoaDonTS24ID.bv');">Quản lý hóa đơn</a></li>
			<li><a onclick="view('${ADMIN_CALL_SERVICES_CONTEXT_PATH}/receiptTS24ID.bv');">Quản lý phiếu thu</a></li>
		</ul>
		</li>
	</ul>
</div>

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