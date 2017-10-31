package ts24.com.vn.web.unit;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import ts24.com.vn.model.ObjUserProfile;

import com.google.gson.Gson;
import com.sun.jersey.core.util.Base64;

public class TestSocial {

	static String GOOGLE_CLIENT_ID ="226127222416-mctn01qh05sgnbbn7fgfa5erqbic1svf.apps.googleusercontent.com";
	static String GOOGLE_CLIENT_SECRET="iwgtAW2g74P5EAw7SkOUzEK4";
	static String GOOGLE_REDIRECT_URI = "http://localhost:8080/tracuu.xhd/admincallservices/loginTS24IDGoogle.bv";
	static String FORCE = "force";
	static String CODE = "code";
	public static void main(String[] args) {
		loginGoogle();
	}
	
	private static  String getGoogleAuthUrl() {
		String googleLoginUrl = "";
		try {
			googleLoginUrl = "https://accounts.google.com/o/oauth2/auth?" 
				+ "scope=email"
				+ "&redirect_uri=" + URLEncoder.encode(GOOGLE_REDIRECT_URI, "UTF-8")
				+ "&response_type=" + CODE
				+ "&client_id=" + GOOGLE_CLIENT_ID
				+ "&approval_prompt=" + FORCE;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("Caused by [getGoogleAuthUrl]: ");
		}
		return googleLoginUrl;
	}

	private static void loginGoogle() {
		try {
			String googleLoginUrl = "";
			try {
				googleLoginUrl = "https://accounts.google.com/o/oauth2/auth?" 
					+ "scope=email"
					+ "&redirect_uri=" + URLEncoder.encode(GOOGLE_REDIRECT_URI, "UTF-8")
					+ "&response_type=" + CODE
					+ "&client_id=" + GOOGLE_CLIENT_ID
					+ "&approval_prompt=" + FORCE;
				System.out.println("lam==" + googleLoginUrl);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			String BASE_URL = "http://localhost:8080/ts24id.rest/IDTS24.rest";
			String auth = new String(Base64.encode("admin:admin"));
			/** Create Issue **/
		//	String param = "?userAccount=do.tran@ts24corp.com&passAccount=123456&option=ID&userAgent=ts24pro&passAgent=e10adc3949ba59abbe56e057f20f883e";
			String param = "?userAgent=ts24pro&passAgent=e10adc3949ba59abbe56e057f20f883e";			
			Gson gson = new Gson();
			ObjUserProfile objProfile = new ObjUserProfile();
			objProfile.setUid("lam.le11@ts24corp.com");
			objProfile.setMail("lam.le11@ts24corp.com");
			objProfile.setFullname("le ngoc lam");
			objProfile.setTelephoneNumber("123456");
			objProfile.setUserPassword("e10adc3949ba59abbe56e057f20f883e");
		    String data = gson.toJson(objProfile);
		    
			/*String a = Jstringify({"answer":"asdasdasd","countblocked":0,
				"countlogin":0,"fullname":"lam","mail":"lam.le3@ts24corp.com",
				"permanentblocked":0,"question":"asdasd","registeddate":"",
				"status":1,"telephoneNumber":"0213121212","uid":"lam.le3@ts24corp.com",
				"userPassword":"e10adc3949ba59abbe56e057f20f883e"});*/
			
		//	String issue = TS24IDServiceRest.postMethodData( BASE_URL+"/createProfile" + param, data);
		//	System.out.println(issue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
