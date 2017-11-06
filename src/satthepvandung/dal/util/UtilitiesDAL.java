package satthepvandung.dal.util;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains all common functions use for project
 * 
 * 
 */
public class UtilitiesDAL {

	// support unique id's
	public static Random random = new Random(System.currentTimeMillis());
	private static long latestTime = 0;
	private static int latestRand = 0;
	private static int seqnr = 0;
	// range to use for converting long numbers: digits, lower case letters,
	// upper case letters
	// (except l (lower case L), to not confuse with 1 (one))
	private static String alphanums = "0123456789abcdefghijkmnopqrstuvwxyz";

	// two digits
	private static int dig2 = alphanums.length() * alphanums.length();

	/**
	 * Convert a number to a relatively short representation, using a high
	 * radix, using digits and letters, both lower and upper case (except
	 * capitals "I" and "O").
	 * 
	 * @param number
	 *            the number to convert
	 * @return the resulting string
	 */
	static public String superRadix(long num, String charset) {
		if (num == 0)
			return "0";

		// the actual radix of 60 (the higher the radix, the smaller the
		// resulting representation)
		int radlen = charset.length();

		// generate the digits
		StringBuilder result = new StringBuilder();
		while (num > 0) {
			int mod = (int) (num % radlen); // get the modulo (remainder) of
											// dividing by 60
			result.insert(0, charset.substring(mod, mod + 1));
			num /= radlen; // integer division
		}
		return result.toString();
	}

	/**
	 * get a unique identifier, the result is shorter than the UUID, but still
	 * likely to be unique the id is built up from three random generators, that
	 * have very different seeds: 1- the time in msec since 1/1/2008 2- a number
	 * from a randomizer with its seed set at start up even if two machines
	 * generate the id at exactly the same time and date in milliseconds, the
	 * chance of the numbers be the same is still only 1 out of 10000 the result
	 * is codified using a 36 radix (thus using all numbers and letters)
	 * 
	 * @return
	 */
	synchronized public static String uniqueid() {
		// extension of base time, in milliseconds, representing the days
		// between, roughly, 1/1/1970 and 8/1/2008
		long ext = (38 * 365 + 9 + 180 + 31) * 24; // (the compiler will
													// optimize this to one
													// number)
		ext *= 3600;
		ext *= 1000;

		// the milliseconds since the extended base time
		long current = System.currentTimeMillis();
		long x = current - ext; // this will make the number smaller, for easier
								// debugging
		int rand;
		if (current == latestTime) {
			// this situation happens when multiple id's are generated,
			// typically
			// during conversion from OG. Use a sequence number to make the
			// value unique
			rand = seqnr++;
			// make sure not to collide with the latest random number
			if (rand == latestRand)
				rand = seqnr++;
		} else {
			// create a random number (seeded when the WS started)
			rand = random.nextInt(dig2);
			seqnr = 0;
			latestTime = current;
			latestRand = rand;
		}

		// create the guid by concatenating the two "numbers" using a radix that
		// includes
		// digits, lower and upper case letters
		return superRadix(x, alphanums) + superRadix(rand, alphanums);
	}
	
	static public boolean validatePassword(String password) {
		Pattern p = Pattern.compile("[A-Z]");
		Matcher m = p.matcher(password);
		if (m.find() && hasNumber(password) && hasLowerCase(password)) {
			return true;
		}
		return false;
	}
	


	public static boolean hasNumber(String s) {
		for (int j = 0;j < s.length();j++) {
			if (Character.isDigit(s.charAt(j))) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean hasLowerCase(String s) {
		for (int j = 0;j < s.length();j++) {
			if (Character.isLowerCase(s.charAt(j))) {
				return true;
			}
		}
		return false;
	}
	
	public static Date getCurrentDate(){
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}
	public static String formatEffectiveDate(Date date){
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyyMMdd");
		return dt1.format(date);
	}
	public static String formatRateValue(double d){
		DecimalFormat decim = new DecimalFormat("00.0000");
		return decim.format(d);
	}
	public static String convertTransactionTime(Date date){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dateFormat.format(date);
	}
	
	public static String md5_md5(String input) throws Exception

	{
		String s = input;
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(s.getBytes(), 0, s.length());
		// System.out.println("MD5: "+new
		// BigInteger(1,m.digest()).toString(16));
		// return new BigInteger(1,m.digest()).toString(16);
		return asHex(m.digest());
	}

	public static String asHex(byte[] buf) {
		char[] HEX_CHARS = "0123456789abcdef".toCharArray();

		char[] chars = new char[2 * buf.length];
		for (int i = 0; i < buf.length; ++i) {
			chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
			chars[2 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
		}
		return new String(chars);
	}
	
	
	public static int checkEmail( String _email )
	{
		if ( !StringHasNTimeChar( _email, '@', 1 ) )
		{
			return 1;
		}
		return StringHasChar( _email, '.' ) ? 0 : 2;
	}
	
	public static boolean StringHasNTimeChar( String _stringToCheck, char _char,
			int _existTime )
	{
		boolean returnValue = false;
		int i = 0;
		int times = 0;
		for ( ; i < _stringToCheck.length( ); i ++ )
		{
			if ( _stringToCheck.charAt( i ) != _char )
			{
				continue;
			}
			times ++;
			if ( times == _existTime + 1 )
			{
				break;
			}
		}

		return times == _existTime;
	}
	
	public static boolean StringHasChar( String _stringToCheck, char _char )
	{
		boolean returnValue = false;
		int i = 0;
		do
		{
			if ( i >= _stringToCheck.length( ) )
			{
				break;
			}
			if ( _stringToCheck.charAt( i ) == _char )
			{
				returnValue = true;
				break;
			}
			returnValue = false;
			i ++;
		}
		while ( true );
		return returnValue;
	}
	
	public static boolean checkMaSoThue(String value_mst){
		try{
			if(value_mst.length() < 10 || value_mst.length()>14)
				return false;
			String msttext = formatMaSoThue(value_mst);
			
			if(msttext=="0000000000" && !IsNumeric(msttext))
				return false;
			
			int kq=0;
			kq=kq+ Integer.parseInt(msttext.substring(0,1))*31;
			kq=kq+Integer.parseInt(msttext.substring(1,2))*29;
			kq=kq+Integer.parseInt(msttext.substring(2,3))*23;
			kq=kq+Integer.parseInt(msttext.substring(3,4))*19;
			kq=kq+Integer.parseInt(msttext.substring(4,5))*17;
			kq=kq+Integer.parseInt(msttext.substring(5,6))*13;
			kq=kq+Integer.parseInt(msttext.substring(6,7))*7;
			kq=kq+Integer.parseInt(msttext.substring(7,8))*5;
			kq=kq+Integer.parseInt(msttext.substring(8,9))*3;
			

			if( Integer.parseInt(msttext.substring(9,10))==(10-(kq % 11))){
				return true;
			}
			else{ return false; }
		
		}catch(Exception e) {
	         e.printStackTrace();
	         return false;
	    }
	}
	
	public static String formatMaSoThue(String number){
		if(number.length() >= 10){
			number=number.substring(0, 10);
		}
		return number;
	}
	
	public static boolean IsNumeric(String sText)
	{
		try{
		    String ValidChars = "0123456789.";
		    boolean IsNumber=true;
		    String Char;
		    for (int i = 0; i < sText.length() && IsNumber == true; i++) 
		    { 
			    Char = String.valueOf(sText.charAt(i)); 
			    if (ValidChars.indexOf(Char) == -1) 
			    {
			       IsNumber = false;
			    }
		    }
		    return IsNumber;
		}catch(Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
}
