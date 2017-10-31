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
import org.apache.poi.ss.util.CellRangeAddress;

import ts24.com.vn.dal.model.nonentity.ReportUseOfInvoicesView;
import ts24.com.vn.service.IReportService;

public class UseOfInvoicesReportServiceImpl  implements IReportService<ReportUseOfInvoicesView>{
	private Logger logger = Logger.getLogger(UseOfInvoicesReportServiceImpl.class.getName());
	
	public HSSFWorkbook exportExcel(List<ReportUseOfInvoicesView> list_TH,List<ReportUseOfInvoicesView> list, String tuNgay, String denNgay, String fieldSorting) throws Exception {
		/**
         * 	PROCESS EXCEL
         */
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFCellStyle cellStyleTitle, cellStyleRecordYellow, cellStyleRecordRed, cellStyleYellowColorDes, cellStyleredColorDes, cellStyleTitleDate, cellStyleTitleDonViTien, cellStyleBorder, cellStyleNote, cellStyleTitleMain, cellStyleBackgroundColor = null;
		HSSFFont fontTitle, fontRowContent, fontTitleMain;
		HSSFSheet sheet = workbook.createSheet("BC TH sử dụng hóa đơn");
		
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
        int r = 5;
        
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
		row = sheet.createRow((short) (1));	
		row.setHeight((short) 400);
		cell = row.createCell( 0);
		cell.setCellValue("BÁO CÁO TÌNH HÌNH SỬ DỤNG HOÁ ĐƠN");
//		CellRangeAddress region = CellRangeAddress.valueOf("$A$2:$P$2");
//		sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 15));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$P$2"));

		cell.setCellStyle(cellStyleTitleMain);
		
		row = sheet.createRow((short) (2));	
//		row.setHeight((short) 300);
		cell = row.createCell( 0);
		cell.setCellValue("(Kể cả hoá đơn do đơn vị, cá nhân tự in)");
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$3:$P$3"));
		cell.setCellStyle(cellStyleTitleDate);
		
		row = sheet.createRow((short) (3));	
//		row.setHeight((short) 300);
		cell = row.createCell( 0);
		cell.setCellValue("Kỳ tính từ ngày "+ tuNgay +" đến ngày" + denNgay);
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$4:$P$4"));
		cell.setCellStyle(cellStyleTitleDate);
		
		row = sheet.createRow((short) (r));	
		row.setHeight((short) 400);
		cell = row.createCell(6);
		cell.setCellValue("Số hoá đơn sử dụng, xóa bỏ, mất, hủy");
		sheet.addMergedRegion(CellRangeAddress.valueOf("$G$"+ (r+1) + ":$P$" + (r+1)));
		cell.setCellStyle(cellStyleTitle);
		for (int i = 7; i <= 15; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(cellStyleTitle);
		}
		

		// Create Title Excel
		String[] rowTitle = {"STT", "Mã số thuế bán", "Mã loại hóa đơn", "Tên hóa đơn", "Mẫu hoá đơn", "Ký hiệu hoá đơn", "Từ số", "Đến số", 
				"Cộng", "Số lượng đã sử dụng", "Số lượng", "Số", "Số lượng", "Số", "Số lượng", "Số"};
		for(int i=0;i<rowTitle.length;i++){
			if(i<6 ){
//				sheet.addMergedRegion(new Region(r, (short) i, r+4, (short) i));
				sheet.addMergedRegion(new CellRangeAddress(r, r+4, i, i)); //rowFrom,rowTo,colFrom,colTo
				cell = row.createCell(i);
				cell.setCellValue(getStringCell(rowTitle[i]));
				cell.setCellStyle(cellStyleTitle);
				// set border
//				for (int j = 1; j <= 4; j++) {
//					row = sheet.createRow((short) (r+j));
////					cell = row.createCell(i);
////					cell.setCellValue(getStringCell(rowTitle[i]));
//					cell.setCellStyle(cellStyleTitle);
//				}
			}
  	  	}

		r= r+1;
		row = sheet.createRow((short) (r));	
		row.setHeight((short) 400);
		cell = row.createCell(6);
		cell.setCellValue("Tổng số sử dụng, xóa bỏ, mất, hủy");
//		sheet.addMergedRegion(new Region(r, (short) 6, r, (short) 8));
		sheet.addMergedRegion(new CellRangeAddress(r, r, 6, 8));
		cell.setCellStyle(cellStyleTitle);
		for (int i = 7; i <= 8; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(cellStyleTitle);
		}
		//----------------------
		cell = row.createCell(9);
		cell.setCellValue("Trong đó");
//		sheet.addMergedRegion(new Region(r, (short) 9, r, (short) 15));
		sheet.addMergedRegion(new CellRangeAddress(r, r, 9, 15));
		cell.setCellStyle(cellStyleTitle);
		for (int i = 10; i <= 15; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(cellStyleTitle);
		}
		
		r= r+1;
		// Từ số -> Số lượng đã sử dụng		
		row = sheet.createRow((short) r);
		row.setHeight((short) 450);
		for(int i=0;i<rowTitle.length;i++){
			if(i>=6 && i < 10){
//				sheet.addMergedRegion(new Region(r, (short) i, r+2, (short) i));
				sheet.addMergedRegion(new CellRangeAddress(r, r+2, i, i));
				cell = row.createCell(i);
				cell.setCellValue(getStringCell(rowTitle[i]));
				cell.setCellStyle(cellStyleTitle);
			}
  	  	}
		
		// ------- row---------------------
		row.setHeight((short) 400);
		cell = row.createCell(10);
		cell.setCellValue("Số xoá bỏ");
		sheet.addMergedRegion(new CellRangeAddress(r, r, 10, 11));
		cell.setCellStyle(cellStyleTitle);
		cell = row.createCell(11);
		cell.setCellStyle(cellStyleTitle);
		//----------------------
		cell = row.createCell(12);
		cell.setCellValue("Số mất");
		sheet.addMergedRegion(new CellRangeAddress(r, r, 12, 13));
		cell.setCellStyle(cellStyleTitle);
		cell = row.createCell(13);
		cell.setCellStyle(cellStyleTitle);
		//----------------------
		cell = row.createCell(14);
		cell.setCellValue("Số hủy");
		sheet.addMergedRegion(new CellRangeAddress(r, r, 14, 15));
		cell.setCellStyle(cellStyleTitle);
		cell = row.createCell(15);
		cell.setCellStyle(cellStyleTitle);
		
		// Số lượng xóa bỏ -> Số lượng hủy		
		r= r+1;
		row = sheet.createRow((short) r);
		row.setHeight((short) 450);
		for(int i=0;i<rowTitle.length;i++){
			if(i >= 10){
//				sheet.addMergedRegion(new Region(r, (short) i, r+1, (short) i));
				sheet.addMergedRegion(new CellRangeAddress(r, r+1, i, i));
				cell = row.createCell(i);
				cell.setCellValue(getStringCell(rowTitle[i]));
				cell.setCellStyle(cellStyleTitle);
			}
  	  	}
		
		// row number column
		r= r+2;
		row = sheet.createRow((short) (r));	
//		row.setHeight((short) 400);
		for(int i=0;i<16;i++){
			int k = i+1;
			cell = row.createCell(i);
			cell.setCellValue("["+ k +"]");
			cell.setCellStyle(cellStyleTitle);
  	  	}
		
		/**
		 * 	I./ LOOP DATA:
		 */
        if(list != null && list.size() >0){
        	for (ReportUseOfInvoicesView obj : list) {
        		String mstNguoiBan = "", mauHoaDon = "", kyHieuHoaDon = "", maLoaiHoaDon = "", tenHoaDon = "", 
        				tuSo = "", denSo = "", tongCong = "", soLuongDaSuDung = "", soLuongXoaBo = "", soXoaBo = "", soLuongHuy = "", 
        				soHuy = "", soLuongMat = "", soMat = "";
        		
        		if(!StringUtils.isEmpty(obj.getMstNguoiBan())){
        			mstNguoiBan = obj.getMstNguoiBan();
        		}
        		if(!StringUtils.isEmpty(obj.getMauHoaDon())){
        			mauHoaDon = obj.getMauHoaDon();
        		}
        		if(!StringUtils.isEmpty(obj.getKyHieuHoaDon())){
        			kyHieuHoaDon = obj.getKyHieuHoaDon();
        		}
        		
        		if(!StringUtils.isEmpty(obj.getMaLoaiHoaDon())){
        			maLoaiHoaDon = obj.getMaLoaiHoaDon();
        		}
        		if(!StringUtils.isEmpty(obj.getTenHoaDon())){
        			tenHoaDon = obj.getTenHoaDon();
        		}
        		if(!StringUtils.isEmpty(obj.getTuSo())){
        			tuSo = obj.getTuSo();
        		}
        		if(!StringUtils.isEmpty(obj.getDenSo())){
        			denSo = obj.getDenSo();
        		}
        		if(!StringUtils.isEmpty(obj.getTongCong())){
        			tongCong = obj.getTongCong();
        		}
        		if(!StringUtils.isEmpty(obj.getSoLuongDaSuDung())){
        			soLuongDaSuDung = obj.getSoLuongDaSuDung();
        		}
        		if(!StringUtils.isEmpty(obj.getSoLuongXoaBo())){
        			soLuongXoaBo = obj.getSoLuongXoaBo();
        		}
        		if(!StringUtils.isEmpty(obj.getSoXoaBo())){
        			 soXoaBo = obj.getSoXoaBo();
        		}
        		if(!StringUtils.isEmpty(obj.getSoLuongHuy())){
       			 	soLuongHuy = obj.getSoLuongHuy();
        		}
        		if(!StringUtils.isEmpty(obj.getSoHuy())){
       			 	soHuy = obj.getSoHuy();
        		}
        		if(!StringUtils.isEmpty(obj.getSoLuongMat())){
       			 	soLuongMat = obj.getSoLuongMat();
        		}
        		if(!StringUtils.isEmpty(obj.getSoMat())){
        			soMat = obj.getSoMat();
        		}
    			
                row = sheet.createRow((r + 1));
//                row.setHeight((short) 300);
                cell = row.createCell(0);
                cell.setCellValue(stt);
                cell.setCellStyle(cellStyleBorder);
                
                setValueRow(cellStyleBackgroundColor, row, mstNguoiBan, mauHoaDon, kyHieuHoaDon, maLoaiHoaDon, tenHoaDon, tuSo, denSo, tongCong, 
                		soLuongDaSuDung, soLuongXoaBo, soXoaBo, soLuongHuy, soHuy, soLuongMat, soMat);
                  
                r++;
                stt++;
			}
        }
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
			String mstNguoiBan, String mauHoaDon, String kyHieuHoaDon, String maLoaiHoaDon, String tenHoaDon, String tuSo, String
			denSo, String tongCong, String soLuongDaSuDung, String soLuongXoaBo, String soXoaBo, String soLuongHuy, String soHuy, String soLuongMat, String soMat) {
		
		HSSFCell cell;
		cell = row.createCell(1);
		cell.setCellValue(getStringCell(mstNguoiBan + ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(2);
		cell.setCellValue(getStringCell(maLoaiHoaDon+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(3);
		cell.setCellValue(getStringCell(tenHoaDon+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(4);
		cell.setCellValue(getStringCell(mauHoaDon+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(5);
		cell.setCellValue(getStringCell(kyHieuHoaDon+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(6);
		cell.setCellValue(getStringCell(tuSo+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(7);
		cell.setCellValue(getStringCell(denSo+ ""));
		cell.setCellStyle(bgcolor);

		cell = row.createCell(8);
		cell.setCellValue(getStringCell(tongCong+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(9);
		cell.setCellValue(getStringCell(soLuongDaSuDung+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(10);
		cell.setCellValue(getStringCell(soLuongXoaBo+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(11);
		cell.setCellValue(getStringCell(soXoaBo+ ""));
		cell.setCellStyle(bgcolor);

		cell = row.createCell( 12);
		cell.setCellValue(getStringCell(soLuongHuy+ ""));
		cell.setCellStyle(bgcolor);

		cell = row.createCell( 13);
		cell.setCellValue(getStringCell(soHuy+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell( 14);
		cell.setCellValue(getStringCell(soLuongMat+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell( 15);
		cell.setCellValue(getStringCell(soMat+ ""));
		cell.setCellStyle(bgcolor);
		
	}

	private void setWidthColumn(HSSFSheet sheet) {
		// Set Column Widths
		sheet.setColumnWidth(0, 1500);
		sheet.setColumnWidth(1, 3800);
		sheet.setColumnWidth(2, 2500);
		sheet.setColumnWidth(3, 5000);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 2000); // ký hiệu
		sheet.setColumnWidth(6, 3000);
		sheet.setColumnWidth(7, 3000);
		sheet.setColumnWidth(8, 2000);
		sheet.setColumnWidth(9, 2000);
		sheet.setColumnWidth(10, 2000);
		sheet.setColumnWidth(11, 5000); //số xóa bỏ
		sheet.setColumnWidth(12, 1500);
		sheet.setColumnWidth(13, 1500);
		sheet.setColumnWidth(14, 1500);
		sheet.setColumnWidth(15, 1500);
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
