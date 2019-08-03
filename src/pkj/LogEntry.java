package pkj;

import java.util.Date;

public class LogEntry {
	
	private String ipAdrress;
	private Date accessTime;
	private String request;
	private int statusCode;
	private int bytesReturned;
	
	public LogEntry(String ip,Date access,String req,int status,int bytes) {
		this.ipAdrress = ip;
		this.accessTime = access;
		this.request = req;
		this.statusCode = status;
		this.bytesReturned = bytes;
	}
	
	@Override
	public String toString() {
		return ipAdrress + " " + accessTime + " " + request + " " + statusCode + " " + bytesReturned;
	}
	
	public String getIp() {
		return this.ipAdrress;
	}
	
	public String getRequest() {
		return this.request;
	}
	
	public int getStatusCode() {
		return this.statusCode;
	}
	
	public int getBytesReturned() {
		return this.bytesReturned;
	}
	
	public Date getAccessTime() {
		return this.accessTime;
	}
}
