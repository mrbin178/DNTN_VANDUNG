<?xml version="1.0" encoding="utf-8"?>
<!-- DWXMLSource="BienBanHoaDon.xml" -->
<!DOCTYPE xsl:stylesheet [
	<!ENTITY nbsp "&#160;">
	<!ENTITY copy "&#169;">
	<!ENTITY reg "&#174;">
	<!ENTITY trade "&#8482;">
	<!ENTITY mdash "&#8212;">
	<!ENTITY ldquo "&#8220;">
	<!ENTITY rdquo "&#8221;">
	<!ENTITY pound "&#163;">
	<!ENTITY yen "&#165;">
	<!ENTITY euro "&#8364;">
]>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:inv="http://laphoadon.gdt.gov.vn/2014/09/invoicexml/v1">
	<xsl:output method="html" encoding="utf-8" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>
	<xsl:decimal-format name="vndong" decimal-separator=',' grouping-separator='.'/>
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
			<xsl:otherwise>
				<xsl:value-of select="$val"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="BBanHDon">
		<html xmlns="http://www.w3.org/1999/xhtml">
			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
				<style>
table { border: 0; width: 700px; font-size:11pt; font-family:"Times New Roman", Times, serif; color:#000 border-collapse:collapse; }
.cell, .half-cell { border: 1px solid #000; display:inline-block; *display:inline; zoom:1; padding: 0px; }
.cellformat{ width:14px; height:14px; line-height:14px; text-align:center; }
.cellformatheight{ height:14px; line-height:14px; text-align:center; }
.cellformatdetail{ width:20px; height:20px; line-height:20px; text-align:center; }
.borderTD { border:1px solid #000; border-collapse:collapse; vertical-align:middle; }
.borderLR { border:1px solid #000; border-collapse:collapse; vertical-align:middle; border-bottom:none; border-top:none; }
.borderTB { border:1px solid #000; border-collapse:collapse; vertical-align:middle; border-right:none; border-left:none; }
</style>
				<title>BIEN_BAN_HOA_DON</title>
			</head>
			<body>
				<div style="width:700px; margin:0 auto; padding:8px;">
					<table align="center">
						<tr>
							<td>
								<!-- Thông tin Tiêu đề -->
								<table>
									<col width="70%"/>
									<col width="30%"/>
									<tr>
										<td colspan="2" align="center" valign="middle">
      CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM<br/>
      Độc lập - Tự do - Hạnh phúc<br/>
      -------------------------
    </td>
									</tr>
									<tr>
										<td colspan="2">&nbsp;</td>
									</tr>
									<tr>
										<td colspan="3" align="center" valign="middle">
											<span style="font-size:16pt">
												<b>BIÊN BẢN ĐIỀU CHỈNH HÓA ĐƠN</b>
											</span>
										</td>
									</tr>
									<tr>
										<td colspan="2">&nbsp;</td>
									</tr>
									<tr>
										<td colspan="2" align="left" valign="top">
											<i>
      - Căn cứ vào thông tư Số: 32/2011/TT-BTC ngày 14/03/2011 của Bộ Tài chính Hướng dẫn về khởi tạo, phát hành và sử dụng hóa đơn điện tử bán hàng hóa, cung ứng dịch vụ<br/>
      - Căn cứ vào tình hình thực tế của hai bên
    </i>
										</td>
									</tr>
									<tr>
										<td colspan="2">&nbsp;</td>
									</tr>
									<tr>
										<td colspan="2" align="left" valign="top">
      Hôm nay,
      <xsl:choose>
												<xsl:when test="(NDungBBan/ThongTinHDonThayThe/ngayHDonNew) and (NDungBBan/ThongTinHDonThayThe/ngayHDonNew)!='' and (substring(NDungBBan/ThongTinHDonThayThe/ngayHDonNew,5,1)='-')">ngày <xsl:value-of select="substring(NDungBBan/ThongTinHDonThayThe/ngayHDonNew,9,2)"/> tháng <xsl:value-of select="substring(NDungBBan/ThongTinHDonThayThe/ngayHDonNew,6,2)"/> năm <xsl:value-of select="substring(NDungBBan/ThongTinHDonThayThe/ngayHDonNew,1,4)"/>
												</xsl:when>
												<xsl:when test="(NDungBBan/ThongTinHDonThayThe/ngayHDonNew) and (NDungBBan/ThongTinHDonThayThe/ngayHDonNew)!='' and (substring(NDungBBan/ThongTinHDonThayThe/ngayHDonNew,3,1)='/')">ngày <xsl:value-of select="substring(NDungBBan/ThongTinHDonThayThe/ngayHDonNew,1,2)"/> tháng <xsl:value-of select="substring(NDungBBan/ThongTinHDonThayThe/ngayHDonNew,4,2)"/> năm <xsl:value-of select="substring(NDungBBan/ThongTinHDonThayThe/ngayHDonNew,7,4)"/>
												</xsl:when>
												<xsl:otherwise>ngày <span style="margin-left:10px">&nbsp;</span> tháng <span style="margin-left:10px">&nbsp;</span> năm <span style="margin-left:30px">&nbsp;</span>
												</xsl:otherwise>
											</xsl:choose>
      chúng tôi gồm có:
    </td>
									</tr>
									<tr>
										<td colspan="2">&nbsp;</td>
									</tr>
									<tr>
										<td colspan="2" align="left" valign="top">Bên mua (Bên A): <b>
												<xsl:value-of select="translate(NDungBBan/NguoiMua/tenDViNMua,$lower,$upper)"/>
											</b>
										</td>
									</tr>
									<tr>
										<td colspan="2" align="left" valign="top">Mã số thuế: <xsl:value-of select="NDungBBan/NguoiMua/MSTNMua"/>
										</td>
									</tr>
									<tr>
										<td colspan="2" align="left" valign="top">Địa chỉ: <xsl:value-of select="NDungBBan/NguoiMua/diaChiNMua"/>
										</td>
									</tr>
									<tr>
										<td colspan="2" align="left" valign="top">Đại diện: <xsl:value-of select="NDungBBan/NguoiMua/hoTenNMua"/>
										</td>
									</tr>
									<tr>
										<td colspan="2">&nbsp;</td>
									</tr>
									<tr>
										<td colspan="2" align="left" valign="top">Bên bán (Bên B): <xsl:value-of select="NDungBBan/NguoiBan/tenDViNBan"/>
										</td>
									</tr>
									<tr>
										<td colspan="2" align="left" valign="top">Mã số thuế: <xsl:value-of select="NDungBBan/NguoiBan/MSTNBan"/>
										</td>
									</tr>
									<tr>
										<td colspan="2" align="left" valign="top">Địa chỉ: <xsl:value-of select="NDungBBan/NguoiBan/diaChiNBan"/>
										</td>
									</tr>
									<tr>
										<td align="left" valign="top">Đại diện: <xsl:value-of select="NDungBBan/NguoiBan/hoTenNBan"/>
										</td>
										<td align="left" valign="top">Chức vụ: <xsl:value-of select="NDungBBan/NguoiBan/chucVu"/>
										</td>
									</tr>
									<tr>
										<td colspan="2">&nbsp;</td>
									</tr>
									<tr>
										<td colspan="2" align="left" valign="top">
      Hai bên cùng đồng ý điều chỉnh thông tin trên hóa đơn điện tử số:
      <xsl:choose>
												<xsl:when test="(NDungBBan/ThongTinHDonHuyThuHoi/soHDonOld) and (NDungBBan/ThongTinHDonHuyThuHoi/soHDonOld!='')">
													<xsl:value-of select="NDungBBan/ThongTinHDonHuyThuHoi/soHDonOld"/>.</xsl:when>
												<xsl:otherwise>
													<span style="margin-left:50px">&nbsp;</span>.</xsl:otherwise>
											</xsl:choose>
      Ký hiệu:
      <xsl:choose>
												<xsl:when test="(NDungBBan/ThongTinHDonHuyThuHoi/kyHieuHDonOld) and (NDungBBan/ThongTinHDonHuyThuHoi/kyHieuHDonOld!='')">
													<xsl:value-of select="NDungBBan/ThongTinHDonHuyThuHoi/kyHieuHDonOld"/>.</xsl:when>
												<xsl:otherwise>
													<span style="margin-left:50px">&nbsp;</span>.</xsl:otherwise>
											</xsl:choose>
      Do
      <xsl:choose>
												<xsl:when test="(NDungBBan/NguoiBan/tenDViNBan) and (NDungBBan/NguoiBan/tenDViNBan!='')">
													<xsl:value-of select="NDungBBan/NguoiBan/tenDViNBan"/>
												</xsl:when>
												<xsl:otherwise>
													<span style="margin-left:200px">&nbsp;</span>
												</xsl:otherwise>
											</xsl:choose>
      phát hành ngày
      <xsl:choose>
												<xsl:when test="(NDungBBan/ThongTinHDonHuyThuHoi/ngayHDonOld) and (NDungBBan/ThongTinHDonHuyThuHoi/ngayHDonOld)!='' and (substring(NDungBBan/ThongTinHDonHuyThuHoi/ngayHDonOld,5,1)='-')">
													<xsl:value-of select="concat(substring(NDungBBan/ThongTinHDonHuyThuHoi/ngayHDonOld,9,2),'/',substring(NDungBBan/ThongTinHDonHuyThuHoi/ngayHDonOld,6,2),'/',substring(NDungBBan/ThongTinHDonHuyThuHoi/ngayHDonOld,1,4))"/>
												</xsl:when>
												<xsl:when test="(NDungBBan/ThongTinHDonHuyThuHoi/ngayHDonOld) and (NDungBBan/ThongTinHDonHuyThuHoi/ngayHDonOld)!='' and (substring(NDungBBan/ThongTinHDonHuyThuHoi/ngayHDonOld,3,1)='/')">
													<xsl:value-of select="concat(substring(NDungBBan/ThongTinHDonHuyThuHoi/ngayHDonOld,1,2),'/',substring(NDungBBan/ThongTinHDonHuyThuHoi/ngayHDonOld,4,2),'/',substring(NDungBBan/ThongTinHDonHuyThuHoi/ngayHDonOld,7,4))"/>
												</xsl:when>
												<xsl:otherwise>&nbsp;</xsl:otherwise>
											</xsl:choose>
      như sau:
    </td>
									</tr>
								</table>
								<!-- Thông tin điều chỉnh -->
								<table cellspacing="0" cellpadding="2" style="table-layout:fixed;">
									<col width="50%"/>
									<col width="50%"/>
									<tr>
										<td align="center" class="borderTD">
											<i>Thông tin đã ghi</i>
										</td>
										<td align="center" class="borderTD">
											<i>Thông tin điều chỉnh</i>
										</td>
									</tr>
									<tr>
										<td align="center" class="borderTD">
											<xsl:choose>
												<xsl:when test="(NDungBBan/ThongTinHDonHuyThuHoi/ThongTinDaGhi) and (NDungBBan/ThongTinHDonHuyThuHoi/ThongTinDaGhi)!=''">
													<xsl:value-of select="NDungBBan/ThongTinHDonHuyThuHoi/ThongTinDaGhi"/>
												</xsl:when>
												<xsl:otherwise>&nbsp;</xsl:otherwise>
											</xsl:choose>
										</td>
										<td align="center" class="borderTD">
											<xsl:choose>
												<xsl:when test="(NDungBBan/ThongTinHDonThayThe/ThongTinDieuChinh) and (NDungBBan/ThongTinHDonThayThe/ThongTinDieuChinh)!=''">
													<xsl:value-of select="NDungBBan/ThongTinHDonThayThe/ThongTinDieuChinh"/>
												</xsl:when>
												<xsl:otherwise>&nbsp;</xsl:otherwise>
											</xsl:choose>
										</td>
									</tr>
								</table>
								<!-- Thông tin CKS -->
								<table>
									<col width="30%"/>
									<col width="40%"/>
									<col width="30%"/>
									<tr>
										<td colspan="3" align="left" valign="top"/>
									</tr>
									<tr>
										<td align="center" valign="bottom" style="white-space:nowrap">
											<b>ĐẠI DIỆN BÊN A</b>
											<br/>
											<br/>
											<br/>
											<xsl:value-of select="NDungBBan/NguoiMua/hoTenNMua"/>
										</td>
										<td align="left" valign="middle">&nbsp;</td>
										<td align="center" valign="bottom" style="white-space:nowrap">
											<b>ĐẠI DIỆN BÊN B</b>
											<br/>
											<br/>
											<br/>
											<xsl:value-of select="NDungBBan/NguoiBan/hoTenNBan"/>
										</td>
									</tr>
								</table>
								<!-- Thông tin xem CKS -->
								<table>
									<col width="30%"/>
									<col width="40%"/>
									<col width="30%"/>
									<xsl:if test="(CKS) and count(CKS)!=0">
										<tr>
											<td colspan="4">&nbsp;</td>
										</tr>
										<tr>
											<xsl:if test="(CKS/CKSNguoiMua/Serial) and (CKS/CKSNguoiMua/Serial)!=''">
												<xsl:choose>
													<xsl:when test="(CKS/CKSNguoiMua/LogoCKS) and (CKS/CKSNguoiMua/LogoCKS)!=''">
														<td align="left" valign="middle" style="white-space:nowrap; font-size:9pt; max-width:350px;">
															<span style="position:absolute; z-index:-10;">
																<img src='data:image/png;base64,{CKS/CKSNguoiMua/LogoCKS}'/>
															</span>
      Signer Info: <xsl:value-of select="CKS/CKSNguoiMua/Subject"/>
															<br/>
      Serial number: <xsl:value-of select="CKS/CKSNguoiMua/Serial"/>
															<br/>
														</td>
													</xsl:when>
													<xsl:otherwise>
														<td align="left" valign="middle" style="padding-left:30px; white-space:nowrap; font-size:9pt; max-width:350px;">
															<span style="position:absolute; margin-left:-35px; margin-top:-5px;">
																<img src='data:image/png;base64,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'/>
															</span>
      Signer Info: <xsl:value-of select="CKS/CKSNguoiMua/Subject"/>
															<br/>
      Serial number: <xsl:value-of select="CKS/CKSNguoiMua/Serial"/>
															<br/>
														</td>
													</xsl:otherwise>
												</xsl:choose>
											</xsl:if>
											<xsl:if test="not(CKS/CKSNguoiMua/Serial) or (CKS/CKSNguoiMua/Serial)=''">
												<td>&nbsp;</td>
											</xsl:if>
											<td>&nbsp;</td>
											<xsl:if test="(CKS/CKSNguoiBan/Serial) and (CKS/CKSNguoiBan/Serial)!=''">
												<xsl:choose>
													<xsl:when test="(CKS/CKSNguoiBan/LogoCKS) and (CKS/CKSNguoiBan/LogoCKS)!=''">
														<td align="left" valign="middle" style="white-space:nowrap; font-size:9pt; max-width:350px;">
															<span style="position:absolute; z-index:-10;">
																<img src='data:image/png;base64,{CKS/CKSNguoiBan/LogoCKS}'/>
															</span>
      Signer Info: <xsl:value-of select="CKS/CKSNguoiBan/Subject"/>
															<br/>
      Serial number: <xsl:value-of select="CKS/CKSNguoiBan/Serial"/>
															<br/>
														</td>
													</xsl:when>
													<xsl:otherwise>
														<td align="left" valign="middle" style="padding-left:30px; white-space:nowrap; font-size:9pt; max-width:350px;">
															<span style="position:absolute; margin-left:-35px; margin-top:-5px;">
																<img src='data:image/png;base64,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'/>
															</span>
      Signer Info: <xsl:value-of select="CKS/CKSNguoiBan/Subject"/>
															<br/>
      Serial number: <xsl:value-of select="CKS/CKSNguoiBan/Serial"/>
															<br/>
														</td>
													</xsl:otherwise>
												</xsl:choose>
											</xsl:if>
											<xsl:if test="not(CKS/CKSNguoiBan/Serial) or (CKS/CKSNguoiBan/Serial)=''">
												<td>&nbsp;</td>
											</xsl:if>
										</tr>
										<tr>
											<td colspan="4">
												<br/>
												<br/>
											</td>
										</tr>
									</xsl:if>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
