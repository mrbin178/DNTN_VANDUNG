package satthepvandung.web.common;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class LocaleCustomize{
	
	public static Locale getLocale(String keyLocale){
		//returns array of all locales
	    Locale locales[] = SimpleDateFormat.getAvailableLocales();
	    Locale locale = null;
	    for (int i = 0; i < locales.length; i++) {
	    	if(locales[i].toString().equals(keyLocale)){
	    		locale = locales[i];
	    	}
		}
	    return locale;
	}
	
}
