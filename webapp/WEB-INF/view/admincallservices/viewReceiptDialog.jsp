<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<link rel="stylesheet" type="text/css" href="<c:url value="/css/webstyles.css"/>" />

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
	function taixml(_id) {
		document.forms["searchInvoiceForm"].id.value = _id;
		document.getElementById("searchInvoiceForm").target = "";
		document.forms["searchInvoiceForm"].action = '${SEARCH_RECEIPT_CONTEXT_PATH}/download_file_xml.bv';
		document.forms["searchInvoiceForm"].submit();
	}

	function printpdf() {
		window.frames["idHDCD"].print();
		/*var title = '';
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
		window.frames["idHDCD"].print();
		ShowLang.style.display = 'none';
		nguoiCD.style.display = 'none';
		ngayCD.style.display = 'none';
		nguoiCD = '';
		ngayCD = '';
		ShowLang = '';*/
	}

	function resizeIframe(obj) {
		obj.style.height = obj.contentWindow.document.body.scrollHeight + 10 + 'px';
	}
</script>
<body style="background:#fff;">
	<form:form action="" commandName="searchInvoiceForm" name="searchInvoiceForm" id="searchInvoiceForm" enctype="multipart/form-data">
		<div align="center"> 
			<table width="700px" cellspacing="0" cellpadding="3" border="0" class="txt_1">
			<tbody>
				<tr>
					<td align="right">
						<c:choose>
							<c:when test="${not empty idInvoiceDetail  && idInvoiceDetail!= ''}">
								<c:set value="${idInvoiceDetail}" var="iId" />
								<div class="bnt f_right">
									<input type="hidden" value="${iId }" name="id" id="id" /> <a
										href="#" onclick="taixml('${iId }');">Tải phiếu thu</a>
								</div>
								<div class="bnt f_right">
									<a href="#" onclick="printpdf();">In</a>&nbsp;
								</div>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	</form:form>
	<div align="center" class='error' style='color: red;'>
		<c:if test="${not empty errorMessage  && errorMessage!= ''}"><c:out value="${errorMessage}"></c:out></c:if>
		<c:if test="${statusViewBrowseFileHD eq '-99'}">Cấu trúc file không đúng định dạng phiếu thu!</c:if>
		<c:if test="${statusViewBrowseFileHD eq '-2'}">Lỗi trong quá trình hiển thị phiếu thu!</c:if>
	</div>
	<c:choose>
		<c:when test="${not empty idInvoiceDetail  && idInvoiceDetail!= ''}">
			<c:set value="${idInvoiceDetail}" var="iId" />
			<div id="idPrintHD">
				<iframe
					src="${ADMIN_CALL_SERVICES_CONTEXT_PATH}/detailPhieuThuTS24ID.bv?id=${iId }"
					width="100%" frameborder="0" scrolling="no" onload="resizeIframe(this)" id="idHDCD" name="idHDCD"></iframe>
			</div>
			<input type="hidden" value="${iId}" name="id" id="id" />
		</c:when>
	</c:choose>

</body>
