package ts24.com.vn.web.digisign;

import com.ts24.view.XmlSignatureInfomationXpath;;

public class DigitalSignVerify {

	public String getInfoXmlSignatureXpathHDDT_BenhVien(String fileSource){
		XmlSignatureInfomationXpath xmlSign = new XmlSignatureInfomationXpath();
		String infoDigiSign = "";
		try {
			infoDigiSign = xmlSign.getInfoXmlSignatureXpathHDDT_BenhVien(fileSource);
		} catch (Exception e) {
			e.printStackTrace( );
			return infoDigiSign;
		}
		return infoDigiSign;
	}
	
	public String getInfoXmlSignatureXpathHDDT_Vnpt(String fileSource, String[] listId){
		XmlSignatureInfomationXpath xmlSign = new XmlSignatureInfomationXpath();
		String infoDigiSign = "";
		try {
			infoDigiSign = xmlSign.getInfoXmlSignatureXpathListId(fileSource, listId);
		} catch (Exception e) {
			e.printStackTrace( );
			return infoDigiSign;
		}
		return infoDigiSign;
	}

}
