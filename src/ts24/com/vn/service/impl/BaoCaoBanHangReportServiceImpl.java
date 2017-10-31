package ts24.com.vn.service.impl;

import java.util.List;

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
import org.springframework.util.StringUtils;

import ts24.com.vn.dal.dao.HoaDonDao;
import ts24.com.vn.dal.model.nonentity.BaoCaoBanHangView;
import ts24.com.vn.service.IReportService;

public class BaoCaoBanHangReportServiceImpl  implements IReportService<BaoCaoBanHangView>{

	private Logger logger = Logger.getLogger(BaoCaoBanHangReportServiceImpl.class.getName());
	HoaDonDao dao;
	
	public HSSFWorkbook exportExcel(List<BaoCaoBanHangView> list_TH,List<BaoCaoBanHangView> list, String tuNgay, String denNgay, String fieldSorting) throws Exception {
		/**
         * 	PROCESS EXCEL
         */
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFCellStyle cellStyleTitle, cellStyleRecordYellow, cellStyleRecordRed, cellStyleYellowColorDes, cellStyleredColorDes, cellStyleBorder,cellStyleBorder1, cellStyleTitleMain,cellStyleTitleDate,cellStyleBackgroundColor = null;
		HSSFFont fontTitle, fontRowContent,fontTitleMain;
		HSSFSheet sheet = workbook.createSheet("Báo cáo bán hàng");
		HSSFSheet sheet1 = workbook.createSheet("Chi tiết bán hàng");
		
		
		// font tilte main
				fontTitleMain = workbook.createFont();
				fontTitleMain.setFontName("Arial");
				fontTitleMain.setFontHeightInPoints((short) 11);
				fontTitleMain.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				fontTitleMain.setColor(HSSFColor.BLUE.index);
		
				
				
		// font tilte
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
		
		
		cellStyleBorder1 = workbook.createCellStyle();
		setBorderLine(cellStyleBorder1);
		cellStyleBorder1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyleBorder1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyleBorder1.setFont(fontTitle);
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
		
		cellStyleTitleMain = workbook.createCellStyle();
		cellStyleTitleMain.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyleTitleMain.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyleTitleMain.setFont(fontTitleMain);
		
		// from date to date
		cellStyleTitleDate = workbook.createCellStyle();
		cellStyleTitleDate.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyleTitleDate.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyleTitleDate.setFont(fontRowContent);
		
		// Create Title Excel
		HSSFRow row = sheet.createRow((short) 11);
		HSSFRow row1 = sheet1.createRow((short) 18);
		HSSFCell cell;
		HSSFCell cell1;
		int stt = 1,stt1= 1;
        int r = 6,r1= 6;
        
		cellStyleTitle = workbook.createCellStyle();
//		HSSFColor colorTitle =  setColor(workbook,(byte) 192, (byte)192,(byte) 192);
//		setBackgroundColor(cellStyleTitle, colorTitle);
		cellStyleTitle.setFont(fontTitle);
		cellStyleTitle.setAlignment((short) 0);
		cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyleTitle.setWrapText(true);   //Wrapping text
		setBorderLine(cellStyleTitle);
		
		
		cellStyleYellowColorDes = workbook.createCellStyle();
//		HSSFColor yellow =  setColor(workbook,(byte) 255, (byte)255,(byte) 0);
//		setBackgroundColor(cellStyleYellowColorDes, yellow);
		
		cellStyleredColorDes = workbook.createCellStyle();
//		HSSFColor Red =  setColor(workbook,(byte) 255, (byte)102,(byte) 0);
//		setBackgroundColor(cellStyleredColorDes, Red);
		
		setWidthColumn(sheet);
		setWidthColumn1(sheet1);
		
		//String tungay = tuNgay ,denngay=denNgay ;
		/*
         * Create EXCEL
         */
		//setRowNoteData(cellStyleYellowColorDes, cellStyleredColorDes, cellStyleBorder, sheet);
		//setRowTieuDE(cellStyleYellowColorDes, cellStyleredColorDes, cellStyleBorder, sheet);
		//setRowTieuDE(cellStyleYellowColorDes, cellStyleredColorDes, cellStyleBorder, sheet1);
		// Create Title Excel
		row = sheet.createRow(2);
		cell = row.createCell( 1);
		cell.setCellValue("BÁO CÁO BÁN HÀNG");
		cell.setCellStyle(cellStyleTitleMain);
		sheet.addMergedRegion(new Region(2, (short) 1, 2, (short) 11));
		
		row = sheet.createRow(3);
		cell = row.createCell( 1);
		cell.setCellValue("Từ ngày "+tuNgay + " Đến ngày "+denNgay);
		cell.setCellStyle(cellStyleTitleDate);
		
		/*cell = row.createCell( 6);
		cell.setCellValue("Đến ngày");
		cell.setCellStyle(cellStyleBorder1);*/
		sheet.addMergedRegion(new Region(3, (short) 1, 3, (short) 11));
		
		
		row1 = sheet1.createRow(2);
		cell1 = row1.createCell( 1);
		cell1.setCellValue("BÁO CÁO BÁN HÀNG");
		cell1.setCellStyle(cellStyleTitleMain);
		sheet1.addMergedRegion(new Region(2, (short) 1, 2, (short) 18));
		
		row1 = sheet1.createRow(3);
		cell1 = row1.createCell( 1);
		cell1.setCellValue("Từ ngày "+tuNgay + " Đến ngày "+denNgay);
		cell1.setCellStyle(cellStyleTitleDate);
		
		/*cell1 = row1.createCell( 6);
		cell1.setCellValue("Đến ngày");
		cell1.setCellStyle(cellStyleBorder1);*/
		sheet1.addMergedRegion(new Region(3, (short) 1, 3, (short) 18));
		//=============
		String[] rowTitle = {"STT", "MST đơn vị bán hàng", "Mã hàng", "Tên hàng", "ĐVT", "Số lượng", "Đơn giá", 
				"Doanh thu", "Tiền thuế", "Thành tiền", "Ghi chú"};
		row = sheet.createRow((short) r);
		row.setHeight((short) 500);
		cell = row.createCell(0);
		cell.setCellValue(getStringCell(""));

		for(int i=0;i<rowTitle.length;i++){
  		  cell = row.createCell(i+1);
  		  cell.setCellValue(getStringCell(rowTitle[i]));
  		  cell.setCellStyle(cellStyleTitle);
  	  	}
		//============chi tiet ban hang=====
		String[] rowTitle1 = {"STT", "MST đơn vị bán hàng", "MST mua hàng", "Ngày bán(Xuất HD)", "Số HD", "Mẫu số" , "Ký hiệu" , "Diễn giãi", "Mã hàng", "Tên hàng", "ĐVT", "Số lượng", "Đơn giá", 
				"Doanh thu", "Thuế suất", "Tiền thuế", "Thành tiền", "Ghi chú"};
		row1 = sheet1.createRow((short) r1);
		row1.setHeight((short) 500);
		cell1 = row1.createCell(0);
		cell1.setCellValue(getStringCell(""));

		for(int i1=0;i1<rowTitle1.length;i1++){
  		  cell1 = row1.createCell(i1+1);
  		  cell1.setCellValue(getStringCell(rowTitle1[i1]));
  		  cell1.setCellStyle(cellStyleTitle);
  	  	}
		//====================
		/**
		 * 	I./ LOOP DATA:
		 */
        if(list != null && list.size() >0){
        	/*BaoCaoBanHangView objViewDenNgay = null ;
			BaoCaoBanHangView objViewTuNgay = null ;
			
				int sizeList = list.size() ;
				if(fieldSorting.contains("ASC")){
					objViewDenNgay = list.get(sizeList-1);					
    				objViewTuNgay = list.get(0);
				}else{
					objViewDenNgay = list.get(0);					
    				objViewTuNgay = list.get(sizeList-1);
				}
											
			if(!StringUtils.isEmpty(tuNgay) && StringUtils.isEmpty(denNgay)){
				if(objViewDenNgay != null){
					denngay = objViewDenNgay.getNgayXHDon();
				}
			}else if(StringUtils.isEmpty(tuNgay) && !StringUtils.isEmpty(denNgay)){
				if(objViewTuNgay != null){
					tungay = objViewTuNgay.getNgayXHDon();
				}
				
			}else if(StringUtils.isEmpty(tuNgay) && StringUtils.isEmpty(denNgay)){
				if(objViewTuNgay != null){
					tungay = objViewTuNgay.getNgayXHDon();
				}
				if(objViewDenNgay != null){
					denngay = objViewDenNgay.getNgayXHDon();
				}
			}*/
        	for (BaoCaoBanHangView obj : list_TH) {
        		  String mstNguoiBan = "", maHang= "", tenHang ="", DVT= "", soLuong= "", donGia= "",
      					doanhThu= "", tienThue="", thanhTien="", ghiChu="" ;
      					
        		  if(!StringUtils.isEmpty(obj.getMaSoThueBan())){
	       		    	mstNguoiBan = obj.getMaSoThueBan();
      		  }
	       		  if(!StringUtils.isEmpty(obj.getMaHang())){
	       		    	maHang = obj.getMaHang();
	       		  }
	       		  if(!StringUtils.isEmpty(obj.getTenHang())){
	       			   tenHang = obj.getTenHang();
	        	  }
	       		  if(!StringUtils.isEmpty(obj.getDonViTinh())){
	       			DVT = obj.getDonViTinh();
	        		    }
	       		  if(!StringUtils.isEmpty(obj.getSoLuong())){
	       			soLuong = obj.getSoLuong();
	        		    }
	       		  if(!StringUtils.isEmpty(obj.getDonGia())){
	       			donGia = obj.getDonGia();
	        		    }
	       		  if(!StringUtils.isEmpty(obj.getDoanhThu())){
	       			doanhThu = obj.getDoanhThu();// thanh tien
	        		    }
	       		  if(!StringUtils.isEmpty(obj.getTienThue())){
	       			tienThue = obj.getTienThue();
	        		    }
	       		  if(!StringUtils.isEmpty(obj.getThanhTien())){
	       			thanhTien = obj.getThanhTien();
	        		    }
	       		  if(!StringUtils.isEmpty(obj.getGhiChu())){
	       			ghiChu = obj.getGhiChu();
	        		    }
      	                //===============
                      row = sheet.createRow((r + 1));
                      
                      cell = row.createCell(1);
                      cell.setCellValue(stt);
                      cell.setCellStyle(cellStyleBorder);
                      
                      setValueRow(cellStyleBackgroundColor, row, mstNguoiBan, maHang, tenHang, DVT, soLuong, donGia,
      						doanhThu, tienThue, thanhTien, ghiChu);
                        
                      r++;
                      stt++;
        	}
        	
        	//==============
        	for (BaoCaoBanHangView obj : list) {
        	//	for (int j = 0 ; j < list.size() ;j++) {
        		String mstNguoiBan = "", maHang = "", tenHang = "", DVT = "", soLuong = "", 
        				donGia = "", doanhThu = "", tienThue = "", thanhTien = "", ghiChu="";
        		String mstMua = "", ngayBan= "", soHD= "" ,dienGiai="",thueSuat= "",mauSo= "", kyHieu= "";
        		
        		
        		  
	       		  if(!StringUtils.isEmpty(obj.getMaSoThueBan())){
	       		    	mstNguoiBan = obj.getMaSoThueBan();
        		  }
	       		  if(!StringUtils.isEmpty(obj.getMaHang())){
	       		    	maHang = obj.getMaHang();
	       		  }
	       		  if(!StringUtils.isEmpty(obj.getTenHang())){
	       			   tenHang = obj.getTenHang();
	        	  }
	       		  if(!StringUtils.isEmpty(obj.getDonViTinh())){
	       			DVT = obj.getDonViTinh();
	        		    }
	       		  if(!StringUtils.isEmpty(obj.getSoLuong())){
	       			soLuong = obj.getSoLuong();
	        		    }
	       		  if(!StringUtils.isEmpty(obj.getDonGia())){
	       			donGia = obj.getDonGia();
	        		    }
	       		  if(!StringUtils.isEmpty(obj.getDoanhThu())){
	       			doanhThu = obj.getDoanhThu();// thanh tien
	        		    }
	       		  if(!StringUtils.isEmpty(obj.getTienThue())){
	       			tienThue = obj.getTienThue();
	        		    }
	       		  if(!StringUtils.isEmpty(obj.getThanhTien())){
	       			thanhTien = obj.getThanhTien();
	        		    }
	       		  if(!StringUtils.isEmpty(obj.getGhiChu())){
	       			ghiChu = obj.getGhiChu();
	        		    }
		   		
	       		  
	       		  //=========chi tiet
	       		 if(!StringUtils.isEmpty(obj.getMaSoThueMua())){
	       			mstMua = obj.getMaSoThueMua();
     		  }
	       		  if(!StringUtils.isEmpty(obj.getNgayXHDon())){
	       			ngayBan = obj.getNgayXHDon();
	       		  }
	       		  if(!StringUtils.isEmpty(obj.getSoHD())){
	       			soHD = obj.getSoHD();
	        	  }
	       		  if(!StringUtils.isEmpty(obj.getDienGiai())){
	       			dienGiai = obj.getDienGiai();
	        		    }
	       		  if(!StringUtils.isEmpty(obj.getThueSuat())){
	       			thueSuat = obj.getThueSuat();
	        		   }
	       		  
	       		  if(!StringUtils.isEmpty(obj.getMauHDon()) && !StringUtils.isEmpty(obj.getSoThuTu())){
	       			mauSo = obj.getMauHDon()+"/"+obj.getSoThuTu();
		         }
	       		  if(!StringUtils.isEmpty(obj.getKyHieuHDon())){
	       			kyHieu = obj.getKyHieuHDon();
		         }
	       		  
	       		  row1 = sheet1.createRow((r1 + 1));
	                
	                cell1 = row1.createCell(1);
	                cell1.setCellValue(stt1);
	                cell1.setCellStyle(cellStyleBorder);
	                
	                setValueRowChiTiet(cellStyleBackgroundColor, row1, mstNguoiBan,mstMua,ngayBan,soHD,mauSo,kyHieu,dienGiai, maHang, tenHang, DVT, soLuong, donGia,
							doanhThu,thueSuat, tienThue, thanhTien, ghiChu);
	                  
	                r1++;
	                stt1++;
	       		
	               
	                //===============
              /*  row = sheet.createRow((r + 1));
                
                cell = row.createCell(1);
                cell.setCellValue(stt);
                cell.setCellStyle(cellStyleBorder);
                
                setValueRow(cellStyleBackgroundColor, row, mstNguoiBan, maHang, tenHang, DVT, soLuong, donGia,
						doanhThu, tienThue, thanhTien, ghiChu);
                  
                r++;
                stt++;*/
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
			String mstNguoiBan, String maHang, String tenHang, String DVT, String soLuong, String donGia, String
			doanhThu, String tienThue, String thanhTien, String ghiChu) {
		
		HSSFCell cell;
		cell = row.createCell(2);
		cell.setCellValue(getStringCell(mstNguoiBan + ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(3);
		cell.setCellValue(getStringCell(maHang+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(4);
		cell.setCellValue(getStringCell(tenHang+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(5);
		cell.setCellValue(getStringCell(DVT+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(6);
		cell.setCellValue(getStringCell(soLuong+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(7);
		cell.setCellValue(getStringCell(donGia+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(8);
		cell.setCellValue(getStringCell(doanhThu+ ""));
		cell.setCellStyle(bgcolor);

		cell = row.createCell(9);
		cell.setCellValue(getStringCell(tienThue+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(10);
		cell.setCellValue(getStringCell(thanhTien+ ""));
		cell.setCellStyle(bgcolor);
		
		cell = row.createCell(11);
		cell.setCellValue(getStringCell(ghiChu+ ""));
		cell.setCellStyle(bgcolor);
						
	}

	private void setValueRowChiTiet(HSSFCellStyle bgcolor, HSSFRow row,
			String mstNguoiBan, String mstMua, String ngayBan, String soHD,String mauSo, String kyHieu, String dienGiai,String maHang, String tenHang, String DVT, String soLuong, String donGia, String
			doanhThu, String thueSuat, String tienThue, String thanhTien, String ghiChu) {
		
		HSSFCell cell1;
		cell1 = row.createCell(2);
		cell1.setCellValue(getStringCell(mstNguoiBan + ""));
		cell1.setCellStyle(bgcolor);
		
		cell1 = row.createCell(3);
		cell1.setCellValue(getStringCell(mstMua + ""));
		cell1.setCellStyle(bgcolor);
		
		cell1 = row.createCell(4);
		cell1.setCellValue(getStringCell(ngayBan + ""));
		cell1.setCellStyle(bgcolor);
		
		cell1 = row.createCell(5);
		cell1.setCellValue(getStringCell(soHD + ""));
		cell1.setCellStyle(bgcolor);
		
		cell1 = row.createCell(6);
		cell1.setCellValue(getStringCell(mauSo + ""));
		cell1.setCellStyle(bgcolor);
		
		cell1 = row.createCell(7);
		cell1.setCellValue(getStringCell(kyHieu + ""));
		cell1.setCellStyle(bgcolor);
		
		cell1 = row.createCell(8);
		cell1.setCellValue(getStringCell(dienGiai + ""));
		cell1.setCellStyle(bgcolor);
				
		cell1 = row.createCell(9);
		cell1.setCellValue(getStringCell(maHang+ ""));
		cell1.setCellStyle(bgcolor);
		
		cell1 = row.createCell(10);
		cell1.setCellValue(getStringCell(tenHang+ ""));
		cell1.setCellStyle(bgcolor);
		
		cell1 = row.createCell(11);
		cell1.setCellValue(getStringCell(DVT+ ""));
		cell1.setCellStyle(bgcolor);
		
		cell1 = row.createCell(12);
		cell1.setCellValue(getStringCell(soLuong+ ""));
		cell1.setCellStyle(bgcolor);
		
		cell1 = row.createCell(13);
		cell1.setCellValue(getStringCell(donGia+ ""));
		cell1.setCellStyle(bgcolor);
		
		cell1 = row.createCell(14);
		cell1.setCellValue(getStringCell(doanhThu+ ""));
		cell1.setCellStyle(bgcolor);

		cell1 = row.createCell(15);
		cell1.setCellValue(getStringCell(thueSuat + ""));
		cell1.setCellStyle(bgcolor);
		
		cell1 = row.createCell(16);
		cell1.setCellValue(getStringCell(tienThue+ ""));
		cell1.setCellStyle(bgcolor);
		
		cell1 = row.createCell(17);
		cell1.setCellValue(getStringCell(thanhTien+ ""));
		cell1.setCellStyle(bgcolor);
		
		cell1 = row.createCell(18);
		cell1.setCellValue(getStringCell(ghiChu+ ""));
		cell1.setCellStyle(bgcolor);
						
	}
	
	private void setRowTieuDE(HSSFCellStyle cellStyleYellowColorDes, HSSFCellStyle cellStyleRedColorDes, HSSFCellStyle cellStyleBorder, HSSFSheet sheet) {
		HSSFRow row;
		HSSFCell cell;
		row = sheet.createRow( (1));
		cell = row.createCell( 0);
		cell.setCellValue(getStringCell(""));
		cell.setCellStyle(cellStyleRedColorDes);
		
		cell = row.createCell( 1);
		cell.setCellValue(getStringCell("BÁO CÁO BÁN HÀNG"));
		cell.setCellStyle(cellStyleBorder);
		
		row = sheet.createRow( (2));	
		cell = row.createCell( 0);
		cell.setCellValue(getStringCell("Từ ngày"));
		cell.setCellStyle(cellStyleYellowColorDes);
		
		cell = row.createCell( 1);
		cell.setCellValue(getStringCell("Đến ngày"));
		cell.setCellStyle(cellStyleBorder);
		
		
	}
	
	
	private void setRowNoteData(HSSFCellStyle cellStyleYellowColorDes, HSSFCellStyle cellStyleRedColorDes, HSSFCellStyle cellStyleBorder, HSSFSheet sheet) {
		HSSFRow row;
		HSSFCell cell;
		row = sheet.createRow( (1));
		cell = row.createCell( 0);
		cell.setCellValue(getStringCell(""));
		cell.setCellStyle(cellStyleRedColorDes);
		
		cell = row.createCell( 1);
		cell.setCellValue(getStringCell("Consumer"));
		cell.setCellStyle(cellStyleBorder);
		
		row = sheet.createRow( (2));	
		cell = row.createCell( 0);
		cell.setCellValue(getStringCell(""));
		cell.setCellStyle(cellStyleYellowColorDes);
		
		cell = row.createCell( 1);
		cell.setCellValue(getStringCell("Business"));
		cell.setCellStyle(cellStyleBorder);
		
		row = sheet.createRow( (3));	
		cell = row.createCell( 0);
		cell.setCellValue(getStringCell("No"));
		cell.setCellStyle(cellStyleBorder);
		
		cell = row.createCell( 1);
		cell.setCellValue(getStringCell("Not eligible"));
		cell.setCellStyle(cellStyleBorder);
		
		row = sheet.createRow( (4));	
		cell = row.createCell( 0);
		cell.setCellValue(getStringCell("Yes"));
		cell.setCellStyle(cellStyleBorder);
		
		cell = row.createCell( 1);
		cell.setCellValue(getStringCell("Eligible"));
		cell.setCellStyle(cellStyleBorder);
	}

	private void setWidthColumn1(HSSFSheet sheet) {
		// Set Column Widths
		sheet.setColumnWidth(0, 800);
		sheet.setColumnWidth(1, 1500);
		sheet.setColumnWidth(2, 3800);
		sheet.setColumnWidth(3, 3800);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 2000);//so hoa don
		sheet.setColumnWidth(6, 4000);
		sheet.setColumnWidth(7, 2000);
		sheet.setColumnWidth(8, 5000);
		sheet.setColumnWidth(9, 8000);
		sheet.setColumnWidth(10, 8000);//ten hang
		sheet.setColumnWidth(11, 1500);
		sheet.setColumnWidth(12, 2000);
		sheet.setColumnWidth(13, 3000);
		sheet.setColumnWidth(14, 3000);
		sheet.setColumnWidth(15, 2000);
		sheet.setColumnWidth(16, 3000);
		sheet.setColumnWidth(17, 3000);
		sheet.setColumnWidth(18, 4000);
		
	}
	private void setWidthColumn(HSSFSheet sheet) {
		// Set Column Widths
		sheet.setColumnWidth(0, 800);
		sheet.setColumnWidth(1, 1500);
		sheet.setColumnWidth(2, 3800);
		sheet.setColumnWidth(3, 8000);
		sheet.setColumnWidth(4, 8000);
		sheet.setColumnWidth(5, 1500);
		sheet.setColumnWidth(6, 2000);//so luong
		sheet.setColumnWidth(7, 3000);
		sheet.setColumnWidth(8, 3000);
		sheet.setColumnWidth(9, 3500);
		sheet.setColumnWidth(10, 3800);
		sheet.setColumnWidth(11, 5000);
		
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
