<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld" %>

<div style="display: none;" id="loaderContainer">
	<div id="loader" style="z-index: 10;" align="center">
		<p style="vertical-align: middle;">
			<img class="valgin-mid" alt="loading.gif" title=""
				src="<c:url value="/images/ajax-loader.gif"/>" width="24" /> <b
				style="color: blue; vertical-align: top;">Đang truy xuất dữ
				liệu. Vui lòng đợi...</b>
		</p>
	</div>
</div>
<c:if test="${not empty successMessage || not empty errorMessage}">
		<div class="row">
			<div class="col-lg-6 col-lg-offset-3">
			<!--notification start-->
		         	<c:if test="${not empty successMessage}">
			           	 <div class="alert alert-success fade in text-center">
			                 <button data-dismiss="alert" class="close close-sm" type="button">
			                     <i class="icon-remove"></i>
			                 </button>
								${successMessage}
			             </div>
		             </c:if>
		            <c:if test="${not empty errorMessage}">
			             <div class="alert alert-block alert-danger fade in text-center">
			                 <button data-dismiss="alert" class="close close-sm" type="button">
			                     <i class="icon-remove"></i>
			                 </button>
								${errorMessage}
			             </div>
					</c:if>
			     <!--notification end-->
			</div>
	     </div>
     </c:if>
    
<div class="page-title">
	<h3>Tạo tài khoản</h3>
</div>
<spring:form action="" id="createUserTS24ID" name="createUserTS24ID" commandName="createUserTS24IDView">
	<p class="required">(<em>*</em>) Phải nhập thông tin</p>
	<div class="fieldset">
		<h4>Thông tin đăng nhập</h4>
		<ul class="form-list">
			<li><label for="email_address" class="required"><em>*</em> Tên đăng nhập</label>
				<div class="input-box">
					<spring:input path="username" cssClass="input-text required-entry" placeholder="Tên đăng nhập là địa chỉ email" />
						<spring:errors path="username" cssClass="error"/>
				</div></li>
			<li class="fields"><label for="password" class="required"><em>*</em> Mật khẩu</label>
				<div class="input-box">
						<spring:password path="password" cssClass="input-text required-entry"/>
						<spring:errors path="password" cssClass="error"/>
				</div> <label for="confirmation" class="required"><em>*</em> Nhập lại mật khẩu</label>
				<div class="input-box">
						<spring:password path="confirmation" cssClass="input-text required-entry"/>
						<spring:errors path="confirmation" cssClass="error"/>
				</div></li>
		</ul>
	</div>
	<div class="fieldset">
		<input name="success_url" value="" type="hidden"> <input
			name="error_url" value="" type="hidden">
		<h4 class="legend">Thông tin cá nhân</h4>
		<ul class="form-list">
			<li class="fields">
				<div class="customer-name">
					<div class="input-box">
						<label for="fullname" class="required"><em>*</em> Họ và tên khách hàng</label>
						<div class="input-box">
								<spring:input path="fullname" cssClass="input-text required-entry"/>
								<spring:errors path="fullname" cssClass="error"/>
						</div>
					</div>
				</div>
			</li>
			<li><label for="telnumber" class="required">Email</label>
				<div class="input-box">
					<spring:input path="email" cssClass="input-text required-entry" placeholder="Thêm email phụ để nhận thông tin phản hồi tương tự email chính"/>
					<spring:errors path="email" cssClass="error" />
				</div></li>
				<li><label for="telnumber" class="required"><em>*</em> Điện thoại</label>
				<div class="input-box">
					<spring:input path="telnumber" cssClass="input-text required-entry"/>
					<spring:errors path="telnumber" cssClass="error" />
				</div></li>
		</ul>
	</div>
	<div class="line"></div>
	<div class="buttons-set">
		<p class="back-link f_right">
			<a href="${ADMIN_CALL_SERVICES_CONTEXT_PATH}/loginTS24ID.bv" class="back-link"><small>« </small>Quay lại</a>
		</p>
	</div>
	<div class="bnt f_left">
		<!-- <button class="btn btn-primary" onclick="send()" type="button" style="display:block;" id="loading_button">Tạo tài khoản</button> -->
		<a href="#" onclick="send();" id="loading_button">Tạo tài khoản</a>
	</div>
	<div class="clr">&nbsp;</div>
</spring:form>

<script type="text/javascript" charset="utf-8">
	hideBtnLoader('loading_button');
	function send() {
		if (document.forms["createUserTS24ID"].username.value == "") {
			alert("Vui lòng nhập Tên đăng nhập là email!");
			document.forms["createUserTS24ID"].username.select();
			document.forms["createUserTS24ID"].username.focus();
			return;
		}else if (checkEmail(document.forms["createUserTS24ID"].username.value) == false) {
			alert("Định dạng Tên đăng nhập(email) không hợp lệ!");
			document.forms["createUserTS24ID"].username.select();
			document.forms["createUserTS24ID"].username.focus();
			return;
		}
		if (document.forms["createUserTS24ID"].password.value == "") {
			alert("Vui lòng nhập mật khẩu!");
			document.forms["createUserTS24ID"].password.select();
			document.forms["createUserTS24ID"].password.focus();
			return;
		}
		if (document.forms["createUserTS24ID"].confirmation.value == "") {
			alert("Vui lòng nhập xác nhận mật khẩu!");
			document.forms["createUserTS24ID"].confirmation.select();
			document.forms["createUserTS24ID"].confirmation.focus();
			return;
		}
		if (document.forms["createUserTS24ID"].password.value != document.forms["createUserTS24ID"].confirmation.value) {
			alert("Xác nhận mật khẩu không trùng khớp!");
			document.forms["createUserTS24ID"].confirmation.select();
			document.forms["createUserTS24ID"].confirmation.focus();
			return;
		}
		if (document.forms["createUserTS24ID"].fullname.value == "") {
			alert("Vui lòng nhập họ & tên khách hàng!");
			document.forms["createUserTS24ID"].fullname.select();
			document.forms["createUserTS24ID"].fullname.focus();
			return;
		}
		if (document.forms["createUserTS24ID"].email.value != "") {
			if (checkEmail(document.forms["createUserTS24ID"].email.value) == false) {
				alert("Định dạng email không hợp lệ!");
				document.forms["createUserTS24ID"].email.select();
				document.forms["createUserTS24ID"].email.focus();
				return;
			}
		}
		if (document.forms["createUserTS24ID"].telnumber.value == "") {
			alert("Vui lòng nhập điện thoại!");
			document.forms["createUserTS24ID"].telnumber.select();
			document.forms["createUserTS24ID"].telnumber.focus();
			return;
		}
		showBtnLoader('loading_button');
		document.forms["createUserTS24ID"].action = "${ADMIN_CALL_SERVICES_CONTEXT_PATH}/createAccountTS24ID.bv";
		document.forms["createUserTS24ID"].submit();
	}
</script>