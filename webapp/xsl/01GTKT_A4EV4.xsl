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
        <script type="text/javascript" src="http://www.ts24.com.vn/web/media/js/jquery-1.8.2.min.js"><xsl:comment/></script>
        <script type="text/javascript" src="http://www.ts24.com.vn/web/media/js/details.js"><xsl:comment/></script>
        <script type="text/javascript" src="http://www.ts24.com.vn/web/media/js/qrcode.js"><xsl:comment/></script>
        <script type="text/javascript" src="http://www.ts24.com.vn/web/media/js/jquery.qrcode.js"><xsl:comment/></script>
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
            <table cellspacing="0" cellpadding="2" style="table-layout:fixed;">
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
                  Cộng tiền hàng <i>(Sub total): </i>VND
                </td>
                <td class="borderTD">&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2" align="left" class="borderTD">
                  Thuế suất GTGT <i>(VAT rate):</i>
                </td>
                <td colspan="3" align="right" class="borderTD">
                  Tiền thuế GTGT <i>(VAT): </i>VND
                </td>
                <td class="borderTD">&nbsp;</td>
              </tr>
              <tr>
                <td colspan="5" align="right" class="borderTD">
                  Tổng cộng tiền thanh toán <i>(Total of payment): </i>VND
                </td>
                <td class="borderTD">&nbsp;</td>
              </tr>
              <tr>
                <td colspan="5" align="right" class="borderTD">
                  Tổng cộng ngoại tệ có thuế <i>(Total foreign currency with VAT):</i>
                </td>
                <td align="right" class="borderTD">&nbsp;</td>
              </tr>
            </table>
          </xsl:when>
          <!-- TRƯỜNG HỢP 2: CHI TIẾT HÓA ĐƠN CÓ BẢNG KÊ-->  
          <xsl:when test="(inv:invoiceData/inv:userDefines/inv:SoBangKe) and (inv:invoiceData/inv:userDefines/inv:SoBangKe)!='' and (inv:invoiceData/inv:userDefines/inv:SoBangKe)!=0">
            <table cellspacing="0" cellpadding="2" style="table-layout:fixed;">
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
              <xsl:choose>
                <xsl:when test="(inv:invoiceData/inv:currencyCode) and (inv:invoiceData/inv:currencyCode)!='' and (translate(inv:invoiceData/inv:currencyCode,$lower,$upper)!='VND' or translate(inv:invoiceData/inv:currencyCode,$lower,$upper)!='VNĐ')">
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
                        <xsl:when test="(inv:invoiceData/inv:totalAmountWithoutVAT) and (inv:invoiceData/inv:totalAmountWithoutVAT)!=''"><xsl:value-of select="format-number(inv:invoiceData/inv:totalAmountWithoutVAT, '#.##0,00', 'vndong')"/></xsl:when>
                        <xsl:otherwise>&nbsp;</xsl:otherwise>
                      </xsl:choose>
                    </td>
                  </tr>
                </xsl:when>
                <xsl:otherwise>
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
                </xsl:otherwise>
              </xsl:choose>
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
                  Cộng tiền hàng <i>(Sub total): </i>VND
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
                    <xsl:when test="(inv:invoiceData/inv:userDefines/inv:TyLeVAT) and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!='' and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!=0 and translate(inv:invoiceData/inv:userDefines/inv:TyLeVAT,$lower,$upper)!='X' and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!=-1 and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!=-2"><xsl:value-of select="format-number(inv:invoiceData/inv:userDefines/inv:TyLeVAT, '#.##0,##', 'vndong')"/>%,</xsl:when>
                    <xsl:when test="(inv:invoiceData/inv:userDefines/inv:TyLeVAT) and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!='' and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)=0">0%,</xsl:when>
                    <xsl:when test="(inv:invoiceData/inv:userDefines/inv:TyLeVAT) and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!='' and (translate(inv:invoiceData/inv:userDefines/inv:TyLeVAT,$lower,$upper)='X' or (inv:invoiceData/inv:userDefines/inv:TyLeVAT)=-1)">X%,</xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
                <td colspan="3" align="right" class="borderTD">
                  Tiền thuế GTGT <i>(VAT): </i>VND
                </td>
                <td align="right" class="borderTD">
                  <xsl:choose>
                    <xsl:when test="(inv:invoiceData/inv:totalVATAmount) and (inv:invoiceData/inv:totalVATAmount)!='' and translate(inv:invoiceData/inv:userDefines/inv:TyLeVAT,$lower,$upper)!='X' and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!=-1 and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!=-2"><xsl:value-of select="format-number(inv:invoiceData/inv:totalVATAmount, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
              </tr>
              <tr>
                <td colspan="5" align="right" class="borderTD">
                  Tổng cộng tiền thanh toán <i>(Total of payment): </i>VND
                </td>
                <td align="right" class="borderTD">
                  <xsl:choose>
                    <xsl:when test="(inv:invoiceData/inv:totalAmountWithVAT) and (inv:invoiceData/inv:totalAmountWithVAT)!=''"><xsl:value-of select="format-number(inv:invoiceData/inv:totalAmountWithVAT, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
              </tr>
              <tr>
                <td colspan="5" align="right" class="borderTD">
                  Tổng cộng ngoại tệ có thuế <i>(Total foreign currency with VAT):</i>
                </td>
                <td align="right" class="borderTD">
                  <xsl:choose>
                    <xsl:when test="(inv:invoiceData/inv:totalAmountWithVATFrn) and (inv:invoiceData/inv:totalAmountWithVATFrn)!=''"><xsl:value-of select="format-number(inv:invoiceData/inv:totalAmountWithVATFrn, '#.##0,00', 'vndong')"/></xsl:when>
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
              <xsl:choose>
                <xsl:when test="(inv:invoiceData/inv:currencyCode) and (inv:invoiceData/inv:currencyCode)!='' and (translate(inv:invoiceData/inv:currencyCode,$lower,$upper)!='VND' or translate(inv:invoiceData/inv:currencyCode,$lower,$upper)!='VNĐ')">
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
                        <xsl:when test="(inv:unitPrice) and (inv:unitPrice)!='' and (inv:unitPrice)!=0"><xsl:value-of select="format-number(inv:unitPrice, '#.##0,00', 'vndong')"/></xsl:when>
                        <xsl:otherwise>&nbsp;</xsl:otherwise>
                      </xsl:choose>
                    </td>
                    <td align="right" class="borderTD">
                      <xsl:choose>
                        <xsl:when test="(inv:itemTotalAmountWithoutVat) and (inv:itemTotalAmountWithoutVat)!='' and (inv:itemTotalAmountWithoutVat)!=0"><xsl:value-of select="format-number(inv:itemTotalAmountWithoutVat, '#.##0,00', 'vndong')"/></xsl:when>
                        <xsl:otherwise>&nbsp;</xsl:otherwise>
                      </xsl:choose>
                    </td>
                  </tr>
                  </xsl:for-each>
                </xsl:when>
                <xsl:otherwise>
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
                </xsl:otherwise>
              </xsl:choose>
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
                  Cộng tiền hàng <i>(Sub total): </i>VND
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
                    <xsl:when test="(inv:invoiceData/inv:userDefines/inv:TyLeVAT) and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!='' and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!=0 and translate(inv:invoiceData/inv:userDefines/inv:TyLeVAT,$lower,$upper)!='X' and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!=-1 and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!=-2"><xsl:value-of select="format-number(inv:invoiceData/inv:userDefines/inv:TyLeVAT, '#.##0,##', 'vndong')"/>%,</xsl:when>
                    <xsl:when test="(inv:invoiceData/inv:userDefines/inv:TyLeVAT) and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!='' and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)=0">0%,</xsl:when>
                    <xsl:when test="(inv:invoiceData/inv:userDefines/inv:TyLeVAT) and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!='' and (translate(inv:invoiceData/inv:userDefines/inv:TyLeVAT,$lower,$upper)='X' or (inv:invoiceData/inv:userDefines/inv:TyLeVAT)=-1)">X%,</xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
                <td colspan="3" align="right" class="borderTD">
                  Tiền thuế GTGT <i>(VAT): </i>VND
                </td>
                <td align="right" class="borderTD">
                  <xsl:choose>
                    <xsl:when test="(inv:invoiceData/inv:totalVATAmount) and (inv:invoiceData/inv:totalVATAmount)!='' and translate(inv:invoiceData/inv:userDefines/inv:TyLeVAT,$lower,$upper)!='X' and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!=-1 and (inv:invoiceData/inv:userDefines/inv:TyLeVAT)!=-2"><xsl:value-of select="format-number(inv:invoiceData/inv:totalVATAmount, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
              </tr>
              <tr>
                <td colspan="5" align="right" class="borderTD">
                  Tổng cộng tiền thanh toán <i>(Total of payment): </i>VND
                </td>
                <td align="right" class="borderTD">
                  <xsl:choose>
                    <xsl:when test="(inv:invoiceData/inv:totalAmountWithVAT) and (inv:invoiceData/inv:totalAmountWithVAT)!=''"><xsl:value-of select="format-number(inv:invoiceData/inv:totalAmountWithVAT, '#.##0,##', 'vndong')"/></xsl:when>
                    <xsl:otherwise>&nbsp;</xsl:otherwise>
                  </xsl:choose>
                </td>
              </tr>
              <tr>
                <td colspan="5" align="right" class="borderTD">
                  Tổng cộng ngoại tệ có thuế <i>(Total foreign currency with VAT):</i>
                </td>
                <td align="right" class="borderTD">
                  <xsl:choose>
                    <xsl:when test="(inv:invoiceData/inv:totalAmountWithVATFrn) and (inv:invoiceData/inv:totalAmountWithVATFrn)!=''"><xsl:value-of select="format-number(inv:invoiceData/inv:totalAmountWithVATFrn, '#.##0,00', 'vndong')"/></xsl:when>
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
    <div style="position:absolute; z-index:100; margin:250px 0px 0px 710px;"><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAwAAAHbBAMAAAANF2b3AAAAMFBMVEUAAACZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmy/KNBAAAAD3RSTlMAd7sRRDMiiGbMVaqZ3e5rYYK9AAAE2ElEQVRIx61WW49LURTeddqjxENPVVAlW+tO5LQdRHhoxy1UOFMjrg+nlbg/tCVUDelgdARJXZLG5aEl7iI145Lw0pEQlxdEJMKDH+DBT2B9q1FVTRH7YX/d+6zrt/bau+LXEVsAmDZGEjzjzdGXAdudeQKPiZV1ss4ar2lODYTq7JgMDpo62OYUVh/BX+64IDIpXCbo5c19p7JsG3rrpkPEZ4PIU4GQ4nsrBLZOCGqDDQKpwPSJIGBoX7EuFgeDTpkMPKWfg91TIdntYnfvaFZDQThI6aTgspUlmdRzBJbs4K+1xAKh/eR2lH14mSSH6JL07iJA1VhHOcAwAAS6wJU73FfBJqxgNMSZ1CFqSwK6PZ8JSlVyOyDSNR3ARRHLhzNuweZjDWAdhb1OVu9dhBVMtjbddDgYJEO5UUBluBWfblC2bjVPvGjMy4FzxIvCoUQ9BR1M2CWtdIhYvSMrJLkKMku7ThskMihI39qDLGJaEiAZrtofnjNQAEUSESf1PFXfDUfsRziLAFeXCVBf0bcrQiWg0eJMKAuDBNucnzjcCeQoMjuPWPrWJv5PRrCitrACT42D8+sJCRxaPuUerqnmnMxH0UawqVojHRBf5SVNtyUrCebCUeC8ysXdYNDRZyJuJL3kXUmFTOi/ImgrOakfxt5Da29NwRH6tlmEQtaB2SiX6zlKENj7Af52u9DalrP8dTG+FQSitjsk5SdeEsxzDPnMMlkqY6ikUxWtjyrg2sTR8MDv0OE7E0SWmFXrzaZkNR+X3HwvhSs/mTmYwaZC95nSFk1/txpN95/KC6H7+xPIes2jPET9SOxQmq10VMgfW4nM3G3ivOBWbO/iszvMBFgQqBYDxF6cak65jUR2xI6P/o7r3RLdYR8l4H3O1CzuOh2nwIijU4VSBoCluA4FW2FsFsYWiMaRZrBMYJzzABm9cpKGATMb36NU6uS+102J3JdBFJnYW4qiX0yiKKI3z+C+0LYW/+w9suBFWZKMEBPXpUDwP54Cg+bsZrCrzyuiYBDpZQiEbATKk6lG0zj9VwEv3yIVydW85oMHJQqWtAWYtx+gzaNCSWAZkogzhdUaK6z02yt1zxlAdDZ6PeQFbBkBUMcATiYByxbWPS8sySsef0OdYOqE5JBq1GHew9S1G/CgyBqDnh7mOje12n9g8MZFNnZPIs0KwF9N06q3eNg3MROR6dxjg8JlAJQ3EVCTuGqtJtZKQSv7x7sojmltGYTOQVC/Y7eMTQFwjs/jL0obnnkDZVQmHkPLrazgqN+fSHBEBOAIhLCB2tjLJOcOFXTQiqvodjAvQWjJ8WfF0ViEjhQc7eEjNcH4caSOVf90jRkLK1JBhJ7f/wvCWMUw7iE8PbCeLqIAPomLdyI46ASFo5keaQN0VwMsYJVpA7uLMO86cZpi2SqsBGI1E5nz5hu9c7tX9RTWG2A9vU5vMyTE/C8uJHY5jHX9eG4CepGztvag5MaDZHcI3tc7E01PyN/2iuI/DMsD44mCDikn0WbJdxZd8pjmHg3l2MEKKy7gT98gLUIrVXJ+t5sXB6MF82lmft4bbD71g3KfyLNpSd7Rt//sPcudU/qn9q1dZPtm/JTDMk20erJa30SNt/Ak3MIowGK+hV1sS2MY482Y2MQLu7jR5zdEPnQDlulMswAAAABJRU5ErkJggg=="/></div>
  </xsl:variable>

  <!-- Đưa vào Hình hóa đơn xóa bỏ -->
  <xsl:variable name="HinhHDXoaBo">
    <div style="position:absolute; z-index:100; margin:0px auto;"><img src="data:image/png;base64,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"/></div>
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
        <td colspan="2" align="left" valign="top" style="border-bottom:1px solid #0a51a1">Điện thoại <i>(Tel.)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerPhoneNumber"/></td>
        <td align="left" valign="top" style="border-bottom:1px solid #0a51a1">Fax: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerFaxNumber"/></td>
        <td align="left" valign="top" style="border-bottom:1px solid #0a51a1">Email: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:sellerEmail"/></td>
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
      <col width="25%" />
      <col width="15%" />
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
        <td align="left" valign="top">Hình thức thanh toán <i>(Payment method)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:payments/inv:payment/inv:paymentMethodName"/></td>
        <td align="left" valign="top">Loại tiền <i>(Currency)</i>: <xsl:value-of select="/inv:invoice/inv:invoiceData/inv:currencyCode"/></td>
        <td colspan="2" align="left" valign="top">
          Tỷ giá <i>(Exchange rate)</i> :
          <xsl:choose>
            <xsl:when test="(/inv:invoice/inv:invoiceData/inv:exchangeRate) and (/inv:invoice/inv:invoiceData/inv:exchangeRate)!='' and (/inv:invoice/inv:invoiceData/inv:exchangeRate)!=1"><xsl:value-of select="format-number(/inv:invoice/inv:invoiceData/inv:exchangeRate, '#.##0,##', 'vndong')"/></xsl:when>
            <xsl:otherwise>&nbsp;</xsl:otherwise>
          </xsl:choose>
        </td>
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
          <span style="position:absolute; margin-left:-35px; margin-top:-5px;"><img src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACYAAAAmCAMAAACf4xmcAAAAbFBMVEUAAAAAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhFnWh4EAAAAI3RSTlMA+/PopnFsQTAZCwT21M+8fmUqJyTu27ifeVFFN+LIlllOOc5ANW4AAACuSURBVBgZ7cFHAoMgAATAtffeNX3//8cA5haE5O4MTn9oQtglE0NYxR65wiZySV5gEeUUWpj1A6UYRulIZYNJNnEXwGThRwWD2OGufOBYVlJxlgwaif+CFFJxI2hkgcsKQjJQ8mJoRAWFDcBMyevxLfGp3IDOoeC10Giu3K3wKeQXaKV1TqnoKDhPHGkLSiOFEMdSnx8zjGoqZQqzgILbwSYg2cCuZoVf3HucfvAGz7MW50BW4N0AAAAASUVORK5CYII=' /></span>
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
          <span style="position:absolute; margin-left:-35px; margin-top:-5px;"><img src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACYAAAAmCAMAAACf4xmcAAAAbFBMVEUAAAAAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhEAdhFnWh4EAAAAI3RSTlMA+/PopnFsQTAZCwT21M+8fmUqJyTu27ifeVFFN+LIlllOOc5ANW4AAACuSURBVBgZ7cFHAoMgAATAtffeNX3//8cA5haE5O4MTn9oQtglE0NYxR65wiZySV5gEeUUWpj1A6UYRulIZYNJNnEXwGThRwWD2OGufOBYVlJxlgwaif+CFFJxI2hkgcsKQjJQ8mJoRAWFDcBMyevxLfGp3IDOoeC10Giu3K3wKeQXaKV1TqnoKDhPHGkLSiOFEMdSnx8zjGoqZQqzgILbwSYg2cCuZoVf3HucfvAGz7MW50BW4N0AAAAASUVORK5CYII=' /></span>
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
    <table cellspacing="0" cellpadding="2" style="table-layout:fixed;">
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
      <xsl:choose>
        <xsl:when test="(/inv:invoice/inv:invoiceData/inv:currencyCode) and (/inv:invoice/inv:invoiceData/inv:currencyCode)!='' and (translate(/inv:invoice/inv:invoiceData/inv:currencyCode,$lower,$upper)!='VND' or translate(/inv:invoice/inv:invoiceData/inv:currencyCode,$lower,$upper)!='VNĐ')">
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
                <xsl:when test="(inv:unitPrice) and (inv:unitPrice)!='' and (inv:unitPrice)!=0"><xsl:value-of select="format-number(inv:unitPrice, '#.##0,00', 'vndong')"/></xsl:when>
                <xsl:otherwise>&nbsp;</xsl:otherwise>
              </xsl:choose>
            </td>
            <td align="right" class="borderTD">
              <xsl:choose>
                <xsl:when test="(inv:itemTotalAmountWithoutVat) and (inv:itemTotalAmountWithoutVat)!='' and (inv:itemTotalAmountWithoutVat)!=0"><xsl:value-of select="format-number(inv:itemTotalAmountWithoutVat, '#.##0,00', 'vndong')"/></xsl:when>
                <xsl:otherwise>&nbsp;</xsl:otherwise>
              </xsl:choose>
            </td>
          </tr>
          </xsl:for-each>
        </xsl:when>
        <xsl:otherwise>
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
        </xsl:otherwise>
      </xsl:choose>
    </table>
  </xsl:variable>
</xsl:stylesheet>