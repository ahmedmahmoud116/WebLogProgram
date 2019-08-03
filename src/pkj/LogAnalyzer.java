package pkj;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class LogAnalyzer {
	
	private ArrayList<LogEntry> records;
	
	public LogAnalyzer() {
		records = new ArrayList<LogEntry>();
	}
	
	public void readFile() throws FileNotFoundException {
		records.clear();
		DirectoryResource dr = new DirectoryResource();
		Scanner sc = null;
		for(File f : dr.selectedFiles()) {
			sc = new Scanner(f);
			while(sc.hasNextLine()) {
					String line  = sc.nextLine();
					records.add(WebLogParser.parseEntry(line));
			}
		}
		sc.close();
	}
	
	public void printAll() {
		for(LogEntry le : records) {
			System.out.println(le);
		}
	}
	
	public ArrayList<String> countUniqueIPs() {
		ArrayList<String> uniqueIp = new ArrayList<String>();
		for(LogEntry le : records) {
			String ip = le.getIp();
			if(!uniqueIp.contains(ip)) {
				uniqueIp.add(ip);
			}
		}
		return uniqueIp;
	}
	
	public void printAllHigherThanNum(int num) {
		for(LogEntry le : records) {
			if(le.getStatusCode() > num) {
				System.out.println(le);
			}
		}
	}
	
	public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
		ArrayList<String> ipvisit = new ArrayList<String>();
		
		for(LogEntry le : records) {
			String ip = le.getIp();
			
			Date d = le.getAccessTime();
			
			String date = d.toString().substring(4,10);	
			
			if(date.equalsIgnoreCase(someday) && !ipvisit.contains(le.getIp())) {
				ipvisit.add(ip);
			}
			
		}
		return ipvisit;
	}
	
	public int countUniqueIPsInRange(int low,int high) {
		ArrayList<String> ip = new ArrayList<String>();
		ArrayList<String> uniqueip = new ArrayList<String>();
		for(LogEntry le : records) {
			if(le.getStatusCode() >= low && le.getStatusCode() <= high && !uniqueip.contains(le.getIp())) {
				System.out.println(le);
				ip.add(le.getIp());
				uniqueip.add(le.getIp());
			}
		}
		return ip.size();
	}
	
	public HashMap<String,Integer> countVisitsPerIP() {
		HashMap<String, Integer> count = new HashMap<String,Integer>();
		for(LogEntry le : records) {
			String ip = le.getIp();
			if(!count.containsKey(ip)) {
				count.put(ip, 1);
			}
			else {
				count.put(ip, count.get(ip) + 1);
			}
		}
		return count;
	}
	
	public int mostNumberVisitsByIP(HashMap<String,Integer> count) {
		int max = 0;
		int indx = 0;
		for(LogEntry le: records) {
			if(count.containsKey(le.getIp()))
			 indx  = count.get(le.getIp());
			if(max< indx)
				max = indx;
		}
		return max;
	}
	
	public ArrayList<String> iPsMostVisits(HashMap<String,Integer> count) {
		ArrayList<String> al = new ArrayList<String>();
		int max = mostNumberVisitsByIP(count);

		for(String s: count.keySet()) {
			if(max == count.get(s)) {
				al.add(s);
			}
		}
		return al;
	}
	
	public HashMap<String,ArrayList<String>> iPsForDays(){
		HashMap<String,ArrayList<String>> count = new HashMap<String,ArrayList<String>>();
		for(LogEntry le:records) {
			String ip = le.getIp();
			
			Date d = le.getAccessTime();
			
			String date = d.toString().substring(4,10);	
			
			if(!count.containsKey(date)) {
				 ArrayList<String> al = new  ArrayList<String>();
				 al.add(ip);
				count.put(date, al);
			}
			else {
				 ArrayList<String> al = count.get(date);
				 al.add(ip);
				 count.put(date, al);
			}
			
		}
		return count;
	}
	
	public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> count) {
		int max = 0;
		String day = "";
		for(String date:count.keySet()) {
			ArrayList<String> al = count.get(date);
			if(max< al.size()) {
				max = al.size();
				day = date;
			}
		}
		return day;
	}
	
	public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> count,String day){
		ArrayList<String> visit = count.get(day);
		System.out.println(visit);
		HashMap<String,Integer> counter = new HashMap<String,Integer>();
		for(String date : visit) {
			if(!counter.containsKey(date)) {
				counter.put(date, 1);
			}
			else {
				counter.put(date,counter.get(date) + 1);
			}
		}
		visit = iPsMostVisits(counter);
		return visit;
		
	}
}
