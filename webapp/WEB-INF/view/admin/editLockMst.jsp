<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-1_0-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div class="ts24-window-content">
	<div class="contenttable">
		<h2>Sửa khóa mã số thuế</h2>
		<spring:form commandName="createLockMstForm" id="createLockMstForm" name="createLockMstForm"
			action="${ADMIN_CONTEXT_PATH}/editLockMst.bv">
			<table width="100%" cellspacing="0" cellpadding="3" border="0" class="txt_1">
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
						<td align="center" class="error" colspan="3" style="border-bottom: 1px dotted #CCCCCC; color: blue;">
								${successMessage}
						</td>
					</tr>
					</c:if>
					<tr>
						<td width="35%" align="right" class="input_form">Mã số thuế<span
								class="requiredfield">*</span>:
						</td>
						<td width="25%">
							<spring:input path="mst" id="mst" type="text" name="mst" style="width:200px" maxlength="255" />
							<spring:input path="id" id="id" type="hidden" name="id" />
							<spring:input path="ngayTao" id="ngayTao" type="hidden" name="ngayTao" />
						</td>
						<td ><spring:errors path="mst" cssClass="error" /></td>
					</tr>
					<tr>
						<td valign="middle" align="right" class="input_form">Ngày bắt đầu<span class="requiredfield">*</span>: </td>
						<td valign="middle"><input class="textinput tcal input" type="text"
							name="ngayBd" id="ngayBd" style="width: 150px;" value="${ngayBd}"/></td>
						<td><spring:errors path="ngayBd" cssClass="error" /></td>
					</tr>
					
					<tr>
						<td valign="middle" align="right" class="input_form">Ngày kết thúc: </td>
						<td valign="middle"><input class="textinput tcal input" type="text"
							name="ngayKt" id="ngayKt" style="width: 150px;" value="${ngayKt}"/></td>
						<td ><spring:errors path="ngayKt" cssClass="error" /></td>
					</tr>

					<tr valign="middle">
						<td></td>
						<td valign="middle">
							<input class="formButton" value="Cập nhật" type="submit"> 
							<input class="formButton" value="Hủy" type="button" onclick="goBack()"></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</spring:form>

	</div>
</div>
<script type="text/javascript" charset="utf-8">
	function goBack() {
		DEMOMVC.redirect('${ADMIN_CONTEXT_PATH}/lock_mst.bv');
	}
</script>