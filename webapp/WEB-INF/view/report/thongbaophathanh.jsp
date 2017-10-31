<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-1_0-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="ts24-window-content">
	<div class="contenttable">
		<h2 class="search">Tra cứu thông báo phát hành hóa đơn
		</h2>
		<form id="searchPhatHanhHoaDonForm" name="searchPhatHanhHoaDonForm">
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
						<td valign="middle"><select name=loaiHDon id="loaiHDon" class="form-control">
								<option value="" selected="selected">------ Tất cả ------</option>
								<option value="1" >Hóa đơn điện tử</option>
								<option value="2">Hóa đơn xác thực</option>
						</select></td>
					</tr>
					<tr>
						<td class="input_form">&nbsp;</td>
						<td valign="middle" align="left" colspan="3">
							<input class='bntInput' value='Tra cứu' type='button' onclick="btnSearch();">
							<span style="visibility: hidden;" id="btnExport"></span>
							<span style="visibility: hidden; float: right;" id="btnExportPhatHanh"></span>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div id="output"></div>
	</div>
</div>
<div id="errorDialog"></div>
<div id="popupInvoiceNotice"></div>
<div align="center" class="error">${errorMessage}</div>
<div id="HDDTTable" class="jtable-main-container"></div>

<script type="text/javascript">
	var fieldSorting = "";
	var exportInvoiceNoticePopupId = "#popupInvoiceNotice";

	function viewPopup(selectedNoticeIds){
		window.open("${REPORT_CONTEXT_PATH}/baoCaoPhatHanhTongHop.bv?selectedNoticeIds="+ selectedNoticeIds + "&" + $('#searchPhatHanhHoaDonForm').serialize(),'_blank',
			'height=300,width=630,left=350,top=200,resizable=yes,scrollbars=yes,toolbar=no,menubar=no,location=no,directories=no,status=no,titlebar=yes');
	}
	$(document).ready(function(){
		$('#btnExport').click(function(){
			processNotices();	
		});
		
		$('#btnExportPhatHanh').click(function(){
			processExportPhatHanh();	
		});
	
	});
	
	function processExportPhatHanh(){
		var seletedNotices = getSelectedNotice();
		if (seletedNotices.length > 0){
			clickExportPhatHanh(seletedNotices, 'exportToExcelListSelectedThongBaoPhatHanh.bv');
		}else{
			DEMOMVC.showWarning('Vui lòng chọn dữ liệu cần xuất excel thông báo phát hành hóa đơn.');
		}
	}
	
	function clickExportPhatHanh(seletedNotices, url){
		if (confirm("Bạn có muốn xuất Excel danh sách phát hành hóa đơn này không?") == true) {
				var link = document.createElement("a");    
				link.id="lnkDwnldLnk";
				document.body.appendChild(link);
				$("#lnkDwnldLnk").attr({'href': '${REPORT_CONTEXT_PATH}/'+ url + '?fieldSorting='+ fieldSorting +'&selectedNoticeIds='+ seletedNotices + '&' + $('#searchPhatHanhHoaDonForm').serialize()}); 
				$('#lnkDwnldLnk')[0].click();    
				document.body.removeChild(link);
		    }
	} 
	
	function processNotices(){
		var seletedNotices = getSelectedNotice();
		//alert(seletedNotices);
		if (seletedNotices.length > 0){
			viewPopup(seletedNotices);
		}else{
			DEMOMVC.showWarning('Vui lòng chọn thông báo phát hành hóa đơn cần xuất ecxel.');
		}
	}
	
	function getSelectedNotice(){
		//Get all selected rows
	    var selectedRows = $('#HDDTTable').jtable('selectedRows');
	    if (selectedRows.length > 0) {
	        //Show selected rows
	       selectedRows = jQuery.map(selectedRows,function (row) {
	            var record = $(row).data('record');
	            return record.ID;
	        });
	    }
	    return selectedRows;
	}
	
	function clickExport(fieldSorting){
		if (confirm("Bạn có muốn xuất báo cáo này ra excel không?") == true) {
			var link = document.createElement("a");    
			link.id="lnkDwnldLnk";
			document.body.appendChild(link);
			$("#lnkDwnldLnk").attr({'href': '${REPORT_CONTEXT_PATH}/exportToExcelBangKeBanRaSearch.bv?fieldSorting='+ fieldSorting +'&' + $('#searchPhatHanhHoaDonForm').serialize() }); 
			$('#lnkDwnldLnk')[0].click();    
			document.body.removeChild(link);
	    }
	} 
	function btnSearch() {
		$("#loading").show();
		$("#loading-b").show();
		var idTable = "#HDDTTable";		
		setupTable(idTable);
		$(idTable).jtable('reload');
		$("#loading").hide();
		$("#loading-b").hide();
		$('.jtable-busy-message').css('display', 'none');
	}
	function setupTable(idTable) {
		$(idTable).jtable({
			title : '',
			//paging : false,
			//pageSize : 50,
			sorting : true,
			multiSorting : true,
			defaultSorting : 'MaSoThue DESC',
			selecting: true, //Enable selecting
 	        multiselect: true, //Allow multiple selecting
 	        selectingCheckboxes: true, //Show checkboxes on first column */
        
			actions : {
				listAction : function(postData, jtParams) {
				 	if (document.searchPhatHanhHoaDonForm.tuNgay.value == "") {
						alert("Vui lòng chọn ngày bắt đầu!");
						document.searchPhatHanhHoaDonForm.tuNgay.select();
						document.searchPhatHanhHoaDonForm.tuNgay.focus();
						$("#loading").hide();
						$("#loading-b").hide();
						hideLoadding();
						return;
					}
					if (document.searchPhatHanhHoaDonForm.denNgay.value == "") {
						alert("Vui lòng chọn ngày kết thúc!");
						document.searchPhatHanhHoaDonForm.denNgay.select();
						document.searchPhatHanhHoaDonForm.denNgay.focus();
						hideLoadding();
						return;
					}

					if (checkValidNgay(document.searchPhatHanhHoaDonForm.tuNgay.value) == false) {
						alert("Định dạng từ ngày không hợp lệ!");
						document.searchPhatHanhHoaDonForm.tuNgay.select();
						document.searchPhatHanhHoaDonForm.tuNgay.focus();
						hideLoadding();
						return;
					}

					if (checkValidNgay(document.searchPhatHanhHoaDonForm.denNgay.value) == false) {
						alert("Định dạng đến ngày không hợp lệ!");
						document.searchPhatHanhHoaDonForm.denNgay.select();
						document.searchPhatHanhHoaDonForm.denNgay.focus();
						hideLoadding();
						return;
					}

					if (parseInt(formatNamThangNgay(document.searchPhatHanhHoaDonForm.denNgay.value)) < parseInt(formatNamThangNgay(document.searchPhatHanhHoaDonForm.tuNgay.value))) {
						alert('Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu');
						document.searchPhatHanhHoaDonForm.tuNgay.focus();
						document.searchPhatHanhHoaDonForm.tuNgay.select();
						hideLoadding();
						return;
					}
														
					if(checkMoth(document.searchPhatHanhHoaDonForm.tuNgay.value ,document.searchPhatHanhHoaDonForm.denNgay.value) > 93){
						alert('Vui lòng chọn ngày bắt đầu và ngày kết thúc trong vòng 3 tháng!');
						document.searchPhatHanhHoaDonForm.tuNgay.focus();
						document.searchPhatHanhHoaDonForm.tuNgay.select();
						hideLoadding();
						return;
					}  
					 
					console.log("Loading from custom function...");
					return $.Deferred(function($dfd) {
						$.ajax({
							url : '${REPORT_CONTEXT_PATH}/reportThongBaoPhatHanh.bv?jtStartIndex=' + 0 + '&jtPageSize=' + 0 + '&fieldSorting='+ jtParams.jtSorting,
							type : 'POST',
							dataType : 'json',
							data : $('#searchPhatHanhHoaDonForm').serialize(),
							success : function(data) {
								if(data.TotalRecordCount>0){
									fieldSorting = jtParams.jtSorting;
                            		$('#btnExport').css('visibility', 'visible');
                            		//$('#btnExport').html("<input class='bntInput' value='Xuất Excel' type='button' onclick='clickExport(&#039;" + fieldSorting +"&#039;);'>");
                            		$('#btnExport').html("<input class='bntInput' value='Xuất báo cáo' type='button'>");
                            		$('#btnExportPhatHanh').css('visibility', 'visible');
                            		$('#btnExportPhatHanh').html("<input class='bntInput' value='Xuất excel ds phát hành ' type='button'>");
                            	}else{
                            		$('#btnExport').css('visibility', 'hidden');
                            		$('#btnExportPhatHanh').css('visibility', 'hidden');
                            	}
								$dfd.resolve(data);
							},
							error : function() {
								$dfd.reject();
							}
						});
					});
				}
			},
			fields : {
				 STT : {
					key : false,
					edit : false,
					title : 'STT',
					width: '5%',
					display : function(data) {
						return data.record.STT;
					},
					sorting : false
				},
				MaSoThue : {
					key : true,
					edit : false,
					title : 'Mã số thuế',
					width: '12%',
					display : function(data) {
						return data.record.MaSoThue;
					},
					sorting : true
				},
				MauHoaDon : {
					key : true,
					edit : false,
					width: '10%',
					title : 'Mẫu hóa đơn',
					display : function(data) {
						return data.record.MauHoaDon;	
					},
					sorting : true
				},
				KyHieu : {
					key : true,
					edit : false,
					width: '10%',
					title : 'Ký hiệu HĐ',
					display : function(data) {
						return data.record.KyHieu;
								
					},
					sorting : true
				},
				
				SoLuong : {
					key : true,
					edit : false,
					width: '10%',
					title : 'Số lượng',
					display : function(data) {
						return data.record.SoLuong;
					},
					sorting : false
				},
				TuSo : {
					key : true,
					edit : false,
					width: '10%',
					title : 'Từ số',
					display : function(data) {
						return data.record.TuSo;
					},
					sorting : true
				},
				DenSo : {
					key : true,
					edit : false,
					width: '10%',
					title : 'Đến số',
					display : function(data) {
						return data.record.DenSo;
					},
					sorting : true
				},
				NgayLapPhieu : {
					key : true,
					width: '15%',
					edit : false,
					title : 'Ngày thông báo',
					display : function(data) {
						return data.record.NgayLapPhieu;
					},
					sorting : false
				},
				NgayBatDauSuDung : {
					key : true,
					width: '15%',
					edit : false,
					title : 'Ngày BĐ sử dụng',
					display : function(data) {
						return data.record.NgayBatDauSuDung;
					},
					sorting : true
				}
				
			}
		});

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
		$('#btnExportPhatHanh').css('visibility', 'hidden');
		$('.jtable-busy-message').css('display', 'none');
		//$('#HDDTTable > div > div.jtable-busy-message').css('visibility', 'hidden');
		
	}
	
	
</script>