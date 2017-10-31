<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-1_0-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<%@ page import="ts24.com.vn.web.util.Path" %>

<script type="text/javascript" charset="utf-8">
	hideBtnLoader('loading_button');
	function beginLoadForm(){
		document.loginData.username.focus();
	}
</script>
<body onLoad="beginLoadForm();">
	<div class="page-title">
	<h3>Đăng nhập tài khoản ts24</h3>
</div>
<spring:form id="loginData" name="loginData" action="${ADMIN_CALL_SERVICES_CONTEXT_PATH}/loginTS24ID.bv" commandName="loginTS24IDView" method='POST'>
	<input name="form_key" type="hidden" value="5iG7sKfupWUg6Vq2">
	<div class="row">
		<div class="col-lg-6 col-sm-6 col-xs-12 new-users">
			<div class="content">
				<h4>Khách hàng mới</h4>
				<p>Hãy tạo cho mình một tài khoản, bạn sẽ nhận được các ưu đãi
					bất ngờ mà chỉ thành viên mới có.</p>
			</div>
			<div class="buttons-set">
				<div class="bnt f_left">
					<a href="${ADMIN_CALL_SERVICES_CONTEXT_PATH}/createAccountTS24ID.bv" id="loading_button" onclick="showBtnLoader('loading_button');;">Tạo tài khoản</a>
				</div>
			</div>
			<div class="clr">&nbsp;</div>
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
		</div>
		<div class="col-lg-6 col-sm-6 col-xs-12 registered-users">
			<div class="content">
				<h4>Bạn đã có tài khoản</h4>
				<p>Nếu bạn đã đăng ký, bạn vui lòng đăng nhập vào hệ thống ở
					bên dưới.</p>
				<p class="required">(<em>*</em>) Phải nhập thông tin</p>
				<c:if test="${not empty successMessage || not empty errorMessage}">
					<div class="row">
						<div class="col-lg-12">
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
				<ul class="form-list">
					<li><label for="email_address" class="required"><em>*</em> Tên đăng nhập / email</label>
						<div class="input-box">
							<input class="input-text" type="text" name="username" id="username" title="Địa chỉ email">
							<spring:errors path="username" cssClass="error"/>
						</div></li>
					<li><label for="password" class="required"><em>*</em> Mật khẩu</label>
						<div class="input-box">
							<input class="input-text" type="password" name="password" id="password" title="Mật khẩu">
							<spring:errors path="password" cssClass="error"/>
						</div></li>
					<li><label for="captcha" class="required"><em>*</em>  Mã xác nhận</label>
						<div class="input-box captcha">
						<div class="g-recaptcha" data-sitekey="${GOOGLE_CAPTCHA_PUBLICKEY}"></div>
							<%-- <div style="float: left;">
								<%
						          //ReCaptcha c = ReCaptchaFactory.newReCaptcha(Path.TS24_GOOGLE_CAPTCHA_PUBLICKEY, Path.TS24_GOOGLE_CAPTCHA_PRIVATEKEY, false);
						          //out.print(c.createRecaptchaHtml(null, null));
						        %>
						  </div> --%>
			              <c:if test="${ not empty errorMessageCaptcha }"> 
			              	<div style="float: left;" class="error"><c:out value="${errorMessageCaptcha}"></c:out></div> 
			              </c:if>
	                	</div>
					</li>
				</ul>
			</div>

			<div class="clr">&nbsp;</div>
			<div class="f_right">
				<a target="_blank" href="https://www.ts24.com.vn/ts24id_web/admin/getCodeSendEmailforgetPass.html"
					>Bạn muốn tìm lại mật khẩu?</a>
			</div>
			<div class="bnt f_left">
				<input class="formButton" value="Đăng nhập" type="submit" onClick=";form.submit();this.disabled=true;document.body.style.cursor = 'wait'; this.className='formButton-disabled';" />
			</div>
			<div class="clr">
				<a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=<%=Path.GOOGLE_REDIRECT_URI%>&response_type=code&client_id=<%=Path.GOOGLE_CLIENT_ID%>&approval_prompt=force">
					<span class="ced-sociallogin-google-inner ced_google_connect"></span>
				</a>
			</div>
		</div>
	</div>
</spring:form>
</body>