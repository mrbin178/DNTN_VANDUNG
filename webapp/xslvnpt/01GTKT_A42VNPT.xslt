<?xml version="1.0" encoding="utf-8"?><!-- DWXMLSource="01_GTGT_DT_VNPT_v1.0.2.xml" -->
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

  <xsl:output method="html" encoding="utf-8" doctype-public=" var tempDoc = document.impl" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>
  
  <xsl:decimal-format name="vndong" decimal-separator=',' grouping-separator='.' />
  <xsl:variable name="lower">abcdefghijklmnopqrstuvwxyzàáảãạăằắẳẵặâầấẩẫậđèéẻẽẹêềếểễệìíỉĩịòóỏõọôồốổỗộơờớởỡợùúủũụưừứửữựỳýỷỹỵ</xsl:variable>
  <xsl:variable name="upper">ABCDEFGHIJKLMNOPQRSTUVWXYZÀÁẢÃẠĂẰẮẲẴẶÂẦẤẨẪẬĐÈÉẺẼẸÊỀẾỂỄỆÌÍỈĨỊÒÓỎÕỌÔỒỐỔỖỘƠỜỚỞỠỢÙÚỦŨỤƯỪỨỬỮỰỲÝỶỸỴ</xsl:variable>
  
  <!-- Trạng thái điều chỉnh hóa đơn -->
  <xsl:template name="TrangThaiDCHD">
    <xsl:param name="val"/>
    <xsl:choose>
      <xsl:when test="$val = '1'">Hóa đơn gốc (hóa đơn thường)</xsl:when>
      <xsl:when test="$val = '2'">Hóa đơn bị thay thế (hay còn gọi là hóa đơn bị xóa bỏ)</xsl:when>
      <xsl:when test="$val = '3'">Thay thế cho hóa đơn xác thực số:</xsl:when>
      <xsl:when test="$val = '4'">Hóa đơn bị điều chỉnh</xsl:when>
      <xsl:when test="$val = '5'">Điều chỉnh cho hóa đơn xác thực số:</xsl:when>
      <xsl:when test="$val = '6'">Hóa đơn bị xóa bỏ</xsl:when>
      <xsl:when test="$val = '7'">Xóa bỏ cho hóa đơn xác thực số:</xsl:when>
      <xsl:when test="$val = '8'">Hóa đơn hủy</xsl:when>
      <xsl:when test="$val = '9'">Hóa đơn điều chỉnh chiết khấu</xsl:when>
      <xsl:otherwise><xsl:value-of select="$val"/></xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <xsl:template match="Invoice">
    <html>
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>HOA_DON_DIEN_TU</title>
      
        <style type="text/css">
          table { empty-cells:show; border: 0; width: 700px; font-size:10pt; font-family:"Times New Roman", Times, serif; color:#0a51a1; border-collapse:collapse; table-layout:fixed }
		  .pagebreak { page-break-after: always; }
          .cell, .half-cell { border: 1px solid #000; display:inline-block; *display:inline; zoom:1; padding: 0px; }
          .cellformat{ width:14px; height:14px; line-height:14px; text-align:center; }
          .cellformatheight{ height:14px; line-height:14px; text-align:center; }
          .cellformatdetail{ width:20px; height:20px; line-height:20px; text-align:center; }
          .borderTD { border:1px solid #000; border-collapse:collapse; vertical-align:middle; }
          .borderTD_note { border:1px solid #000; border-collapse:collapse; vertical-align:middle; word-break:break-all; }
          .borderLR { border:1px solid #000; border-collapse:collapse; vertical-align:middle; border-bottom:none; border-top:none; }
          .borderTB { border:1px solid #000; border-collapse:collapse; vertical-align:middle; border-right:none; border-left:none; }
		  .borderTop { border-top:1px solid #000; border-collapse:collapse; vertical-align:middle; }
		  .borderBottom { border-bottom:1px solid #000; border-collapse:collapse; vertical-align:middle; }
		  .borderLeft { border-left:1px solid #000; border-collapse:collapse; vertical-align:middle; }
		  .borderRight { border-right:1px solid #000; border-collapse:collapse; vertical-align:middle; }
        </style>
      </head>
      
      <body>
      <div style="width:700px; margin:0 auto; padding:8px; background-image: url('data:image/png;base64,{TvanData/Content/Extra/Background}'); background-repeat:no-repeat; background-position:center; text-align:center;">
        <!-- Hình hóa đơn mẫu -->
        <xsl:if test="(TvanData/Content/InvoiceNo) and (TvanData/Content/InvoiceNo)='0000000'">
          <xsl:copy-of select="$HinhHDMau"/>
        </xsl:if>
        
        <!-- Thông tin ứng dụng XuatHoaDon™ -->
        <xsl:copy-of select="$UngDungXHD"/>
        
        <!-- Mẫu số HĐ và Tiêu đề -->
        <xsl:copy-of select="$MauSovaTieuDe"/>
        <br />
  
        <!-- Thông tin Người bán và Người mua -->
        <xsl:copy-of select="$TTNguoiBanvaNguoiMua"/>

        <!-- Hình hóa đơn xóa bỏ -->
        <xsl:if test="(TvanData/Content/Extra/HoaDonThayThe) and (TvanData/Content/Extra/HoaDonThayThe)=7">
          <xsl:copy-of select="$HinhHDXoaBo"/>
        </xsl:if>
           
        <!-- Thông tin chi tiết hóa đơn (Hàng hóa, dịch vụ) -->
        <xsl:choose>
          <!-- TRƯỜNG HỢP 1: NỘP HÓA ĐƠN MẪU -->  
          <xsl:when test="(TvanData/Content/InvoiceNo) and (TvanData/Content/InvoiceNo)='0000000'">
            <table cellspacing="0" cellpadding="2" style="color:BLACK; table-layout:fixed;">
              <col width="6%"/>
              <col width="20%"/>
              <col width="6%"/>
              <col width="7%"/>
              <col width="10%"/>
              <col width="15%"/>
              <col width="6%"/>
              <col width="15%"/>
              <col width="15%"/>
              <tr>
                <td align="center" class="borderTD">STT</td>
                <td align="center" class="borderTD">Tên hàng hóa, dịch vụ</td>
                <td align="center" class="borderTD">Đơn vị tính</td>
                <td align="center" class="borderTD">Số lượng</td>
                <td align="center" class="borderTD">Đơn giá</td>
                <td align="center" class="borderTD">Thành tiền<br />chưa thuế</td>
                <td align="center" class="borderTD">Thuế suất (%)</td>
                <td align="center" class="borderTD">Tiền thuế</td>
                <td align="center" class="borderTD">Thành tiền<br />có thuế</td>
              </tr>
              <tr>
                <td align="center" class="borderTD">(1)</td>
                <td align="center" class="borderTD">(2)</td>
                <td align="center" class="borderTD">(3)</td>
                <td align="center" class="borderTD">(4)</td>
                <td align="center" class="borderTD">(5)</td>
                <td align="center" class="borderTD">(6)</td>
                <td align="center" class="borderTD">(7)</td>
                <td align="center" class="borderTD">(8)</td>
                <td align="center" class="borderTD">(9)</td>
              </tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr>
                <td colspan="5" align="right" class="borderTD"><b>Tổng cộng:</b></td>
                <td class="borderTD">&nbsp;</td>
                <td class="borderTD">&nbsp;</td>
                <td class="borderTD">&nbsp;</td>
                <td class="borderTD">&nbsp;</td>
              </tr>
            </table>
          </xsl:when>
          <!-- TRƯỜNG HỢP 2: CHI TIẾT HÓA ĐƠN CÓ BẢNG KÊ-->  
          <xsl:when test="(TvanData/Content/Extra/SoBangKe) and (TvanData/Content/Extra/SoBangKe)!='' and (TvanData/Content/Extra/SoBangKe)!=0">
            <table cellspacing="0" cellpadding="2" style="color:BLACK; table-layout:fixed;">
              <col width="6%"/>
              <col width="20%"/>
              <col width="6%"/>
              <col width="7%"/>
              <col width="10%"/>
              <col width="15%"/>
              <col width="6%"/>
              <col width="15%"/>
              <col width="15%"/>
              <tr>
                <td align="center" class="borderTD">STT</td>
                <td align="center" class="borderTD">Tên hàng hóa, dịch vụ</td>
                <td align="center" class="borderTD">Đơn vị tính</td>
                <td align="center" class="borderTD">Số lượng</td>
                <td align="center" class="borderTD">Đơn giá</td>
                <td align="center" class="borderTD">Thành tiền<br />chưa thuế</td>
                <td align="center" class="borderTD">Thuế suất (%)</td>
                <td align="center" class="borderTD">Tiền thuế</td>
                <td align="center" class="borderTD">Thành tiền<br />có thuế</td>
              </tr>
              <tr>
                <td align="center" class="borderTD">(1)</td>
                <td align="center" class="borderTD">(2)</td>
                <td align="center" class="borderTD">(3)</td>
                <td align="center" class="borderTD">(4)</td>
                <td align="center" class="borderTD">(5)</td>
                <td align="center" class="borderTD">(6)</td>
                <td align="center" class="borderTD">(7)</td>
                <td align="center" class="borderTD">(8)</td>
                <td align="center" class="borderTD">(9)</td>
              </tr>
              <tr>
                <td align="center" class="borderTD_note"><xsl:number format="1"/></td>
                <td align="left" class="borderTD">
                  <xsl:choose>
                    <xsl:when test="(TvanData/Content/Extra/ThongTinHangChung) and (TvanData/Content/Extra/ThongTinHangChung)!=''"><xsl:value-of select="TvanData/Content/Extra/ThongTinHangChung"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
                <td align="center" class="borderTD">&nbsp;</td>
                <td align="center" class="borderTD">&nbsp;</td>
                <td align="center" class="borderTD">&nbsp;</td>
                <td align="right" class="borderTD_note">
                  <xsl:choose>
                    <xsl:when test="(TvanData/Content/Total) and (TvanData/Content/Total)!=''"><xsl:value-of select="format-number(TvanData/Content/Total, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
                <td align="center" class="borderTD">&nbsp;</td>
                <td align="right" class="borderTD_note">
                  <xsl:choose>
                    <xsl:when test="(TvanData/Content/VAT_Amount) and (TvanData/Content/VAT_Amount)!=''"><xsl:value-of select="format-number(TvanData/Content/VAT_Amount, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
                <td align="right" class="borderTD_note">
                  <xsl:choose>
                    <xsl:when test="(TvanData/Content/Amount) and (TvanData/Content/Amount)!=''"><xsl:value-of select="format-number(TvanData/Content/Amount, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
              </tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr>
                <td align="right" colspan="5" class="borderTD"><b>Tổng cộng:</b></td>
                <td align="right" class="borderTD_note"><b>
                  <xsl:choose>
                    <xsl:when test="(TvanData/Content/Total) and (TvanData/Content/Total)!=''"><xsl:value-of select="format-number(TvanData/Content/Total, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </b></td>
                <td align="center" class="borderTD">&nbsp;</td>
                <td align="right" class="borderTD_note"><b>
                  <xsl:choose>
                    <xsl:when test="(TvanData/Content/VAT_Amount) and (TvanData/Content/VAT_Amount)!=''"><xsl:value-of select="format-number(TvanData/Content/VAT_Amount, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </b></td>
                <td align="right" class="borderTD_note"><b>
                  <xsl:choose>
                    <xsl:when test="(TvanData/Content/Amount) and (TvanData/Content/Amount)!=''"><xsl:value-of select="format-number(TvanData/Content/Amount, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </b></td>
              </tr>
            </table>
          </xsl:when>
          <!-- TRƯỜNG HỢP 3: CHI TIẾT HÓA ĐƠN KHÔNG CÓ BẢNG KÊ-->  
          <xsl:otherwise>
            <table cellspacing="0" cellpadding="2" style="color:BLACK; table-layout:fixed;">
              <col width="6%"/>
              <col width="20%"/>
              <col width="6%"/>
              <col width="7%"/>
              <col width="10%"/>
              <col width="15%"/>
              <col width="6%"/>
              <col width="15%"/>
              <col width="15%"/>
              <tr>
                <td align="center" class="borderTD">STT</td>
                <td align="center" class="borderTD">Tên hàng hóa, dịch vụ</td>
                <td align="center" class="borderTD">Đơn vị tính</td>
                <td align="center" class="borderTD">Số lượng</td>
                <td align="center" class="borderTD">Đơn giá</td>
                <td align="center" class="borderTD">Thành tiền<br />chưa thuế</td>
                <td align="center" class="borderTD">Thuế suất (%)</td>
                <td align="center" class="borderTD">Tiền thuế</td>
                <td align="center" class="borderTD">Thành tiền<br />có thuế</td>
              </tr>
              <tr>
                <td align="center" class="borderTD">(1)</td>
                <td align="center" class="borderTD">(2)</td>
                <td align="center" class="borderTD">(3)</td>
                <td align="center" class="borderTD">(4)</td>
                <td align="center" class="borderTD">(5)</td>
                <td align="center" class="borderTD">(6)</td>
                <td align="center" class="borderTD">(7)</td>
                <td align="center" class="borderTD">(8)</td>
                <td align="center" class="borderTD">(9)</td>
              </tr>
              <xsl:for-each select="TvanData/Content/Products/Product">
              <tr>
                <td align="center" class="borderTD_note"><xsl:number format="1"/></td>
                <td align="left" class="borderTD">
                  <xsl:choose>
                    <xsl:when test="(ProdName) and (ProdName)!=''"><xsl:value-of select="ProdName"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
                <td align="center" class="borderTD">
                  <xsl:choose>
                    <xsl:when test="(ProdUnit) and (ProdUnit)!=''"><xsl:value-of select="ProdUnit"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
                <td align="right" class="borderTD_note">
                  <xsl:choose>
                    <xsl:when test="(ProdQuantity) and (ProdQuantity)!='' and (ProdQuantity)!=0"><xsl:value-of select="format-number(ProdQuantity, '#.##0,####', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
                <td align="right" class="borderTD_note">
                  <xsl:choose>
                    <xsl:when test="(ProdPrice) and (ProdPrice)!='' and (ProdPrice)!=0"><xsl:value-of select="format-number(ProdPrice, '#.##0,####', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
                <td align="right" class="borderTD_note">
                  <xsl:choose>
                    <xsl:when test="(Total) and (Total)!='' and (Total)!=0"><xsl:value-of select="format-number(Total, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
                <td align="center" class="borderTD_note">
                  <xsl:choose>
                    <xsl:when test="(VATRate) and (VATRate)!='' and number(VATRate)!=(VATRate)"><xsl:value-of select="VATRate"/></xsl:when>
                    <xsl:when test="(VATRate) and (VATRate)!='' and (VATRate)!=-1 and (VATRate)!=-2"><xsl:value-of select="format-number(VATRate, '#.##0,###', 'vndong')"/></xsl:when>
                    <xsl:when test="(VATRate) and (VATRate)=0">0</xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
                <td align="right" class="borderTD_note">
                  <xsl:choose>
                    <xsl:when test="(VATAmount) and (VATAmount)!='' and (VATAmount)!=0"><xsl:value-of select="format-number(VATAmount, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
                <td align="right" class="borderTD_note">
                  <xsl:choose>
                    <xsl:when test="(Amount) and (Amount)!='' and (Amount)!=0"><xsl:value-of select="format-number(Amount, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
              </tr>
              </xsl:for-each>
              <xsl:if test="count(TvanData/Content/Products/Product)=0 or not(TvanData/Content/Products/Product)">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(TvanData/Content/Products/Product) and count(TvanData/Content/Products/Product)=1">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(TvanData/Content/Products/Product) and count(TvanData/Content/Products/Product)=2">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(TvanData/Content/Products/Product) and count(TvanData/Content/Products/Product)=3">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(TvanData/Content/Products/Product) and count(TvanData/Content/Products/Product)=4">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(TvanData/Content/Products/Product) and count(TvanData/Content/Products/Product)=5">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(TvanData/Content/Products/Product) and count(TvanData/Content/Products/Product)=6">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(TvanData/Content/Products/Product) and count(TvanData/Content/Products/Product)=7">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(TvanData/Content/Products/Product) and count(TvanData/Content/Products/Product)=8">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(TvanData/Content/Products/Product) and count(TvanData/Content/Products/Product)=9">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(TvanData/Content/Products/Product) and count(TvanData/Content/Products/Product)=10">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(TvanData/Content/Products/Product) and count(TvanData/Content/Products/Product)=11">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(TvanData/Content/Products/Product) and count(TvanData/Content/Products/Product)=12">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(TvanData/Content/Products/Product) and count(TvanData/Content/Products/Product)=13">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(TvanData/Content/Products/Product) and count(TvanData/Content/Products/Product)=14">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <tr>
                <td align="right" colspan="5" class="borderTD"><b>Tổng cộng:</b></td>
                <td align="right" class="borderTD_note"><b>
                  <xsl:choose>
                    <xsl:when test="(TvanData/Content/Total) and (TvanData/Content/Total)!=''"><xsl:value-of select="format-number(TvanData/Content/Total, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </b></td>
                <td class="borderTD">&nbsp;</td>
                <td align="right" class="borderTD_note"><b>
                  <xsl:choose>
                    <xsl:when test="(TvanData/Content/VAT_Amount) and (TvanData/Content/VAT_Amount)!=''"><xsl:value-of select="format-number(TvanData/Content/VAT_Amount, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </b></td>
                <td align="right" class="borderTD_note"><b>
                  <xsl:choose>
                    <xsl:when test="(TvanData/Content/Amount) and (TvanData/Content/Amount)!=''"><xsl:value-of select="format-number(TvanData/Content/Amount, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </b></td>
              </tr>
            </table>
          </xsl:otherwise>
        </xsl:choose>
        
        <!-- Số tiền bằng chữ -->
        <xsl:copy-of select="$SoTienBangChu"/>
        
        <!-- Thông tin CKS -->
        <xsl:copy-of select="$CKS"/>
        
        <!-- Ghi chú -->
        <xsl:copy-of select="$GhiChu"/>
      
      <xsl:if test="(TvanData/Content/Extra/SoBangKe) and (TvanData/Content/Extra/SoBangKe)!='' and (TvanData/Content/Extra/SoBangKe)!=0">
        <div class="pagebreak"><br class="pagebreak" /></div>
        
        <!-- Đưa vào Bảng kê -->
        <xsl:copy-of select="$BangKe"/>
        
        <!-- Thông tin CKS -->
        <xsl:copy-of select="$CKS"/>
      </xsl:if>
      </div>
      </body>
    </html>
  </xsl:template>

  <!-- Đưa vào Hình HĐ mẫu -->
  <xsl:variable name="HinhHDMau">
    <div style="position:absolute; z-index:100; margin:430px 0px 0px 250px;"><img src="data:image/png;base64,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"/></div>
  </xsl:variable>

  <!-- Đưa vào Thông tin úng dụng XuatHoaDon™ -->
  <xsl:variable name="UngDungXHD">
    <div style="position:absolute; z-index:100; margin:250px 0px 0px 710px;"><img src="data:image/png;base64,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"/></div>
  </xsl:variable>
  
  <!-- Đưa vào Hình hóa đơn xóa bỏ -->
  <xsl:variable name="HinhHDXoaBo">
    <div style="position:absolute; z-index:100; margin:-25px 0px 0px 0px;"><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAArwAAAH0CAYAAADfWf7fAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoV2luZG93cykiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6MDAyMkY1OTlDNUMzMTFFNjhBQUNGOTM4MkJEQkY0RkIiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6MDAyMkY1OUFDNUMzMTFFNjhBQUNGOTM4MkJEQkY0RkIiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDowMDIyRjU5N0M1QzMxMUU2OEFBQ0Y5MzgyQkRCRjRGQiIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDowMDIyRjU5OEM1QzMxMUU2OEFBQ0Y5MzgyQkRCRjRGQiIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PgH4K2YAAGgjSURBVHja7d17uJVl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"/></div>
  </xsl:variable>

  <!-- Đưa vào Mẫu số HĐ và Tiêu đề -->
  <xsl:variable name="MauSovaTieuDe">
    <table style="table-layout:auto">
      <col width="20%" />
      <col width="60%" />
      <col width="20%" />
      <tr>
        <td align="center" valign="middle" style="max-width:200px; max-height:150px">
          <xsl:choose>
            <xsl:when test="(/Invoice/TvanData/Content/Extra/Logo) and (/Invoice/TvanData/Content/Extra/Logo!='')"><img src='data:image/png;base64,{/Invoice/TvanData/Content/Extra/Logo}' /></xsl:when>
            <xsl:otherwise>&nbsp;</xsl:otherwise>
          </xsl:choose>
        </td>
        <td align="center" valign="middle">
          <span style="font-size:16pt">
          <b>HÓA ĐƠN GIÁ TRỊ GIA TĂNG</b>
          </span>
          <br />
          <xsl:choose>
            <xsl:when test="((/Invoice/TvanData/Content/Extra/NguoiChuyenDoi) and (/Invoice/TvanData/Content/Extra/NguoiChuyenDoi)!='') and ((/Invoice/TvanData/Content/Extra/LoaiHDView) and (/Invoice/TvanData/Content/Extra/LoaiHDView=1))">(HOÁ ĐƠN CHUYỂN ĐỔI TỪ HOÁ ĐƠN XÁC THỰC)</xsl:when>
            <xsl:when test="((/Invoice/TvanData/Content/Extra/NguoiChuyenDoi) and (/Invoice/TvanData/Content/Extra/NguoiChuyenDoi)!='') and (not(/Invoice/TvanData/Content/Extra/LoaiHDView) or (/Invoice/TvanData/Content/Extra/LoaiHDView=0))">(HOÁ ĐƠN CHUYỂN ĐỔI TỪ HOÁ ĐƠN ĐIỆN TỬ)</xsl:when>
            <xsl:otherwise><span id="inHD"></span></xsl:otherwise>
          </xsl:choose>
        </td>
        <td align="left" valign="top" style="font-size:9pt; white-space:nowrap;">
          Mẫu số: <xsl:value-of select="/Invoice/TvanData/Content/InvoicePattern"/><br />
          Ký hiệu: <xsl:value-of select="/Invoice/TvanData/Content/SerialNo"/><br />
          Số: <span style="font-size:15pt"><b><xsl:value-of select="/Invoice/TvanData/Content/InvoiceNo"/></b></span>
        </td>
      </tr>
    </table>
  </xsl:variable>
  
  <!-- Đưa vào Thông tin Người bán và Người mua -->
  <xsl:variable name="TTNguoiBanvaNguoiMua">
    <table>
      <col width="30%" />
      <col width="20%" />
      <col width="35%" />
      <col width="15%" />
      <tr>
        <td colspan="3" align="left" valign="middle"><b><xsl:value-of select="translate(/Invoice/TvanData/Content/ComName,$lower,$upper)"/></b></td>
        <td rowspan="4" align="right" valign="middle" style="z-index:-1; margin-right:5px;">
          <xsl:choose>
            <xsl:when test="(/Invoice/qrCodeData) and (/Invoice/qrCodeData!='')"><img style="max-height:90px; max-width:90px;" src='data:image/png;base64,{/Invoice/qrCodeData}' /></xsl:when>
            <xsl:otherwise>&nbsp;</xsl:otherwise>
          </xsl:choose>
        </td>
      </tr>
      <tr>
        <td colspan="3" align="left" valign="top">Địa chỉ: <xsl:value-of select="/Invoice/TvanData/Content/ComAddress"/></td>
      </tr>
      <tr>
        <td align="left" valign="top">MST: <xsl:value-of select="/Invoice/TvanData/Content/ComTaxCode"/></td>
        <td align="left" valign="top">Tel: <xsl:value-of select="/Invoice/TvanData/Content/ComPhone"/></td>
        <td align="left" valign="top">Email: <xsl:value-of select="/Invoice/TvanData/Content/Extra/EmailNguoiBan"/></td>
      </tr>
      <tr>
        <td align="left" valign="top">Số tài khoản: <xsl:value-of select="/Invoice/TvanData/Content/ComBankNo"/></td>
        <td colspan="2" align="left" valign="top">Tại ngân hàng: <xsl:value-of select="/Invoice/TvanData/Content/ComBankName"/></td>
      </tr>
    </table>
    
    <table>
      <col width="35%" />
      <col width="30%" />
      <col width="25%" />
      <col width="10%" />
      <xsl:choose>
        <xsl:when test="(/Invoice/TvanData/Content/Extra/MauIn) and (/Invoice/TvanData/Content/Extra/MauIn!='') and (substring-after(/Invoice/TvanData/Content/Extra/MauIn,'_')='41VNPT')">
      <tr>
        <td colspan="2" align="left" valign="top" style="border-top:1px solid #000">Họ tên khách hàng: <xsl:value-of select="/Invoice/TvanData/Content/Buyer"/></td>
        <td colspan="2" align="left" valign="top" style="border-top:1px solid #000">Mã nhận hóa đơn: <xsl:value-of select="/Invoice/TvanData/Content/Extra/MaNhanHoaDon"/></td>
      </tr>
        </xsl:when>
        <xsl:otherwise>
      <tr>
        <td colspan="4" align="left" valign="top" style="border-top:1px solid #000">Họ tên khách hàng: <xsl:value-of select="/Invoice/TvanData/Content/Buyer"/></td>
      </tr>
        </xsl:otherwise>
      </xsl:choose>
      <tr>
        <td colspan="4" align="left" valign="top">Tên đơn vị: <xsl:value-of select="/Invoice/TvanData/Content/CusName"/></td>
      </tr>
      <tr>
        <td colspan="4" align="left" valign="top">Địa chỉ: <xsl:value-of select="/Invoice/TvanData/Content/CusAddress"/></td>
      </tr>
      <tr>
        <td align="left" valign="top">MST: <xsl:value-of select="/Invoice/TvanData/Content/CusTaxCode"/></td>
        <td align="left" valign="top">Tel: <xsl:value-of select="/Invoice/TvanData/Content/CusPhone"/></td>
        <td colspan="2" align="left" valign="top">Email: <xsl:value-of select="/Invoice/TvanData/Content/Extra/EmailNguoiMua"/></td>
      </tr>
      <tr>
        <td align="left" valign="top">Số tài khoản: <xsl:value-of select="/Invoice/TvanData/Content/CusBankNo"/></td>
        <td colspan="3" align="left" valign="top">Tại ngân hàng: <xsl:value-of select="/Invoice/TvanData/Content/CusBankName"/></td>
      </tr>
      <tr>
        <td colspan="2" align="left" valign="top">Hình thức thanh toán: <xsl:value-of select="/Invoice/TvanData/Content/Kind_of_Payment"/></td>
        <td colspan="2" align="left" valign="top">Loại tiền: <xsl:value-of select="/Invoice/TvanData/Content/Extra/currencyCode"/></td>
      </tr>
    </table>
  </xsl:variable>
  
  <!-- Đưa vào Số tiền bằng chữ -->
  <xsl:variable name="SoTienBangChu">
    <table>
      <tr>
        <td align="left">Số tiền bằng chữ: 
          <xsl:choose>
            <xsl:when test="/Invoice/TvanData/Content/Amount_words!=''"><xsl:value-of select="/Invoice/TvanData/Content/Amount_words"/></xsl:when>
            <xsl:otherwise>&nbsp;</xsl:otherwise>
          </xsl:choose>
        </td>
      </tr>
    </table>
  </xsl:variable>

  <!-- Đưa vào Thông tin CKS -->
  <xsl:variable name="CKS">
    <table style="table-layout:auto">
      <col width="30%"/>
      <col width="40%"/>
      <col width="30%"/>
      <tr>
        <td align="center" valign="bottom">
          Người mua hàng
        </td>
        <td align="left" valign="middle">
          <xsl:choose>
            <xsl:when test="(/Invoice/TvanData/Content/Extra/NguoiChuyenDoi) and (/Invoice/TvanData/Content/Extra/NguoiChuyenDoi)!=''">Người chuyển đổi: <xsl:value-of select="/Invoice/TvanData/Content/Extra/NguoiChuyenDoi" /></xsl:when>
            <xsl:otherwise><span id="nguoiCD"></span></xsl:otherwise>
          </xsl:choose>
          <br />
          <xsl:choose>
            <xsl:when test="(/Invoice/TvanData/Content/Extra/NgayChuyenDoi) and (/Invoice/TvanData/Content/Extra/NgayChuyenDoi)!='' and substring(/Invoice/TvanData/Content/Extra/NgayChuyenDoi,5,1)='-'">Ngày chuyển đổi: <xsl:value-of select="concat(substring(/Invoice/TvanData/Content/Extra/NgayChuyenDoi,9,2),'/',substring(/Invoice/TvanData/Content/Extra/NgayChuyenDoi,6,2),'/',substring(/Invoice/TvanData/Content/Extra/NgayChuyenDoi,1,4))" /></xsl:when>
            <xsl:when test="(/Invoice/TvanData/Content/Extra/NgayChuyenDoi) and (/Invoice/TvanData/Content/Extra/NgayChuyenDoi)!='' and substring(/Invoice/TvanData/Content/Extra/NgayChuyenDoi,3,1)='/'">Ngày chuyển đổi: <xsl:value-of select="concat(substring(/Invoice/TvanData/Content/Extra/NgayChuyenDoi,1,2),'/',substring(/Invoice/TvanData/Content/Extra/NgayChuyenDoi,4,2),'/',substring(/Invoice/TvanData/Content/Extra/NgayChuyenDoi,7,4))" /></xsl:when>
            <xsl:otherwise><span id="ngayCD"></span></xsl:otherwise>
          </xsl:choose>
        </td>
        <td align="center" valign="bottom">
          <xsl:choose>
            <xsl:when test="(/Invoice/TvanData/Content/ArisingDate) and (/Invoice/TvanData/Content/ArisingDate)!='' and substring(/Invoice/TvanData/Content/ArisingDate,5,1)='-'"><i>Ngày <xsl:value-of select="substring(/Invoice/TvanData/Content/ArisingDate,9,2)" /> tháng <xsl:value-of select="substring(/Invoice/TvanData/Content/ArisingDate,6,2)" /> năm <xsl:value-of select="substring(/Invoice/TvanData/Content/ArisingDate,1,4)" /></i></xsl:when>
            <xsl:when test="(/Invoice/TvanData/Content/ArisingDate) and (/Invoice/TvanData/Content/ArisingDate)!='' and substring(/Invoice/TvanData/Content/ArisingDate,3,1)='/'"><i>Ngày <xsl:value-of select="substring(/Invoice/TvanData/Content/ArisingDate,1,2)" /> tháng <xsl:value-of select="substring(/Invoice/TvanData/Content/ArisingDate,4,2)" /> năm <xsl:value-of select="substring(/Invoice/TvanData/Content/ArisingDate,7,4)" /></i></xsl:when>
            <xsl:otherwise><i>Ngày <span style="margin-left:10px">&nbsp;</span> tháng <span style="margin-left:10px">&nbsp;</span> năm <span style="margin-left:30px">&nbsp;</span></i></xsl:otherwise>
          </xsl:choose><br />
          Người bán hàng
        </td>
      </tr>
      <tr><td colspan="3">&nbsp;</td></tr>
      <tr><td colspan="3">&nbsp;</td></tr>
      <tr><td colspan="3">&nbsp;</td></tr>
      <tr><td colspan="3">&nbsp;</td></tr>
      <tr><td colspan="3">&nbsp;</td></tr>
      <xsl:if test="(not(/Invoice/image) or (/Invoice/image)='') and (not(/Invoice/imageTVAN) or (/Invoice/imageTVAN)='')">
      <tr><td colspan="3">&nbsp;</td></tr>
      <tr><td colspan="3">&nbsp;</td></tr>
      </xsl:if>
      <xsl:if test="((/Invoice/image) and (/Invoice/image)!='') or ((/Invoice/imageTVAN) and (/Invoice/imageTVAN)!='')">
      <tr>
        <td align="center" valign="top">
          <xsl:value-of select="/Invoice/TvanData/Content/Buyer"/>
        </td>
        <td align="center" valign="middle" style="white-space:nowrap; color:#000;">
          <xsl:if test="((/Invoice/imageTVAN) and (/Invoice/imageTVAN)!='')">
          <div style="position:relative; z-index:1; font-size:9pt;">
          <div style="height:72px; background-image: url('data:image/png;base64,{/Invoice/imageTVAN/@URI}'); background-repeat:no-repeat; background-position:center center; position:absolute; z-index:-1; top:-10px; bottom:0; right:0; left:0; opacity:0.7; filter: alpha(opacity=70);"></div>
          Tình trạng chữ ký: <xsl:value-of select="/Invoice/imageTVAN"/><br />
          Ký bởi: <xsl:value-of select="/Invoice/CNTVAN"/><br />
          Ký ngày: <xsl:value-of select="/Invoice/TvanData/Date"/>
          </div>
          </xsl:if>
        </td>
        <td align="center" valign="middle" style="white-space:nowrap; color:#000;">
          <xsl:if test="((/Invoice/image) and (/Invoice/image)!='')">
          <div style="position:relative; z-index:1; font-size:9pt;">
          <div style="height:72px; background-image: url('data:image/png;base64,{/Invoice/image/@URI}'); background-repeat:no-repeat; background-position:center center; position:absolute; z-index:-1; top:-10px; bottom:0; right:0; left:0; opacity:0.7; filter: alpha(opacity=70);"></div>
          Tình trạng chữ ký: <xsl:value-of select="/Invoice/image"/><br />
          Ký bởi: <xsl:value-of select="/Invoice/CNCom"/><br />
          Ký ngày: <xsl:value-of select="/Invoice/TvanData/Content/SignDate"/>
          </div>
          </xsl:if>
        </td>
      </tr>
      </xsl:if>
      <tr><td colspan="3">&nbsp;</td></tr>
      <tr><td colspan="3">&nbsp;</td></tr>
    </table>
  </xsl:variable>
  
  <!-- Đưa vào Ghi chú -->
  <xsl:variable name="GhiChu">
    <table style="color:BLACK">
      <xsl:if test="(/Invoice/TvanData/Content/Extra/HoaDonThayThe) and (/Invoice/TvanData/Content/Extra/HoaDonThayThe!=7)">
      <tr>
        <td style="font-size:9pt; text-align:center;"><xsl:value-of select="/Invoice/TvanData/Content/Extra/HoaDonThayThe"/></td>
      </tr>
      </xsl:if>
      <tr>
      <xsl:choose>
        <xsl:when test="(/Invoice/TvanData/Content/Extra/LoaiHDView) and (/Invoice/TvanData/Content/Extra/LoaiHDView=1)">
        <td style="font-size:8pt; text-align:center; border-top: 1px solid #000;">
          <i><b>
            (Cần kiểm tra, đối chiếu khi lập, giao, nhận hóa đơn)<br />
            Hóa đơn điện tử được cấp mã xác thực bởi Tổng cục Thuế Việt Nam
          </b></i>
        </td>
        </xsl:when>
        <xsl:otherwise>
        <td style="font-size:8pt; text-align:justify; border-top: 1px solid #000;">
          <i><b><u>Ghi chú:</u></b> Người mua được chuyển đổi hóa đơn điện tử sang hóa đơn giấy để phục vụ việc lưu trữ chứng từ kế toán theo quy định của Luật Kế toán. Hóa đơn chuyển đổi từ hóa đơn điện tử sang hóa đơn dạng giấy bao gồm đầy đủ các thông tin sau: dòng chữ phân biệt giữa hóa đơn  chuyển đổi và hóa đơn điện tử gốc – hóa đơn nguồn (ghi rõ "HOÁ ĐƠN CHUYỂN ĐỔI TỪ HOÁ ĐƠN ĐIỆN TỬ"); họ và tên, chữ ký của người được thực hiện chuyển đổi; thời gian thực hiện chuyển đổi.</i>
        </td>
        </xsl:otherwise>
      </xsl:choose>
      </tr>
    </table>
  </xsl:variable>
  
  <!-- Đưa vào Bảng kê -->
  <xsl:variable name="BangKe">
    <table>
      <col width="35%" />
      <col width="30%" />
      <col width="35%" />
      <tr>
        <td colspan="3" align="center" valign="middle">
          <span style="font-size:16pt">
          <b>BẢNG KÊ CHI TIẾT HÀNG HÓA, DỊCH VỤ (Bảng kê số: <xsl:value-of select="/Invoice/TvanData/Content/Extra/SoBangKe"/>)</b>
          </span>
          <br />
          <i>
          (Kèm theo hóa đơn số <span style="font-size:13pt"><b><xsl:value-of select="/Invoice/TvanData/Content/InvoiceNo"/></b>&nbsp;</span>
          <xsl:choose>
            <xsl:when test="(/Invoice/TvanData/Content/ArisingDate) and (/Invoice/TvanData/Content/ArisingDate)!='' and substring(/Invoice/TvanData/Content/ArisingDate,5,1)='-'">ngày <xsl:value-of select="substring(/Invoice/TvanData/Content/ArisingDate,9,2)" /> tháng <xsl:value-of select="substring(/Invoice/TvanData/Content/ArisingDate,6,2)" /> năm <xsl:value-of select="substring(/Invoice/TvanData/Content/ArisingDate,1,4)" />)</xsl:when>
            <xsl:when test="(/Invoice/TvanData/Content/ArisingDate) and (/Invoice/TvanData/Content/ArisingDate)!='' and substring(/Invoice/TvanData/Content/ArisingDate,3,1)='/'">ngày <xsl:value-of select="substring(/Invoice/TvanData/Content/ArisingDate,1,2)" /> tháng <xsl:value-of select="substring(/Invoice/TvanData/Content/ArisingDate,4,2)" /> năm <xsl:value-of select="substring(/Invoice/TvanData/Content/ArisingDate,7,4)" />)</xsl:when>
            <xsl:otherwise>ngày <span style="margin-left:10px">&nbsp;</span> tháng <span style="margin-left:10px">&nbsp;</span> năm <span style="margin-left:30px">&nbsp;</span>)</xsl:otherwise>
          </xsl:choose>
          </i>
        </td>
      </tr>
      <tr><td colspan="3">&nbsp;</td></tr>
      <tr>
        <td colspan="3" align="left" valign="middle"><b><xsl:value-of select="translate(/Invoice/TvanData/Content/ComName,$lower,$upper)"/></b></td>
      </tr>
      <tr>
        <td colspan="3" align="left" valign="top">Địa chỉ: <xsl:value-of select="/Invoice/TvanData/Content/ComAddress"/></td>
      </tr>
      <tr>
        <td align="left" valign="top">MST: <xsl:value-of select="/Invoice/TvanData/Content/ComTaxCode"/></td>
        <td align="left" valign="top">Tel: <xsl:value-of select="/Invoice/TvanData/Content/ComPhone"/></td>
        <td align="left" valign="top">Email: <xsl:value-of select="/Invoice/TvanData/Content/Extra/EmailNguoiBan"/></td>
      </tr>
    </table>
    
    <!-- Thông tin chi tiết hóa đơn (Hàng hóa, dịch vụ) -->
    <table cellspacing="0" cellpadding="2" style="color:BLACK; table-layout:fixed;">
      <col width="6%"/>
      <col width="20%"/>
      <col width="6%"/>
      <col width="7%"/>
      <col width="10%"/>
      <col width="15%"/>
      <col width="6%"/>
      <col width="15%"/>
      <col width="15%"/>
      <tr>
        <td align="center" class="borderTD">STT</td>
        <td align="center" class="borderTD">Tên hàng hóa, dịch vụ</td>
        <td align="center" class="borderTD">Đơn vị tính</td>
        <td align="center" class="borderTD">Số lượng</td>
        <td align="center" class="borderTD">Đơn giá</td>
        <td align="center" class="borderTD">Thành tiền<br />chưa thuế</td>
        <td align="center" class="borderTD">Thuế suất (%)</td>
        <td align="center" class="borderTD">Tiền thuế</td>
        <td align="center" class="borderTD">Thành tiền <br />có thuế</td>
      </tr>
      <tr>
        <td align="center" class="borderTD">(1)</td>
        <td align="center" class="borderTD">(2)</td>
        <td align="center" class="borderTD">(3)</td>
        <td align="center" class="borderTD">(4)</td>
        <td align="center" class="borderTD">(5)</td>
        <td align="center" class="borderTD">(6)</td>
        <td align="center" class="borderTD">(7)</td>
        <td align="center" class="borderTD">(8)</td>
        <td align="center" class="borderTD">(9)</td>
      </tr>
      <xsl:for-each select="/Invoice/TvanData/Content/Products/Product">
      <tr>
        <td align="center" class="borderTD_note"><xsl:number format="1"/></td>
        <td align="left" class="borderTD">
          <xsl:choose>
            <xsl:when test="(ProdName) and (ProdName)!=''"><xsl:value-of select="ProdName"/></xsl:when>
            <xsl:otherwise>&nbsp;</xsl:otherwise>
          </xsl:choose>
        </td>
        <td align="center" class="borderTD">
          <xsl:choose>
            <xsl:when test="(ProdUnit) and (ProdUnit)!=''"><xsl:value-of select="ProdUnit"/></xsl:when>
            <xsl:otherwise>&nbsp;</xsl:otherwise>
          </xsl:choose>
        </td>
        <td align="right" class="borderTD_note">
          <xsl:choose>
            <xsl:when test="(ProdQuantity) and (ProdQuantity)!='' and (ProdQuantity)!=0"><xsl:value-of select="format-number(ProdQuantity, '#.##0,####', 'vndong')"/></xsl:when>
            <xsl:otherwise>&nbsp;</xsl:otherwise>
          </xsl:choose>
        </td>
        <td align="right" class="borderTD_note">
          <xsl:choose>
            <xsl:when test="(ProdPrice) and (ProdPrice)!='' and (ProdPrice)!=0"><xsl:value-of select="format-number(ProdPrice, '#.##0,####', 'vndong')"/></xsl:when>
            <xsl:otherwise>&nbsp;</xsl:otherwise>
          </xsl:choose>
        </td>
        <td align="right" class="borderTD_note">
          <xsl:choose>
            <xsl:when test="(Total) and (Total)!='' and (Total)!=0"><xsl:value-of select="format-number(Total, '#.##0,##', 'vndong')"/></xsl:when>
            <xsl:otherwise>&nbsp;</xsl:otherwise>
          </xsl:choose>
        </td>
        <td align="center" class="borderTD_note">
          <xsl:choose>
            <xsl:when test="(VATRate) and (VATRate)!='' and number(VATRate)!=(VATRate)"><xsl:value-of select="VATRate"/></xsl:when>
            <xsl:when test="(VATRate) and (VATRate)!='' and (VATRate)!=-1 and (VATRate)!=-2"><xsl:value-of select="format-number(VATRate, '#.##0,###', 'vndong')"/></xsl:when>
            <xsl:when test="(VATRate) and (VATRate)=0">0</xsl:when>
            <xsl:otherwise>&nbsp;</xsl:otherwise>
          </xsl:choose>
        </td>
        <td align="right" class="borderTD_note">
          <xsl:choose>
            <xsl:when test="(VATAmount) and (VATAmount)!='' and (VATAmount)!=0"><xsl:value-of select="format-number(VATAmount, '#.##0,##', 'vndong')"/></xsl:when>
            <xsl:otherwise>&nbsp;</xsl:otherwise>
          </xsl:choose>
        </td>
        <td align="right" class="borderTD_note">
          <xsl:choose>
            <xsl:when test="(Amount) and (Amount)!='' and (Amount)!=0"><xsl:value-of select="format-number(Amount, '#.##0,##', 'vndong')"/></xsl:when>
            <xsl:otherwise>&nbsp;</xsl:otherwise>
          </xsl:choose>
        </td>
      </tr>
      </xsl:for-each>
      <tr>
        <td align="right" colspan="5" class="borderTD"><b>Tổng cộng:</b></td>
        <td align="right" class="borderTD_note"><b>
          <xsl:choose>
            <xsl:when test="(/Invoice/TvanData/Content/Total) and (/Invoice/TvanData/Content/Total)!=''"><xsl:value-of select="format-number(/Invoice/TvanData/Content/Total, '#.##0,##', 'vndong')"/></xsl:when>
            <xsl:otherwise>&nbsp;</xsl:otherwise>
          </xsl:choose>
        </b></td>
        <td class="borderTD">&nbsp;</td>
        <td align="right" class="borderTD_note"><b>
          <xsl:choose>
            <xsl:when test="(/Invoice/TvanData/Content/VAT_Amount) and (/Invoice/TvanData/Content/VAT_Amount)!=''"><xsl:value-of select="format-number(/Invoice/TvanData/Content/VAT_Amount, '#.##0,##', 'vndong')"/></xsl:when>
            <xsl:otherwise>&nbsp;</xsl:otherwise>
          </xsl:choose>
        </b></td>
        <td align="right" class="borderTD_note"><b>
          <xsl:choose>
            <xsl:when test="(/Invoice/TvanData/Content/Amount) and (/Invoice/TvanData/Content/Amount)!=''"><xsl:value-of select="format-number(/Invoice/TvanData/Content/Amount, '#.##0,##', 'vndong')"/></xsl:when>
            <xsl:otherwise>&nbsp;</xsl:otherwise>
          </xsl:choose>
        </b></td>
      </tr>
    </table>
  </xsl:variable>
</xsl:stylesheet>