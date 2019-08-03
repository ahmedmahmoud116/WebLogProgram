package pkj;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebLogParser {
	private static StringBuilder sb;
	private static final SimpleDateFormat sd = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z");
	
	public WebLogParser() {
		
	}

	private static String cut(String del) {
		int indx  = sb.indexOf(del);
		if(indx == -1) {
			indx = sb.length();
		}
		String ret = sb.substring(0,indx);
		sb.delete(0, indx + del.length());
		return ret;
	}
	
	public static LogEntry parseEntry(String line) {
		//110.76.104.12 - - [30/Sep/2015:07:47:11 -0400] "GET //favicon.ico HTTP/1.1" 200 3426
		sb = new StringBuilder(line);
		String del = " ";
		String ip = cut(del);
		cut(del);		// to escape -
		cut(del);		// to escape the second -
		cut("[");
		
		String dates = cut("]");
		Date date = parseDate(dates); //parsing the date text to date object
		
		cut("\""); //escape the first " 
		String req = cut("\"");	//get the request 
		
		cut(del); //escape the space
		String state = cut(del); //get the status and parse it into int
		int status = Integer.parseInt(state);
		
		String bytes = cut(del); //get the status and parse it into int
		int byterec = Integer.parseInt(bytes);

		return new LogEntry(ip, date, req, status, byterec);
	}
	
	public static Date parseDate(String date) {
		Date d = sd.parse(date, new ParsePosition(0));
		return d;
	}
}
