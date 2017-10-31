<?xml version="1.0" encoding="utf-8"?><!-- DWXMLSource="05_HDXK_DT.xml" -->
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
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" encoding="utf-8" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>
<xsl:decimal-format name="vndong" decimal-separator=',' grouping-separator='.' />
<xsl:template match="/">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

<title>HOA_DON_DIEN_TU</title>

</head>

<body background="bgimage.jpg">
<div style="max-width:720px; border:1px solid #000; font-size:11pt; font-family:'Times New Roman', 'serif'; ">

<!-- Mẫu số HĐ và Tiêu đề -->
<table width="700" align="center" style="color:BLUE">
    <tr valign="middle">
        <td width="200" rowspan="2" align="center">
            <xsl:choose>
            <xsl:when test="HDonXK/NDungHDon/TTinHDon/loaiLogo='00'"> </xsl:when>
            <xsl:when test="HDonXK/NDungHDon/TTinHDon/loaiLogo='01'"><img src='data:image/png;base64,{HDonXK/NDungHDon/TTinHDon/ddanLogo}' /></xsl:when>
            <xsl:otherwise> </xsl:otherwise>
            </xsl:choose>
        </td>
        <td align="center" valign="bottom" style="font-size:16pt">
            <b>HÓA ĐƠN XUẤT KHẨU</b><br />
			(EXPORTS INVOICE)	
        </td>
        <td width="155" height="65" rowspan="2" align="left" valign="middle" style="font-size:9pt">
            Mẫu số (Form): <xsl:value-of select="HDonXK/NDungHDon/TTinHDon/mauHDon"/><br />
            Ký hiệu (Serial): <xsl:value-of select="HDonXK/NDungHDon/TTinHDon/kyHieuHDon"/><br />
            Số (No): 
          <xsl:value-of select="HDonXK/NDungHDon/TTinHDon/soHDon"/></td>
    </tr>
    <tr valign="middle">
      	<td align="center" valign="top">
			<b>(HÓA ĐƠN ĐIỆN TỬ)</b><br />
    		<xsl:if test="HDonXK/NDungHDon/TTinHDon/lienHDon=''"><xsl:value-of select="HDonXK/NDungHDon/TTinHDon/lienHDon"/></xsl:if>
            <xsl:if test="HDonXK/NDungHDon/TTinHDon/lienHDon=''"> </xsl:if>
        </td>
	</tr>
</table><br />

<!-- Thông tin người bán -->
<table width="700" align="center" style="color:BLUE">
    <tr>
    <td colspan="3"><b><xsl:value-of select="HDonXK/NDungHDon/NBan/tenDVi"/></b></td>
    </tr>
  <tr>
    <td colspan="3">Địa chỉ (Add): <xsl:value-of select="HDonXK/NDungHDon/NBan/diaChi"/></td>
  </tr>
  <tr>
    <td width="220">MST (TaxCode): <xsl:value-of select="HDonXK/NDungHDon/NBan/MST"/></td>
    <td>Điện thoại (Tel): <xsl:value-of select="HDonXK/NDungHDon/NBan/dienThoai"/></td>
    <td width="220">Fax: <xsl:value-of select="HDonXK/NDungHDon/NBan/fax"/></td>
  </tr>  
</table>

<table width="700" align="center" style="color:BLUE">
  <tr>
    <td width="280">Số tài khoản (Bank A/C): <xsl:value-of select="HDonXK/NDungHDon/NBan/soTKhoan"/></td>
    <td align="left">Tại ngân hàng (In Bank): <xsl:value-of select="HDonXK/NDungHDon/NBan/taiNHang"/></td>
  </tr>  
  <tr>
    <td colspan="3" valign="middle">
        <hr/>
    </td>
  </tr>
</table>

<!-- Thông tin người mua -->
<table width="700" align="center" style="color:BLUE">  
  <tr>
    <td colspan="2">Đơn vị nhập khẩu (Import company): <xsl:value-of select="HDonXK/NDungHDon/NMua/tenDVi"/></td>
  </tr>
  <tr>
    <td colspan="2">Địa chỉ (Add): <xsl:value-of select="HDonXK/NDungHDon/NMua/diaChi"/></td>
  </tr>
  <tr>
    <td width="280">Điện thoại (Tel): <xsl:value-of select="HDonXK/NDungHDon/NMua/dienThoai"/></td>
    <td align="left">Hình thức thanh toán (For pay ment): <xsl:value-of select="HDonXK/NDungHDon/NMua/hinhThucTToan"/></td>
  </tr>  
  <tr>
    <td width="280">Số tài khoản (Bank A/C): <xsl:value-of select="HDonXK/NDungHDon/NMua/soTKhoan"/></td>
    <td align="left">Tại ngân hàng (In Bank): <xsl:value-of select="HDonXK/NDungHDon/NMua/taiNHang"/></td>
  </tr>  
  <tr>
    <td width="280">Số hợp đồng (Contract No): <xsl:value-of select="HDonXK/NDungHDon/NMua/soHDong"/></td>
    <td>Ngày hợp đồng (On contract): <xsl:if test="HDonXK/NDungHDon/NMua/ngayHDong!=''"><xsl:value-of select="concat(substring(HDonXK/NDungHDon/NMua/ngayHDong,9,2),'/',substring(HDonXK/NDungHDon/NMua/ngayHDong,6,2),'/',substring(HDonXK/NDungHDon/NMua/ngayHDong,1,4))"/></xsl:if></td>
  </tr>  
  <tr>
    <td colspan="2">Địa điểm giao hàng (Delivery location): <xsl:value-of select="HDonXK/NDungHDon/NMua/noiGiaoHang"/></td>
  </tr>
  <tr>
    <td colspan="2">Địa điểm nhận hàng (Destination location): <xsl:value-of select="HDonXK/NDungHDon/NMua/noiNhanHang"/></td>
  </tr>
  <tr>
    <td width="280" align="left">Số vận đơn (Waybill No): <xsl:value-of select="HDonXK/NDungHDon/NMua/soVanDon"/></td>
    <td>Số container (Container No): <xsl:value-of select="HDonXK/NDungHDon/NMua/soContainer"/></td>
  </tr>  
  <tr>
    <td colspan="2">Đơn vị vận chuyển (Transport): <xsl:value-of select="HDonXK/NDungHDon/NMua/tenDViVChuyen"/></td>
  </tr>
</table>

<!-- Dữ liệu chính trên HĐ -->
<table border="1" cellspacing="0" cellpadding="3" align="center" width="700">
	<col title="STT" width="40"/>
    <col title="Tên hàng hóa, dịch vụ" width="320"/>
    <col title="Đơn vị tính" width="80"/>
    <col title="Số lượng" width="50"/>
    <col title="Đơn giá" width="90"/>
    <col title="Thành tiền" width="120"/>
  <tr>
    <td align="center" valign="middle">
    	<b>STT<br />
		(NO)</b>
    </td>
    <td align="center" valign="middle">
    	<b>Tên hàng hóa, dịch vụ<br />
		(Description)</b>
    </td>
    <td align="center" valign="middle">
    	<b>Đơn vị tính<br />
		(Unit)</b>
    </td>
    <td align="center" valign="middle">
    	<b>Số lượng<br />
		(Quantity)</b>
    </td>
    <td align="center" valign="middle">
    	<b>Đơn giá<br />
		(Unit price)</b>
    </td>
    <td align="center" valign="middle">
    	<b>Thành tiền<br />
		(Amount)</b>
    </td>
  </tr>
  <xsl:for-each select="HDonXK/NDungHDon/DSachHHoaDVu/HHoaDVu">
  <tr>
    <td align="center" valign="middle"><xsl:value-of select="position()"/></td>
    <td align="left" valign="middle">
    	<xsl:choose>
        <xsl:when test="tenHHoaDVu=''"> </xsl:when>
        <xsl:when test="tenHHoaDVu='0'"> </xsl:when>
        <xsl:otherwise><xsl:value-of select="tenHHoaDVu"/></xsl:otherwise>
        </xsl:choose>
    </td>
    <td align="center" valign="middle">
    	<xsl:choose>
        <xsl:when test="DVT=''"> </xsl:when>
        <xsl:when test="DVT='0'"> </xsl:when>
        <xsl:otherwise><xsl:value-of select="DVT"/></xsl:otherwise>
        </xsl:choose>
    </td>
    <td align="center" valign="middle">
    	<xsl:choose>
        <xsl:when test="soLuong=''"> </xsl:when>
        <xsl:when test="soLuong='0'"> </xsl:when>
        <xsl:otherwise><xsl:value-of select="format-number(soLuong, '#.##0,###', 'vndong')"/></xsl:otherwise>
        </xsl:choose>
    </td>
    <td align="center" valign="middle">
    	<xsl:choose>
        <xsl:when test="donGia=''"> </xsl:when>
        <xsl:when test="donGia='0'"> </xsl:when>
        <xsl:otherwise><xsl:value-of select="format-number(donGia, '#.##0', 'vndong')"/></xsl:otherwise>
        </xsl:choose>
    </td>
    <td align="right" valign="middle">
    	<xsl:choose>
        <xsl:when test="thanhTien=''"> </xsl:when>
        <xsl:when test="thanhTien='0'"> </xsl:when>
        <xsl:otherwise><xsl:value-of select="format-number(thanhTien, '#.##0', 'vndong')"/></xsl:otherwise>
        </xsl:choose>
    </td>
  </tr>
  </xsl:for-each>
  <xsl:for-each select="HDonXK/NDungHDon/DSachHHoaDVu/TToan">
  <tr>
    <td colspan="5" align="right" valign="middle"><b>Cộng tiền bán hàng hóa, dịch vụ (Total amount):</b></td>
    <td align="right" valign="middle">
    	<xsl:choose>
        <xsl:when test="tongTien=''"> </xsl:when>
        <xsl:when test="tongTien='0'"> </xsl:when>
        <xsl:otherwise><b><xsl:value-of select="format-number(tongTien, '#.##0', 'vndong')"/></b></xsl:otherwise>
        </xsl:choose>
    </td>
  </tr>
  </xsl:for-each>
</table>

<table width="700" align="center" style="color:BLUE">
  <tr>
    <td>Số tiền bằng chữ (In words): 
    	<xsl:if test="HDonXK/NDungHDon/DSachHHoaDVu/TToan/tongTienTToanBChu=''"> </xsl:if>
        <xsl:if test="HDonXK/NDungHDon/DSachHHoaDVu/TToan/tongTienTToanBChu!=''"><xsl:value-of select="HDonXK/NDungHDon/DSachHHoaDVu/TToan/tongTienTToanBChu"/>.</xsl:if>        
    </td>
  </tr>
</table>

<!-- Thông tin chữ ký người mua và người bán -->
<table width="700" align="center" style="color:BLUE">
	<tr valign="middle">
		<td width="250" align="center" valign="bottom">&nbsp;
        </td>
        <td>&nbsp;</td>
		<td align="center" valign="middle" width="250">
          	<i>Ngày <xsl:value-of select="substring(HDonXK/NDungHDon/TTinHDon/ngayBan,9,2)" /> tháng <xsl:value-of select="substring(HDonXK/NDungHDon/TTinHDon/ngayBan,6,2)" /> năm <xsl:value-of select="substring(HDonXK/NDungHDon/TTinHDon/ngayBan,1,4)" /></i><br />
          	<b>Người bán hàng</b><br />
            (Ký, đóng dấu và ghi rõ họ tên)<br />
			(Signature, stamp, full name)
        </td>   
    </tr>
</table>

<table width="700" align="center" style="color:BLUE">
	<tr valign="middle">
		<td align="center" valign="middle" width="250">&nbsp;
        </td>   
		<td align="center" valign="top">
        	<xsl:value-of select="HDonXK/NDungHDon/TTinHDon/tieuDeChuyenHoaDonGiay"/><br />
            <xsl:value-of select="HDonXK/NDungHDon/TTinHDon/chuyenHoaDonGiay"/><br /><br /><br />
			<xsl:value-of select="HDonXK/NDungHDon/TTinHDon/nguoiChuyenDoi"/>
        </td>
		<td align="center" valign="middle" width="250">
          	<br /><br /><br /><br />
          	<xsl:value-of select="HDonXK/NDungHDon/TTinHDon/nguoiBan"/>
        </td>   
    </tr>
</table>

<table width="700" align="center" style="color:BLUE">
	<tr>
    	<td align="center">
        	<xsl:if test="HDonXK/NDungHDon/TTinHDon/hoaDonThayThe!=''"><xsl:value-of select="HDonXK/NDungHDon/TTinHDon/hoaDonThayThe"/></xsl:if>
            <xsl:if test="HDonXK/NDungHDon/TTinHDon/hoaDonThayThe=''"> </xsl:if>
        </td>
    </tr>
</table>

<!-- Ghi chú -->
<table width="700" align="center">
	<tr>
    	<td><hr/></td>
    </tr>
</table>

<table width="700" align="center">
  <tr>
    <td width="20">&nbsp;</td>
    <td align="justify" style="font-size:9pt">
    	<b><u> Ghi chú:</u></b>
        Có thể chuyển đổi từ hóa đơn điện tử sang hóa đơn giấy một lần duy nhất bằng cách vào phần mềm XuatHoaDon<sup>&#8482;</sup> của công ty TS24 chọn mục <b>chuyển đổi từ hóa đơn điện tử sang giấy</b> để có thể in ra giấy, ghi rõ họ tên, chữ ký của người chuyển đổi, đóng dấu xác nhận theo hướng dẫn của thông tư Hóa đơn điện tử.
    </td>
    <td width="20">&nbsp;</td>
  </tr>
</table>

</div>
</body>
</html>

</xsl:template>
</xsl:stylesheet>