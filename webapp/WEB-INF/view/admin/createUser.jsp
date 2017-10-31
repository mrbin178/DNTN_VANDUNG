<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-1_0-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div class="ts24-window-content">
	<div class="contenttable">
		<h2>Tạo tài khoản</h2>
		<spring:form commandName="userForm" id="userForm" name="userForm"
			action="${ADMIN_CONTEXT_PATH}/createNewUser.bv">
			<table width="100%" cellspacing="0" cellpadding="3" border="0"
				class="txt_1">
				<tbody>
					<c:if test="${not empty errorMessage && errorMessage !=''}">
						<tr>
							<td align="center" class="error" colspan="3"
								style="border-bottom: 1px dotted #CCCCCC;">${errorMessage}
							</td>
						</tr>
					</c:if>
					<tr>
						<td width="35%" align="right" class="input_form">Tên tài khoản<span
								class="requiredfield">*</span>:
						</td>
						<td width="25%"><spring:input path="fullName" id="fullName"
								type="text" name="fullName" style="width:200px" maxlength="255" />
						</td>
						<td><spring:errors path="fullName" cssClass="error" /></td>
					</tr>
					<tr>
						<td align="right" class="input_form">Tài khoản đăng nhập<span
								class="requiredfield">*</span>:
						</td>
						<td><spring:input path="username" id="username" type="text"
								name="username" style="width:200px" maxlength="255" /></td>
						<td><spring:errors path="username" cssClass="error" /></td>
					</tr>
					<tr>
						<td align="right" class="input_form">Mật khẩu<span
								class="requiredfield">*</span>:
						</td>
						<td><spring:password path="matKhau" style="width:200px"
								maxlength="255" /></td>
						<td><spring:errors path="matKhau" cssClass="error" /></td>

					</tr>
					<tr>
						<td align="right" class="input_form">Email<span
								class="requiredfield">*</span>:
						</td>
						<td><spring:input path="email" id="email" type="text"
								name="email" style="width:200px" maxlength="255" /></td>
						<td><spring:errors path="email" cssClass="error" /></td>
					</tr>
					<tr>
						<td align="right" class="input_form">Cấp quản lý<span
								class="requiredfield">*</span>:
						</td>
						<td><spring:select path="role" cssClass="form-control"
								onchange="change_quanly('${ADMIN_CONTEXT_PATH}/changequanly.bv');"
								style="min-width:210px">

								<c:if test="${role < 3}">
									<c:choose>
										<c:when test="${userForm.role == 2}">
											<spring:option selected="true" value="2">
							                Quản lý tỉnh
							            </spring:option>
										</c:when>
										<c:otherwise>
											<spring:option value="2">
							                Quản lý tỉnh
							            </spring:option>
										</c:otherwise>
									</c:choose>
								</c:if>
								<c:if test="${role < 4}">
									<c:choose>
										<c:when test="${userForm.role == 3}">
											<spring:option selected="true" value="3">
							                Quản lý quận
							            </spring:option>

										</c:when>

										<c:otherwise>
											<spring:option value="3">
							                Quản lý quận
							            </spring:option>
										</c:otherwise>
									</c:choose>
								</c:if>
								<c:if test="${role < 5}">
									<c:choose>
										<c:when test="${userForm.role == 4}">
											<spring:option selected="true" value="4">
							               Người dùng
							            </spring:option>

										</c:when>

										<c:otherwise>
											<spring:option value="4">
							               Người dùng
							            </spring:option>
										</c:otherwise>
									</c:choose>
								</c:if>
							</spring:select></td>
						<td><spring:errors name="role" cssClass="error" /></td>
					</tr>
					<tr>
						<td align="right" class="input_form">Tỉnh(T.Phố)<span
								class="requiredfield">*</span>:
						</td>
						<td><spring:select class="form-control changecity"
								path="matinh" style="min-width:210px">
								<c:forEach items="${arrTinhTP}" var="item">
									<spring:option value="${item.maTinhThue}">
										<c:out value="${item.tenCoQuanThue}"></c:out>
									</spring:option>
								</c:forEach>
							</spring:select></td>
						<td><spring:errors path="matinh" cssClass="error" /></td>

					</tr>

					<tr>
						<td align="right" class="input_form"><span
								class="requiredfield"></span>Quận:
						</td>
						<td><c:choose>
								<c:when
									test="${not empty quyenMaQuan  && quyenMaQuan eq 'quan'}">
									<c:set var="disable_combobox" value="true"></c:set>
								</c:when>
								<c:otherwise>
									<c:set var="disable_combobox" value="false"></c:set>
								</c:otherwise>
							</c:choose> <spring:select path="macqt" class="form-control loaddiststric"
								disabled="${disable_combobox}" style="min-width:210px">
								<c:choose>
									<c:when test="${userForm.role == 2}">
										<spring:option value="">
											<c:out value="------ Tất cả ------"></c:out>
										</spring:option>
									</c:when>
									<c:otherwise>
										<spring:option value="">
											<c:out value="------ Vui lòng chọn quận ------"></c:out>
										</spring:option>
									</c:otherwise>
								</c:choose>

								<c:forEach items="${arrQuan}" var="item">
									<spring:option value="${item.maCoQuanThue}">
										<c:out value="${item.tenCoQuanThue}"></c:out>
									</spring:option>
								</c:forEach>
							</spring:select></td>
						<td><spring:errors path="macqt" cssClass="error" /></td>

					</tr>
					<tr>
						<td valign="top" align="right" class="input_form">Nhóm quyền quản lý<span
								class="requiredfield">*</span>:
						</td>
						<td colspan="2"><c:forEach items="${arrNhomQuyen}" var="item">
								<span class="divide_2column_permission"> <input
									name="chkRule" id="chkRule" type="checkbox"
									value="${item.maNhomQuyen}" /> ${item.tenNhomQuyen}
								</span>
							</c:forEach></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td colspan="2" align="left"><i>(Nếu giới hạn quyền theo
								mã số thuế thì vui lòng check vào mục giới hạn bên dưới để nhập
								mã số thuế. Các mã số thuế cách nhau bằng dấu phẩy [<strong>0309478306,0309478306-999</strong>]).
						</i></td>
					</tr>
					<tr>
						<td align="right" class="input_form"><span
								class="requiredfield"></span>Giới hạn:
						</td>
						<td><input name="limit" id="limit" type="checkbox" value="1" /> Giới
							hạn Mã số thuế</td>
						<td><spring:errors name="limit" cssClass="error" /></td>

					</tr>
					<tr id="ShowHide" class="ShowHide"
						style="display: none; padding: 20px; margin-top: 20px;">
						<td align="right" class="input_form">MST giới hạn<span
								class="requiredfield">*</span>:
						</td>
						<td><spring:input path="limitMST" name="limitMST"
								style="width:200px" /></td>
						<td><spring:errors path="limitMST" cssClass="error" /></td>
					</tr>

					<tr valign="middle">
						<td></td>
						<td valign="middle"><input class="formButton" value="Tạo"
							type="submit"> <input class="formButton" value="Hủy"
							type="button" onclick="goBack()"></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</spring:form>

	</div>
</div>
<script type="text/javascript" charset="utf-8">
	function goBack() {
		DEMOMVC.redirect('${ADMIN_CONTEXT_PATH}/danhsachnguoidung.bv');
	}

	$(document).ready(function() {
		$('input[type="checkbox"]').click(function() {
			if ($(this).attr("value") == "1") {
				$(".ShowHide").toggle();
			}
			/*  if($(this).attr("value")=="green"){
			     $(".green").toggle();
			 }
			 if($(this).attr("value")=="blue"){
			     $(".blue").toggle();
			 } */
		});
	});

	function showMe(val) {

		if (document.userForm.elements["chkLimit"].checked == true) {
			$("ShowHide").show();
			//document.getElementById ("ShowHide").innerHTML = "<td colspan='3' align='center'><input id='limitMST' type='text' name='limitMST' style='width:500px' maxlength='500' /></td>";
		} else {
			$("ShowHide").hide();
			//document.getElementById ("ShowHide").innerHTML = "";
		}
	}

	function goBack() {
		DEMOMVC.redirect('${ADMIN_CONTEXT_PATH}/danhsachnguoidung.bv');
	}

	function change_quanly(val) {
		DEMOMVC.redirect('${ADMIN_CONTEXT_PATH}/changequanly.bv?role=' + val);
	}
	function change_quanly(action) {
		document.userForm.action = action;
		document.userForm.submit();
	}
	function change_coquanbaohiem(action) {
		document.userForm.action = action;
		document.userForm.submit();
	}

	$(document).ready(function() {
		$(".changecity").change(function() {
			var id = $(this).val();
			var id_String = 'maTinh=' + id;
			$.ajax({
				type : "POST",
				url : "${ADMIN_CONTEXT_PATH}/changeCity.bv",
				data : id_String,
				cache : false,
				success : function(macqtdata) {
					$(".loaddiststric").html(macqtdata);
				}
			});

		});
	});
</script>