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
<c:if test="${not empty successMessage || not empty successMessagePassword || not empty errorMessage || not empty errorMessagePassword}">
		<div class="row">
			<div class="col-lg-6 col-lg-offset-3">
			<!--notification start-->
		         	<c:if test="${not empty successMessage || not empty successMessagePassword}">
			           	 <div class="alert alert-success fade in text-center">
			                 <button data-dismiss="alert" class="close close-sm" type="button">
			                     <i class="icon-remove"></i>
			                 </button>
			                 	<c:if test="${not empty successMessagePassword}">
			                 		${successMessagePassword} <br />
			                 	</c:if>
								${successMessage}
			             </div>
		             </c:if>
		            <c:if test="${not empty errorMessage || not empty errorMessagePassword}">
			             <div class="alert alert-block alert-danger fade in text-center">
			                 <button data-dismiss="alert" class="close close-sm" type="button">
			                     <i class="icon-remove"></i>
			                 </button>
			                 	<c:if test="${not empty errorMessagePassword}">
			                 		${errorMessagePassword} <br />
			                 	</c:if>
								${errorMessage}
			             </div>
					</c:if>
			     <!--notification end-->
			</div>
	     </div>
     </c:if>
    
<div class="page-title">
	<h3>Sửa thông tin tài khoản</h3>
</div>
<spring:form action="" id="changeInfoUserTS24ID" name="changeInfoUserTS24ID" commandName="changeInfoUserTS24IDView">
	<p class="required">(<em>*</em>) Phải nhập thông tin</p>
	<div class="fieldset">
		<h4>Thông tin tài khoản</h4>
		<ul class="form-list">
			<li><label for="username" class="required"><em>*</em> Tên đăng nhập</label>
				<div class="input-box">
					<spring:input path="username" cssClass="input-text required-entry" readonly="true"/>
						<spring:errors path="username" cssClass="error"/>
				</div>
			</li>
			<li class="group_check">
				<div class="f_left">
                	<input name="checkchangepassword" id="checkchangepassword" onclick="setPasswordForm(this.checked)" title="Đổi mật khẩu" checked="checked" class="checkbox" type="checkbox">&nbsp;
                </div>
                <div class="label_check">
                	<label for="checkchangepassword"><b>Đổi mật khẩu </b><i> (Đổi mật khẩu chỉ áp dụng cho đăng nhập bằng tài khoản TS24. Nếu bạn đăng nhập bằng mạng xã hội hoặc google thì bỏ chọn mục này khi cập nhật)</i></label>
                </div>
            </li>
			<li class="fields" id="passGroup"><label for="oldpassword" class="required"><em>*</em> Mật khẩu cũ</label>
				<div class="input-box">
					<spring:password path="oldpassword" cssClass="input-text required-entry"/>
						<spring:errors path="oldpassword" cssClass="error"/>
				</div>
			<label for="password" class="required"><em>*</em> Mật khẩu mới</label>
				<div class="input-box">
						<spring:password path="password" cssClass="input-text required-entry"/>
						<spring:errors path="password" cssClass="error"/>
				</div> <label for="confirmation" class="required"><em>*</em> Nhập lại mật khẩu mới</label>
				<div class="input-box">
						<spring:password path="confirmation" cssClass="input-text required-entry"/>
						<spring:errors path="confirmation" cssClass="error"/>
				</div>
			</li>
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
			<li><label for="email" class="required">Email</label>
				<div class="input-box">
					<spring:input path="email" cssClass="input-text required-entry"/>
						<spring:errors path="email" cssClass="error"/>
				</div>
			</li>
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
			<a href="${ADMIN_CALL_SERVICES_CONTEXT_PATH}/profileTS24ID.bv" class="back-link"><small>« </small>Quay lại</a>
		</p>
	</div>
	<div class="bnt f_left">
		<!-- <a href="#" onclick="send();form.submit();this.disabled=true;document.body.style.cursor = 'wait'; this.className='formButton-disabled';">Cập nhật</a> -->
		<a href="#" onclick="send();" id="loading_button">Cập nhật</a>
	</div>
	<div class="clr">&nbsp;</div>
</spring:form>

<script type="text/javascript" charset="utf-8">
	hideBtnLoader('loading_button');
	function setPasswordForm(arg){
        if(arg){ // true
            $('#passGroup').show();
        }else{
            $('#passGroup').hide();
        }
    }
    
	function send() {
		if (document.forms["changeInfoUserTS24ID"].username.value == "") {
			alert("Vui lòng nhập Tên đăng nhập là email!");
			document.forms["changeInfoUserTS24ID"].username.select();
			document.forms["changeInfoUserTS24ID"].username.focus();
			return;
		}else if (checkEmail(document.forms["changeInfoUserTS24ID"].username.value) == false) {
			alert("Định dạng Tên đăng nhập(email) không hợp lệ!");
			document.forms["changeInfoUserTS24ID"].username.select();
			document.forms["changeInfoUserTS24ID"].username.focus();
			return;
		}
		if(document.getElementById("checkchangepassword").checked == true){
			if (document.forms["changeInfoUserTS24ID"].oldpassword.value == "") {
				alert("Vui lòng nhập mật khẩu cũ!");
				document.forms["changeInfoUserTS24ID"].oldpassword.select();
				document.forms["changeInfoUserTS24ID"].oldpassword.focus();
				return;
			}
			if (document.forms["changeInfoUserTS24ID"].password.value == "") {
				alert("Vui lòng nhập mật khẩu mới!");
				document.forms["changeInfoUserTS24ID"].password.select();
				document.forms["changeInfoUserTS24ID"].password.focus();
				return;
			}
			if (document.forms["changeInfoUserTS24ID"].confirmation.value == "") {
				alert("Vui lòng nhập xác nhận mật khẩu mới!");
				document.forms["changeInfoUserTS24ID"].confirmation.select();
				document.forms["changeInfoUserTS24ID"].confirmation.focus();
				return;
			}
			if (document.forms["changeInfoUserTS24ID"].password.value != document.forms["changeInfoUserTS24ID"].confirmation.value) {
				alert("Xác nhận mật khẩu mới không trùng khớp!");
				document.forms["changeInfoUserTS24ID"].confirmation.select();
				document.forms["changeInfoUserTS24ID"].confirmation.focus();
				return;
			}
		}
		if (document.forms["changeInfoUserTS24ID"].fullname.value == "") {
			alert("Vui lòng nhập họ & tên khách hàng!");
			document.forms["changeInfoUserTS24ID"].fullname.select();
			document.forms["changeInfoUserTS24ID"].fullname.focus();
			return;
		}
		
		if (document.forms["changeInfoUserTS24ID"].email.value != "") {
			if (checkEmail(document.forms["changeInfoUserTS24ID"].email.value) == false) {
				alert("Định dạng email không hợp lệ!");
				document.forms["changeInfoUserTS24ID"].email.select();
				document.forms["changeInfoUserTS24ID"].email.focus();
				return;
			}
		}
		
		if (document.forms["changeInfoUserTS24ID"].telnumber.value == "") {
			alert("Vui lòng nhập điện thoại!");
			document.forms["changeInfoUserTS24ID"].telnumber.select();
			document.forms["changeInfoUserTS24ID"].telnumber.focus();
			return;
		}
		
		showBtnLoader('loading_button');
		document.forms["changeInfoUserTS24ID"].action = "${ADMIN_CALL_SERVICES_CONTEXT_PATH}/changeInfoAccountTS24ID.bv";
		document.forms["changeInfoUserTS24ID"].submit();
	}
</script>