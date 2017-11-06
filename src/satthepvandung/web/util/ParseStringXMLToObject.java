package satthepvandung.web.util;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import satthepvandung.model.StyleSheetRecord;
import satthepvandung.model.StyleSheetRecords;

public class ParseStringXMLToObject 
{
	public static StyleSheetRecord stringXmlToObject(String xml){
		try {
			String sxml = "<ObjStyleSheetRecord>" + xml + "</ObjStyleSheetRecord>";
			   JAXBContext jc = JAXBContext.newInstance(StyleSheetRecord.class);
			   Unmarshaller unmarshaller = jc.createUnmarshaller();
			   StreamSource streamSource = new StreamSource(new StringReader(sxml));
			   JAXBElement<StyleSheetRecord> je = unmarshaller.unmarshal(streamSource, StyleSheetRecord.class);
			   
			   StyleSheetRecord r = (StyleSheetRecord)je.getValue();
			   if (r != null)
				   return r;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void main(String[] args) 
	{
//		marshalingExample();
		try {
			List<StyleSheetRecord> lits = unMarshalingFileXML2Object(new File("D:/TS24Corp/Requirements/XHD/HDDT/XSL_New_Sheet/StyleSheet.xml"));
			System.out.println("Parse Done");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public static List<StyleSheetRecord> unMarshalingFileXML2Object(File filePath) throws JAXBException {
		List<StyleSheetRecord> list = new ArrayList<StyleSheetRecord>();
		JAXBContext jaxbContext = JAXBContext.newInstance(StyleSheetRecords.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		StyleSheetRecords rcds = (StyleSheetRecords) jaxbUnmarshaller.unmarshal( filePath);
		for(StyleSheetRecord rcd : rcds.getRecords()){
			list.add(rcd);
		}
		return list;
	}

	public static void marshalingExample(File filePath) throws JAXBException {
		StyleSheetRecord list = new StyleSheetRecord();
		JAXBContext jaxbContext = JAXBContext.newInstance(StyleSheetRecord.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(list, System.out);
		jaxbMarshaller.marshal(list, filePath);
	}
	
	
}