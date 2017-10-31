<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-1_0-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld"%>

<div class="ts24-window-content">
	<div class="contenttable">
		<div class="introText">
			<h2>Cập nhật thông tin tài khoản</h2>
		</div>
		<spring:form commandName="userForm" id="userForm" name="userForm"
			method="POST" action="${ADMIN_CONTEXT_PATH}/editUser.bv">
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
						<td width="35%" align="right" class="input_form">Tên tài
							khoản<span class="requiredfield">*</span>:
						</td>
						<td valign="middle" width="25%"><spring:input path="fullName"
								style="width:200px" maxlength="255" /></td>
						<td><spring:errors path="fullName" cssClass="error" /></td>

					</tr>

					<tr>
						<td align="right" class="input_form"><spring:input
								path="username" id="username" type="hidden" name="username" />
							Tài khoản đăng nhập<span class="requiredfield">*</span>:</td>
						<td valign="middle"><spring:input path="username"
								disabled="true" style="width:200px" maxlength="255" /></td>
						<td><spring:errors path="username" cssClass="error" /></td>

					</tr>

					<tr>
						<td align="right" class="input_form">Email<span
							class="requiredfield">*</span>:
						</td>
						<td valign="middle"><spring:input path="email"
								style="width:200px" maxlength="255" /></td>
						<td><spring:errors path="email" cssClass="error" /></td>

					</tr>

					<tr>
						<td align="right" class="input_form">Cấp quản lý<span
							class="requiredfield">*</span>:
						</td>
						<td valign="middle"><spring:select path="role"
								onchange="change_quanly('${ADMIN_CONTEXT_PATH}/changequanlyedit.bv');"
								style="min-width:210px">
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

							</spring:select></td>
						<td><spring:errors name="role" cssClass="error" /></td>

					</tr>

					<tr>
						<td align="right" class="input_form">Tỉnh(T.Phố)<span
							class="requiredfield">*</span>:
						</td>
						<td valign="middle"><spring:select path="matinh"
								class="form-control changecity" style="min-width:210px">

								<c:forEach var="item" items="${arrTinhTP}">
									<c:choose>
										<c:when test="${userForm.matinh == item.maTinhThue}">
											<spring:option selected="true" value="${item.maTinhThue}">
												<c:out value="${item.tenCoQuanThue}"></c:out>
											</spring:option>
										</c:when>

										<c:otherwise>
											<spring:option value="${item.maTinhThue}">
												<c:out value="${item.tenCoQuanThue}"></c:out>
											</spring:option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</spring:select></td>
						<td><spring:errors path="matinh" cssClass="error" /></td>

					</tr>


					<tr>
						<td align="right" class="input_form"><span
							class="requiredfield"></span>Quận:</td>
						<td valign="middle"><c:choose>
								<c:when
									test="${not empty quyenMaQuan  && quyenMaQuan eq 'quan'}">
									<c:set var="disable_combobox" value="true"></c:set>
								</c:when>
								<c:otherwise>
									<c:set var="disable_combobox" value="false"></c:set>
								</c:otherwise>
							</c:choose> <spring:select path="macqt" class="form-control loaddiststric"
								style="min-width:210px" disabled="${disable_combobox}">

								<c:choose>
									<c:when test="${userForm.role == 2}">
										<spring:option value="">
											<c:out value="------ Tất cả quận theo tỉnh ------"></c:out>
										</spring:option>

									</c:when>

									<c:otherwise>
										<spring:option value="">
											<c:out value="------ Vui lòng chọn quận ------"></c:out>
										</spring:option>
									</c:otherwise>
								</c:choose>

								<c:forEach items="${arrQuan}" var="item">
									<c:choose>
										<c:when test="${userForm.macqt == item.maCoQuanThue}">
											<spring:option selected="true" value="${item.maCoQuanThue}">
												<c:out value="${item.tenCoQuanThue}"></c:out>
											</spring:option>
										</c:when>

										<c:otherwise>
											<spring:option value="${item.maCoQuanThue}">
												<c:out value="${item.tenCoQuanThue}"></c:out>
											</spring:option>
										</c:otherwise>
									</c:choose>


								</c:forEach>
							</spring:select></td>
						<td><spring:errors path="macqt" cssClass="error" /></td>

					</tr>

					<tr>
						<td align="right" class="input_form">Nhóm quyền quản lý<span
							class="requiredfield">*</span>:
						</td>
						<td valign="middle" colspan="2"><c:forEach
								items="${arrNhomQuyen}" var="item">
								<span class="divide_2column_permission"> <input
									name="chkRule" id="chkRule" type="checkbox"
									value="${item.maNhomQuyen}"
									<c:forEach items="${arrUserRule}" var="user_rule">
										  	<c:if test="${item.maNhomQuyen eq user_rule.rule}"><c:out value="checked"></c:out> </c:if> 							  	
										  </c:forEach> /> ${item.tenNhomQuyen}
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
							class="requiredfield"></span>Giới hạn:</td>
						<td valign="middle">
							<div>
								<input name="limit" id="limit" type="checkbox" value="1"
									<c:if test="${limit == 1}"><c:out value="checked"></c:out> </c:if> /> Giới
								hạn Mã số thuế
							</div>

						</td>
						<td><spring:errors path="limit" cssClass="error" /></td>

					</tr>


					<tr id="ShowHide" class="ShowHide"
						<c:if test="${limit == 0}"> style = "display: none;  padding: 20px; margin-top: 20px;"  </c:if>>
						<td align="right" class="input_form">MST giới hạn<span
							class="requiredfield">*</span>:
						</td>
						<td valign="middle">
							<div>
								<spring:input path="limitMST" style="width:200px" />
							</div>

						</td>

						<td><spring:errors path="limitMST" cssClass="error" /></td>

					</tr>

					<tr>
						<td colspan="3">
							<div style="margin: 20px 0px 20px 0px;"></div>
						</td>
					</tr>

					<tr valign="middle">
						<td></td>
						<td valign="middle"><input class="formButton"
							value="Cập nhật" type="submit"> <input class="formButton"
							value="Hủy" type="button" onclick="goBack()"></td>
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

		});
	});

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

	function change_quanly(action) {
		document.userForm.action = action;
		document.userForm.submit();
	}
</script>
