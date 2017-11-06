package satthepvandung.model;

public class ObjUserProfile {
	/** Reference defined data in LDAP 
	 * link: http://www.zytrax.com/books/ldap/ape/ 
	 **/

	private String mail; 

	private String telephoneNumber;
	private String userPassword;
	private String uid;
	private int status = 0;
	
	private String answer;
	private String codeactive;
	private int countblocked;
	private int countlogin;
	private String editeddate;
	private String fullname;
//	private int id;
	private int permanentblocked = 0;
	private String question;
	private String registeddate;
	private String timecodeactive;
	private String timetampleblocked;
	private String timecodereset;
	
	private String accounttype;
	private String tokenSocial;
	
	private String facebook;
	private String google;
	private String skype;
	private String zalo;
	private String viber;	
	private String phuong;
	private String xa;
	private String quanhuyen;
	private String tinhtp;
	private String street ;
	
	
	
	

public String getViber() {
		return viber;
	}
	public void setViber(String viber) {
		this.viber = viber;
	}

	public String getPhuong() {
		return phuong;
	}
	public void setPhuong(String phuong) {
		this.phuong = phuong;
	}
	public String getXa() {
		return xa;
	}
	public void setXa(String xa) {
		this.xa = xa;
	}
	public String getQuanhuyen() {
		return quanhuyen;
	}
	public void setQuanhuyen(String quanhuyen) {
		this.quanhuyen = quanhuyen;
	}
	public String getTinhtp() {
		return tinhtp;
	}
	public void setTinhtp(String tinhtp) {
		this.tinhtp = tinhtp;
	}
public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getCodeactive() {
		return codeactive;
	}
	public void setCodeactive(String codeactive) {
		this.codeactive = codeactive;
	}
	public int getCountblocked() {
		return countblocked;
	}
	public void setCountblocked(int countblocked) {
		this.countblocked = countblocked;
	}
	public int getCountlogin() {
		return countlogin;
	}
	public void setCountlogin(int countlogin) {
		this.countlogin = countlogin;
	}
	public String getEditeddate() {
		return editeddate;
	}
	public void setEditeddate(String editeddate) {
		this.editeddate = editeddate;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getPermanentblocked() {
		return permanentblocked;
	}
	public void setPermanentblocked(int permanentblocked) {
		this.permanentblocked = permanentblocked;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getRegisteddate() {
		return registeddate;
	}
	public void setRegisteddate(String registeddate) {
		this.registeddate = registeddate;
	}
	public String getTimecodeactive() {
		return timecodeactive;
	}
	public void setTimecodeactive(String timecodeactive) {
		this.timecodeactive = timecodeactive;
	}
	public String getTimetampleblocked() {
		return timetampleblocked;
	}
	public void setTimetampleblocked(String timetampleblocked) {
		this.timetampleblocked = timetampleblocked;
	}
	//	public String getRootDC() {
//		return rootDC;
//	}
//	public void setRootDC(String rootDC) {
//		this.rootDC = rootDC;
//	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTimecodereset() {
		return timecodereset;
	}
	public void setTimecodereset(String timecodereset) {
		this.timecodereset = timecodereset;
	}
	public String getAccounttype() {
		return accounttype;
	}
	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}
	public String getTokenSocial() {
		return tokenSocial;
	}
	public void setTokenSocial(String tokenSocial) {
		this.tokenSocial = tokenSocial;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getGoogle() {
		return google;
	}
	public void setGoogle(String google) {
		this.google = google;
	}
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
	}
	public String getZalo() {
		return zalo;
	}
	public void setZalo(String zalo) {
		this.zalo = zalo;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	} 
}
