<?xml version="1.0" encoding="utf-8"?><!-- DWXMLSource="HDDT.xml" -->
<!DOCTYPE xsl:stylesheet  [
	<!ENTITY nbsp   "&#160;">
	<!ENTITY copy   "&#169;">
	<!ENTITY reg    "&#174;">
	<!ENTITY trade  "&#8482;">
	<!ENTITY mdash  "&#8212;">
	<!ENTITY ldquo  "&#8220;">
	<!ENTITY rdquo  "&#8221;"> 
	<!ENTITY pound  "&#163;">
	<!ENTITY yen    "&#165;">
	<!ENTITY euro   "&#8364;">
]>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:inv="http://laphoadon.gdt.gov.vn/2014/09/invoicexml/v1">
<xsl:output method="html" encoding="utf-8" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>

<xsl:decimal-format name="vndong" decimal-separator=',' grouping-separator='.' />
<xsl:variable name="lower">abcdefghijklmnopqrstuvwxyzàáảãạăằắẳẵặâầấẩẫậđèéẻẽẹêềếểễệìíỉĩịòóỏõọôồốổỗộơờớởỡợùúủũụưừứửữựỳýỷỹỵ</xsl:variable>
<xsl:variable name="upper">ABCDEFGHIJKLMNOPQRSTUVWXYZÀÁẢÃẠĂẰẮẲẴẶÂẦẤẨẪẬĐÈÉẺẼẸÊỀẾỂỄỆÌÍỈĨỊÒÓỎÕỌÔỒỐỔỖỘƠỜỚỞỠỢÙÚỦŨỤƯỪỨỬỮỰỲÝỶỸỴ</xsl:variable>

<!-- Trạng thái điều chỉnh hóa đơn -->
<xsl:template name="TrangThaiDCHD">
  <xsl:param name="val"/>
  <xsl:choose>
    <xsl:when test="$val = '1'">Hóa đơn gốc (hóa đơn thường)</xsl:when>
    <xsl:when test="$val = '2'">Hóa đơn bị thay thế (hay còn gọi là hóa đơn bị xóa bỏ)</xsl:when>
    <xsl:when test="$val = '3'">Hóa đơn thay thế</xsl:when>
    <xsl:when test="$val = '4'">Hóa đơn bị điều chỉnh</xsl:when>
    <xsl:when test="$val = '5'">Hóa đơn điều chỉnh</xsl:when>
    <xsl:when test="$val = '6'">Hóa đơn bị xóa bỏ</xsl:when>
    <xsl:when test="$val = '7'">Hóa đơn xóa bỏ</xsl:when>
    <xsl:when test="$val = '8'">Hóa đơn hủy</xsl:when>
    <xsl:when test="$val = '9'">Hóa đơn điều chỉnh chiết khấu</xsl:when>
	<xsl:otherwise><xsl:value-of select="$val"/></xsl:otherwise>
  </xsl:choose>
</xsl:template>

<xsl:template match="inv:invoice">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<style>
table { border: 0; width: 700px; font-size:10pt; font-family:"Times New Roman, Times, serif"; color:BLUE; border-collapse:collapse;}
.cell, .half-cell { border: 1px solid #000; display:inline-block; *display:inline; zoom:1; padding: 0px; }
.cellformat{ width:14px; height:14px; line-height:14px; text-align:center; }
.cellformatheight{ height:14px; line-height:14px; text-align:center; }
.cellformatdetail{ width:20px; height:20px; line-height:20px; text-align:center; }
.borderTD {border:1px solid #000; border-collapse:collapse; vertical-align:middle;}
</style>
<title>HOA_DON_DIEN_TU</title>
</head>

<body>
<div style="width:720px; border:1px solid #000; margin:0 auto; background-image: url('data:image/gif;base64,{inv:invoiceData/inv:userDefines/Background}'); background-repeat:no-repeat; background-position:center;">
<table align="center"><tr><td>

<xsl:choose>
  <xsl:when test="(inv:invoiceData/inv:invoiceNumber) and (inv:invoiceData/inv:invoiceNumber)='0000000'">
<!-- Hình hóa đơn mẫu --> 
<div style="position:absolute; z-index:100; margin:480px 0px 0px 250px;"><img src="data:image/png;base64,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"/></div>

<!-- Thông tin XuatHoaDon™ -->
<div style="position:absolute; z-index:100; height:1; white-space:nowrap; font-size:8pt; transform: rotate(270deg); -moz-transform: rotate(270deg); margin:500px 0px 0px 460px; -o-transform: rotate(270deg); -webkit-transform: rotate(270deg); filter:  progid:DXImageTransform.Microsoft.BasicImage(rotation=3); -ms-filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=3); margin:230px\9 0px\9 0px\9 710px\9; color:BLACK;">Tạo hóa đơn điện tử từ ứng dụng <b>XuatHoaDon<sup>&#8482;</sup></b> của <b>Công ty Cổ Phần TS24</b> - MST:<b>0309478306</b> - www.TS24.com.vn </div>

<!-- Mẫu số HĐ và Tiêu đề -->
<table>
  <col width="20%" />
  <col width="60%" />
  <col width="20%" />
  <tr>
    <td align="center" valign="middle" style="max-width:200px; max-height:150px">
      <xsl:choose>
        <xsl:when test="(inv:invoiceData/inv:userDefines/Logo) and (inv:invoiceData/inv:userDefines/Logo!='')"><img src='data:image/png;base64,{inv:invoiceData/inv:userDefines/Logo}' /></xsl:when>
        <xsl:otherwise>&nbsp;</xsl:otherwise>
      </xsl:choose>
    </td>
    <td align="center" valign="middle">
      <span style="font-size:16pt">
      <b>PHIẾU XUẤT KHO KIÊM<br />
      VẬN CHUYỂN NỘI BỘ</b>
      </span>
    </td>
    <td align="left" valign="top" style="font-size:8pt; white-space:nowrap;">
      Mẫu số: <xsl:value-of select="inv:invoiceData/inv:templateCode"/><br />
      Ký hiệu: <xsl:value-of select="inv:invoiceData/inv:invoiceSerries"/><br />
      Số: <xsl:value-of select="inv:invoiceData/inv:invoiceNumber"/>
    </td>
  </tr>
</table>
<br />

<!-- Thông tin Người xuất -->
<table>
  <col width="35%" />
  <col width="30%" />
  <col width="35%" />
  <tr>
    <td colspan="3" align="left" valign="middle"><b><xsl:value-of select="translate(inv:invoiceData/inv:sellerLegalName,$lower,$upper)"/></b></td>
  </tr>
  <tr>
    <td colspan="3" align="left" valign="top">Địa chỉ: <xsl:value-of select="inv:invoiceData/inv:sellerAddressLine"/></td>
  </tr>
  <tr>
    <td align="left" valign="top">MST: <xsl:value-of select="inv:invoiceData/inv:sellerTaxCode"/></td>
    <td align="left" valign="top">Tel: <xsl:value-of select="inv:invoiceData/inv:sellerPhoneNumber"/></td>
    <td align="left" valign="top">Fax: <xsl:value-of select="inv:invoiceData/inv:sellerFaxNumber"/></td>
  </tr>
  <tr>
    <td align="left" valign="top" style="border-bottom:1px solid #000">Số tài khoản: <xsl:value-of select="inv:invoiceData/inv:sellerBankAccount"/></td>
    <td colspan="2" align="left" valign="top" style="border-bottom:1px solid #000">Tại ngân hàng: <xsl:value-of select="inv:invoiceData/inv:sellerBankName"/></td>
  </tr>
  <tr>
    <td align="left" valign="top">Căn cứ lệnh điều động số: <xsl:value-of select="inv:invoiceData/inv:delivery/inv:deliveryOrderNumber"/></td>
    <td align="left" valign="top">
      <xsl:choose>
        <xsl:when test="(inv:invoiceData/inv:delivery/inv:deliveryOrderDate) and ((inv:invoiceData/inv:delivery/inv:deliveryOrderDate)!='') and (substring(inv:invoiceData/inv:delivery/inv:deliveryOrderDate,5,1)='-')">Ngày <xsl:value-of select="substring(inv:invoiceData/inv:delivery/inv:deliveryOrderDate,9,2)"/> tháng <xsl:value-of select="substring(inv:invoiceData/inv:delivery/inv:deliveryOrderDate,6,2)"/> năm <xsl:value-of select="substring(inv:invoiceData/inv:delivery/inv:deliveryOrderDate,1,4)"/></xsl:when>
        <xsl:when test="(inv:invoiceData/inv:delivery/inv:deliveryOrderDate) and ((inv:invoiceData/inv:delivery/inv:deliveryOrderDate)!='') and (substring(inv:invoiceData/inv:delivery/inv:deliveryOrderDate,3,1)='-')">Ngày <xsl:value-of select="substring(inv:invoiceData/inv:delivery/inv:deliveryOrderDate,1,2)"/> tháng <xsl:value-of select="substring(inv:invoiceData/inv:delivery/inv:deliveryOrderDate,4,2)"/> năm <xsl:value-of select="substring(inv:invoiceData/inv:delivery/inv:deliveryOrderDate,7,4)"/></xsl:when>
        <xsl:otherwise>Ngày <span style="margin-left:10px">&nbsp;</span> tháng <span style="margin-left:10px">&nbsp;</span> năm <span style="margin-left:30px">&nbsp;</span></xsl:otherwise>
      </xsl:choose>
    </td>
    <td align="left" valign="top">của: <xsl:value-of select="inv:invoiceData/inv:delivery/inv:deliveryOrderBy"/></td>
  </tr>
  <tr>
    <td colspan="3" align="left" valign="top">về việc: <xsl:value-of select="inv:invoiceData/inv:delivery/inv:deliveryOrderContent"/></td>
  </tr>
  <tr>
    <td colspan="3" align="left" valign="top">Đơn vị vận chuyển (Người giao hàng): <xsl:value-of select="inv:invoiceData/inv:delivery/inv:deliveryBy"/></td>
  </tr>
  <tr>
    <td colspan="2" align="left" valign="top">Phương tiện (Phương thức) vận chuyển: <xsl:value-of select="inv:invoiceData/inv:delivery/inv:transportationMethod"/></td>
    <td align="left" valign="top">Số công ten nơ: <xsl:value-of select="inv:invoiceData/inv:delivery/inv:containerNumber"/></td>
  </tr>
  <tr>
    <td colspan="3" align="left" valign="top">Xuất từ kho: <xsl:value-of select="inv:invoiceData/inv:delivery/inv:fromWarehouseName"/></td>
  </tr>
  <tr>
    <td colspan="3" align="left" valign="top">Nhập đến kho: <xsl:value-of select="inv:invoiceData/inv:delivery/inv:toWarehouseName"/></td>
  </tr>
</table>

<!-- Dữ liệu chính trên HĐ -->
<table cellspacing="0" cellpadding="3" style="color:BLACK; table-layout:fixed;">
	<col width="5%"/>
	<col width="27%"/>
	<col width="12%"/>
	<col width="7%"/>
	<col width="11%"/>
	<col width="11%"/>
	<col width="12%"/>
	<col width="15%"/>
  <tr>
    <td rowspan="2" align="center" class="borderTD">STT</td>
    <td rowspan="2" align="center" class="borderTD">Tên nhãn hiệu, quy cách, phẩm chất vật tư (Sản phẩm, hàng hóa)</td>
    <td rowspan="2" align="center" class="borderTD">Mã số</td>
    <td rowspan="2" align="center" class="borderTD">Đơn vị tính</td>
    <td colspan="2" align="center" class="borderTD">Số lượng</td>
    <td rowspan="2" align="center" class="borderTD">Đơn giá</td>
    <td rowspan="2" align="center" class="borderTD">Thành tiền (VND)</td>
  </tr>
  <tr>
    <td align="center" class="borderTD">Thực xuất</td>
    <td align="center" class="borderTD">Thực nhập</td>
  </tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr>
    <td colspan="7" align="right" class="borderTD" style="padding-right:2%"><b>Tổng cộng:</b></td>
    <td class="borderTD">&nbsp;</td>
  </tr>
</table>

<table>
  <tr>
    <td align="left">Số tiền bằng chữ: 
      <xsl:choose>
        <xsl:when test="inv:invoiceData/inv:totalAmountWithVATInWord!=''"><xsl:value-of select="inv:invoiceData/inv:totalAmountWithVATInWord"/>.</xsl:when>
        <xsl:otherwise>&nbsp;</xsl:otherwise>
      </xsl:choose>
    </td>
  </tr>
</table>

<!-- Thông tin CKS -->
<table>
  <col width="25%"/>
  <col width="25%"/>
  <col width="25%"/>
  <col width="25%"/>
  <tr>
    <td align="center" valign="bottom" style="white-space:nowrap">
      Người lập<br />
      (Ký và ghi rõ họ tên)
      <br /><br />
      <xsl:value-of select="inv:invoiceData/inv:userDefines/NguoiLap"/>
    </td>
    <td align="center" valign="bottom" style="white-space:nowrap">
      Thủ kho xuất<br />
      (Ký và ghi rõ họ tên)
      <br /><br />
      <xsl:value-of select="inv:invoiceData/inv:userDefines/ThuKhoXuat"/>
    </td>
    <td align="center" valign="bottom" style="white-space:nowrap">
      Người vận chuyển<br />
      (Ký và ghi rõ họ tên)
      <br /><br />
      <xsl:value-of select="inv:invoiceData/inv:userDefines/NguoiVanChuyen"/>
    </td>
    <td align="center" valign="bottom" style="white-space:nowrap">
      <xsl:choose>
        <xsl:when test="(inv:invoiceData/inv:invoiceIssuedDate) and (inv:invoiceData/inv:invoiceIssuedDate)!=''"><i>Ngày <xsl:value-of select="substring(inv:invoiceData/inv:invoiceIssuedDate,9,2)" /> tháng <xsl:value-of select="substring(inv:invoiceData/inv:invoiceIssuedDate,6,2)" /> năm <xsl:value-of select="substring(inv:invoiceData/inv:invoiceIssuedDate,1,4)" /></i></xsl:when>
        <xsl:otherwise><i>Ngày <span style="margin-left:10px">&nbsp;</span> tháng <span style="margin-left:10px">&nbsp;</span> năm <span style="margin-left:30px">&nbsp;</span></i></xsl:otherwise>
      </xsl:choose><br />
      Thủ kho nhập<br />
      (Ký và ghi rõ họ tên)
      <br /><br />
      <xsl:value-of select="inv:invoiceData/inv:userDefines/ThuKhoNhap"/>
    </td>
  </tr>
</table>

<!-- Thông tin xem CKS -->
<table>
  <col width="25%"/>
  <col width="25%"/>
  <col width="25%"/>
  <col width="25%"/>
  <xsl:if test="(CKS) and count(CKS)!=0">
  <tr>
    <td colspan="4">&nbsp;</td>   
  </tr>
  <tr>
    <xsl:if test="(CKS/CKSNguoiLap/Serial) and (CKS/CKSNguoiLap/Serial)!=''">
    <xsl:choose>
      <xsl:when test="(CKS/CKSNguoiLap/LogoCKS) and (CKS/CKSNguoiLap/LogoCKS)!=''">
    <td align="left" valign="middle">
      <span style="position:absolute; z-index:-10;"><img src='data:image/png;base64,{CKS/CKSNguoiLap/LogoCKS}' /></span>
      Signer Info: <xsl:value-of select="CKS/CKSNguoiLap/Subject"/><br/>
      Serial number: <xsl:value-of select="CKS/CKSNguoiLap/Serial"/><br/>
      Signing Time: <xsl:value-of select="CKS/CKSNguoiLap/Time"/>
    </td>
      </xsl:when>
      <xsl:otherwise>
    <td align="left" valign="middle" style="padding-left:40px">
      <span style="position:absolute; margin-left:-40px; margin-top:10px"><img src='data:image/png;base64,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' /></span>
      Signer Info: <xsl:value-of select="CKS/CKSNguoiLap/Subject"/><br/>
      Serial number: <xsl:value-of select="CKS/CKSNguoiLap/Serial"/><br/>
      Signing Time: <xsl:value-of select="CKS/CKSNguoiLap/Time"/>
    </td>
      </xsl:otherwise>
    </xsl:choose>
    </xsl:if>
    <xsl:if test="not(CKS/CKSNguoiLap/Serial) or (CKS/CKSNguoiLap/Serial)=''">
    <td>&nbsp;</td>
    </xsl:if>
    <xsl:if test="(CKS/CKSThuKhoXuat/Serial) and (CKS/CKSThuKhoXuat/Serial)!=''">
    <xsl:choose>
      <xsl:when test="(CKS/CKSThuKhoXuat/LogoCKS) and (CKS/CKSThuKhoXuat/LogoCKS)!=''">
    <td align="left" valign="middle">
      <span style="position:absolute; z-index:-10;"><img src='data:image/png;base64,{CKS/CKSThuKhoXuat/LogoCKS}' /></span>
      Signer Info: <xsl:value-of select="CKS/CKSThuKhoXuat/Subject"/><br/>
      Serial number: <xsl:value-of select="CKS/CKSThuKhoXuat/Serial"/><br/>
      Signing Time: <xsl:value-of select="CKS/CKSThuKhoXuat/Time"/>
    </td>
      </xsl:when>
      <xsl:otherwise>
    <td align="left" valign="middle" style="padding-left:40px">
      <span style="position:absolute; margin-left:-40px; margin-top:10px"><img src='data:image/png;base64,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' /></span>
      Signer Info: <xsl:value-of select="CKS/CKSThuKhoXuat/Subject"/><br/>
      Serial number: <xsl:value-of select="CKS/CKSThuKhoXuat/Serial"/><br/>
      Signing Time: <xsl:value-of select="CKS/CKSThuKhoXuat/Time"/>
    </td>
      </xsl:otherwise>
    </xsl:choose>
    </xsl:if>
    <xsl:if test="not(CKS/CKSThuKhoXuat/Serial) or (CKS/CKSThuKhoXuat/Serial)=''">
    <td>&nbsp;</td>
    </xsl:if>
    <xsl:if test="(CKS/CKSNguoiVanChuyen/Serial) and (CKS/CKSNguoiVanChuyen/Serial)!=''">
    <xsl:choose>
      <xsl:when test="(CKS/CKSNguoiVanChuyen/LogoCKS) and (CKS/CKSNguoiVanChuyen/LogoCKS)!=''">
    <td align="left" valign="middle">
      <span style="position:absolute; z-index:-10;"><img src='data:image/png;base64,{CKS/CKSNguoiVanChuyen/LogoCKS}' /></span>
      Signer Info: <xsl:value-of select="CKS/CKSNguoiVanChuyen/Subject"/><br/>
      Serial number: <xsl:value-of select="CKS/CKSNguoiVanChuyen/Serial"/><br/>
      Signing Time: <xsl:value-of select="CKS/CKSNguoiVanChuyen/Time"/>
    </td>
      </xsl:when>
      <xsl:otherwise>
    <td align="left" valign="middle" style="padding-left:40px">
      <span style="position:absolute; margin-left:-40px; margin-top:10px"><img src='data:image/png;base64,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' /></span>
      Signer Info: <xsl:value-of select="CKS/CKSNguoiVanChuyen/Subject"/><br/>
      Serial number: <xsl:value-of select="CKS/CKSNguoiVanChuyen/Serial"/><br/>
      Signing Time: <xsl:value-of select="CKS/CKSNguoiVanChuyen/Time"/>
    </td>
      </xsl:otherwise>
    </xsl:choose>
    </xsl:if>
    <xsl:if test="not(CKS/CKSNguoiVanChuyen/Serial) or (CKS/CKSNguoiVanChuyen/Serial)=''">
    <td>&nbsp;</td>
    </xsl:if>
    <xsl:if test="(CKS/CKSThuKhoNhap/Serial) and (CKS/CKSThuKhoNhap/Serial)!=''">
    <xsl:choose>
      <xsl:when test="(CKS/CKSThuKhoNhap/LogoCKS) and (CKS/CKSThuKhoNhap/LogoCKS)!=''">
    <td align="left" valign="middle">
      <span style="position:absolute; z-index:-10;"><img src='data:image/png;base64,{CKS/CKSThuKhoNhap/LogoCKS}' /></span>
      Signer Info: <xsl:value-of select="CKS/CKSThuKhoNhap/Subject"/><br/>
      Serial number: <xsl:value-of select="CKS/CKSThuKhoNhap/Serial"/><br/>
      Signing Time: <xsl:value-of select="CKS/CKSThuKhoNhap/Time"/>
    </td>
      </xsl:when>
      <xsl:otherwise>
    <td align="left" valign="middle" style="padding-left:40px">
      <span style="position:absolute; margin-left:-40px; margin-top:10px"><img src='data:image/png;base64,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' /></span>
      Signer Info: <xsl:value-of select="CKS/CKSThuKhoNhap/Subject"/><br/>
      Serial number: <xsl:value-of select="CKS/CKSThuKhoNhap/Serial"/><br/>
      Signing Time: <xsl:value-of select="CKS/CKSThuKhoNhap/Time"/>
    </td>
      </xsl:otherwise>
    </xsl:choose>
    </xsl:if>
    <xsl:if test="not(CKS/CKSThuKhoNhap/Serial) or (CKS/CKSThuKhoNhap/Serial)=''">
    <td>&nbsp;</td>
    </xsl:if>
  </tr>
  <tr>
    <td colspan="4"><br /><br /></td>   
  </tr>
  </xsl:if>
</table>

<!-- Ghi chú -->
<table style="color:BLACK">
  <tr>
    <td style="font-size:8pt; text-align:justify; border-top: 1px solid #000;">
      <b><u>Ghi chú:</u></b> Người bán hoặc người mua có thể chuyển đổi từ hóa đơn điện tử sang hóa đơn giấy một lần duy nhất bằng 2 cách: cách 1, vào phần mềm XuatHoaDon<sup>&#8482;</sup> của công ty TS24 chọn mục <b>chuyển đổi từ hóa đơn điện tử sang giấy</b> để có thể in ra giấy; cách 2, in hóa đơn ra giấy và ghi trực tiếp vào chỗ trống trên hóa đơn nội dung "HÓA ĐƠN CHUYỂN ĐỔI TỪ HÓA ĐƠN ĐIỆN TỬ"; ghi rõ họ tên, chữ ký của người thực hiện chuyển đổi, đóng dấu xác nhận (nếu có) theo hướng dẫn của thông tư Hóa đơn điện tử.
    </td>
  </tr>
</table>
  </xsl:when>
  <xsl:otherwise>
<!-- Thông tin XuatHoaDon™ -->
<div style="position:absolute; z-index:100; height:1; white-space:nowrap; font-size:8pt; transform: rotate(270deg); -moz-transform: rotate(270deg); margin:500px 0px 0px 460px; -o-transform: rotate(270deg); -webkit-transform: rotate(270deg); filter:  progid:DXImageTransform.Microsoft.BasicImage(rotation=3); -ms-filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=3); margin:230px\9 0px\9 0px\9 710px\9; color:BLACK;">Tạo hóa đơn điện tử từ ứng dụng <b>XuatHoaDon<sup>&#8482;</sup></b> của <b>Công ty Cổ Phần TS24</b> - MST:<b>0309478306</b> - www.TS24.com.vn </div>

<!-- Mẫu số HĐ và Tiêu đề -->
<table>
  <col width="20%" />
  <col width="60%" />
  <col width="20%" />
  <tr>
    <td align="center" valign="middle" style="max-width:200px; max-height:150px">
      <xsl:choose>
        <xsl:when test="(inv:invoiceData/inv:userDefines/Logo) and (inv:invoiceData/inv:userDefines/Logo!='')"><img src='data:image/png;base64,{inv:invoiceData/inv:userDefines/Logo}' /></xsl:when>
        <xsl:otherwise>&nbsp;</xsl:otherwise>
      </xsl:choose>
    </td>
    <td align="center" valign="middle">
      <span style="font-size:16pt">
      <b>PHIẾU XUẤT KHO KIÊM<br />
      VẬN CHUYỂN NỘI BỘ</b>
      </span>
    </td>
    <td align="left" valign="top" style="font-size:8pt; white-space:nowrap;">
      Mẫu số: <xsl:value-of select="inv:invoiceData/inv:templateCode"/><br />
      Ký hiệu: <xsl:value-of select="inv:invoiceData/inv:invoiceSerries"/><br />
      Số: <xsl:value-of select="inv:invoiceData/inv:invoiceNumber"/>
    </td>
  </tr>
</table>
<br />

<!-- Thông tin Người xuất -->
<table>
  <col width="35%" />
  <col width="30%" />
  <col width="35%" />
  <tr>
    <td colspan="3" align="left" valign="middle"><b><xsl:value-of select="translate(inv:invoiceData/inv:sellerLegalName,$lower,$upper)"/></b></td>
  </tr>
  <tr>
    <td colspan="3" align="left" valign="top">Địa chỉ: <xsl:value-of select="inv:invoiceData/inv:sellerAddressLine"/></td>
  </tr>
  <tr>
    <td align="left" valign="top">MST: <xsl:value-of select="inv:invoiceData/inv:sellerTaxCode"/></td>
    <td align="left" valign="top">Tel: <xsl:value-of select="inv:invoiceData/inv:sellerPhoneNumber"/></td>
    <td align="left" valign="top">Fax: <xsl:value-of select="inv:invoiceData/inv:sellerFaxNumber"/></td>
  </tr>
  <tr>
    <td align="left" valign="top" style="border-bottom:1px solid #000">Số tài khoản: <xsl:value-of select="inv:invoiceData/inv:sellerBankAccount"/></td>
    <td colspan="2" align="left" valign="top" style="border-bottom:1px solid #000">Tại ngân hàng: <xsl:value-of select="inv:invoiceData/inv:sellerBankName"/></td>
  </tr>
  <tr>
    <td align="left" valign="top">Căn cứ lệnh điều động số: <xsl:value-of select="inv:invoiceData/inv:delivery/inv:deliveryOrderNumber"/></td>
    <td align="left" valign="top">
      <xsl:choose>
        <xsl:when test="(inv:invoiceData/inv:delivery/inv:deliveryOrderDate) and ((inv:invoiceData/inv:delivery/inv:deliveryOrderDate)!='') and (substring(inv:invoiceData/inv:delivery/inv:deliveryOrderDate,5,1)='-')">Ngày <xsl:value-of select="substring(inv:invoiceData/inv:delivery/inv:deliveryOrderDate,9,2)"/> tháng <xsl:value-of select="substring(inv:invoiceData/inv:delivery/inv:deliveryOrderDate,6,2)"/> năm <xsl:value-of select="substring(inv:invoiceData/inv:delivery/inv:deliveryOrderDate,1,4)"/></xsl:when>
        <xsl:when test="(inv:invoiceData/inv:delivery/inv:deliveryOrderDate) and ((inv:invoiceData/inv:delivery/inv:deliveryOrderDate)!='') and (substring(inv:invoiceData/inv:delivery/inv:deliveryOrderDate,3,1)='-')">Ngày <xsl:value-of select="substring(inv:invoiceData/inv:delivery/inv:deliveryOrderDate,1,2)"/> tháng <xsl:value-of select="substring(inv:invoiceData/inv:delivery/inv:deliveryOrderDate,4,2)"/> năm <xsl:value-of select="substring(inv:invoiceData/inv:delivery/inv:deliveryOrderDate,7,4)"/></xsl:when>
        <xsl:otherwise>Ngày <span style="margin-left:10px">&nbsp;</span> tháng <span style="margin-left:10px">&nbsp;</span> năm <span style="margin-left:30px">&nbsp;</span></xsl:otherwise>
      </xsl:choose>
    </td>
    <td align="left" valign="top">của: <xsl:value-of select="inv:invoiceData/inv:delivery/inv:deliveryOrderBy"/></td>
  </tr>
  <tr>
    <td colspan="3" align="left" valign="top">về việc: <xsl:value-of select="inv:invoiceData/inv:delivery/inv:deliveryOrderContent"/></td>
  </tr>
  <tr>
    <td colspan="3" align="left" valign="top">Đơn vị vận chuyển (Người giao hàng): <xsl:value-of select="inv:invoiceData/inv:delivery/inv:deliveryBy"/></td>
  </tr>
  <tr>
    <td colspan="2" align="left" valign="top">Phương tiện (Phương thức) vận chuyển: <xsl:value-of select="inv:invoiceData/inv:delivery/inv:transportationMethod"/></td>
    <td align="left" valign="top">Số công ten nơ: <xsl:value-of select="inv:invoiceData/inv:delivery/inv:containerNumber"/></td>
  </tr>
  <tr>
    <td colspan="3" align="left" valign="top">Xuất từ kho: <xsl:value-of select="inv:invoiceData/inv:delivery/inv:fromWarehouseName"/></td>
  </tr>
  <tr>
    <td colspan="3" align="left" valign="top">Nhập đến kho: <xsl:value-of select="inv:invoiceData/inv:delivery/inv:toWarehouseName"/></td>
  </tr>
</table>

<!-- Dữ liệu chính trên HĐ -->
<table cellspacing="0" cellpadding="3" style="color:BLACK; table-layout:fixed;">
	<col width="5%"/>
	<col width="27%"/>
	<col width="12%"/>
	<col width="7%"/>
	<col width="11%"/>
	<col width="11%"/>
	<col width="12%"/>
	<col width="15%"/>
  <tr>
    <td rowspan="2" align="center" class="borderTD">STT</td>
    <td rowspan="2" align="center" class="borderTD">Tên nhãn hiệu, quy cách, phẩm chất vật tư (Sản phẩm, hàng hóa)</td>
    <td rowspan="2" align="center" class="borderTD">Mã số</td>
    <td rowspan="2" align="center" class="borderTD">Đơn vị tính</td>
    <td colspan="2" align="center" class="borderTD">Số lượng</td>
    <td rowspan="2" align="center" class="borderTD">Đơn giá</td>
    <td rowspan="2" align="center" class="borderTD">Thành tiền (VND)</td>
  </tr>
  <tr>
    <td align="center" class="borderTD">Thực xuất</td>
    <td align="center" class="borderTD">Thực nhập</td>
  </tr>
  <xsl:for-each select="inv:invoiceData/inv:items/inv:item">
  <tr>
    <td align="center" class="borderTD"><xsl:value-of select="position()"/></td>
    <td align="left" class="borderTD">
      <xsl:choose>
        <xsl:when test="(inv:itemName) and (inv:itemName)!=''"><xsl:value-of select="inv:itemName"/></xsl:when>
        <xsl:otherwise>&nbsp;</xsl:otherwise>
      </xsl:choose>
    </td>
    <td align="center" class="borderTD">
      <xsl:choose>
        <xsl:when test="(inv:itemCode) and (inv:itemCode)!=''"><xsl:value-of select="inv:itemCode"/></xsl:when>
        <xsl:otherwise>&nbsp;</xsl:otherwise>
      </xsl:choose>
    </td>
    <td align="center" class="borderTD">
      <xsl:choose>
        <xsl:when test="(inv:unitName) and (inv:unitName)!=''"><xsl:value-of select="inv:unitName"/></xsl:when>
        <xsl:otherwise>&nbsp;</xsl:otherwise>
      </xsl:choose>
    </td>
    <td align="center" class="borderTD">
      <xsl:choose>
        <xsl:when test="(inv:quantity) and (inv:quantity)!='' and (inv:quantity)!=0"><xsl:value-of select="format-number(inv:quantity, '#.##0,##', 'vndong')"/></xsl:when>
        <xsl:otherwise>&nbsp;</xsl:otherwise>
      </xsl:choose>
    </td>
    <td align="center" class="borderTD">
      <xsl:choose>
        <xsl:when test="(inv:quantity) and (inv:quantity)!='' and (inv:quantity)!=0"><xsl:value-of select="format-number(inv:quantity, '#.##0,##', 'vndong')"/></xsl:when>
        <xsl:otherwise>&nbsp;</xsl:otherwise>
      </xsl:choose>
    </td>
    <td align="center" class="borderTD">
      <xsl:choose>
        <xsl:when test="(inv:unitPrice) and (inv:unitPrice)!='' and (inv:unitPrice)!=0"><xsl:value-of select="format-number(inv:unitPrice, '#.##0,##', 'vndong')"/></xsl:when>
        <xsl:otherwise>&nbsp;</xsl:otherwise>
      </xsl:choose>
    </td>
    <td align="right" class="borderTD">
      <xsl:choose>
        <xsl:when test="(inv:itemTotalAmountWithoutVat) and (inv:itemTotalAmountWithoutVat)!='' and (inv:itemTotalAmountWithoutVat)!=0"><xsl:value-of select="format-number(inv:itemTotalAmountWithoutVat, '#.##0,##', 'vndong')"/></xsl:when>
        <xsl:otherwise>&nbsp;</xsl:otherwise>
      </xsl:choose>
    </td>
  </tr>
  </xsl:for-each>
  <xsl:if test="count(inv:invoiceData/inv:items/inv:item)=0 or not(inv:invoiceData/inv:items/inv:item)">
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  </xsl:if>
  <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=1">
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  </xsl:if>
  <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=2">
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  </xsl:if>
  <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=3">
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  </xsl:if>
  <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=4">
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  </xsl:if>
  <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=5">
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  </xsl:if>
  <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=6">
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  </xsl:if>
  <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=7">
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  </xsl:if>
  <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=8">
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  </xsl:if>
  <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=9">
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  </xsl:if>
  <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=10">
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  </xsl:if>
  <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=11">
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  </xsl:if>
  <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=12">
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  </xsl:if>
  <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=13">
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  </xsl:if>
  <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=14">
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  </xsl:if>
  <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=15">
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  </xsl:if>
  <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=16">
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  </xsl:if>
  <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=17">
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  </xsl:if>
  <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=18">
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  </xsl:if>
  <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=19">
  <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
  </xsl:if>
  <tr>
    <td colspan="7" align="right" class="borderTD" style="padding-right:2%"><b>Tổng cộng:</b></td>
    <td align="right" class="borderTD">
      <xsl:choose>
        <xsl:when test="(inv:invoiceData/inv:totalAmountWithoutVAT) and (inv:invoiceData/inv:totalAmountWithoutVAT)!='' and (inv:invoiceData/inv:totalAmountWithoutVAT)!=0"><xsl:value-of select="format-number(inv:invoiceData/inv:totalAmountWithoutVAT, '#.##0,##', 'vndong')"/></xsl:when>
        <xsl:otherwise>&nbsp;</xsl:otherwise>
      </xsl:choose>
    </td>
  </tr>
</table>

<table>
  <tr>
    <td align="left">Số tiền bằng chữ: 
      <xsl:choose>
        <xsl:when test="inv:invoiceData/inv:totalAmountWithVATInWord!=''"><xsl:value-of select="inv:invoiceData/inv:totalAmountWithVATInWord"/>.</xsl:when>
        <xsl:otherwise>&nbsp;</xsl:otherwise>
      </xsl:choose>
    </td>
  </tr>
</table>

<!-- Thông tin CKS -->
<table>
  <col width="25%"/>
  <col width="25%"/>
  <col width="25%"/>
  <col width="25%"/>
  <tr>
    <td align="center" valign="bottom" style="white-space:nowrap">
      Người lập<br />
      (Ký và ghi rõ họ tên)
      <br /><br />
      <xsl:value-of select="inv:invoiceData/inv:userDefines/NguoiLap"/>
    </td>
    <td align="center" valign="bottom" style="white-space:nowrap">
      Thủ kho xuất<br />
      (Ký và ghi rõ họ tên)
      <br /><br />
      <xsl:value-of select="inv:invoiceData/inv:userDefines/ThuKhoXuat"/>
    </td>
    <td align="center" valign="bottom" style="white-space:nowrap">
      Người vận chuyển<br />
      (Ký và ghi rõ họ tên)
      <br /><br />
      <xsl:value-of select="inv:invoiceData/inv:userDefines/NguoiVanChuyen"/>
    </td>
    <td align="center" valign="bottom" style="white-space:nowrap">
      <xsl:choose>
        <xsl:when test="(inv:invoiceData/inv:invoiceIssuedDate) and (inv:invoiceData/inv:invoiceIssuedDate)!=''"><i>Ngày <xsl:value-of select="substring(inv:invoiceData/inv:invoiceIssuedDate,9,2)" /> tháng <xsl:value-of select="substring(inv:invoiceData/inv:invoiceIssuedDate,6,2)" /> năm <xsl:value-of select="substring(inv:invoiceData/inv:invoiceIssuedDate,1,4)" /></i></xsl:when>
        <xsl:otherwise><i>Ngày <span style="margin-left:10px">&nbsp;</span> tháng <span style="margin-left:10px">&nbsp;</span> năm <span style="margin-left:30px">&nbsp;</span></i></xsl:otherwise>
      </xsl:choose><br />
      Thủ kho nhập<br />
      (Ký và ghi rõ họ tên)
      <br /><br />
      <xsl:value-of select="inv:invoiceData/inv:userDefines/ThuKhoNhap"/>
    </td>
  </tr>
</table>

<!-- Thông tin xem CKS -->
<table>
  <col width="25%"/>
  <col width="25%"/>
  <col width="25%"/>
  <col width="25%"/>
  <xsl:if test="(CKS) and count(CKS)!=0">
  <tr>
    <td colspan="4">&nbsp;</td>   
  </tr>
  <tr>
    <xsl:if test="(CKS/CKSNguoiLap/Serial) and (CKS/CKSNguoiLap/Serial)!=''">
    <xsl:choose>
      <xsl:when test="(CKS/CKSNguoiLap/LogoCKS) and (CKS/CKSNguoiLap/LogoCKS)!=''">
    <td align="left" valign="middle">
      <span style="position:absolute; z-index:-10;"><img src='data:image/png;base64,{CKS/CKSNguoiLap/LogoCKS}' /></span>
      Signer Info: <xsl:value-of select="CKS/CKSNguoiLap/Subject"/><br/>
      Serial number: <xsl:value-of select="CKS/CKSNguoiLap/Serial"/><br/>
      Signing Time: <xsl:value-of select="CKS/CKSNguoiLap/Time"/>
    </td>
      </xsl:when>
      <xsl:otherwise>
    <td align="left" valign="middle" style="padding-left:40px">
      <span style="position:absolute; margin-left:-40px; margin-top:10px"><img src='data:image/png;base64,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' /></span>
      Signer Info: <xsl:value-of select="CKS/CKSNguoiLap/Subject"/><br/>
      Serial number: <xsl:value-of select="CKS/CKSNguoiLap/Serial"/><br/>
      Signing Time: <xsl:value-of select="CKS/CKSNguoiLap/Time"/>
    </td>
      </xsl:otherwise>
    </xsl:choose>
    </xsl:if>
    <xsl:if test="not(CKS/CKSNguoiLap/Serial) or (CKS/CKSNguoiLap/Serial)=''">
    <td>&nbsp;</td>
    </xsl:if>
    <xsl:if test="(CKS/CKSThuKhoXuat/Serial) and (CKS/CKSThuKhoXuat/Serial)!=''">
    <xsl:choose>
      <xsl:when test="(CKS/CKSThuKhoXuat/LogoCKS) and (CKS/CKSThuKhoXuat/LogoCKS)!=''">
    <td align="left" valign="middle">
      <span style="position:absolute; z-index:-10;"><img src='data:image/png;base64,{CKS/CKSThuKhoXuat/LogoCKS}' /></span>
      Signer Info: <xsl:value-of select="CKS/CKSThuKhoXuat/Subject"/><br/>
      Serial number: <xsl:value-of select="CKS/CKSThuKhoXuat/Serial"/><br/>
      Signing Time: <xsl:value-of select="CKS/CKSThuKhoXuat/Time"/>
    </td>
      </xsl:when>
      <xsl:otherwise>
    <td align="left" valign="middle" style="padding-left:40px">
      <span style="position:absolute; margin-left:-40px; margin-top:10px"><img src='data:image/png;base64,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' /></span>
      Signer Info: <xsl:value-of select="CKS/CKSThuKhoXuat/Subject"/><br/>
      Serial number: <xsl:value-of select="CKS/CKSThuKhoXuat/Serial"/><br/>
      Signing Time: <xsl:value-of select="CKS/CKSThuKhoXuat/Time"/>
    </td>
      </xsl:otherwise>
    </xsl:choose>
    </xsl:if>
    <xsl:if test="not(CKS/CKSThuKhoXuat/Serial) or (CKS/CKSThuKhoXuat/Serial)=''">
    <td>&nbsp;</td>
    </xsl:if>
    <xsl:if test="(CKS/CKSNguoiVanChuyen/Serial) and (CKS/CKSNguoiVanChuyen/Serial)!=''">
    <xsl:choose>
      <xsl:when test="(CKS/CKSNguoiVanChuyen/LogoCKS) and (CKS/CKSNguoiVanChuyen/LogoCKS)!=''">
    <td align="left" valign="middle">
      <span style="position:absolute; z-index:-10;"><img src='data:image/png;base64,{CKS/CKSNguoiVanChuyen/LogoCKS}' /></span>
      Signer Info: <xsl:value-of select="CKS/CKSNguoiVanChuyen/Subject"/><br/>
      Serial number: <xsl:value-of select="CKS/CKSNguoiVanChuyen/Serial"/><br/>
      Signing Time: <xsl:value-of select="CKS/CKSNguoiVanChuyen/Time"/>
    </td>
      </xsl:when>
      <xsl:otherwise>
    <td align="left" valign="middle" style="padding-left:40px">
      <span style="position:absolute; margin-left:-40px; margin-top:10px"><img src='data:image/png;base64,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' /></span>
      Signer Info: <xsl:value-of select="CKS/CKSNguoiVanChuyen/Subject"/><br/>
      Serial number: <xsl:value-of select="CKS/CKSNguoiVanChuyen/Serial"/><br/>
      Signing Time: <xsl:value-of select="CKS/CKSNguoiVanChuyen/Time"/>
    </td>
      </xsl:otherwise>
    </xsl:choose>
    </xsl:if>
    <xsl:if test="not(CKS/CKSNguoiVanChuyen/Serial) or (CKS/CKSNguoiVanChuyen/Serial)=''">
    <td>&nbsp;</td>
    </xsl:if>
    <xsl:if test="(CKS/CKSThuKhoNhap/Serial) and (CKS/CKSThuKhoNhap/Serial)!=''">
    <xsl:choose>
      <xsl:when test="(CKS/CKSThuKhoNhap/LogoCKS) and (CKS/CKSThuKhoNhap/LogoCKS)!=''">
    <td align="left" valign="middle">
      <span style="position:absolute; z-index:-10;"><img src='data:image/png;base64,{CKS/CKSThuKhoNhap/LogoCKS}' /></span>
      Signer Info: <xsl:value-of select="CKS/CKSThuKhoNhap/Subject"/><br/>
      Serial number: <xsl:value-of select="CKS/CKSThuKhoNhap/Serial"/><br/>
      Signing Time: <xsl:value-of select="CKS/CKSThuKhoNhap/Time"/>
    </td>
      </xsl:when>
      <xsl:otherwise>
    <td align="left" valign="middle" style="padding-left:40px">
      <span style="position:absolute; margin-left:-40px; margin-top:10px"><img src='data:image/png;base64,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' /></span>
      Signer Info: <xsl:value-of select="CKS/CKSThuKhoNhap/Subject"/><br/>
      Serial number: <xsl:value-of select="CKS/CKSThuKhoNhap/Serial"/><br/>
      Signing Time: <xsl:value-of select="CKS/CKSThuKhoNhap/Time"/>
    </td>
      </xsl:otherwise>
    </xsl:choose>
    </xsl:if>
    <xsl:if test="not(CKS/CKSThuKhoNhap/Serial) or (CKS/CKSThuKhoNhap/Serial)=''">
    <td>&nbsp;</td>
    </xsl:if>
  </tr>
  <tr>
    <td colspan="4"><br /><br /></td>   
  </tr>
  </xsl:if>
</table>

<!-- Ghi chú -->
<table style="color:BLACK">
  <tr>
    <td style="font-size:8pt; text-align:justify; border-top: 1px solid #000;">
      <b><u>Ghi chú:</u></b> Người bán hoặc người mua có thể chuyển đổi từ hóa đơn điện tử sang hóa đơn giấy một lần duy nhất bằng 2 cách: cách 1, vào phần mềm XuatHoaDon<sup>&#8482;</sup> của công ty TS24 chọn mục <b>chuyển đổi từ hóa đơn điện tử sang giấy</b> để có thể in ra giấy; cách 2, in hóa đơn ra giấy và ghi trực tiếp vào chỗ trống trên hóa đơn nội dung "HÓA ĐƠN CHUYỂN ĐỔI TỪ HÓA ĐƠN ĐIỆN TỬ"; ghi rõ họ tên, chữ ký của người thực hiện chuyển đổi, đóng dấu xác nhận (nếu có) theo hướng dẫn của thông tư Hóa đơn điện tử.
    </td>
  </tr>
</table>
  </xsl:otherwise>
</xsl:choose>

</td></tr></table>
</div>
</body>
</html>

</xsl:template>
</xsl:stylesheet>