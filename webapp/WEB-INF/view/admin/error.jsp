<div class="ts24-titlebar">
	<div class="ts24-titlebar-left"></div>
	<div class="ts24-titlebar-right"></div>
	<div class="ts24-titlebar-center">
		<div class="ts24-titlebar-title-panel">
			<h2>Quyền quản lý tài khoản</h2>
		</div>
		<div class="ts24-titlebar-button-panel">&nbsp;</div>
	</div>
</div>
<div class="error" align="center">
	<div>Bạn không có quyền thực hiện thao tác này, vui lòng liên hệ admin để biết thông tin quyền sử dụng.</div>
	<br />
	<div>
		<input class="formButton" value="Trờ về" type="button" onclick="goBack()">
	</div>
</div>
<script type="text/javascript" charset="utf-8">
	function goBack() {
		DEMOMVC.redirect('${CONTEXT_PATH}/home.bv');
	}
</script>
