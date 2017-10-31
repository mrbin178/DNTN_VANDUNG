package ts24.com.vn.web.util;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.hssf.record.*;
import org.apache.poi.hssf.model.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.*;

import java.sql.*;
import java.util.*;
import java.io.*;
import java.util.zip.*;

public class ExcelManager {
    public ExcelManager() {
    }

    public void zipfile(String InFolder,String OutZip){
    	
    	
    	  try
    	     {
    		   
    	     File inFolder=new File(InFolder);
    		 File outFolder=new File(OutZip);
    	     ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
    	     BufferedInputStream in = null;
    	     byte[] data    = new byte[1000];
    	     String files[] = inFolder.list();
    	     for (int i=0; i<files.length; i++)
    	      {
    	      in = new BufferedInputStream(new FileInputStream(inFolder.getPath() + "/" + files[i]), 1000);                 
    	      out.putNextEntry(new ZipEntry(files[i])); 
    	      int count;
    	      while((count = in.read(data,0,1000)) != -1)
    			  {
    	           out.write(data, 0, count);
    	          }
    	      out.closeEntry();
    	      }
    	      out.flush();
    	      out.close();
    	      }
    	      catch(Exception e)
    	         {
    	              e.printStackTrace();
    	          } 
    	
    }
    
    
    public void zipfile_many(java.util.ArrayList arrFileName,String sPath,String OutZip){
    	
    	
  	  try
  	     {
  		   
  	     
  		 File outFolder=new File(OutZip);
  	     ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
  	     BufferedInputStream in = null;
  	     byte[] data    = new byte[1000];
  	     
  	     
  	     
  	     int sizefile=arrFileName.size();
  	     for (int i=0; i<sizefile; i++)
  	      {
  	     String filename[]=arrFileName.get(i).toString().split("/",2);
  	     
  	    	 
  	      in = new BufferedInputStream(new FileInputStream(sPath+ "/" + arrFileName.get(i)), 1000);                 
  	      out.putNextEntry(new ZipEntry(filename[1].toString())); 
  	      int count;
  	      while((count = in.read(data,0,1000)) != -1)
  			  {
  	           out.write(data, 0, count);
  	          }
  	      out.closeEntry();
  	      }
  	      out.flush();
  	      out.close();
  	      }
  	      catch(Exception e)
  	         {
  	              e.printStackTrace();
  	          } 
  	
  }
    

    public int  check_file(String FileName){
    int err=0;
    try
           {

               // create a poi workbook from the excel spreadsheet file
               POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(FileName));
               HSSFWorkbook wb = new HSSFWorkbook(fs);



           }  catch (Exception e)
           {
               err=1;
              // e.printStackTrace();
            }
    return err;
    }


    public int  check_number_column(String FileName){
    int err=0;
    try
           {
               POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(FileName));
               HSSFWorkbook wb = new HSSFWorkbook(fs);


           }  catch (Exception e)
           {
               err=1;
             //  e.printStackTrace();
            }
    return err;
    }



    public int checkNumber(String str){
    int value=0;
    String arr[]={"0","1","2","3","4","5","6","7","8","9"};
    for(int i=0;i<str.length();i++){
       for(int j=0;j<arr.length;j++){
          if(!arr[j].equals(String.valueOf(str.charAt(i)))){
             return 1;
          }
       }
    }

    return value;
    }

   public int checkarr(int so, java.util.ArrayList Arr){
    for(int i=0;i<Arr.size();i++){
      if(Integer.parseInt(Arr.get(i).toString()) ==so){
          return 1;
       }
     }
      return 0;

     }
   
   
   public String getMaSoThue(String sNumber){
	   sNumber=sNumber.trim();
	   if(sNumber.length()>0 && sNumber.length() <11 ){
		   return sNumber+"000";
	   }
	return sNumber;
   }
   
   public String getHaiSo(String sNumber){
	   sNumber=sNumber.trim();
	   if(sNumber.length()<2 ){
		   return "0"+sNumber;
	   }
	return sNumber;
   }
	   
  
   public String getValueExcel(HSSFCell cell, HSSFSheet sheet,HSSFWorkbook wb){
	   Utilities oDate=new Utilities();
	   
	   String sValue="";
	   if(cell!=null){
	  
	  switch (cell.getCellType()) {
       case HSSFCell.CELL_TYPE_BOOLEAN:
    	  sValue="";
           break;
      
        
           
       case HSSFCell.CELL_TYPE_STRING:
    	   sValue=cell.getStringCellValue();
    	   break;   
     
       case HSSFCell.CELL_TYPE_NUMERIC:
 
    	   if(HSSFDateUtil.isCellDateFormatted(cell)){
				Calendar cal = Calendar.getInstance(); 
				   double d = cell.getNumericCellValue(); 
				   
				   cal.setTime(HSSFDateUtil.getJavaDate(d)); 
				   sValue = (String.valueOf(cal.get(Calendar.YEAR))).substring(0); 
				   sValue = this.getHaiSo(String.valueOf(cal.get(Calendar.DAY_OF_MONTH))) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" +  sValue;     
    	   }else{
    	   
    	   if(!String.valueOf(cell.getNumericCellValue()).equals("") && !String.valueOf(cell.getNumericCellValue()).equals("null") )
    	      sValue=oDate.formatNum_Grid(cell.getNumericCellValue());
           
    	   }
    	   break;   
       
       case HSSFCell.CELL_TYPE_FORMULA:
    		HSSFFormulaEvaluator evaluator = new HSSFFormulaEvaluator(sheet, wb);
    		CellValue cellValue = evaluator.evaluate(cell);
    		//sValue=oDate.formatNum_Grid(cellValue.getNumberValue());
         	 
    		if(cellValue!=null){
    			
    			
    		switch (cellValue.getCellType()) {
                 case HSSFCell.CELL_TYPE_BOOLEAN:
                	 sValue="";
                     break;
                 case HSSFCell.CELL_TYPE_NUMERIC:
                	 sValue=oDate.formatNum_Grid(cellValue.getNumberValue());
                     break;
                 case HSSFCell.CELL_TYPE_FORMULA:
                	 sValue=oDate.formatNum_Grid(cellValue.getNumberValue());;
                	 break;
                 case HSSFCell.CELL_TYPE_STRING:
                	 sValue=cellValue.getStringValue();
                	 break;
                 case HSSFCell.CELL_TYPE_BLANK:
                	 sValue="";
                	 break;
                 case HSSFCell.CELL_TYPE_ERROR:
                	 sValue="";
                	 break;
    		 }
    		}
    		
       break;
       case HSSFCell.CELL_TYPE_BLANK:
    	   sValue="";
       	 break;
       case HSSFCell.CELL_TYPE_ERROR:
    	   sValue="";
       break;
       }

	   }

	return sValue;
 }
   
   public String getValueExcel_NoNumber(HSSFCell cell, HSSFSheet sheet,HSSFWorkbook wb){
	   Utilities oDate=new Utilities();
	   
	   String sValue="";
	   if(cell!=null){
	  
	  switch (cell.getCellType()) {
       case HSSFCell.CELL_TYPE_BOOLEAN:
    	  sValue="";
           break;
      
        
           
       case HSSFCell.CELL_TYPE_STRING:
    	   sValue=cell.getStringCellValue();
    	   break;   
     
       case HSSFCell.CELL_TYPE_NUMERIC:
 
    	   if(HSSFDateUtil.isCellDateFormatted(cell)){
				Calendar cal = Calendar.getInstance(); 
				   double d = cell.getNumericCellValue(); 
				   
				   cal.setTime(HSSFDateUtil.getJavaDate(d)); 
				   sValue = (String.valueOf(cal.get(Calendar.YEAR))).substring(0); 
				   sValue = this.getHaiSo(String.valueOf(cal.get(Calendar.DAY_OF_MONTH))) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" +  sValue;     
    	   }else{
    	   
    	   if(!String.valueOf(cell.getNumericCellValue()).equals("") && !String.valueOf(cell.getNumericCellValue()).equals("null") )
    		   sValue=oDate.formatNum_Grid(cell.getNumericCellValue());
    	       sValue=sValue.replaceAll("\\.","");
           
    	   }
    	   break;   
       
       case HSSFCell.CELL_TYPE_FORMULA:
    		HSSFFormulaEvaluator evaluator = new HSSFFormulaEvaluator(sheet, wb);
    		CellValue cellValue = evaluator.evaluate(cell);
    		//sValue=oDate.formatNum_Grid(cellValue.getNumberValue());
         	 
    		if(cellValue!=null){
    			
    			
    		switch (cellValue.getCellType()) {
                 case HSSFCell.CELL_TYPE_BOOLEAN:
                	 sValue="";
                     break;
                 case HSSFCell.CELL_TYPE_NUMERIC:
                	 sValue=oDate.formatNum_Grid(cellValue.getNumberValue());
                	 sValue=sValue.replaceAll("\\.","");
                     break;
                 case HSSFCell.CELL_TYPE_FORMULA:
                	 sValue=oDate.formatNum_Grid(cellValue.getNumberValue());
                	 sValue=sValue.replaceAll("\\.","");
                	 break;
                 case HSSFCell.CELL_TYPE_STRING:
                	 sValue=cellValue.getStringValue();
                	 break;
                 case HSSFCell.CELL_TYPE_BLANK:
                	 sValue="";
                	 break;
                 case HSSFCell.CELL_TYPE_ERROR:
                	 sValue="";
                	 break;
    		 }
    		}
    		
       break;
       case HSSFCell.CELL_TYPE_BLANK:
    	   sValue="";
       	 break;
       case HSSFCell.CELL_TYPE_ERROR:
    	   sValue="";
       break;
       }

	   }

	return sValue;
 }
   
 



}
