<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-1_0-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="ts24-window-content">
	<div class="contenttable">
		<h2 class="search">Báo cáo bán hàng
		</h2>
		<form id="searchBaoCaoBanHangForm" name="searchBaoCaoBanHangForm">
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
								<option value="3">HDDT-Xác thực chống giả (Điện tử xác thực)</option>
						</select></td>
					</tr>
					<tr>
						<td class="input_form">&nbsp;</td>
						<td valign="middle" align="left">
							<input class='bntInput' value='Tra cứu' type='button' onclick="btnSearch();">
							<span style="visibility: hidden;" id="btnExport"></span>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div id="output"></div>
	</div>
</div>
<div align="center" class="error">${errorMessage}</div>
<div id="HDDTTable" class="jtable-main-container"></div>

<script type="text/javascript">
var fieldSorting = "";
	function clickExport(){
		if (confirm("Bạn có muốn xuất báo cáo này ra excel không?") == true) {
			var link = document.createElement("a");    
			link.id="lnkDwnldLnk";
			document.body.appendChild(link);
			$("#lnkDwnldLnk").attr({'href': '${REPORT_CONTEXT_PATH}/exportToExcelBaoCaoBanHangSearch.bv?fieldSorting='+ fieldSorting +'&'+ $('#searchBaoCaoBanHangForm').serialize() }); 
			$('#lnkDwnldLnk')[0].click();    
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
	}
	function setupTable(idTable) {
		$(idTable).jtable({
			title : '',
			//paging : true,
			//pageSize : 50,
			sorting : true,
			multiSorting : true,
			defaultSorting : 'NgayXHDon DESC',
			actions : {
				listAction : function(postData, jtParams) {
					
					// kiem tra ngay bao cao
				 	if (document.searchBaoCaoBanHangForm.tuNgay.value == "") {
						alert("Vui lòng chọn ngày bắt đầu!");
						document.searchBaoCaoBanHangForm.tuNgay.select();
						document.searchBaoCaoBanHangForm.tuNgay.focus();
						hideLoadding();
						return;
					}
					if (document.searchBaoCaoBanHangForm.denNgay.value == "") {
						alert("Vui lòng chọn ngày kết thúc!");
						document.searchBaoCaoBanHangForm.denNgay.select();
						document.searchBaoCaoBanHangForm.denNgay.focus();
						hideLoadding();
						return;
					}

					if (checkValidNgay(document.searchBaoCaoBanHangForm.tuNgay.value) == false) {
						alert("Định dạng từ ngày không hợp lệ!");
						document.searchBaoCaoBanHangForm.tuNgay.select();
						document.searchBaoCaoBanHangForm.tuNgay.focus();
						hideLoadding();
						return;
					}

					if (checkValidNgay(document.searchBaoCaoBanHangForm.denNgay.value) == false) {
						alert("Định dạng đến ngày không hợp lệ!");
						document.searchBaoCaoBanHangForm.denNgay.select();
						document.searchBaoCaoBanHangForm.denNgay.focus();
						hideLoadding();
						return;
					}

					if (parseInt(formatNamThangNgay(document.searchBaoCaoBanHangForm.denNgay.value)) < parseInt(formatNamThangNgay(document.searchBaoCaoBanHangForm.tuNgay.value))) {
						alert('Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu');
						document.searchBaoCaoBanHangForm.tuNgay.focus();
						document.searchBaoCaoBanHangForm.tuNgay.select();
						hideLoadding();
						return;
					}
														
					 if(checkMoth(document.searchBaoCaoBanHangForm.tuNgay.value ,document.searchBaoCaoBanHangForm.denNgay.value) > 93){
						alert('Vui lòng chọn ngày bắt đầu và ngày kết thúc trong vòng 3 tháng!');
						document.searchBaoCaoBanHangForm.tuNgay.focus();
						document.searchBaoCaoBanHangForm.tuNgay.select();
						hideLoadding();
						return;
					}   
						
					//alert("lam");
					console.log("Loading from custom function...");
					return $.Deferred(function($dfd) {
						$.ajax({
// 							url : '${REPORT_CONTEXT_PATH}/baoCaoBanHang.bv?jtStartIndex='
// 									+ jtParams.jtStartIndex + '&jtPageSize=' + jtParams.jtPageSize
// 									+ '&jtSorting='+ jtParams.jtSorting,
								url : '${REPORT_CONTEXT_PATH}/baoCaoBanHang.bv?jtStartIndex='
									+ 0 + '&jtPageSize=' + 0
									+ '&jtSorting='+ jtParams.jtSorting,
							type : 'POST',
							dataType : 'json',
							data : $('#searchBaoCaoBanHangForm').serialize(),
							success : function(data) {
							
								if(data.TotalRecordCount>0){
									fieldSorting = jtParams.jtSorting;
// 									alert(fieldSorting);
                            		$('#btnExport').css('visibility', 'visible');
                            		//$('#btnExport').bvl("<a href='#' onclick='clickExport(&#039;" + fieldSorting +"&#039;);'>Xuất Excel</a>");
                            		$('#btnExport').html("<input class='bntInput' value='Xuất Excel' type='button' onclick='clickExport(&#039;" + fieldSorting +"&#039;);'>");
                            	}else{
                            		$('#btnExport').css('visibility', 'hidden');
                            	}
								
								$dfd.resolve(data);
							},
							error : function() {
								$('.jtable-busy-message').css('display', 'none');
								$dfd.reject();
							}
						});
					});
				}
			},
			fields : {
				MaSoThue : {
					key : true,
					edit : false,
					title : 'MST bán',
					display : function(data) {
						return data.record.MaSoThue;
					},
					sorting : true
				},
				 MaHang : {
					key : true,
					edit : false,
					title : 'Mã hàng',
					width: '10%',
					display : function(data) {
						return data.record.MaHang;
					},
					sorting : false
				},
				TenHang : {
					key : true,
					edit : false,
					title : 'Tên hàng',
					width: '15%',
					display : function(data) {
						return  data.record.TenHang;
					},
					sorting : false
				},
				DVT : {
					key : true,
					edit : false,
					title : 'ĐVT',
					width: '5%',
					display : function(data) {
						return data.record.DVT;
					},
					sorting : false
				},
				
				DVT : {
					key : true,
					edit : false,
					title : 'ĐVT',
					width: '5%',
					display : function(data) {
						return data.record.DVT;
					},
					sorting : false
				},
				SoLuong : {
					key : true,
					edit : false,
					title : 'Số lượng',
					display : function(data) {
						return data.record.SoLuong;
					},
					sorting : false
				},
				DonGia : {
					key : true,
					edit : false,
					title : 'Đơn giá',
					width: '10%',
					display : function(data) {
						return data.record.DonGia;
					},
					sorting : false
				},
				DoanhThu : {
					key : true,
					edit : false,
					title : 'Doanh thu',
					width: '10%',
					display : function(data) {
						return data.record.DoanhThu;
					},
					sorting : false
				},
				TienThue : {
					key : true,
					edit : false,
					width: '10%',
					title : 'Tiền thuế',
					display : function(data) {
						return data.record.TienThue;
					},
					sorting : false
				},
				ThanhTien : {
					key : true,
					edit : false,
					width: '10%',
					title : 'Thành tiền',
					display : function(data) {
						return data.record.ThanhTien;
					},
					sorting : false
				},
				NgayXHDon : {
					key : false,
					edit : false,
					width: '10%',
					title : 'Ngày XHD',
					display : function(data) {
						return data.record.NgayXHDon;
					},
					sorting : true
				} 
				/* NgayXHDon : {
					key : true,
					edit : false,
					title : 'Ngày xuất hóa đơn',
					display : function(data) {
						//	return data.record.NgayXHDon;
						return moment(data.record.NgayXHDon)
								.format('YYYY-MM-DD');
					},
					sorting : true
				} */

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
		$('.jtable-busy-message').css('display', 'none');
		//$('#HDDTTable > div > div.jtable-busy-message').css('visibility', 'hidden');
	}
	
</script>