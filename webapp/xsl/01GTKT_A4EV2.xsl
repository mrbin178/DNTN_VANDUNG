<?xml version="1.0" encoding="utf-8"?><!-- DWXMLSource="HDDT_GTGT_Elite_M4.xml" -->
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
          .cell, .half-cell { border: 1px solid #000; display:inline-block; *display:inline; zoom:1; padding: 0px; }
          .cellformat{ width:14px; height:14px; line-height:14px; text-align:center; }
          .cellformatheight{ height:14px; line-height:14px; text-align:center; }
          .cellformatdetail{ width:20px; height:20px; line-height:20px; text-align:center; }
          .borderTD { border:1px solid #000; border-collapse:collapse; vertical-align:middle; }
          .borderLR { border:1px solid #000; border-collapse:collapse; vertical-align:middle; border-bottom:none; border-top:none; }
          .borderTB { border:1px solid #000; border-collapse:collapse; vertical-align:middle; border-right:none; border-left:none; }
		  .borderTop { border-top:1px solid #000; border-collapse:collapse; vertical-align:middle; }
		  .borderBottom { border-bottom:1px solid #000; border-collapse:collapse; vertical-align:middle; }
		  .borderLeft { border-left:1px solid #000; border-collapse:collapse; vertical-align:middle; }
		  .borderRight { border-right:1px solid #000; border-collapse:collapse; vertical-align:middle; }
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
      <div style="width:700px; margin:0 auto; padding:8px; background-image: url('data:image/png;base64,{inv:invoiceData/inv:userDefines/inv:Background}'); background-repeat:no-repeat; background-position:center; text-align:center;">
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
        <br />
        
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
            <table cellspacing="0" cellpadding="2" style="color:BLACK; table-layout:fixed;">
              <col width="6%"/>
              <col width="38%"/>
              <col width="12%"/>
              <col width="12%"/>
              <col width="12%"/>
              <col width="20%"/>
              <tr>
                <td align="center" class="borderTD">
                  STT<br />
                  <i>(No.)</i>
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
                <td align="center" class="borderTD">(6) = (4) x (5)</td>
              </tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr>
                <td colspan="5" align="right" class="borderTD">
                  Cộng tiền hàng <i>(Sub total):</i>
                </td>
                <td class="borderTD">&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2" align="left" class="borderTD">
                  Thuế suất GTGT <i>(VAT rate):</i>
                </td>
                <td colspan="3" align="right" class="borderTD">
                  Tiền thuế GTGT <i>(VAT):</i>
                </td>
                <td class="borderTD">&nbsp;</td>
              </tr>
              <tr>
                <td colspan="5" align="right" class="borderTD">
                  Tổng cộng tiền thanh toán <i>(Total of payment):</i>
                </td>
                <td class="borderTD">&nbsp;</td>
              </tr>
            </table>
          </xsl:when>
          <!-- TRƯỜNG HỢP 2: CHI TIẾT HÓA ĐƠN CÓ BẢNG KÊ-->  
          <xsl:when test="(inv:invoiceData/inv:userDefines/inv:SoBangKe) and (inv:invoiceData/inv:userDefines/inv:SoBangKe)!='' and (inv:invoiceData/inv:userDefines/inv:SoBangKe)!=0">
            <table cellspacing="0" cellpadding="2" style="color:BLACK; table-layout:fixed;">
              <col width="6%"/>
              <col width="38%"/>
              <col width="12%"/>
              <col width="12%"/>
              <col width="12%"/>
              <col width="20%"/>
              <tr>
                <td align="center" class="borderTD">
                  STT<br />
                  <i>(No.)</i>
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
                <td align="center" class="borderTD">(6) = (4) x (5)</td>
              </tr>
              <tr>
                <td align="center" class="borderTD"><xsl:number format="1"/></td>
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
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr>
                <td colspan="5" align="right" class="borderTD">
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
                <td colspan="2" align="left" class="borderTD">
                  Thuế suất GTGT <i>(VAT rate):</i>
                  <xsl:choose>
                    <xsl:when test="(inv:invoiceData/inv:userDefines/inv:TyLeVAT) and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!='' and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)=-1">KCT</xsl:when>
                    <xsl:when test="(inv:invoiceData/inv:userDefines/inv:TyLeVAT) and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!='' and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!=-1 and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!=-2"><xsl:value-of select="format-number(inv:invoiceData/inv:userDefines/inv:TyLeVAT, '#.##0,##', 'vndong')"/>%</xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
                <td colspan="3" align="right" class="borderTD">
                  Tiền thuế GTGT <i>(VAT):</i>
                </td>
                <td align="right" class="borderTD">
                  <xsl:choose>
                    <xsl:when test="(inv:invoiceData/inv:totalVATAmount) and (inv:invoiceData/inv:totalVATAmount)!='' and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!=-1 and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!=-2"><xsl:value-of select="format-number(inv:invoiceData/inv:totalVATAmount, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
              </tr>
              <tr>
                <td colspan="5" align="right" class="borderTD">
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
            <table cellspacing="0" cellpadding="2" style="color:BLACK; table-layout:fixed;">
              <col width="6%"/>
              <col width="38%"/>
              <col width="12%"/>
              <col width="12%"/>
              <col width="12%"/>
              <col width="20%"/>
              <tr>
                <td align="center" class="borderTD">
                  STT<br />
                  <i>(No.)</i>
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
                <td align="center" class="borderTD">(6) = (4) x (5)</td>
              </tr>
              <xsl:for-each select="inv:invoiceData/inv:items/inv:item">
              <tr>
                <td align="center" class="borderTD"><xsl:number format="1"/></td>
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
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=1">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=2">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=3">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=4">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=5">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=6">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=7">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=8">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <xsl:if test="(inv:invoiceData/inv:items/inv:item) and count(inv:invoiceData/inv:items/inv:item)=9">
              <tr><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td><td class="borderTD">&nbsp;</td></tr>
              </xsl:if>
              <tr>
                <td colspan="5" align="right" class="borderTD">
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
                <td colspan="2" align="left" class="borderTD">
                  Thuế suất GTGT <i>(VAT rate):</i>
                  <xsl:choose>
                    <xsl:when test="(inv:invoiceData/inv:userDefines/inv:TyLeVAT) and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!='' and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)=-1">KCT</xsl:when>
                    <xsl:when test="(inv:invoiceData/inv:userDefines/inv:TyLeVAT) and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!='' and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!=-1 and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!=-2"><xsl:value-of select="format-number(inv:invoiceData/inv:userDefines/inv:TyLeVAT, '#.##0,##', 'vndong')"/>%</xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
                <td colspan="3" align="right" class="borderTD">
                  Tiền thuế GTGT <i>(VAT):</i>
                </td>
                <td align="right" class="borderTD">
                  <xsl:choose>
                    <xsl:when test="(inv:invoiceData/inv:totalVATAmount) and (inv:invoiceData/inv:totalVATAmount)!='' and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!=-1 and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!=-2"><xsl:value-of select="format-number(inv:invoiceData/inv:totalVATAmount, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
              </tr>
              <tr>
                <td colspan="5" align="right" class="borderTD">
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

        <xsl:if test="(inv:invoiceData/inv:userDefines/inv:SoBangKe) and (inv:invoiceData/inv:userDefines/inv:SoBangKe)!='' and (inv:invoiceData/inv:userDefines/inv:SoBangKe)!=0">
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
      <div style="position:absolute; z-index:100; margin:480px 0px 0px 250px;"><img src="data:image/png;base64,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"/></div>
  </xsl:variable>

  <!-- Đưa vào Background -->
  <xsl:variable name="Background">
    <xsl:choose>
      <xsl:when test="(/inv:invoice/inv:invoiceData/inv:userDefines/inv:Backgroud) and  (/inv:invoice/inv:invoiceData/inv:userDefines/inv:Backgroud!='')"><div style="position:absolute; z-index:-1; text-align:center; vertical-align:middle; margin:300px 0px 0px 180px; opacity:0.7; filter: alpha(opacity=70);"><img style="max-width: 300px; margin-top: 95px;" src='data:image/png;base64,{/inv:invoice/inv:invoiceData/inv:userDefines/inv:Backgroud}' /></div></xsl:when>
      <xsl:otherwise></xsl:otherwise>
    </xsl:choose>
  </xsl:variable>

  <!-- Đưa vào Thông tin ứng dụng XuatHoaDon™ -->
  <xsl:variable name="UngDungXHD">
    <div style="position:absolute; z-index:100; margin:250px 0px 0px 710px;"><img src="data:image/png;base64,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"/></div>
  </xsl:variable>

  <!-- Đưa vào Hình hóa đơn xóa bỏ -->
  <xsl:variable name="HinhHDXoaBo">
    <div style="position:absolute; z-index:100; margin:0px auto;"><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAArwAAAH0CAMAAADohw7tAAAKN2lDQ1BzUkdCIElFQzYxOTY2LTIuMQAAeJydlndUU9kWh8+9N71QkhCKlNBraFICSA29SJEuKjEJEErAkAAiNkRUcERRkaYIMijggKNDkbEiioUBUbHrBBlE1HFwFBuWSWStGd+8ee/Nm98f935rn73P3Wfvfda6AJD8gwXCTFgJgAyhWBTh58WIjYtnYAcBDPAAA2wA4HCzs0IW+EYCmQJ82IxsmRP4F726DiD5+yrTP4zBAP+flLlZIjEAUJiM5/L42VwZF8k4PVecJbdPyZi2NE3OMErOIlmCMlaTc/IsW3z2mWUPOfMyhDwZy3PO4mXw5Nwn4405Er6MkWAZF+cI+LkyviZjg3RJhkDGb+SxGXxONgAoktwu5nNTZGwtY5IoMoIt43kA4EjJX/DSL1jMzxPLD8XOzFouEiSniBkmXFOGjZMTi+HPz03ni8XMMA43jSPiMdiZGVkc4XIAZs/8WRR5bRmyIjvYODk4MG0tbb4o1H9d/JuS93aWXoR/7hlEH/jD9ld+mQ0AsKZltdn6h21pFQBd6wFQu/2HzWAvAIqyvnUOfXEeunxeUsTiLGcrq9zcXEsBn2spL+jv+p8Of0NffM9Svt3v5WF485M4knQxQ143bmZ6pkTEyM7icPkM5p+H+B8H/nUeFhH8JL6IL5RFRMumTCBMlrVbyBOIBZlChkD4n5r4D8P+pNm5lona+BHQllgCpSEaQH4eACgqESAJe2Qr0O99C8ZHA/nNi9GZmJ37z4L+fVe4TP7IFiR/jmNHRDK4ElHO7Jr8WgI0IABFQAPqQBvoAxPABLbAEbgAD+ADAkEoiARxYDHgghSQAUQgFxSAtaAYlIKtYCeoBnWgETSDNnAYdIFj4DQ4By6By2AE3AFSMA6egCnwCsxAEISFyBAVUod0IEPIHLKFWJAb5AMFQxFQHJQIJUNCSAIVQOugUqgcqobqoWboW+godBq6AA1Dt6BRaBL6FXoHIzAJpsFasBFsBbNgTzgIjoQXwcnwMjgfLoK3wJVwA3wQ7oRPw5fgEVgKP4GnEYAQETqiizARFsJGQpF4JAkRIauQEqQCaUDakB6kH7mKSJGnyFsUBkVFMVBMlAvKHxWF4qKWoVahNqOqUQdQnag+1FXUKGoK9RFNRmuizdHO6AB0LDoZnYsuRlegm9Ad6LPoEfQ4+hUGg6FjjDGOGH9MHCYVswKzGbMb0445hRnGjGGmsVisOtYc64oNxXKwYmwxtgp7EHsSewU7jn2DI+J0cLY4X1w8TogrxFXgWnAncFdwE7gZvBLeEO+MD8Xz8MvxZfhGfA9+CD+OnyEoE4wJroRIQiphLaGS0EY4S7hLeEEkEvWITsRwooC4hlhJPEQ8TxwlviVRSGYkNimBJCFtIe0nnSLdIr0gk8lGZA9yPFlM3kJuJp8h3ye/UaAqWCoEKPAUVivUKHQqXFF4pohXNFT0VFysmK9YoXhEcUjxqRJeyUiJrcRRWqVUo3RU6YbStDJV2UY5VDlDebNyi/IF5UcULMWI4kPhUYoo+yhnKGNUhKpPZVO51HXURupZ6jgNQzOmBdBSaaW0b2iDtCkVioqdSrRKnkqNynEVKR2hG9ED6On0Mvph+nX6O1UtVU9Vvuom1TbVK6qv1eaoeajx1UrU2tVG1N6pM9R91NPUt6l3qd/TQGmYaYRr5Grs0Tir8XQObY7LHO6ckjmH59zWhDXNNCM0V2ju0xzQnNbS1vLTytKq0jqj9VSbru2hnaq9Q/uE9qQOVcdNR6CzQ+ekzmOGCsOTkc6oZPQxpnQ1df11Jbr1uoO6M3rGelF6hXrtevf0Cfos/ST9Hfq9+lMGOgYhBgUGrQa3DfGGLMMUw12G/YavjYyNYow2GHUZPTJWMw4wzjduNb5rQjZxN1lm0mByzRRjyjJNM91tetkMNrM3SzGrMRsyh80dzAXmu82HLdAWThZCiwaLG0wS05OZw2xljlrSLYMtCy27LJ9ZGVjFW22z6rf6aG1vnW7daH3HhmITaFNo02Pzq62ZLde2xvbaXPJc37mr53bPfW5nbse322N3055qH2K/wb7X/oODo4PIoc1h0tHAMdGx1vEGi8YKY21mnXdCO3k5rXY65vTW2cFZ7HzY+RcXpkuaS4vLo3nG8/jzGueNueq5clzrXaVuDLdEt71uUnddd457g/sDD30PnkeTx4SnqWeq50HPZ17WXiKvDq/XbGf2SvYpb8Tbz7vEe9CH4hPlU+1z31fPN9m31XfKz95vhd8pf7R/kP82/xsBWgHcgOaAqUDHwJWBfUGkoAVB1UEPgs2CRcE9IXBIYMj2kLvzDecL53eFgtCA0O2h98KMw5aFfR+OCQ8Lrwl/GGETURDRv4C6YMmClgWvIr0iyyLvRJlESaJ6oxWjE6Kbo1/HeMeUx0hjrWJXxl6K04gTxHXHY+Oj45vipxf6LNy5cDzBPqE44foi40V5iy4s1licvvj4EsUlnCVHEtGJMYktie85oZwGzvTSgKW1S6e4bO4u7hOeB28Hb5Lvyi/nTyS5JpUnPUp2Td6ePJninlKR8lTAFlQLnqf6p9alvk4LTduf9ik9Jr09A5eRmHFUSBGmCfsytTPzMoezzLOKs6TLnJftXDYlChI1ZUPZi7K7xTTZz9SAxESyXjKa45ZTk/MmNzr3SJ5ynjBvYLnZ8k3LJ/J9879egVrBXdFboFuwtmB0pefK+lXQqqWrelfrry5aPb7Gb82BtYS1aWt/KLQuLC98uS5mXU+RVtGaorH1futbixWKRcU3NrhsqNuI2ijYOLhp7qaqTR9LeCUXS61LK0rfb+ZuvviVzVeVX33akrRlsMyhbM9WzFbh1uvb3LcdKFcuzy8f2x6yvXMHY0fJjpc7l+y8UGFXUbeLsEuyS1oZXNldZVC1tep9dUr1SI1XTXutZu2m2te7ebuv7PHY01anVVda926vYO/Ner/6zgajhop9mH05+x42Rjf2f836urlJo6m06cN+4X7pgYgDfc2Ozc0tmi1lrXCrpHXyYMLBy994f9Pdxmyrb6e3lx4ChySHHn+b+O31w0GHe4+wjrR9Z/hdbQe1o6QT6lzeOdWV0iXtjusePhp4tLfHpafje8vv9x/TPVZzXOV42QnCiaITn07mn5w+lXXq6enk02O9S3rvnIk9c60vvG/wbNDZ8+d8z53p9+w/ed71/LELzheOXmRd7LrkcKlzwH6g4wf7HzoGHQY7hxyHui87Xe4Znjd84or7ldNXva+euxZw7dLI/JHh61HXb95IuCG9ybv56Fb6ree3c27P3FlzF3235J7SvYr7mvcbfjT9sV3qID0+6j068GDBgztj3LEnP2X/9H686CH5YcWEzkTzI9tHxyZ9Jy8/Xvh4/EnWk5mnxT8r/1z7zOTZd794/DIwFTs1/lz0/NOvm1+ov9j/0u5l73TY9P1XGa9mXpe8UX9z4C3rbf+7mHcTM7nvse8rP5h+6PkY9PHup4xPn34D94Tz+49wZioAAAAJcEhZcwAALiMAAC4jAXilP3YAAAIuUExURf///wEBAYABAasBAcABAc0BNNUsLNsmJuAhIeMeHuYbNOgwMOosLOwpKe0mJt4jNOAxMeIuLuMsLOUqN+YoNOcyMugwMOkuLuosNusqNOwyMu0xMe0vL+UtNuYsNOcqM+gxMekuNuotNOosM+syMuwvNuwuNO0tM+0yMugwNekvNOoyMuoxNusvNOwuM+wxNu0xNe0vM+kuN+oxNeowNOsvN+syNuwxNewwN+0vN+0xNe0xNOowN+syNusyNeswN+wwNuwyNuwxN+0xN+0yNesxN+swNewyNewwNu0yNe0xNu0wNesyN+wxNuwyN+wxNu0xNe0yN+0xNuswN+wyNuwxN+wyNu0xNu0xN+0yNuwxN+wxN+wyNuwxN+0yNu0yN+0xNu0yNuwxN+wxNuwyN+0xNu0xNu0yN+0yN+0xNuwyN+wxNu0yN+0yNu0yNu0yN+0yN+wxN+wxN+wyNu0yNu0xN+0yN+0yNu0yN+0xN+0yN+wyN+wyN+wxN+0yNu0yN+0xN+0yNu0yN+0yN+0xN+wyN+wyN+0yN+0xNu0yN+0yN+0xN+0yN+0yN+0yNuwyN+0yN+0yN+0yNu0yN+0yN+0yN+0yNu0xNu0yN+0yN+0yN+wyN+wyN+0xN+0yN+0yN+0yN+0yN+0xN+0yN+0yNu0yN+0yN+0yN+0yN+wyN+0yN+0yN+0yN+0yN+0yN+4yN+0yN+0yN+0yN+0yN+0yN+0yN+0yN+0yN+0yN+0yN0VS/wsAAAC5dFJOUwABAgMEBQYHCAkKCwwNDg8QERITFBUWFxgZGhscHR4fICIjJCUnKCkqLC0vMDIzNTY4OTs8Pj9AQkNFRkdJSkxNTlBRVFVYWVxeYGJkZmhqbG5wcnR2eHp8foCBg4WHiYuNj5GTlZaXmZudn6GjpKanqqutrq+xsrO1tri5ury9v8DBw8TGx8nKzM3P0NLT1dbX2Nrb3N3f3+Dh4uPk5ebn6Onq6+zt7u/w8fLz9PX19vf4+fr7/P3+KrfUywAAG7hJREFUeNrt3flDVPX+x/EZUMB9QSSvS1iiSJpLZCqiZpo7Ii6ZXRNBr/t1+UIqWWa4I5JhXtS8hFoaCpd95vnffX+Y21VLBWbOmfP5nPN6/FbBzJnXZ2LO+8znvN+hlMq1IRHrrK1MCW2D46MVhdhl9HHYFvoHcKdAaYhNCu4A/wiFPvkVev8+SIGILQb9vRd+/SQUCoUmfgtcfleZiB3evQx8OzH2Dylbu+DpOqUiNlj3FLq2pvzvn2ffBE6MUTBiujEngJuzX/xXQ/cD9xYoGzHbgnvA/qF/+rcrHkHvrsGKR8w1eFcvPFrx1/8w4SxwZZoSElNNuwKcnfCq/5SypRNaNygjMdOGVujckvKa/zqrHjg5VjGJecaeBOpnvf4Hhu6Lwv2FSkpMs/BniO4b+safWf4QIrvTFJaYJG13BB4u7+vH3qoGruYqLzFH7lWg+q2+fzBc2gGtG5WYmGJjK3SUhvv1s/k3gKpMhSYmyKwEbuT398czKqLw78XKTby3+N8QrcgYwG8sbYZImeo28bpSK4tA89KB/VL2GaA2T+mJl/JqgTPZA/21cEk7tBUrP/FOcRu0l4Tj+M2ZdcCpLEUo3sg6BdTNjO+X08uj0FSoFMULhU0QLU+P+/eXJPj7InGK/eVckvBf7uuq2yTZldp1B85Zw5viPmcWifddV9IObZsSf9fFebVCJF4OXqdNL4tAc5EyleQoaoZImVOV1uKmgX5DJxKnjIooNDm4N2FcFVCXr2TFbfl1QNU4Rx+zuA06NqtuE3crtc0dbnyvm3sNqFbdJm5WatXAtenOP3DsToxlSljcsuyBe/egLbwP0b1DFLK4YcheV+/+zfw/oP495SzOe68eqHTz/p1Y3wfVbeJ0pZaMjjdv6LgjEq8k9Rob/GUEHn6svMU5Hz+EyJfJ6PL40Su7TIrEKdZf96PkPNkr+vuKxGvWD8ntbL7+T53VReIUmymxPplP+dJMC5F4eTLNZ/DzaUIi8YrNUUt+P/4P7wIHhmkBJF7DDgB3P/TiqUcfB358X2sg8Xn/RzycHby2Bbq3q26TeCq17d3Q4uHU9qkXgXOTtBIyUJPOARenenkIg77ohd9Wai1kYFb+Br1feD3xuqARODRcyyH9N/wQ0Fjg/YGM+ifQMEcrIv01pwH45ygjjmVNC3R/lqpFkf5I/awbWtaYcjg5F4DzU7Qu0rcp54ELOeYc0KCdPfB4lVZG+rLqMfTsHGTUMc2/DRweocWRNxlxGLg937TDGnkE+Gme1kdeb95PwJGRBh7Zp0+gZ4fqNnldpbajB558aubBvV0D1ORoleSVZX0NUPO22f9rrdY6yV+tNv6Dee4t4OhILZX8qSQ6Ctyaq3JSrGPLxSgTL+SJpyz6GmCyaV+hiMeV2gXg/GQ7DtawL6/FW7ZtfZnTABwbpYWTUces23Q47KAhGzbFWwWNwEHbbtM1Y6u8eFup2XqjzaRzwKWpWsHgmnrJ2lscU7Z1wdN1WsOgWvcUurbZenO5tzfmi6esb+sRa4myQCsZPAt80FDpk1+hd9dgLWaw+KSV3d++AS5P03oGyTS/NBFN2doFz9ZrRYNj/TP/tG+eVQ+cHKtFDYaxJ4EfZvnl5SR16IB4y38jS5YnbdyLeFupfRmBh8v99aImnAWu5mp1/S33qi/H9IVLkzDiULy1oRU6S/04INX94bLiqcxKH4+mjo31XqRV9qdF9yG6d4hvX9/SZoiUpWmh/SetLALNS/38ErOrgWvTtdZ+M/0aUJ3t7xcZ3twBbcVabX8pboOOzWHfv878OqBqnBbcP8ZVAXX5QXipGRVRaCrUmvtFYRNEKzIC8mqLmiGyJ13L7gfpeyLQXBScFzz+NFCbp5W3X14tcHp8kF5yuKQd2jaFtfiWr+OmNmgvCdo65l0HTmVp/W2WdQq4HsBP0PQ9UfilSO8Ai2uXXyAa0NqlsAmi5Rl6E9gpozzQV42yvgLqZup9YKOZdcBXQT7vK26DjhLVbZZW3AH/pnRGLXAmW+8Gu2SfAWpnBD2GAOxG8h/tDvyD3/eB+o72Zb8gsxK4ka8g7JB/Q3fEvGijb+998l+lVtoJrRsVxHO5V4GvJygI0034WneB/4Uv7/f3H/XfeLWFP0N031AFYa6h+6Lw80IF8VdjTwL1sxSEqdRz7k3WP4NOn3QX9J2ULZ3wTF1jXmvaFeAb1W0mVmrfAFfUZ/lNdduuXni0QkGYZsUjdbjv24K7fuuQ6YdKbb9mi/TLmBPAzdkKwhyzbwInxiiIfrB7fpf/KjXN0xuIdy4B5yYqCBNMPAdcekdB9Ncgf0xC8oNPfoXev2uG9EDYOS3cd4YdBBoLFMTAjD4GNMxREF6a0wAc0/zdgVvTAt3bVbd5V6lt74aWNQoiHlMvAt9NVhDemPwdcHGqgoizbtvZA49XKQgvrHoMPTtVqcXvg0bg0AgFkWwjDgGNHyiIRIw8CtyaqyCSa+4t4OhIBZGg1b9D945UBZE8qTu64ffVCiJxOTXA91MURLJM+R6oyVEQjvwh+LwHnnyqIJLj0yfQ87k+6pwy7zZwRKdgySgyjgC35ykIB4vfw8Dt+QoiKX8mDuvyjj7MdIIm/y0jzquMSEZpfF6lsRt/FnQBx126KOmmuQ26dO5epXYUaNDXQa4Zri8t3RL7In64gnDRSm0XcYO2QCWFNuq5QJtPkyS2RXqtgnDOWm37T5r3fwSO6+YUh4w+Bvz4voJIjmEHgTu6LdARBXd0q2ty6YZspyo1NRlIvonfApffVRCJefcy8K3auyS7btuqJkQJU2Mtr6j9W4LU0tBDQ/cD99R4M04L7qmZrJfU8jhuauPtuQln1Ww+LtOuAGc1QMHbum1LJ7RqzMcAbWiFzi2q1LymAUsDpqFh5tRtGm03MBrXaJLlDyGyO01B9Efabg3KNcpb1Rrn3E+5V4HqtxSEOcKlHdC6UUH0ZWMrdJSGFYRR8m8AlZkK4k0yK4Eb+QrCNEP2RuH+IgXxeovuQ3TvEAVhoKXNEClT3fa6Sq0sAs1LFYSZss8AtTMUxKvMqAXOZCsIY+u2knZoK1YQf1XcBu0lqtRMNrMOOJWlIF6WdQqom6kgzJZRHoWmQgXxosImiJZnKAjjLWmCaHm6gvhDenkUmpYoCGs+I6/nKYiYvOs6k7Kpbtuk6uTlGnaTsrDor00tcHq8ghh/GqjVp5Bd53l7ItBcFPQYipohskfn/7ZZ3ATRikBX2BkVUWharPeCfcZVAXUB3oWSXwdUjdM7wUrFbdCxOaC1Snhzh75ttNn0a0B1IL/Pz64Grk3Xe8Beabsj8GBZ8F74sge6M8p+C4O4h1V7m30isxKofy9IL/m9et1V4hexDhuBqdvC6sPiJ4HqbaQOWD4z+MsIPPw4CC/144cQ+VK9B/3ko2D084x1ff1I6+0vY04AP/i8P9esH9Rv25/WP4OurT7ujJiytQuerddK+5HPp4doxoy/6zY/z22KTfdSpeZfH94FDvhwYt6wA8DdD7XCfjb6uC9nlWqibTCsbYEuf02JTtnepVniwTD1EnBukn9e0KRzwKWpWtkgGPRFL/y20i8vZ+Vv0PuF5jAHRUEjcHC4H17K8INAY4HWNDhGHQMa5tj/QuY0AMdGaUUDZU0LdH+WaveLSP2sG1rWaDWDJucCcH6KzS9hynngQo7WMoB1284eeLzK3hew6jH07FSlFkzzbwOHR9h58CMOA7fnaxWDauQR4Kd5Nh76vJ+AIyO1hgG2+gn07LCubkvd0QNPVmv9gu3tGqDGsqInpwaoeVurF3QW/hGz9ONC3Dp9PGrN6aPFJ+oS8MLd6ksk4gJrLplaf3FanDfZji+rYl8LTtZ6yUt1mw3bBHyxIUNcYPwGLd9shRPnDTN7a2xsE/IwrZO8ksE3Jfjs9g9xnrG3g/nuxjtxXsq2Lni6zrTDWvcUuralaH3kjQxsgeDTZhPiQt12ALi7wJwDWuDXNj/iAqPafvm6wZo4L9Zw0YjW+NP83dpSXKjbTGl16/umwuICI5qMB6KduzjPgPEOARmkIS5Y7u1gndgIo+VaB4nHhLPA1Vxvnjz3aoCGx4nzwqWeDZPc0AqdpWGtgcTNozG+ARyYLM7zZID6oiCOqhcXLHsAkd1pyXvCtN0ReLBMyUvisquBa9OT9XTTrwHV2cpdHKnbNndAW3Fynqy4DTo2q1ITp+TXAVXj3H+icVVAXb4SF+dkVEShqdDtpylsgmhFhvIWRxU1Q2RPuptPkb4nAs1FylqcNv40UJvn3hPk1QKnxytpcaFuK2mHtk0u1VLhTW3QXqJKTVz623gdOJXlxkNnnQKu5yljce+sNAq/uHBWuqQJouXpSljcvh5Q7vD1gIzypFzLkKDL+gqom+nkQ86sA77KUrbiumJnK6tYHVisXCUZZtQCZxzafZB9BqidoVQlOdLKItC81ImHWtoMkbI0ZSpJ49COW092C0vQZVYCNxLcQZN/w5P7NCTwNrZCRyJ3mYVLO6B1o5KU5Mu9Cnwd9/29E7728N5kCXzdlkhnhVhXCFVq4pWFP0N0Xxw9bYbui8LPC5WgeGfsSaB+wN3EZtUDJ8cqP/HUhmfQuWVAfRxTtnjWy0TkRdOuAN8MoG6b8A1wZZqSE+8N3tULj1b098dXPILeXYOVmxhhwd1+9yIdut+wiRcSdGNOADdn9/2Ds28a0LNa5CX9mpRm6JQ3Cbp3LvU5+WTit8Cld5SVmGZQXzOnYhOyBikpMVDBnTfMZR92ELhToJTETKOPvXbO6vs/AsdGKyMx1toW6N7+l7otZXs3tKxVPmKyqReB7ya//C8nfwdcnKp0xPC6bWcPPF714r9a9Rh6dqpSE/N90AgcGv7HPw4/BDR+oFzEBiOPArfmxv5hbgNwdKRSEUus/h26d6SGQqk7uuH31UpE7JFTA3w/Zcp5oCZHeYhNUj/vgf+0Qc/nqUpDLDPvXwCN85SE2PfmbQT4l968YuVpQ9t/dNogKthEkkSXysRS+pJCbKWvh8VS2pgjttKWSLGVNqOLpXQbkNhKN2CKrZWabn0XS6npiNhK7Z7EUmq0J7ZSi1OxlJpLi63U1l9spYEqYimNshJbaYigWErjW8VWGpwtttrYCh2l4fgfIFzaAa0blaQkWWYlcCM/sQfJvwFUZipNSaZF9yG6d0iiDzNkbxTuL1KekrxKrSwCzUudeKilzRApU90mSTKjFjiT7cyDZZ8BamcoVUmG4jZoLwk79XDhknZoK1au4rqsr4C6mU4+5Mw64KssZSvuKmyCaHmGsw+aUR6FpkKlKy5K3xOFX4qcf+AlTRAtT1fC4pa868ApVz7fs04B1/OUsbgiVlltCrv06JucrQNFnht/Gqh18W9jXi1werySFqcVNUNkj6tnpel7ItBcpKzF2esBFUm5HlDYBNGKDOUtzsmvA6rGuf9E46qAunwlLk7VUps7kvcdWHEbdGxW3SaOyK4Grk1P1tNNvwZUZyt3SdyyBxDZncR9X2m7I/BgmZKXBHmy49ah3cISbO/Ve3KvQ2YlUP+e8pf4K7VSz7qDbGiFzlLVbRKnCWc9vL839ypwdoJWQeIR66zgWUe8wYl0hZBAG7ofuPeRl4fw0b1+d04VeW7WDwZ0gR5zAvhhllZDBiBlaxc8W+/9gax/Bl1bU7Qi0l8TvwUuG9FBd9rlPue0iDwXmzllSO/ywX1NyBL5n2EHDJsaseAucGCYVkb68v6PwHGj5qyOPv7aqbAizys1Myel9WvKmwTbpHPApanmHdjUS8C5SVoheZ2Vv0HvF0ZOBx70RS/8tlJrJK+u1A4CjcbOZS9ofMNMeQm2OQ3AsVHmHuCoY0DDHK2U/EnqZ93Qssbsg1zTAt2fpWq15EWTzwMXckw/zJwLwPnJWi95btVj6Nk5yPwDHbSzBx6v0orJf404DNyeb8fBzr8NHB6hVZNQKBSa9xNwdKQthzvyCPDTPK2bhFJ39MCT1TYd8uon0LNDdVvgvV0D1OTYddA5NUDN21q9YLP0j5iFHxei08eXTtSPjNQaBpXVhbtVl0jEYdZfMrXm4rQ4XvRcAM5PsfklTLHja0Fxmi+2CVixIUMc5psNWsZvhROnxbbGDvfDSxlu9iZkcbpS89dNCQbf/iFO893tYMbeeCdOW9sCXdt9dSNuyvYuaFmrtfU5n7ZAMLDZhDjtQ782n4m1+flQK+xbvm77ZVSDNXHau/5uuBhrbfmu1tmPfN/q1pimwuKwQDQZN6KduzgtIOMdDBikIU5Xal9G4OHHQXipH3s7wkicNu1KgEaaTTgLXJmmVfeHDa3QuSUwwyTDWzwb2ykOC+AYX48GJovTFgZxgLono+rFYWm7I/BgWfBe+LIHENmdpneAvaZfA6qzg/jSs6uBa9P1HrBVcRt0bA4H88WHN3dAW7HeBVYaVwXU5Qc3gPw6oGqc3gn2WdwE0YqMIEeQURGFpsV6L1gmfU8EmouCHkNRM0T2pOv9YJO8WuD0eAUx/jRQm6cg7KlVNrVBe0lYSYRC4ZJ2aNukLCyRdQq4rr82f3wKXQdOZSkIGyxpgmi5zvOen/+XR6FpiYIwv8Iuj0JToYJ4UWETRMszFITZZtbpM/J1Z1J1MxWE8dWJvlV6hWLVsGbLPgPUzlAQrzKjFjiTrSDMtLQZImXaSfUaaWURaF6qIAykPax9WhTEvc02yL+huwf6lFkJ3MhXEGZVaqUd0LpRQfRlYyt0lKpuM8hb1cDVXAXRt9yrQPVbCsIUyx/qnpf+1227I/BwuYIwwtB9Ufh5oYLor4U/Q3TfUAXhvVn1wMmxCqL/xp4E6mcpCI+lqMNGPGJ9WFIUhJfU2yhOgeqAZaYVj6B3l7rKxWHwrl54tEJBeFWp7QfuLVAQ8VkQjK6vZpp9U52UEzLmBHBztoJIfqW2tQuerlMQiVj3FLq2qW5LMk0PcYTPZ8yYKTa3SfN2EzbIz9O9jDTsIHBHk84dUXAHODhMQSSHZpU6avQxX060NbNS296tKdHOWtsC3dtVt7lu8nfAxakKwklTLwLfTVYQ7lr5GHp2qlJzum7b2QOPVykIFw0/BDR+oCCc90EjcGi4gnDL3Abg6EgF4YaRR4GGuQrCFak7uuH31QrCLat/h+4dqQrCeVPOAzU5CsI9OTXA+SkKwmmfPoGez/Vnwd0Pt8974MmnCsJRIw4Dt+crCLfNuw0cHqEgHI70iCq1ZNRtR4Db8xSEPsx0gqYyAr5XGZG80vh7lcYO0QWc5H/U6aKkM6dgR4FbunSeZHNv6eughMW+tFTxm3Qj9EV8grRdxEOrtAUqEdqo5yltPk3AGm2R9lZs2/8aBTFgo48BDXMUhJfmNADHdMPVABU06rZAAww7CDTqVtcBVWq6IdsUajIwQO9cAs6pFYYRJp4DLr2jIPpHTYjMqtu2qbFWf6n9m3HU0rCfFtxV403jDN0P3FUz2TdTy2NDqY13n6ZdAb5Rs3kDTfhGAxTeaP0z6NyqSs3Mum1LJzzT6JpX04Alw2lo2GtptJ35dZvGNb66UvtSQ0UtsPwhRL5U3faS3KvA16rUzK/bvtaI8j/Z2AqdpWEFYb5waSe0blQQ/5VZCdzIVxB2yL8BVGYqiFAoFFp0H6J7hygIWwzZG4X7ixREKK0sAs1LFYRNljZDpCwt6DHMqAXOZOv9YJfsM0DtjGCHUNwGHSWq1Oyr20raoa04wAlkfQXUzdRbwUYz64CvsoL68gubIFqeofeBnTLKo9BUGMjXnr4nCr8U6U1gr6JfILonPXgvPO86cCpL7wCrz/tOAdfzAnm+v0mVmu3ruKkN2oNVcY8/DdTmafF98AlaC5weH6BzpWaIBPFcyZ+1SwSag1K7ZFQEt0r1pcImiFYE4qpRfh1QNU5r7h/jqoA6/++sCm/uCPg3M75U3AYdm31et2VXA9ema7X9Zvo1oNrXe1S0G8m3/L47UPtAfc3X+7Lfq9cOfF/LrATq3/NjpVbaCa3qWeFrG/x5L+KEs7rrNAByrwJnfXYXuO73Dwj/9d8Yuh+495GWNgg+uuerHrXqcRUoY08CP/ij51zK1i54tl6LGhzrn0GXH7p9/u0b4LL6ugbKtMvAt9ZPxPnkV3XUDmLd5oNZZMMOaJZBQC24CxyweArk+z8CxzX9M5BGHwd+fN/WSk3zu4LN4nl6k84BlzTxPsCmXgLOTbLvwFf+Br1faGZtoA36ohd+W2lbpaZp4RIKhUIFjcBBq+q2OQ3AsVFaOxl1DGiYY83xpn7WDS1rtHASCoVCa1qg+7NUOw528nngQo5WTWJyLgDnJ9twqKseQ89OVWryvG7b2QOPVxl/nCMOA7fna8HkRfNvA4dHmH2Qc28BR0dqteRlI48Ct+aaXKnt6IEnq7VU8lern0DPDmPrtrdrgBpVavLquq0GqHnbzIP71Oz/tcSMD+ZPTTypOQL8NE9LJK837yfgiHElkRXlpHjNxItRtlzIE88Z9zVA7CuUKVoZ6dsUs76AterLa/G8bjNo68uof9q1bUg8N6cB+KcBmw4LGoFDw7Ui0n/DD5mw3dvOrfLiOQNutJl60dKblMRrk84BFz28xXFtC3RvT9FKyMClbO+GlrUePbvdN+aL5zxs6/Gh7S1RxGuxhkofJv15/dCMSjz3ya/Q+/ckt7J71x9tAMVrE78FLr+bzKdc/9QnDVjF87ptaxc8TV775jEngJuzFbw4YdYPwIkxyXkynw0dEK8lb2RJbNzLx4pcnPNxcoZFTbviw0Fb4rUJZ4ErLg9/2NAKnVvCSlucFd7i9oDUzP/z63BZ8ZzLo6kX+nmst3htyN4o3F/oymOn7Y7Aw2UKWdyy7AFEdqc5/8C514DqbCUs7smuBq5Nd/phi9ugY7MqNXG3btvcAW3Fjj7muCqgLl/hitvy64Cqcc494OImiFZkKFlxX0ZFFJoWO/Ro6WURaC5SrJIcRc0QKUt34qHyaoEzqtQkeXXbGaA2L/Ez6E1t0F6iSk2SWbeVtEPbpgTfdVmngOt5ilOSK+86cCorkYdY0gTR8nRlKcmWXh6FpiUJ/n6hghQvFCbyl3NmXcJ/uUUSPGetmxn3OXOxIhTvFMd3tcCpqxUiidRt8VynXdoMkbI0pSfeSiuLQPPSAfxGRkUU/r1Y0Yn3Fv97QHsT8m8AVZnKTUyQWQnc6N+usHBpB7RuVGhiio2t0FHaj7rtrWrgaq4SE3PkXgWq3+rrx5Y/dOlODJEE6rbdEXi4/I0/M3Sfe/fAiSRg4c8Q3feGPk2z6oGTY5WUmGfsSaB+1mv+a4rrfR9EEhDrePPK3qRJ6bgjEr/X9hpb8Qh6dw1WQmKuwbt64dGKP1dq+4F7CxSPmG3BX/vrzr6ZxP6+IvH7c2fzWGf1dQpGbLDuxZkSHsy0EInfC9N8YtOEBikTscWgP+ao/QO4U6BAxCYFd4B/hLZ5NUFTJH6jj8O2UErlWkUh9llbmfL/6DxQrO0nejQAAAAASUVORK5CYII="/></div>
  </xsl:variable>

  <!-- Đưa vào Thông tin người bán -->
  <xsl:variable name="TTNguoiBan">
    <table style="font-size:11pt; table-layout:auto;">
      <col width="20%" />
      <col width="15%" />
      <col width="30%" />
      <col width="35%" />
      <tr>
        <td align="left" valign="middle" style="max-width:200px; max-height:150px; white-space:nowrap;">
          <xsl:choose>
            <xsl:when test="(/inv:invoice/inv:invoiceData/inv:userDefines/inv:Logo) and (/inv:invoice/inv:invoiceData/inv:userDefines/inv:Logo!='')"><img style="max-width:200px; max-height:150px;" src='data:image/png;base64,{/inv:invoice/inv:invoiceData/inv:userDefines/inv:Logo}' /></xsl:when>
            <xsl:otherwise></xsl:otherwise>
          </xsl:choose>
        </td>
        <td colspan="3" align="left" valign="middle" style="padding-left:7px;">
          <span style="font-size:14pt;"><b><xsl:value-of select="translate(/inv:invoice/inv:invoiceData/inv:sellerLegalName,$lower,$upper)"/></b></span><br />
          Mã số thuế <i>(Tax code)</i>:
          <span style="font-size:11pt;"><b><xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerTaxCode"/></b></span><br />
          Địa chỉ <i>(Address)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerAddressLine"/>
        </td>
      </tr>
      <tr>
        <td colspan="4" align="left" valign="top">Số tài khoản <i>(Account No.)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerBankAccount"/></td>
      </tr>
      <tr>
        <td colspan="4" align="left" valign="top">Tại ngân hàng <i>(At)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerBankName"/></td>
      </tr>
      <tr>
        <td colspan="2" align="left" valign="top" style="border-bottom:1px solid #000">Điện thoại <i>(Tel.)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerPhoneNumber"/></td>
        <td align="left" valign="top" style="border-bottom:1px solid #000">Fax: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerFaxNumber"/></td>
        <td align="left" valign="top" style="border-bottom:1px solid #000">Email: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerEmail"/></td>
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
        <td align="center" valign="middle">&nbsp;</td>
        <td align="center" valign="middle">
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
        <td align="left" valign="top" style="font-size:9pt; white-space:nowrap; padding-top:2px;">
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
    <table style="color:BLACK">
      <xsl:if test="(/inv:invoice/inv:invoiceData/inv:userDefines/inv:HoaDonThayThe) and (/inv:invoice/inv:invoiceData/inv:userDefines/inv:HoaDonThayThe)!='' and (/inv:invoice/inv:invoiceData/inv:userDefines/inv:HoaDonThayThe!=7)">
      <tr>
        <td style="font-size:9pt; text-align:center;"><xsl:value-of select="/inv:invoice/inv:invoiceData/inv:userDefines/inv:HoaDonThayThe"/></td>
      </tr>
      </xsl:if>
      <tr>
      <xsl:choose>
        <xsl:when test="(/inv:invoice/inv:invoiceData/inv:userDefines/inv:LoaiHDView) and (/inv:invoice/inv:invoiceData/inv:userDefines/inv:LoaiHDView=1)">
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
      <col width="45%" />
      <col width="20%" />
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
        <td align="left" valign="top">MST <i>(Tax code)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerTaxCode"/></td>
        <td align="left" valign="top">Tel: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerPhoneNumber"/></td>
        <td align="left" valign="top">Email: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerEmail"/></td>
      </tr>
    </table>

    <!-- Thông tin chi tiết hóa đơn (Hàng hóa, dịch vụ) -->
    <table cellspacing="0" cellpadding="2" style="color:BLACK; table-layout:fixed;">
      <col width="6%"/>
      <col width="37%"/>
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
        <td align="center" class="borderTD">(7) = (5) x (6)</td>
      </tr>
      <xsl:for-each select="/inv:invoice/inv:invoiceData/inv:items/inv:item">
      <tr>
        <td align="center" class="borderTD"><xsl:number format="1"/></td>
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