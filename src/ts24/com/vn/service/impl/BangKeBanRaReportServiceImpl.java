package ts24.com.vn.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

import ts24.com.vn.dal.dao.HoaDonDao;
import ts24.com.vn.dal.model.nonentity.ReportBangKeBanRaView;
import ts24.com.vn.service.IReportService;

public class BangKeBanRaReportServiceImpl  implements IReportService<ReportBangKeBanRaView>{

	private Logger logger = Logger.getLogger(BangKeBanRaReportServiceImpl.class.getName());
	HoaDonDao dao;
	
	public HSSFWorkbook exportExcel(List<ReportBangKeBanRaView> list_TH,List<ReportBangKeBanRaView> list, String tuNgay, String denNgay, String fieldSorting) throws Exception {
//		10: Thuế 10%;
//	 	5: Thuế 5%;
//	 	0: Thuế 0%;
//	 	-1: Không chịu thuế (KCT);
//	 	-2: Không kê khai nộp thuế (KKKNT)
		/**
         * 	PROCESS EXCEL
         */
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFCellStyle cellStyleTitle, cellStyleRecordYellow, cellStyleRecordRed, cellStyleYellowColorDes, cellStyleredColorDes, cellStyleTitleDate, cellStyleTitleDonViTien, cellStyleBorder, cellStyleNote, cellStyleTitleMain, cellStyleBackgroundColor = null;
		HSSFFont fontTitle, fontRowContent, fontTitleMain;
		HSSFSheet sheet = workbook.createSheet("Bảng kê bán ra");
		
		// font tilte main
		fontTitleMain = workbook.createFont();
		fontTitleMain.setFontName("Arial");
		fontTitleMain.setFontHeightInPoints((short) 11);
		fontTitleMain.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontTitleMain.setColor(HSSFColor.BLUE.index);
		
		// font tilte row
		fontTitle = workbook.createFont();
		fontTitle.setFontName("Arial");
		fontTitle.setFontHeightInPoints((short) 9);
		fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		
		// font row
		fontRowContent = workbook.createFont();
		fontRowContent.setFontName("Arial");
		fontRowContent.setFontHeightInPoints((short) 9);
		fontRowContent.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		
		cellStyleBorder = workbook.createCellStyle();
		setBorderLine(cellStyleBorder);
		cellStyleBorder.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		cellStyleBorder.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyleBorder.setFont(fontTitle);
		
		cellStyleNote = workbook.createCellStyle();
		setBorderLine(cellStyleNote);
//		cellStyleNote.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		cellStyleNote.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyleNote.setFont(fontRowContent);
		
		cellStyleTitleMain = workbook.createCellStyle();
		cellStyleTitleMain.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyleTitleMain.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyleTitleMain.setFont(fontTitleMain);
		
		// from date to date
		cellStyleTitleDate = workbook.createCellStyle();
		cellStyleTitleDate.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyleTitleDate.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyleTitleDate.setFont(fontRowContent);
		
		// from don vi tien
		cellStyleTitleDonViTien = workbook.createCellStyle();
		cellStyleTitleDonViTien.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		cellStyleTitleDonViTien.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyleTitleDonViTien.setFont(fontRowContent);
				
		// bgcolor
		cellStyleBackgroundColor = workbook.createCellStyle();
		setBorderLine(cellStyleBackgroundColor);
		cellStyleBackgroundColor.setFont(fontRowContent);
		
		// background color yellow
		cellStyleRecordYellow = workbook.createCellStyle();
		cellStyleRecordYellow.setFillForegroundColor(HSSFColor.YELLOW.index);
		cellStyleRecordYellow.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		setBorderLine(cellStyleRecordYellow);
		cellStyleRecordYellow.setFont(fontRowContent);
		
		// background color red
		cellStyleRecordRed = workbook.createCellStyle();
		HSSFColor lightRed =  setColor(workbook,(byte) 255, (byte)102,(byte) 0);
		setBackgroundColor(cellStyleRecordRed, lightRed);
		setBorderLine(cellStyleRecordRed);
		cellStyleRecordRed.setFont(fontRowContent);
		
		// Create Title Excel
		HSSFRow row = sheet.createRow((short) 10);
		HSSFCell cell;
		int stt = 1;
        int r = 6;
        
		cellStyleTitle = workbook.createCellStyle();
		HSSFColor colorTitle =  setColor(workbook,(byte) 192, (byte)192,(byte) 192);
		setBackgroundColor(cellStyleTitle, colorTitle);
		cellStyleTitle.setFont(fontTitle);
		cellStyleTitle.setAlignment((short) 0);
		cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyleTitle.setWrapText(true);   //Wrapping text
		setBorderLine(cellStyleTitle);
		
		
		cellStyleYellowColorDes = workbook.createCellStyle();
		HSSFColor yellow =  setColor(workbook,(byte) 255, (byte)255,(byte) 0);
		setBackgroundColor(cellStyleYellowColorDes, yellow);
		
		cellStyleredColorDes = workbook.createCellStyle();
		HSSFColor Red =  setColor(workbook,(byte) 255, (byte)102,(byte) 0);
		setBackgroundColor(cellStyleredColorDes, Red);
		
		setWidthColumn(sheet);
		
		/*
         * Create EXCEL
         */
		 // title
		row = sheet.createRow((short) (2));	
		row.setHeight((short) 400);
		cell = row.createCell( 0);
		cell.setCellValue("BẢNG KÊ HOÁ ĐƠN, CHỨNG TỪ HÀNG HOÁ, DỊCH VỤ BÁN RA");
		sheet.addMergedRegion(new Region(2, (short) 0, 2, (short) 13));
		cell.setCellStyle(cellStyleTitleMain);
		
		row = sheet.createRow((short) (3));	
		row.setHeight((short) 400);
		cell = row.createCell( 0);
		cell.setCellValue("Kỳ tính từ ngày "+ tuNgay +" đến ngày " + denNgay);
		sheet.addMergedRegion(new Region(3, (short) 0, 3, (short) 13));
		cell.setCellStyle(cellStyleTitleDate);
		
		row = sheet.createRow((short) (5));	
		row.setHeight((short) 300);
		cell = row.createCell( 0);
		cell.setCellValue("Đơn vị tiền: đồng Việt Nam");
		sheet.addMergedRegion(new Region(5, (short) 0, 5, (short) 13));
		cell.setCellStyle(cellStyleTitleDonViTien);
		
		row = sheet.createRow((short) (r));	
		row.setHeight((short) 500);
		cell = row.createCell(2);
		cell.setCellValue("Hoá đơn, chứng từ bán");
		sheet.addMergedRegion(new Region(r, (short) 2, r, (short) 5));
		cell.setCellStyle(cellStyleTitle);
		cell = row.createCell(3);
		cell.setCellStyle(cellStyleTitle);
		cell = row.createCell(4);
		cell.setCellStyle(cellStyleTitle);
		cell = row.createCell(5);
		cell.setCellStyle(cellStyleTitle);

		// Create Title Excel
		String[] rowTitle = {"STT", "Mã số thuế bán", "Mẫu hoá đơn", "Ký hiệu hoá đơn", "Số hoá đơn", "Ngày, tháng, năm phát hành", "Tên người mua", 
				"Mã số thuế người mua", "Mặt hàng", "Doanh số bán chưa có thuế", "Thuế suất", "Thuế GTGT", "Chiết khấu, Giảm trừ (nếu có)", "Ghi chú"};
		for(int i=0;i<rowTitle.length;i++){
			if(i<2 || i>5 ){
				sheet.addMergedRegion(new Region(r, (short) i, r+1, (short) i));
				cell = row.createCell(i);
				cell.setCellValue(getStringCell(rowTitle[i]));
				cell.setCellStyle(cellStyleTitle);
//				row = sheet.createRow((short) r+1);
//				cell = row.createCell(i);
//				cell.setCellStyle(cellStyleTitle);
			}
  	  	}
		
		r= r+1;
		row = sheet.createRow((short) r);
		row.setHeight((short) 500);
		for(int i=0;i<rowTitle.length;i++){
			if(i>1 && i<6 ){
				cell = row.createCell(i);
				cell.setCellValue(getStringCell(rowTitle[i]));
				cell.setCellStyle(cellStyleTitle);
			}
  	  	}
		
		 // row number column
		r= r+1;
		row = sheet.createRow((short) (r));	
		row.setHeight((short) 400);
		for(int i=0;i<14;i++){
			int k = i+1;
			cell = row.createCell(i);
			cell.setCellValue("["+ k +"]");
			cell.setCellStyle(cellStyleTitle);
  	  	}
		
		/**
		 * 	I./ LOOP DATA:
		 */
        if(list != null && list.size() >0){
        	for (ReportBangKeBanRaView obj : list) {
        		String mstNguoiBan = "", mauHoaDon = "", kyHieuHoaDon = "", soHoaDon = "", ngayThangNamPhatHanh = "", 
        				tenNguoiMua = "", mstNguoiMua = "", matHang = "", doanhSoBanChuaCoThue = "", thueSuat = "",
    					thueGTGT = "", chietKhauGiamTru="", ghiChu="";
        		
        		
        		if(!StringUtils.isEmpty(obj.getMstNguoiBan())){
        			mstNguoiBan = obj.getMstNguoiBan();
        		}
        		if(!StringUtils.isEmpty(obj.getMauHoaDon())){
        			mauHoaDon = obj.getMauHoaDon();
        		}
        		if(!StringUtils.isEmpty(obj.getKyHieuHoaDon())){
        			kyHieuHoaDon = obj.getKyHieuHoaDon();
        		}
        		if(!StringUtils.isEmpty(obj.getSoHoaDon())){
        			soHoaDon = obj.getSoHoaDon();
        		}
        		if(!StringUtils.isEmpty(obj.getNgayThangNamPhatHanh())){
        			ngayThangNamPhatHanh = obj.getNgayThangNamPhatHanh();
        		}
        		if(!StringUtils.isEmpty(obj.getTenNguoiMua())){
        			tenNguoiMua = obj.getTenNguoiMua();
        		}
        		if(!StringUtils.isEmpty(obj.getMstNguoiMua())){
        			mstNguoiMua= obj.getMstNguoiMua();
        		}
        		if(!StringUtils.isEmpty(obj.getMatHang())){
        			matHang= obj.getMatHang();
        		}
        		if(!StringUtils.isEmpty(obj.getDoanhSoBanChuaCoThue())){
        			doanhSoBanChuaCoThue = obj.getDoanhSoBanChuaCoThue();
        		}
        		if(!StringUtils.isEmpty(obj.getThueSuat())){
        			thueSuat = obj.getThueSuat();
        		}
        		if(!StringUtils.isEmpty(obj.getThueGTGT())){
        			thueGTGT = obj.getThueGTGT();
        		}
        		if(!StringUtils.isEmpty(obj.getChietKhauGiamTru())){
        			chietKhauGiamTru = obj.getChietKhauGiamTru();
        		}
    			
                row = sheet.createRow((r + 1));
                row.setHeight((short) 300);
                cell = row.createCell(0);
                cell.setCellValue(stt);
                cell.setCellStyle(cellStyleBorder);
                
                setValueRow(cellStyleBackgroundColor, row, mstNguoiBan, mauHoaDon, kyHieuHoaDon, soHoaDon, ngayThangNamPhatHanh, tenNguoiMua,
						mstNguoiMua, matHang, doanhSoBanChuaCoThue, thueSuat, thueGTGT, chietKhauGiamTru, ghiChu);
                  
                r++;
                stt++;
			}
        }
        setRowNoteData(cellStyleYellowColorDes, cellStyleredColorDes, cellStyleNote, sheet, r);

		return workbook;
	}

	private void setBackgroundColor(HSSFCellStyle cellStyleredColorDes, HSSFColor color) {
		cellStyleredColorDes.setFillForegroundColor(color.getIndex());
		cellStyleredColorDes.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	}

	private void setBorderLine(HSSFCellStyle bgcolor) {
		bgcolor.setBorderTop((short) 1); // single line border
		bgcolor.setBorderBottom((short) 1); // single line border
		bgcolor.setBorderLeft((short) 1); // single line border
		bgcolor.setBorderRight((short) 1); // single line border
	}

	private void setValueRow(HSSFCellStyle bgcolor, HSSFRow row,
			String mstNguoiBan, String mauHoaDon, String kyHieuHoaDon, String soHoaDon, String ngayThangNamPhatHanh, String tenNguoiMua, String
			mstNguoiMua, String matHang, String DoanhSoBanChuaCoThue, String thueSuat, String thueGTGT, String chietKhauGiamTru, String ghiChu) {
		
		HSSFCell cell;
		cell = row.createCell(1);
		cell.setCellValue(getStringCell(mstNguoiBan + ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(2);
		cell.setCellValue(getStringCell(mauHoaDon+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(3);
		cell.setCellValue(getStringCell(kyHieuHoaDon+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(4);
		cell.setCellValue(getStringCell(soHoaDon+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(5);
		cell.setCellValue(getStringCell(ngayThangNamPhatHanh+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(6);
		cell.setCellValue(getStringCell(tenNguoiMua+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(7);
		cell.setCellValue(getStringCell(mstNguoiMua+ ""));
		cell.setCellStyle(bgcolor);

		cell = row.createCell(8);
		cell.setCellValue(getStringCell(matHang+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(9);
		cell.setCellValue(getStringCell(DoanhSoBanChuaCoThue+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(10);
		cell.setCellValue(getStringCell(thueSuat+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(11);
		cell.setCellValue(getStringCell(thueGTGT+ ""));
		cell.setCellStyle(bgcolor);

		cell = row.createCell( 12);
		cell.setCellValue(getStringCell(chietKhauGiamTru+ ""));
		cell.setCellStyle(bgcolor);

		cell = row.createCell( 13);
		cell.setCellValue(getStringCell(ghiChu+ ""));
		cell.setCellStyle(bgcolor);
		
	}

	private void setRowNoteData(HSSFCellStyle cellStyleYellowColorDes, HSSFCellStyle cellStyleRedColorDes, HSSFCellStyle fontRowContent, HSSFSheet sheet, int r) {
		HSSFRow row;
		HSSFCell cell;
		r = r +1;
		row = sheet.createRow( (r+1));
		cell = row.createCell( 4);
		cell.setCellValue(getStringCell("Ghi chú : Thuế suất"));
		sheet.addMergedRegion(new Region(r+1, (short)4, r+1, (short)6));
		
		cell = row.createCell( 7);
		cell.setCellValue(getStringCell(""));
		//--------------------------------
		row = sheet.createRow( (r+2));	
		cell = row.createCell( 4);
		cell.setCellValue(getStringCell("Loại:"));
		sheet.addMergedRegion(new Region(r+2, (short)4, r+2, (short)6));
		cell.setCellStyle(fontRowContent);
		cell = row.createCell( 5);
		cell.setCellStyle(fontRowContent);
		cell = row.createCell( 6);
		cell.setCellStyle(fontRowContent);
		cell = row.createCell( 7);
		cell.setCellValue(getStringCell("Thuế suất"));
		cell.setCellStyle(fontRowContent);
		//--------------------------------
		row = sheet.createRow( (r+3));	
		cell = row.createCell( 4);
		cell.setCellValue(getStringCell("1. Hàng hoá, dịch vụ không chịu thuế GTGT:"));
		sheet.addMergedRegion(new Region(r+3, (short)4, r+3, (short)6));
		cell.setCellStyle(fontRowContent);
		cell = row.createCell( 5);
		cell.setCellStyle(fontRowContent);
		cell = row.createCell( 6);
		cell.setCellStyle(fontRowContent);
		cell = row.createCell( 7);
		cell.setCellValue(getStringCell("KCT"));
		cell.setCellStyle(fontRowContent);
		//--------------------------------
		row = sheet.createRow( (r+4));	
		cell = row.createCell( 4);
		cell.setCellValue(getStringCell("2. Hàng hoá, dịch vụ chịu thuế suất thuế GTGT 0%:"));
		sheet.addMergedRegion(new Region(r+4, (short)4, r+4, (short)6));
		cell.setCellStyle(fontRowContent);
		cell = row.createCell( 5);
		cell.setCellStyle(fontRowContent);
		cell = row.createCell( 6);
		cell.setCellStyle(fontRowContent);
		cell = row.createCell( 7);
		cell.setCellValue(getStringCell("0%"));
		cell.setCellStyle(fontRowContent);
		//--------------------------------
		row = sheet.createRow( (r+5));	
		cell = row.createCell( 4);
		cell.setCellValue(getStringCell("3. Hàng hoá, dịch vụ chịu thuế suất thuế GTGT 5%:"));
		sheet.addMergedRegion(new Region(r+5, (short)4, r+5, (short)6));
		cell.setCellStyle(fontRowContent);
		cell = row.createCell( 5);
		cell.setCellStyle(fontRowContent);
		cell = row.createCell( 6);
		cell.setCellStyle(fontRowContent);
		cell = row.createCell( 7);
		cell.setCellValue(getStringCell("5%"));
		cell.setCellStyle(fontRowContent);
		//--------------------------------
		row = sheet.createRow( (r+6));	
		cell = row.createCell( 4);
		cell.setCellValue(getStringCell("4. Hàng hoá, dịch vụ chịu thuế suất thuế GTGT 10%:"));
		sheet.addMergedRegion(new Region(r+6, (short)4, r+6, (short)6));
		cell.setCellStyle(fontRowContent);
		cell = row.createCell( 5);
		cell.setCellStyle(fontRowContent);
		cell = row.createCell( 6);
		cell.setCellStyle(fontRowContent);
		cell = row.createCell( 7);
		cell.setCellValue(getStringCell("10%"));
		cell.setCellStyle(fontRowContent);
	}

	private void setWidthColumn(HSSFSheet sheet) {
		// Set Column Widths
		sheet.setColumnWidth(0, 1000);
		sheet.setColumnWidth(1, 3800);
		sheet.setColumnWidth(2, 3500);
		sheet.setColumnWidth(3, 2000);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 3500);
		sheet.setColumnWidth(6, 8000);
		sheet.setColumnWidth(7, 3800);
		sheet.setColumnWidth(8, 6000);
		sheet.setColumnWidth(9, 3000);
		sheet.setColumnWidth(10, 2000);
		sheet.setColumnWidth(11, 2000);
		sheet.setColumnWidth(12, 2000);
		sheet.setColumnWidth(13, 4000);
	}
	
	private HSSFColor setColor(HSSFWorkbook workbook, byte r, byte g, byte b) {
		HSSFPalette palette = workbook.getCustomPalette();
		HSSFColor hssfColor = null;
		try {
			hssfColor = palette.findColor(r, g, b);
			if (hssfColor == null) {
				palette.setColorAtIndex(HSSFColor.LAVENDER.index, r, g, b);
				hssfColor = palette.getColor(HSSFColor.LAVENDER.index);
			}
		} catch (Exception e) {
			logger.error(e);
		}

		return hssfColor;
	}
	
	private HSSFRichTextString getStringCell(String str){
		if(str == null || str.isEmpty()){
			return null;
		}
		HSSFRichTextString result = new HSSFRichTextString(str);
		return result;
	}
	
}
