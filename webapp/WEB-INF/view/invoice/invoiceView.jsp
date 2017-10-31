<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-1_0-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="ts24-window-content">
	<div class="contenttable">
		<h2 class="search">Tra cứu hóa đơn điện tử/Hóa đơn xác thực</h2>
		<form id="searchHDDTForm" name="searchHDDTForm">
			<table width="100%" cellspacing="0" cellpadding="3" border="0"
				class="txt_1">
				<tbody>
					<tr>
						<td width="25%" valign="middle" align="right" class="input_form">Từ
							ngày:</td>
						<td valign="middle" width="20%"><input class="textinput" type="text"
							name="tuNgay" id="tuNgay" style="width: 150px;" /></td>
						<td width="15%" valign="middle" align="right" class="input_form">Đến
							ngày:</td>
						<td valign="middle"><input class="textinput" type="text"
							name="denNgay" id="denNgay" style="width: 150px;" />[dd/mm/yyyy]</td>
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

						<td valign="middle" align="right" class="input_form">MST
							người mua:</td>
						<td valign="middle"><input class="textinput" type="text"
							name="mstNguoiMua" id="mstNguoiMua" style="width: 150px;" /></td>
					</tr>

					<tr>
						<td valign="middle" align="right" class="input_form">Loại hóa
							đơn:</td>
						<td valign="middle"><select name=loaiHDon id="loaiHDon">
								<option value="" selected="selected">------ Tất cả ------</option>
								<option value="1" >Hóa đơn điện tử</option>
								<option value="2">Hóa đơn xác thực</option>
						</select></td>
						<c:choose>
							<c:when test="${not empty webSession.showFieldMaNhanHD && webSession.showFieldMaNhanHD eq 'show'}">
								<td valign="middle" align="right" class="input_form">Mã nhận hóa đơn:</td>
								<td valign="middle"><input class="textinput" type="text"
									name="maNhanHDon" id="maNhanHDon" style="width: 150px;" /></td>
							</c:when>
							<c:otherwise>
								<td></td>
								<td></td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td class="input_form">&nbsp;</td>
						<td valign="middle" align="left">
							<div class="bnt f_left">
								<a href="#" onclick="btnSearchADD();">Tra cứu</a>&nbsp;
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<p>&nbsp;</p>
		<spring:form id="searchHDDTFormBrowse" name="searchHDDTFormBrowse"
			enctype="multipart/form-data">
			<h2 class="search">Tra cứu thông tin hóa đơn theo excel</h2>
			<table width="100%" cellspacing="0" cellpadding="5" border="0"
				class="txt_1">
				<tbody>
					<tr>
						<td width="40%" valign="middle" align="right" class="input_form">Chọn
							file excel (xls):</td>
						<td valign="middle"><input type="file" name="fileHS"
							id="fileHS" size="25" required /> <a href="#"
							onclick="MM_openBrWindow('../upload/filemau/MAU_DSACH_HOADON.xls','','');"><strong>Tải
									biểu mẫu </strong> </a></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td valign="middle" align="left">
							<div class="bnt f_left">
								<a onclick="searchInvoiceFromExcel(fileHS.value);" href="#">Tra cứu</a>
							</div>
						</td>
					</tr>
				</tbody>
			</table>

		</spring:form>
		<div id="output"></div>
	</div>
</div>
<div align="center" class="error">${errorMessage}</div>
<div id="tblExcel">
	<c:if test="${not empty listRecords && listRecords !='' }">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tbl_style_blue txt_small" >
			<tr>
				<th width="5%">STT</th>
				<th width="15%" align="left">Mst người bán</th>
				<th width="15%" align="left">Mst người mua</th>
				<th width="15" align="left">Mẫu HĐ/ STT</th>
				<th width="15%" align="left">Ký hiệu HĐ</th>
				<th width="15%" align="left">Số hóa đơn</th>
				<th width="15%" align="left">Ngày xuất hóa đơn</th>
			</tr>
			<c:set var="bid" value="1" />
			<c:set var="cong" value="0" />
			<c:forEach items="${listRecords}" var="i">
				<c:set var="class_stype" value="tbl_style_blue_odd" />
				<c:if test="${bid%2 == 0}">
					<c:set var="class_stype" value="tbl_style_blue_even" />
				</c:if>
				<tr class="<c:out value="${class_stype}"></c:out>">
					<td align="left"><c:out value="${bid+begin}"></c:out></td>
					<td align="left"><c:out value="${i.maSoThue}"></c:out></td>
					<td align="left"><c:out value="${i.mstNguoiMua }"></c:out></td>
					<td align="left"><c:out value="${i.mauHDon }"></c:out>/<c:out value="${i.soThuTu }"></c:out></td>
					<td align="left"><c:out value="${i.kyHieuHDon }"></c:out></td>
					<td align="left"><a title="Xem hóa đơn" href="#" class="css_masothue" onclick="viewInvoiceDetail('${i.id}', ${i.loaiHDon});"><c:out value="${i.soHoaDon}"></c:out></a>
					</td>
					<td align="left">	
						${fn:substring(i.ngayXHDon, 0, 10)}
					</td>
				</tr>
				<c:if test="${bid == totalRecords}">
				</c:if>
				<c:set var="bid" value="${bid+1}" />
			</c:forEach>
		</table>
	</c:if>
</div>
<div id="HDDTTable" class="jtable-main-container"></div>

<script type="text/javascript">
	function viewInvoiceDetail(id, loaiHDon){
		window.open("${HOADON_DIENTU_CONTEXT_PATH}/hoadon_chitiet.bv?id="+ id+ "&loaiHD="+ loaiHDon,'_blank',
			'height=650,width=900,left=250,top=10,resizable=no,scrollbars=yes,toolbar=no,menubar=no,location=no,directories=no,status=no,titlebar=no');
	}
	function submitForm(fileHS) {
		if (!validate_fileXML(fileHS)) {
			alert('File không đúng định dạng (.xls)!');
			document.forms["searchHDDTFormBrowse"].fileHS.focus();
			return;
		}
		console.log("submit event");
		var fd = new FormData(document.getElementById("searchHDDTFormBrowse"));
		fd.append("label", "WEBUPLOAD");
		fd.append("jtStartIndex", 0);
		fd.append("jtPageSize", 0);
		fd.append("jtSorting", "NgayXHDon DESC");
		$.ajax({
			url : "${HOADON_DIENTU_CONTEXT_PATH}/searchInvoiceFromExcel.bv",
// 			url : '${HOADON_DIENTU_CONTEXT_PATH}hoadon_search.bv?jtStartIndex='	+ 0 + '&jtPageSize=' + 0 + '&jtSorting=' + "NgayXHDon DESC",
			type : "POST",
			data : fd,
			processData : false, // tell jQuery not to process the data
			contentType : false, // tell jQuery not to set contentType
		}).done(function(data) {
			console.log("Output:");
			console.log(data);
		});
		return false;
	}
	function searchInvoiceFromExcel(fileHS) {
		$("#loading").show();
		$("#loading-b").show();
		if (document.forms["searchHDDTFormBrowse"].fileHS.value == "") {			
			alert('Vui lòng chọn file hóa đơn định dạng (.xls)!');
			document.forms["searchHDDTFormBrowse"].fileHS.focus();
			$("#loading").hide();
			$("#loading-b").hide();
			return;
		}
		if (validate_fileXML(fileHS)) {
			document.forms["searchHDDTFormBrowse"].action = '${HOADON_DIENTU_CONTEXT_PATH}/searchInvoiceFromExcel.bv?jtStartIndex='+ 0 + '&jtPageSize=' + 0 + '&jtSorting="NgayXHDon DESC"';
			document.forms["searchHDDTFormBrowse"].submit();
			$("#loading").hide();
			$("#loading-b").hide();
		} else {
			if (!validate_fileXML(fileHS)) {
				alert('File không đúng định dạng (.xls, .xlsx)!');
				document.forms["searchHDDTFormBrowse"].fileHS.focus();
				$("#loading").hide();
				$("#loading-b").hide();
				return;
			}
		}
	}
	function validate_fileXML(fileName) {
		var allowed_extensions = new Array("xls", "XLS", "xlsx", "XLSX");
		var file_extension = fileName.split('.').pop();
		for (var i = 0; i <= allowed_extensions.length; i++) {
			if (allowed_extensions[i] == file_extension) {
				return true; // valid file extension
			}
		}
		return false;
	}
	function btnSearchADD() {
		$("#loading").show();
		$("#loading-b").show();
		var idTable = "#HDDTTable";		
		setupAdditionalPPTable(idTable);
		$(idTable).jtable('reload');
		$("#loading").hide();
		$("#loading-b").hide();
	}
	function setupAdditionalPPTable(idTable) {
		$(idTable).jtable({
			title : '',
			paging : true,
			pageSize : 50,
			sorting : true,
			multiSorting : true,
			defaultSorting : 'NgayXHDon DESC',
			actions : {
				listAction : function(postData, jtParams) {
					//alert("lam");
					if (checkValidNgay(document.searchHDDTForm.tuNgay.value) == false) {
						alert("Định dạng từ ngày không hợp lệ!");
						document.searchBaoCaoBanHangForm.tuNgay.select();
						document.searchBaoCaoBanHangForm.tuNgay.focus();
						hideLoadding();
						return;
					}

					if (checkValidNgay(document.searchHDDTForm.denNgay.value) == false) {
						alert("Định dạng đến ngày không hợp lệ!");
						document.searchBaoCaoBanHangForm.denNgay.select();
						document.searchBaoCaoBanHangForm.denNgay.focus();
						hideLoadding();
						return;
					}
					console.log("Loading from custom function...");
					return $.Deferred(function($dfd) {
						$.ajax({
							url : '${HOADON_DIENTU_CONTEXT_PATH}/hoadon_search.bv?jtStartIndex='
									+ jtParams.jtStartIndex + '&jtPageSize=' + jtParams.jtPageSize
									+ '&jtSorting='+ jtParams.jtSorting,
							type : 'POST',
							dataType : 'json',
							data : $('#searchHDDTForm').serialize(),
							success : function(data) {
								//alert("lam1");
								//var ajaxDisplay = document.getElementById('tblExcel');
						        //ajaxDisplay.innerHTML = '';
								//$("#tblExcel").hide();
								$("#tblExcel").html('');
								$dfd.resolve(data);
								//alert(data);
							},
							error : function() {
								//alert('Error Ajax');
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
					title : 'Mã số thuế',
					display : function(data) {
						return data.record.MaSoThue;
					},
					sorting : true
				},
				MSTNguoiMua : {
					key : true,
					edit : false,
					title : 'Mst người mua',
					display : function(data) {
						return data.record.MSTNguoiMua;
					},
					sorting : true
				},
				MauHDon : {
					key : true,
					edit : false,
					title : 'Mẫu HĐ/ STT',
					display : function(data) {
						return data.record.MauHDon + "/"
								+ data.record.SoThuTu;
					},
					sorting : false
				},
				KyHieuHDon : {
					key : true,
					edit : false,
					title : 'Ký hiệu HĐ',
					display : function(data) {
						return data.record.KyHieuHDon;
					},
					sorting : true
				},
				SoHoaDon : {
					key : true,
					edit : false,
					title : 'Số hóa đơn',
					display : function(data) {
						var $linkSHD = $('<a href="#" title="Xem hóa đơn">'
								+ data.record.SoHoaDon + '</a>');
						$linkSHD
								.click(function() {
									window
											.open(
													"${HOADON_DIENTU_CONTEXT_PATH}/hoadon_chitiet.bv?id="
															+ data.record.ID
															+ "&loaiHD="
															+ data.record.loaiHDon,
													'_blank',
													'height=650,width=900,left=250,top=10,resizable=no,scrollbars=yes,toolbar=no,menubar=no,location=no,directories=no,status=no,titlebar=no');
								});
						return $linkSHD;
					},
					sorting : true
				},

				NgayXHDon : {
					key : true,
					edit : false,
					title : 'Ngày xuất hóa đơn',
					display : function(data) {
						//	return data.record.NgayXHDon;
						return moment(data.record.NgayXHDon)
								.format('YYYY-MM-DD');
					},
					sorting : true
				},
				MaNhanHDon : {
					key : true,
					edit : false,
					title : 'Mã nhận Hóa Đơn',
					display : function(data) {
							return data.record.MaNhanHDon;
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
</script>