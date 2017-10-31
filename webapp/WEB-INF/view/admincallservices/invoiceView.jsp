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
						<td valign="middle" width="20%"><input class="textinput tcal" type="text"
							name="tuNgay" id="tuNgay" style="width: 150px;" /></td>
						<td width="15%" valign="middle" align="right" class="input_form">Đến
							ngày:</td>
						<td valign="middle"><input class="textinput tcal" type="text"
							name="denNgay" id="denNgay" style="width: 150px;" />[dd/mm/yyyy]</td>
					</tr>
					<tr>
						<td valign="middle" align="right" class="input_form">Mã nhận hóa đơn:</td>
						<td valign="middle"><input class="textinput" type="text"
							name="maNhanHDon" id="maNhanHDon" style="width: 150px;" /></td>
						<td></td>
						<td></td>
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
		window.open("${HOADON_DIENTU_CONTEXT_PATH}/hoadon_TS24ID_chitiet.bv?id="+ id+ "&loaiHD="+ loaiHDon,'_blank',
			'height=650,width=900,left=250,top=10,resizable=no,scrollbars=yes,toolbar=no,menubar=no,location=no,directories=no,status=no,titlebar=no');
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
							url : '${ADMIN_CALL_SERVICES_CONTEXT_PATH}/hoaDonTS24ID.bv?jtStartIndex='
									+ jtParams.jtStartIndex + '&jtPageSize=' + jtParams.jtPageSize
									+ '&jtSorting='+ jtParams.jtSorting,
							type : 'POST',
							dataType : 'json',
							data : $('#searchHDDTForm').serialize(),
							success : function(data) {
								$("#tblExcel").html('');
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
													"${ADMIN_CALL_SERVICES_CONTEXT_PATH}/viewDialog.bv?id="
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
						//return moment(data.record.NgayXHDon).format('YYYY-MM-DD');
						return moment(data.record.NgayXHDon).format('DD/MM/YYYY');
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

</script>