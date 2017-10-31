<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-1_0-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="ts24-window-content">
	<div class="contenttable">
		<h2 class="search">Báo cáo bảng kê mua vào bán ra
		</h2>
		<form id="searchBangKeHoaDonBanRaTongHopForm" name="searchBangKeHoaDonBanRaTongHopForm">
			<table width="100%" cellspacing="0" cellpadding="3" border="0"
				class="txt_1">
				<tbody>
					<tr>
						<td width="25%" valign="middle" align="right" class="input_form">Từ
							ngày:</td>
						<td valign="middle" width="20%"><input class="textinput tcal input" type="text"
							name="tuNgay" id="tuNgay" style="width: 150px;" value="${tuNgay}"/></td>
						<td width="15%" valign="middle" align="right" class="input_form">Đến
							ngày:</td>
						<td valign="middle"><input class="textinput tcal input" type="text"
							name="denNgay" id="denNgay" style="width: 150px;" value="${denNgay}"/>[dd/mm/yyyy]</td>
					</tr>

					<tr>
						<td valign="middle" align="right" class="input_form">Tỉnh(Tphố):</td>
						<td valign="middle"><select class="form-control changecity"
							name="maTinh">
								<option value="">
									<c:out value="---------- Tất cả -------------"></c:out>
								</option>
								<c:forEach items="${arrTinhTP}" var="item">
									<option value="${item.maTinhThue}">
										<c:out value="${item.tenCoQuanThue}"></c:out>
									</option>
								</c:forEach>
						</select></td>

						<td valign="middle" align="right" class="input_form">Cơ quan
							thuế:</td>
						<td valign="middle"><select name="maCoQuan"
							class="form-control loaddiststric">
								<option value="">
									<c:out value="---------- Tất cả -------------"></c:out>
								</option>

								<c:forEach items="${arrQuan}" var="item">
									<option value="${item.maCoQuanThue}">
										<c:out value="${item.tenCoQuanThue}"></c:out>
									</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td valign="middle" align="right" class="input_form">MST
							người bán:</td>
						<td valign="middle"><input id="mstNguoiBan" type="text"
							name="mstNguoiBan" style="width: 150px" maxlength="255"></td>

						<td valign="middle" align="right" class="input_form">Loại hóa
							đơn:</td>
						<td valign="middle"><select name=loaiHDon id="loaiHDon">
								<option value="" selected="selected">------ Tất cả ------</option>
								<option value="1" >Hóa đơn điện tử</option>
								<option value="2">Hóa đơn xác thực</option>
						</select></td>
					</tr>
					<tr>
						<td valign="middle" align="center" colspan="4">
							<span>
							<input class='bntInput' value='Xuất bảng kê hóa đơn bán ra' type='button' onclick="clickExport('exportToExcelTongHopBangKeHoaDonBanRa.bv', 'Bảng kê hóa đơn bán ra');">
							</span><span>
							<input class='bntInput' value='Xuất báo cáo tổng hợp (BC26)' type='button' onclick="clickExport('exportToExcelTongHopBC26.bv', 'Tổng hợp BC26');">
							</span><span>
							<input class='bntInput' value='Xuất thông báo phát hành' type='button' onclick="clickExport('exportToExcelThongBaoPhatHanh.bv', 'Thông báo phát hành');">
							</span>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div id="output"></div>
	</div>
</div>
<div align="center" class="error">${errorMessage}</div>

<script type="text/javascript">
	function clickExport(url, title){
		var check = true;
		if(url != 'exportToExcelThongBaoPhatHanh.bv'){
			check = checkInputValue();
		}
		if(check){
			if (confirm("Bạn có muốn xuất Excel báo cáo ["+ title +"] không?") == true) {
				var link = document.createElement("a");    
				link.id="lnkDwnldLnk";
				document.body.appendChild(link);
				$("#lnkDwnldLnk").attr({'href': '${REPORT_CONTEXT_PATH}/'+ url + "?" + $('#searchBangKeHoaDonBanRaTongHopForm').serialize() }); 
				$('#lnkDwnldLnk')[0].click();    
				document.body.removeChild(link);
		    }
		}
	}
	
	function  checkInputValue(){
		if (document.searchBangKeHoaDonBanRaTongHopForm.tuNgay.value == "") {
			alert("Vui lòng chọn ngày bắt đầu!");
			document.searchBangKeHoaDonBanRaTongHopForm.tuNgay.select();
			document.searchBangKeHoaDonBanRaTongHopForm.tuNgay.focus();
			$("#loading").hide();
			$("#loading-b").hide();
			hideLoadding();
			return false;
		}
		if (document.searchBangKeHoaDonBanRaTongHopForm.denNgay.value == "") {
			alert("Vui lòng chọn ngày kết thúc!");
			document.searchBangKeHoaDonBanRaTongHopForm.denNgay.select();
			document.searchBangKeHoaDonBanRaTongHopForm.denNgay.focus();
			hideLoadding();
			return false;
		}
	
		if (checkValidNgay(document.searchBangKeHoaDonBanRaTongHopForm.tuNgay.value) == false) {
			alert("Định dạng từ ngày không hợp lệ!");
			document.searchBangKeHoaDonBanRaTongHopForm.tuNgay.select();
			document.searchBangKeHoaDonBanRaTongHopForm.tuNgay.focus();
			hideLoadding();
			return false;
		}
	
		if (checkValidNgay(document.searchBangKeHoaDonBanRaTongHopForm.denNgay.value) == false) {
			alert("Định dạng đến ngày không hợp lệ!");
			document.searchBangKeHoaDonBanRaTongHopForm.denNgay.select();
			document.searchBangKeHoaDonBanRaTongHopForm.denNgay.focus();
			hideLoadding();
			return false;
		}
	
		if (parseInt(formatNamThangNgay(document.searchBangKeHoaDonBanRaTongHopForm.denNgay.value)) < parseInt(formatNamThangNgay(document.searchBangKeHoaDonBanRaTongHopForm.tuNgay.value))) {
			alert('Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu');
			document.searchBangKeHoaDonBanRaTongHopForm.tuNgay.focus();
			document.searchBangKeHoaDonBanRaTongHopForm.tuNgay.select();
			hideLoadding();
			return false;
		}
											
		 if(checkMoth(document.searchBangKeHoaDonBanRaTongHopForm.tuNgay.value ,document.searchBangKeHoaDonBanRaTongHopForm.denNgay.value) > 93){
			alert('Vui lòng chọn ngày bắt đầu và ngày kết thúc trong vòng 3 tháng!');
			document.searchBangKeHoaDonBanRaTongHopForm.tuNgay.focus();
			document.searchBangKeHoaDonBanRaTongHopForm.tuNgay.select();
			hideLoadding();
			return false;
		}
		 return true;
	} 
	function btnSearch() {
		$("#loading").show();
		$("#loading-b").show();
		var idTable = "#HDDTTable";		
		setupTable(idTable);
		$(idTable).jtable('reload');
		$("#loading").hide();
		$("#loading-b").hide();
	}

	$(document).ready(function() {
		$(".changecity").change(function() {
			var id = $(this).val();
			var id_String = 'maTinh=' + id;
			$.ajax({
				type : "POST",
				url : "${HOADON_DIENTU_CONTEXT_PATH}/changeCity.bv",
				data : id_String,
				cache : false,
				success : function(macqtdata) {
					$(".loaddiststric").html(macqtdata);
				}
			});

		});
	});
	

	function formatNamThangNgay(today) {
		var namThangNgay = "";
		if (today.length > 0) {
			var nam = null;
			var thang = null;
			var ngay = null;
			var arr = new Array();
			today = today.trim();
			arr = today.split("/");
			if (arr.length > 0) {
				ngay = arr[0];
				thang = arr[1];
				nam = arr[2];
				namThangNgay = nam + thang + ngay;
			}
		}
		return namThangNgay;
	}
	
	function checkMoth(tungay,denngay){
		 var to = moment(tungay, "DD/MM/YYYY");
		var from = moment(denngay, "DD/MM/YYYY");
		var d1 = new Date(to);
		var d2 = new Date(from);
					
		var diff = d2.getTime() - d1.getTime();
		var diffDays = diff / (24 * 60 * 60 * 1000);
		return diffDays; 
	}
	
	function hideLoadding() {
		$("#loading").hide();
		$("#loading-b").hide();		
		$('#btnExport').css('visibility', 'hidden');
		$('.jtable-busy-message').css('display', 'none');
	}
	
</script>