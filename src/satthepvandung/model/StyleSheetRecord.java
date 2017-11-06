package satthepvandung.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Record")
@XmlAccessorType(XmlAccessType.FIELD)
public class StyleSheetRecord {

	private String MaToKhai;
	private String MaPhuLuc;
	private String MaDinhDanh;
	private String TenToKhai;
	private String ElementName;
	private String StyleSheet;
	private String Version;
	public String getMaToKhai() {
		return MaToKhai;
	}
	public void setMaToKhai(String maToKhai) {
		MaToKhai = maToKhai;
	}
	public String getMaPhuLuc() {
		return MaPhuLuc;
	}
	public void setMaPhuLuc(String maPhuLuc) {
		MaPhuLuc = maPhuLuc;
	}
	public String getMaDinhDanh() {
		return MaDinhDanh;
	}
	public void setMaDinhDanh(String maDinhDanh) {
		MaDinhDanh = maDinhDanh;
	}
	public String getTenToKhai() {
		return TenToKhai;
	}
	public void setTenToKhai(String tenToKhai) {
		TenToKhai = tenToKhai;
	}
	public String getElementName() {
		return ElementName;
	}
	public void setElementName(String elementName) {
		ElementName = elementName;
	}
	public String getStyleSheet() {
		return StyleSheet;
	}
	public void setStyleSheet(String styleSheet) {
		StyleSheet = styleSheet;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}

}
