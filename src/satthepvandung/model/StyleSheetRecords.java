package satthepvandung.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Records")
@XmlAccessorType(XmlAccessType.FIELD)
public class StyleSheetRecords {
	@XmlElement(name = "Record")
	private List<StyleSheetRecord> records = null;

	public List<StyleSheetRecord> getRecords() {
		return records;
	}

	public void setRecords(List<StyleSheetRecord> records) {
		this.records = records;
	}

}