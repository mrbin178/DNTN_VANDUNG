package ts24.com.vn.web.util;

import java.io.InputStream;
import java.util.Properties;

//import org.ts24.admin.bso.util.PropertiesTS;


public class Path {
	
	public static String SMTP_HOST_NAME;
	public static String FROM_HOST_NAME;
	public static String SMTP_AUTH_USER;
	public static String SMTP_AUTH_PWD;
	
	public static String ACCESS_KEY;
	public static String PATH_DOWNLOAD;
	public static String URL_WEBAPP;
	public static String URL_XSL;
	public static String URL_XSL_BV;
	public static String URL_XSLT_VNPT;
	public static String UPLOAD_WEBFILE_PATH;
	
	public static String IDTS24REST_URL;
	public static String ID_AGENT;
	public static String PASS_AGENT;
	
	public static String GOOGLE_CLIENT_ID;
	public static String GOOGLE_CLIENT_SECRET;
	public static String GOOGLE_REDIRECT_URI;

	public static String GOOGLE_CAPTCHA_PUBLICKEY;
	public static String GOOGLE_CAPTCHA_PRIVATEKEY;
	public static String GOOGLE_CAPTCHA_URL;
	
	public static String URL_XSLT_RECEIPT;
	public static String URL_XSL_TS24;
	
	public  Path(){
		Properties m_Properties;
		try{
			
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream oInputStream = loader.getResourceAsStream ("resources/application.properties");
			 m_Properties  = new Properties();
			 m_Properties.load(oInputStream);
		 
			 Path.ACCESS_KEY = m_Properties.getProperty("ACCESS_KEY");
			 Path.PATH_DOWNLOAD = m_Properties.getProperty("PATH_DOWNLOAD");
			 Path.URL_WEBAPP = m_Properties.getProperty("URL_WEBAPP");
			 Path.URL_XSL = m_Properties.getProperty("URL_XSL");
			 Path.URL_XSL_BV = m_Properties.getProperty("URL_XSL_BV");
			 Path.URL_XSLT_VNPT = m_Properties.getProperty("URL_XSLT_VNPT");
//			 Path.KEY_FULL_SEARCH= m_Properties.getProperty("KEY_FULL_SEARCH");
			 Path.UPLOAD_WEBFILE_PATH = m_Properties.getProperty("UPLOAD_WEBFILE_PATH");
			 
			 Path.IDTS24REST_URL = m_Properties.getProperty("IDTS24REST_URL");
			 Path.ID_AGENT = m_Properties.getProperty("ID_AGENT");
			 Path.PASS_AGENT = m_Properties.getProperty("PASS_AGENT");
			 
			 Path.GOOGLE_CLIENT_ID = m_Properties.getProperty("GOOGLE_CLIENT_ID");
			 Path.GOOGLE_CLIENT_SECRET = m_Properties.getProperty("GOOGLE_CLIENT_SECRET");
			 Path.GOOGLE_REDIRECT_URI = m_Properties.getProperty("GOOGLE_REDIRECT_URI");
			 
			 Path.GOOGLE_CAPTCHA_PUBLICKEY = m_Properties.getProperty("GOOGLE_CAPTCHA_PUBLICKEY");
			 Path.GOOGLE_CAPTCHA_PRIVATEKEY = m_Properties.getProperty("GOOGLE_CAPTCHA_PRIVATEKEY");
			 Path.GOOGLE_CAPTCHA_URL = m_Properties.getProperty("GOOGLE_CAPTCHA_URL");
			 
			 Path.URL_XSLT_RECEIPT = m_Properties.getProperty("URL_XSLT_RECEIPT");
			 
			 Path.URL_XSL_TS24 = m_Properties.getProperty("URL_XSL_TS24");
			 			 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
