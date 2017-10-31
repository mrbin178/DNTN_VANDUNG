<?xml version="1.0" encoding="utf-8"?><!-- DWXMLSource="HDDT_GTGT_STIHL_M1.xml" -->
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

  <xsl:template match="inv:invoice">
    <html>
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>HOA_DON_DIEN_TU</title>
      <!-- 
        <script type="text/javascript" src="http://www.ts24.com.vn/web/media/js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="http://www.ts24.com.vn/web/media/js/details.js"></script>
        <script type="text/javascript" src="http://www.ts24.com.vn/web/media/js/qrcode.js"></script>
        <script type="text/javascript" src="http://www.ts24.com.vn/web/media/js/jquery.qrcode.js"></script>
        -->
        <style type="text/css">
          table { empty-cells:show; border: 0; width: 700px; font-size:10pt; font-family:"Times New Roman", Times, serif; color:#0a51a1; border-collapse:collapse; table-layout:fixed }
		  .pagebreak { page-break-after: always; }
          .cell, .half-cell { border: 1px solid #0a51a1; display:inline-block; *display:inline; zoom:1; padding: 0px; }
          .cellformat{ width:14px; height:14px; line-height:14px; text-align:center; }
          .cellformatheight{ height:14px; line-height:14px; text-align:center; }
          .cellformatdetail{ width:20px; height:20px; line-height:20px; text-align:center; }
          .borderTD { border:1px solid #0a51a1; border-collapse:collapse; vertical-align:middle; }
          .borderLR { border:1px solid #0a51a1; border-collapse:collapse; vertical-align:middle; border-bottom:none; border-top:none; }
          .borderTB { border:1px solid #0a51a1; border-collapse:collapse; vertical-align:middle; border-right:none; border-left:none; }
		  .borderTop { border-top:1px solid #0a51a1; border-collapse:collapse; vertical-align:middle; }
		  .borderBottom { border-bottom:1px solid #0a51a1; border-collapse:collapse; vertical-align:middle; }
		  .borderLeft { border-left:1px solid #0a51a1; border-collapse:collapse; vertical-align:middle; }
		  .borderRight { border-right:1px solid #0a51a1; border-collapse:collapse; vertical-align:middle; }
        </style>
      </head>
      
      <body>
        <!-- Tạo Barcode -->
        <!--
        <xsl:choose>
          <xsl:when test="(inv:invoiceData/inv:userDefines/inv:LoaiHDView) and (inv:invoiceData/inv:userDefines/inv:LoaiHDView=1)">
            <input type="hidden" id="qrcodeContent">
             <xsl:attribute name="value">
               <xsl:value-of select="inv:controlData/inv:qrCodeData" />
             </xsl:attribute>
            </input>
          </xsl:when>
          <xsl:otherwise></xsl:otherwise>
        </xsl:choose>
        -->
      <div style="width:700px; margin:0 auto; padding:8px; text-align:center; border:3px double #0a51a1;">
        <!-- Background -->
        <xsl:copy-of select="$Background"/>
  
        <!-- Hình hóa đơn mẫu -->
        <xsl:if test="(inv:invoiceData/inv:invoiceNumber) and (inv:invoiceData/inv:invoiceNumber)='0000000'">
          <xsl:copy-of select="$HinhHDMau"/>
        </xsl:if>

        <!-- Thông tin ứng dụng XuatHoaDon™ -->
        <xsl:copy-of select="$UngDungXHD"/>
        
        <!-- Thông tin Người bán -->
        <xsl:copy-of select="$TTNguoiBan"/>

        <!-- Mẫu số HĐ và Tiêu đề -->
        <xsl:copy-of select="$MauSovaTieuDe"/>
        
        <!-- Thông tin Hóa đơn xác thực và thông tin giao dịch -->
        <xsl:copy-of select="$TTXacThucvaTTGiaoDich"/>

        <!-- Hình hóa đơn xóa bỏ -->
        <xsl:if test="(inv:invoiceData/inv:userDefines/inv:HoaDonThayThe) and (inv:invoiceData/inv:userDefines/inv:HoaDonThayThe)=7">
          <xsl:copy-of select="$HinhHDXoaBo"/>
        </xsl:if>

        <!-- Thông tin chi tiết hóa đơn (Hàng hóa, dịch vụ) -->
        <xsl:choose>
          <!-- TRƯỜNG HỢP 1: NỘP HÓA ĐƠN MẪU --> 
          <xsl:when test="(inv:invoiceData/inv:invoiceNumber) and (inv:invoiceData/inv:invoiceNumber)='0000000'">
            <table cellspacing="0" cellpadding="2" style="table-layout:fixed;">
              <col width="6%"/>
              <col width="12%"/>
              <col width="30%"/>
              <col width="10%"/>
              <col width="12%"/>
              <col width="12%"/>
              <col width="18%"/>
              <tr>
                <td align="center" class="borderTD">
                  STT<br />
                  <i>(No.)</i>
                </td>
                <td align="center" class="borderTD">
                  Mã hàng hóa<br />
                  <i>(Code)</i>
                </td>
                <td align="center" class="borderTD">
                  Tên hàng hóa, dịch vụ<br />
                  <i>(Name of goods, services)</i>
                </td>
                <td align="center" class="borderTD">
                  Đơn vị tính<br />
                  <i>(Unit)</i>
                </td>
                <td align="center" class="borderTD">
                  Số lượng<br />
                  <i>(Quantity)</i>
                </td>
                <td align="center" class="borderTD">
                  Đơn giá<br />
                  <i>(Unit Price)</i>
                </td>
                <td align="center" class="borderTD">
                  Thành tiền<br />
                  <i>(Amount)</i>
                </td>
              </tr>
              <tr>
                <td align="center" class="borderTD">(1)</td>
                <td align="center" class="borderTD">(2)</td>
                <td align="center" class="borderTD">(3)</td>
                <td align="center" class="borderTD">(4)</td>
                <td align="center" class="borderTD">(5)</td>
                <td align="center" class="borderTD">(6)</td>
                <td align="center" class="borderTD">(7) = (5) x (6)</td>
              </tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr>
                <td colspan="6" align="right" class="borderTD">
                  Cộng tiền hàng <i>(Sub total):</i>
                </td>
                <td class="borderTD">&nbsp;</td>
              </tr>
              <tr>
                <td colspan="3" align="left" class="borderTD">
                  Thuế suất GTGT <i>(VAT rate):</i>
                </td>
                <td colspan="3" align="right" class="borderTD">
                  Tiền thuế GTGT <i>(VAT):</i>
                </td>
                <td class="borderTD">&nbsp;</td>
              </tr>
              <tr>
                <td colspan="6" align="right" class="borderTD">
                  Tổng cộng tiền thanh toán <i>(Total of payment):</i>
                </td>
                <td class="borderTD">&nbsp;</td>
              </tr>
            </table>
          </xsl:when>
          <!-- TRƯỜNG HỢP 2: CHI TIẾT HÓA ĐƠN CÓ BẢNG KÊ-->  
          <xsl:when test="(inv:invoiceData/inv:userDefines/inv:SoBangKe) and (inv:invoiceData/inv:userDefines/inv:SoBangKe)!='' and (inv:invoiceData/inv:userDefines/inv:SoBangKe)!=0">
            <table cellspacing="0" cellpadding="2" style="table-layout:fixed;">
              <col width="6%"/>
              <col width="12%"/>
              <col width="30%"/>
              <col width="10%"/>
              <col width="12%"/>
              <col width="12%"/>
              <col width="18%"/>
              <tr>
                <td align="center" class="borderTD">
                  STT<br />
                  <i>(No.)</i>
                </td>
                <td align="center" class="borderTD">
                  Mã hàng hóa<br />
                  <i>(Code)</i>
                </td>
                <td align="center" class="borderTD">
                  Tên hàng hóa, dịch vụ<br />
                  <i>(Name of goods, services)</i>
                </td>
                <td align="center" class="borderTD">
                  Đơn vị tính<br />
                  <i>(Unit)</i>
                </td>
                <td align="center" class="borderTD">
                  Số lượng<br />
                  <i>(Quantity)</i>
                </td>
                <td align="center" class="borderTD">
                  Đơn giá<br />
                  <i>(Unit Price)</i>
                </td>
                <td align="center" class="borderTD">
                  Thành tiền<br />
                  <i>(Amount)</i>
                </td>
              </tr>
              <tr>
                <td align="center" class="borderTD">(1)</td>
                <td align="center" class="borderTD">(2)</td>
                <td align="center" class="borderTD">(3)</td>
                <td align="center" class="borderTD">(4)</td>
                <td align="center" class="borderTD">(5)</td>
                <td align="center" class="borderTD">(6)</td>
                <td align="center" class="borderTD">(7) = (5) x (6)</td>
              </tr>
              <tr>
                <td align="center" class="borderTD"><xsl:number format="1"/></td>
                <td class="borderTD">&nbsp;</td>
                <td align="left" class="borderTD">
                  <xsl:choose>
                    <xsl:when test="(inv:invoiceData/inv:userDefines/inv:ThongTinHangChung) and (inv:invoiceData/inv:userDefines/inv:ThongTinHangChung)!=''"><xsl:value-of select="inv:invoiceData/inv:userDefines/inv:ThongTinHangChung"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
                <td class="borderTD">&nbsp;</td>
                <td class="borderTD">&nbsp;</td>
                <td class="borderTD">&nbsp;</td>
                <td align="right" class="borderTD">
                  <xsl:choose>
                    <xsl:when test="(inv:invoiceData/inv:totalAmountWithoutVAT) and (inv:invoiceData/inv:totalAmountWithoutVAT)!=''"><xsl:value-of select="format-number(inv:invoiceData/inv:totalAmountWithoutVAT, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
              </tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr>
                <td colspan="6" align="right" class="borderTD">
                  Cộng tiền hàng <i>(Sub total):</i>
                </td>
                <td align="right" class="borderTD">
                  <xsl:choose>
                    <xsl:when test="(inv:invoiceData/inv:totalAmountWithoutVAT) and (inv:invoiceData/inv:totalAmountWithoutVAT)!=''"><xsl:value-of select="format-number(inv:invoiceData/inv:totalAmountWithoutVAT, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
              </tr>
              <tr>
                <td colspan="3" align="left" class="borderTD">
                  Thuế suất GTGT <i>(VAT rate):</i>
                  <xsl:choose>
                    <xsl:when test="(inv:invoiceData/inv:userDefines/inv:vatPercentage) and (inv:invoiceData/inv:userDefines/inv:vatPercentage)!=''"><xsl:value-of select="format-number(inv:invoiceData/inv:userDefines/inv:vatPercentage, '#.##0,##', 'vndong')"/>%</xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
                <td colspan="3" align="right" class="borderTD">
                  Tiền thuế GTGT <i>(VAT):</i>
                </td>
                <td align="right" class="borderTD">
                  <xsl:choose>
                    <xsl:when test="(inv:invoiceData/inv:totalVATAmount) and (inv:invoiceData/inv:totalVATAmount)!=''"><xsl:value-of select="format-number(inv:invoiceData/inv:totalVATAmount, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
              </tr>
              <tr>
                <td colspan="6" align="right" class="borderTD">
                  Tổng cộng tiền thanh toán <i>(Total of payment):</i>
                </td>
                <td align="right" class="borderTD">
                  <xsl:choose>
                    <xsl:when test="(inv:invoiceData/inv:totalAmountWithVAT) and (inv:invoiceData/inv:totalAmountWithVAT)!=''"><xsl:value-of select="format-number(inv:invoiceData/inv:totalAmountWithVAT, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
              </tr>
            </table>
          </xsl:when>
          <!-- TRƯỜNG HỢP 3: CHI TIẾT HÓA ĐƠN KHÔNG CÓ BẢNG KÊ-->  
          <xsl:otherwise>
            <table cellspacing="0" cellpadding="2" style="table-layout:fixed;">
              <col width="6%"/>
              <col width="12%"/>
              <col width="30%"/>
              <col width="10%"/>
              <col width="12%"/>
              <col width="12%"/>
              <col width="18%"/>
              <tr>
                <td align="center" class="borderTD">
                  STT<br />
                  <i>(No.)</i>
                </td>
                <td align="center" class="borderTD">
                  Mã hàng hóa<br />
                  <i>(Code)</i>
                </td>
                <td align="center" class="borderTD">
                  Tên hàng hóa, dịch vụ<br />
                  <i>(Name of goods, services)</i>
                </td>
                <td align="center" class="borderTD">
                  Đơn vị tính<br />
                  <i>(Unit)</i>
                </td>
                <td align="center" class="borderTD">
                  Số lượng<br />
                  <i>(Quantity)</i>
                </td>
                <td align="center" class="borderTD">
                  Đơn giá<br />
                  <i>(Unit Price)</i>
                </td>
                <td align="center" class="borderTD">
                  Thành tiền<br />
                  <i>(Amount)</i>
                </td>
              </tr>
              <tr>
                <td align="center" class="borderTD">(1)</td>
                <td align="center" class="borderTD">(2)</td>
                <td align="center" class="borderTD">(3)</td>
                <td align="center" class="borderTD">(4)</td>
                <td align="center" class="borderTD">(5)</td>
                <td align="center" class="borderTD">(6)</td>
                <td align="center" class="borderTD">(7) = (5) x (6)</td>
              </tr>
              <xsl:for-each select="inv:invoiceData/inv:items/inv:item">
              <tr>
                <td align="center" class="borderTD"><xsl:number format="1"/></td>
                <td align="center" class="borderTD">
                  <xsl:choose>
                    <xsl:when test="(inv:itemCode) and (inv:itemCode)!=''"><xsl:value-of select="inv:itemCode"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
                <td align="left" class="borderTD">
                  <xsl:choose>
                    <xsl:when test="(inv:itemName) and (inv:itemName)!=''"><xsl:value-of select="inv:itemName"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
                <td align="center" class="borderTD">
                  <xsl:choose>
                    <xsl:when test="(inv:unitName) and (inv:unitName)!=''"><xsl:value-of select="inv:unitName"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
                <td align="right" class="borderTD">
                  <xsl:choose>
                    <xsl:when test="(inv:quantity) and (inv:quantity)!='' and (inv:quantity)!=0"><xsl:value-of select="format-number(inv:quantity, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
                <td align="right" class="borderTD">
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
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=1">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=2">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=3">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=4">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=5">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=6">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=7">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=8">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=9">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <tr>
                <td colspan="6" align="right" class="borderTD">
                  Cộng tiền hàng <i>(Sub total):</i>
                </td>
                <td align="right" class="borderTD">
                  <xsl:choose>
                    <xsl:when test="(inv:invoiceData/inv:totalAmountWithoutVAT) and (inv:invoiceData/inv:totalAmountWithoutVAT)!=''"><xsl:value-of select="format-number(inv:invoiceData/inv:totalAmountWithoutVAT, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
              </tr>
              <tr>
                <td colspan="3" align="left" class="borderTD">
                  Thuế suất GTGT <i>(VAT rate):</i>
                  <xsl:choose>
                    <xsl:when test="(inv:invoiceData/inv:userDefines/inv:vatPercentage) and (inv:invoiceData/inv:userDefines/inv:vatPercentage)!=''"><xsl:value-of select="format-number(inv:invoiceData/inv:userDefines/inv:vatPercentage, '#.##0,##', 'vndong')"/>%</xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
                <td colspan="3" align="right" class="borderTD">
                  Tiền thuế GTGT <i>(VAT):</i>
                </td>
                <td align="right" class="borderTD">
                  <xsl:choose>
                    <xsl:when test="(inv:invoiceData/inv:totalVATAmount) and (inv:invoiceData/inv:totalVATAmount)!=''"><xsl:value-of select="format-number(inv:invoiceData/inv:totalVATAmount, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
              </tr>
              <tr>
                <td colspan="6" align="right" class="borderTD">
                  Tổng cộng tiền thanh toán <i>(Total of payment):</i>
                </td>
                <td align="right" class="borderTD">
                  <xsl:choose>
                    <xsl:when test="(inv:invoiceData/inv:totalAmountWithVAT) and (inv:invoiceData/inv:totalAmountWithVAT)!=''"><xsl:value-of select="format-number(inv:invoiceData/inv:totalAmountWithVAT, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
              </tr>
            </table>
          </xsl:otherwise>
        </xsl:choose>

        <!-- Số tiền bằng chữ -->
        <xsl:copy-of select="$SoTienBangChu"/>
        
        <!-- Thông tin Ký HĐ -->
        <xsl:copy-of select="$TTKyHD"/>
        
        <!-- Thông tin CKS -->
        <xsl:copy-of select="$CKS"/>
        
        <!-- Ghi chú -->
        <xsl:copy-of select="$GhiChu"/>
      </div>

      <xsl:if test="(inv:invoiceData/inv:userDefines/inv:SoBangKe) and (inv:invoiceData/inv:userDefines/inv:SoBangKe)!='' and (inv:invoiceData/inv:userDefines/inv:SoBangKe)!=0">
        <div class="pagebreak"><br class="pagebreak" /></div>

        <div style="width:700px; margin:0 auto; padding:8px; text-align:center; border:3px double #0a51a1;">
          <!-- Đưa vào Bảng kê -->
          <xsl:copy-of select="$BangKe"/>
          
          <!-- Thông tin CKS -->
          <xsl:copy-of select="$CKS"/>
        </div>
      </xsl:if>
      </body>
    </html>
  </xsl:template>

  <!-- Đưa vào Hình HĐ mẫu -->
  <xsl:variable name="HinhHDMau">
      <div style="position:absolute; z-index:100; margin:420px 0px 0px 250px;"><img src="data:image/png;base64,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"/></div>
  </xsl:variable>

  <!-- Đưa vào Background -->
  <xsl:variable name="Background">
    <xsl:choose>
      <xsl:when test="(/inv:invoice/inv:invoiceData/inv:userDefines/inv:Backgroud) and  (/inv:invoice/inv:invoiceData/inv:userDefines/inv:Backgroud!='')"><div style="position:absolute; z-index:-1; text-align:center; vertical-align:middle; margin:300px 0px 0px 180px; opacity:0.3; filter: alpha(opacity=30);"><img style="max-width: 600px; margin:120px 0px 0px -120px;" src='data:image/png;base64,{/inv:invoice/inv:invoiceData/inv:userDefines/inv:Backgroud}' /></div></xsl:when>
      <xsl:otherwise><div style="position:absolute; z-index:-1; text-align:center; vertical-align:middle; margin:300px 0px 0px 180px; opacity:0.3; filter: alpha(opacity=30);"><img style="max-width: 600px; margin:120px 0px 0px -120px;" src='data:image/png;base64,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' /></div></xsl:otherwise>
    </xsl:choose>
  </xsl:variable>

  <!-- Đưa vào Thông tin ứng dụng XuatHoaDon™ -->
  <xsl:variable name="UngDungXHD">
    <div style="position:absolute; z-index:100; margin:250px 0px 0px 710px;"><img src="data:image/png;base64,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"/></div>
  </xsl:variable>

  <!-- Đưa vào Hình hóa đơn xóa bỏ -->
  <xsl:variable name="HinhHDXoaBo">
    <div style="position:absolute; z-index:100; margin:0px auto;"><img src="data:image/png;base64,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"/></div>
  </xsl:variable>

  <!-- Đưa vào Thông tin người bán -->
  <xsl:variable name="TTNguoiBan">
    <table style="table-layout:auto;">
      <col width="25%" />
      <col width="10%" />
      <col width="15%" />
      <col width="15%" />
      <col width="15%" />
      <col width="20%" />
      <tr>
        <td colspan="5" align="left" valign="top" style="padding-right:7px;">
          <span style="font-size:14pt;"><b><xsl:value-of select="translate(/inv:invoice/inv:invoiceData/inv:sellerLegalName,$lower,$upper)"/><br />
          <i>THK POWERTOOLS (VIETNAM) COMPANY LIMITED</i></b></span>
        </td>
        <td align="right" valign="top" style="max-width:200px; max-height:150px; white-space:nowrap;">
          <xsl:choose>
            <xsl:when test="(/inv:invoice/inv:invoiceData/inv:userDefines/inv:Logo) and (/inv:invoice/inv:invoiceData/inv:userDefines/inv:Logo!='')"><img style="max-width:200px; max-height:150px;" src='data:image/png;base64,{/inv:invoice/inv:invoiceData/inv:userDefines/inv:Logo}' /></xsl:when>
            <xsl:otherwise></xsl:otherwise>
          </xsl:choose>
        </td>
      </tr>
      <tr>
        <td colspan="6" align="left" valign="top">
          Mã số thuế <i>(Tax code)</i>:
          <span style="font-size:11pt;"><b><xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerTaxCode"/></b></span>
        </td>
      </tr>
      <tr>
        <td colspan="6" align="left" valign="top">Địa chỉ <i>(Address)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerAddressLine"/></td>
      </tr>
      <tr>
        <td colspan="3" align="left" valign="top">Số tài khoản <i>(Account No.)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerBankAccount"/></td>
        <td colspan="3" align="left" valign="top">Tại ngân hàng <i>(At)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerBankName"/></td>
      </tr>
      <tr>
        <td colspan="2" align="left" valign="top" style="border-bottom:1px solid #0a51a1">Điện thoại <i>(Tel.)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerPhoneNumber"/></td>
        <td colspan="2" align="left" valign="top" style="border-bottom:1px solid #0a51a1">Fax: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerFaxNumber"/></td>
        <td colspan="2" align="left" valign="top" style="border-bottom:1px solid #0a51a1">Email: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerEmail"/></td>
      </tr>
    </table>
  </xsl:variable>
  
  <!-- Đưa vào Mẫu số HĐ và Tiêu đề -->
  <xsl:variable name="MauSovaTieuDe">
    <table style="table-layout:auto">
      <col width="20%" />
      <col width="60%" />
      <col width="20%" />
      <tr>
        <td align="center" valign="middle" style="border-bottom:1px solid #0a51a1">&nbsp;</td>
        <td align="center" valign="middle" style="border-bottom:1px solid #0a51a1">
          <span style="font-size:16pt">
          <b>HÓA ĐƠN GIÁ TRỊ GIA TĂNG</b><br />
          <b><i>(VAT INVOICE)</i></b>
          </span>
          <br />
          <xsl:choose>
            <xsl:when test="(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate) and (/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate)!=''">Ngày <i>(Date)</i>: <xsl:value-of select="substring(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate,9,2)" /> Tháng <i>(Month)</i>: <xsl:value-of select="substring(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate,6,2)" /> Năm <i>(Year)</i>: <xsl:value-of select="substring(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate,1,4)" /></xsl:when>
            <xsl:otherwise>Ngày <i>(Date)</i>: <span style="margin-left:10px">&nbsp;</span> Tháng <i>(Month)</i>: <span style="margin-left:10px">&nbsp;</span> Năm <i>(Year)</i>: <span style="margin-left:30px">&nbsp;</span></xsl:otherwise>
          </xsl:choose>
          <br />
          <xsl:choose>
            <xsl:when test="((/inv:invoice/inv:invoiceData/inv:userDefines/inv:NguoiChuyenDoi) and (/inv:invoice/inv:invoiceData/inv:userDefines/inv:NguoiChuyenDoi)!='') and ((/inv:invoice/inv:invoiceData/inv:userDefines/inv:LoaiHDView) and (/inv:invoice/inv:invoiceData/inv:userDefines/inv:LoaiHDView=1))">(HOÁ ĐƠN CHUYỂN ĐỔI TỪ HOÁ ĐƠN XÁC THỰC)</xsl:when>
            <xsl:when test="((/inv:invoice/inv:invoiceData/inv:userDefines/inv:NguoiChuyenDoi) and (/inv:invoice/inv:invoiceData/inv:userDefines/inv:NguoiChuyenDoi)!='') and (not(/inv:invoice/inv:invoiceData/inv:userDefines/inv:LoaiHDView) or (/inv:invoice/inv:invoiceData/inv:userDefines/inv:LoaiHDView=0))">(HOÁ ĐƠN CHUYỂN ĐỔI TỪ HOÁ ĐƠN ĐIỆN TỬ)</xsl:when>
            <xsl:otherwise><span id="inHD"></span></xsl:otherwise>
          </xsl:choose>
        </td>
        <td align="left" valign="top" style="font-size:9pt; white-space:nowrap; padding-top:2px; border-bottom:1px solid #0a51a1;">
          Mẫu số <i>(Form)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:templateCode"/><br />
          Ký hiệu <i>(Serial)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:invoiceSeries"/><br />
          Số <i>(No.)</i>: <span style="font-size:15pt"><b><xsl:value-of select="/inv:invoice/inv:invoiceData/inv:invoiceNumber"/></b></span>
        </td>
      </tr>
    </table>
  </xsl:variable>
  
  <!-- Đưa vào Thông tin Hóa đơn xác thực và thông tin giao dịch -->
  <xsl:variable name="TTXacThucvaTTGiaoDich">
    <table>
      <col width="45%" />
      <col width="20%" />
      <col width="20%" />
      <col width="15%" />
      <xsl:choose>
        <xsl:when test="(/inv:invoice/inv:certifiedData/inv:certifiedId) and (/inv:invoice/inv:certifiedData/inv:certifiedId)!=''">
          <tr>
            <td colspan="3" align="left" valign="middle">
              <xsl:choose>
                <xsl:when test="(/inv:invoice/inv:invoiceData/inv:adjustmentType)=3">Thay thế cho hóa đơn xác thực số:</xsl:when>
                <xsl:when test="(/inv:invoice/inv:invoiceData/inv:adjustmentType)=5">Điều chỉnh cho hóa đơn xác thực số:</xsl:when>
                <xsl:when test="(/inv:invoice/inv:invoiceData/inv:adjustmentType)=7">Xóa bỏ cho hóa đơn xác thực số:</xsl:when>
                <xsl:otherwise></xsl:otherwise>
              </xsl:choose>
              <xsl:value-of select="/inv:invoice/inv:certifiedData/inv:originalCertifiedId"/><br />
              Số hóa đơn xác thực <i>(Certified ID)</i>: <xsl:value-of select="/inv:invoice/inv:certifiedData/inv:certifiedId"/><br />
              Mã xác thực <i>(Certified code)</i>: <xsl:value-of select="/inv:invoice/inv:certifiedData/inv:signature"/>
            </td>
            <td rowspan="2" align="right" valign="top">
              <xsl:choose>
                <xsl:when test="(/inv:invoice/inv:controlData/inv:qrCodeData) and (/inv:invoice/inv:controlData/inv:qrCodeData!='')"><div id="qrcodeTable"></div></xsl:when>
                <xsl:otherwise>&nbsp;</xsl:otherwise>
              </xsl:choose>
            </td>
          </tr>
          <xsl:choose>
            <xsl:when test="(/inv:invoice/inv:invoiceData/inv:userDefines/inv:MauIn) and (/inv:invoice/inv:invoiceData/inv:userDefines/inv:MauIn!='') and (substring-after(/inv:invoice/inv:invoiceData/inv:userDefines/inv:MauIn,'_')='41')">
              <tr>
                <td colspan="3" align="left" valign="top">Mã nhận hóa đơn <i>(Invoice ID)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:userDefines/inv:MaNhanHoaDon"/></td>
              </tr>
              <tr>
                <td colspan="4" align="left" valign="top">Họ tên người mua hàng <i>(Buyer's Name)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:buyerDisplayName"/></td>
              </tr>
            </xsl:when>
            <xsl:otherwise>
              <tr>
                <td colspan="3" align="left" valign="top">Họ tên người mua hàng <i>(Buyer's Name)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:buyerDisplayName"/></td>
              </tr>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:when>
        <xsl:otherwise>
          <xsl:if test="(/inv:invoice/inv:invoiceData/inv:userDefines/inv:MauIn) and (/inv:invoice/inv:invoiceData/inv:userDefines/inv:MauIn!='') and (substring-after(/inv:invoice/inv:invoiceData/inv:userDefines/inv:MauIn,'_')='41')">
            <tr>
              <td colspan="3" align="left" valign="top">Mã nhận hóa đơn <i>(Invoice ID)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:userDefines/inv:MaNhanHoaDon"/></td>
            </tr>
          </xsl:if>
          <tr>
            <td colspan="4" align="left" valign="top">Họ tên người mua hàng <i>(Buyer's Name)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:buyerDisplayName"/></td>
          </tr>
        </xsl:otherwise>
      </xsl:choose>
      <tr>
        <td colspan="4" align="left" valign="top">Tên đơn vị <i>(Company's Name)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:buyerLegalName"/></td>
      </tr>
      <tr>
        <td colspan="4" align="left" valign="top">Địa chỉ <i>(Address)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:buyerAddressLine"/></td>
      </tr>
      <tr>
        <td align="left" valign="top">Mã số thuế <i>(Tax code)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:buyerTaxCode"/></td>
        <td align="left" valign="top">Fax: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:buyerFaxNumber"/></td>
      </tr>
      <tr>
        <td align="left" valign="top">Số điện thoại <i>(Tel.)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:buyerPhoneNumber"/></td>
        <td colspan="2" align="left" valign="top">Email: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:buyerEmail"/></td>
      </tr>
      <tr>
        <td align="left" valign="top">Số tài khoản <i>(Account No.)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:buyerBankAccount"/></td>
        <td colspan="3" align="left" valign="top">Tại ngân hàng <i>(At)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:buyerBankName"/></td>
      </tr>
      <tr>
        <td colspan="2" align="left" valign="top">Hình thức thanh toán <i>(Payment method)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:payments/inv:payment/inv:paymentMethodName"/></td>
        <td colspan="2" align="left" valign="top">Loại tiền <i>(Currency)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:currencyCode"/></td>
      </tr>
    </table>
  </xsl:variable>
  
  <!-- Đưa vào Số tiền bằng chữ -->
  <xsl:variable name="SoTienBangChu">
    <table>
      <tr>
        <td align="left">Số tiền viết bằng chữ <i>(in words)</i>: 
          <xsl:choose>
            <xsl:when test="/inv:invoice/inv:invoiceData/inv:totalAmountWithVATInWords!=''"><xsl:value-of select="/inv:invoice/inv:invoiceData/inv:totalAmountWithVATInWords"/></xsl:when>
            <xsl:otherwise>&nbsp;</xsl:otherwise>
          </xsl:choose>
        </td>
      </tr>
    </table>
  </xsl:variable>

  <!-- Đưa vào Thông tin Ký HĐ -->
  <xsl:variable name="TTKyHD">
    <table>
      <col width="30%"/>
      <col width="40%"/>
      <col width="30%"/>
      <tr>
        <td align="center" valign="top" style="white-space:nowrap">
          Người mua hàng <i>(Buyer)</i><br />
          <br /><br />
          <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:buyerDisplayName"/>
        </td>
        <td align="left" valign="middle">
          <xsl:choose>
            <xsl:when test="(/inv:invoice/inv:invoiceData/inv:userDefines/inv:NguoiChuyenDoi) and (/inv:invoice/inv:invoiceData/inv:userDefines/inv:NguoiChuyenDoi)!=''">Người chuyển đổi: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:userDefines/inv:NguoiChuyenDoi" /></xsl:when>
            <xsl:otherwise><span id="nguoiCD"></span></xsl:otherwise>
          </xsl:choose>
          <br /><br />
          <xsl:choose>
            <xsl:when test="(/inv:invoice/inv:invoiceData/inv:userDefines/inv:NgayChuyenDoi) and (/inv:invoice/inv:invoiceData/inv:userDefines/inv:NgayChuyenDoi)!='' and substring(/inv:invoice/inv:invoiceData/inv:userDefines/inv:NgayChuyenDoi,5,1)='-'">Ngày chuyển đổi: <xsl:value-of select="concat(substring(/inv:invoice/inv:invoiceData/inv:userDefines/inv:NgayChuyenDoi,9,2),'/',substring(/inv:invoice/inv:invoiceData/inv:userDefines/inv:NgayChuyenDoi,6,2),'/',substring(/inv:invoice/inv:invoiceData/inv:userDefines/inv:NgayChuyenDoi,1,4))" /></xsl:when>
            <xsl:when test="(/inv:invoice/inv:invoiceData/inv:userDefines/inv:NgayChuyenDoi) and (/inv:invoice/inv:invoiceData/inv:userDefines/inv:NgayChuyenDoi)!='' and substring(/inv:invoice/inv:invoiceData/inv:userDefines/inv:NgayChuyenDoi,3,1)='/'">Ngày chuyển đổi: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:userDefines/inv:NgayChuyenDoi" /></xsl:when>
            <xsl:otherwise><span id="ngayCD"></span></xsl:otherwise>
          </xsl:choose>
        </td>
        <td align="center" valign="top" style="white-space:nowrap">
          Người bán hàng <i>(Seller)</i><br />
          <br /><br />
          <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerContactPersonName"/>
        </td>
      </tr>
    </table>
  </xsl:variable>
  
  <!-- Đưa vào Thông tin CKS -->
  <xsl:variable name="CKS">
    <table>
      <col width="49%"/>
      <col width="2%"/>
      <col width="49%"/>
      <xsl:if test="(/inv:invoice/CKS) and count(/inv:invoice/CKS)!=0">
      <tr>
        <td colspan="2">&nbsp;</td>   
      </tr>
      <tr>
        <xsl:if test="(/inv:invoice/CKS/CKSNguoiMua/Serial) and (/inv:invoice/CKS/CKSNguoiMua/Serial)!=''">
        <xsl:choose>
          <xsl:when test="(/inv:invoice/CKS/CKSNguoiMua/LogoCKS) and (/inv:invoice/CKS/CKSNguoiMua/LogoCKS)!=''">
        <td align="justify" valign="top" style="font-size:9pt; max-width:350px;">
          <span style="position:absolute; z-index:-10;"><img src='data:image/png;base64,{/inv:invoice/CKS/CKSNguoiMua/LogoCKS}' /></span>
          Signer Info: <xsl:value-of select="/inv:invoice/CKS/CKSNguoiMua/Subject"/><br/>
          Serial number: <xsl:value-of select="/inv:invoice/CKS/CKSNguoiMua/Serial"/><br/>
        </td>
          </xsl:when>
          <xsl:otherwise>
        <td align="justify" valign="top" style="padding-left:30px; font-size:9pt; max-width:350px;">
          <span style="position:absolute; margin-left:-35px; margin-top:-5px;"><img src='data:image/png;base64,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' /></span>
          Signer Info: <xsl:value-of select="/inv:invoice/CKS/CKSNguoiMua/Subject"/><br/>
          Serial number: <xsl:value-of select="/inv:invoice/CKS/CKSNguoiMua/Serial"/><br/>
        </td>
          </xsl:otherwise>
        </xsl:choose>
        </xsl:if>
        <xsl:if test="not(/inv:invoice/CKS/CKSNguoiMua/Serial) or (/inv:invoice/CKS/CKSNguoiMua/Serial)=''">
        <td>&nbsp;</td>
        </xsl:if>
        <td>&nbsp;</td>
        <xsl:if test="(/inv:invoice/CKS/CKSNguoiBan/Serial) and (/inv:invoice/CKS/CKSNguoiBan/Serial)!=''">
        <xsl:choose>
          <xsl:when test="(/inv:invoice/CKS/CKSNguoiBan/LogoCKS) and (/inv:invoice/CKS/CKSNguoiBan/LogoCKS)!=''">
        <td align="justify" valign="top" style="font-size:9pt; max-width:350px;">
          <span style="position:absolute; z-index:-10;"><img src='data:image/png;base64,{/inv:invoice/CKS/CKSNguoiBan/LogoCKS}' /></span>
          Signer Info: <xsl:value-of select="/inv:invoice/CKS/CKSNguoiBan/Subject"/><br/>
          Serial number: <xsl:value-of select="/inv:invoice/CKS/CKSNguoiBan/Serial"/><br/>
        </td>
          </xsl:when>
          <xsl:otherwise>
        <td align="justify" valign="top" style="padding-left:30px; font-size:9pt; max-width:350px;">
          <span style="position:absolute; margin-left:-35px; margin-top:-5px;"><img src='data:image/png;base64,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' /></span>
          Signer Info: <xsl:value-of select="/inv:invoice/CKS/CKSNguoiBan/Subject"/><br/>
          Serial number: <xsl:value-of select="/inv:invoice/CKS/CKSNguoiBan/Serial"/><br/>
        </td>
          </xsl:otherwise>
        </xsl:choose>
        </xsl:if>
        <xsl:if test="not(/inv:invoice/CKS/CKSNguoiBan/Serial) or (/inv:invoice/CKS/CKSNguoiBan/Serial)=''">
        <td>&nbsp;</td>
        </xsl:if>
      </tr>
      <tr>
        <td colspan="2"><br /><br /></td>   
      </tr>
      </xsl:if>
    </table>
  </xsl:variable>
  
  <!-- Đưa vào Ghi chú -->
  <xsl:variable name="GhiChu">
    <table>
      <xsl:if test="(/inv:invoice/inv:invoiceData/inv:userDefines/inv:HoaDonThayThe) and (/inv:invoice/inv:invoiceData/inv:userDefines/inv:HoaDonThayThe)!='' and (/inv:invoice/inv:invoiceData/inv:userDefines/inv:HoaDonThayThe!=7)">
      <tr>
        <td style="font-size:9pt; text-align:center;"><xsl:value-of select="/inv:invoice/inv:invoiceData/inv:userDefines/inv:HoaDonThayThe"/></td>
      </tr>
      </xsl:if>
      <tr>
      <xsl:choose>
        <xsl:when test="(/inv:invoice/inv:invoiceData/inv:userDefines/inv:LoaiHDView) and (/inv:invoice/inv:invoiceData/inv:userDefines/inv:LoaiHDView=1)">
        <td style="font-size:8pt; text-align:center; border-top: 1px solid #0a51a1;">
          <i><b>
            (Cần kiểm tra, đối chiếu khi lập, giao, nhận hóa đơn)<br />
            Hóa đơn điện tử được cấp mã xác thực bởi Tổng cục Thuế Việt Nam
          </b></i>
        </td>
        </xsl:when>
        <xsl:otherwise>
        <td style="font-size:8pt; text-align:justify; border-top: 1px solid #0a51a1;">
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
          <b>BẢNG KÊ CHI TIẾT HÀNG HÓA, DỊCH VỤ</b><br />
          <i>(TABLE DETAILS OF GOODS, SERVICES)</i>
          </span>
          <br />
          <i>
          (Kèm theo hóa đơn số <span style="font-size:13pt"><b><xsl:value-of select="/inv:invoice/inv:invoiceData/inv:invoiceNumber"/></b>&nbsp;</span>
          <xsl:choose>
            <xsl:when test="(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate) and (/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate)!='' and substring(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate,5,1)='-'">ngày <xsl:value-of select="substring(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate,9,2)" /> tháng <xsl:value-of select="substring(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate,6,2)" /> năm <xsl:value-of select="substring(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate,1,4)" />)</xsl:when>
            <xsl:when test="(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate) and (/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate)!='' and substring(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate,3,1)='/'">ngày <xsl:value-of select="substring(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate,1,2)" /> tháng <xsl:value-of select="substring(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate,4,2)" /> năm <xsl:value-of select="substring(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate,7,4)" />)</xsl:when>
            <xsl:otherwise>ngày <span style="margin-left:10px">&nbsp;</span> tháng <span style="margin-left:10px">&nbsp;</span> năm <span style="margin-left:30px">&nbsp;</span>)</xsl:otherwise>
          </xsl:choose><br />
          (File attached with invoice: No.: <span style="font-size:13pt"><b><xsl:value-of select="/inv:invoice/inv:invoiceData/inv:invoiceNumber"/></b>&nbsp;</span>
          <xsl:choose>
            <xsl:when test="(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate) and (/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate)!='' and substring(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate,5,1)='-'">Date: <xsl:value-of select="concat(substring(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate,9,2),'/',substring(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate,6,2),'/',substring(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate,1,4))" />)</xsl:when>
            <xsl:when test="(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate) and (/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate)!='' and substring(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate,3,1)='/'">Date: <xsl:value-of select="concat(substring(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate,1,2),'/',substring(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate,4,2),'/',substring(/inv:invoice/inv:invoiceData/inv:invoiceIssuedDate,7,4))" />)</xsl:when>
            <xsl:otherwise>Date: <span style="margin-left:50px">&nbsp;</span>)</xsl:otherwise>
          </xsl:choose><br />
          </i>
        </td>
      </tr>
      <tr><td colspan="3">&nbsp;</td></tr>
      <tr>
        <td colspan="3" align="left" valign="middle"><b><xsl:value-of select="translate(/inv:invoice/inv:invoiceData/inv:sellerLegalName,$lower,$upper)"/></b></td>
      </tr>
      <tr>
        <td colspan="3" align="left" valign="top">Địa chỉ <i>(Address)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerAddressLine"/></td>
      </tr>
      <tr>
        <td colspan="3" align="left" valign="top">MST <i>(Tax code)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerTaxCode"/></td>
      </tr>
      <tr>
        <td align="left" valign="top" style="border-bottom:1px solid #0a51a1">Điện thoại <i>(Tel.)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerPhoneNumber"/></td>
        <td align="left" valign="top" style="border-bottom:1px solid #0a51a1">Fax: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerFaxNumber"/></td>
        <td align="left" valign="top" style="border-bottom:1px solid #0a51a1">Email: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerEmail"/></td>
      </tr>
    </table>

    <!-- Thông tin chi tiết hóa đơn (Hàng hóa, dịch vụ) -->
    <table cellspacing="0" cellpadding="2" style="table-layout:fixed;">
      <col width="6%"/>
      <col width="10%"/>
      <col width="27%"/>
      <col width="10%"/>
      <col width="6%"/>
      <col width="10%"/>
      <col width="15%"/>
      <col width="16%"/>
      <tr>
        <td align="center" class="borderTD">
          STT<br />
          <i>(No.)</i>
        </td>
        <td align="center" class="borderTD">
          Mã hàng hóa<br />
          <i>(Code)</i>
        </td>
        <td align="center" class="borderTD">
          Tên hàng hóa, dịch vụ<br />
          <i>(Description)</i>
        </td>
        <td align="center" class="borderTD">
          Đơn vị tính<br />
          <i>(Unit)</i>
        </td>
        <td align="center" class="borderTD">
          Thuế suất (%)<br />
          <i>(VAT rate)</i>
        </td>
        <td align="center" class="borderTD">
          Số lượng<br />
          <i>(Quantity)</i>
        </td>
        <td align="center" class="borderTD">
          Đơn giá<br />
          <i>(Unit price)</i>
        </td>
        <td align="center" class="borderTD">
          Thành tiền<br />
          <i>(Amount)</i>
        </td>
      </tr>
      <tr>
        <td align="center" class="borderTD">(1)</td>
        <td align="center" class="borderTD">(2)</td>
        <td align="center" class="borderTD">(3)</td>
        <td align="center" class="borderTD">(4)</td>
        <td align="center" class="borderTD">(5)</td>
        <td align="center" class="borderTD">(6)</td>
        <td align="center" class="borderTD">(7)</td>
        <td align="center" class="borderTD">(8) = (6) x (7)</td>
      </tr>
      <xsl:for-each select="/inv:invoice/inv:invoiceData/inv:items/inv:item">
      <tr>
        <td align="center" class="borderTD"><xsl:number format="1"/></td>
        <td align="center" class="borderTD">
          <xsl:choose>
            <xsl:when test="(inv:itemCode) and (inv:itemCode)!=''"><xsl:value-of select="inv:itemCode"/></xsl:when>
            <xsl:otherwise>&nbsp;</xsl:otherwise>
          </xsl:choose>
        </td>
        <td align="left" class="borderTD">
          <xsl:choose>
            <xsl:when test="(inv:itemName) and (inv:itemName)!=''"><xsl:value-of select="inv:itemName"/></xsl:when>
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
            <xsl:when test="(inv:vatPercentage) and (inv:vatPercentage)!='' and (inv:vatPercentage)!=-1 and (inv:vatPercentage)!=-2"><xsl:value-of select="format-number(inv:vatPercentage, '#.##0,###', 'vndong')"/></xsl:when>
            <xsl:when test="(inv:vatPercentage) and (inv:vatPercentage)=0">0</xsl:when>
            <xsl:otherwise>&nbsp;</xsl:otherwise>
          </xsl:choose>
        </td>
        <td align="right" class="borderTD">
          <xsl:choose>
            <xsl:when test="(inv:quantity) and (inv:quantity)!='' and (inv:quantity)!=0"><xsl:value-of select="format-number(inv:quantity, '#.##0,##', 'vndong')"/></xsl:when>
            <xsl:otherwise>&nbsp;</xsl:otherwise>
          </xsl:choose>
        </td>
        <td align="right" class="borderTD">
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
    </table>
  </xsl:variable>
</xsl:stylesheet>