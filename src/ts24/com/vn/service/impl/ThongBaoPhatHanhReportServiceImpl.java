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
import ts24.com.vn.dal.model.nonentity.ReportTBaoPhatHanhView;
import ts24.com.vn.service.IReportService;

public class ThongBaoPhatHanhReportServiceImpl  implements IReportService<ReportTBaoPhatHanhView>{

	private Logger logger = Logger.getLogger(ThongBaoPhatHanhReportServiceImpl.class.getName());
	HoaDonDao dao;
	
	public HSSFWorkbook exportExcel(List<ReportTBaoPhatHanhView> list_TH, List<ReportTBaoPhatHanhView> list, String tuNgay, String denNgay, String fieldSorting) throws Exception {
		/**
         * 	PROCESS EXCEL
         */
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFCellStyle cellStyleTitle, cellStyleRecordYellow, cellStyleRecordRed, cellStyleYellowColorDes, cellStyleredColorDes, cellStyleTitleDate, cellStyleTitleDonViTien, cellStyleBorder, cellStyleNote, cellStyleTitleMain, cellStyleBackgroundColor = null;
		HSSFFont fontTitle, fontRowContent, fontTitleMain;
		HSSFSheet sheet = workbook.createSheet("TBPH");
		
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
        int r = 4;
        
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
		cell.setCellValue("THÔNG BÁO PHÁT HÀNH HÓA ĐƠN");
		sheet.addMergedRegion(new Region(2, (short) 0, 2, (short) 8));
		cell.setCellStyle(cellStyleTitleMain);
		
		row = sheet.createRow((short) (r));	
		row.setHeight((short) 500);
		cell = row.createCell(2);
		cell.setCellValue("Hoá đơn, chứng từ bán");
		sheet.addMergedRegion(new Region(r, (short) 2, r, (short) 6));
		cell.setCellStyle(cellStyleTitle);
		cell = row.createCell(3);
		cell.setCellStyle(cellStyleTitle);
		cell = row.createCell(4);
		cell.setCellStyle(cellStyleTitle);
		cell = row.createCell(5);
		cell.setCellStyle(cellStyleTitle);
		cell = row.createCell(6);
		cell.setCellStyle(cellStyleTitle);

		// Create Title Excel
		String[] rowTitle = {"STT", "Mã số thuế bán", "Mẫu hoá đơn", "Ký hiệu hoá đơn", "Số lượng", "Từ số", "Đến số", "Ngày thông báo", "Ngày bắt đầu sử dụng"};
		for(int i=0;i<rowTitle.length;i++){
			if(i<2 || i>6 ){
				sheet.addMergedRegion(new Region(r, (short) i, r+1, (short) i));
				cell = row.createCell(i);
				cell.setCellValue(getStringCell(rowTitle[i]));
				cell.setCellStyle(cellStyleTitle);
			}
  	  	}
		
		r= r+1;
		row = sheet.createRow((short) r);
		row.setHeight((short) 500);
		for(int i=0;i<rowTitle.length;i++){
			if(i>1 && i<7 ){
				cell = row.createCell(i);
				cell.setCellValue(getStringCell(rowTitle[i]));
				cell.setCellStyle(cellStyleTitle);
			}
  	  	}
		
		 // row number column
		r= r+1;
		row = sheet.createRow((short) (r));	
		row.setHeight((short) 300);
		for(int i=0;i<9;i++){
			int k = i+1;
			cell = row.createCell(i);
			cell.setCellValue("["+ k +"]");
			cell.setCellStyle(cellStyleTitle);
  	  	}
		
		/**
		 * 	I./ LOOP DATA:
		 */
        if(list != null && list.size() >0){
        	for (ReportTBaoPhatHanhView obj : list) {
        		String mstNguoiBan = "", mauHoaDon = "", kyHieuHoaDon = "", soLuong = "", tuSo = "", denSo = "", ngayLapPhieu = "", ngayBatDauSuDung = "";
        		
        		if(!StringUtils.isEmpty(obj.getMaSoThue())){
        			mstNguoiBan = obj.getMaSoThue();
        		}
        		if(!StringUtils.isEmpty(obj.getMauHoaDon())){
        			mauHoaDon = obj.getMauHoaDon();
        		}
        		if(!StringUtils.isEmpty(obj.getKyHieu())){
        			kyHieuHoaDon = obj.getKyHieu();
        		}
        		if(!StringUtils.isEmpty(obj.getSoLuong())){
        			soLuong = obj.getSoLuong();
        		}
        		if(!StringUtils.isEmpty(obj.getTuSo())){
        			tuSo = obj.getTuSo();
        		}
        		if(!StringUtils.isEmpty(obj.getDenSo())){
        			denSo = obj.getDenSo();
        		}
        		if(!StringUtils.isEmpty(obj.getNgayLapPhieu())){
        			ngayLapPhieu= obj.getNgayLapPhieu();
        		}
        		if(!StringUtils.isEmpty(obj.getNgayBatDauSuDung())){
        			ngayBatDauSuDung= obj.getNgayBatDauSuDung();
        		}

        		row = sheet.createRow((r + 1));
                row.setHeight((short) 300);
                cell = row.createCell(0);
                cell.setCellValue(stt);
                cell.setCellStyle(cellStyleBorder);
                
                setValueRow(cellStyleBackgroundColor, row, mstNguoiBan, mauHoaDon, kyHieuHoaDon, soLuong, tuSo, denSo, ngayLapPhieu, ngayBatDauSuDung);
                  
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
			String mstNguoiBan, String mauHoaDon, String kyHieuHoaDon, String soLuong, String tuSo, String denSo, String
			ngayLapPhieu, String ngayBatDauSuDung) {
		
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
		cell.setCellValue(getStringCell(soLuong+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(5);
		cell.setCellValue(getStringCell(tuSo+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(6);
		cell.setCellValue(getStringCell(denSo+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(7);
		cell.setCellValue(getStringCell(ngayLapPhieu+ ""));
		cell.setCellStyle(bgcolor);

		cell = row.createCell(8);
		cell.setCellValue(getStringCell(ngayBatDauSuDung+ ""));
		cell.setCellStyle(bgcolor);
		
	}

	private void setWidthColumn(HSSFSheet sheet) {
		// Set Column Widths
		sheet.setColumnWidth(0, 1000);
		sheet.setColumnWidth(1, 3800);
		sheet.setColumnWidth(2, 3500);
		sheet.setColumnWidth(3, 2000);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 3500);
		sheet.setColumnWidth(6, 3500);
		sheet.setColumnWidth(7, 3800);
		sheet.setColumnWidth(8, 3800);
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
