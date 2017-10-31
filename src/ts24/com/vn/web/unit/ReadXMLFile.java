package ts24.com.vn.web.unit;

import java.io.File;

import ts24.com.vn.web.util.ReadXMLFileUsingDom;

public class ReadXMLFile {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		try {
			ReadXMLFileUsingDom readXML = new ReadXMLFileUsingDom();
			String fileName = "", pathXML ="", key="";
			pathXML = "D:\\TS24Corp\\EclipseProjects\\HDDT-CQT_CTRL\\webapp\\xml\\StyleSheet.xml";
			key = "01GTKT_1";
			File xmlFile = new File(pathXML);
			fileName = readXML.ReadXMLFileStyleSheet(xmlFile, key);
			System.out.println("FileName: " + fileName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}


//01GTKT_1
//01GTKT_41
//01GTKT_42
//01GTKT_43
//01GTKT_44
//01GTKT_45
//01GTKT_46
//01GTKT_47
//01GTKT_48
//01GTKT_49
//01GTKT_50
//01GTKT_51
//01GTKT_80
//01GTKT_58
//02GTTT_1
//02GTTT_41
//02GTTT_50
//02GTTT_51
//02GTTT_80
//02GTTT_58
//03XKNB_1
//04HGDL_1
//06HDXK_1
//07KPTQ_1
//BBTH
//BBDC
//01GTKT_41VNPT
//01GTKT_42VNPT
//BCHD_QDPHHD
//BCHD_TB01_TT32
//01GTKT_410
//02GTTT_41VNPT
//01GTKT_411
//01GTKT_412