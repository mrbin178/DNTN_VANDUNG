package satthepvandung.web.util;

//Processed by NMI's Java Code Viewer 4.8.1 Â© 1997-2000 B. Lemaire
//Website: http://njcv.htmlplanet.com	E-mail: info@njcv.htmlplanet.com
//Copy registered to Evaluation Copy
//Source File Name:   Utilities.java

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;

import com.itextpdf.text.pdf.AcroFields;

//Referenced classes of package goldencad.util:
//         LogManager

public class Utilities {

	static int POSTLEITZAHL_LENGTH = 5;
	static int DATE_MAXLENGTH = 10;
	boolean RESTRICT_BROWSING = false;
	boolean RESTRICT_WHITELIST = false;
	String RESTRICT_PATH = "/etc;/var";
	private LogManager log = LogManager.getInstance();

	// LogManager log;

	public Utilities() {
		// log = LogManager.getInstance();
	}

	public static int countChar(char _ch, String _inString) {

		if (_inString == null)
			throw new IllegalArgumentException("ERROR: Argument is null!");
		int length = _inString.length();
		int count = 0;
		for (int i = 0; i < length; i++)
			if (_inString.charAt(i) == _ch)
				count++;

		return count;
	}

	public static boolean checkName(String _name) {
		return checkName(_name, 1);
	}

	public static boolean checkName(String _name, int _minLetterCount) {
		if (_minLetterCount <= 0)
			throw new IllegalArgumentException(
					"ERROR: Minimum letter count must be greater than 0!");
		if (_name == null)
			return false;
		return _name.length() - countChar(' ', _name) >= _minLetterCount;
	}

	public static int checkPostleitzahl(String _postleitzahl) {

		if (_postleitzahl == null)
			return 1;
		if (_postleitzahl.length() > POSTLEITZAHL_LENGTH)
			return 2;
		if (_postleitzahl.length() < POSTLEITZAHL_LENGTH)
			return 3;
		return string2intAble(_postleitzahl) ? 0 : 4;
	}

	public static boolean checkEmail(String _email) {
		if (!StringHasNTimeChar(_email, '@', 1))
			return false;
		return StringHasChar(_email, '.') ? true : false;
	}

	public static boolean StringHasChar(String _stringToCheck, char _char) {
		boolean returnValue = false;
		int i = 0;
		do {
			if (i >= _stringToCheck.length())
				break;
			if (_stringToCheck.charAt(i) == _char) {
				returnValue = true;
				break;
			}
			returnValue = false;
			i++;
		} while (true);
		return returnValue;
	}

	public static boolean StringHasNTimeChar(String _stringToCheck, char _char,
			int _existTime) {
		boolean returnValue = false;
		int i = 0;
		int times = 0;
		for (; i < _stringToCheck.length(); i++) {
			if (_stringToCheck.charAt(i) != _char)
				continue;
			times++;
			if (times == _existTime + 1)
				break;
		}

		return times == _existTime;
	}

	public static long string2long(String s) {
		long a = 0L;
		try {
			a = Long.parseLong(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	public static boolean string2longAble(String s) {
		boolean returnvalue = false;
		long a = 0L;
		try {
			a = Long.parseLong(s);
			returnvalue = true;
		} catch (Exception e) {
			returnvalue = false;
			e.printStackTrace();
		}
		return returnvalue;
	}

	public static int string2int(String s) {
		int a = 0;
		try {
			a = Integer.parseInt(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	public static boolean string2intAble(String s) {
		boolean returnvalue = false;
		int a = 0;
		try {
			a = Integer.parseInt(s);
			returnvalue = true;
		} catch (Exception e) {
			returnvalue = false;
			e.printStackTrace();
		}
		return returnvalue;
	}

	public static String formatInputDay(String sDay) {
		if (sDay == null || sDay.length() == 0)
			return null;
		sDay.trim();
		int day = sDay.indexOf(47);
		if (day == -1)
			day = sDay.indexOf(45);
		if (day == -1)
			return null;
		int month = sDay.lastIndexOf(47);
		if (month == -1)
			month = sDay.lastIndexOf(45);
		if (month == -1)
			System.out.print("month=-1");
		String Day = (new StringBuffer(sDay)).substring(0, day);
		String Month = (new StringBuffer(sDay)).substring(day + 1, month);
		String Year = (new StringBuffer(sDay)).substring(month + 1);
		return Year + "-" + Month + "-" + Day;
	}

	public static Date formatDayFromString(String sDay) {
		if (sDay == null || sDay.length() == 0)
			return null;
		sDay.trim();
		int day = sDay.indexOf(47);
		if (day == -1)
			day = sDay.indexOf(45);
		if (day == -1)
			return null;
		int month = sDay.lastIndexOf(47);
		if (month == -1)
			month = sDay.lastIndexOf(45);
		if (month == -1)
			System.out.print("month=-1");
		String Day = (new StringBuffer(sDay)).substring(0, day);
		String Month = (new StringBuffer(sDay)).substring(day + 1, month);
		String Year = (new StringBuffer(sDay)).substring(month + 1);
		Date d = new Date(Integer.parseInt(Year), Integer.parseInt(Month),
				Integer.parseInt(Day));
		return d;
	}

	public static String formatDateStringToString(String sDay, int i) {
		if (sDay == null || sDay.length() == 0)
			return null;
		sDay.trim();
		int day = sDay.indexOf(47);
		if (day == -1)
			day = sDay.indexOf(45);
		if (day == -1)
			return null;
		int month = sDay.lastIndexOf(47);
		if (month == -1)
			month = sDay.lastIndexOf(45);
		if (month == -1)
			System.out.print("month=-1");
		int hour = sDay.lastIndexOf(58);
		int min = sDay.lastIndexOf(32);
		String Year = (new StringBuffer(sDay)).substring(0, day);
		String Month = (new StringBuffer(sDay)).substring(day + 1, month);
		String Day = (new StringBuffer(sDay)).substring(month + 1, hour - 3);
		String sHour = (new StringBuffer(sDay)).substring(min + 1, min + 3);
		String sMin = (new StringBuffer(sDay)).substring(hour + 1);
		String d = "";
		if (i == 1)
			d = sHour + ":" + sMin + " (PST) " + Day + "/" + Month + "/" + Year;
		else
			d = Day + "/" + Month + "/" + Year;
		return d;
	}

	public static String formatOutputDay(java.sql.Date date, String c) {
		if (date == null)
			return null;
		int day = date.getDate();
		int month = date.getMonth();
		int year = date.getYear();
		if (year < 1900)
			year += 1900;
		return day + c + (month + 1) + c + year;
	}

	public static String formatshortdate(String sDay) {
		if (sDay == null || sDay.length() == 0)
			return null;
		sDay.trim();
		int day = sDay.indexOf(47);
		if (day == -1)
			day = sDay.indexOf(45);
		if (day == -1)
			return null;
		int month = sDay.lastIndexOf(47);
		if (month == -1)
			month = sDay.lastIndexOf(45);
		if (month == -1)
			System.out.print("month=-1");
		String Year = (new StringBuffer(sDay)).substring(0, day);
		String Month = (new StringBuffer(sDay)).substring(day + 1, month);
		String Day = (new StringBuffer(sDay)).substring(month + 1);
		String d = Day + "/" + Month + "/" + Year;
		System.out.println("Day : " + Year + "-" + Month + "-" + Day);
		return d;
	}

	public static double roundNum(double dInput) {
		double reduce = dInput % 0.001D;
		double rs = 0.0D;
		if (reduce < 0.00040000000000000002D && reduce != 0.0D)
			rs = dInput - reduce;
		else if (reduce == 0.0D)
			rs = dInput;
		else
			rs = (dInput - reduce) + 0.001D;
		return rs;
	}

	public static float roundNum(float dInput) {
		Float reduce = new Float((double) dInput % 0.001D);
		float rs = 0.0F;
		if ((double) reduce.floatValue() < 0.00040000000000000002D
				&& reduce.floatValue() != 0.0F)
			rs = dInput - reduce.floatValue();
		else if (reduce.floatValue() == 0.0F)
			rs = dInput;
		else
			rs = (new Float((double) (dInput - reduce.floatValue()) + 0.001D))
					.floatValue();
		return rs;
	}

	public static float roundNum1(float dInput) {
		float reduce = dInput % 1.0F;
		float rs = 0.0F;
		if ((double) reduce < 0.39989999999999998D && reduce != 0.0F)
			rs = dInput - reduce;
		else if (reduce == 0.0F)
			rs = dInput;
		else
			rs = (dInput - reduce) + 1.0F;
		return rs;
	}

	public static double roundNum1(double dInput) {
		double reduce = dInput % 1.0D;
		double rs = 0.0D;
		if (reduce < 0.39989999999999998D && reduce != 0.0D)
			rs = dInput - reduce;
		else if (reduce == 0.0D)
			rs = dInput;
		else
			rs = (dInput - reduce) + 1.0D;
		return rs;
	}

	public static String convertString(String sNum) {
		int length = sNum.length();
		int chardot = sNum.indexOf(".");
		int chare = sNum.indexOf("E", chardot);
		String sMu = "";
		String sReturn = "";
		String sDigit = "";
		if (chare != -1) {
			sReturn = sReturn + sNum.substring(0, chardot);
			System.out.println("SReturn 1 is : " + sReturn);
			sMu = sNum.substring(chare + 1, length);
			System.out.println("sMu is : " + sMu);
			Integer iMu = new Integer(sMu);
			System.out.println("iMu is : " + iMu.intValue());
			if (chare - chardot - 1 >= iMu.intValue()) {
				sReturn = sReturn
						+ sNum.substring(chardot + 1, chardot + iMu.intValue()
								+ 1);
				System.out.println("SReturn 2 is : " + sReturn);
				if (chare - 1 >= chardot + iMu.intValue() + 1) {
					sDigit = sNum
							.substring(chardot + iMu.intValue() + 1, chare);
					System.out.println("sDigit 1 is : " + sDigit);
					sDigit = "." + sDigit;
					System.out.println("sDigit 2 is : " + sDigit);
					sReturn = sReturn + sDigit;
					System.out.println("SReturn 3 is : " + sReturn);
				} else {
					sReturn = sReturn + ".";
				}
			} else {
				sReturn = sReturn + sNum.substring(chardot + 1, chare);
				for (int i = 1; i <= (iMu.intValue() - chare) + chardot + 1; i++)
					sReturn = sReturn + "0";

				sReturn = sReturn + ".";
			}
		} else {
			sReturn = sNum.toString();
		}
		return sReturn;
	}

	public static String formatNum1(float fInput) {
		DecimalFormat df1 = new DecimalFormat("#,###.000");
		String returnValue = df1.format(fInput);
		return returnValue;
	}

	public static String formatNum1(double dInput) {
		DecimalFormat df1 = new DecimalFormat("#,###");
		String returnValue = df1.format(dInput);
		return returnValue;
	}

	public static String formatNumLong(long fInput) {
		DecimalFormat df1 = new DecimalFormat("#,###");
		String returnValue = df1.format(fInput);
		return returnValue;
	}

	public static String formatNum(float fInput) {
		NumberFormat df1 = NumberFormat.getInstance();
		String returnValue = df1.format(fInput);
		return returnValue;
	}

	public static String formatNum(double dInput) {
		NumberFormat df1 = NumberFormat.getInstance();
		String returnValue = df1.format(dInput);
		return returnValue;
	}

	public static String addString(String sInput) {
		int chardot = sInput.indexOf(".");
		int length = sInput.length();
		System.out.print("Do dai chuoi : " + length);
		String sReturn = null;
		if (chardot != -1) {
			sReturn = sInput;
			for (int i = 1; i <= 3 - (length - chardot - 1); i++)
				sReturn = sReturn + "0";

		} else {
			sReturn = sInput + ".000";
		}
		return sReturn;
	}

	public static String chuthuong(String sInput) {
		String sOutput = "";
		try {
			if (sInput != null)
				sOutput = sInput.toLowerCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sOutput;
	}

	public static String convertUnicode(String str) {
		if (str == null)
			return null;
		if (str == "")
			return str;
		try {
			str = new String(str.getBytes("8859_1"), "UTF8");
			//str = new String(str.getBytes("10646_1"), "UTF8");
		} catch (Exception e) {
		}
		return str;
	}

	// Su dung khi len Web ko convert
	public static String convertUnicode1(String str) {
		if (str == null)
			return null;
		if (str == "")
			return str;
		try {
			str = new String(str.getBytes("8859_1"), "UTF8");
		} catch (Exception e) {
		}
		return str;
	}

	// Su dung khi len Web convert cho cac trang mrequest
	public static String convertUnicode2(String str) {
		if (str == null)
			return null;
		if (str == "")
			return str;
		try {
			str = new String(str.getBytes("8859_1"), "UTF8");
		} catch (Exception e) {
		}
		return str;
	}

	public static String Thang(String thang) {
		if (thang.equals("Jan"))
			return "01";
		if (thang.equals("Feb"))
			return "02";
		if (thang.equals("Mar"))
			return "03";
		if (thang.equals("Apr"))
			return "04";
		if (thang.equals("May"))
			return "05";
		if (thang.equals("Jun"))
			return "06";
		if (thang.equals("Jul"))
			return "07";
		if (thang.equals("Aug"))
			return "08";
		if (thang.equals("Sep"))
			return "09";
		if (thang.equals("Oct"))
			return "10";
		if (thang.equals("Nov"))
			return "11";
		if (thang.equals("Dec"))
			return "12";
		else
			return "00";
	}

	public static int formatNgaySo(String today) {
		String nam = null;
		String thang = null;
		String ngay = null;
		String arr[] = new String[6];
		today = today.trim();
		arr = today.split(" ");
		ngay = arr[2];
		thang = Thang(arr[1]);
		nam = arr[5];
		String namthangngay = nam + thang + ngay;
		return Integer.parseInt(namthangngay);
	}

	public static String formatNgayThangNam(String today) {
		String nam = null;
		String thang = null;
		String ngay = null;
		String arr[] = new String[6];
		today = today.trim();
		arr = today.split(" ");
		ngay = arr[2];
		thang = Thang(arr[1]);
		nam = arr[5];
		String namthangngay = ngay + "-" + thang + "-" + nam;
		return namthangngay;
	}

	public static String replaceContent(String str) {
		if (str == null)
			return null;
		if (str == "")
			return str;
		try {
			str = str.replaceAll("\"", "");
			str = str.replaceAll("\r\n", "<br>");
			str = str.replaceAll("\n", "");
			str = str.replaceAll("\"", "&quot;");
		} catch (Exception e) {
		}
		return str;
	}

	public static String formatNgay1(String today) {
		String namthangngay = "";
		if (today != null && !today.equals("") && !today.equals("null")) {
			String nam = null;
			String thang = null;
			String ngay = null;
			String arr[] = new String[3];
			today = today.trim();
			arr = today.split("-");
			if (arr.length > 0) {
				nam = arr[0];
				thang = arr[1];
				ngay = arr[2];
				namthangngay = ngay + "-" + thang + "-" + nam;
			}
		}
		return namthangngay;
	}

	public static String formatNgay(String today) {
		String nam = null;
		String thang = null;
		String ngay = null;
		String arr[] = new String[6];
		today = today.trim();
		arr = today.split(" ");
		ngay = arr[2];
		thang = Thang(arr[1]);
		nam = arr[5];
		String namthangngay = nam + "-" + thang + "-" + ngay;
		return namthangngay;
	}

	public static int compare2Date(String dateA, String dateB) {
		// String dateA = â€œ02/25/2005ï¿½?; // February 25th, 2005
		// String dateB = â€œ27-05-2004ï¿½?; // 27th of May, 2004

		SimpleDateFormat sdfA = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdfB = new SimpleDateFormat("dd-MM-yyyy");

		java.util.Date dtA = sdfA.parse(dateA, new ParsePosition(0));
		java.util.Date dtB = sdfB.parse(dateB, new ParsePosition(0));

		if (dtA.compareTo(dtB) <= 0) {
			return 0; // date A is before B
		} else if (dtA.compareTo(dtB) > 0) {
			return 1; // date B is before A
		} else {
			return 2; // date A and B are the same
		}

	}

	public static int compare2Date2011(String dateA, String dateB) {
		// String dateA = â€œ02/25/2005ï¿½?; // February 25th, 2005
		// String dateB = â€œ27-05-2004ï¿½?; // 27th of May, 2004
		dateA = formatNgayThangNam_(dateA);
		dateB = formatNgayThangNam_(dateB);

		SimpleDateFormat sdfA = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdfB = new SimpleDateFormat("dd-MM-yyyy");

		java.util.Date dtA = sdfA.parse(dateA, new ParsePosition(0));
		java.util.Date dtB = sdfB.parse(dateB, new ParsePosition(0));

		if (dtA.compareTo(dtB) <= 0) {
			return 0; // date A is before B
		} else if (dtA.compareTo(dtB) > 0) {
			return 1; // date B is before A
		} else {
			return 2; // date A and B are the same
		}

	}

	public static int getCompare2Date(String dateA, String dateB) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calendarA = Calendar.getInstance();
		Calendar calendarB = Calendar.getInstance();
		Date dtA = null;
		Date dtB = null;

		try {
			dtA = format.parse(dateA);
			// calendarA.DATE(dtA);
			dtB = format.parse(dateB);
			calendarB.setTime(dtB);
			// calendarA.add(Calendar.DATE, days);
			System.out.print("Son ");
			System.out.print(calendarA.DATE);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return 2;

		// return dtA.getTime()-dtB.getTime();

	}

	public static String AddDate(String strDate, int days) {
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

		Calendar calendar = Calendar.getInstance();
		try {
			Date dt = format.parse(strDate);
			calendar.setTime(dt);
			calendar.add(Calendar.DATE, days);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return format.format(calendar.getTime());
	}

	public static String AddYear(String strDate, int years) {
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

		Calendar calendar = Calendar.getInstance();
		try {
			Date dt = format.parse(strDate);
			calendar.setTime(dt);
			calendar.add(Calendar.YEAR, years);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return format.format(calendar.getTime());
	}

	public static String AddMonth(String strDate, int month) {
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

		Calendar calendar = Calendar.getInstance();
		try {
			Date dt = format.parse(strDate);
			calendar.setTime(dt);
			calendar.add(Calendar.MONTH, month);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return format.format(calendar.getTime());
	}

	public static String NumberToString(String sNumber) {
		sNumber = sNumber.trim();
		String sReturn = "";
		if (sNumber.indexOf(".") <= 0) {
			int iLen = sNumber.length();
			String sNumber1 = "";
			for (int i = iLen - 1; i >= 0; i--) {
				sNumber1 = sNumber1 + sNumber.charAt(i);
			}

			int iRe = 0;
			String sCut;
			do {
				sCut = "";
				if (iLen <= 3) {
					break;
				}
				sCut = sNumber1.substring(iRe * 3, iRe * 3 + 3);
				if (sCut != ",") {
					sReturn = Read(sCut, iRe) + sReturn;
				}
				iRe++;
				iLen -= 3;
			} while (true);
			sCut = sNumber1.substring(iRe * 3, iRe * 3 + iLen);
			if (sCut != ",") {
				sReturn = Read(sCut, iRe) + sReturn;
			}
		}
		return sReturn;
	}

	public static String Read(String sNumber, int iPo) {
		String sReturn = "";
		String sPo[] = { "", "ng\340n ", "tri\u1EC7u ", "t\u1EF7 " };
		String sSo[] = { "kh\364ng ", "m\u1ED9t ", "hai ", "ba ", "b\u1ED1n ",
				"n\u0103m ", "s\341u ", "b\u1EA3y ", "t\341m ", "ch\355n " };
		String sDonvi[] = { "", "m\u01B0\u01A1i ", "tr\u0103m " };
		int iLen = sNumber.length();
		int iRe = 0;
		for (int i = 0; i < iLen; i++) {
			String sRead;
			label0: {
				String sTemp = "" + sNumber.charAt(i);
				int iTemp = Integer.parseInt(sTemp);
				sRead = "";
				if (iTemp == 0) {
					switch (iRe) {
					case 1: // '\001'
						if (Integer.parseInt("" + sNumber.charAt(0)) != 0) {
							sRead = "l\u1EBB ";
						}
						break;

					case 2: // '\002'
						if (Integer.parseInt("" + sNumber.charAt(0)) != 0
								&& Integer.parseInt("" + sNumber.charAt(1)) != 0) {
							sRead = "kh\364ng tr\u0103m ";
						}
						break;
					}
					break label0;
				}
				if (iTemp == 1) {
					switch (iRe) {
					case 1: // '\001'
						sRead = "m\u01B0\u01A1i ";
						break;

					default:
						sRead = "m\u1ED9t " + sDonvi[iRe];
						break;
					}
					break label0;
				}
				if (iTemp == 5) {
					switch (iRe) {
					case 0: // '\0'
						try {
							if (Integer.parseInt("" + sNumber.charAt(1)) != 0) {
								sRead = "l\u0103m ";
							} else {
								sRead = "n\u0103m ";
							}
							break label0;
						} catch (Exception ex) {
						}

						// fall through

					default:
						sRead = sSo[iTemp] + sDonvi[iRe];
						break;
					}
				} else {
					sRead = sSo[iTemp] + sDonvi[iRe];
				}
			}
			sReturn = sRead + sReturn;
			iRe++;
		}

		if (sReturn.length() > 0) {
			sReturn = sReturn + sPo[iPo];
		}
		return sReturn;
	}

	public static String BoDauNgoacSoAm(String Number) {

		if (Number != null && !Number.equals("")) {
			if (String.valueOf(Number.charAt(0)).equals("(")) {

				Number = "-" + Number.substring(1, Number.length() - 1);
			}
		}

		return Number;
	}

	public static double formatNumStringtoDouble(String sInput) {
		double fInput = 0;
		try {

			sInput = BoDauNgoacSoAm(sInput);
			sInput = sInput.replaceAll("\\.", "");

			// Anh viet
			if (sInput.indexOf("(") > 0) {
				sInput = sInput.replaceAll("\\)", "");
				sInput = sInput.replaceAll("\\(", "");
				sInput = "-" + sInput;
			}
			// ket thuc

			if (sInput != null && !sInput.equals("")) {
				fInput = Double.parseDouble(sInput);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return fInput;
	}

	public static long formatNumStringtoLong(String sInput) {
		long dInput = 0;
		try {

			sInput = BoDauNgoacSoAm(sInput);
			sInput = sInput.replaceAll("\\,", "");

			// Anh viet
			if (sInput.indexOf("(") > 0) {
				sInput = sInput.replaceAll("\\)", "");
				sInput = sInput.replaceAll("\\(", "");
				sInput = "-" + sInput;
			}
			// ket thuc

			if (sInput != null && !sInput.equals("")) {
				dInput = Long.parseLong(sInput);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return dInput;
	}


	public String getDir(String dir, String name) {
		if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}
		File mv = new File(name);
		String new_dir = null;
		if (!mv.isAbsolute()) {
			new_dir = dir + name;
		} else {
			new_dir = name;
		}
		return new_dir;
	}

	public boolean isAllowed(File path, boolean write) throws IOException {

		boolean READ_ONLY1 = false;
		if (READ_ONLY1 && write) {
			return false;
		}
		if (RESTRICT_BROWSING) {
			StringTokenizer stk = new StringTokenizer(RESTRICT_PATH, ";");
			while (stk.hasMoreTokens()) {
				if (path != null
						&& path.getCanonicalPath().startsWith(stk.nextToken())) {
					return RESTRICT_WHITELIST;
				}
			}
			return !RESTRICT_WHITELIST;
		} else {
			return true;
		}
	}

	// Create Directory
	public int CreateFolder(String sFolderName, String sPath)
			throws IOException {
		String dir = sPath;
		String dir_name = sFolderName;
		String new_dir = getDir(dir, dir_name);
		if (!isAllowed(new File(new_dir), true)) {
			return 1;
		} else if (new File(new_dir).mkdirs()) {
			return 0;
		} else {
			return 2;
		}
	}

	public String getNamThangNgay_File() {
		Calendar cal = Calendar.getInstance();

		int day = cal.get(Calendar.DATE);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		return String.valueOf(year) + "-" + String.valueOf(month) + "-"
				+ String.valueOf(day);
	}

	public String encodeUrl(String ValueUrl) {
		if (ValueUrl != null && !ValueUrl.equals("")) {
			return ValueUrl.replaceAll(" ", "+");
			// return java.net.URLEncoder.(ValueUrl);
		}
		return ValueUrl;
	}

	public String decodeUrl(String ValueUrl) {
		if (ValueUrl != null && !ValueUrl.equals("")) {
			return java.net.URLDecoder.decode(ValueUrl);
		}
		return ValueUrl;
	}

	public String getNgay_Cur() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		return getHaiSoInt(day);
	}

	public String getThang_Cur() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		return getHaiSoInt(month);
	}

	public String getNam_Cur() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return String.valueOf(year);
	}

	public String getGio_Cur() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		return getHaiSoInt(hour);
	}

	public String getPhut_Cur() {
		Calendar cal = Calendar.getInstance();
		int minute = cal.get(Calendar.MINUTE);
		return getHaiSoInt(minute);
	}

	public String getGiay_Cur() {
		Calendar cal = Calendar.getInstance();
		int minute = cal.get(Calendar.SECOND);
		return getHaiSoInt(minute);
	}
	
	public String getMiliGiay_Cur() {
		Calendar cal = Calendar.getInstance();
		int minute = cal.get(Calendar.MILLISECOND);
		return getHaiSoInt(minute);
	}
	
	public String getNamThangNgay_Cur() {
		return getNam_Cur() + "-" + getThang_Cur() + "-" + getNgay_Cur();
	}
	

	public String getDateCurFull() {
		Format format = new SimpleDateFormat("ddMMyy");
		String xref = format.format(new Date()) +"-"+ getGio_Cur() + getPhut_Cur() + getGiay_Cur() +"-"+ getMiliGiay_Cur();
		return xref;
	}
	
	
	
	public String getDay_Of_Weeks() {
		Calendar cal = Calendar.getInstance();
		int d = cal.get(Calendar.DAY_OF_WEEK);
		String Date_Of_Weeks = "";
		switch (d) {
		case 1:
			Date_Of_Weeks = "Chá»§ Nháº­t";
			break;
		case 2:
			Date_Of_Weeks = "Thá»© Hai";
			break;
		case 3:
			Date_Of_Weeks = "Thá»© Ba";
			break;
		case 4:
			Date_Of_Weeks = "Thá»© TÆ°";
			break;
		case 5:
			Date_Of_Weeks = "Thá»© NÄƒm";
			break;
		case 6:
			Date_Of_Weeks = "Thá»© SÃ¡u";
			break;
		case 0:
			Date_Of_Weeks = "Thá»© Báº£y";
			break;
		}

		return Date_Of_Weeks;
	}

	/**
	 * function to read file unicode
	 */
	public String readFileUnicode(String sFileName, String sPath) {
		String sFileLocation = "";
		String sLine = "";
		String sFileContent = "";
		sFileLocation = sPath;
		sFileLocation += sFileName;

		try {
			FileInputStream fis = new FileInputStream(sFileLocation);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					fis, "UTF-8"));
			while ((sLine = reader.readLine()) != null) {
				sFileContent += sLine;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sFileContent;
	}

	public String formatdate(String date) {
		if (!date.equals("")) {
			String[] Arr = date.split("/");
			date = Arr[0] + "/" + getHaiSo(Arr[1]) + "/" + getHaiSo(Arr[2]);
		}

		return date;
	}

	public String formatdate_(String date) {
		if (!date.equals("")) {
			String[] Arr = date.split("-");
			date = Arr[2] + "/" + getHaiSo(Arr[1]) + "/" + getHaiSo(Arr[0]);
		}

		return date;
	}

	public String formatdate__(String date) {
		if (!date.equals("")) {
			String[] Arr = date.split("-");
			date = Arr[2] + "-" + getHaiSo(Arr[1]) + "-" + getHaiSo(Arr[0]);
		}

		return date;
	}

	public ArrayList<String> ListNgay(String tungay, String denngay, int type,
			int limit) {
		ArrayList<String> rValue = new ArrayList<String>();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		int count = 0;

		Date ngayBD = new Date();
		Date ngayKT = new Date();

		if (tungay != null && denngay != null) {
			try {
				ngayBD = df.parse(tungay);
				ngayKT = df.parse(denngay);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String tungay_1 = this.formatdate(tungay);
		String denngay_1 = this.formatdate(denngay);
		if (Date.parse(tungay_1) > Date.parse(denngay_1)) {
			if (type == 1)
				rValue.add(GenFolderDate(tungay));
			else if (type == 2)
				rValue.add(tungay);
			else if (type == 3)
				rValue.add(GenDateSql(tungay));

			return rValue;
		}

		if (tungay.equals("") || denngay.equals("")) {
			return null;
		}

		String[] aTungay = tungay.split("/");
		String[] aDenngay = denngay.split("/");
		int ngayt = Integer.parseInt(aTungay[0]), ngayd = Integer
				.parseInt(aDenngay[0]), thangt = Integer.parseInt(aTungay[1]), thangd = Integer
				.parseInt(aDenngay[1]), namt = Integer.parseInt(aTungay[2]), namd = Integer
				.parseInt(aDenngay[2]);
		// start add value to date list
		boolean flag = true;
		if (tungay.equals(denngay)) {
			if (type == 1)
				rValue.add(GenFolderDate(tungay));
			else if (type == 2)
				rValue.add(tungay);
			else if (type == 3)
				rValue.add(GenDateSql(tungay));

			return rValue;
		} else {
			while (flag && (count < limit)) {
				String stn = (ngayt < 10 ? "0" + ngayt : "" + ngayt) + "/"
						+ (thangt < 10 ? "0" + thangt : "" + thangt) + "/"
						+ (namt + "");
				String sdn = (ngayd < 10 ? "0" + ngayd : "" + ngayd) + "/"
						+ (thangd < 10 ? "0" + thangd : "" + thangd) + "/"
						+ (namd + "");

				int quanday = ((thangt == 1 || thangt == 3 || thangt == 5
						|| thangt == 7 || thangt == 8 || thangt == 10 || thangt == 12) ? 31
						: ((thangt == 4 || thangt == 6 || thangt == 9 || thangt == 11) ? 30
								: ((thangt == 2 && (namt % 4) == 0) ? 29 : 28)));

				// Create data search
				if (type == 1)
					rValue.add(GenFolderDate(stn));
				else if (type == 2)
					rValue.add(stn);
				else if (type == 3)
					rValue.add(GenDateSql(stn));
				// End Create data search
				count++;

				if (!stn.equals(sdn)) {
					if (ngayt < quanday)
						ngayt++;
					else {
						ngayt = 1;
						if (thangt < 12)
							thangt++;
						else {
							thangt = 1;
							namt++;
						}
					}
				} else {
					flag = false;
				}
			}
		}

		return rValue;
	}

	public String getNamThangNgay_Folder() {
		Calendar cal = Calendar.getInstance();

		int day = cal.get(Calendar.DATE);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		return String.valueOf(year) + String.valueOf(this.getHaiSoInt(month))
				+ String.valueOf(this.getHaiSoInt(day));
	}

	public static String GenFolderDate(String date) {
		String folder = "";
		String[] aTungay = date.split("/");

		int ngayt = Integer.parseInt((aTungay[0]));
		int thangt = Integer.parseInt((aTungay[1]));
		int namt = Integer.parseInt((aTungay[2]));

		folder = (namt + "").substring(2, 4)
				+ (thangt < 10 ? "0" + thangt : "" + thangt)
				+ (ngayt < 10 ? "0" + ngayt : "" + ngayt);

		return folder;
	}

	public static String GenDateSql(String date) {
		String[] aTungay = date.split("/");

		int ngayt = Integer.parseInt((aTungay[0]));
		int thangt = Integer.parseInt((aTungay[1]));
		int namt = Integer.parseInt((aTungay[2]));

		date = (namt + "") + "-" + (thangt < 10 ? "0" + thangt : "" + thangt)
				+ "-" + (ngayt < 10 ? "0" + ngayt : "" + ngayt);

		return date;
	}

	public static String getHaiSoInt(int iNumber) {
		String sNumber = String.valueOf(iNumber);
		if (sNumber.length() < 2) {
			return "0" + sNumber;
		}
		return sNumber;
	}

	public String getHaiSo(String iNumber) {
		String sNumber = iNumber;
		if (sNumber.length() < 2) {
			return "0" + sNumber;
		}
		return sNumber;
	}

	public String dateFormatDMY(String date) {
		String rValue = "";

		if (date != null && date.length() > 0) {
			String fStr = date.split(" ").length > 1 ? date.split(" ")[0]
					: date;
			String[] arr = fStr.split("-");
			int ngayt = Integer.parseInt((arr[0]));
			int thangt = Integer.parseInt((arr[1]));
			int namt = Integer.parseInt((arr[2]));

			rValue = (ngayt < 10 ? "0" + ngayt : "" + ngayt) + "/"
					+ (thangt < 10 ? "0" + thangt : "" + thangt) + "/"
					+ (namt + "");
		}

		return rValue;
	}

	public String dateFormatDMY_T(String date) {
		String rValue = "";

		if (date != null && date.length() > 0) {
			String fStr = date.split(" ").length > 1 ? date.split(" ")[0]
					: date;
			String[] arr = fStr.split("-");
			int ngayt = Integer.parseInt((arr[2]));
			int thangt = Integer.parseInt((arr[1]));
			int namt = Integer.parseInt((arr[0]));

			rValue = (ngayt < 10 ? "0" + ngayt : "" + ngayt) + "/"
					+ (thangt < 10 ? "0" + thangt : "" + thangt) + "/"
					+ (namt + "");
		}

		return rValue;
	}

	// ---------------------------- Begin copy
	// File-------------------------------------
	public static int copyfile(String srFile, String dtFile) {
		int result = 0;
		try {
			File f1 = new File(srFile);
			File f2 = new File(dtFile);
			InputStream in = new FileInputStream(f1);

			// For Append the file.
			// OutputStream out = new FileOutputStream(f2,true);

			// For Overwrite the file.
			OutputStream out = new FileOutputStream(f2);

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
			System.out.println("File copied.");
			result = 1;
		} catch (FileNotFoundException ex) {
			System.out
					.println(ex.getMessage() + " in the specified directory.");
			// System.exit(0);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return result;

	}

	// Copy Files
	public int CopyFile(String[] vFiles, String sSourcePath, String sTargetPath)
			throws IOException {
		int icheck = 0;
		Vector v = expandFileList(vFiles, true);
		String dir = sSourcePath;

		if (!dir.endsWith(File.separator)) {
			dir += File.separator;
		}
		String dir_name = sTargetPath;
		String new_dir = getDir(dir, dir_name);
		if (!isAllowed(new File(new_dir), true)) {
			icheck = 1; // You are not allowed to access

		} else {
			boolean error = false;
			if (!new_dir.endsWith(File.separator)) {
				new_dir += File.separator;
			}
			try {
				// byte buffer[] = new byte[0xffff];
				byte buffer[] = new byte[30 * 1024];
				for (int i = 0; i < v.size(); i++) {
					log.log("vao day:" + buffer);
					File f_old = (File) v.get(i);
					log.log("old" + f_old.toString());
					log.log("file new "
							+ f_old.getAbsolutePath().substring(dir.length()));
					File f_new = new File(new_dir + f_old.getName());
					log.log(f_new.toString());
					if (!isAllowed(f_old, false) || !isAllowed(f_new, true)) {
						icheck = 2; // You are not allowed to access
						error = true;
					} else if (f_old.isDirectory()) {
						f_new.mkdirs();
					}
					// Overwriting is forbidden
					else if (!f_new.exists()) {
						copyStreams(new FileInputStream(f_old),
								new FileOutputStream(f_new), buffer);
					} else {
						// File exists
						icheck = 3; // file already exists. Copying aborted

						error = true;
						break;
					}
				}
			} catch (IOException e) {
				icheck = 4; // Error.Copying aborted
				log.log(e.toString());
				error = true;
			}
			if ((!error) && (v.size() > 1)) {
				icheck = 0; // All files copied

			} else if ((!error) && (v.size() > 0)) {
				icheck = 0; // File copied

			} else if (!error) {
				icheck = 5; // No files selected

			}
		}
		return icheck;
	}

	static Vector expandFileList(String[] files, boolean inclDirs) {
		Vector v = new Vector();
		if (files == null) {
			return v;
		}
		for (int i = 0; i < files.length; i++) {
			// v.add(new File(URLDecoder.decode(files[i])));
			v.add(new File(files[i]));
		}
		for (int i = 0; i < v.size(); i++) {
			File f = (File) v.get(i);
			if (f.isDirectory()) {
				File[] fs = f.listFiles();
				for (int n = 0; n < fs.length; n++) {
					v.add(fs[n]);
				}
				if (!inclDirs) {
					v.remove(i);
					i--;
				}
			}
		}
		return v;
	}

	static void copyStreams(InputStream in, OutputStream out, byte[] buffer)
			throws IOException {
		copyStreamsWithoutClose(in, out, buffer);
		in.close();
		out.close();
	}

	static void copyStreamsWithoutClose(InputStream in, OutputStream out,
			byte[] buffer) throws IOException {
		int b;
		while ((b = in.read(buffer)) != -1) {
			out.write(buffer, 0, b);
		}
	}

	public static int checkExitsFile(String path) {
		int itrue = 0;
		try {
			File aFile = new File(path);
			if (aFile.exists()) {
				itrue = 1;
			}
		} catch (Exception e) {
			itrue = 0;
		}
		return itrue;
	}

	public int DeleteFiles(String sFiles) throws IOException {

		// Delete Files
		int chkDelete = 0;
		String[] aFile = { sFiles };
		Vector v = expandFileList(aFile, true);
		boolean error = false;
		// delete backwards
		for (int i = v.size() - 1; i >= 0; i--) {
			File f = (File) v.get(i);
			if (!isAllowed(f, true)) {
				chkDelete = 1; // You are not allowed to access

				error = true;
				break;
			}
			if (!f.canWrite() || !f.delete()) {
				chkDelete = 2; // Cannot delete

				error = true;
				break;
			}
		}
		if ((!error) && (v.size() > 1)) {
			chkDelete = 0; // All files deleted
		} else if ((!error) && (v.size() > 0)) {
			chkDelete = 0; // File deleted
		} else if (!error) {
			chkDelete = 3; // No select file

		}
		return chkDelete;
	}

	// ---------------------------- end copy
	// File-------------------------------------

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

	public static String getContentUrl(String input) throws Exception {

		String Out = "";
		try {
			String thisLine;
			URL u = new URL(input);

			BufferedReader theHTML = new BufferedReader(new InputStreamReader(
					u.openStream(), "UTF-8"));

			while ((thisLine = theHTML.readLine()) != null) {
				Out += thisLine;
				// System.out.println(thisLine);
			} // while loop ends here
			theHTML.close();
		}

		catch (MalformedURLException e) {
			System.err.println(e);
		} catch (IOException e) {

			System.err.println(e);
		}

		return Out;

	}


	public static String formatNgayThangNam__(String today) {
		String ngaythangnam = "";
		if (today != null && !today.equals("") && !today.equals("null")) {
			String nam = null;
			String thang = null;
			String ngay = null;
			String arr[] = new String[3];
			today = today.trim();
			arr = today.split("/");
			if (arr.length > 0) {
				nam = arr[0];
				thang = arr[1];
				ngay = arr[2];
				ngaythangnam = ngay + "-" + thang + "-" + nam;
			}
		}
		return ngaythangnam;
	}

	public static String formatNgayThangNam2011(String today) {
		String ngaythangnam = "";
		if (today != null && !today.equals("") && !today.equals("null")
				&& !today.equals("0001/01/01") && !today.equals("01/01/0001")) {
			String nam = null;
			String thang = null;
			String ngay = null;
			String arr[] = new String[3];
			today = today.trim();
			arr = today.split("/");
			if (arr.length > 0) {
				nam = arr[0];
				thang = arr[1];
				ngay = arr[2];
				ngaythangnam = ngay + "/" + thang + "/" + nam;
			}
		}
		return ngaythangnam;
	}

	public static String formatNgayThangNam_(String today) {
		String ngaythangnam = "";
		try {

			if (today != null && !today.equals("") && !today.equals("null")) {
				String nam = null;
				String thang = null;
				String ngay = null;
				String arr[] = new String[3];
				today = today.trim();
				arr = today.split("-");
				if (arr.length > 0) {
					nam = arr[0];
					thang = arr[1];
					ngay = arr[2];
					ngaythangnam = ngay + "-" + thang + "-" + nam;
				}
			}
		} catch (Exception e) {
			// System.out.print(e.toString()+today);
			ngaythangnam = "";
		}
		return ngaythangnam;
	}

	public static String formatFullDateTime_1(String datetime) {
		String hhmmss = null;
		String namthangngay = null;
		String fullDateTime = null;
		String ngaythangnam = null;

		if (datetime != null && !datetime.equals("")
				&& datetime.trim().length() == 10) {

			namthangngay = formatNgaySo4(datetime);
			fullDateTime = namthangngay;

		}
		if (datetime != null && !datetime.equals("")
				&& datetime.trim().length() > 10) {

			String arr[] = new String[6];
			datetime = datetime.trim();
			arr = datetime.split(" ");

			ngaythangnam = arr[0];
			hhmmss = arr[1];

			namthangngay = formatNgaySo4(ngaythangnam);
			fullDateTime = namthangngay + " " + hhmmss;
		}

		return fullDateTime;
	}

	public static String formatNgaySo4_(String today) {
		String namthangngay = "";
		if (!today.equals("") && !today.equals("null") && today != null) {
			String nam = null;
			String thang = null;
			String ngay = null;
			String arr[] = new String[3];
			today = today.trim();
			arr = today.split("-");
			if (arr.length > 0) {
				nam = arr[2];
				thang = arr[1];
				ngay = arr[0];
				namthangngay = nam + "/" + thang + "/" + ngay;
			}
		}
		return namthangngay;
	}

	public static String formatNgaySo4(String today) {
		String namthangngay = "";
		if (!today.equals("") && !today.equals("null") && today != null) {
			String nam = null;
			String thang = null;
			String ngay = null;
			String arr[] = new String[3];
			today = today.trim();
			arr = today.split("-");
			if (arr.length > 0) {
				nam = arr[2];
				thang = arr[1];
				ngay = arr[0];
				namthangngay = nam + "-" + thang + "-" + ngay;
			}
		}
		return namthangngay;
	}

	public static String formatFullDateTime_2(String datetime) {
		String hhmmss = null;
		String namthangngay = null;
		String fullDateTime = null;
		String ngaythangnam = null;

		if (datetime != null && !datetime.equals("")
				&& datetime.trim().length() == 10) {

			namthangngay = formatNgaySo5(datetime);
			fullDateTime = namthangngay;

		}
		if (datetime != null && !datetime.equals("")
				&& datetime.trim().length() > 10) {

			String arr[] = new String[6];
			String arr_[] = new String[3];
			datetime = datetime.trim();
			arr = datetime.split(" ");

			ngaythangnam = arr[0];
			hhmmss = arr[1];
			arr_ = hhmmss.split(":");

			namthangngay = formatNgaySo5(ngaythangnam);
			fullDateTime = namthangngay + " " + arr_[0] + ":" + arr_[1];
		}

		return fullDateTime;
	}

	public static String formatNgaySo5(String today) {
		String namthangngay = "";
		if (!today.equals("") && !today.equals("null") && today != null) {
			String nam = null;
			String thang = null;
			String ngay = null;
			String arr[] = new String[3];
			today = today.trim();
			arr = today.split("-");
			if (arr.length > 0) {
				nam = arr[2];
				thang = arr[1];
				ngay = arr[0];
				namthangngay = nam + "-" + thang + "-" + ngay;
			}
		}
		return namthangngay;
	}

	public String Phantrang(int record, int sotrang, int totalRows, int curPage) {

		String Paging = "";
		int totalPages = 0;
		// khai bao so tin hien thi tren mot trang
		int maxRows = record;

		// khai bao so phan doan
		int maxPages = sotrang;
		// So trang hien co

		if (totalRows % maxRows == 0)
			totalPages = (int) (totalRows / maxRows);
		else
			totalPages = (int) (totalRows / maxRows + 1);

		// khai bao bien bat dau tu thu i
		int curRow = (curPage - 1) * maxRows + 1;
		// String Paging
		Paging = "Current Page :&nbsp;<font color=red>" + curPage
				+ "</font>&nbsp;&nbsp;&nbsp;"
				+ "Total pages :&nbsp;<FONT color=red>" + totalPages
				+ "</FONT><br> ";
		// chi tao ra mot paging(1 2 3 4 next) khi tong so hang lon hon moi
		// trang
		if (totalRows > maxRows) {
			// khai bï¿½o bien bat dau va ket thuc 1->5, 6->10 ...
			int start = 1;
			int end = 1;
			String paging1 = "";
			// Dinh Nghia paging (1 2 3 4 5)
			for (int i = 1; i <= totalPages; i++) {
				if ((i > ((int) ((curPage - 1) / maxPages)) * maxPages)
						&& (i <= ((int) ((curPage - 1) / maxPages + 1))
								* maxPages)) {
					if (start == 1)
						start = i;
					if (i == curPage)
						paging1 += String.valueOf(i) + "&nbsp;&nbsp;";
					else
						// khai bï¿½o javascript goto page
						paging1 += "<a href='javascript:GotoPage("
								+ String.valueOf(i) + ")'>" + String.valueOf(i)
								+ "</a>&nbsp;&nbsp;";
					end = i;
				}
			}
			// khai bï¿½o Previous >1
			Paging += "Go to page :&nbsp;&nbsp;";
			if (curPage > maxPages)
				Paging += "<a href='javascript:GotoPage("
						+ String.valueOf(start - 1)
						+ ")'>Previous</a>&nbsp;&nbsp;";
			Paging += paging1;
			// khai bï¿½o next
			if ((((curPage - 1) / maxPages + 1) * maxPages) < totalPages)
				Paging += "<a href='javascript:GotoPage("
						+ String.valueOf(end + 1) + ")'>Next</a>&nbsp;&nbsp;";
		}
		return Paging;
	}

	public String Phantrang_(int record, int sotrang, int totalRows, int curPage) {

		String Paging = "";
		int totalPages = 0;
		// khai bao so tin hien thi tren mot trang
		int maxRows = record;

		// khai bao so phan doan
		int maxPages = sotrang;
		// So trang hien co

		if (totalRows % maxRows == 0)
			totalPages = (int) (totalRows / maxRows);
		else
			totalPages = (int) (totalRows / maxRows + 1);

		// khai bao bien bat dau tu thu i
		int curRow = (curPage - 1) * maxRows + 1;
		// String Paging
		// Paging
		// ="Current Page :&nbsp;<font color=red>"+curPage+"</font>&nbsp;&nbsp;&nbsp;"+
		// "Total pages :&nbsp;<FONT color=red>"+totalPages+"</FONT><br> ";
		// chi tao ra mot paging(1 2 3 4 next) khi tong so hang lon hon moi
		// trang
		if (totalRows > maxRows) {
			// khai bï¿½o bien bat dau va ket thuc 1->5, 6->10 ...
			int start = 1;
			int end = 1;
			String paging1 = "";
			// Dinh Nghia paging (1 2 3 4 5)
			for (int i = 1; i <= totalPages; i++) {
				if ((i > ((int) ((curPage - 1) / maxPages)) * maxPages)
						&& (i <= ((int) ((curPage - 1) / maxPages + 1))
								* maxPages)) {
					if (start == 1)
						start = i;
					if (i == curPage)
						paging1 += "<span class='phantrang' id='cur' >"
								+ String.valueOf(i) + "</span>";
					else
						// khai bï¿½o javascript goto page
						paging1 += "<a  class='phantrang'  href='javascript:GotoPage("
								+ String.valueOf(i)
								+ ")'>"
								+ String.valueOf(i)
								+ "</a>";
					end = i;
				}
			}
			// khai bï¿½o Previous >1
			Paging += "Trang :&nbsp;";
			if (curPage > maxPages)
				Paging += "<a class='phantrang' href='javascript:GotoPage("
						+ String.valueOf(start - 1) + ")'>Â»</a>";
			Paging += paging1;
			// khai bï¿½o next
			if ((((curPage - 1) / maxPages + 1) * maxPages) < totalPages)
				Paging += "<a  class='phantrang'  href='javascript:GotoPage("
						+ String.valueOf(end + 1) + ")'>Â»</a>";
		}

		return Paging;
	}

	public String Phantrang_giaidap(int record, int sotrang, int totalRows,
			int curPage) {

		String Paging = "";
		int totalPages = 0;
		// khai bao so tin hien thi tren mot trang
		int maxRows = record;

		// khai bao so phan doan
		int maxPages = sotrang;
		// So trang hien co

		if (totalRows % maxRows == 0)
			totalPages = (int) (totalRows / maxRows);
		else
			totalPages = (int) (totalRows / maxRows + 1);

		// khai bao bien bat dau tu thu i
		int curRow = (curPage - 1) * maxRows + 1;
		// String Paging
		// Paging
		// ="Current Page :&nbsp;<font color=red>"+curPage+"</font>&nbsp;&nbsp;&nbsp;"+
		// "Total pages :&nbsp;<FONT color=red>"+totalPages+"</FONT><br> ";
		// chi tao ra mot paging(1 2 3 4 next) khi tong so hang lon hon moi
		// trang
		if (totalRows > maxRows) {
			// khai bï¿½o bien bat dau va ket thuc 1->5, 6->10 ...
			int start = 1;
			int end = 1;
			String paging1 = "";
			// Dinh Nghia paging (1 2 3 4 5)
			for (int i = 1; i <= totalPages; i++) {
				if ((i > ((int) ((curPage - 1) / maxPages)) * maxPages)
						&& (i <= ((int) ((curPage - 1) / maxPages + 1))
								* maxPages)) {
					if (start == 1)
						start = i;
					if (i == curPage)
						paging1 += "<span class='phantrang' id='cur' >"
								+ String.valueOf(i) + "</span>";
					else
						// khai bï¿½o javascript goto page
						paging1 += "<a  class='phantrang'  href='javascript:GotoPage("
								+ String.valueOf(i)
								+ ")'>"
								+ String.valueOf(i)
								+ "</a>";
					end = i;
				}
			}
			// khai bï¿½o Previous >1
			Paging += "Trang :&nbsp;";
			if (curPage > maxPages)
				Paging += "<a class='phantrang' href='javascript:GotoPage("
						+ String.valueOf(start - 1) + ")'>Â»</a>";
			Paging += paging1;
			// khai bï¿½o next
			if ((((curPage - 1) / maxPages + 1) * maxPages) < totalPages)
				Paging += "<a  class='phantrang'  href='javascript:GotoPage("
						+ String.valueOf(end + 1) + ")'>Â»</a>";
		}

		return Paging;
	}

	public static String formatNum_No(double Input) {

		String dInput = formatNum_Grid(Input);
		dInput = dInput.replaceAll(".", "");
		return dInput;
	}

	/**
	 * format full datetime (yyyy-mm-dd hh:mm:ss) --> (dd/mm/yyyy)
	 */
	public String formatDateTime_1(String datetime) {
		String hhmmss = null;
		String namthangngay = null;
		String dateTime = null;
		String ngaythangnam = null;
		String arr[] = new String[6];
		datetime = datetime.trim();
		arr = datetime.split(" ");

		namthangngay = arr[0];
		hhmmss = arr[1];

		ngaythangnam = formatNgaySo5(namthangngay);

		dateTime = ngaythangnam;

		return dateTime;
	}


	public static String formatNum_Grid(double dInput) {
		NumberFormat df1 = NumberFormat.getInstance(Locale.GERMAN);
		// DecimalFormat df1 = new DecimalFormat("#,###.###");
		String returnValue = df1.format(dInput);
		return returnValue;

	}

	/**
	 * UNZIP FILES.....
	 * 
	 * @param pathZipFile
	 * @param pathUnZipFile
	 * @return
	 * @throws ZipException
	 * @throws IOException
	 */
	public int unzip(String pathZipFile, String pathUnZipFile)
			throws ZipException, IOException {

		int result = 0;

		try {
			System.out.println("Unzipping file... at: " + pathZipFile);

			int BUFFER = 2048;
			File file = new File(pathZipFile);

			ZipFile zip = new ZipFile(file);

			String newPath = pathUnZipFile;

			new File(newPath).mkdir();
			Enumeration zipFileEntries = zip.entries();

			// Process each entry
			while (zipFileEntries.hasMoreElements()) {
				// grab a zip file entry
				ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();

				String currentEntry = entry.getName();

				File destFile = new File(newPath, currentEntry);

				destFile = new File(newPath, destFile.getName());
				File destinationParent = destFile.getParentFile();

				// create the parent directory structure if needed
				destinationParent.mkdirs();
				if (!entry.isDirectory()) {
					BufferedInputStream is = new BufferedInputStream(
							zip.getInputStream(entry));
					int currentByte;
					// establish buffer for writing file
					byte data[] = new byte[BUFFER];

					// write the current file to disk
					FileOutputStream fos = new FileOutputStream(destFile);
					BufferedOutputStream dest = new BufferedOutputStream(fos,
							BUFFER);

					// read and write until last byte is encountered
					while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
						dest.write(data, 0, currentByte);
					}
					dest.flush();
					dest.close();
					is.close();

				}
				if (currentEntry.endsWith(".zip")) {
					// found a zip file, try to open
					unzip(destFile.getAbsolutePath(), newPath);
				}
			}

			result = 1; // unzip success!
			zip.close();

		} catch (ZipException e) {
			result = -1; // unzip fail!
			e.printStackTrace();
		} catch (IOException e) {
			result = -2; // .zip file cannot found!
			e.printStackTrace();
		}

		return result;
	}


	public static String str_db_decode(String str) {
		if (str == null)
			return null;
		if (str == "")
			return str;
		try {
			str = str.trim();
			str = str.replaceAll("\\\\'", "'");
			// str= StringEscapeUtils.escapeHtml(str);
		} catch (Exception e) {
		}
		return str;
	}

	public static String getCurDate_HHMMSS(String today) {
		String nam = null;
		String thang = null;
		String ngay = null;
		String hhmmss = null;
		String arr[] = new String[6];
		today = today.trim();
		arr = today.split(" ");
		ngay = arr[2];
		thang = Thang(arr[1]);
		nam = arr[5];
		hhmmss = arr[3];
		String namthangngay = nam + thang + ngay + hhmmss;
		return namthangngay;
	}

	public SecretKey createKeyXHD() {

		String constantKey = "ts24/taxonline2010/enctxt";
		constantKey = constantKey + "~~~~~~~~~~~~~~~~~~~~~~~~";
		constantKey = constantKey.substring(0, 24);

		String original = new String(constantKey);
		String temp = "";

		byte[] defaultBytes = null;

		try {
			defaultBytes = original.getBytes("UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		/*
		 * key 24 bytes - tripleDes byte[] keyBytes = new byte[] { 83, 121, 115,
		 * 116, 101, 109, 46, 66, 121, 116, 101, 91, 93, 101, 50, 48, 49, 48,
		 * 47, 101, 110, 99, 116, 120 };
		 */

		byte[] keyBytes = new byte[] { 83, 121, 115, 116, 101, 109, 46, 66 };

		SecretKey key64 = new SecretKeySpec(keyBytes, "DES");

		return key64;

	}

	static public String byteToHex(byte b) {
		// Returns hex String representation of byte b
		char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 't', 's', '/',
				'x', 'o', 'n', 'l', 'i', 'n', '~' };
		char[] array = { hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f] };
		return new String(array);
	}

	static public String charToHex(char c) {
		// Returns hex String representation of char c
		byte hi = (byte) (c >>> 8);
		byte lo = (byte) (c & 0xff);
		return byteToHex(hi) + byteToHex(lo);
	}

	public static byte[] hexToBytes(char[] hex) {
		int length = hex.length / 2;
		byte[] raw = new byte[length];
		for (int i = 0; i < length; i++) {
			int high = Character.digit(hex[i * 2], 16);
			int low = Character.digit(hex[i * 2 + 1], 16);
			int value = (high << 4) | low;
			if (value > 127)
				value -= 256;
			raw[i] = (byte) value;
		}
		return raw;
	}

	public static byte[] hexToBytes(String hex) {
		return hexToBytes(hex.toCharArray());
	}

	public int getCurrentDate(String whatyouget) {
		Calendar cal = Calendar.getInstance();

		int day = cal.get(Calendar.DATE);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		int dow = cal.get(Calendar.DAY_OF_WEEK);
		int dom = cal.get(Calendar.DAY_OF_MONTH);
		int doy = cal.get(Calendar.DAY_OF_YEAR);
		int qua = 0;

		int ret = 0;
		if (whatyouget == "day") {
			ret = day;
		}
		if (whatyouget == "month") {
			ret = month;
		}
		if (whatyouget == "year") {
			ret = year;
		}
		if (whatyouget == "dow") {
			ret = dow;
		}

		if (whatyouget == "dom") {
			ret = dom;
		}
		if (whatyouget == "doy") {
			ret = doy;
		}
		if (whatyouget == "qoy") {
			if (month > 0 && month <= 3) {
				qua = 1;
			}
			if (month > 3 && month <= 6) {
				qua = 2;
			}
			if (month > 6 && month <= 9) {
				qua = 3;
			}
			if (month > 9 && month <= 12) {
				qua = 4;
			}
			log.log("Thang:" + month + "Quy: " + qua + " whatyouget: "
					+ whatyouget);
			ret = qua;
		}

		return ret;

	}


	public String GetServicesNew(String sMaDichVu) {

		if (sMaDichVu != null && sMaDichVu.equals("Service"))
			return "Service7";
		if (sMaDichVu != null && sMaDichVu.equals("Service2"))
			return "Service8";
		if (sMaDichVu != null && sMaDichVu.equals("Service3"))
			return "Service9";

		return sMaDichVu;
	}

	public int GetServicesNewViTri(int iViTri) {

		if (iViTri == 4 || iViTri == 5 || iViTri == 6)
			return iViTri + 3;

		return iViTri;
	}

	public double GetServicesPrice(String ArrPrice, int iNam) {

		String[] arr = ArrPrice.trim().split("-");
		if (arr.length > 2 && iNam > 0 && iNam < 4) {
			return Double.parseDouble(arr[iNam - 1].trim());
		}
		return 0;
	}

	public String getService(String sServiceName) {

		if (sServiceName == null || sServiceName.equals(""))
			return "";

		if (sServiceName.equals("Service"))
			return "A1";
		if (sServiceName.equals("Service2"))
			return "A2";
		if (sServiceName.equals("Service3"))
			return "A3";
		if (sServiceName.equals("Service7"))
			return "A1 (Má»›i)";
		if (sServiceName.equals("Service8"))
			return "A2 (Má»›i)";
		if (sServiceName.equals("Service9"))
			return "A3 (Má»›i)";

		if (sServiceName.equals("Service4"))
			return "A4";
		if (sServiceName.equals("Service5"))
			return "A5";
		if (sServiceName.equals("Service6"))
			return "Amax";

		return sServiceName;
	}

	public String getServiceLoai(String sServiceName) {

		if (sServiceName == null || sServiceName.equals(""))
			return "";

		if (sServiceName.equals("Service"))
			return "Advisor A1";
		if (sServiceName.equals("Service2"))
			return "Advisor A2";
		if (sServiceName.equals("Service3"))
			return "Advisor A3";
		if (sServiceName.equals("Service7"))
			return "Advisor A1";
		if (sServiceName.equals("Service8"))
			return "Advisor A2";
		if (sServiceName.equals("Service9"))
			return "Advisor A3";

		if (sServiceName.equals("Service4"))
			return "Advisor A4";
		if (sServiceName.equals("Service5"))
			return "Advisor A5";
		if (sServiceName.equals("Service6"))
			return "Advisor Amax";

		if (sServiceName.equals("B1"))
			return "Business B1";
		if (sServiceName.equals("B2"))
			return "Business B2";
		if (sServiceName.equals("B3"))
			return "Business B3";

		if (sServiceName.equals("B4"))
			return "Business B4";
		if (sServiceName.equals("B5"))
			return "Business B5";

		return sServiceName;
	}

	public String getServiceKeKhai(String sServiceName) {

		if (sServiceName == null || sServiceName.equals(""))
			return "";

		if (sServiceName.equals("Service"))
			return "(KÃª khai thuáº¿ cho 01 Doanh nghiá»‡p)";
		if (sServiceName.equals("Service2"))
			return "(KÃª khai thuáº¿ cho 02 Doanh nghiá»‡p)";
		if (sServiceName.equals("Service3"))
			return "(KÃª khai thuáº¿ cho 04 Doanh nghiá»‡p)";
		if (sServiceName.equals("Service7"))
			return "(KÃª khai thuáº¿ cho 01 Doanh nghiá»‡p)";
		if (sServiceName.equals("Service8"))
			return "(KÃª khai thuáº¿ cho 02 Doanh nghiá»‡p)";
		if (sServiceName.equals("Service9"))
			return "(KÃª khai thuáº¿ cho 04 Doanh nghiá»‡p)";

		if (sServiceName.equals("Service4"))
			return "(KÃª khai thuáº¿ cho 07 Doanh nghiá»‡p)";
		if (sServiceName.equals("Service5"))
			return "(KÃª khai thuáº¿ cho 10 Doanh nghiá»‡p)";
		if (sServiceName.equals("Service6"))
			return "(KÃª khai thuáº¿ cho 15 Doanh nghiá»‡p)";

		if (sServiceName.equals("B1"))
			return "(KÃª khai thuáº¿ cho 01 Doanh nghiá»‡p) Sá»‘ lÆ°á»£ng NV tá»‘i Ä‘a/Báº£ng lÆ°Æ¡ng khÃ´ng giá»›i háº¡n";
		if (sServiceName.equals("B2"))
			return "(KÃª khai thuáº¿ cho 01 Doanh nghiá»‡p) Sá»‘ lÆ°á»£ng NV tá»‘i Ä‘a/Báº£ng lÆ°Æ¡ng khÃ´ng giá»›i háº¡n";
		if (sServiceName.equals("B3"))
			return "(KÃª khai thuáº¿ cho 01 Doanh nghiá»‡p) Sá»‘ lÆ°á»£ng NV tá»‘i Ä‘a/Báº£ng lÆ°Æ¡ng khÃ´ng giá»›i háº¡n";
		if (sServiceName.equals("B4"))
			return "(KÃª khai thuáº¿ cho 01 Doanh nghiá»‡p) Sá»‘ lÆ°á»£ng NV tá»‘i Ä‘a/Báº£ng lÆ°Æ¡ng khÃ´ng giá»›i háº¡n";
		if (sServiceName.equals("B5"))
			return "(KÃª khai thuáº¿ cho 01 Doanh nghiá»‡p) Sá»‘ lÆ°á»£ng NV tá»‘i Ä‘a/Báº£ng lÆ°Æ¡ng khÃ´ng giá»›i háº¡n";

		if (sServiceName.equals("Business"))
			return "(KÃª khai thuáº¿ cho cÃ¡ nhÃ¢n)";
		if (sServiceName.equals("Deluxe"))
			return "(KÃª khai thuáº¿ cho cÃ¡ nhÃ¢n)";
		if (sServiceName.equals("Premier"))
			return "(KÃª khai thuáº¿ cho cÃ¡ nhÃ¢n)";

		return sServiceName;
	}

	public boolean CheckCharacterUser(String UserName) {
		Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
		Matcher matcher = pattern.matcher(UserName);
		return true;
	}

	// check input ma so thue
	public boolean checkMaSoThue(String value_mst) {

		try {
			if (value_mst.length() > 14)
				return false;
			String msttext = formatMaSoThue(value_mst);

			if (msttext == "0000000000" && !IsNumeric(msttext))

				return false;

			// }
			int kq = 0;
			kq = kq + Integer.parseInt(msttext.substring(0, 1)) * 31;
			kq = kq + Integer.parseInt(msttext.substring(1, 2)) * 29;
			kq = kq + Integer.parseInt(msttext.substring(2, 3)) * 23;
			kq = kq + Integer.parseInt(msttext.substring(3, 4)) * 19;
			kq = kq + Integer.parseInt(msttext.substring(4, 5)) * 17;
			kq = kq + Integer.parseInt(msttext.substring(5, 6)) * 13;
			kq = kq + Integer.parseInt(msttext.substring(6, 7)) * 7;
			kq = kq + Integer.parseInt(msttext.substring(7, 8)) * 5;
			kq = kq + Integer.parseInt(msttext.substring(8, 9)) * 3;

			if (Integer.parseInt(msttext.substring(9, 10)) == (10 - (kq % 11))) {
				// alert("Ma so thue khong hop le");
				// alert("MA SO THUE"+kq);
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	public static String formatMaSoThue(String number) {
		if (number.length() >= 10) {
			number = number.substring(0, 10);
		}
		return number;

	}

	public boolean IsNumeric(String sText)

	{
		try {
			String ValidChars = "0123456789.";
			boolean IsNumber = true;
			String Char;

			for (int i = 0; i < sText.length() && IsNumber == true; i++) {
				Char = String.valueOf(sText.charAt(i));
				if (ValidChars.indexOf(Char) == -1) {
					IsNumber = false;
				}
			}

			return IsNumber;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	public boolean checkExitsTax(ArrayList arr, String sTaxCode) {

		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i).toString().equals(sTaxCode))
				return true;
		}

		return false;
	}

	public boolean checkExitsTaxMaToKhai(ArrayList arr, ArrayList arrMaToKhai,
			String sTaxCode, String sMaToKhai) {

		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i).toString().equals(sTaxCode)) {
				if (arrMaToKhai.get(i).toString().equals(sMaToKhai)) {

					return true;
				}
			}

		}

		return false;
	}

	public String getThangQuyNam(String sCode) {

		if (sCode.contains("Q"))
			return "QuÃ½";
		if (sCode.length() == 4)
			return "NÄƒm";
		if (sCode.length() == 7)
			return "ThÃ¡ng";
		if (sCode.length() == 10)
			return "Sá»‘ láº§n phÃ¡t sinh";
		return sCode;

	}

	public String getNameSign(AcroFields af, String sCode) {
		String name = "";
		for (int p = 0; p < af.getSignatureNames().size(); p++) {

			name = (String) af.getSignatureNames().get(p);
			if (!name.contains(sCode))
				return name;

		}

		return "";
	}
	
	public static String formatyyyyMMdd_To_ddMMyyyy(String today) {
	    String namthangngay = "";
	    if (!today.equals("") && !today.equals("null") && today != null) {
	        String nam = null;
	        String thang = null;
	        String ngay = null;
	        String arr[] = new String[3];
	        today = today.trim();
	        arr = today.split("-");
	        if (arr.length > 0) {
	            nam = arr[0];
	            thang = arr[1];
	            ngay = arr[2];
	            namthangngay = ngay + "-" + thang + "-" + nam;
	        }
	    }
	    return namthangngay;
	} 
	
	
	// check input ma so thue add sub "-"
	public static String AddStringMaSoThue(String value_mst) {
		String sMSTTest = "";
		try {
			if (value_mst.length() > 10 && value_mst.length() < 14 ){
//				String msttext = formatMaSoThue(value_mst);
				String msttext = value_mst;
				String kq = "";
				kq = kq + (msttext.substring(0, 10));
				// add sub "-"
				sMSTTest = kq + "-" + Integer.parseInt(msttext.substring(10, 13));
				
			}else{
				sMSTTest = value_mst;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sMSTTest ;	
	}

	public String randomXref() {
		Format format = new SimpleDateFormat("ddMMyy");
		String xref = format.format(new Date()) +"-"+ getGio_Cur() + getPhut_Cur() + getGiay_Cur() +"-"+ getMiliGiay_Cur();
		return xref;
	}
	
	
	public static boolean isValidDate( String yyyyMMdd )
	{
		if ( yyyyMMdd == null )
			return false;
		// set the format to use as a constructor argument
		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );

		if ( yyyyMMdd.trim( ).length( ) != dateFormat.toPattern( ).length( ) )
			return false;
		dateFormat.setLenient( false );
		// parse the inDate parameter
		try
		{
			dateFormat.parse( yyyyMMdd.trim( ) );
		}
		catch ( Exception e )
		{
			return false;
		}
		return true;
	}

	
	//Them dau "-" cho MST > 10
	public static String addCharGreaterThan10(String value_mst){
		try{
			String result = "", MST = "";
			if (value_mst.length() > 10 && !value_mst.contains( "-" )) {
				MST = value_mst.substring(0, 10);
				result = MST + "-" + value_mst.substring( MST.length( ), value_mst.length( ) );
				return result;
			}else{
				return value_mst;
			}
			
		}catch(Exception e) {
	         e.printStackTrace();
	         return value_mst;
	    }
	}
 
	public static String PinCode_DigitalSign(String sMST){
		// input taxcode 0309478306 = (AEHDBKGCIF)
		// SUM = AB + CD + EF + GH  = (04 + 39 + 36 + 80)
		// range numbers: 1,5 + 8,4 + 2,10 + 7,3
		String pass = "", A="", B="", C="", D="", E="", F="", G="", H="";
		String S1S2="", S3="", S4="", S5="", S6="", sChk = "";
		
		int sum = 0;
		// check tax code greater than 10 then sub 10 character
		if(sMST.length( )> 10 ){
			sMST = sMST.substring( 0, 10 );
		}
		// Get format value
		A = sMST.substring( 0, 1 );
		B = sMST.substring( 4, 5 );
		C = sMST.substring( 7, 8 );
		D = sMST.substring( 3, 4 );
		E = sMST.substring( 1, 2 );
		F = sMST.substring( 9, 10 );
		G = sMST.substring( 6, 7 );
		H = sMST.substring( 2, 3 );
		// add sum
		sum += Integer.parseInt( A + B );
		sum += Integer.parseInt( C + D );
		sum += Integer.parseInt( E + F );
		sum += Integer.parseInt( G + H );
	
		// check value sum and conditions equals S1S2
		sChk = String.valueOf( sum );
		if(sum > 100){
			S1S2 = sChk.substring( sChk.length( ) - 2, sChk.length( ) );
		}else if(sum < 10){
			S1S2 = String.valueOf( sum + 10 );
		}else{ //sum is 2 number
			S1S2 = sChk; 
		}
		
		// check sum is value S3
		if(sum > 100){ // s2 equals 1 character the rest
			S3 = sChk.substring( sChk.length( ) - 1, sChk.length( ) );
		}else{ // S3 = S1 + S2
			S3 = String.valueOf( Integer.parseInt( S1S2.substring( 0, 1 ) ) + Integer.parseInt( S1S2.substring( 1, 2 ) ) );  
		}
		
		S4 = String.valueOf( Integer.parseInt( S3 ) * 2 + 2 );
		S5 = String.valueOf( Integer.parseInt( S4 ) * 3 + 2 );
		S6 = String.valueOf( Integer.parseInt( S5 ) * 4 );
		
		pass =  S1S2 + S3 + S4 + S5 + S6;
		return pass;
	}
	
	public static DataHandler getFileContent(String path){
		
		String fullPath = path;
		
		DataHandler repositoryItem = null;
		
		try {

			/*InputStream inputStream = new FileInputStream(fullPath);

			byte[] buffer = new byte[1024];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			int bytesRead = 0;
			while ((inputStream != null)
					&& ((bytesRead = inputStream.read(buffer)) != -1)) {
				baos.write(buffer, 0, bytesRead);
			}
			return baos.toByteArray();*/
			
			
			File repositoryItemFile = new File(fullPath);
			
			if(repositoryItemFile.exists())
				repositoryItem = new DataHandler(new FileDataSource(repositoryItemFile));


		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return repositoryItem;
		
	}
	
//==================================================================================================================
	
	/**
	 * FORMAT DISPLAY: TINH TRANG CHUNG HO SO BHXH
	 */
	public static String formatStatusBHXH_old(String hoso_dtu,
			String hoso_giaydtu, String hoso_loai5, int option){
		
		String format_str_old = "BOPHAN:_TINHTRANG_[NGUOIXULY]_(LYDO)(NGAY)";
		String format_str_new = "[BOPHAN]: TINHTRANG -[NGUOIXULY]-(LYDO)(NGAY)";
		
		String key_format_old = "_";
		String key_format_new = "-\\[";
		
		/*
		 * check tinh trang format new or old?
		 */
		if( (hoso_dtu != null && !hoso_dtu.equals("")) ||
			(hoso_giaydtu != null && !hoso_giaydtu.equals("")) ||
			(hoso_loai5 != null && !hoso_loai5.equals(""))){
			
			/*
			 * replace str: \r\n | \n
			 */
			if(hoso_dtu != null && !hoso_dtu.equals("")) 
				hoso_dtu = hoso_dtu.replaceAll("(\\\r\\\n)", "~").replaceAll("(\\\n)", "~");
			if(hoso_giaydtu != null && !hoso_giaydtu.equals(""))
				hoso_giaydtu = hoso_giaydtu.replaceAll("(\\\r\\\n)", "~").replaceAll("(\\\n)", "~");
			if(hoso_loai5 != null && !hoso_loai5.equals(""))
				hoso_loai5 = hoso_loai5.replaceAll("(\\\r\\\n)", "~").replaceAll("(\\\n)", "~");
			
			// if format tinhtrang: old
			if( (hoso_dtu != null && !hoso_dtu.equals("") && hoso_dtu.contains("_")) || 
				(hoso_giaydtu != null && !hoso_giaydtu.equals("") && hoso_giaydtu.contains("_")) ||
				(hoso_loai5 != null && !hoso_dtu.equals("") && hoso_loai5.contains("_")))
				
				return formatStatusBHXH_old(hoso_dtu, hoso_giaydtu, hoso_loai5, option, "old");
			
			// if format tinhtrang: new
			else return formatStatusBHXH_old(hoso_dtu, hoso_giaydtu, hoso_loai5, option, "new");
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param hoso_dtu
	 * @param hoso_giaydtu
	 * @param hoso_loai5
	 * @param option
	 * @return
	 */
	private static String formatStatusBHXH_old(String hoso_dtu,
			String hoso_giaydtu, String hoso_loai5, int option, String type) {

		String s_bophanxuly = "", s_tinhtrang = "", s_nguoixuly = "", s_lydo_ngayxuly = "", s_lydo = "", s_ngayxuly = "";
		String return_hsodtu = "", return_hsogiaydtu = "", return_hsoloai5 = "", s_return = "";

		String[] arr_str = null;
		String[] _arr_str = null;
		String[] arr_hoso_dtu = null, arr_hoso_giaydtu = null, arr_hoso_loai5 = null, arr_hoso_temp = null;
		
		String format_key = "";
		/*
		 * check type: new, old
		 */
		if(type.equals("new")){
			format_key = "-\\[";
		}else if(type.equals("old")){
			format_key = "_";
		}
		
		try {

			/**
			 * PROCESS: tinh trang chung ho so bhxh
			 * 
			 * 1./ loop tung dong tinhtrang:
			 * 2./ format lai chuoi tinhtrang:
			 */

			/*
			 * PROCESS hoso_dtu
			 */
			if (hoso_dtu != null && !hoso_dtu.equals("")) {
				if (hoso_dtu.contains("~")) { // neu co nhiu dong tinhtrang
					arr_hoso_dtu = hoso_dtu.split("~");
					// chi xem final tinh trang
					if (option == 1) {
						arr_hoso_temp = new String[1];
						arr_hoso_temp[0] = arr_hoso_dtu[arr_hoso_dtu.length - 1];
						arr_hoso_dtu = arr_hoso_temp;
					}
				} else { // chi co 1 dong tinh trang
					arr_hoso_dtu = new String[1];
					arr_hoso_dtu[0] = hoso_dtu;
				}
//				return_hsodtu = "<b>+ Hồ sơ điện tử: </b> <br />";
				for (String hoso : arr_hoso_dtu) {
					if(hoso != null && !hoso.equals("")){
						hoso = hoso.trim();
						arr_str = hoso.split(format_key);
						// get values:
						if(type.equals("old")){ // format tinhtrang: old
							s_bophanxuly = arr_str[0].trim();
							s_tinhtrang = arr_str[2].trim();
							s_nguoixuly = arr_str[3].trim();
							s_lydo_ngayxuly = arr_str[4].trim();
						}else if(type.equals("new")){ // format tinhtrang: new
							s_bophanxuly = arr_str[0].split(":")[0].trim();
							s_tinhtrang = arr_str[0].split(":")[1].trim();
							s_nguoixuly = arr_str[1].split("]-")[0].trim();
							s_lydo_ngayxuly = arr_str[1].split("]-")[1].trim();
						}
						s_bophanxuly = format_DepartBHXH(s_bophanxuly);
						if (s_lydo_ngayxuly.toLowerCase().contains("lý do")) {
							s_lydo_ngayxuly = s_lydo_ngayxuly.replace(")(", "_");
							s_lydo = s_lydo_ngayxuly.split("_")[0].trim() + ")";
							s_ngayxuly = "(" + s_lydo_ngayxuly.split("_")[1].trim();
						}
						else s_ngayxuly = s_lydo_ngayxuly;
//						else s_ngayxuly = s_lydo_ngayxuly.replaceAll("\\(Ngày:", "").replaceAll("\\)", "");
						
						// process return_hsodtu
						return_hsodtu += " - Phòng: " + s_bophanxuly + "; ";
						return_hsodtu += "Người xử lý: " + s_nguoixuly + " ";
//						return_hsodtu += "Ngày xử lý: " + s_ngayxuly + "; ";
						return_hsodtu += s_ngayxuly + "; ";
						return_hsodtu += "Tình trạng: <b>" + s_tinhtrang + "</b> " + s_lydo;

						return_hsodtu += "<br />";
					}
				} // end for hoso_dtu
				
				/*
				 * 	PROCESS hoso_giaydtu
				 */
//				if (hoso_giaydtu != null && !hoso_giaydtu.equals("")) {
				
//				} // end if hoso_giaydtu
				s_return = return_hsodtu;
				
			} // end if hoso_dtu
			/*
			 * 	PROCESS hoso_loai5
			 */
//			else if (hoso_loai5 != null && !hoso_loai5.equals("")) {
				
//			} // end if hoso_loai5
			
			else s_return = null;

		} catch (Exception e) {
			return null;
		}

		return s_return;

	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	private static String format_DepartBHXH(String input){
		
		if (input.toUpperCase().contains("HETHONG"))
			input = "Hệ thống";
		else if (input.toUpperCase().contains("TNHS"))
			input = "Tiếp nhận hồ sơ";
		else if (input.toUpperCase().contains("CHEDO"))
			input = "Chế độ";
		else if (input.toUpperCase().contains("THU"))
			input = "Thu";
		else if (input.toUpperCase().contains("SOTHE"))
			input = "Sổ thẻ";
		else if (input.toUpperCase().contains("KETOAN"))
			input = "Kế toán";
		else if (input.toUpperCase().contains("BANGIAMDOC"))
			input = "Ban giám đốc";
		else if (input.toUpperCase().contains("GIAMDINH"))
			input = "Giám định";
		
		return input;
		
	}
	
	
//=================================================================
	
	/**
	 * 
	 * @param hoso_dtu
	 * @param hoso_giaydtu
	 * @param hoso_loai5
	 * @param ketqua
	 * @return
	 */
	public static String formatStatusBHXH(String hoso_dtu,
			String hoso_giaydtu, String hoso_loai5, String ketqua, String loaihso){
		
		String format_str_old = "BOPHAN:_TINHTRANG_[NGUOIXULY]_(LYDO)(NGAY)";
		String format_str_new = "[BOPHAN]: TINHTRANG -[NGUOIXULY]-(LYDO)(NGAY)";
		
		String key_format_old = "_\\(";
		String key_format_new = "-\\(";
		
		/*
		 * check tinh trang format new or old?
		 */
		if( hoso_dtu != null && !hoso_dtu.equals(""))
		{
			/*
			 * replace str: \r\n | \n
			 */
			if(hoso_dtu != null && !hoso_dtu.equals("")) 
				hoso_dtu = hoso_dtu.replaceAll("(\\\r\\\n)", "~").replaceAll("(\\\n)", "~");
			
			// if format tinhtrang: old
			if( hoso_dtu != null && !hoso_dtu.equals("") && hoso_dtu.contains("_["))
				return formatStatusBHXH(hoso_dtu, hoso_giaydtu, hoso_loai5, "old", ketqua, loaihso);
			// if format tinhtrang: new
			else return formatStatusBHXH(hoso_dtu, hoso_giaydtu, hoso_loai5, "new", ketqua, loaihso);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param hoso_dtu
	 * @param hoso_giaydtu
	 * @param hoso_loai5
	 * @param ketqua
	 * @return
	 */
	private static String formatStatusBHXH(String hoso_dtu,
			String hoso_giaydtu, String hoso_loai5, String type, String ketqua, String loaihso) {

		String s_ngayxuly = "", s_nganhso = "", s_phongxuly = "", s_nguoixuly = "", s_tinhtrang = "";
		String return_hsodtu = "", return_hsogiaydtu = "", return_hsoloai5 = "", s_return = "";

		String[] arr_str = null;
		String[] _arr_str = null;
		String[] arr_hoso_dtu = null, arr_hoso_giaydtu = null, arr_hoso_loai5 = null, arr_hoso_temp = null;
		
		String format_key = "", format_key_ = "", format_ngan = "";
		String BHXH_LIST_STATUS_RETURN = null, BHXH_LIST_STATUS_ALERT = null;
		String condition_1 = "", condition_2 = "", condition_3 = "";
		String s_alert_1 = "", s_alert_2 = "", s_alert_3 = "", s_alert_4 = "", s_alert_5 = ""; 
		
		/*
		 * check type & format key
		 */
//		if(type.equals("new"))
//			format_key = "-\\(";
//		else if(type.equals("old"))
//			format_key = "_\\(";
		format_key_ = "@";
		format_ngan = "\\[Ngan\\]";
		
		/*
		 * process list staus, alert
		 */
		BHXH_LIST_STATUS_RETURN = "Duyệt hồ sơ,Đã trả kết quả cho đơn vị,Đã nhận hồ sơ và đưa vào ngăn";
//		BHXH_LIST_STATUS_RETURN = Path.BHXH_LIST_STATUS_RETURN.split(",");
		condition_1 = BHXH_LIST_STATUS_RETURN.split(",")[0]; // da duyet
		condition_2 = BHXH_LIST_STATUS_RETURN.split(",")[1]; // da tra ket qua
		condition_3 = BHXH_LIST_STATUS_RETURN.split(",")[2]; // da nhan hso va dua vao ngan

		BHXH_LIST_STATUS_ALERT = "HS bị lỗi,HS đã được duyệt,Đã trả kết quả ngày,Mời đơn vị đến nhận KQ từ ngày-Mã ngăn HS,Đang xử lý";
//		BHXH_LIST_STATUS_ALERT = Path.BHXH_LIST_STATUS_ALERT.split(",");
		s_alert_1 = BHXH_LIST_STATUS_ALERT.split(",")[0]; // hso bi loi
		s_alert_2 = BHXH_LIST_STATUS_ALERT.split(",")[1]; // hso da duoc duyet
		s_alert_3 = BHXH_LIST_STATUS_ALERT.split(",")[2]; // da tra ket qua
		s_alert_4 = BHXH_LIST_STATUS_ALERT.split(",")[3]; // da xu ly xong
		s_alert_5 = BHXH_LIST_STATUS_ALERT.split(",")[4]; // dang xu ly
		
		try {

			/**
			 * PROCESS: tinh trang chung ho so bhxh
			 * 
			 * 1./ loop tung dong tinhtrang:
			 * 2./ format lai chuoi tinhtrang:
			 */

			/*
			 * format bit ket qua
			 */
			//ketqua = formatBitKetQuaBHXH(ketqua, loaihso);
			
			/*
			 * PROCESS hoso_dtu
			 */
			if (hoso_dtu != null && !hoso_dtu.equals("")) {
				
				// neu co nhiu dong tinhtrang
				if (hoso_dtu.contains("~")) {
					arr_hoso_dtu = hoso_dtu.split("~");
					// chi lay dong cuoi cung
					hoso_dtu = arr_hoso_dtu[arr_hoso_dtu.length - 1];
				}
				/*
				 * check loai hoso
				 */
//				if(loaihso.equals("0"))
//					return_hsodtu = "<b><i>- HS điện tử: </i></b>";
//				else if(loaihso.equals("1"))
//					return_hsodtu = "<b><i>- HS giấy: </i></b>";
//				else if(loaihso.equals("2"))
//					return_hsodtu = "<b><i>- HS Email: </i></b>";
//				else if(loaihso.equals("3"))
//					return_hsodtu = "<b><i>- HS giấy điện tử: </i></b>";
//				else if(loaihso.equals("4"))
//					return_hsodtu = "<b><i>- HS sổ thẻ: </i></b>";
//				else if(loaihso.equals("5"))
//					return_hsodtu = "<b><i>- HS vừa điện tử vừa giấy: </i></b>";
				
				/*
				 * cehck ket qua
				 */
//				if(ketqua != null && !ketqua.equals("")){
					
//					if(ketqua.equals("03")){ // ho so sai
//						return_hsodtu += s_alert_1;
					
//					}else if(ketqua.equals("02")){ // ho so dung
						
						if(hoso_dtu.contains("_[")) // old format
							format_key = "_\\(";
						else // new format
							format_key = "-\\(";
				
						arr_str = hoso_dtu.split(format_key);
						
						/*
						 * check tinh trang tra ve:
						 */
//						if(hoso_dtu.contains( format_ngan.replaceAll("\\\\", "") )){ // da xu ly va dua vao ngan
//							
//							hoso_dtu = hoso_dtu.replaceAll(format_ngan, format_key_);
//							arr_str = hoso_dtu.split("-"+format_key_+":");
//							
//							s_nganhso = ": " + arr_str[1].split(format_key)[0].trim().split("-")[0].trim();
//							s_nguoixuly = arr_str[1].split(format_key)[0].trim().split("-")[1].trim();
//							
//						}
//						else{ // cac tinh trang khac
//							s_nguoixuly = arr_str[0].trim().split(":")[1].split("-")[1].trim();
//						}
//						
////						s_phongxuly = format_DepartBHXH(arr_str[0].trim().split(":")[0]);
//						s_phongxuly = arr_str[0].trim().split(":")[0];
//						s_tinhtrang = arr_str[0].trim().split(":")[1].split("-")[0].trim();
//						
//						return_hsodtu += "<b><i>" + s_tinhtrang + s_nganhso + "</b></i> <br />";
//						return_hsodtu += "Phòng xử lý: " +s_phongxuly + "; ";
//						return_hsodtu += "Người xử lý: " +s_nguoixuly + "; ";
//						return_hsodtu += "Ngày xử lý: " +s_ngayxuly;
						
						/*
						 * check tinh trang tra ve: cac truong hop 1,2,3,4
						 */
						if(hoso_dtu.contains(condition_1)){ // ho so da duyet
							
							return_hsodtu += s_alert_2;
						}
						else if(hoso_dtu.contains(condition_2)){ // da tra ket qua
							
							s_ngayxuly = formatNgayXulyBHXH( arr_str[1].trim() );
							return_hsodtu += s_alert_3 +": "+ s_ngayxuly; 
						}
						else if(hoso_dtu.contains(condition_3)){
							
							s_ngayxuly = formatNgayXulyBHXH( arr_str[1].trim() );
							
							hoso_dtu = hoso_dtu.replaceAll(format_ngan, format_key_);
							arr_str = hoso_dtu.split("-"+format_key_+":");
							
							s_nganhso = arr_str[1].split(format_key)[0].trim().split("-")[0].trim();
								
							return_hsodtu += s_alert_4.split("-")[0] +": "+ s_ngayxuly + ". "; 
							return_hsodtu += s_alert_4.split("-")[1] +": "+ s_nganhso;
						}
						else{ // dang xu ly
							s_phongxuly = format_DepartBHXH(arr_str[0].trim().split(":")[0]);
							return_hsodtu += s_alert_5 + "<br />";
							return_hsodtu += "Phòng xử lý: " +s_phongxuly;
						}
						
//					}
//				}
//				return_hsodtu += "<br />";
				
				/*
				 * 	PROCESS hoso_giaydtu
				 */
				if (hoso_giaydtu != null && !hoso_giaydtu.equals("")) {
					
				} // end if hoso_giaydtu
				s_return = return_hsodtu;
				
			} // end if hoso_dtu
			
			/*
			 * 	PROCESS hoso_loai5
			 */
			else if (hoso_loai5 != null && !hoso_loai5.equals("")) {
				
			} // end if hoso_loai5
			else s_return = null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return s_return;

	}
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	private static String formatNgayXulyBHXH(String input){
		
		String format = "(Ngày:yyyy-mm-dd hh:mm:ss)"; // format to: [dd/mm/yyyy]
		String [] arr_date = null;
		
		try{
			
			input = input.toLowerCase().replaceAll("ngày:", "").replaceAll("\\)", "");
			
			if(input.contains(" ")){
				arr_date = input.split(" ");
				return formatNgaySo5(arr_date[0]).replaceAll("-", "/") +" "+ arr_date[1];
				
			}else return formatNgaySo5(input).replaceAll("-", "/");
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	private static String formatBitKetQuaBHXH(String input, String type){
		
		String format = "01100100000000001000000000";
		String bit2 = "", bit6 = "", _return = "";
		try{
			
			if(type.equals("0") || type.equals("5")){
				// get bit at position: 2
				bit2 = input.substring(2, 3);
				
				if(bit2.equals("1")){
					// get bit at position: 6
					bit6 = input.substring(6, 7);
					
					if(bit6.equals("1"))
						_return = "02"; // hop le
					else if(bit6.equals("0"))
						_return = "03"; // khong hop le
					
				}else if(bit2.equals("0"))
					_return = "00";
				
			}else if(type.equals("1") || type.equals("2") || type.equals("3") || type.equals("4")) 
				_return = "02"; // hop le
			else _return = "00";
			
			return _return;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	//===========
	public static String formatNgayThangNam_tracuu(String today) {
		String ngaythangnam = "";
		if (!today.equals("") && !today.equals("null") && today != null) {
			String nam = null;
			String thang = null;
			String ngay = null;
			String arr[] = new String[3];
			today = today.trim();
			arr = today.split("-");
			if (arr.length > 0) {
				nam = arr[0];
				thang = arr[1];
				ngay = arr[2];
				ngaythangnam = ngay + "/" + thang + "/" + nam;
			}
		}
		return ngaythangnam;
	}
	
	//===========
	private static String tcvn(){
		String a = "" ;
		a+="‘µ’, ‘¸’, ‘¶’, ‘·’, ‘¹’"; 
		a+="‘¨’, ‘»’, ‘¾’, ‘¼’, ‘½’, ‘Æ’"; 
		a+="‘©’, ‘Ç’, ‘Ê’, ‘È’, ‘É’, ‘Ë’";
		a+=" ‘®’, ‘Ì’, ‘Ð’, ‘Î’, ‘Ï’, ‘Ñ’";
		a+=" ‘ª’, ‘Ò’, ‘Õ’, ‘Ó’, ‘Ô’, ‘Ö’";
		a+=" ‘×’, ‘Ý’, ‘Ø’, ‘Ü’, ‘Þ’";
		a+=" ‘ß’, ‘ã’, ‘á’, ‘â’, ‘ä’"; 
		a+="‘«’, ‘å’, ‘è’, ‘æ’, ‘ç’, ‘é’"; 
		a+=" ‘¬’, ‘ê’, ‘í’, ‘ë’, ‘ì’, ‘î’";
		a+=" ‘ï’, ‘ó’, ‘ñ’, ‘ò’, ‘ô’"; 
		a+=" ‘­’, ‘õ’, ‘ø’, ‘ö’, ‘÷’, ‘ù’"; 
		a+=" ‘ú’, ‘ý’, ‘û’, ‘ü’, ‘þ’"; 
		a+=" ‘¡’, ‘¢’, ‘§’, ‘£’, ‘¤’, ‘¥’, ‘¦";
		return a ;
	}
	static String tcvn3 = "µ¸¶·¹¨»¾¼½Æ©ÇÊÈÉË®ÌÐÎÏÑªÒÕÓÔÖ×ÝØÜÞßãáâä«åèæçé¬êíëìîïóñòô­õøö÷ùúýûüþ¡¢§£¤¥¦" ;
	static String unicode = "àáảãạăằắẳẵặâầấẩẫậđèéẻẽẹêềếểễệìíỉĩịòóỏõọôồốổỗộơờớởỡợùúủũụưừứửữựỳýỷỹỵĂÂĐÊÔƠƯ";
	private static char []tcvnchars = tcvn3.toCharArray();

	private static String uni(){
		String uni = "" ;
		uni+="‘à’, ‘á’, ‘ả’, ‘ã’, ‘ạ’"; 
		uni+="‘ă’, ‘ằ’, ‘ắ’, ‘ẳ’, ‘ẵ’, ‘ặ’"; 
		uni+="‘â’, ‘ầ’, ‘ấ’, ‘ẩ’, ‘ẫ’, ‘ậ’"; 
		uni+="‘đ’, ‘è’, ‘é’, ‘ẻ’, ‘ẽ’, ‘ẹ’"; 
		uni+="‘ê’, ‘ề’, ‘ế’, ‘ể’, ‘ễ’, ‘ệ’"; 
		uni+="‘ì’, ‘í’, ‘ỉ’, ‘ĩ’, ‘ị’"; 
		uni+="‘ò’, ‘ó’, ‘ỏ’, ‘õ’, ‘ọ’"; 
		uni+="‘ô’, ‘ồ’, ‘ố’, ‘ổ’, ‘ỗ’, ‘ộ’"; 
		uni+="‘ơ’, ‘ờ’, ‘ớ’, ‘ở’, ‘ỡ’, ‘ợ’";
		uni+="‘ù’, ‘ú’, ‘ủ’, ‘ũ’, ‘ụ'"; 
		uni+="‘ư’, ‘ừ’, ‘ứ’, ‘ử’, ‘ữ’, ‘ự’"; 
		uni+="‘ỳ’, ‘ý’, ‘ỷ’, ‘ỹ’, ‘ỵ’, ";
			uni+="‘Ă’, ‘Â’, ‘Đ’, ‘Ê’, ‘Ô’, ‘Ơ’, ‘Ư’ ";
			return uni;
	}

	private static char[] unichars = unicode.toCharArray();
    private static char[] convertTable;
    
    public static String TCVN3ToUnicode(String value) {
        char[] chars = value.toCharArray();
        convertTable = new char[256];
        
        for (int i = 0; i < 256; i++){
            convertTable[i] = (char)i;
        }
        for (int i = 0; i < tcvnchars.length; i++){
            convertTable[tcvnchars[i]] = unichars[i];
        }
        for (int i = 0; i < chars.length; i++){
            if (chars[i] < (char)256)
                chars[i] = convertTable[chars[i]];
        }
        return new String(chars);
    }
    
    /**
	 * 
	 * @param CODE
	 * @param CONFIG_CODE
	 * @return
	 */
	public static boolean CHECK_BHXH_CODE(String CODE, String CONFIG_CODE){
		
		try{
			String [] ARR_CODE = null;
			
			if (CONFIG_CODE == null || CONFIG_CODE.equals( "" ))
				return false;
			else if (CODE == null || CODE.equals( "" ))
				return false;
			
			CODE = CODE.toUpperCase();
			CONFIG_CODE = CONFIG_CODE.toUpperCase( ).replaceAll("'", "");
			if( CONFIG_CODE.contains( "," )){
				ARR_CODE = CONFIG_CODE.split(",");
				for (String _CODE : ARR_CODE) {
					if(_CODE != null && !_CODE.equals( "" )){
						if( _CODE.trim( ).equals( CODE.trim( ) ) )
							return true;
					}
				}
			}else{
				if( CONFIG_CODE.trim().equals( CODE.trim( ) ) ){
					return true;
				}
			}
			
			return false;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
    
	
	public static String formatNgayThangNam__ddmmyyyy(String today) {
		String ngaythangnam = "";
		if (today != null && !today.equals("") && !today.equals("null")) {
			String nam = null;
			String thang = null;
			String ngay = null;
			String arr[] = new String[3];
			today = today.trim();
			arr = today.split("-");
			if (arr.length > 0) {
				nam = arr[0];
				thang = arr[1];
				ngay = arr[2];
				ngaythangnam = ngay + "/" + thang + "/" + nam;
			}
		}
		return ngaythangnam;
	}
	
	public static String getFileNameByFilePath(String filePath){
		try{
			if(filePath == null || filePath.equals( "" )){
				return null;
			}
			int pos = filePath.lastIndexOf( "/" );
			return filePath.substring( pos + 1, filePath.length( ) );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings( { "rawtypes", "unchecked" } )
	public static ArrayList getThongTinCKS(String sign) {
		String sig = "" ;
		String signFirst [] = null ;
		String signArr [] = null ;
		ArrayList arr1 = new ArrayList();
		try{
			if(sign == null || sign.equals("")){
				return null ;
			}
			if(sign.contains("#;")){
				 signFirst = sign.split("#;");
			}
			for(int j= 0 ; signFirst != null && j < signFirst.length ; j++){
				if(signFirst[j].contains("~~~")){
					 signArr  = signFirst[j].split("~~~");
				}
				ArrayList arr = new ArrayList();
				for (int i = 0; i < signArr.length; i++) {
					if(signArr[i].contains("~#~")){
						sig = signArr[i].split("~#~")[1];
						arr.add(sig);
					}
				}
				arr1.add(arr);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null ;
		}
		
		return arr1;
	}
	
	
	public String randomXref_tenFile() {
		Format format = new SimpleDateFormat("ddMMyy");
		String xref = format.format(new Date()) + getGio_Cur() + getPhut_Cur() + getGiay_Cur() + getMiliGiay_Cur();
		return xref;
	}
	
	public static String formatDateTime2ddMMyyyyFull(String dateTime) {
		try
		{
			if(StringUtils.isEmpty(dateTime)){
				return null;
			}
			dateTime = dateTime.replaceAll("\\.0", "").replaceAll("/", "-");
			String today = "", ngayThangNam = "";
			String arrDt[] = new String[6];
			dateTime = dateTime.trim();
			arrDt = dateTime.split(" ");
			today = arrDt[0];
			if (today != null && !today.equals("") && !today.equals("null")) {
				String nam = null;
				String thang = null;
				String ngay = null;
				String arr[] = new String[3];
				today = today.trim();
				arr = today.split("-");
				if (arr.length > 0) {
					nam = arr[0];
					thang = arr[1];
					ngay = arr[2];
					ngayThangNam = ngay + "-" + thang + "-" + nam;
				}
			}
			return ngayThangNam + " " + arrDt[1];
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return "";
		}
	}
	public static String formatDateTime2ddMMyyyy(String dateTime) {
		try
		{
			if(StringUtils.isEmpty(dateTime)){
				return null;
			}
			dateTime = dateTime.replaceAll("\\.0", "").replaceAll("/", "-");
			String today = "", ngayThangNam = "";
			String arrDt[] = new String[6];
			dateTime = dateTime.trim();
			arrDt = dateTime.split(" ");
			today = arrDt[0];
			if (today != null && !today.equals("") && !today.equals("null")) {
				String nam = null;
				String thang = null;
				String ngay = null;
				String arr[] = new String[3];
				today = today.trim();
				arr = today.split("-");
				if (arr.length > 0) {
					nam = arr[0];
					thang = arr[1];
					ngay = arr[2];
					ngayThangNam = ngay + "-" + thang + "-" + nam;
				}
			}
			return ngayThangNam;
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return "";
		}
	}
	
	public static String formatDateTimeTo01MMyyyy(String datetime) {
		if(datetime == null || datetime.equals("")){
			return null;
		}
		datetime = datetime.replaceAll("/", "-");
		String nam = null;
		String thang = null;
		String arr[] = new String[2];
		datetime = datetime.trim();
		arr = datetime.split(" ");
		String arrDate[] = arr[0].split("-");
		thang = arrDate[1];
		nam = arrDate[0];
		String namthangngay = "01-" + thang + "-" + nam;
		return namthangngay;
	}
	
	public static String formatDateTimeToEndMMyyyy(String ddMMyyyy) {
		if(ddMMyyyy == null || ddMMyyyy.equals("")){
			return null;
		}
		ddMMyyyy = ddMMyyyy.replaceAll("/", "-");
		String endDate = String.valueOf(AddMonth(ddMMyyyy, 1));
		return String.valueOf(AddDate(endDate, -1));
	}
	
	public static boolean isValidDateddmmyyyy(String dateOfBirth) {
        boolean valid = true;
        try {
        	SimpleDateFormat sdfA = new SimpleDateFormat("dd/MM/yyyy");
        	sdfA.setLenient(false);
        	Date date= sdfA.parse(dateOfBirth);
       //	  DateTime dob = ((Object) sdfA).parseDateTime(dateOfBirth);
        //    DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
         //   DateTime dob = formatter.parseDateTime(dateOfBirth);
        } catch (Exception e) {
            valid = false;
        }
        return valid;
    }
	
	public static int formatNgaySo_ddmmyyyy(String today) {
		String nam = null;
		String thang = null;
		String ngay = null;
		String arr[] = new String[6];
		today = today.trim().replaceAll("/", "-");;
		arr = today.split("-");
		ngay = arr[0];
		thang = arr[1];
		nam = arr[2];
		String namthangngay = nam + thang + ngay;
		return Integer.parseInt(namthangngay);
	}
	
	public static long calNumberDay(String datetimeStart, String datetimeStop){
		long lResult = 0;
		try {
			Date d1 = null;
			Date d2 = null;
			//String dateStart = "2014-06-08 15:51:52";
			//String dateStop = "2014-06-09 16:50:53";
			//HH converts hour in 24 hours format (0-23), day caculation
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			
			d1 = format.parse(datetimeStart);
			d2 = format.parse(datetimeStop);
 
			//in milliseconds
			long diff = d2.getTime() - d1.getTime();
 
			//long diffSeconds = diff / 1000 % 60;
			//long diffMinutes = diff / (60 * 1000) % 60;
			//long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);
 		
			lResult = diffDays;
			return lResult;
 
		} catch (Exception e) {
			e.printStackTrace();
			return lResult;
		}
		
	}
	
	
	public static String formatDateTimeTo01MMyyyy_new(String datetime) {
		if(datetime == null || datetime.equals("")){
			return null;
		}
		datetime = datetime.replaceAll("/", "-");
		String nam = null;
		String thang = null;
		String arr[] = new String[2];
		datetime = datetime.trim();
		arr = datetime.split(" ");
		String arrDate[] = arr[0].split("-");
		thang = arrDate[1];
		nam = arrDate[0];
		String namthangngay = "01/" + thang + "/" + nam;
		return namthangngay;
	}
	
	public static String formatDateTimeToEndMMyyyy_new(String ddMMyyyy) {
		if(ddMMyyyy == null || ddMMyyyy.equals("")){
			return null;
		}
		ddMMyyyy = ddMMyyyy.replaceAll("/", "-");
		String endDate = String.valueOf(AddMonth(ddMMyyyy, 1));
		return String.valueOf(AddDate(endDate, -1).replaceAll("-", "/"));
	}
	
	public static void main(String[] args) {
	//	calNumberDay("01/10/2016","31/10/2016");
	//	String mst = "432543 OR 234355 OR" ;
	//	System.out.println("lam=="+mst.substring(0, mst.length() - 2));
		System.out.println(checkEmail("do.tran@ts24corp.com"));	
	}
	
}
