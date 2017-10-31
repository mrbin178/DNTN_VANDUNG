package ts24.com.vn.web.util;

import java.io.*;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;


public class Commons {
	String exReturn = "";
	String nameReturn = "";
	public Commons() { }
	
	public boolean checkValidFile(String fileName){
		boolean rValue = false;
		String[] exs = {"zip","jpg","bmp","png","pdf","doc"};
		
		String[] arrFName = fileName.split("\\.");
		String ex = arrFName[arrFName.length-1];
		
		for(int i=0; i<exs.length; i++){
			if(ex.equalsIgnoreCase(exs[i])) {
				rValue = true;
				exReturn = ex;
				nameReturn = arrFName[0];
			}
		}
		
		return rValue;
	}
	
	public boolean checkValidFileExcelUpload(String fileName){
		boolean rValue = false;
		String[] exs = {"xls","xlsx", "xls","xlsx"};
		
		String[] arrFName = fileName.split("\\.");
		String ex = arrFName[arrFName.length-1];
		
		for(int i=0; i<exs.length; i++){
			if(ex.equalsIgnoreCase(exs[i])) {
				rValue = true;
				exReturn = ex;
				nameReturn = arrFName[0];
			}
		}
		
		return rValue;
	}
	
	public boolean checkValidFileUpload_File(String fileName){
		boolean rValue = false;
		String[] exs = {"zip","rar","pdf","doc","xls","docx","xlsx"};
		
		String[] arrFName = fileName.split("\\.");
		String ex = arrFName[arrFName.length-1];
		
		for(int i=0; i<exs.length; i++){
			if(ex.equalsIgnoreCase(exs[i])) {
				rValue = true;
				exReturn = ex;
				nameReturn = arrFName[0];
			}
		}
		
		return rValue;
	}
	
	public boolean checkValidFileUpload_Image(String fileName){
		boolean rValue = false;
		String[] exs = {"jpg","png","bmp","tif","gif"};
		
		String[] arrFName = fileName.split("\\.");
		String ex = arrFName[arrFName.length-1];
		
		for(int i=0; i<exs.length; i++){
			if(ex.equalsIgnoreCase(exs[i])) {
				rValue = true;
				exReturn = ex;
				nameReturn = arrFName[0];
			}
		}
		
		return rValue;
	}
	
	public boolean checkValidFileUpload_Soft(String fileName){
		boolean rValue = false;
		String[] exs = {"exe","msi","zip","rar"};
		
		String[] arrFName = fileName.split("\\.");
		String ex = arrFName[arrFName.length-1];
		
		for(int i=0; i<exs.length; i++){
			if(ex.equalsIgnoreCase(exs[i])) {
				rValue = true;
				exReturn = ex;
				nameReturn = arrFName[0];
			}
		}
		
		return rValue;
	}
	
	
	public boolean checkValidFileUpload_Soft(String fileName, String[] exs){
		boolean rValue = false;
	
		
		String[] arrFName = fileName.split("\\.");
		String ex = arrFName[arrFName.length-1];
		
		for(int i=0; i<exs.length; i++){
			if(ex.equalsIgnoreCase(exs[i])) {
				rValue = true;
				exReturn = ex;
				nameReturn = arrFName[0];
			}
		}
		
		return rValue;
	}
	
	
	public void WriteCountUploadFile(boolean isHaveUpload) throws FileNotFoundException, IOException{
		//FileOutputStream output;
		//FileInputStream input = new FileInputStream(Definitions.UPLOAD_INFO_PATH+"\\counter.txt");
		//DataInputStream dtIn = new DataInputStream(input);
		
		//String sCount = dtIn.readLine();
		//input.close();
		
		//int count = 0;
		//if(sCount!=null&&!sCount.equals("")&&!sCount.equals("null"))
		//	count = Integer.parseInt(sCount);
		
		//if(isHaveUpload && (count > 0)){
		//	output = new FileOutputStream(Definitions.UPLOAD_INFO_PATH+"\\counter.txt");
		//	PrintStream ps = new PrintStream(output);
		//	ps.println(count+1);
		//	output.close();
		//}
	}
	
	public void sendStatus(String url, String id, String taxid, int status){
		//Instantiate an HttpClient
		HttpClient client = new HttpClient();
		//Instantiate a GET HTTP method
		HttpMethod method = new GetMethod(url);
		//Define name-value pairs to set into the QueryString
		NameValuePair nvp1= new NameValuePair("IdArr",id);
		NameValuePair nvp2= new NameValuePair("TaxID",taxid);
		NameValuePair nvp3= new NameValuePair("status", status + "");
		
		method.setQueryString(new NameValuePair[]{nvp1,nvp2,nvp3});
		try{
			client.executeMethod(method);
		}
		catch(IOException e) {
			e.printStackTrace();
		} 
	}
	
	public String getContents(File aFile) {
	    //...checks on aFile are elided
	    StringBuilder contents = new StringBuilder();
	    
	    try {
	      //use buffering, reading one line at a time
	      //FileReader always assumes default encoding is OK!
	      BufferedReader input =  new BufferedReader(new FileReader(aFile));
	      try {
	        String line = null; //not declared within while loop
	        /*
	        * readLine is a bit quirky :
	        * it returns the content of a line MINUS the newline.
	        * it returns null only for the END of the stream.
	        * it returns an empty String if two newlines appear in a row.
	        */
	        while (( line = input.readLine()) != null){
	          contents.append(line);
	          contents.append(System.getProperty("line.separator"));
	        }
	      }
	      finally {
	        input.close();
	      }
	    }
	    catch (IOException ex){
	      ex.printStackTrace();
	    }
	    
	    return contents.toString();
	  }

	  /**
	  * Change the contents of text file in its entirety, overwriting any
	  * existing text.
	  *
	  * This style of implementation throws all exceptions to the caller.
	  *
	  * @param aFile is an existing file which can be written to.
	  * @throws IllegalArgumentException if param does not comply.
	  * @throws FileNotFoundException if the file does not exist.
	  * @throws IOException if problem encountered during write.
	  */
	  public void setContents(File aFile, String aContents)
	                                 throws FileNotFoundException, IOException {
	    if (aFile == null) {
	      throw new IllegalArgumentException("File should not be null.");
	    }
	    if (!aFile.exists()) {
	      throw new FileNotFoundException ("File does not exist: " + aFile);
	    }
	    if (!aFile.isFile()) {
	      throw new IllegalArgumentException("Should not be a directory: " + aFile);
	    }
	    if (!aFile.canWrite()) {
	      throw new IllegalArgumentException("File cannot be written: " + aFile);
	    }

	    //use buffering
	    Writer output = new BufferedWriter(new FileWriter(aFile));
	    try {
	    	//FileWriter always assumes default encoding is OK!
	    	output.write( aContents );
	    }
	    finally {
	      output.close();
	    }
	}

	public void WriteUploadInfoFile(String data) throws FileNotFoundException, IOException{
		//File out = new File(Definitions.UPLOAD_INFO_PATH+"\\dataupload_cur.txt");
		//String str = getContents(out);
		//setContents(out, str + data.replace('\\', '|'));
	}
	
	public void WriteUrlReturn(String data) throws FileNotFoundException, IOException{
		//File out = new File(Definitions.UPLOAD_APP_INFO_PATH+"\\url_return.txt");
		//setContents(out, data);
	}

	public String getExReturn() {
		return exReturn;
	}

	public String getNameReturn() {
		return nameReturn;
	}
	public boolean checkValidFileUpload_File_XML(String fileName){
		boolean rValue = false;
		String[] exs = {"xml", "XML"};
		
		String[] arrFName = fileName.split("\\.");
		String ex = arrFName[arrFName.length-1];
		
		for(int i=0; i<exs.length; i++){
			if(ex.equalsIgnoreCase(exs[i])) {
				rValue = true;
				exReturn = ex;
				nameReturn = arrFName[0];
			}
		}
		
		return rValue;
	}
}
