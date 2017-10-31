<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-1_0-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<%@ page import="ts24.com.vn.web.util.Path" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="css/cssError.css">
</head>
<script type="text/javascript" charset="utf-8">
	function beginLoadForm(){
		document.loginData.username.focus();
	}
</script>
<body onLoad="beginLoadForm();">
	<spring:form id="loginData" name="loginData" action="${CONTEXT_PATH}/login.bv" commandName="loginView" method='POST'>
       <div class="message-row" align="center">
			<c:if test="${ not empty errorMessage }">
			  <p class="error">${errorMessage}</p>
			</c:if>
		</div>
		<table width="100%" cellspacing="0" cellpadding="3" border="0" class="txt_1 captcha">
				<tr>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="40%" valign="middle" align="right" class="input_form">Tên đăng nhập:</td>
					<td valign="middle" width="15%"><input class="textinput" type="text" name="username" id="username"></td>
					<td><spring:errors path="username" cssClass="error"/></td>
				</tr>
				<tr>
					<td valign="middle" align="right" class="input_form">Mật khẩu:</td>
					<td><input class="textinput" type="password" name="password" id="password"></td>
					<td><spring:errors path="password" cssClass="error"/></td>
				</tr>
				<tr>
					<td valign="top" align="right" class="input_form">Mã xác nhận:</td>
					<td colspan="2">
						<div class="g-recaptcha" data-sitekey="${GOOGLE_CAPTCHA_PUBLICKEY}"></div>
						<%-- <div style="float: left;">
							<%
					          ReCaptcha c = ReCaptchaFactory.newReCaptcha(Path.TS24_GOOGLE_CAPTCHA_PUBLICKEY, Path.TS24_GOOGLE_CAPTCHA_PRIVATEKEY, false);
					          out.print(c.createRecaptchaHtml(null, null));
					        %>
					  </div> --%>
		              <c:if test="${ not empty errorMessageCaptcha }"> 
		              	<div style="float: left;" class="error"><c:out value="${errorMessageCaptcha}"></c:out></div> 
		              </c:if>
                	</td>
				</tr>
				<tr>
					<td class="input_form">&nbsp;</td>
					<td valign="middle" align="left">
							<input class="formButton" value="Đăng nhập" type="submit" onClick="form.submit();this.disabled=true;document.body.style.cursor = 'wait'; this.className='formButton-disabled';" />
							<input type="hidden" name="j_character_encoding" value="UTF-8">
											
					</td>
				</tr>
		</table>
	</spring:form>
</body>
</html>