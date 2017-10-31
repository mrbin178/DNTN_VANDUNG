<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-1_0-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8;charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap/bootstrap-theme.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/webstyles.css"/>" />
</head>
<body>
	
	<div class="ts24-titlebar">
		<div class="ts24-titlebar-left"></div>
		<div class="ts24-titlebar-right"></div>
		<div class="ts24-titlebar-center">
			<div class="ts24-titlebar-title-panel" align="center">
				<h2>Thông tin chi tiết tài khoản</h2>
			</div>
			<div class="ts24-titlebar-button-panel">&nbsp;</div>
		</div>
	</div>
	<div class="ts24-window-content">
		<div class="contenttable">
			<form commandName="userForm" id="userForm" name="userForm" action="">
				<!-- <table class="formTable" style="width: 100%;"> -->
				<table width="100%" cellspacing="0" cellpadding="3" border="0"
					class="txt_1">
					<!-- <tbody class="rowIntro"> -->
					<tbody>
						<tr>
							<td width="30%" align="right" class="input_form"><span
								class="requiredfield"></span>Tên tài khoản:</td>
							<td>
								<div>${userForm.fullName}</div>
							</td>
						</tr>
						<tr>
							<td width="25%" align="right" class="input_form"><span
								class="requiredfield"></span>Tài khoản đăng nhập:</td>
							<td>
								<div>${userForm.username}</div>
							</td>
						</tr>
						<tr>
							<td width="25%" align="right" class="input_form"><span
								class="requiredfield"></span>Email:</td>
							<td>
								<div>${userForm.email}</div>
							</td>
						</tr>
	
						<tr>
							<td width="25%" align="right" class="input_form"><span
								class="requiredfield"></span>Cấp quản lý:</td>
							<td>
								<div>
									<c:if test="${userForm.role == 2}"> Quản lý tỉnh</c:if>
									<c:if test="${userForm.role == 3}"> Quản lý quận </c:if>
									<c:if test="${userForm.role == 4}"> Người dùng</c:if>
								</div>
							</td>
						</tr>
	
						<tr>
							<td width="25%" align="right" class="input_form"><span
								class="requiredfield"></span>Tên cơ quan:</td>
							<td>
								<div>${userForm.tencqt}</div>
							</td>
						</tr>
						<tr>
							<td width="25%" align="right" class="input_form"><span
								class="requiredfield"></span>MST LIMIT:</td>
							<td>
								<div>${userForm.limitMST}</div>
							</td>
						</tr>
	
						<tr>
							<td width="25%" align="right" class="input_form"><span
								class="requiredfield"></span>Nhóm quyền quản lý:</td>
							<td>
								<div>
									<c:forEach items="${arrNhomQuyen}" var="item">
										<span class="divide_2column_permission"> <input
											name="chkRule" id="chkRule" disabled="disabled"
											type="checkbox" value="${item.maNhomQuyen}"
											<c:forEach items="${arrUserRule}" var="user_rule">
									  	<c:if test="${item.maNhomQuyen eq user_rule.rule}"><c:out value="checked"></c:out> </c:if> 							  	
									  </c:forEach> /> ${item.tenNhomQuyen}
										</span>
									</c:forEach>
								</div>
							</td>
						</tr>
	
					</tbody>
				</table>
			</form>
		</div>
	</div>
</body>
</html>