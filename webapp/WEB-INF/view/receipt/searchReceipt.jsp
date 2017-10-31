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
	#Header, #Footer {
		display: none !important;
	}
	.header, .hide {
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
		document.forms["searchReceiptForm"].maTraCuu.focus();
	}
	function taixml(_id) {
		document.forms["searchReceiptForm"].id.value = _id;
		document.getElementById("searchReceiptForm").target = "";
		document.forms["searchReceiptForm"].action = '${SEARCH_RECEIPT_CONTEXT_PATH}/download_file_xml.bv';
		document.forms["searchReceiptForm"].submit();
	}

	function printpdf() {
		window.frames["idHDCD"].print();
	}

	function viewxml(_id) {
		document.forms["searchReceiptForm"].id.value = _id;
		document.getElementById("searchReceiptForm").target = "_blank";
		document.forms["searchReceiptForm"].action = '${SEARCH_RECEIPT_CONTEXT_PATH}/view_certified_receipt.bv';
		document.forms["searchReceiptForm"].submit();
	}

	function searchReceipt() {
		document.getElementById("searchReceiptForm").target = "";
		if (document.forms["searchReceiptForm"].maTraCuu.value == ""
				&& document.forms["searchReceiptForm"].soPhieuThu.value == ""
				&& document.forms["searchReceiptForm"].ngayThuTien.value == ""
				&& document.forms["searchReceiptForm"].mstNguoiBan.value == ""){
			alert("Vui lòng nhập thông tin tìm kiếm!");
			document.forms["searchReceiptForm"].maTraCuu.focus();
			return;
		}

		if (document.forms["searchReceiptForm"].maTraCuu.value == ""
				&& (document.forms["searchReceiptForm"].soPhieuThu.value == ""
						|| document.forms["searchReceiptForm"].ngayThuTien.value == ""
						|| document.forms["searchReceiptForm"].mstNguoiBan.value == "")) {
			alert("Vui lòng nhập đầy đủ số phiếu thu, ngày thu tiền, mã số thuế!");
			if (document.forms["searchReceiptForm"].soPhieuThu.value == "") {
				document.forms["searchReceiptForm"].soPhieuThu.focus();
			} else if (document.forms["searchReceiptForm"].ngayThuTien.value == "") {
				document.forms["searchReceiptForm"].ngayThuTien.focus();
			} else if (document.forms["searchReceiptForm"].mstNguoiBan.value == "") {
				document.forms["searchReceiptForm"].mstNguoiBan.focus();
			}
			return;
		}
		if (checkValidNgay(document.forms["searchReceiptForm"].ngayThuTien.value) == false) {
			alert("Định dạng ngày không hợp lệ. Định dang phải [dd/mm/yyyy]!");
			document.forms["searchReceiptForm"].ngayThuTien.select();
			document.forms["searchReceiptForm"].ngayThuTien.focus();
			return;
		}

		showLoader();
		document.forms["searchReceiptForm"].action = "${SEARCH_RECEIPT_CONTEXT_PATH}/searchReceipt.bv";
		document.forms["searchReceiptForm"].submit();
	}

	function CheckEnter(id) {
		var charfield = document.getElementById(id)
		charfield.onkeyup = function(e) {
			var e = window.event || e
			if (e.keyCode == 13) {
				searchReceipt();
			} else {
			}
		}
	}

	function viewBrowseFileXMLReceipt(keyBrowse, fileHS) {
		if (document.forms["searchReceiptForm"].fileHS.value == "") {
			alert('Vui lòng chọn file hóa đơn định dạng (.XML)!');
			document.forms["searchReceiptForm"].fileHS.focus();
			return;
		}
		if (validate_fileXML(fileHS)) {
			document.forms["searchReceiptForm"].keyViewBrowseFile.value = keyBrowse;
			document.forms["searchReceiptForm"].action = '${SEARCH_RECEIPT_CONTEXT_PATH}/searchReceipt.bv';
			document.forms["searchReceiptForm"].submit();
		} else {
			if (!validate_fileXML(fileHS)) {
				alert('File không đúng định dạng (.XML)!');
				document.forms["searchReceiptForm"].fileHS.focus();
				return;
			}
		}
	}
	function validate_fileXML(fileName) {
		var allowed_extensions = new Array("xml", "XML");
		var file_extension = fileName.split('.').pop();
		for (var i = 0; i <= allowed_extensions.length; i++) {
			if (allowed_extensions[i] == file_extension) {
				return true; // valid file extension
			}
		}
		return false;
	}

	function resizeIframe(obj) {
		obj.style.height = obj.contentWindow.document.body.scrollHeight + 10 + 'px';
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
	<h2 class="search">Tra cứu thông tin phiếu thu</h2>
	<form:form action="" commandName="searchReceiptForm"
		name="searchReceiptForm" id="searchReceiptForm"
		enctype="multipart/form-data">
		<table width="100%" cellspacing="0" cellpadding="3" border="0"
			class="txt_1">
			<tbody>
				<tr>
					<td width="30%" valign="middle" align="right" class="input_form">Mã
						 tra cứu:</td>
					<td valign="middle"><form:input path="maTraCuu"
							onkeypress="CheckEnter('maTraCuu');" cssClass="input"
							cssStyle="width: 400px;" tabindex="1" /></td>
				</tr>
				<tr>
					<td valign="middle" align="right" class="input_form">Số phiếu thu:</td>
					<td valign="middle"><form:input path="soPhieuThu"
							cssClass="input" cssStyle="width: 400px;"
							onkeypress="CheckEnter('soPhieuThu');" tabindex="4" /></td>
				</tr>
				<tr>
					<td valign="middle" align="right" class="input_form">MST người bán:</td>
					<td valign="middle"><form:input path="mstNguoiBan" cssClass="input" cssStyle="width: 400px;"
							onkeypress="CheckEnter('mstNguoiBan');" tabindex="6" /></td>
				</tr>
				<tr>
					<td valign="middle" align="right" class="input_form">Ngày
						thu tiền:</td>
					<td valign="middle"><form:input path="ngayThuTien"
							cssClass="input" onkeypress="CheckEnter('ngayThuTien');"
							cssStyle="width: 300px;" tabindex="5" /> [dd/mm/yyyy]</td>
				</tr>
				<tr>
					<td class="input_form">&nbsp;</td>
					<td valign="middle" align="left">
						<div class="bnt f_left">
							<a href="#" onclick="searchReceipt();">Tra cứu</a>&nbsp;
							<form:hidden path="accessKey" />
						</div> <c:choose>
							<c:when test="${not empty searchResult  && searchResult!= ''}">
								<c:set value="${searchResult}" var="i" />
								<div class="bnt f_left">
									<a href="#" onclick="printpdf();">In</a>&nbsp;
								</div>
								<div class="bnt f_left">
									<input type="hidden" value="${i.id }" name="id" id="id" /> <a
										href="#" onclick="taixml('${i.id }');">Tải phiếu thu</a>
								</div>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tbody>
		</table>
		<p>&nbsp;</p>
		<h2 class="search">Xem phiếu thu gốc</h2>
		<table width="100%" cellspacing="0" cellpadding="5" border="0"
			class="txt_1">
			<tbody>
				<tr>
					<td width="30%" valign="middle" align="right" class="input_form">Chọn
						phiếu thu gốc (xml):</td>
					<td valign="middle"><input type="file" name="fileHS"
						id="fileHS" size="25" class="nopHS" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td valign="middle" align="left">
						<div class="bnt f_left">
							<a
								onclick="viewBrowseFileXMLReceipt('showViewBrowseFile', fileHS.value);"
								href="#">Xem phiếu thu</a>
							<form:hidden path="fileNameBrowse" />
							<input type="hidden" name="keyViewBrowseFile"
								id="keyViewBrowseFile" />
							<c:if
								test="${not empty showBtnPrintFile && showBtnPrintFile eq 'show'}">
								<a href="#" onclick="printpdf();">In</a>
							</c:if>

						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</form:form>
	<div align="center" class='error' style='color: red;'>
		<c:if test="${not empty errorMessage  && errorMessage!= ''}"><c:out value="${errorMessage}"></c:out></c:if>
		<c:if test="${statusViewBrowseFileHD eq '-99'}">Cấu trúc file không đúng định dạng phiếu thu!"</c:if>
		<c:if test="${statusViewBrowseFileHD eq '-2'}">Lỗi trong quá trình hiển thị phiếu thu!</c:if>
	</div>
	<c:choose>
		<c:when test="${not empty searchResult  && searchResult!= ''}">
			<c:set value="${searchResult}" var="i" />
			<div id="idPrintHD">
				<iframe
					src="${SEARCH_RECEIPT_CONTEXT_PATH}/view_certified_receipt.bv?key=${searchReceiptForm.accessKey }&id=${i.id }"
					width="100%" frameborder="0" scrolling="no"
					onload="resizeIframe(this)" id="idHDCD" name="idHDCD"></iframe>
			</div>
			<input type="hidden" value="${i.id }" name="id" id="id" />
		</c:when>
		<c:when
			test="${not empty searchReceiptForm.fileNameBrowse && searchReceiptForm.fileNameBrowse !=''}">
			<iframe
				src="${SEARCH_RECEIPT_CONTEXT_PATH}/show_browse_file_receipt.bv?key=${searchReceiptForm.accessKey }&fileNameBrowse=${searchReceiptForm.fileNameBrowse}"
				width="100%" scrolling="no" onload="resizeIframe(this)"
				frameborder="0" id="idHDCD" name="idHDCD"></iframe>
		</c:when>
	</c:choose>

</body>
