<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-1_0-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="ts24-window-content">
	<div class="contenttable" align="center">
		<h2 class="search">Xuất Excel thông tin đã chọn
		</h2>
		<form id="searchBangKeHoaDonBanRaTongHopForm" name="searchBangKeHoaDonBanRaTongHopForm">
			<table width="600px" cellspacing="0" cellpadding="3" border="0"
				class="txt_1">
				<tbody>
					<tr>
						<td width="10%" valign="middle" align="right" class="input_form">Từ
							ngày:</td>
						<td valign="middle" width="20%"><input class="textinput tcal input" type="text"
							name="tuNgay" id="tuNgay" style="width: 150px;" value="${tuNgay}"/></td>
						<td width="12%" valign="middle" align="right" class="input_form">Đến
							ngày:</td>
						<td valign="middle"><input class="textinput tcal input" type="text"
							name="denNgay" id="denNgay" style="width: 150px;" value="${denNgay}"/>[dd/mm/yyyy]</td>
					</tr>
					<tr>
						<td valign="middle" align="center" colspan="4">
							<span>
							<input class='bntInput' value='Xuất bảng kê hóa đơn bán ra' type='button' onclick="clickExport('exportToExcelPhatHanhHoaDonBanRa.bv', 'Bảng kê hóa đơn bán ra', '${selectedNoticeIds}');">
							</span><span>
							<input class='bntInput' value='Xuất báo cáo tổng hợp (BC26)' type='button' onclick="clickExport('exportToExcelPhatHanhTongHopBC26.bv', 'Tổng hợp BC26', '${selectedNoticeIds}');">
							</span><span>
							
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
// 	var selectedNoticeIds = ${selectedNoticeIds};
	function clickExport(url, title, selectedNoticeIds){
		
		var check = true;
		if(url != 'exportToExcelThongBaoPhatHanh.bv'){
			check = checkInputValue();
		}
		if(check){
			if (confirm("Bạn có muốn xuất Excel báo cáo ["+ title +"] không?") == true) {
				var link = document.createElement("a");    
				link.id="lnkDwnldLnk";
				document.body.appendChild(link);
				$("#lnkDwnldLnk").attr({'href': '${REPORT_CONTEXT_PATH}/'+ url + "?selectedNoticeIds="+ selectedNoticeIds +"&" + $('#searchBangKeHoaDonBanRaTongHopForm').serialize() }); 
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
			document.searchBangKeHoaDonBanRaTongHopForm.tuNgayu.select();
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