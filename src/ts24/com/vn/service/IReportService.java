package ts24.com.vn.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface IReportService<T> {
	public HSSFWorkbook exportExcel(List<T> data_TH,List<T> data, String tuNgay, String denNgay, String fieldSorting) throws Exception;
}
