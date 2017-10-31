<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-1_0-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld" %>


<div class="bell-window-content">
	<div class="contenttable">
		<div class="introText" style="border-bottom: 1px dotted #CCCCCC;">
			<p>Thay đổi mật khẩu</p>
		</div>
		<!-- <div class="stepIntro" style="margin-top: 10px;">The following properties will be used to create your new project</div>
		<div class="requiredfield" style="color: #666666;margin-left: 10px;">* Indicates required fields</div> -->
		<div style="border-bottom: 1px dotted #CCCCCC;margin: 0px 0px 10px 0px;"></div>
		<spring:form  commandName="userForm" id="userForm" name="userForm" action="${ADMIN_CONTEXT_PATH}/changePass.bv">
			<table width="100%" cellspacing="0" cellpadding="3" border="0"	class="txt_1">				
					<tbody>
					<c:if test="${not empty errorMessage && errorMessage !=''}">
					<tr>
						<td align="center" class="error" colspan="3" style="border-bottom: 1px dotted #CCCCCC;">
								${errorMessage}
						</td>
					</tr>
					</c:if>
					<c:if test="${not empty successMessage && successMessage !=''}">
					<tr>
						<td align="center" class="success" colspan="3" style="border-bottom: 1px dotted #CCCCCC;">
								${successMessage}
						</td>
					</tr>
					</c:if>
					
					<tr>
						<td width="35%" align="right" class="input_form"> 
							Mật khẩu cũ<span class="requiredfield">*</span>:
						</td>
						<td width="15%">
							<div>
								<spring:password path="matKhauCu"  style="width:200px" maxlength="255" /> 
							</div>
							
						</td>
						<td><spring:errors path="matKhauCu" cssClass="error"/></td>
					
					</tr>
					
					<tr>
						<td align="right" class="input_form"> 
							Mật khẩu mới<span class="requiredfield">*</span>:
							
						</td>
						<td>
							<div>
								<spring:password path="matKhauMoi"  style="width:200px" maxlength="255" /> 
							</div>
							
						</td>
						<td><spring:errors path="matKhauMoi" cssClass="error"/></td>
					
					</tr>
					<tr >
						<td align="right" class="input_form"> 
							Nhập lại mật khẩu<span class="requiredfield">*</span>:
						</td>
						<td>
							<div>
								<spring:password path="matKhauMoiRe"  style="width:200px" maxlength="255" />
							</div>
							
						</td>
						<td><spring:errors path="matKhauMoiRe" cssClass="error"/></td>
					
					</tr>
					<tr valign="middle">
						
								<td></td>
								<td  valign="middle">
									<input class="formButton" value="Thay đổi" type="submit">
									<input class="formButton" value="Hủy" type="button" onclick="goBack()">
								</td>
								<td></td>
							
					</tr>
				</tbody>
			</table>
		</spring:form>
		
	</div>
</div>
<script type="text/javascript" charset="utf-8">
	function goBack(){
		DEMOMVC.redirect('${CONTEXT_PATH}/home.bv');
	}
			
</script>