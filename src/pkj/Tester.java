package pkj;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Tester {
	
	public Tester() {
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
//		testLogAnalyzer();
//		testUniqueIP();
//		testPrintNum();
//		testSomeday();
//		testCountunique();
//		testCountVisitsPerIP();
//		testMostNumberVisitsByIP();
//		testiPsForDays();
		testiPsWithMostVisitsOnDay();
	}
	
	public static void testLogAnalyzer() throws FileNotFoundException {
		LogAnalyzer la = new LogAnalyzer();
		la.readFile();
		la.printAll();
	}
	
	public static void testLogEntry() {
	        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
	        System.out.println(le);
	        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
	        System.out.println(le2);
	}
	
	public static void testUniqueIP() throws FileNotFoundException{
		LogAnalyzer la = new LogAnalyzer();
		la.readFile();
		System.out.println("Number of unique ips: " + la.countUniqueIPs().size());
	}
	
	public static void testPrintNum() throws FileNotFoundException{
		LogAnalyzer la = new LogAnalyzer();
		la.readFile();
		la.printAllHigherThanNum(400);
	}
	
	public static void testSomeday() throws FileNotFoundException{
		LogAnalyzer la = new LogAnalyzer();
		la.readFile();
		ArrayList<String> ip = la.uniqueIPVisitsOnDay("Sep 24");
		System.out.println("unique IP Visits On Day" + ip.size());
	}
	
	public static void testCountunique() throws FileNotFoundException{
		LogAnalyzer la = new LogAnalyzer();
		la.readFile();
		int low = 400;
		int high = 499;
		System.out.println("the ips of range from " + low + " to " + high + " = " + la.countUniqueIPsInRange(low, high));
	}
	
	public static void testCountVisitsPerIP() throws FileNotFoundException{
		LogAnalyzer la = new LogAnalyzer();
		la.readFile();
		HashMap<String,Integer> count = la.countVisitsPerIP();
		System.out.println(count);
	}
	
	public static void testMostNumberVisitsByIP() throws FileNotFoundException{
		LogAnalyzer la = new LogAnalyzer();
		la.readFile();
		HashMap<String,Integer> count = la.countVisitsPerIP();
		System.out.println("most number of visits in the file: " + la.mostNumberVisitsByIP(count));
		ArrayList<String> al = la.iPsMostVisits(count);
		System.out.println("The IPs:");
		for(String s: al) {
			System.out.println(s);
		}
	}
	
	public static void testiPsForDays() throws FileNotFoundException{
		LogAnalyzer la = new LogAnalyzer();
		la.readFile();
		HashMap<String,ArrayList<String>> count = la.iPsForDays();
		System.out.println(count);
		System.out.println("The day with the most visited is: " + la.dayWithMostIPVisits(count));
	}
	
	public static void testiPsWithMostVisitsOnDay() throws FileNotFoundException{
		LogAnalyzer la = new LogAnalyzer();
		la.readFile();
		HashMap<String,ArrayList<String>> count = la.iPsForDays();
		String day = "Sep 30";
		ArrayList<String> al = la.iPsWithMostVisitsOnDay(count, day);
		System.out.println(al);
	}
}
