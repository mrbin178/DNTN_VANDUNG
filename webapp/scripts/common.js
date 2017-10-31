// process for get file by action upload image/file
function getFileName(formname) {
	if (formname.browseImage.value != "")
		formname.fileName.value = formname.browseImage.value.split('\\')
				.reverse()[0].split('/').reverse()[0];
}

// ----- process post data fck by ajax
function replaceAll_FCK(streng, soeg, erstat) {
	var st = streng;
	if (soeg.length == 0)
		return st;
	var idx = st.indexOf(soeg);
	while (idx >= 0) {
		st = st.substring(0, idx) + erstat + st.substr(idx + soeg.length);
		idx = st.indexOf(soeg);
	}
	return st;
}
function ProtectedSource_FCK(sContent) {
	var tags = [ '&', '%', '+' ];
	var tagmap = [ '###', '@@@', '!!!' ];
	var tem = sContent;
	for (i = 0; i < tags.length; i++) {

		tem = replaceAll_FCK(tem, tags[i], tagmap[i]);
	}
	return tem;
}
function ProtectedSource_nonFCK(sContent) {
	var tags = [ '%', '+' ];
	var tagmap = [ '@@@', '!!!' ];
	var tem = sContent;
	for (i = 0; i < tags.length; i++) {

		tem = replaceAll_FCK(tem, tags[i], tagmap[i]);
	}
	return tem;
}

// --- function use to checkAll_clearAll ca'c (checkBox)
function checkAll_clearAll(formname) {
	var checkboxname = formname.check_clear;
	var checkedAll = checkboxname.checked;

	if (checkedAll) {
		checkedAll = true;
	} else {
		checkedAll = false;
	}

	var len = formname.elements.length;
	for (i = 0; i < len; i++) {
		sName = formname.elements[i].name;
		if (sName.substring(0, 4) == "chec") {
			formname.elements[i].checked = checkedAll;
		}
	}
}

// --- function use to checkAll_clearAll ca'c (checkBox) : case [delete]
function checkAll_clearAll_0(formname) {
	var checkboxname = formname.check_clear_0;
	var checkedAll = checkboxname.checked;

	if (checkedAll) {
		checkedAll = true;
	} else {
		checkedAll = false;
	}

	var len = formname.elements.length;
	for (i = 0; i < len; i++) {
		sName = formname.elements[i].name;
		if (sName == "checkbox_delete") {
			formname.elements[i].checked = checkedAll;
		}
	}
}
// --- function use to checkAll_clearAll ca'c (checkBox) : case display
function checkAll_clearAll_1(formname) {
	var checkboxname = formname.check_clear_1;
	var checkedAll = checkboxname.checked;

	if (checkedAll) {
		checkedAll = true;
	} else {
		checkedAll = false;
	}

	var len = formname.elements.length;
	for (i = 0; i < len; i++) {
		sName = formname.elements[i].name;
		if (sName == "checkbox_display") {
			formname.elements[i].checked = checkedAll;
		}
	}
}
// --- function use to checkAll_clearAll ca'c (checkBox) : case noi bat
function checkAll_clearAll_2(formname) {
	var checkboxname = formname.check_clear_2;
	var checkedAll = checkboxname.checked;

	if (checkedAll) {
		checkedAll = true;
	} else {
		checkedAll = false;
	}

	var len = formname.elements.length;
	for (i = 0; i < len; i++) {
		sName = formname.elements[i].name;
		if (sName == "checkbox_noibat") {
			formname.elements[i].checked = checkedAll;
		}
	}
}
// --- function use to checkAll_clearAll ca'c (checkBox) : case copy chuyen muc
// tuvan
function checkAll_clearAll_3(formname) {
	var checkboxname = formname.check_clear_3;
	var checkedAll = checkboxname.checked;

	if (checkedAll) {
		checkedAll = true;
	} else {
		checkedAll = false;
	}

	var len = formname.elements.length;
	for (i = 0; i < len; i++) {
		sName = formname.elements[i].name;
		if (sName == "checkbox_copied") {
			formname.elements[i].checked = checkedAll;
		}
	}
}

// --- function use to checkAll_clearAll ca'c (checkBox) : case duyet/chuyen
// duyet
function checkAll_clearAll_4(formname) {
	var checkboxname = formname.check_clear_4;
	var checkedAll = checkboxname.checked;

	if (checkedAll) {
		checkedAll = true;
	} else {
		checkedAll = false;
	}

	var len = formname.elements.length;
	for (i = 0; i < len; i++) {
		sName = formname.elements[i].name;
		if (sName == "chkMove") {
			formname.elements[i].checked = checkedAll;
		}
	}
}

// --- function use to checkAll_clearAll ca'c (checkBox) : case thu hoi
function checkAll_clearAll_5(formname) {
	var checkboxname = formname.check_clear_5;
	var checkedAll = checkboxname.checked;

	if (checkedAll) {
		checkedAll = true;
	} else {
		checkedAll = false;
	}

	var len = formname.elements.length;
	for (i = 0; i < len; i++) {
		sName = formname.elements[i].name;
		if (sName == "chkRollBack") {
			formname.elements[i].checked = checkedAll;
		}
	}
}

// go to page for pager by ajax
function GoToPagerAjax(url, ele) {
	loadURL(url, ele);
}
// go to page for pager
function GotoPage(page) {
	window.location = page;
}
// open poup dhtml window
function openPoupPage(page, pageTitle) { // Define arbitrary function to run
											// desired DHTML Window widget codes
	ajaxwin = dhtmlwindow.open("ajaxbox", "iframe", page, pageTitle,
			"width=650px,height=350px,left=1px,top=1px,resize=1,scrolling=1");
}
// open new window/tab
function MM_openBrWindow(theURL, winName, features) { // v2.0

	window.open(theURL, winName, features);
}
// check char contains in str
function contains(str, obj) {
	if (str.indexOf(obj) > 0) {
		return true;
	}
	return false;
}
// function check input numeric for any browser (IE, FireFox, etc.)
function CheckNumericKeyInfo(value) {
	if (!/^\d+$/.test(value))
		return false;
	return true;
}
// check condition input length
function CheckInputLen(value, Len) {
	if (value.length != Len)
		return false;
	return true;
}
// check input numeric
function CheckNumeric(id) {
	var charfield = document.getElementById(id)
	charfield.onkeyup = function(e) {
		var e = window.event || e
		if ((e.keyCode > 47 && e.keyCode < 58)
				|| (e.keyCode > 95 && e.keyCode < 106) || e.keyCode == 8
				|| e.keyCode == 13 || e.keyCode == 46 || e.keyCode == 44)
			return;
		else
			document.getElementById(id).value = "";
	}
}
// check input numeric for order (quantity+declare)
function CheckNumeric_order(id) {
	var charfield = document.getElementById(id)
	charfield.onkeyup = function(e) {
		var e = window.event || e
		if ((e.keyCode > 47 && e.keyCode < 58)
				|| (e.keyCode > 95 && e.keyCode < 106) || e.keyCode == 13
				|| e.keyCode == 46 || e.keyCode == 44)
			return;
		else
			document.getElementById(id).value = 1;
	}
}

// check dont allow input special character ($,%,#,@ etc. but accept underscore)
function Check_Valid_Char(o, w) {
	var r = {
		'special' : /[\W]/g
	}
	o.value = o.value.replace(r[w], '');
}

// check input space
function CheckInputSpace(id) {
	var charfield = document.getElementById(id)
	charfield.onkeyup = function(e) {
		var e = window.event || e
		if (e.keyCode == 32)
			document.getElementById(id).value = ""; // not allow input space.
	}
}
// check input format date [dd-mm-yyyy] hoặc [dd/mm/yyyy]
function checkValidNgay(sDate) // dd-mm-yyyy
{ // return a Date object if validate, otherwise , return 0

	i = 0;
	if (sDate != "") {
		while (i < sDate.length) {
			code = sDate.charCodeAt(i);
			// alert(code);
			if (code > 58 || (code < 47 && code != 45 && code != 47)) {// 45 =
																		// '-'
																		// ** 47
																		// = '/'
				return false;
			}
			i++;
		}

		if (contains(sDate, '-') == false && contains(sDate, '/') == false)
			return false;
		else {
			if (contains(sDate, '-') == true) {
				var listStr = sDate.split('-');
				var len = listStr.length;
				if (listStr.length != 3) {
					return false;
				}
				var d = listStr[0];
				var m = listStr[1];
				var y = listStr[2];
				if (d == '' || m == '' || y == '') {
					return false;
				}
				if ((d > 31 || d <= 0 || d.length != 2)
						|| (m > 12 || m <= 0 || m.length != 2)
						|| (y <= 0 || y.length != 4)) {
					return false;
				}
				m = m - 1; // thang trong Date bat dau tu 0
			}
			if (contains(sDate, "/") == true) {
				var listStr = sDate.split('/');
				var len = listStr.length;
				if (listStr.length != 3) {
					return false;
				}
				var d = listStr[0];
				var m = listStr[1];
				var y = listStr[2];
				if (d == '' || m == '' || y == '') {
					return false;
				}
				if ((d > 31 || d <= 0 || d.length != 2)
						|| (m > 12 || m <= 0 || m.length != 2)
						|| (y <= 0 || y.length != 4)) {
					return false;
				}
				m = m - 1; // thang trong Date bat dau tu 0
			}
		}

	}
	return true;
}
// show calendar follow format [dd-mm-yyyy]
function displayCalendar(button, inputField) {
	Zapatec.Calendar.setup({
		firstDay : 1,
		weekNumbers : true,
		showOthers : true,
		showsTime : true,
		timeFormat : "24",
		step : 2,
		range : [ 1950.01, 2020.12 ],
		electric : false,
		singleClick : false,
		inputField : inputField,
		// position : [1030,310],

		button : button,
		ifFormat : "%d-%m-%Y",
		daFormat : "%d-%m-%Y",
		align : "Br"
	});
}
// show calendar follow format [dd-mm-yyyy]
function displayCalendar_FullDateTime(button, inputField) {
	Zapatec.Calendar.setup({
		firstDay : 1,
		weekNumbers : true,
		showOthers : true,
		showsTime : true,
		timeFormat : "24",
		step : 2,
		range : [ 1950.01, 2020.12 ],
		electric : false,
		singleClick : false,
		inputField : inputField,
		// position : [1030,310],

		button : button,
		ifFormat : "%d-%m-%Y %H:%M:%S",
		daFormat : "%d-%m-%Y %H:%M:%S",
		align : "Br"
	});
}
// show calendar follow format [mm-yyyy]
function displayCalendar_mmYYYY(button, inputField) {
	Zapatec.Calendar.setup({
		firstDay : 1,
		weekNumbers : true,
		showOthers : true,
		showsTime : true,
		timeFormat : "24",
		step : 2,
		range : [ 1950.01, 2020.12 ],
		electric : false,
		singleClick : false,
		inputField : inputField,
		// position : [1030,310],

		button : button,
		ifFormat : "%m-%Y",
		daFormat : "%m-%Y",
		align : "Br"
	});
}

// trim string
function Trim(stringa) {
	reTrim = /\s+$|^\s+/g;
	return stringa.replace(reTrim, "");
}

// showing pop up menu in ie6
/*
 * sfHover = function() { var sfEls =
 * document.getElementById("t_popup").getElementsByTagName("LI"); for (var i=0;
 * i<sfEls.length; i++) { sfEls[i].onmouseover=function() { this.className+="
 * sfhover"; } sfEls[i].onmouseout=function() {
 * this.className=this.className.replace(new RegExp(" sfhover\\b"), ""); } } }
 * if (window.attachEvent) window.attachEvent("onload", sfHover);
 */

// ------------ function to convert a number (ex: 123456) into money (123,456)
// -----------------
/*
 * onBlur="this.value=formatCurrency(this.value);" ---> set function
 * formatCurrency() on a input type
 */
// =================== chi dc nhap so chan ==========================
function formatCurrency(num) {
	num = num.toString().replace(/\$|\,/g, '');
	if (isNaN(num))
		num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num * 100 + 0.50000000001);
	cents = num % 100;
	num = Math.floor(num / 100).toString();
	for ( var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
		num = num.substring(0, num.length - (4 * i + 3)) + ','
				+ num.substring(num.length - (4 * i + 3));
	return (((sign) ? '' : '-') + num);
}
// =================== co the nhap dc so le? ==========================
function formatCurrency1(num) {
	num = num.toString().replace(/\$|\,/g, '');
	if (isNaN(num))
		num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num * 100 + 0.50000000001);
	cents = num % 100;
	num = Math.floor(num / 100).toString();
	// if(cents<10)
	// cents = "0" + cents;
	for ( var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
		num = num.substring(0, num.length - (4 * i + 3)) + ','
				+ num.substring(num.length - (4 * i + 3));
	return (((sign) ? '' : '-') + num + '.' + cents);
}

// ---------------- check email --------------------
function checkEmail(emailStr) {

	var checkTLD = 1;

	/*
	 * The following is the list of known TLDs that an e-mail address must end
	 * with.
	 */

	var knownDomsPat = /^(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum)$/;

	var emailPat = /^(.+)@(.+)$/;

	var specialChars = "\\(\\)><@,;:\\\\\\\"\\.\\[\\]";

	/*
	 * The following string represents the range of characters allowed in a
	 * username or domainname. It really states which chars aren't allowed.
	 */

	var validChars = "\[^\\s" + specialChars + "\]";

	var quotedUser = "(\"[^\"]*\")";

	var ipDomainPat = /^\[(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})\]$/;

	/*
	 * The following string represents an atom (basically a series of
	 * non-special characters.)
	 */

	var atom = validChars + '+';

	/*
	 * The following string represents one word in the typical username. For
	 * example, in john.doe@somewhere.com, john and doe are words. Basically, a
	 * word is either an atom or quoted string.
	 */

	var word = "(" + atom + "|" + quotedUser + ")";

	// The following pattern describes the structure of the user

	var userPat = new RegExp("^" + word + "(\\." + word + ")*$");

	/*
	 * The following pattern describes the structure of a normal symbolic
	 * domain, as opposed to ipDomainPat, shown above.
	 */

	var domainPat = new RegExp("^" + atom + "(\\." + atom + ")*$");

	var matchArray = emailStr.match(emailPat);

	if (matchArray == null) {

		/*
		 * Too many/few @'s or something; basically, this address doesn't even
		 * fit the general mould of a valid e-mail address.
		 */

		// alert("Email address seems incorrect (check @ and .'s)");
		return false;
	}
	var user = matchArray[1];
	var domain = matchArray[2];

	// Start by checking that only basic ASCII characters are in the strings
	// (0-127).

	for (i = 0; i < user.length; i++) {
		if (user.charCodeAt(i) > 127) {
			// alert("Ths username contains invalid characters.");
			return false;
		}
	}

	for (i = 0; i < domain.length; i++) {
		if (domain.charCodeAt(i) > 127) {
			// alert("Ths domain name contains invalid characters.");
			return false;
		}
	}

	// See if "user" is valid

	if (user.match(userPat) == null) {

		// user is not valid

		// alert("The username doesn't seem to be valid.");
		return false;
	}

	/*
	 * if the e-mail address is at an IP address (as opposed to a symbolic host
	 * name) make sure the IP address is valid.
	 */

	var IPArray = domain.match(ipDomainPat);
	if (IPArray != null) {

		// this is an IP address

		for ( var i = 1; i <= 4; i++) {
			if (IPArray[i] > 255) {
				// alert("Destination IP address is invalid!");
				return false;
			}
		}
		return true;
	}

	// Domain is symbolic name. Check if it's valid.

	var atomPat = new RegExp("^" + atom + "$");
	var domArr = domain.split(".");
	var len = domArr.length;
	for (i = 0; i < len; i++)

	{
		if (domArr[i].search(atomPat) == -1) {
			// alert("The domain name does not seem to be valid.");
			return false;
		}
	}

	if (checkTLD && domArr[domArr.length - 1].length != 2
			&& domArr[domArr.length - 1].search(knownDomsPat) == -1) {

		return false;
	}

	// Make sure there's a host name preceding the domain.

	if (len < 2) {
		// alert("This address is missing a hostname!");
		return false;
	}

	// If we've gotten this far, everything's valid!
	return true;
}

// Show/Hide Loader
/*
 * function showLoader(target) { try { if (!target) target = this;
 * target.document.getElementById("loaderContainer").style.display = "";
 *  } catch (e) { return false; } return true; }
 * 
 * function hideLoader(target) {
 * 
 * 
 * try { if (!target) target = this;
 * 
 * _loff(target);
 * target.document.getElementById("loaderContainer").style.display = "none";
 *  } catch (e) { return false; } return true; }
 */
// Show/Hide Loader
function showLoader() {
	this.document.getElementById("loaderContainer").style.display = "";
	//this.document.getElementById("loading_button").style.display = "none";
	// return true;
}
function hideLoader() {
	this.document.getElementById("loaderContainer").style.display = "none";
	//this.document.getElementById("loading_button").style.display = "block";
	// return true;
}

function showBtnLoader(idButton) {
	this.document.getElementById("loaderContainer").style.display = "";
	this.document.getElementById(idButton).style.display = "none";
}
function hideBtnLoader(idButton) {
	this.document.getElementById("loaderContainer").style.display = "none";
	this.document.getElementById(idButton).style.display = "block";
}

function _loff(target) {
	try {
		if (!target)
			target = this;

		target.document.getElementById("loaderContainer").style.display = "none";
	} catch (e) {
		return false;
	}
	return true;
}
// End Loader

function autoChangeMoney_keypress_daucham(id) {
	var smoney = document.getElementById(id).value;
	emoney = currency_number_format(smoney);
	document.getElementById(id).value = emoney;
}

function currency_number_format(v) {
	v2 = "";
	if (v.indexOf(',') > 0) {
		v1 = v.substring(0, v.indexOf(','));
		v2 = v.substring(v.indexOf(','), v.length);
		v = v1;
		v3 = v2.substring(1, v2.length);
		if (v3.indexOf(',') > 0) {
			v2 = v3.substring(0, v3.indexOf(','));
		} else if (v3.indexOf('.') > 0) {
			v2 = v3.substring(0, v3.indexOf('.'));
		} else {
			v2 = v3;
		}
		if (isNaN(v2))
			v2 = '0';

		if (v2.length >= 2) {
			numTemp = new Number(v2.substring(0, 2));
			v2 = numTemp;
		}

		v2 = "," + v2;

	}

	v = v.toString().replace(/\$|\./g, '');
	if (isNaN(v))
		v = "0";

	sign = (v == (v = Math.abs(v)));
	v = Math.floor(v * 100 + 0.50000000001);
	cents = v % 100;
	v = Math.floor(v / 100).toString();

	if (cents < 10)
		cents = "0" + cents;
	for ( var i = 0; i < Math.floor((v.length - (1 + i)) / 3); i++)
		v = v.substring(0, v.length - (4 * i + 3)) + '.'
				+ v.substring(v.length - (4 * i + 3));

	return (((sign) ? '' : '-') + v) + v2;

}

function popitup(url) {
	newwindow = window.open(url, 'name',
			'dialog=yes, height=600px, width=600, scrollbars=yes');
	if (window.focus) {
		newwindow.focus()
	}
	return false;

	// window.open(url,'name','dialog=yes, resizable=no, height=800,
	// width=565');
}

function CheckNumericKeyInfo(value) {
	if (!/^\d+$/.test(value))
		return false;
	return true;
}

// check input ma so thue
function checkMaSoThue(masothue) {

	if (masothue.length > 14)
		return false;
	msttext = formatMaSoThue(masothue);

	if (msttext == "0000000000" && !IsNumeric(msttext))

		return false;

	// }
	var kq = 0;
	kq = kq + msttext.substr(0, 1) * 31;
	kq = kq + msttext.substr(1, 1) * 29;
	kq = kq + msttext.substr(2, 1) * 23;
	kq = kq + msttext.substr(3, 1) * 19;
	kq = kq + msttext.substr(4, 1) * 17;
	kq = kq + msttext.substr(5, 1) * 13;
	kq = kq + msttext.substr(6, 1) * 7;
	kq = kq + msttext.substr(7, 1) * 5;
	kq = kq + msttext.substr(8, 1) * 3;

	if (msttext.substr(9, 1) == (10 - (kq % 11))) {
		// alert("Ma so thue khong hop le");
		// alert("MA SO THUE"+kq);
		return true;
	} else {
		return false;
	}

}
function IsNumeric(sText)

{
	var ValidChars = "0123456789.";
	var IsNumber = true;
	var Char;

	for (i = 0; i < sText.length && IsNumber == true; i++) {
		Char = sText.charAt(i);
		if (ValidChars.indexOf(Char) == -1) {

			IsNumber = false;
		}
	}

	return IsNumber;

}

function formatMaSoThue(number) {
	if (number.length >= 10) {

		number = number.substr(0, 10);

	}
	return number;

}


