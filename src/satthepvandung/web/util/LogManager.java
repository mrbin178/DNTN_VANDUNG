package satthepvandung.web.util;

//Processed by NMI's Java Code Viewer 4.8.1 © 1997-2000 B. Lemaire
//Website: http://njcv.htmlplanet.com	E-mail: info@njcv.htmlplanet.com
//Copy registered to Evaluation Copy
//Source File Name:   LogManager.java



import java.io.*;
import java.text.DateFormat;
import java.util.Calendar;

public class LogManager {

 public static final int LOG_LEVEL_INFO = 1;
 public static final int LOG_LEVEL_WARNING = 2;
 public static final int LOG_LEVEL_ERROR = 3;
 public static final int LOG_LEVEL_DEBUG = 4;
 private int logLevel;
 private boolean logInStream;
 private boolean logInConsole;
 private boolean logInFile;
 private PrintWriter mWriter;
 private PrintStream out;
 private StringBuffer composedMsg;
 private static LogManager instance = null;

 protected LogManager() {
     logLevel = 1;
     logInStream = false;
     logInConsole = true;
     logInFile = false;
     out = System.out;
     composedMsg = new StringBuffer();
 }

 public static LogManager getInstance() {
     if(instance == null)
         instance = new LogManager();
     return instance;
 }

 private boolean isInLogLevel(int _logLevel) {
     return _logLevel <= logLevel;
 }

 public void log(String _msg, int _logLevel) {
     if(isInLogLevel(_logLevel)) {
         StringBuffer logMsg = new StringBuffer(getCurrentDate() + ", ");
         String strLogLevel = null;
         switch(_logLevel) {
         case 4: // '\004'
             strLogLevel = "(DEBUG)";
             break;

         case 3: // '\003'
             strLogLevel = "(ERROR)";
             break;

         case 1: // '\001'
             strLogLevel = "(INFO)";
             break;

         case 2: // '\002'
             strLogLevel = "(WARNING)";
             break;
         }
         logMsg.append(strLogLevel + ", ");
         logMsg.append(_msg);
         String strLogMsg = logMsg.toString();
         if(isLogInFile())
             doLogInFile(strLogMsg);
         if(isLogInConsole())
             doLogInConsole(strLogMsg);
         doLogInStream(strLogMsg);
     }
 }

 public void log(String _msg) {
     log(_msg, 4);
 }

 public void logStart() {
     composedMsg.setLength(0);
 }

 public void logAppend(String _msg) {
     composedMsg.append(_msg);
 }

 public void logEnd(int _logLevel) {
     String logMsg = composedMsg.toString();
     composedMsg.setLength(0);
     log(logMsg, _logLevel);
 }

 public void logEnd() {
     logEnd(4);
 }

 private String getCurrentDate() {
     DateFormat df = DateFormat.getDateTimeInstance(1, 1);
     java.util.Date dat = Calendar.getInstance().getTime();
     String res = df.format(dat);
     return res;
 }

 public void setLogLevel(int _logLevel) {
     if(_logLevel > 0)
         logLevel = _logLevel;
 }

 public int getLogLevel() {
     return logLevel;
 }

 public void setLogInStream(boolean _logInStream) {
     logInStream = _logInStream;
 }

 public boolean isLogInStream() {
     return logInStream;
 }

 public void setLogInConsole(boolean _logInConsole) {
     logInConsole = _logInConsole;
 }

 public boolean isLogInConsole() {
     return logInConsole;
 }

 public void setLogInFile(boolean _logInFile) {
     logInFile = _logInFile;
 }

 public boolean isLogInFile() {
     return logInFile;
 }

 private void doLogInConsole(String _msg) {
     System.out.println(_msg);
 }

 private void doLogInStream(String _msg) {
     try {
         mWriter.println(_msg);
         mWriter.flush();
     }
     catch(Exception e) { }
 }

 private void doLogInFile(String _msg) {
     try {
         String logFile = "LOG_FILE_NAME";
         if(logFile != null) {
             PrintStream ps = new PrintStream(new FileOutputStream(logFile, true));
             System.setOut(ps);
             out = ps;
         }
     }
     catch(FileNotFoundException e) {
         e.printStackTrace();
     }
 }

 public void setWriter(PrintWriter _writer) {
     mWriter = _writer;
 }

}
