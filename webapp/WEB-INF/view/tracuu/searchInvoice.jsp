<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<style type="text/css" media="print">
@page {
	size: auto A4;
	margin: 3mm;
}

@media print {
	#Header,#Footer {
		display: none !important;
	}
	.header,.hide {
		visibility: hidden
	}
}

.text-print {
	color: #0a51a1;
	font-family: 'Times New Roman', Times, serif;
	font-size: 10pt;
}
</style>


<script type="text/javascript" charset="utf-8">
	function beginLoadForm() {
		document.forms["searchInvoiceForm"].maNhanHD.focus();
	}
	function taixml(_id) {
		document.forms["searchInvoiceForm"].id.value = _id;
		document.getElementById("searchInvoiceForm").target = "";
		document.forms["searchInvoiceForm"].action = '${SEARCHINVOICE_CONTEXT_PATH}/download_file_xml.bv';
		document.forms["searchInvoiceForm"].submit();
	}

	function printpdf() {
		var Browser = navigator.userAgent;
		if (Browser.indexOf('Firefox') >= 0) {
			window.frames["idHDCD"].print();
		} else if (Browser.indexOf('Chrome') >= 0) {
			window.frames["idHDCD"].print();
		} else{
			var target = document.getElementById('idHDCD');
			try {
				target.contentWindow.document.execCommand('print', false, null);
			} catch (e) {
				target.contentWindow.print();
			}
		}
	}

	function printChange2pdf() {
		var title = '';
		<c:if test="${not empty hdcd && hdcd == '1'}">
		title = '(HÓA ĐƠN CHUYỂN ĐỔI TỪ HÓA ĐƠN XÁC THỰC)';
		</c:if>
		<c:if test="${not empty hdcd && hdcd == '0'}">
		title = '(HÓA ĐƠN CHUYỂN ĐỔI TỪ HÓA ĐƠN ĐIỆN TỬ)';
		</c:if>
		<c:if test="${not empty LoaiHDView && LoaiHDView == '1'}">
		title = '(HÓA ĐƠN CHUYỂN ĐỔI TỪ HÓA ĐƠN XÁC THỰC)';
		</c:if>
		<c:if test="${not empty LoaiHDView && LoaiHDView == '0'}">
		title = '(HÓA ĐƠN CHUYỂN ĐỔI TỪ HÓA ĐƠN ĐIỆN TỬ)';
		</c:if>
		var nguoiCD = '';
		var ngayCD = '';
		var ShowLang = '';

		nguoiCD = document.getElementById('idHDCD').contentWindow.document
				.getElementById("nguoiCD");
		ngayCD = document.getElementById('idHDCD').contentWindow.document
				.getElementById("ngayCD");
		ShowLang = document.getElementById('idHDCD').contentWindow.document
				.getElementById("inHD");
		ShowLang.innerHTML = title;
		nguoiCD.innerHTML = 'Người chuyển đổi:';
		ngayCD.innerHTML = 'Ngày chuyển đổi:';

		ShowLang.style.display = 'block';
		nguoiCD.style.display = 'block';
		ngayCD.style.display = 'block';
		var Browser = navigator.userAgent;
		if (Browser.indexOf('Firefox') >= 0) {
			window.frames["idHDCD"].print();
		} else if (Browser.indexOf('Chrome') >= 0) {
			window.frames["idHDCD"].print();
		} else{
			var target = document.getElementById('idHDCD');
			try {
				target.contentWindow.document.execCommand('print', false, null);
			} catch (e) {
				target.contentWindow.print();
			}
		}
		ShowLang.style.display = 'none';
		nguoiCD.style.display = 'none';
		ngayCD.style.display = 'none';
		nguoiCD = '';
		ngayCD = '';
		ShowLang = '';
	}

	function viewxml(_id) {
		document.forms["searchInvoiceForm"].id.value = _id;
		document.getElementById("searchInvoiceForm").target = "_blank";
		document.forms["searchInvoiceForm"].action = '${SEARCHINVOICE_CONTEXT_PATH}/view_certified_invioce.bv';
		document.forms["searchInvoiceForm"].submit();
	}

	function searchHoaDon() {
		document.getElementById("searchInvoiceForm").target = "";
		if (document.forms["searchInvoiceForm"].maNhanHD.value == ""
				&& document.forms["searchInvoiceForm"].soHoaDon.value == ""
				&& document.forms["searchInvoiceForm"].ngayXuat.value == ""
				&& document.forms["searchInvoiceForm"].mauHDon.value == ""
				&& document.forms["searchInvoiceForm"].kyHieuHDon.value == ""
				&& document.forms["searchInvoiceForm"].mstNguoiBan.value == "") {
			alert("Vui lòng nhập thông tin tìm kiếm!");
			document.forms["searchInvoiceForm"].maNhanHD.focus();
			return;
		}

		if (document.forms["searchInvoiceForm"].maNhanHD.value == ""
				&& (document.forms["searchInvoiceForm"].soHoaDon.value == ""
						|| document.forms["searchInvoiceForm"].ngayXuat.value == ""
						|| document.forms["searchInvoiceForm"].mauHDon.value == ""
						|| document.forms["searchInvoiceForm"].kyHieuHDon.value == "" || document.forms["searchInvoiceForm"].mstNguoiBan.value == "")) {
			alert("Vui lòng nhập đầy đủ mẫu số, ký hiệu, số hóa đơn, ngày xuất, mã số thuế!");
			if (document.forms["searchInvoiceForm"].mauHDon.value == "") {
				document.forms["searchInvoiceForm"].mauHDon.focus();
			} else if (document.forms["searchInvoiceForm"].kyHieuHDon.value == "") {
				document.forms["searchInvoiceForm"].kyHieuHDon.focus();
			} else if (document.forms["searchInvoiceForm"].soHoaDon.value == "") {
				document.forms["searchInvoiceForm"].soHoaDon.focus();
			} else if (document.forms["searchInvoiceForm"].ngayXuat.value == "") {
				document.forms["searchInvoiceForm"].ngayXuat.focus();
			} else if (document.forms["searchInvoiceForm"].mstNguoiBan.value == "") {
				document.forms["searchInvoiceForm"].mstNguoiBan.focus();
			}
			return;
		}
		if (checkValidNgay(document.forms["searchInvoiceForm"].ngayXuat.value) == false) {
			alert("Định dạng ngày không hợp lệ. Định dang phải [dd/mm/yyyy]!");
			document.forms["searchInvoiceForm"].ngayXuat.select();
			document.forms["searchInvoiceForm"].ngayXuat.focus();
			return;
		}

		showLoader();
		document.forms["searchInvoiceForm"].action = "${SEARCHINVOICE_CONTEXT_PATH}/searchInvoice.bv";
		document.forms["searchInvoiceForm"].submit();
	}

	function CheckEnter(id) {
		var charfield = document.getElementById(id)
		charfield.onkeyup = function(e) {
			var e = window.event || e
			if (e.keyCode == 13) {
				searchHoaDon();
			} else {
				//document.forms["searchInvoiceForm"].so.focus();
			}
		}
	}

	function viewBrowseFileXMLInvoice(keyBrowse) {
		var fileHS = document.forms["searchInvoiceForm"].fileHS.value;
		if (fileHS == "") {
			alert('Vui lòng chọn file hóa đơn định dạng (.XML)!');
			document.forms["searchInvoiceForm"].fileHS.focus();
			return;
		}
		if (validate_fileXML(fileHS)) {
			document.forms["searchInvoiceForm"].keyViewBrowseFile.value = keyBrowse;
			document.forms["searchInvoiceForm"].action = '${SEARCHINVOICE_CONTEXT_PATH}/searchInvoice.bv';
			document.forms["searchInvoiceForm"].submit();
		} else {
			if (!validate_fileXML(fileHS)) {
				alert('File không đúng định dạng (.XML)!');
				document.forms["searchInvoiceForm"].fileHS.focus();
				return;
			}
		}
	}
	function validate_fileXML(fileName) {
		var allowed_extensions = new Array("xml", "XML");
		var file_extension = fileName.split('.').pop();
		for ( var i = 0; i <= allowed_extensions.length; i++) {
			if (allowed_extensions[i] == file_extension) {
				return true; // valid file extension
			}
		}
		return false;
	}

	function resizeIframe(obj) {
		obj.style.height = obj.contentWindow.document.body.scrollHeight + 10
				+ 'px';
	}
</script>
<body onload="beginLoadForm()">
	<div style="display: none;" id="loaderContainer">
		<div id="loader" style="z-index: 10;" align="center">
			<p style="vertical-align: middle;">
				<img class="valgin-mid" alt="loading.gif" title=""
					src="<c:url value="/images/ajax-loader.gif"/>" width="24" /> <b
					style="color: blue; vertical-align: top;">Đang truy xuất dữ
					liệu. Vui lòng đợi...</b>
			</p>
		</div>
	</div>
	<h2 class="search">Tra cứu thông tin hóa đơn</h2>
	<form:form action="" commandName="searchInvoiceForm"
		name="searchInvoiceForm" id="searchInvoiceForm"
		enctype="multipart/form-data">
		<table width="100%" cellspacing="0" cellpadding="3" border="0"
			class="txt_1">
			<tbody>
				<tr>
					<td width="30%" valign="middle" align="right" class="input_form">Mã
						nhận HĐ:</td>
					<td valign="middle"><form:input path="maNhanHD"
							onkeypress="CheckEnter('maNhanHD');" cssClass="input"
							cssStyle="width: 400px;" tabindex="1" />
					</td>
				</tr>
				<tr>
					<td valign="middle" align="right" class="input_form">Hoặc</td>
					<td></td>
				</tr>
				<tr>
					<td valign="middle" align="right" class="input_form">Mẫu số:</td>
					<td valign="middle"><form:input path="mauHDon"
							cssClass="input" cssStyle="width: 400px;"
							onkeypress="CheckEnter('mauHDon');" tabindex="3" />
					</td>
				</tr>
				<tr>
					<td valign="middle" align="right" class="input_form">Ký hiệu:</td>
					<td valign="middle"><form:input path="kyHieuHDon"
							cssClass="input" cssStyle="width: 400px;"
							onkeypress="CheckEnter('kyHieuHDon');" tabindex="3" />
					</td>
				</tr>
				<tr>
					<td valign="middle" align="right" class="input_form">Số hóa
						đơn:</td>
					<td valign="middle"><form:input path="soHoaDon"
							cssClass="input" cssStyle="width: 400px;"
							onkeypress="CheckEnter('soHoaDon');" tabindex="4" />
					</td>
				</tr>
				<tr>
					<td valign="middle" align="right" class="input_form">Ngày
						xuất:</td>
					<td valign="middle"><form:input path="ngayXuat"
							cssClass="input" onkeypress="CheckEnter('ngayXuat');"
							cssStyle="width: 300px;" tabindex="5" /> [dd/mm/yyyy]</td>
				</tr>
				<tr>
					<td valign="middle" align="right" class="input_form">MST người
						bán:</td>
					<td valign="middle"><form:input path="mstNguoiBan"
							cssClass="input" cssStyle="width: 400px;"
							onkeypress="CheckEnter('mstNguoiBan');" tabindex="6" />
					</td>
				</tr>
				<tr>
					<td class="input_form">&nbsp;</td>
					<td valign="middle" align="left">
						<div class="bnt f_left">
							<a href="#" onclick="searchHoaDon();">Tra cứu</a>&nbsp;
							<form:hidden path="accessKey" />
						</div> <c:choose>
							<c:when test="${not empty searchResult  && searchResult!= ''}">
								<c:set value="${searchResult}" var="i" />
								<div class="bnt f_left">
									<a href="#" onclick="printpdf();">In</a>&nbsp;
								</div>
								<div class="bnt f_left">
									<a href="#" onclick="printChange2pdf();">Chuyển đổi</a>&nbsp;
								</div>
								<div class="bnt f_left">
									<input type="hidden" value="${i.id }" name="id" id="id" /> <a
										href="#" onclick="taixml('${i.id }');">Tải hóa đơn</a>
								</div>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</tbody>
		</table>
		<p>&nbsp;</p>
		<h2 class="search">Xem hóa đơn gốc</h2>
		<table width="100%" cellspacing="0" cellpadding="5" border="0"
			class="txt_1">
			<tbody>
				<tr>
					<td width="30%" valign="middle" align="right" class="input_form">Chọn
						hóa đơn gốc (xml):</td>
					<td valign="middle"><input type="file" name="fileHS"
						id="fileHS" size="25" class="nopHS" />
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td valign="middle" align="left">
						<div class="bnt f_left">
							<a onclick="viewBrowseFileXMLInvoice('showViewBrowseFile');"
								href="#">Xem hóa đơn</a>
							<form:hidden path="fileNameBrowse" />
							<input type="hidden" name="keyViewBrowseFile"
								id="keyViewBrowseFile" />
							<c:if
								test="${not empty showBtnPrintFile && showBtnPrintFile eq 'show'}">
								<a href="#" onclick="printpdf();">In</a>
							</c:if>

						</div></td>
				</tr>
			</tbody>
		</table>
	</form:form>
	<div align="center" class='error' style='color: red;'>
		<c:if test="${not empty errorMessage  && errorMessage!= ''}">
			<c:out value="${errorMessage}"></c:out>
		</c:if>
		<c:if test="${statusViewBrowseFileHD eq '-99'}">Cấu trúc file không đúng định dạng hóa đơn!"</c:if>
		<c:if test="${statusViewBrowseFileHD eq '-2'}">Lỗi trong quá trình hiển thị hóa đơn!</c:if>
	</div>
	<c:choose>
		<c:when test="${not empty searchResult  && searchResult!= ''}">
			<c:set value="${searchResult}" var="i" />
			<div id="idPrintHD">
				<iframe
					src="${SEARCHINVOICE_CONTEXT_PATH}/view_certified_invioce.bv?key=${searchInvoiceForm.accessKey }&id=${i.id }"
					width="100%" frameborder="0" scrolling="no"
					onload="resizeIframe(this)" id="idHDCD" name="idHDCD"></iframe>
			</div>
			<input type="hidden" value="${i.id }" name="id" id="id" />
		</c:when>
		<c:when
			test="${not empty searchInvoiceForm.fileNameBrowse && searchInvoiceForm.fileNameBrowse !=''}">
			<iframe
				src="${SEARCHINVOICE_CONTEXT_PATH}/show_browse_file_invoice.bv?key=${searchInvoiceForm.accessKey }&fileNameBrowse=${searchInvoiceForm.fileNameBrowse}"
				width="100%" scrolling="no" onload="resizeIframe(this)"
				frameborder="0" id="idHDCD" name="idHDCD"></iframe>
		</c:when>
	</c:choose>

</body>
