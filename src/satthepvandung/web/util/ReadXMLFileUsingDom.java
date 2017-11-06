package satthepvandung.web.util;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import satthepvandung.model.StyleSheetRecord;
public class ReadXMLFileUsingDom
{

	public static String ReadXMLFileStyleSheet(File xmlFile, String key)
	{
		String sFileName = "";
		try {
			List<StyleSheetRecord> list = ParseStringXMLToObject.unMarshalingFileXML2Object(xmlFile);
			if(list != null && list.size() > 0){
				for (StyleSheetRecord styleSheetRecord : list) {
					if(styleSheetRecord.getMaToKhai() != null && styleSheetRecord.getMaToKhai().trim().toLowerCase().equals(key.toLowerCase())){
						sFileName = styleSheetRecord.getStyleSheet();
						break;
					} 
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sFileName;
	}
	
	
	public static String ReadXMLFileStyleSheetNode(File xmlFile, String key)
	{
		try {
			String sFileName = "";
			// begin attach file
			//File xmlFile = new File( xmlFilePath );
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			System.out.println("Root element file StyleSheet.xml ::::::::::::::::::::::" + doc.getDocumentElement().getNodeName());
			// take the form tag in template
			NodeList nodeList = doc.getElementsByTagName(doc.getDocumentElement().getNodeName());

			System.out.println("===============================================================");

			//do this the old way, because nodeList is not iterable
			for (int itr = 0; itr < nodeList.getLength(); itr++) {
				Node node = nodeList.item(itr);
				//System.out.println("\nNode Name :" + node.getNodeName());
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					
					NodeList nodeListTK = eElement.getElementsByTagName("Record");
					for (int itr1 = 0; itr1 < nodeListTK.getLength(); itr1++) {
						Node nodeTK = nodeListTK.item(itr1);
						if (nodeTK.getNodeType() == nodeTK.ELEMENT_NODE) {
							Element eElement1 = (Element) nodeTK;
							String sMaToKhai = "";
		                	sMaToKhai = eElement1.getElementsByTagName("MaToKhai").item(0).getTextContent();
		                	if(sMaToKhai != null && sMaToKhai.equals( key )){
		                		sFileName = eElement1.getElementsByTagName("StyleSheet").item(0).toString();
		                		break;
		                	}
						}
					}
				}
			}
			
			return sFileName;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}