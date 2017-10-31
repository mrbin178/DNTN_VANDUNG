package ts24.com.vn.web.util;

import java.util.*;
import java.net.*;
import java.text.*;
import java.util.zip.*;
import java.io.*;
import java.sql.*;

public class FileManager {
	private LogManager log = LogManager.getInstance();

	// FEATURES
	boolean NATIVE_COMMANDS = true;

	/**
	 * If true, all operations (besides upload and native commands) which change
	 * something on the file system are permitted
	 */

	// If true, uploads are allowed even if READ_ONLY = true
	boolean ALLOW_UPLOAD = true;

	// Allow browsing and file manipulation only in certain directories
	boolean RESTRICT_BROWSING = false;

	// If true, the user is allowed to browse only in RESTRICT_PATH,
	// if false, the user is allowed to browse all directories besides
	// RESTRICT_PATH
	boolean RESTRICT_WHITELIST = false;

	// Paths, sperated by semicolon
	// private static final String RESTRICT_PATH = "C:\\CODE;E:\\"; //Win32:
	// Case important!!
	String RESTRICT_PATH = "/etc;/var";

	// The refresh time in seconds of the upload monitor window
	int UPLOAD_MONITOR_REFRESH = 2;

	// The number of colums for the edit field
	int EDITFIELD_COLS = 85;

	// The number of rows for the edit field
	int EDITFIELD_ROWS = 30;

	// Open a new window to view a file
	boolean USE_POPUP = true;

	/**
	 * If USE_DIR_PREVIEW = true, then for every directory a tooltip will be
	 * created (hold the mouse over the link) with the first DIR_PREVIEW_NUMBER
	 * entries. This can yield to performance issues. Turn it off, if the
	 * directory loads to slow.
	 */
	boolean USE_DIR_PREVIEW = false;

	int DIR_PREVIEW_NUMBER = 10;

	/**
	 * The name of an optional CSS Stylesheet file
	 */

	/**
	 * The compression level for zip file creation (0-9) 0 = No compression 1 =
	 * Standard compression (Very fast) ... 9 = Best compression (Very slow)
	 */
	int COMPRESSION_LEVEL = 1;

	/**
	 * The FORBIDDEN_DRIVES are not displayed on the list. This can be usefull,
	 * if the server runs on a windows platform, to avoid a message box, if you
	 * try to access an empty removable drive (See KNOWN BUGS in Readme.txt).
	 */
	String[] FORBIDDEN_DRIVES = {"a:\\"};

	/**
	 * Command of the shell interpreter and the parameter to run a programm
	 */
	String[] COMMAND_INTERPRETER = {"cmd", "/C"}; // Dos,Windows

	// private static final String[] COMMAND_INTERPRETER = {"/bin/sh","-c"}; //
	// Unix

	/**
	 * Max time in ms a process is allowed to run, before it will be terminated
	 */
	long MAX_PROCESS_RUNNING_TIME = 30 * 1000; // 30 seconds

	boolean dd = true;

	public FileManager() {
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

	/**
	 * Copies all data from in to out
	 * 
	 * @param in
	 *            the input stream
	 * @param out
	 *            the output stream
	 * @param buffer
	 *            copy buffer
	 */
	static void copyStreamsWithoutClose(InputStream in, OutputStream out,
			byte[] buffer) throws IOException {
		int b;

		while ((b = in.read(buffer)) != -1) {
			out.write(buffer, 0, b);
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

	public int UnzipFile(String sFile) {
		File f = new File(sFile);
		String root = f.getParent();
		int chkUnzip = 0;
		try {
			ZipFile zf = new ZipFile(f);
			Enumeration entries = zf.entries();
			// First check whether a file already exist
			boolean error = false;
			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				if (!entry.isDirectory()
						&& new File(root + File.separator + entry.getName())
								.exists()) {
					chkUnzip = 1; // Cannot unzip. File already exists.
					error = true;
					break;
				}
			}
			if (!error) {
				// Unzip File
				entries = zf.entries();
				byte buffer[] = new byte[0xffff];
				while (entries.hasMoreElements()) {
					ZipEntry entry = (ZipEntry) entries.nextElement();
					File n = new File(root + File.separator + entry.getName());
					if (entry.isDirectory()) {
						n.mkdirs();
					} else {
						n.getParentFile().mkdirs();
						n.createNewFile();
						copyStreams(zf.getInputStream(entry),
								new FileOutputStream(n), buffer);
					}
				}
				zf.close();
				chkUnzip = 0; // Unzip was successfull
			}
		} catch (ZipException ex) {
			chkUnzip = 2; // Cannot unzip. No valid zip file
			log.log("Unzip error 2: " + ex.toString());
		} catch (IOException ex) {
			chkUnzip = 3; // Error

		}
		return chkUnzip;
	}

	public int WriteFiles(String sNameFile, String sDatabase, String sPathUpload)
			throws IOException {

		// Delete Files
		int chkDelete = 0;

		// String nameOfTextFile = "/usr/anil/imp.txt";
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(sNameFile));
			pw.println("goldencad_CONNECTION_POOL_NAME=mySQLPool");
			pw.println("goldencad_JDBC_DRIVER=org.gjt.mm.mysql.Driver");
			pw.println("goldencad_JDBC_URL=jdbc:mysql://192.168.0.120/"
					+ sDatabase.toLowerCase()
					+ "?useUnicode=true&characterEncoding=utf-8");
			pw.println("goldencad_JDBC_LOGIN=root");
			pw.println("goldencad_JDBC_PWD=admin");
			pw.println("goldencad_JDBC_SERVER_URL=");
			pw.println("goldencad_JDBC_SERVER_PWD=");
			pw.println("goldencad_MAIL_CONTACT=ainhi2006@yahoo.com");
			pw.println("goldencad_ADMIN_USER=");
			pw.println("goldencad_ADMIN_PWD=");
			pw.println("goldencad_ROWS=10");

			pw.println("goldencad_SMTP_HOST_NAME=mail.goldeniso.com");
			pw.println("goldencad_FROM_HOST_NAME=quandat@goldeniso.com");
			pw.println("goldencad_SMTP_AUTH_USER=quandat@goldeniso.com");
			pw.println("goldencad_SMTP_AUTH_PWD=quandatemail");

			pw.println("goldencad_ADV_IMAGE_PATH=");
			pw.println("goldencad_NEWS_IMAGE_PATH=");
			pw.println("goldencad_ADV_UPLOAD_PATH=" + sPathUpload
					+ "/quanlitymanual");
			pw.println("goldencad_UPLOAD_PATH_PROCEDURE=" + sPathUpload
					+ "/procedure");
			pw.println("goldencad_UPLOAD_PATH_OUTDOCUMENT=" + sPathUpload
					+ "/outdocument");
			pw.println("goldencad_UPLOAD_PATH_FILE=" + sPathUpload + "/file");
			pw.println("goldencad_UPLOAD_PATH_RECORD=" + sPathUpload
					+ "/record");
			pw.println("goldencad_UPLOAD_PATH_ATTACH=" + sPathUpload
					+ "/attachment");
			pw.println("goldencad_NEWS_UPLOAD_PATH=");
			pw.println("goldencad_IP=192.168.0");

			pw.println("goldencad_LOG_FILE_NAME=");
			pw.println("goldencad_CONNECTION_LOG_FILE_NAME=");

			// pw.println(str);
			// clean up
			pw.close();
		} catch (IOException e) {

		}

		return chkDelete;
	}

	public int DeleteFiles(String sFiles) throws IOException {

		// Delete Files
		int chkDelete = 0;
		String[] aFile = {sFiles};
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

	public int WriteFileTxt(String sPathUpload, String sNameFile)
			throws IOException {
		// Delete Files
		int chkDelete = 0;
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(sPathUpload
					+ sNameFile));
			// clean up
			pw.close();

		} catch (IOException e) {
		}

		return chkDelete;
	}

	public int WriteFileTxtBarcode(String sPathUpload, String sNameFile,
			String sContent) throws IOException {
		// Delete Files
		int chkDelete = 0;
		try {
			String sContent_ = "";

			sContent_ = this.getContents(sPathUpload + sNameFile);

			PrintWriter pw = new PrintWriter(new FileOutputStream(sPathUpload
					+ sNameFile));
			pw.print(sContent_.replaceAll("~null~", "~~"));
			pw.print(sContent.replaceAll("~null~", "~~"));
			// clean up
			pw.close();

		} catch (IOException e) {
		}

		return chkDelete;
	}

	public String getContents(String aFile) {

		StringBuilder contents = new StringBuilder();

		try {

			BufferedReader input = new BufferedReader(new FileReader(aFile));
			try {
				String line = null; // not declared within while loop

				while ((line = input.readLine()) != null) {
					contents.append(line);
					contents.append(System.getProperty("line.separator"));
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return contents.toString();
	}

	// public static byte[] readFile(String file) throws IOException {
	// return readFile(new File(file));
	// }
	/** Read file return file type byte[] **/
	public static byte[] readFileToBytes(File file) throws IOException {
		// Open file
		RandomAccessFile f = new RandomAccessFile(file, "r");
		try {
			// Get and check length
			long longlength = f.length();
			int length = (int) longlength;
			if (length != longlength)
				throw new IOException("File size >= 2 GB");
			// Read file and return data
			byte[] data = new byte[length];
			f.readFully(data);
			return data;
		} finally {
			f.close();
		}
	}

	/** Write bytes to file **/
	public static void writeBytesToFile(File fileName, byte[] bytes)
			throws IOException {
		BufferedOutputStream bos = null;

		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			bos = new BufferedOutputStream(fos);
			bos.write(bytes);
		} finally {
			if (bos != null) {
				try {
					// flush and close the BufferedOutputStream
					bos.flush();
					bos.close();
				} catch (Exception e) {
				}
			}
		}
	}

	public static Object bytesToObject(byte[] bytes) throws IOException,
			ClassNotFoundException {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ObjectInputStream is = new ObjectInputStream(bais);
		return is.readObject();
	}

	public static int uploadFile(String pathFile, byte[] object) {

		try {

			File file = new File(pathFile);

			synchronized (object) {
				try {

					InputStream myInputStream = new ByteArrayInputStream(object);

					BufferedOutputStream out = new BufferedOutputStream(
							new FileOutputStream(file));
					BufferedInputStream in = new BufferedInputStream(
							myInputStream);

					byte[] buffer = new byte[1024];
					while (true) {
						int bytesRead = in.read(buffer);
						if (bytesRead == -1)
							break;
						out.write(buffer, 0, bytesRead);
					}
					in.close();
					out.close();

					if (file.exists())
						return 1;
					else
						return 0;

				} catch (Exception e) {
					// e.printStackTrace();
					removeFile(pathFile); // remove file
					return 0;
				}

			}
		} catch (Exception ex) {
			// ex.printStackTrace();
			removeFile(pathFile); // remove file
			return 0;
		}

	}
	public static boolean removeFile(String path) {

		try {
			File file = new File(path);
			if (file.exists()) {
				return file.delete();
			} else {
				// logger.debug("Folder was exist!");
			}
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public static String readByteToString(byte[] object) {

		synchronized (object) {
			try {
				StringBuffer content = new StringBuffer();
				// return content.toString();

				InputStream myInputStream = new ByteArrayInputStream(object);

				// BufferedOutputStream out = new BufferedOutputStream(new
				// FileOutputStream(file));
				BufferedInputStream in = new BufferedInputStream(myInputStream);

				byte[] buffer = new byte[1];
				while (true) {
					int bytesRead = in.read(buffer);

					if (bytesRead == -1)
						break;

					if (buffer[0] > 31) {

						content.append(new String(buffer, 0, bytesRead, "utf-8"));
					}

					// content.append( System.getProperty( "line.separator" ) );
					// content.replace(0, content.length(), "ucs-2");
					// out.write(buffer, 0, bytesRead);
				}
				in.close();
				// out.close();
				return content.toString();
			} catch (Exception e) {
				// e.printStackTrace();
				// removeFile(pathFile); // remove file
				return "";
			}

		}
	}

	public static String readByteToString_(byte[] object) {
		synchronized (object) {
			try {

				InputStream in = new ByteArrayInputStream(object);

				DataInputStream input = new DataInputStream(in);
				boolean eof = false;

				while (eof == false) {
					char[] value = {'0', '1'};
					int b = input.readByte();
					if (b == -1) {
						eof = true;
					}
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < 8; ++i) {
						sb.append(value[b % 2]);
						b /= 2;
					}
					System.out.println(sb.reverse());
				}
				input.close();
				return "";
				//
			} catch (Exception e) {
				// e.printStackTrace();
				// removeFile(pathFile); // remove file
				return "";
			}
		}
	}

	public static String readByteTest(byte[] object) {

		synchronized (object) {
			try {
				StringBuffer content = new StringBuffer();
				// return content.toString();

				InputStream myInputStream = new ByteArrayInputStream(object);

				// BufferedOutputStream out = new BufferedOutputStream(new
				// FileOutputStream(file));
				BufferedInputStream in = new BufferedInputStream(myInputStream);

				byte[] buffer = new byte[1];
				while (true) {
					int bytesRead = in.read(buffer);

					if (bytesRead == -1)
						break;

					// for(int i = 0; i < 2; ++i) {

					if (buffer[0] > 0) {

						content.append(new String(buffer, 0, bytesRead, "utf-8"));
						// }
					}
					// content.append( System.getProperty( "line.separator" ) );
					// content.replace(0, content.length(), "ucs-2");
					// out.write(buffer, 0, bytesRead);
				}
				in.close();
				// out.close();
				return content.toString();
			} catch (Exception e) {
				// e.printStackTrace();
				// removeFile(pathFile); // remove file
				return "";
			}

		}
	}

	public static String getChuoiCQBH() {
		String chuoi = "";
		chuoi += "<CoQuanBH>%CoQuanBH%</CoQuanBH>";
		chuoi += "<CoQuanChuQuan>%CoQuanChuQuan%</CoQuanChuQuan>";

		chuoi += "<Thang>%Thang%</Thang>";
		chuoi += "<Quy>%Quy%</Quy>";
		chuoi += "<Nam>%Nam%</Nam>";

		chuoi += "<TenDonVi>%TenDonVi%</TenDonVi>";
		chuoi += "<MaDonVi>%MaDonVi%</MaDonVi>";
		chuoi += "<DiaChiDonVi>%DiaChiDonVi%</DiaChiDonVi>";
		chuoi += "<DiaChiBH>%DiaChiBH%</DiaChiBH>";
		chuoi += "<SoTKNH>%SoTKNH%</SoTKNH>";
		chuoi += "<TenNganHang>%TenNganHang%</TenNganHang>";

		return chuoi;
	}

	public static String getChuoiNguoiKy() {
		String chuoi = "";
		chuoi += "<NguoiLap>%NguoiLap%</NguoiLap><NguoiKy1>%NguoiKy1% </NguoiKy1><NguoiKy2> %NguoiKy2%</NguoiKy2></C12>";

		return chuoi;
	}
	public static String readFileToString(String file) {
		StringBuilder contents = new StringBuilder();
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF8"));
			try {
				String line = null; // not declared within while loop
				while ((line = input.readLine()) != null) {
					contents.append(line);
					contents.append(System.getProperty("line.separator"));
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return contents.toString();
	}

}
