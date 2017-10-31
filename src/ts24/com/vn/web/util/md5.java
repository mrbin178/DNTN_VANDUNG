package ts24.com.vn.web.util;

import java.lang.*;
import java.io.*;
import java.security.*;

/**
* Author: Skeleton
* Company: http://bnportal.dev.java.net
*/

public class md5 {

private static MessageDigest md;


protected static String processPassword( String stPassword_ )
throws IOException
{
StringReader sr = null;
StringBuffer sbPassword = new StringBuffer( );
int cod = 0;
md.update( stPassword_.getBytes( ));

sr = new StringReader( new String( md.digest( )));

while (( cod = sr.read( )) != -1 ){
sbPassword.append( Integer.toHexString( cod ));
/*System.out.println( "Cryptographic text: " + sbPassword.toString( ));*/
}
return sbPassword.toString();
}


public static String encode(String input ) throws Exception

{
    String s=input;
     MessageDigest m=MessageDigest.getInstance("MD5");
     m.update(s.getBytes(),0,s.length());
   // System.out.println("MD5: "+new BigInteger(1,m.digest()).toString(16));
    // return new BigInteger(1,m.digest()).toString(16);
     return  asHex(m.digest());
}

public static String asHex(byte[] buf)
{
	 char[] HEX_CHARS = "0123456789abcdef".toCharArray();

    char[] chars = new char[2 * buf.length];
    for (int i = 0; i < buf.length; ++i)
    {
        chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
        chars[2 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
    }
    return new String(chars);
}


}
